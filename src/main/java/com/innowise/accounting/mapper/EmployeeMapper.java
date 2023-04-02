package com.innowise.accounting.mapper;

import com.innowise.accounting.domain.Employee;
import com.innowise.accounting.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target="")
    Employee toEntity(EmployeeDto employeeDto);
    EmployeeDto toDto(Employee employee);
}
