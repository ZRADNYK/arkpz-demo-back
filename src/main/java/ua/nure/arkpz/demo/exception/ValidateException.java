package ua.nure.arkpz.demo.exception;

import java.util.Map;

public class ValidateException extends RuntimeException {
    private final Map<String, String> errorsMap;

    public ValidateException(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }

}
