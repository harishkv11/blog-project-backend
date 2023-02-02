package com.harishverma.blog.payloads;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.*;
import org.modelmapper.internal.bytebuddy.description.field.FieldDescription;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostResponse {
    private List<PostDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private long totalElements;
    private Integer totalPages;
    private boolean lastPage;
}
