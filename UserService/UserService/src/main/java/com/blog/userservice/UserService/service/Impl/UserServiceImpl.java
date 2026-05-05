package com.blog.userservice.UserService.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import com.blog.userservice.UserService.entity.User;
import com.blog.userservice.UserService.exception.ResourceNotFoundException;
import com.blog.userservice.UserService.payloads.UserDto;
import com.blog.userservice.UserService.repository.UserRepository;
import com.blog.userservice.UserService.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    //=====================Create User==================================
    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepository.save(user);
        return this.UserToDto(savedUser);
    }
    //=======================UpdateUser=================================
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser=this.userRepository.save(user);
        UserDto userDto1=this.UserToDto(updatedUser);
        return userDto1 ;
    }
    //===========================GetUserByID============================
    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));

        return this.UserToDto(user);
    }
    //========================GetAllUsers=============================
    @Override
    public List<UserDto> getAllUsers() {
        List<User>users=this.userRepository.findAll();
        List<UserDto>userDtos=users.stream().map(user->this.UserToDto(user)).collect(Collectors.toList());
        return  userDtos;
    }
    //=========================DeleteUser==========================
    @Override
    public void deleteUser(Integer userId) {
        User user= this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepository.delete(user);
    }
    //================ Using ModelMapper Library============================
    private User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
        return user;
    }
    private UserDto UserToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto ;

    }
}
