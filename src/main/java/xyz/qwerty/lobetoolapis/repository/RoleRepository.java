package xyz.qwerty.lobetoolapis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.qwerty.lobetoolapis.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
