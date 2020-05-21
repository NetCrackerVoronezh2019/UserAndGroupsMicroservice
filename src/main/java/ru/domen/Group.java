package ru.domen;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @Column(name = "groupId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long groupId;
    @Column(name = "name")
    private String name;
    @Column(name = "dialogId")
    private Long dialogId;
    @ManyToMany
    @JoinTable(name = "Users_groups",
                joinColumns = @JoinColumn(name = "groupId"),
                inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<User> users = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "Users_notifications",
            joinColumns = @JoinColumn(name = "groupId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<User> subscribers = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "Admins_groups",
            joinColumns = @JoinColumn(name = "groupId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<User> admins = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User creator;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "group")
    private List<GroupsNotification> notifications = new ArrayList<>();

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "group")
    private List<Post> posts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjectId")
    private Subject subject;

    public List<GroupsNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<GroupsNotification> notifications) {
        this.notifications = notifications;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<User> subscribers) {
        this.subscribers = subscribers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
