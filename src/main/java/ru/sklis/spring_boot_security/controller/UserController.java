package ru.sklis.spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.sklis.spring_boot_security.model.User;
import ru.sklis.spring_boot_security.service.RoleService;
import ru.sklis.spring_boot_security.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String start() {
        return "/index";
    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        Long id = userService.findByUsername(principal.getName()).getId();
        User user = userService.getUser(id);

        // Проверяем, имеет ли пользователь роль администратора
        if (user.getRoles().stream().anyMatch(role -> role.getRoleName().equals("ROLE_ADMIN"))) {
            return "redirect:/admin";
        }

        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }


    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("userNew", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "new_user";
    }


    @PostMapping("/save")
    public String creatUser(@Valid @ModelAttribute("userNew") User user,
                            BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "new_user";
        }

        if (user.getId() != 0){
            userService.updateUser(user);
        } else {
            if (userService.uniqueUsername(user)) {
                model.addAttribute("uniqueUsername", "Пользователь с таким сеществует");
                return "new_user";
            }
            userService.saveUser(user);
        }
        return "redirect:/admin";

    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/update_info")
    public String updateUser(@RequestParam("id") long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("userNew", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "new_user";
    }

}