package com.pai.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String lastname;
    @Column(name = "email", unique = true)
    private String email;
    private String password;
    @Column(name = "register_time")
    private LocalDateTime registrationDateTime = LocalDateTime.now();
    private String description;
    @Column(name = "status")
    private boolean status;

    public Account(String name, String lastname, String email, String password, LocalDateTime registrationDateTime, String description, boolean status) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.registrationDateTime = registrationDateTime;
        this.description = description;
        this.status = status;
    }
}
