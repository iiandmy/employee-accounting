package com.innowise.accounting.mapper;

import com.innowise.accounting.domain.Employee;
import com.innowise.accounting.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EmployeeMapper {
//    @Mapping()
    Employee toEntity(EmployeeDto employeeDto);
    @Mapping(target = "departmentId", source = "employee.getDepartment().id")
    EmployeeDto toDto(Employee employee);
}
