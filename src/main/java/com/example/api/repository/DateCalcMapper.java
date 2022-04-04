package com.example.api.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.api.entity.DateCalc;

@Mapper
public interface DateCalcMapper {

	// SELECT全件
	@Select("SELECT * FROM datecalc")
	public List<DateCalc> selectAll();

	// SELECT１件
	@Select("SELECT * FROM datecalc WHERE id = #{id}")
	public DateCalc selectOne(int id);

	// 新規登録
	@Insert("INSERT INTO datecalc  (name, plusyear, plusmonth, plusday) VALUES (#{name}, #{plusyear}, #{plusmonth}, #{plusday})")
	public void insertOne(DateCalc dateCalc);

	// 更新
	@Update("UPDATE datecalc SET name = #{name}, plusyear = #{plusyear}, plusmonth = #{plusmonth}, plusday = #{plusday} WHERE id = #{id}")
	public void updateOne(DateCalc dateCalc);

	// 削除
	@Delete("DELETE FROM datecalc WHERE id = #{id}")
	public void deleteOne(DateCalc dateCalc);

}
