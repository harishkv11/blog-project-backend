package com.harishverma.blog.services.Impl;

import com.harishverma.blog.entities.User;
import com.harishverma.blog.exceptions.ResourceNotFoundException;
import com.harishverma.blog.payloads.UserDTO;
import com.harishverma.blog.repositories.UserRepo;
import com.harishverma.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        User savedUser = userRepo.save(user);
        return userToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id", id));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        User newUser = userRepo.save(user);
        return userToDTO(newUser);
    }

    @Override
    public UserDTO getUser(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User"," id ", id));
        return userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOList =  users.stream().map(user -> userToDTO(user)).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User"," id ", id));
        userRepo.delete(user);
    }

    private User dtoToUser(UserDTO userDTO){
       User user = modelMapper.map(userDTO, User.class);
       return user;
    }

    private UserDTO userToDTO(User user){
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}
