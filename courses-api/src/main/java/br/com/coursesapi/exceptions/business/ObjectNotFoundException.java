package br.com.coursesapi.exceptions.business;

import br.com.coursesapi.exceptions.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends BaseRuntimeException {
    private static final String KEY = "object.not.found";

    public ObjectNotFoundException(String message) {
        super(Map.of("message", message));
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}
