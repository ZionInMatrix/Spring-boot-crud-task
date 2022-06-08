package cz.homeoffice.taskproject.rest.models;

import cz.homeoffice.taskproject.entity.PersonalData;
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
    private PersonalData personalData;
}
