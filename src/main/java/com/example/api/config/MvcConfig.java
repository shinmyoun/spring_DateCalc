package com.example.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/**
	 * 概要：
	 *  「/login」というURLからlogin.htmlを呼び出す
	 * メモ:
	 *  通常、コントローラが存在し、画面(html)用のビュー名を返し、画面を表示する
	 *  LoginController実装場合は、必要。
	 *  「/login」というURLでリクエストが来たら
	 *  「login」ってビュー名(login.html)で表示する処理
	 */
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/top").setViewName("top");
		registry.addViewController("/login").setViewName("login");
	}

}