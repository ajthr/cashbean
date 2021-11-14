package com.cashbean.repositories;

import com.cashbean.models.Group;
import com.cashbean.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {

    List<Group> findByUser(User user);

}
