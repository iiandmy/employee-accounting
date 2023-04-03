package com.innowise.accounting.service;

import com.innowise.accounting.domain.Department;
import com.innowise.accounting.domain.Employee;
import com.innowise.accounting.dto.EmployeeRegistrationRequestDto;
import com.innowise.accounting.exception.department.DepartmentNotExistsException;
import com.innowise.accounting.exception.employee.EmployeeAlreadyExistsException;
import com.innowise.accounting.mapper.EmployeeMapper;
import com.innowise.accounting.persistence.DepartmentRepository;
import com.innowise.accounting.persistence.EmployeeRepository;
import com.innowise.accounting.persistence.RoleRepository;
import com.innowise.accounting.util.StringConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('USER')")
    public List<EmployeeRegistrationRequestDto> getEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Employee createEmployee(EmployeeRegistrationRequestDto employeeDto) {
        if (employeeRepository.findByEmail(employeeDto.getEmail()).isPresent()) {
            throw new EmployeeAlreadyExistsException();
        }
        Department employeeDepartment = departmentRepository.findByName(
                employeeDto.getDepartment().getName()
        ).orElseThrow(DepartmentNotExistsException::new);

        Employee createdEmployee = Employee.builder()
                .email(employeeDto.getEmail())
                .passwordHash(passwordEncoder.encode(employeeDto.getPassword()))
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .roleSet(Set.of(roleRepository.findByName(StringConstants.ROLE_USER).orElseThrow()))
                .department(employeeDepartment)
                .build();

        employeeRepository.save(createdEmployee);
        employeeDepartment.getEmployeeSet().add(createdEmployee);

        return createdEmployee;
    }
}
