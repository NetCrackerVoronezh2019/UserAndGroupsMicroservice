package ru.domen;

import javax.persistence.*;

@Entity
@Table(name = "FriendshipNotifications")
public class FriendshipNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="notificationID")
    private Integer notificationId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingoingId")
    private User ingoing;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "outgoingId")
    private User outgoing;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public User getIngoing() {
        return ingoing;
    }

    public void setIngoing(User ingoing) {
        this.ingoing = ingoing;
    }

    public User getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(User outgoing) {
        this.outgoing = outgoing;
    }
}
