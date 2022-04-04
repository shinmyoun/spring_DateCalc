package com.example.web.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DateCalc {

	private int id;

	@NotBlank(message = "(Entityクラスに設定したオリジナルメッセージ)：入力してください")
	private String name;

	@NotNull
	private int plusyear;

	@NotNull
	private int plusmonth;

	@NotNull
	private int plusday;
}