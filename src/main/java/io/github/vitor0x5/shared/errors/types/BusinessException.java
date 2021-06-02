package io.github.vitor0x5.shared.errors.types;

public class BusinessException extends RuntimeException{

    public static String emailAlreadyUsed = "Email address already used";
    public static String incorrectUser = "Incorrect user";

    public BusinessException(String message) {
        super(message);
    }
}
