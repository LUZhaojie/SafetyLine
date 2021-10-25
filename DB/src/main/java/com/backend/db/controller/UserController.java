package com.backend.db.controller;

import com.backend.db.bean.User;
import com.backend.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @GetMapping("/user")
    public User Userbyid(@RequestParam("id") Long id){
        return userService.getById(id);
    }


    @ResponseBody
    @GetMapping("/user/name")
    public List<User> UserbyName(@RequestParam("username") String username){
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        List<User> list = userService.getBaseMapper().selectByMap(map);
        return list;
    }


    @ResponseBody
    @DeleteMapping("/user/delete")
    public User deleteUser(@RequestParam("id") Long id){
        User u = userService.getById(id);
        userService.removeById(id);
        return u;
    }

    @ResponseBody
    @PostMapping("/user/saveUser")
    public Boolean saveUser(User user){
        Boolean b = userService.save(user);
        if (b) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @ResponseBody
    @GetMapping("/user/all")
    public List<User> getAll(){
        return userService.list();
    }

    @ResponseBody
    @GetMapping("/user/login")
    public Boolean login(@RequestParam("username") String name,@RequestParam("password") String pwd){
        Map<String, Object> map = new HashMap<>();
        map.put("username", name);
        map.put("password", pwd);
        List<User> list = userService.getBaseMapper().selectByMap(map);
        if(!list.isEmpty()){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @ResponseBody
    @GetMapping("/user/role")
    public Integer getRole(@RequestParam("username") String name){
        Map<String, Object> map = new HashMap<>();
        map.put("username", name);
        List<User> list = userService.getBaseMapper().selectByMap(map);
        return list.get(0).getRole();
    }

    @ResponseBody
    @PostMapping("/user/changepwd")
    public Integer changePwd(@RequestParam("username") String name,@RequestParam("password") String pwd){
        Map<String, Object> map = new HashMap<>();
        map.put("username", name);
        User u = userService.getBaseMapper().selectByMap(map).get(0);
        u.setPassword(pwd);
        return userService.getBaseMapper().updateById(u);
    }

}
