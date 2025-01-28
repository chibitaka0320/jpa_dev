package com.example.jpa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {

    private Integer articleId;
    private String title;
    private String content;
    private UserDTO user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
