package com.example.web.service;

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
        //findByUsernameで見つけてきたユーザ情報をUserModelに入れる
		UserModel UserModel = userMapper.findByUsername(username);

        //UserDetailsにreturn
        return UserModel;
	}

}
