package br.com.authapi.exceptions.business;


import br.com.authapi.exceptions.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusinessRuleException extends BaseRuntimeException {
    private final static String KEY = "business.rule";

    public BusinessRuleException(String message) {
        super(Map.of("message", message));
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}
