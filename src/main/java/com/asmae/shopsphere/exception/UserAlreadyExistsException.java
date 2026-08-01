package com.asmae.shopsphere.exception;

import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String username) {

        super("Username "+username+" Already exists in database");
    }
}
