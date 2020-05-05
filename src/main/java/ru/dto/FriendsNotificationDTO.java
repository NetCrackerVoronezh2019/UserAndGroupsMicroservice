package ru.dto;

import ru.domen.FriendshipNotification;

import java.util.ArrayList;
import java.util.List;

public class FriendsNotificationDTO {
    private Integer notificationId;
    private Long outgoingId;
    private String outgoingName;

    public static FriendsNotificationDTO getFriendNotificationDTO(FriendshipNotification friendshipNotification) {
        FriendsNotificationDTO friendsNotificationDTO = new FriendsNotificationDTO();
        friendsNotificationDTO.notificationId = friendshipNotification.getNotificationId();
        friendsNotificationDTO.outgoingId = friendshipNotification.getOutgoing().getUserId();
        friendsNotificationDTO.outgoingName = friendshipNotification.getOutgoing().getLastName() + " " + friendshipNotification.getOutgoing().getFirstName();
        return friendsNotificationDTO;
    }

    public static List<FriendsNotificationDTO> getFriendNotificationDTO(List<FriendshipNotification> friendshipNotifications) {
        List<FriendsNotificationDTO> friendsNotificationDTOS = new ArrayList<>();
        for (FriendshipNotification not :
                friendshipNotifications) {
            friendsNotificationDTOS.add(getFriendNotificationDTO(not));
        }
        return friendsNotificationDTOS;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Long getOutgoingId() {
        return outgoingId;
    }

    public void setOutgoingId(Long outgoingId) {
        this.outgoingId = outgoingId;
    }

    public String getOutgoingName() {
        return outgoingName;
    }

    public void setOutgoingName(String outgoingName) {
        this.outgoingName = outgoingName;
    }
}
