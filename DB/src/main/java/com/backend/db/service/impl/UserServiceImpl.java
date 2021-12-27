package com.backend.db.service.impl;

import com.backend.db.bean.User;
import com.backend.db.mapper.UserMapper;
import com.backend.db.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    public User Userbyid(Long id){
        User u = getById(id);
        return u;
    }

    public List<User> UserbyName(String username){
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        List<User> list = getBaseMapper().selectByMap(map);
        return list;
    }

    public User deleteUser(Long id){
        User u = getById(id);
        removeById(id);
        return u;
    }


    public Boolean saveUser(User user){
        Boolean b = save(user);
        if (b) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    public List<User> getAll(){
        return list();
    }


    public Boolean login(String name,String pwd){
        Map<String, Object> map = new HashMap<>();
        map.put("username", name);
        map.put("password", pwd);
        List<User> list = getBaseMapper().selectByMap(map);
        if(!list.isEmpty()){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    public Integer getRole(String name) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("username", name);
        List<User> list = getBaseMapper().selectByMap(map);
        if (list.isEmpty() || list.size() == 0){
            throw new Exception("cannot find "+name);
        }
        return list.get(0).getRole();
    }


    public Integer changePwd(String name,String pwd) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("username", name);
        List<User> list = getBaseMapper().selectByMap(map);
        if (list.isEmpty() || list.size() == 0){
            throw new Exception("cannot find "+name);
        }
        User u = list.get(0);
        u.setPassword(pwd);
        return getBaseMapper().updateById(u);
    }


    public void changeRole(Long id) throws Exception{
        User u = Userbyid(id);
        if (u.getRole()==1) {
            u.setRole(0);
        }else{
            u.setRole(1);
        }
        updateById(u);
    }
}
