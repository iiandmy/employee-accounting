package com.innowise.accounting.mapper;

import com.innowise.accounting.domain.Employee;
import com.innowise.accounting.dto.EmployeeRegistrationRequestDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EmployeeMapper {
    Employee toEntity(EmployeeRegistrationRequestDto employeeDto);
    EmployeeRegistrationRequestDto toDto(Employee employee);
}
