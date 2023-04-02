package com.innowise.accounting.exception.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class EmployeeAlreadyExistsException extends EmployeeException {
    public EmployeeAlreadyExistsException() {
        super("Employee already exists");
    }

    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}
