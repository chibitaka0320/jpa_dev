package com.example.jpa.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.jpa.dto.UserDTO;
import com.example.jpa.dto.UserDetailsDTO;
import com.example.jpa.entity.UserEntity;
import com.example.jpa.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO findById(Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        UserEntity userEntity = user.get();

        UserDTO userDTO = new UserDTO();

        BeanUtils.copyProperties(userEntity, userDTO);

        return userDTO;
    }

    public UserDetailsDTO findByIdDetails(Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        UserEntity userEntity = user.get();

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();

        BeanUtils.copyProperties(userEntity, userDetailsDTO);

        return userDetailsDTO;
    }
}
