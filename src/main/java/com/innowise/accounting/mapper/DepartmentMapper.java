package com.innowise.accounting.mapper;

import com.innowise.accounting.domain.Department;
import com.innowise.accounting.dto.DepartmentDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);
    Department toDepartment(DepartmentDto departmentDto);
}
