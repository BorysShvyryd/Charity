package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 64)
    @Column(length = 64)
    private String name;

    @Size(max = 64)
    @Column(length = 64)
    private String surname;

    @Size(max = 24)
    @Column(length = 24)
    private String phone;

    @Size(max = 128)
    @Column(length = 128)
    private String address;

    @Size(max = 64)
    @Column(length = 64)
    private String city;

    @Size(max = 8)
    @Column(length = 8)
    private String zipcode;

    @Email
    @NotBlank
    @Size(max = 128)
    @Column(nullable = false, unique = true, length = 128)
    private String email;

    @Column(length = 128)
    private String password;

    @Transient
    private String password2;

    private int enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet;

}
