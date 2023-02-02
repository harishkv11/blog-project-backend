package com.harishverma.blog.payloads;

import com.harishverma.blog.entities.Category;
import com.harishverma.blog.entities.User;
import lombok.*;

import java.util.Date;

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
}
