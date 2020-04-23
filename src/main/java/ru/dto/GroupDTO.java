package ru.dto;

import ru.domen.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupDTO {
    private Long groupId;
    private String name;
    private Long dialogId;
    private Long creatorId;
    private String subjectName;
    private String image;
    private String description;
    private boolean isNotificationsOn;
    private Long countNot;

    public static GroupDTO getGroupDTO(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.dialogId = group.getDialogId();
        groupDTO.groupId = group.getGroupId();
        groupDTO.name = group.getName();
        groupDTO.creatorId = group.getCreator().getUserId();
        groupDTO.image = group.getImage();
        groupDTO.subjectName = group.getSubject().getName();
        groupDTO.description = group.getDescription();
        return groupDTO;
    }

    public Long getCountNot() {
        return countNot;
    }

    public void setCountNot(Long countNot) {
        this.countNot = countNot;
    }

    public boolean isNotificationsOn() {
        return isNotificationsOn;
    }

    public void setNotificationsOn(boolean notificationsOn) {
        isNotificationsOn = notificationsOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static List<GroupDTO> getGroupDTO(List<Group> groups) {
        List<GroupDTO> groupDTOS = new ArrayList<>();
        for (Group g:
                groups) {
            groupDTOS.add(GroupDTO.getGroupDTO(g));
        }
        return groupDTOS;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDialogId() {
        return dialogId;
    }

    public void setDialogId(Long dialogId) {
        this.dialogId = dialogId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
