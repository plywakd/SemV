package com.pai.pai_demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(name = "registration_time")
    private LocalDateTime registrationDateTime;
    @Type(type = "text")
    private String description;
    private boolean status;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Set<Role> roles;

    public Account(String name, String lastName, String email, String password, LocalDateTime registrationDateTime, String description, boolean status) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.registrationDateTime = registrationDateTime;
        this.description = description;
        this.status = status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
