package com.backend.db.mapper;

import com.backend.db.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from user")
//    public User getuser(String id);


}
