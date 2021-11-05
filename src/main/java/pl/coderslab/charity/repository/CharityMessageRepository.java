package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.CharityMessage;

@Repository
public interface CharityMessageRepository extends JpaRepository<CharityMessage, Long> {
}
