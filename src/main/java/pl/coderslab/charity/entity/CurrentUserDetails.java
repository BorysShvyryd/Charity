package pl.coderslab.charity.entity;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class CurrentUserDetails implements UserDetails, Serializable, CredentialsContainer {

    private User user;
    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CurrentUserDetails currentUserToCurrentUserDetails(User currentUser) {
        CurrentUserDetails currentUserDetails = new CurrentUserDetails();
        currentUserDetails.login = currentUser.getEmail();
        currentUserDetails.password = currentUser.getPassword();
        currentUserDetails.grantedAuthorities = currentUser.getRoleSet();
        currentUserDetails.user = currentUser;
        return currentUserDetails;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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

    @Override
    public void eraseCredentials() {

    }
}
