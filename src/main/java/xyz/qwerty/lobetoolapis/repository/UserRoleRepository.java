package xyz.qwerty.lobetoolapis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.qwerty.lobetoolapis.entity.UserRole;
import xyz.qwerty.lobetoolapis.entity.UserRoleKey;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleKey> {

}
