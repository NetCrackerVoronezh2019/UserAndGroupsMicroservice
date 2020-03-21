package ru.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.domen.Group;
import ru.domen.User;
import ru.dto.GroupDTO;
import ru.dto.UserDTO;
import ru.services.GroupService;
import ru.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class GroupsController {

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @PostMapping("/createGroup/")
    public void createGroup(@RequestParam Long creatorId, @RequestParam String groupName,@RequestParam Long dialogId) {
        Group newGroup = new Group();
        newGroup.setName(groupName);
        newGroup.setCreator(userService.getUserById(creatorId));
        newGroup.getUsers().add(newGroup.getCreator());
        newGroup.setDialogId(dialogId);
        groupService.saveGroup(newGroup);
    }

    @GetMapping("/getGroup")
    public GroupDTO getGroup(@RequestParam Long groupId) {
        return GroupDTO.getGrupDTO(groupService.getGroupById(groupId));
    }
    @GetMapping("/getGroupUsers")
    public List<UserDTO> getGroupUsers(@RequestParam Long groupId) {
        List<User> users = groupService.getGroupById(groupId).getUsers();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User us:
             users) {
            usersDTO.add(UserDTO.getUserDTO(us));
        }
        return usersDTO;
    }
    @DeleteMapping("/leaveGroup/")
    public void leaveGroup(@RequestParam Long userId,@RequestParam Long groupId) {
        Group group = groupService.getGroupById(groupId);
        group.setUsers(group.getUsers().stream().filter(user -> user.getUserId() != userId).collect(Collectors.toList()));
        groupService.saveGroup(group);
    }

    @DeleteMapping("/deleteGroup")
    public void deleteGroup(@RequestBody Long groupId) {
        groupService.deleteGroup(groupId);
    }

}
