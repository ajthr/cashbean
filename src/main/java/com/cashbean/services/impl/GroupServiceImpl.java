package com.cashbean.services.impl;

import com.cashbean.models.Group;
import com.cashbean.models.User;
import com.cashbean.repositories.GroupRepository;
import com.cashbean.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group getById(UUID id) {
        return groupRepository.getById(id);
    }

    public List<Group> getByUser(User user) {
        return groupRepository.findByUser(user);
    }

    @Override
    public Group addGroup(String groupName, User user) {
        Group group = new Group(groupName, user);
        groupRepository.save(group);
        return group;
    }

}
