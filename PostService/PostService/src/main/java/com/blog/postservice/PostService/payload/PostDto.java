package com.blog.postservice.PostService.payload;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {

    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private LocalDateTime addDate;
}
