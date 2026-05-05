package com.blog.userservice.UserService.controller;
import java.util.List;

import com.blog.userservice.UserService.payloads.ApiResponse;
import com.blog.userservice.UserService.payloads.UserDto;
import com.blog.userservice.UserService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //********************** Create User **************************
    @PostMapping("/")
    public ResponseEntity<UserDto> crateUser(@Valid @RequestBody UserDto userDto){
        UserDto createduserDto=this.userService.createUser(userDto);
        return  new ResponseEntity<>(createduserDto, HttpStatus.CREATED);
    }

    //************************** Update User **************************
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto updatedUser= this.userService.updateUser(userDto,userId);
        return  ResponseEntity.ok(updatedUser);
    }
    //******************************* DeleteUser *************************
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted succesfully",true),HttpStatus.OK);
    }

    //****************************** Get All User **********************
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //************************* Get User by ID ************************
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok( this.userService.getUserById(userId));
    }
























}
