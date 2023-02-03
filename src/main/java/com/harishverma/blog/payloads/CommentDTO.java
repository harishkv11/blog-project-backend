package com.harishverma.blog.payloads;

import com.harishverma.blog.entities.Post;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private int id;
    private String content;
}
