package io.github.vitor0x5.shared.exceptions.types;

public class NotFoundException extends RuntimeException{

    public static String userNotFound = "User not found";
    public static String transactionNotFound = "Transaction not found";

    public NotFoundException(String message) {
        super(message);
    }
}
