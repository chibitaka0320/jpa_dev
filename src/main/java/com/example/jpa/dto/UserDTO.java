package com.example.jpa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer userId;
    private String name;
    private String introduction;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
