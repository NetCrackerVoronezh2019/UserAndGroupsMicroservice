package ru.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.domen.GroupsNotification;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GroupNotificationRepository extends CrudRepository<GroupsNotification,Long> {

    @Modifying
    @Query("DELETE FROM GroupsNotification N Where N.group.groupId = :groupId and N.user.userId=:userId")
    void deleteByGroupIdUserId(Long groupId, Long userId);
}
