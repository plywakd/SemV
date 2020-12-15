package com.pai.STM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JsonIgnore
    private String password;
    private boolean status;
    @Column(name = "reg_date_time")
    private LocalDateTime registrationDateTime;
}
