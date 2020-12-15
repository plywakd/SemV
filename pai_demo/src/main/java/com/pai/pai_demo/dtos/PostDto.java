package com.pai.pai_demo.dtos;

import com.pai.pai_demo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class PostDto {

    @NotBlank(message = "Title must be not null")
    @Size(min = 5, max = 255, message = "Title must contain at least {min} to {max} characters")
    private String title;
    @NotBlank(message = "Content must be not null")
    @Size(min = 25, message = "Content must contain at least {min} characters")
    private String content;
    private Category category;
//    @NotBlank(message = "Author id must be not null")
    private int authorId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
