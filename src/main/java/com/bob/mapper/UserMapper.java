package com.bob.mapper;

import com.bob.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from big_event.user where username = #{userName}")
    User findUserByUserName(String userName);

    @Insert("insert into user(username, password, create_time, update_time) " + "value(#{username}, #{password}, now(),now())")
    void add(String username, String password);

    @Update("update user set nickname =#{nickname}, email=#{email}, update_time=#{updateTime} where id = #{id}")
    void updateUserInfo(User user);

    @Update("update user set password=#{newPassword}, update_time=now() where id =#{id}")
    void updatePwd(String newPassword, Integer id);
}
