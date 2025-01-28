package com.example.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.jpa.dto.ArticleDTO;
import com.example.jpa.dto.ArticleWithCommentDTO;
import com.example.jpa.dto.CommentDTO;
import com.example.jpa.dto.UserDTO;
import com.example.jpa.entity.ArticleEntity;
import com.example.jpa.repository.ArticleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleDTO> findAll() {
        List<ArticleEntity> articleEntityList = articleRepository.findAll();

        List<ArticleDTO> articleDTOList = new ArrayList<>();

        for (ArticleEntity articleEntity : articleEntityList) {

            ArticleDTO articleDTO = convertToArticleDTO(articleEntity);
            articleDTOList.add(articleDTO);

        }

        return articleDTOList;
    }

    public Page<ArticleEntity> getArticles(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleRepository.findAll(pageable);
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
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(articleEntity, articleDTO);

        if (articleEntity.getUser() != null) {
            UserDTO articleUserDTO = new UserDTO();
            BeanUtils.copyProperties(articleEntity.getUser(), articleUserDTO);
            articleDTO.setUser(articleUserDTO);
        }

        return articleDTO;
    }

    private ArticleWithCommentDTO convArticleWithCommentDTO(ArticleEntity articleEntity) {
        ArticleWithCommentDTO articleWithCommentDTO = new ArticleWithCommentDTO();
        BeanUtils.copyProperties(articleEntity, articleWithCommentDTO);

        if (articleEntity.getUser() != null) {
            UserDTO articleUserDTO = new UserDTO();
            BeanUtils.copyProperties(articleEntity.getUser(), articleUserDTO);
            articleWithCommentDTO.setUser(articleUserDTO);
        }

        List<CommentDTO> commentList = articleEntity.getCommentList().stream()
                .map(commentEntity -> {
                    CommentDTO commentDTO = new CommentDTO();
                    BeanUtils.copyProperties(commentEntity, commentDTO);

                    if (commentEntity.getUser() != null) {
                        UserDTO commentUserDTO = new UserDTO();
                        BeanUtils.copyProperties(commentEntity.getUser(), commentUserDTO);
                        commentDTO.setUser(commentUserDTO);
                    }
                    return commentDTO;
                })
                .collect(Collectors.toList());

        articleWithCommentDTO.setCommentList(commentList);

        return articleWithCommentDTO;
    }

}
