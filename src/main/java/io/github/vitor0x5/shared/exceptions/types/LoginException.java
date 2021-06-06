package io.github.vitor0x5.shared.exceptions.types;

public class LoginException extends RuntimeException{
    public static String userNotFound = "Email and/or password are incorrect";

    public LoginException(String message) {super(message);}
}
