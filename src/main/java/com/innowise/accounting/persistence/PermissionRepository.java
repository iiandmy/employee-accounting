package com.innowise.accounting.persistence;

import com.innowise.accounting.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
