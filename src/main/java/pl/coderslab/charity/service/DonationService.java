package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Donation> findAllByUser(User user) {
        return donationRepository.findAllByUser(user);
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
}
