package com.innowise.accounting.service;

import com.innowise.accounting.domain.Department;
import com.innowise.accounting.exception.department.DepartmentNotExistsException;
import com.innowise.accounting.persistence.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public Department getDepartment(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(DepartmentNotExistsException::new);
    }
}
