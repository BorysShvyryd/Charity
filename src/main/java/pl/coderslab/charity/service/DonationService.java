package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public int sumOfAllBagsReturned() {
        return donationRepository.findAll()
                .stream()
                .mapToInt(Donation::getQuantity)
                .sum();
    }

    public Long count() {
        return donationRepository.count();
    }

    public void save(Donation donation) {
        donationRepository.save(donation);
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    public List<Donation> findAllByUserSortByStatus(User user) {
        return donationRepository.findAllByUser(user).stream()
                .sorted(Comparator.comparing(Donation::getDateTimeTransmitted, Comparator.nullsLast(Comparator.reverseOrder())))
                .sorted(Comparator.comparing(Donation::getDateTimeReceived, Comparator.nullsLast(Comparator.reverseOrder())))
                .sorted(Comparator.comparingInt(Donation::getStatus))
                .collect(Collectors.toList());
    }

    public List<Donation> findAllSortByStatus() {
        return donationRepository.findAll().stream()
                .sorted(Comparator.comparing(Donation::getDateTimeTransmitted, Comparator.nullsLast(Comparator.reverseOrder())))
                .sorted(Comparator.comparing(Donation::getDateTimeReceived, Comparator.nullsLast(Comparator.reverseOrder())))
                .sorted(Comparator.comparingInt(Donation::getStatus))
                .collect(Collectors.toList());
    }

    public Donation getById(Long id) {
        return donationRepository.getById(id);
    }

    public Stream<Donation> donationsStreemChange(String stream_change, String querySearch) {

        String[] operations = stream_change.split(";");

        Stream<Donation> donationsStream;

        if ("".equals(stream_change))
            return findAllSortByStatus().stream();

        if (querySearch == null) {
            donationsStream = findAll().stream();
        } else {
            donationsStream = findAllSortByStatus().stream();
        }

        for (String operation : operations) {
            switch (operation.split("=")[0]) {
                case "sort":
                    switch (operation.split("=")[1]) {
                        case "institution_up":
                            donationsStream = donationsStream.sorted((o1, o2) ->
                                    o1.getInstitution().getName().compareTo(o2.getInstitution().getName()));
                            break;
                        case "institution_down":
                            donationsStream = donationsStream.sorted((o1, o2) ->
                                    o2.getInstitution().getName().compareTo(o1.getInstitution().getName()));
                            break;
                        case "address_up":
                            donationsStream = donationsStream.sorted((o1, o2) ->
                                    o1.getStreet().compareTo(o2.getStreet()));
                            break;
                        case "address_down":
                            donationsStream = donationsStream.sorted((o1, o2) ->
                                    o2.getStreet().compareTo(o1.getStreet()));
                            break;
                        case "username_up":
                            donationsStream = donationsStream.sorted((o1, o2) ->
                                    (o1.getUser().getName() + o1.getUser().getSurname())
                                            .compareTo(o2.getUser().getName() + o2.getUser().getSurname()));
                            break;
                        case "username_down":
                            donationsStream = donationsStream.sorted((o1, o2) ->
                                    (o2.getUser().getName() + o2.getUser().getSurname())
                                            .compareTo(o1.getUser().getName() + o1.getUser().getSurname()));
                            break;
                    }

                case "filter":
                    switch (operation.split("=")[1]) {
                        case "username":
                            donationsStream = donationsStream.filter(o ->
                                    (o.getUser().getSurname() + o.getUser().getName()).toLowerCase()
                                            .contains(querySearch.toLowerCase()));
                            break;
                        case "institution":
                            donationsStream = donationsStream.filter(o ->
                                    o.getInstitution().getName().toLowerCase()
                                            .contains(querySearch.toLowerCase()));
                            break;
                        case "address":
                            donationsStream = donationsStream.filter(o ->
                                    o.getStreet().toLowerCase()
                                            .contains(querySearch.toLowerCase()));
                            break;
                    }
            }
        }

        return donationsStream;
    }
}
