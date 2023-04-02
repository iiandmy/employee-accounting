package com.innowise.accounting.exception.department;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DepartmentNotExistsException extends DepartmentException {
    public DepartmentNotExistsException() {
        super("Department with such id not exist");
    }

    public DepartmentNotExistsException(String message) {
        super(message);
    }
}
