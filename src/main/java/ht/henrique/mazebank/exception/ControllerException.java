package ht.henrique.mazebank.exception;

import ht.henrique.mazebank.util.type.ReturnCode;
import org.springframework.http.HttpStatus;

public class ControllerException extends BaseException {

    public ControllerException(ReturnCode returnCode, String message){
        super(returnCode, message);
    }
}
