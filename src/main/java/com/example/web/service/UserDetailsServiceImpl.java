package com.example.web.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.web.entity.UserModel;
import com.example.web.repository.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserMapper userMapper;

	public UserDetailsServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// findByUsernameで見つけてきたユーザ情報をUserModelに入れる
		Optional<UserModel> UserModel = userMapper.findByUsername(username);

		// userが見つからなかった場合の処理
		if (UserModel == null) {
			throw new UsernameNotFoundException("User" + username + "was not found in the database");
		}

		// UserDetails型のオブジェクトを認証処理に渡す
		UserDetails userDetails = UserModel.get();

		// UserDetailsにreturn
		return userDetails;
	}

}
