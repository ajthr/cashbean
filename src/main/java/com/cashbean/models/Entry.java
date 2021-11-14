package com.cashbean.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "entry")
@Getter
@Setter
@NoArgsConstructor
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID entryId;

    @Column
    private String title;

    @Column
    private int amount;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    public Entry(String title, int amount, User user, Group group) {
        this.title = title;
        this.amount = amount;
        this.user = user;
        this.group = group;
    }

    public Entry(String title, int amount, User user) {
        this.title = title;
        this.amount = amount;
        this.user = user;
    }

}
