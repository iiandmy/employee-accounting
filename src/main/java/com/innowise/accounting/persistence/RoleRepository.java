package com.innowise.accounting.persistence;

import com.innowise.accounting.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
