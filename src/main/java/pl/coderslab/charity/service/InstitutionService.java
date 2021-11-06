package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<Institution> lastFourInstitutions() {
        return institutionRepository.findAll()
                .stream()
                .sorted((o1, o2) -> o2.getId().compareTo(o1.getId()))
                .limit(4)
                .collect(Collectors.toList());
    }

    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    public List<Institution> findAllActive() {
        return institutionRepository.findAllByStatusOrderByStatus(true);
    }

    public Institution getById(Long id) {
        return institutionRepository.getById(id);
    }

    public Institution save(Institution institution) {
        return institutionRepository.save(institution);
    }
}
