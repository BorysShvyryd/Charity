package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.User;

import java.util.List;

@Service
public interface UserService {

    User findByEmail(String userEmail);

    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void update(User user);

    void saveNewPassUser(User user);

    void delete(User user);

    int countAdmin();
}
