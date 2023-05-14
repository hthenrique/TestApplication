package ht.henrique.mazebank.exception;

import ht.henrique.mazebank.util.type.ReturnCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends Exception{

    private final ReturnCode returnCode;
    private final String message;

    public BaseException(ReturnCode returnCode, String message){
        this.returnCode = returnCode;
        this.message = message;
    }
}
