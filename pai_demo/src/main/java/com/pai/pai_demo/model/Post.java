package com.pai.pai_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String title;
    @Type(type = "text")
    private String content;

    @Enumerated(value = EnumType.STRING)
    private Category category;
    private LocalDateTime publicationTime = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Account author;

}
