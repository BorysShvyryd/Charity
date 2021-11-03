package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.User;

@Service
public interface UserService {

    User findByUserName(String userEmail);

    void save(User user);
}
