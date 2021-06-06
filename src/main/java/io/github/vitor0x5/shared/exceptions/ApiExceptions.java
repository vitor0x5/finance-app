package io.github.vitor0x5.shared.exceptions;

import java.util.Arrays;
import java.util.List;

public class ApiExceptions {

    private List<String> errors;

    public ApiExceptions(String errorMessage) {
        this.errors = Arrays.asList(errorMessage);
    }

    public ApiExceptions(List<String> errorMessages) {
        this.errors = errorMessages;
    }

    public List<String> getErrors() {
        return errors;
    }
}
