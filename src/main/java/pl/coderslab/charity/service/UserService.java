package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
