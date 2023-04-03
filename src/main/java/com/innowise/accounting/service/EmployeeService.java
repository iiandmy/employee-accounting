package com.innowise.accounting.service;

import com.innowise.accounting.dto.EmployeeDto;
import com.innowise.accounting.mapper.EmployeeMapper;
import com.innowise.accounting.persistence.EmployeeRepository;
import com.innowise.accounting.persistence.PermissionRepository;
import com.innowise.accounting.persistence.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDto> getEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }
}
