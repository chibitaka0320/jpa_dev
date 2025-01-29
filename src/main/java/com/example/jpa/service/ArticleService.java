package com.example.jpa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.jpa.dto.ArticleDTO;
import com.example.jpa.dto.ArticleWithCommentDTO;
import com.example.jpa.entity.ArticleEntity;
import com.example.jpa.repository.ArticleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final ModelMapper modelMapper;

    public List<ArticleDTO> findAll() {
        List<ArticleEntity> articleEntityList = articleRepository.findAll();

        List<ArticleDTO> articleDTOList = articleEntityList.stream().map(this::convertToArticleDTO)
                .collect(Collectors.toList());

        return articleDTOList;
    }

    public Page<ArticleDTO> getArticles(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ArticleEntity> articlePage = articleRepository.findAll(pageable);

        List<ArticleDTO> articleDTOs = articlePage.getContent().stream().map(this::convertToArticleDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(articleDTOs, pageable, articlePage.getTotalElements());
    }

    public ArticleWithCommentDTO findById(Integer id) {
        ArticleEntity aritcleEntity = articleRepository.findById(id).orElse(null);

        if (aritcleEntity == null) {
            return null;
        }

        ArticleWithCommentDTO articleWithCommentDTO = convArticleWithCommentDTO(aritcleEntity);
        return articleWithCommentDTO;
    }

    public ArticleEntity save(ArticleEntity articleEntity) {
        return articleRepository.save(articleEntity);
    }

    public ArticleEntity update(ArticleEntity articleEntity) {
        return articleRepository.save(articleEntity);
    }

    public void deleteEmployee(Integer id) {
        articleRepository.deleteById(id);
    }

    private ArticleDTO convertToArticleDTO(ArticleEntity articleEntity) {
        return modelMapper.map(articleEntity, ArticleDTO.class);
    }

    private ArticleWithCommentDTO convArticleWithCommentDTO(ArticleEntity articleEntity) {
        return modelMapper.map(articleEntity, ArticleWithCommentDTO.class);
    }

}
