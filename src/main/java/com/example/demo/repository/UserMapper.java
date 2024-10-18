package com.example.demo.repository;
import com.example.demo.model.User;
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
}
