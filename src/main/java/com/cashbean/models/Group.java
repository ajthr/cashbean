package com.cashbean.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID groupId;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnore
    private User user;

    public Group(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
