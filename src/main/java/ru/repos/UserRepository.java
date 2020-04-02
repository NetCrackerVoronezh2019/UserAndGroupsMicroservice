package ru.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User,Long> {

    @Query("SELECT u FROM User u where u.userId=:userId and u in (" +
            "SELECT u1.outgoing FROM User u1 where u1 in u.ingoing)")
    public List<User> fiendUserFriends(Long userId);

}
