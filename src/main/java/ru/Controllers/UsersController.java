package ru.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.domen.Group;
import ru.domen.User;
import ru.dto.GroupDTO;
import ru.dto.UserDTO;
import ru.services.RoleService;
import ru.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/createUser/")
    public void createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setImageURL(userDTO.getImageURL());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setEmail(userDTO.getEmail());
        user.setBirthday(userDTO.getBirthday());
        user.setUserId(userDTO.getUserId());
        user.setRole(roleService.getRoleByName(userDTO.getRole()));
        userService.saveUser(user);
    }

    @GetMapping("/getUser")
    public UserDTO getUser(@RequestParam(name = "userId") Long userId) {
        User us = userService.getUserById(userId);
        return UserDTO.getUserDTO(us);
    }

    @PutMapping("/userSettings")
    public void userSettings(@RequestBody UserDTO userDTO) {
        User user = userService.getUserById(userDTO.getUserId());
        user.setBirthday(userDTO.getBirthday());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setImageURL(userDTO.getImageURL());
        userService.saveUser(user);
    }


    @PostMapping("startDialogWithUser")
    public void startDialog(@RequestParam Long userId, @RequestParam Long creatorId) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl("http://localhost:8088/user/createDialog").queryParam("userId",userId).queryParam("creatorId",creatorId);
        restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null, Object.class);
    }

    @GetMapping("/friends")
    public List<UserDTO> getFriends(@RequestParam Long userId) {
        List<User> friends = userService.getFriends(userId);
        List<UserDTO> friendsDTO = new ArrayList<>();
        for (User user :
                friends) {
            friendsDTO.add(UserDTO.getUserDTO(user));
        }
        return friendsDTO;
    }


    @GetMapping("/IngoingFriends")
    public List<UserDTO> getIngoingFriends(@RequestParam Long userId) {
        List<User> friends = userService.getIngoingFriends(userId);
        List<UserDTO> friendsDTO = new ArrayList<>();
        for (User user :
                friends) {
            friendsDTO.add(UserDTO.getUserDTO(user));
        }
        return friendsDTO;
    }

    @GetMapping("/OutgoingFriends")
    public List<UserDTO> getOutgoingFriends(@RequestParam Long userId) {
        List<User> friends = userService.getOutgoingFriends(userId);
        List<UserDTO> friendsDTO = new ArrayList<>();
        for (User user :
                friends) {
            friendsDTO.add(UserDTO.getUserDTO(user));
        }
        return friendsDTO;
    }

    @PutMapping("/addToFriends")
    public void addToFriends(@RequestParam Long ingoingId, @RequestParam Long outgoingId) {
        User ingoing = userService.getUserById(ingoingId);
        ingoing.getIngoing().add(userService.getUserById(outgoingId));
        userService.saveUser(ingoing);
    }

}
