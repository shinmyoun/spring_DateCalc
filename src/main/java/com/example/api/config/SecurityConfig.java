package com.example.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// パスワードのハッシュ化
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * BCryptPasswordEncoder bcryptアルゴリズムを使用したエンコーダーにより パスワードのハッシュ化を提供しているクラス
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// 認証リクエストの設定 authorize=認可リクエスト 認証→認可の流れ
				.authorizeRequests()

				// 認証の必要があるよう設定
				.anyRequest()// いかなるリクエストも
				.authenticated()// 認証が必要

				// フォームベースの設定
				.and().formLogin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				// インメモリ認証を設定 インメモリorDB認証のどちらか
				.inMemoryAuthentication()

				// "user"を追加
				.withUser("user")
				// "password"をBCryptで暗号化
				.password(passwordEncoder().encode("1"))
				// 権限＝ロールを設定
				.authorities("ROLE_USER");
	}

}
