package xyz.qwerty.lobetoolapis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.qwerty.lobetoolapis.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
