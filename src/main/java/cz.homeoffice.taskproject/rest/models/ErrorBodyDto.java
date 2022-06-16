package cz.homeoffice.taskproject.rest.models;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorBodyDto {

    private Integer httpCode;
    private HttpStatus httpStatus;
    private String message;
}
