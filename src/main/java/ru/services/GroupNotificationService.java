package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.GroupsNotification;
import ru.domen.Post;
import ru.domen.User;
import ru.repos.GroupNotificationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupNotificationService {
    @Autowired
    private GroupNotificationRepository groupNotificationRepository;

    public List<GroupsNotification> createGroupNotifications(Post post) {
        List<GroupsNotification> notifications = new ArrayList<>();
        for (User user :
                post.getGroup().getSubscribers()) {
            GroupsNotification groupsNotification = new GroupsNotification();
            groupsNotification.setGroup(post.getGroup());
            groupsNotification.setUser(user);
            groupsNotification = groupNotificationRepository.save(groupsNotification);
            notifications.add(groupsNotification);
        }
        return notifications;
    }

    public void deleteNotifications(Long groupId,Long userId) {
        groupNotificationRepository.deleteByGroupIdUserId(groupId,userId);
    }

}
