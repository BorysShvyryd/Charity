package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer quantity;

    @ManyToMany
    @NotNull
    private List<Category> categories;

    @OneToOne
    @NotNull
    private Institution institution;

    @NotBlank
    @Size(max = 128)
    @Column(length = 128)
    private String street;

    @NotBlank
    @Size(max = 128)
    @Column(length = 128)
    private String city;

    @NotBlank
    @Size(max = 8)
    @Column(length = 8)
    private String zipCode;

    @NotBlank
    private String phone;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @NotNull
    private LocalTime pickUpTime;

    private String pickUpComment;

    @NotNull
    @ManyToOne
    private User user;

    private byte status;

//***********************************************
//   Getters & Setters
//***********************************************

    private LocalDateTime dateTimeReceived;

    private LocalDateTime dateTimeTransmitted;

    public LocalDateTime getDateTimeReceived() {
        return dateTimeReceived;
    }

    public void setDateTimeReceived(LocalDateTime dateTimeReceived) {
        this.dateTimeReceived = dateTimeReceived;
    }

    public LocalDateTime getDateTimeTransmitted() {
        return dateTimeTransmitted;
    }

    public void setDateTimeTransmitted(LocalDateTime dateTimeTransmitted) {
        this.dateTimeTransmitted = dateTimeTransmitted;
    }
}
