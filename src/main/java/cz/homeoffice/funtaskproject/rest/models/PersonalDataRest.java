package cz.homeoffice.funtaskproject.rest.models;

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

    @Override
    public String toString() {
        return "PersonalDataRest{" +
                "id=" + id +
                ", dateOfBirthday=" + dateOfBirthday +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
