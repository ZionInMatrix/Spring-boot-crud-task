package cz.homeoffice.funtaskproject.rest.models;

import cz.homeoffice.funtaskproject.entity.PersonalDataDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRest {
    private Integer id;
    private String userName;
    private String email;
    private String password;
    private String accessToken;
    private PersonalDataDao personalData;

    @Override
    public String toString() {
        return "UserRest{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", personalData=" + personalData +
                '}';
    }
}
