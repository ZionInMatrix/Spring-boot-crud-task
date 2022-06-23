package cz.homeoffice.taskproject.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDataDto {

    private Long id;
    private String name;
    private String username;
    private String password;
    private PersonalDataDto personalData;
}
