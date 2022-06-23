package cz.homeoffice.taskproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "PERSONAL_DATA")
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "POSTCODE")
    private String postcode;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;
}
