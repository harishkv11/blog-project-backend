package com.harishverma.blog.payloads;

import com.harishverma.blog.entities.Category;
import com.harishverma.blog.entities.Comment;
import com.harishverma.blog.entities.User;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private Integer postId;
    private String title;
    private String content;

    private String imageName;
    private Date addedDate;

    private CategoryDTO category;
    private UserDTO user;

    private Set<Comment> comments = new HashSet<>();
}
