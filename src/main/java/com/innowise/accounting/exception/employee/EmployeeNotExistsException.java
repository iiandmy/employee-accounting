package com.innowise.accounting.exception.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmployeeNotExistsException extends EmployeeException {
    public EmployeeNotExistsException() {
        super("Employee not exists");
    }

    public EmployeeNotExistsException(String message) {
        super(message);
    }
}
