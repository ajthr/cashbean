package com.cashbean.controllers;

import com.cashbean.dto.CreateEntryRequest;
import com.cashbean.models.Entry;
import com.cashbean.models.User;
import com.cashbean.services.EntryService;
import com.cashbean.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class EntryController {

    private final EntryService entryService;
    private final UserService userService;

    @Autowired
    public EntryController(EntryService entryService, UserService userService) {
        this.entryService = entryService;
        this.userService = userService;
    }

    @PostMapping(value = "/entry")
    public ResponseEntity<Void> createEntry(@Valid @RequestBody CreateEntryRequest entryRequest, HttpServletRequest request) {
        User user = userService.getFromRequest(request);
        if (user != null) {
            Entry entry = entryService.addEntry(entryRequest.getTitle(),
                                                entryRequest.getAmount(),
                                                user,
                                                entryRequest.getGroupId());
            if (entry != null) {
                return ResponseEntity.status(201).build();
            }
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping(value = "/entry", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Entry>> getEntries(HttpServletRequest request) {
        User user = userService.getFromRequest(request);
        if (user != null) {
            List<Entry> entries = entryService.getByUser(user);
            return ResponseEntity.status(200).body(entries);
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping(value = "/entry/g/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Entry>> getEntriesByGroup(HttpServletRequest request, @PathVariable String id) {
        User user = userService.getFromRequest(request);
        if (user != null) {
            List<Entry> entries = entryService.getByGroupId(UUID.fromString(id));
            return ResponseEntity.status(200).body(entries);
        }
        return ResponseEntity.status(401).build();
    }

}
