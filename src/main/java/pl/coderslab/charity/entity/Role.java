package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name", length = 30, nullable = false)
    private String name;

//***********************************************
//   Getters & Setters
//***********************************************

    @Override
    public String getAuthority() {
        return name;
    }
}
