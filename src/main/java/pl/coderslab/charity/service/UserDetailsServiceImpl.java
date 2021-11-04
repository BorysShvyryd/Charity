package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.CurrentUser;
import pl.coderslab.charity.entity.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userService.findByUserName(email);

        if (user == null) {throw new UsernameNotFoundException(email); }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        user.getRoleSet().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthorities);
//        return new CurrentUser(user.getUsername(),user.getPassword(),
//                grantedAuthorities, user);

    }
}
