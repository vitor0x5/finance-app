package io.github.vitor0x5.shared.exceptions;

import io.github.vitor0x5.shared.exceptions.types.BusinessException;
import io.github.vitor0x5.shared.exceptions.types.LoginException;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionsHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptions handleBusinessException(BusinessException ex) {
        String message = ex.getMessage();
        return new ApiExceptions(message);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiExceptions handleNotFoundException(NotFoundException ex) {
        String message = ex.getMessage();
        return new ApiExceptions(message);
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiExceptions handleLoginException(LoginException ex) {
        String message = ex.getMessage();
        return new ApiExceptions(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptions handleNotFoundException(MethodArgumentNotValidException ex) {
        List<String> messages = ex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new ApiExceptions(messages);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptions handleInvalidDateFormatException(HttpMessageNotReadableException ex) {
        if(ex.getMessage().contains("LocalDate")) {
            return new ApiExceptions("Invalid Date format. Expected: dd-MM-yyyy");
        }
        return new ApiExceptions("Invalid payload. Please check if the values are in the correct format");
    }
}
