package io.github.vitor0x5.shared.errors.types;

public class NotFoundException extends RuntimeException{

    public static String userNotFound = "User not found";
    public static String incomeNotFound = "Income not found";
    public static String transactionNotFound = "Transaction not found";
    public static String outcomeNotFound = "Outcome not found";

    public NotFoundException(String message) {
        super(message);
    }
}
