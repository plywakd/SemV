package com.oopProj.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "student", indexes = {@Index(name = "idx_surname", columnList = "surname", unique = false),
        @Index(name = "idx_index_num", columnList = "index_num", unique = false)})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 100)
    private String surname;
    @Column(name="index_num",nullable = false, unique = true)
    private String indexNum;
    @Column(length = 50)
    private String email;
    @Column(nullable = false)
    private boolean landline;
    @ManyToMany(mappedBy = "students")
    private Set<Project> projects;
}
