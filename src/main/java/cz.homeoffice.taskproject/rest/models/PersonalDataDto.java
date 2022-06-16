package cz.homeoffice.taskproject.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalDataDto {

    private Long id;
    private LocalDate dateOfBirthday;
    private String address;
    private String phoneNumber;
    private LocalDate dateOfCreation;
}
