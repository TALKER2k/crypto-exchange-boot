package com.company.cryptoboot.repositories;

import com.company.cryptoboot.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
