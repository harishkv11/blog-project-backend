package com.harishverma.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDTO {
    private Integer id;

    @NotEmpty
    @Size(min = 5, max = 100, message = "Title Should be min of 5 characters and less than 100 characters")
    private String title;
    @NotEmpty
    @Size(min = 5, max = 100, message = "Description Should be min of 5 characters and less than 100 characters")
    private String description;
}
