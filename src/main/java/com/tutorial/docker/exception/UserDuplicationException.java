package com.tutorial.docker.exception;

public class UserDuplicationException extends RuntimeException {

    public UserDuplicationException(String email) {
        super("This email is already taken : " + email);
    }
}
