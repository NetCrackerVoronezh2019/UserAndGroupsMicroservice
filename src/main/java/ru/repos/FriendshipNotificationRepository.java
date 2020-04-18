package ru.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.FriendshipNotification;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FriendshipNotificationRepository extends CrudRepository<FriendshipNotification,Long> {
    @Modifying
    @Query("DELETE from FriendshipNotification where ingoing.userId=:ingoingId and outgoing.userId=:outgoingId")
    public void deleteNotification(Long ingoingId,Long outgoingId);

    @Modifying
    @Query("DELETE from FriendshipNotification where ingoing.userId=:ingoingId")
    public void deleteNotifications(Long ingoingId);

}
