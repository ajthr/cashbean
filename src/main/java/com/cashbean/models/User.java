package com.cashbean.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    @JsonIgnore
    private String password;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
