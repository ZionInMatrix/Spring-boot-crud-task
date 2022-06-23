package cz.homeoffice.taskproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "USER")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "SYS_DATE_CREATE")
    private LocalDateTime sysDateCreate;

    @Column(name = "SYS_DATE_EDIT")
    private LocalDateTime sysDateEdit;

    @Column(name = "SYS_LOGIN_ID_CREATE")
    private String sysLoginIdCreate;

    @Column(name = "SYS_LOGIN_ID_EDIT")
    private String sysLoginIdEdit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSONAL_DATA", referencedColumnName = "ID")
    private PersonalData personalData;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
