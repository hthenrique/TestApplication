package ht.henrique.mazebank.exception;

import ht.henrique.mazebank.util.type.ReturnCode;
import org.springframework.http.HttpStatus;

public class DatabaseException extends BaseException{

    public DatabaseException(ReturnCode returnCode, String message){
        super(returnCode, message);
    }
}
