package ru.sklis.spring_boot_security.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sklis.spring_boot_security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUsersByUsername(String username);
}
