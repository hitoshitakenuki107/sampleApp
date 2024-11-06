package com.example.demo.repository;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM usertbl")
    List<User> getAllUsers();

    @Select("SELECT * FROM usertbl WHERE id = #{id}")
    User getUserById(@Param("id") Long id);

    @Select("SELECT * FROM usertbl WHERE name = #{name}")
    User getUserByName(@Param("name") String name);

    @Select("SELECT * FROM usertbl WHERE name = #{name} AND pass = #{pass}")
    User getUserByNameAndPass(@Param("name") String name, @Param("pass") String pass);

    @Insert("INSERT INTO usertbl (name, pass, nickname) VALUES (#{name}, #{pass}, #{nickname})")
    void insertUser(User user);
}
