package com.cashbean.controllers;

import com.cashbean.dto.CreateGroupRequest;
import com.cashbean.models.Group;
import com.cashbean.models.User;
import com.cashbean.services.GroupService;
import com.cashbean.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class GroupController {

    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @PostMapping(value = "/group")
    public ResponseEntity<Void> createEntry(@Valid @RequestBody CreateGroupRequest groupRequest, HttpServletRequest request) {
        User user = userService.getFromRequest(request);
        if (user != null) {
            Group group = groupService.addGroup(groupRequest.getGroupName(), user);
            if (group != null) {
                return ResponseEntity.status(201).build();
            }
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping(value = "/group", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Group>> getEntries(HttpServletRequest request) {
        User user = userService.getFromRequest(request);
        if (user != null) {
            List<Group> groups = groupService.getByUser(user);
            return ResponseEntity.status(200).body(groups);
        }
        return ResponseEntity.status(401).build();
    }

}
