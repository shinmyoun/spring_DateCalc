package com.example.api.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.api.entity.DateCalc;
import com.example.api.service.DateCalcService;

@Controller
public class DateCalcController {

	@Autowired
	DateCalcService service;

	//表示
	/**
	 * top.htmlを表示
	 * 
	 * @param Model model
	 * @return top.html
	 */
	@GetMapping("/")
	public String loginSuccess(Model model) {
		List<DateCalc> dateCalc = service.selectAll();

		LocalDate[] resultdateArray = new LocalDate[0];
		Map<Integer, LocalDate> resultdateMap = new HashMap<>();

		model.addAttribute("dateCalc", dateCalc);
		model.addAttribute("resultdateArray", resultdateArray);
		model.addAttribute("resultdateMap", resultdateMap);
		return "top";
	}

	/**
	 * 計算結果をtop.htmlに表示
	 * 
	 * @param Model  model
	 * @param String inputdateHTML
	 * @return top.html
	 */
	@PostMapping("/calc")
	public String calc(Model model, @ModelAttribute("inputdate") String inputdateHTML) {
		// @ModelAttributeでhtml画面に入力された値を受取る
		// DBから全データをselectしDateCalc型のListに格納
		List<DateCalc> dateCalc = service.selectAll();
		// htmlファイルに入力された数値を日付型に変換
		LocalDate inputdate = LocalDate.parse((inputdateHTML), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		// 確認用
//      System.out.println("inputdateは " + inputdate);
//      System.out.println("End");

		// 加減年・月・日格納用の配列を作成
		int[] plusyear = new int[dateCalc.size()];
		int[] plusmonth = new int[dateCalc.size()];
		int[] plusday = new int[dateCalc.size()];

		// 計算結果格納用のLocalDate型の配列を作成
		LocalDate[] resultdateArray = new LocalDate[dateCalc.size()];

		// 上記の配列を用いて、DB内の値の数だけ繰り返し処理し、各計算式に対する計算結果resultdateを作成
		for (int i = 0; i < dateCalc.size(); i++) {
			plusyear[i] = dateCalc.get(i).getPlusyear();
			plusmonth[i] = dateCalc.get(i).getPlusmonth();
			plusday[i] = dateCalc.get(i).getPlusday();
			resultdateArray[i] = inputdate.plusYears(plusyear[i]).plusMonths(plusmonth[i]).plusDays(plusday[i]);
		}

		// 確認用
//      System.out.println("計算結果日は↓");
//      for(LocalDate value : resultdateArray) {
//          System.out.println(value);
//      }
//      System.out.println("End");
//      System.out.println("resultdateArray[0]は：" + resultdateArray[0]);

		// Mapに格納
		Map<Integer, LocalDate> resultdateMap = new HashMap<>();
		for (int i = 0; i < dateCalc.size(); i++) {
			resultdateMap.put(i + 1, resultdateArray[i]);
		}

		// 確認用
      System.out.println("resultdateMap確認用");
      for(Integer key : resultdateMap.keySet()) {
          LocalDate value = resultdateMap.get(key);
          System.out.println("idが" + key + "のresultdateは" + value);
      }
      System.out.println("id1のresultdateMapは" + resultdateMap.get(1));
      System.out.println("End");

		// Thymeleafへファイルへ各値を渡す
		model.addAttribute("dateCalc", dateCalc);
		model.addAttribute("inputdate", inputdate);
		model.addAttribute("resultdateArray", resultdateArray);
		model.addAttribute("resultdateMap", resultdateMap);

		// top.htmlを表示
		return "top";
	}

	// 新規登録
	@GetMapping("/register")
	public String displayRegister(@ModelAttribute DateCalc dateCalc, Model model) {
		model.addAttribute("dateCalc", dateCalc);
		return "register";
	}

	@PostMapping("/register")
	public String runRegister(Model model, @Validated @ModelAttribute DateCalc dateCalc, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			System.out.println("エラー発生！BindingResult内容↓↓↓");
			System.out.println(bindingresult);
			System.out.println("エラー発生！BindingResult内容↑↑↑");
			model.addAttribute("dateCalc", dateCalc);
			return "/register";
		}
		service.insertOne(dateCalc);
		return "redirect:/";
	}

	// 更新
	@GetMapping("/update/id={id}")
	public String displayUpdate(Model model, @PathVariable("id") int id) {
		model.addAttribute("dateCalc", service.selectOne(id));
		return "update";
	}

	@PostMapping("/update/id={id}")
	public String runUpdate(Model model, @Validated @ModelAttribute DateCalc dateCalc, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			System.out.println("エラー発生！BindingResult内容↓↓↓");
			System.out.println(bindingresult);
			System.out.println("エラー発生！BindingResult内容↑↑↑");
			model.addAttribute("dateCalc", dateCalc);
			return "/update";
		}

		service.updateOne(dateCalc);
		return "redirect:/";
	}

	// 削除
	@PostMapping("delete/id={id}")
	public String deleteOne(@ModelAttribute DateCalc dateCalc) {
		service.deleteOne(dateCalc);
		return "redirect:/";
	}

}
