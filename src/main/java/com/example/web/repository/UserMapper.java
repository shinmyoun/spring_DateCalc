package com.example.web.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.web.entity.UserModel;

@Mapper
public interface UserMapper {

	// loginUserテーブルから、usernameが一致しているものを検索
	@Select("SELECT * FROM login_user WHERE username = #{username}")
	public Optional<UserModel> findByUsername(String username);
}
