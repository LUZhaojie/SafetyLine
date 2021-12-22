package com.backend.db.controller;

import com.backend.db.bean.User;
import com.backend.db.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @ResponseBody
    @GetMapping("/user")
    public User Userbyid(@RequestParam("id") Long id) {
        return userService.Userbyid(id);
    }


    @ResponseBody
    @GetMapping("/user/name")
    public List<User> UserbyName(@NotEmpty(message = "The username cannot be empty") @RequestParam("username") String username){
        return userService.UserbyName(username);
    }


    @ResponseBody
    @DeleteMapping("/user/delete")
    public User deleteUser( @RequestParam("id") Long id){
        return userService.deleteUser(id);
    }

    @ResponseBody
    @PostMapping("/user/saveUser")
    public Boolean saveUser(@Valid @RequestBody User user){
        return userService.saveUser(user);
    }

    @ResponseBody
    @GetMapping("/user/all")
    public List<User> getAll(){
        return userService.getAll();
    }

    @ResponseBody
    @GetMapping("/user/login")
    public Boolean login(@NotEmpty(message = "The username cannot be empty") @RequestParam("username") String name, @NotEmpty(message = "The password cannot be empty") @RequestParam("password") String pwd){
        return userService.login(name,pwd);
    }

    @ResponseBody
    @GetMapping("/user/role")
    public Integer getRole(@NotEmpty(message = "The username cannot be empty") @RequestParam("username") String name) throws Exception{
        return  userService.getRole(name);
    }

    @ResponseBody
    @PostMapping("/user/changepwd")
    public Integer changePwd(@NotEmpty(message = "The username cannot be empty") @RequestParam("username") String name,@NotEmpty(message = "The password cannot be empty")  @RequestParam("password") String pwd) throws Exception{
        return userService.changePwd(name,pwd);
    }
}
