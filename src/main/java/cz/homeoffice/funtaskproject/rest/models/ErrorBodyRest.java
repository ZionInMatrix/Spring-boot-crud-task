package cz.homeoffice.funtaskproject.rest.models;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorBodyRest {
    private Integer httpCode;
    private HttpStatus httpStatus;
    private String message;

    public void setHttpStatus(HttpStatus httpStatus) {
        httpCode = httpStatus.value();
        this.httpStatus = httpStatus;
    }
}
