package io.github.vitor0x5.shared.exceptions.types;

public class BusinessException extends RuntimeException{

    public static String EMAIL_ALREADY_USED = "Email address already used";
    public static String INCORRECT_USER = "Incorrect user";

    public BusinessException(String message) {
        super(message);
    }
}
