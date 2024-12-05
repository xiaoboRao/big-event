package com.bob.mapper;

import com.bob.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from big_event.user where username = #{userName}")
    User findUserByUserName(String userName);

    @Insert("insert into user(username, password, create_time, update_time) " + "value(#{username}, #{password}, now(),now())")
    void add(String username, String password);
}
