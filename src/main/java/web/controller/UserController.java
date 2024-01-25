package web.controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    public String showAllUsers(ModelMap model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping(value = "/show_single_user")
    public String showSingleUser(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userByID";
    }

    @GetMapping(value = "/add_user")
    public String showFormForAddUser(@ModelAttribute("user") User user) {
        return "addNewUserForm";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/edit_user")
    public String showFormForUpdateUser(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUserForm";
    }

    @PostMapping("/save_edit_user")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/delete_user")
    public String deleteUserById(@RequestParam(name = "id") Long id) {
        userService.removeUserById(id);
        return "redirect:/";
    }
}
