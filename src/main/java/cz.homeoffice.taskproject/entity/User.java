package cz.homeoffice.taskproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String userName;

    @Size(min = 10, max = 30, message = "Password must be between 10 and 30 characters")
    private String password;

    @Email(message = "Email should be valid")
    private String email;

    private String accessToken;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSONAL_DATA", referencedColumnName = "id")
    private PersonalData personalData;
}
