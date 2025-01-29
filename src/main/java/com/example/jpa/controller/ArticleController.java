package com.example.jpa.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.dto.ArticleDTO;
import com.example.jpa.dto.ArticleWithCommentDTO;
import com.example.jpa.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public Page<ArticleDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return articleService.getArticles(page, size);
    }

    @GetMapping("/{articleId}")
    public ArticleWithCommentDTO findById(@PathVariable Integer articleId) {
        return articleService.findById(articleId);
    }
}
