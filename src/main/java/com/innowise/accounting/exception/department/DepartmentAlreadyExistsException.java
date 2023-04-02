package com.innowise.accounting.exception.department;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DepartmentAlreadyExistsException extends DepartmentException {
    public DepartmentAlreadyExistsException() {
        super("Department already exists");
    }

    public DepartmentAlreadyExistsException(String message) {
        super(message);
    }
}
