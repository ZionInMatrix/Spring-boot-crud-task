package cz.homeoffice.taskproject.rest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDataRest {

    private Integer id;
    private LocalDate dateOfBirthday;
    private String address;
    private String phoneNumber;
    private LocalDate dateOfCreation;
}
