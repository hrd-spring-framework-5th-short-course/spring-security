package com.example.demospringsecurity.repositories;

import com.example.demospringsecurity.models.Role;
import com.example.demospringsecurity.models.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {


    @Select("select * from tb_user where username = #{usernameOrEmail} OR email = #{usernameOrEmail}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "roles", column = "id", many = @Many(select = "getRoleByUserId"))
    })
    User loadUserByUsernameRepository(String usernameOrEmail);


    @Select("Select r.id role_id, r.name role_name from tb_role r " +
            "inner join user_role ur on r.id = ur.role_id " +
            "where user_id = #{id}")
    @Results({
            @Result(property = "id", column = "role_id"),
            @Result(property = "name", column = "role_name")
    })
    List<Role> getRoleByUserId(Integer id);


    @Select("Select * from tb_user")
    List<User> getAllUser();

    @Insert("Insert into tb_user(name, gender, username, email, password)" +
            " values(#{name}, #{gender}, #{username}, #{email}, #{password})")
    boolean save(User user);

}
