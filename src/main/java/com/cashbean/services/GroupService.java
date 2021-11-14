package com.cashbean.services;

import com.cashbean.models.Group;
import com.cashbean.models.User;

import java.util.List;
import java.util.UUID;

public interface GroupService {

    Group getById(UUID id);
    List<Group> getByUser(User user);
    Group addGroup(String groupName, User user);

}
