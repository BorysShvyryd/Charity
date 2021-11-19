package pl.coderslab.charity.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.findByName("ROLE_USER"));

        if (userRepository.count() == 0) {
            roleSet.add(roleRepository.findByName("ROLE_ADMIN"));
        }

        user.setRoleSet(roleSet);

        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveNewPassUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public int countAdmin() {
        return userRepository.countAllByRoleSetContaining(roleRepository.findByName("ROLE_ADMIN"));
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Stream<User> usersStreamChange(String stream_change, String querySearch) {
        String[] operations = stream_change.split(";");

        Stream<User> userStream = findAll().stream();

        for (String operation : operations) {
            switch (operation.split("=")[0]) {
                case "filter":
                    switch (operation.split("=")[1]) {
                        case "admin":
                            Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
                            userStream = userStream.filter(o -> o.getRoleSet().contains(roleAdmin));
                            break;
                        case "status_1":
                            userStream = userStream.filter(o -> o.getEnabled() == 1);
                            break;
                        case "status_0":
                            userStream = userStream.filter(o -> o.getEnabled() == 0);
                            break;
                        case "email":
                            userStream = userStream.filter(o ->
                                    o.getEmail().toLowerCase()
                                            .contains(querySearch.toLowerCase()));
                            break;
                        case "name":
                            userStream = userStream.filter(o -> o.getName() != null)
                                    .filter(o ->
                                            o.getName().toLowerCase()
                                                    .contains(querySearch.toLowerCase()));
                            break;
                        case "surname":
                            userStream = userStream.filter(o -> o.getSurname() != null)
                                    .filter(o ->
                                            o.getSurname().toLowerCase()
                                                    .contains(querySearch.toLowerCase()));
                            break;
                    }
                    break;
                case "sort":
                    switch (operation.split("=")[1]) {
                        case "id_up":
                            userStream = userStream.sorted((o1, o2) -> (int) (o1.getId() - o2.getId()));
                            break;
                        case "id_down":
                            userStream = userStream.sorted((o1, o2) -> (int) (o2.getId() - o1.getId()));
                            break;
                        case "email_up":
                            userStream = userStream.sorted((o1, o2) -> (o2.getEmail().compareTo(o1.getEmail())));
                            break;
                        case "email_down":
                            userStream = userStream.sorted(Comparator.comparing(User::getEmail));
                            break;
                        case "name_up":
                            userStream = userStream.sorted(Comparator.nullsLast((o1, o2) -> compareByNull(o1.getName(), o2.getName())));
                            break;
                        case "name_down":
                            userStream = userStream.sorted(Comparator.nullsLast((o1, o2) -> compareByNull(o2.getName(), o1.getName())));
                            break;
                        case "surname_up":
                            userStream = userStream.sorted(Comparator.nullsLast((o1, o2) -> compareByNull(o1.getSurname(), o2.getSurname())));
                            break;
                        case "surname_down":
                            userStream = userStream.sorted(Comparator.nullsLast((o1, o2) -> compareByNull(o2.getSurname(), o1.getSurname())));
                            break;
                    }
            }
        }

        return userStream;
    }

    public int compareByNull(String txt, String otherTxt)
    {
        if ( txt == null )
            return otherTxt == null ? 0 : 1;

        if ( otherTxt == null )
            return 1;

        return txt.compareToIgnoreCase(otherTxt);
    }

    @Override
    public User reBlockedUser(User user) {

        if (user.getEnabled() == 1) {
            user.setEnabled(0);
            log.info("User: " + user.getEmail() + " - unblocked");
        } else {
            user.setEnabled(1);
            log.info("User: " + user.getEmail() + " - blocked");
        }
        return user;
    }

}
