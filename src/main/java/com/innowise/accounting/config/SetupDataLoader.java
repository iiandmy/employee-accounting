package com.innowise.accounting.config;

import com.innowise.accounting.domain.Department;
import com.innowise.accounting.domain.Employee;
import com.innowise.accounting.domain.Permission;
import com.innowise.accounting.domain.Role;
import com.innowise.accounting.persistence.EmployeeRepository;
import com.innowise.accounting.persistence.PermissionRepository;
import com.innowise.accounting.persistence.RoleRepository;
import com.innowise.accounting.util.StringConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean isSetupDone = false;

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    private final String STANDARD_ADMIN_EMAIL = "admin@gmail.com";
    private final String STANDARD_ADMIN_PASSWORD = "qwerty123";
    private final String STANDARD_ADMIN_FNAME = "John";
    private final String STANDARD_ADMIN_LNAME = "Doe";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (isSetupDone) return;

        Permission modifyPermission = createPermissionIfNotFound(StringConstants.MODIFY_PERMISSION);
        Permission readPermission = createPermissionIfNotFound(StringConstants.READ_PERMISSION);
        Permission writePermission = createPermissionIfNotFound(StringConstants.WRITE_PERMISSION);
        Permission deletePermission = createPermissionIfNotFound(StringConstants.DELETE_PERMISSION);

        Set<Permission> adminPermissionSet = Set.of(modifyPermission, readPermission, writePermission, deletePermission);
        Role adminRole = createRoleIfNotFound(StringConstants.ROLE_ADMIN, adminPermissionSet);
        createRoleIfNotFound(StringConstants.ROLE_USER, Set.of(writePermission));

        createStandardAdminIfNotFound(Set.of(adminRole));

        isSetupDone = true;
    }

    @Transactional
    private Employee createStandardAdminIfNotFound(Set<Role> adminRoleSet) {
        return employeeRepository.findByEmail(STANDARD_ADMIN_EMAIL)
                .orElseGet(() -> employeeRepository.save(createEmployee(
                        STANDARD_ADMIN_FNAME,
                        STANDARD_ADMIN_LNAME,
                        STANDARD_ADMIN_PASSWORD,
                        STANDARD_ADMIN_EMAIL,
                        adminRoleSet,
                        null
                )));
    }

    @Transactional
    private Permission createPermissionIfNotFound(String permissionName) {
        return permissionRepository.findByName(permissionName)
                .orElseGet(() -> permissionRepository.save(createPermission(permissionName)));
    }

    @Transactional
    private Role createRoleIfNotFound(String roleName, Set<Permission> permissionSet) {
        return roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(createRole(roleName, permissionSet)));
    }

    private Employee createEmployee(
            String firstName,
            String lastName,
            String password,
            String email,
            Set<Role> roleSet,
            Department department
    ) {
        return Employee.builder()
                .roleSet(roleSet)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .passwordHash(passwordEncoder.encode(password))
                .department(department)
                .build();
    }

    private Permission createPermission(String permissionName) {
        Permission permission = new Permission();
        permission.setName(permissionName);
        return permission;
    }

    private Role createRole(String roleName, Set<Permission> rolePermissionSet) {
        Role role = new Role();
        role.setName(roleName);
        role.setPermissionSet(rolePermissionSet);
        return role;
    }
}
