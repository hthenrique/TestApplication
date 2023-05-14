package ht.henrique.mazebank.util.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;


public enum ReturnCode {
    SUCCESS(200000, HttpStatus.OK, "service.success"),
    INVALID_CREDENTIALS(400001, HttpStatus.BAD_REQUEST, "service.invalid.credentials"),
    INVALID_PARAMETERS(400002, HttpStatus.BAD_REQUEST, "service.invalid.parameters"),
    ALREADY_USER(409000, HttpStatus.CONFLICT, "service.already.user"),
    NOT_FOUND(404000, HttpStatus.NOT_FOUND, "service.resource.not.found"),
    MORE_THAN_ONE_USER(420000, HttpStatus.UNPROCESSABLE_ENTITY, "service.more.than.one.resource"),
    INTERNAL_SERVER_ERROR(500000, HttpStatus.INTERNAL_SERVER_ERROR, "service.internal.server.error");

    @Getter
    private Integer returnCode;
    @Getter
    private HttpStatus httpStatus;
    @Getter
    private String messageId;

    ReturnCode(Integer returnCode, HttpStatus httpStatus, String messageId){
        this.returnCode = returnCode;
        this.httpStatus = httpStatus;
        this.messageId = messageId;

    }
}
