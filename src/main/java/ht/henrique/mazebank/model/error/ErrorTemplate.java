package ht.henrique.mazebank.model.error;

import lombok.Data;

import java.util.List;

@Data
public class ErrorTemplate {

    private Integer errorCode;
    private String errorMessage;
    private String timeStamp;

    private List<Errors> errors;
}
