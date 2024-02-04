package web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping("/add_user")
    public String showFormForAddUser(@ModelAttribute("user") User user) {
        return "addNewUserForm";
    }

    @PostMapping("/add_user")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("show_single_user")
    public String showSingleUser(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userByID";
    }

    @GetMapping(value = "/edit_user")
    public String showFormForUpdateUser(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUserForm";
    }

    @PostMapping("/edit_user")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/delete_user")
    public String deleteUserById(@RequestParam(name = "id") Long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }
}
