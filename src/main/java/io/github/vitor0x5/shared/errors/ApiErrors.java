package io.github.vitor0x5.shared.errors;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    private List<String> errors;

    public ApiErrors(String errorMessage) {
        this.errors = Arrays.asList(errorMessage);
    }

    public ApiErrors(List<String> errorMessages) {
        this.errors = errorMessages;
    }

    public List<String> getErrors() {
        return errors;
    }
}
