package com.cashbean.services.impl;

import com.cashbean.models.Entry;
import com.cashbean.models.Group;
import com.cashbean.models.User;
import com.cashbean.repositories.EntryRepository;
import com.cashbean.repositories.GroupRepository;
import com.cashbean.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public EntryServiceImpl(EntryRepository entryRepository,
                            GroupRepository groupRepository) {
        this.entryRepository = entryRepository;
        this.groupRepository = groupRepository;
    }

    public Entry getById(UUID id) {
        return entryRepository.getById(id);
    }

    public List<Entry> getByUser(User user) {
        return entryRepository.findByUser(user);
    }

    public List<Entry> getByGroupId(UUID groupId) {
        Group group = groupRepository.getById(groupId);
        return entryRepository.findByGroup(group);
    }

    @Override
    public Entry addEntry(String title, int amount, User user, UUID groupId) {
        try {
            Entry entry;
            if (groupId == null) {
                entry = new Entry(title, amount, user);
            } else {
                Group group = groupRepository.getById(groupId);
                entry = new Entry(title, amount, user, group);
            }
            entryRepository.save(entry);
            return entry;
        } catch (Exception e) {
            return null;
        }
    }

}
