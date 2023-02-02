package com.harishverma.blog.services;

import com.harishverma.blog.entities.User;
import com.harishverma.blog.payloads.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user, Integer id);
    UserDTO getUser(Integer id);
    List<UserDTO> getAllUsers();

    void deleteUser(Integer id);
}
