package ru.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
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

    @PostMapping("startDialogWithUser")
    public void startDialog(@RequestParam Long userId, @RequestParam Long creatorId) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl("http://localhost:8088/user/createDialog").queryParam("userId",userId).queryParam("creatorId",creatorId);
        restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null, Object.class);
    }
}
