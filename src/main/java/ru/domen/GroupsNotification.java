package ru.domen;

import javax.persistence.*;

@Entity
@Table(name = "Groupsnotifications")
public class GroupsNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="notificationID")
    private Integer notificationId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupId")
    private Group group;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
