package ua.nure.arkpz.demo.exception;

import java.util.HashMap;
import java.util.Map;

// TODO use in validation?
public class EntitySearchFailedException extends RuntimeException {
    private final Map<String, String> errorsMap;

    public EntitySearchFailedException(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }

    public EntitySearchFailedException() {
        errorsMap = new HashMap<>();
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }
}
