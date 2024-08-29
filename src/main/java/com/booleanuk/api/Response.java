package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Response {

    public static ResponseStatusException notFound(String message) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
    }
}
