package com.cashbean.services;

import com.cashbean.models.Entry;
import com.cashbean.models.User;

import java.util.List;
import java.util.UUID;

public interface EntryService {

    Entry getById(UUID id);
    List<Entry> getByUser(User user);
    List<Entry> getByGroupId(UUID id);
    Entry addEntry(String title, int amount, User user, UUID groupId);

}
