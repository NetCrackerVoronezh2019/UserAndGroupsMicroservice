package ru.dto;

import ru.domen.GroupsNotification;

import java.util.ArrayList;
import java.util.List;

public class GroupNotificationsDTO {
    private Integer notificationId;
    private Long groupId;
    private Long userid;

    public static GroupNotificationsDTO getGroupNotificationsDTO(GroupsNotification groupsNotification) {
        GroupNotificationsDTO groupNotificationsDTO = new GroupNotificationsDTO();
        groupNotificationsDTO.setNotificationId(groupsNotification.getNotificationId());
        groupNotificationsDTO.setGroupId(groupsNotification.getGroup().getGroupId());
        groupNotificationsDTO.setUserid(groupsNotification.getUser().getUserId());
        return groupNotificationsDTO;
    }

    public static List<GroupNotificationsDTO> getGroupNotificationsDTO(List<GroupsNotification> groupsNotifications) {
        List<GroupNotificationsDTO> groupNotificationsDTOS = new ArrayList<>();
        for (GroupsNotification gn :
                groupsNotifications) {
            groupNotificationsDTOS.add(GroupNotificationsDTO.getGroupNotificationsDTO(gn));
        }
        return groupNotificationsDTOS;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
