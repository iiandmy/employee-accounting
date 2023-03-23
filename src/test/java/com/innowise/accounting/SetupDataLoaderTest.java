package com.innowise.accounting;

import com.innowise.accounting.persistence.EmployeeRepository;
import com.innowise.accounting.persistence.PermissionRepository;
import com.innowise.accounting.persistence.RoleRepository;
import com.innowise.accounting.util.StringConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SetupDataLoaderTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void createPermissionTest() {
        Assertions.assertTrue(permissionRepository.findByName(StringConstants.DELETE_PERMISSION).isPresent());
        Assertions.assertTrue(permissionRepository.findByName(StringConstants.READ_PERMISSION).isPresent());
        Assertions.assertTrue(permissionRepository.findByName(StringConstants.MODIFY_PERMISSION).isPresent());
        Assertions.assertTrue(permissionRepository.findByName(StringConstants.WRITE_PERMISSION).isPresent());
    }

    @Test
    void createRoleTest() {
        Assertions.assertTrue(roleRepository.findByName(StringConstants.ROLE_ADMIN).isPresent());
        Assertions.assertTrue(roleRepository.findByName(StringConstants.ROLE_USER).isPresent());
    }

    @Test
    void createStandardAdminTest() {
        var standardAdminEmail = "admin@gmail.com";
        var standardAdminPassword = "qwerty123";
        var standardAdmin = employeeRepository.findByEmail(standardAdminEmail);

        Assertions.assertTrue(standardAdmin.isPresent());
        Assertions.assertTrue(passwordEncoder.matches(standardAdminPassword, standardAdmin.get().getPasswordHash()));
    }

}
