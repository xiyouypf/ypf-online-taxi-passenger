package com.ypf.passenger.api.exception;

import lombok.Data;

/**
 * @author ypf
 * @date 2023/05/05 00:22
 */
@Data
public class PassengerException extends RuntimeException {
    private Integer errorCode;
    private String errorMsg;

    public PassengerException() {}

    public PassengerException(int errorCode, String errorMsg) {
        super("{\"errorCode\":" + errorCode + ",\"errorMsg\":\"" + errorMsg + "\"}");
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
