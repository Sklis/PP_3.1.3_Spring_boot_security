package ru.sklis.spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sklis.spring_boot.model.User;
import ru.sklis.spring_boot.service.UserService;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        System.out.println(userService.getAllUsers());
        return "show_all_users";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "user_info";
    }

    @PostMapping()
    public String creatUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/update_info")
    public String updateUser(@RequestParam("id") long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("newUser", user);
        return "user_info";
    }
}