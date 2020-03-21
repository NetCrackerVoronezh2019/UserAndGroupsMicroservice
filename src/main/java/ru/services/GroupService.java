package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.repos.GroupRepository;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

}
