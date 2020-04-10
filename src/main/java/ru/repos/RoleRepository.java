package ru.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Role;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends CrudRepository<Role,Long> {

    Role findByRoleName(String name);
}
