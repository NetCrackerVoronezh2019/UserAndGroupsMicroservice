package ru.dto;

import ru.domen.Group;

public class GroupDTO {
    private Long groupId;
    private String name;
    private Long dialogId;
    private Long creatorId;

    public static GroupDTO getGrupDTO(Group group) {
        GroupDTO newDTO = new GroupDTO();
        newDTO.dialogId = group.getDialogId();
        newDTO.groupId = group.getGroupId();
        newDTO.name = group.getName();
        newDTO.creatorId = group.getCreator().getUserId();
        return newDTO;
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
}
