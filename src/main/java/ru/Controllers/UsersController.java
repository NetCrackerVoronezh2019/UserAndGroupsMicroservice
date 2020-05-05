package ru.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.domen.FriendshipNotification;
import ru.domen.User;
import ru.dto.FriendsNotificationDTO;
import ru.dto.UserDTO;
import ru.services.FriendshipNotificationService;
import ru.services.RoleService;
import ru.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private FriendshipNotificationService friendshipNotificationService;

    @PostMapping("/createUser/")
    public void createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setImage(userDTO.getImage());
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

    @PutMapping("user/updateUserImage")
    public void updeteAvatar(@RequestParam Long userId,@RequestParam String key) {
        User user = userService.getUserById(userId);
        user.setImage(key);
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
        user.setImage(userDTO.getImage());
        userService.saveUser(user);
    }


    @PostMapping("startDialogWithUser")
    public Long startDialog(@RequestParam Long userId, @RequestParam Long creatorId) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl("http://localhost:8088/user/createDialog").queryParam("userId",userId).queryParam("creatorId",creatorId);
        ResponseEntity<Long> res = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null,  new ParameterizedTypeReference<Long>() {});
        return res.getBody();
    }

    @GetMapping("/friends")
    public List<UserDTO> getFriends(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        List<Long> outgoing = user.getOutgoing().stream().map(User::getUserId).collect(Collectors.toList());
        List<UserDTO> friendsDTO = new ArrayList<>();
        for (User us :
                user.getIngoing()) {
            if (outgoing.contains(us.getUserId())) {
                friendsDTO.add(UserDTO.getUserDTO(us));
            }
        }
        return friendsDTO;
    }


    @GetMapping("/IngoingFriends")
    public List<UserDTO> getIngoingFriends(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        List<Long> outgoing = user.getOutgoing().stream().map(User::getUserId).collect(Collectors.toList());
        List<UserDTO> friendsDTO = new ArrayList<>();
        for (User us :
                user.getIngoing()) {
            if (!outgoing.contains(us.getUserId())) {
                friendsDTO.add(UserDTO.getUserDTO(us));
            }
        }
        return friendsDTO;
    }

    @GetMapping("/OutgoingFriends")
    public List<UserDTO> getOutgoingFriends(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        List<Long> ingoing = user.getIngoing().stream().map(User::getUserId).collect(Collectors.toList());
        List<UserDTO> friendsDTO = new ArrayList<>();
        for (User us :
                user.getOutgoing()) {
            if (!ingoing.contains(us.getUserId())) {
                friendsDTO.add(UserDTO.getUserDTO(us));
            }
        }
        return friendsDTO;
    }

    @PutMapping("/addToFriends")
    public void addToFriends(@RequestParam Long ingoingId, @RequestParam Long outgoingId) {
        User ingoing = userService.getUserById(ingoingId);
        User outgoing = userService.getUserById(outgoingId);
        if (!ingoing.getOutgoing().stream().map(User::getUserId).collect(Collectors.toList()).contains(outgoingId)) {
            FriendshipNotification friendshipNotification = new FriendshipNotification();
            friendshipNotification.setIngoing(ingoing);
            friendshipNotification.setOutgoing(outgoing);
            friendshipNotificationService.saveNotification(friendshipNotification);
        } else {
            friendshipNotificationService.deleteNotification(outgoingId,ingoingId);
        }
        ingoing.getIngoing().add(outgoing);
        userService.saveUser(ingoing);
    }

    @PutMapping("/removeFriend")
    public void removeFriend(@RequestParam Long ingoingId, @RequestParam Long outgoingId) {
        User outgoing = userService.getUserById(outgoingId);
        friendshipNotificationService.deleteNotification(ingoingId,outgoingId);
        outgoing.setOutgoing(outgoing.getOutgoing().stream().filter(user -> user.getUserId()!=ingoingId).collect(Collectors.toList()));
        userService.saveUser(outgoing);
    }

    @GetMapping("user/search")
    public List<UserDTO> search(@RequestParam String firstName, @RequestParam String lastName) {
        return UserDTO.getUserDTO(userService.search(firstName,lastName));
    }

    @GetMapping("user/countNotifications")
    public Integer countNotifications(@RequestParam Long userId) {
        return userService.getUserById(userId).getIngoingNotifications().size();
    }

    @DeleteMapping("user/cleanNotifications")
    public void cleanNotifications(@RequestParam Long userId) {
        friendshipNotificationService.deleteNotifications(userId);
    }

    @GetMapping("getFriendshipNotifications")
    public List<FriendsNotificationDTO> getFriendshipNotifications(@RequestParam Long userId) {
        return FriendsNotificationDTO.getFriendNotificationDTO(userService.getUserById(userId).getIngoingNotifications());
    }

    @DeleteMapping("ignoreNotification")
    public void ignoreNotefications(@RequestParam Integer notificationId) {
        friendshipNotificationService.deleteNotification(notificationId);
    }

}
