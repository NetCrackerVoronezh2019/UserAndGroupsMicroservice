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

    @Query("SELECT u from User u WHERE u.firstName LIKE %:firstName% and u.lastName LIKE %:lastName%")
    public List<User> search(String firstName, String lastName);

}
