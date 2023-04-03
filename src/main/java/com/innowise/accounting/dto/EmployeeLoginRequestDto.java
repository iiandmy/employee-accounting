package com.innowise.accounting.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLoginRequestDto {
    private String email;
    private String password;
}
