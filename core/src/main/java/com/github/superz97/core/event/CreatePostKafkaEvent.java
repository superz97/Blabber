package com.github.superz97.core.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostKafkaEvent {

    private Long postId;
    private Long authorId;
    private String username;

}
