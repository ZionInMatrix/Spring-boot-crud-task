package cz.homeoffice.funtaskproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class UserDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String userName;

    @Size(min = 10, max = 30, message = "Password must be between 10 and 30 characters")
    @Column(nullable = false)
    private String password;

    @Email(message = "Email should be valid")
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String accessToken;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSONAL_DATA", referencedColumnName = "id")
    private PersonalDataDao personalData;
}
