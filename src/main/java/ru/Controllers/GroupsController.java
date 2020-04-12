package ru.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.domen.Group;
import ru.domen.Subject;
import ru.domen.User;
import ru.dto.GroupDTO;
import ru.dto.UserDTO;
import ru.services.GroupService;
import ru.services.SubjectService;
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
    @Autowired
    private SubjectService subjectService;

    @PutMapping("/groupSettings")
    public void groupSettings(@RequestBody GroupDTO groupDTO) {
        Group group = groupService.getGroupById(groupDTO.getGroupId());
        group.setName(groupDTO.getName());
        group.setSubject(subjectService.getSubjectByTranslateName(groupDTO.getSubjectName()));
        groupService.saveGroup(group);
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl("http://localhost:8088/group/settings").queryParam("dialogId",group.getDialogId()).queryParam("groupName",group.getName());
        restTemplate.exchange(uriBuilder.build().encode().toUri(), HttpMethod.PUT, null, Object.class);
    }

    @PostMapping("/createGroup/")
    public void createGroup(@RequestParam Long creatorId, @RequestParam String groupName, @RequestParam String subjectName) {
        Group newGroup = new Group();
        newGroup.setSubject(subjectService.getSubjectByTranslateName(subjectName));
        newGroup.setName(groupName);
        newGroup.setCreator(userService.getUserById(creatorId));
        newGroup.getUsers().add(newGroup.getCreator());
        newGroup.getAdmins().add(newGroup.getCreator());
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl("http://localhost:8088/group/createDialog").queryParam("creatorId",creatorId).queryParam("name",groupName);
        ResponseEntity<Long> res = restTemplate.exchange(uriBuilder.build().encode().toUri(), HttpMethod.POST, null, new ParameterizedTypeReference<Long>() {});
        newGroup.setDialogId(res.getBody());
        groupService.saveGroup(newGroup);
    }

    @PostMapping("group/subscribe")
    public void subscribe(@RequestParam Long userId,@RequestParam Long groupId) {
        Group group = groupService.getGroupById(groupId);
        User user = userService.getUserById(userId);
        group.getUsers().add(user);
        if (user.getUserId() == group.getCreator().getUserId()) {
            group.getAdmins().add(user);
        }
        groupService.saveGroup(group);
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl("http://localhost:8088/group/subscribeDialog").queryParam("userId",userId).queryParam("dialogId",group.getDialogId());
        restTemplate.exchange(uriBuilder.build().encode().toUri(), HttpMethod.POST, null, Object.class);
    }

    @GetMapping("/getGroup")
    public GroupDTO getGroup(@RequestParam Long groupId) {
        return GroupDTO.getGroupDTO(groupService.getGroupById(groupId));
    }
    @GetMapping("/getGroupUsers")
    public List<UserDTO> getGroupUsers(@RequestParam Long groupId) {
        return UserDTO.getUserDTO(groupService.getGroupById(groupId).getUsers());
    }
    @DeleteMapping("/leaveGroup/")
    public void leaveGroup(@RequestParam Long userId,@RequestParam Long groupId) {
        Group group = groupService.getGroupById(groupId);
        group.setUsers(group.getUsers().stream().filter(user -> user.getUserId() != userId).collect(Collectors.toList()));
        group.setAdmins(group.getAdmins().stream().filter(user -> user.getUserId() != userId).collect(Collectors.toList()));
        groupService.saveGroup(group);
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uriBuilder =UriComponentsBuilder.fromHttpUrl("http://localhost:8088/group/leaveDialog/").queryParam("userId",userId).queryParam("dialogId",group.getDialogId());
        restTemplate.exchange(uriBuilder.build().encode().toUri(), HttpMethod.DELETE   , null, Object.class);
    }

    @DeleteMapping("/deleteGroup")
    public void deleteGroup(@RequestBody Long groupId) {
        groupService.deleteGroup(groupId);
    }

    @GetMapping("group/search")
    public List<GroupDTO> search(@RequestParam String name, @RequestParam String subjectName) {
        List<Group> groups = groupService.search(name);
        if (subjectName != "") {
            groups = groups.stream().filter(group -> group.getSubject().getTranslateName().equals(subjectName)).collect(Collectors.toList());
        }
        return GroupDTO.getGroupDTO(groups);
    }

    @GetMapping("groups/getUserGroups")
    public List<GroupDTO> getUserGroups(@RequestParam Long userId) {
        List<Group> groups = userService.getUserById(userId).getGroups();
        return GroupDTO.getGroupDTO(groups);
    }

    @GetMapping("groups/admins")
    public List<UserDTO> getGroupAdmins(@RequestParam Long groupId) {
        return UserDTO.getUserDTO(groupService.getGroupById(groupId).getAdmins());
    }

    @PutMapping("groups/makeAdmin")
    public void makeAdmin(@RequestParam Long groupId,@RequestParam Long userId) {
        Group group = groupService.getGroupById(groupId);
        group.getAdmins().add(userService.getUserById(userId));
        groupService.saveGroup(group);
    }


    @PutMapping("groups/deleteAdmin")
    public void deleteAdmin(@RequestParam Long groupId,@RequestParam Long userId) {
        Group group = groupService.getGroupById(groupId);
        group.setAdmins(group.getAdmins().stream().filter(user -> user.getUserId()!=userId).collect(Collectors.toList()));
        groupService.saveGroup(group);
    }

    @GetMapping("groups/searchUser")
    public List<UserDTO> searchUser(@RequestParam Long groupId, @RequestParam String firstName,@RequestParam String lastName) {
        List<User> users = groupService.getGroupById(groupId).getUsers();
        users = users.stream().filter(user -> user.getFirstName().contains(firstName) && user.getLastName().contains(lastName)).collect(Collectors.toList());
        return UserDTO.getUserDTO(users);
    }
}
