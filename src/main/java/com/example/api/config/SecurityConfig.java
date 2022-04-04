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
	// BCryptPasswordEncoder bcryptアルゴリズムを使用したエンコーダーにより パスワードのハッシュ化を提供しているクラス
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 認証設定（ログインとログアウト）
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// 認証リクエストの設定 authorize=認可リクエスト 認証→認可の流れ
			.authorizeRequests()
			    // ログイン画面は直リンクOK（上記方法だと警告エラーがでるためcss等のファイルを下記のように除外対象にする）
                .antMatchers("/login", "/webjars/**", "/css/**", "/js/**", "/images/**").permitAll()
                // それ以外は直リンク禁止
				.anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login") //ログインページはコントローラを経由しないのでViewNameとの紐付けが必要
                .loginProcessingUrl("/sign_in") //フォームのSubmitURL、このURLへリクエストが送られると認証処理が実行される
                .usernameParameter("username") //リクエストパラメータのname属性を明示
                .passwordParameter("password")
                .defaultSuccessUrl("/top")// ログイン認証成功時の遷移先URLは、defaultSuccessUrl()で指定する必要があるみたい
                .failureUrl("/login?error")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				// インメモリ認証を設定 TODO DB認証に変更すること
				.inMemoryAuthentication()
				// "user"を追加
				.withUser("user")
				// "password"をBCryptで暗号化
				.password(passwordEncoder().encode("1"))
				// 権限＝ロールを設定
				.authorities("ROLE_USER");
	}

}
