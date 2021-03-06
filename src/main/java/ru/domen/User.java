package ru.domen;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "userId")
    private Long userId;
    @Column(name = "email")
    private String email;
    @Column(name = "firsrName")
    private String firstName;
    @Column(name = "middleName")
    private String middleName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name="birthday")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime birthday;
    @Column(name = "image")
    private String image;
    @ManyToMany(mappedBy = "users")
    private List<Group> groups = new ArrayList<>();
    @ManyToMany(mappedBy = "subscribers")
    private List<Group> subscribtion = new ArrayList<>();
    @ManyToMany(mappedBy = "admins")
    private List<Group> groupsAdmining = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "creator")
    private List<Group> submitGroups = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "user_friends",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "friend") })
    private List<User> outgoing = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_friends",
            joinColumns ={ @JoinColumn(name = "friend") },
            inverseJoinColumns = {@JoinColumn(name = "user_id") })
    private List<User> ingoing = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "sender")
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "ingoing")
    private List<FriendshipNotification> ingoingNotifications = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "outgoing")
    private List<FriendshipNotification> outgoingNotifications = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<GroupsNotification> notifications = new ArrayList<>();

    public List<GroupsNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<GroupsNotification> notifications) {
        this.notifications = notifications;
    }

    public List<Group> getSubscribtion() {
        return subscribtion;
    }

    public void setSubscribtion(List<Group> subscribtion) {
        this.subscribtion = subscribtion;
    }

    public List<FriendshipNotification> getIngoingNotifications() {
        return ingoingNotifications;
    }

    public void setIngoingNotifications(List<FriendshipNotification> ingoingNotifications) {
        this.ingoingNotifications = ingoingNotifications;
    }

    public List<FriendshipNotification> getOutgoingNotifications() {
        return outgoingNotifications;
    }

    public void setOutgoingNotifications(List<FriendshipNotification> outgoingNotifications) {
        this.outgoingNotifications = outgoingNotifications;
    }

    public List<Group> getGroupsAdmining() {
        return groupsAdmining;
    }

    public void setGroupsAdmining(List<Group> groupsAdmining) {
        this.groupsAdmining = groupsAdmining;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<User> getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(List<User> outgoing) {
        this.outgoing = outgoing;
    }

    public List<User> getIngoing() {
        return ingoing;
    }

    public void setIngoing(List<User> ingoing) {
        this.ingoing = ingoing;
    }

    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getSubmitGroups() {
        return submitGroups;
    }

    public void setSubmitGroups(List<Group> submitGroups) {
        this.submitGroups = submitGroups;
    }
}
