package pl.coderslab.charity.entity;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CurrentUserDetails implements UserDetails, Serializable, CredentialsContainer {

    private final User user;

//    public CurrentUserDetails(User user) {
//        this.user = user;
//    }

    public CurrentUserDetails(User user) {
//        super(user.getEmail(), user.getPassword(), user.getRoleSet());
        this.user = user;
    }

//    public static CurrentUserDetails fromUserToCurrentUserDetails(User user) {
//        CurrentUserDetails currentUserDetails = new CurrentUserDetails(user);
//        currentUserDetails.user = user;
//        currentUserDetails.login = user.getEmail();
//        currentUserDetails.password = user.getPassword();
//        currentUserDetails.grantedAuthorities = user.getRoleSet();
////        currentUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRoleSet().getName()));
//        return currentUserDetails;
//    }


    public User getUser() {
        return user;
    }

    @Override
    public void eraseCredentials() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoleSet();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
