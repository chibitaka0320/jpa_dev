package com.example.jpa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Integer commentId;
    private UserDTO user;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
