package com.djcps.crm.commons.exception;

/**
 * Created by TruthBean on 2017/6/21 16:58.
 */
public class CrmServerException extends RuntimeException {
    public CrmServerException() {
    }

    public CrmServerException(String message) {
        super(message);
    }

    public CrmServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CrmServerException(Throwable cause) {
        super(cause);
    }

    public CrmServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
