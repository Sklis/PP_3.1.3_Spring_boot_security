package ru.sklis.spring_boot_security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sklis.spring_boot_security.model.Role;
import ru.sklis.spring_boot_security.model.User;
import ru.sklis.spring_boot_security.service.RoleService;
import ru.sklis.spring_boot_security.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Util {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Util(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initDB() {
        Role roleAdmin = new Role(1, "ROLE_ADMIN");
        Role roleUser = new Role(2, "ROLE_USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.save(roleAdmin);
        roleService.save(roleUser);

        adminSet.add(roleAdmin);
        userSet.add(roleUser);

        User admin = new User("Admin", "Adminov",
                "admin@mail.ru", "admin",
                "admin", adminSet);

        User user = new User("User", "Userov",
                "user@mail.ru", "user",
                "user", userSet);

        userService.saveUser(admin);
        userService.saveUser(user);

    }
}
