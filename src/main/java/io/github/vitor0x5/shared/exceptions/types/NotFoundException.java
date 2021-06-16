package io.github.vitor0x5.shared.exceptions.types;

public class NotFoundException extends RuntimeException{

    public static String USER = "User not found";
    public static String TRANSACTION = "Transaction not found";
    public static String ACCOUNT = "Account not found";

    public NotFoundException(String message) {
        super(message);
    }
}
