package com.example.web.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.web.entity.UserModel;

@Mapper
public interface UserMapper {

	//loginUserテーブルから、usernameが一致しているものを検索
    @Select("SELECT * FROM loginUser WHERE username = #{username}")
    public UserModel findByUsername(String username);
}
