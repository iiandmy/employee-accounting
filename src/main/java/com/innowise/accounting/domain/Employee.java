package com.innowise.accounting.domain;

import com.innowise.accounting.util.StringConstants;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "employee")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String passwordHash;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roleSet;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> availableRoles = getRoleSet();
        Set<Permission> availablePermissions = availableRoles.stream()
                .map(Role::getPermissionSet)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        return Stream.concat(
                availableRoles.stream()
                        .map(role ->
                                new SimpleGrantedAuthority(StringConstants.ROLE_PREFIX + role.getName())
                        ),
                availablePermissions.stream()
                        .map(permission ->
                                new SimpleGrantedAuthority(StringConstants.PERMISSION_PREFIX + permission.getName())
                        )
        ).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
