package ht.henrique.mazebank.exception;

import ht.henrique.mazebank.model.error.ErrorTemplate;
import ht.henrique.mazebank.model.error.Errors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@Slf4j
public class DefaultExceptionHandle {

    @ExceptionHandler(value = {ControllerException.class})
    public ResponseEntity<ErrorTemplate> ControllerException(ControllerException exception){
        return handleException(exception);
    }

    @ExceptionHandler(value = {DatabaseException.class})
    public ResponseEntity<ErrorTemplate> DatabaseException(DatabaseException exception){
        return handleException(exception);
    }


    public ResponseEntity<ErrorTemplate> handleException(BaseException exception) {

        List<Errors> errors = new ArrayList<>();
        errors.add(new Errors(exception.getMessage()));

        ErrorTemplate errorTemplate = new ErrorTemplate();
        errorTemplate.setErrorCode(exception.getReturnCode().getReturnCode());
        errorTemplate.setErrorMessage(exception.getReturnCode().getMessageId());
        errorTemplate.setErrors(errors);

        Date date = new Date();
        errorTemplate.setTimeStamp(new Timestamp(date.getTime()).toString());
        log.info(errorTemplate.toString());
        return ResponseEntity.status(exception.getReturnCode().getHttpStatus()).body(errorTemplate);
    }
}
