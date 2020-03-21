package ru.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.domen.User;
import ru.dto.UserDTO;
import ru.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class UsersController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser/")
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/getUser")
    public UserDTO getUser(@RequestParam(name = "userId") Long userId) {
        User us = userService.getUserById(userId);
        return UserDTO.getUserDTO(us);
    }
}
