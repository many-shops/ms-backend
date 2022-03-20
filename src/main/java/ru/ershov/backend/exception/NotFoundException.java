package ru.ershov.backend.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final String type = "not.found";

    <ID extends Number> NotFoundException(String name, ID id) {
        super(name + " with id = " + id + " not found");
    }

}
