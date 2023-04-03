package com.innowise.accounting.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRegistrationRequestDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private DepartmentDto department;
}
