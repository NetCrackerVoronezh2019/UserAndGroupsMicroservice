package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.FriendshipNotification;
import ru.repos.FriendshipNotificationRepository;

@Service
public class FriendshipNotificationService {
    @Autowired
    private FriendshipNotificationRepository friendshipNotificationRepository;

    public void deleteNotification(Long ingoingId,Long outgoingId) {
        friendshipNotificationRepository.deleteNotification(ingoingId,outgoingId);
    }

    public void deleteNotifications(Long ingoingId) {
        friendshipNotificationRepository.deleteNotifications(ingoingId);
    }

    public FriendshipNotification saveNotification(FriendshipNotification friendshipNotification) {
        return friendshipNotificationRepository.save(friendshipNotification);
    }
}
