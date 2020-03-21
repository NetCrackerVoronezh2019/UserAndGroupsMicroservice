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
}
