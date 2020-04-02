package ru.dto;

import ru.domen.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupDTO {
    private Long groupId;
    private String name;
    private Long dialogId;
    private Long creatorId;

    public static GroupDTO getGroupDTO(Group group) {
        GroupDTO newDTO = new GroupDTO();
        newDTO.dialogId = group.getDialogId();
        newDTO.groupId = group.getGroupId();
        newDTO.name = group.getName();
        newDTO.creatorId = group.getCreator().getUserId();
        return newDTO;
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
