package com.example.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.DateCalc;
import com.example.api.repository.DateCalcMapper;

@Service
public class DateCalcService {

	@Autowired
	DateCalcMapper mapper;

	// select全件
	public List<DateCalc> selectAll() {
		return mapper.selectAll();
	}

	// select１件
	public DateCalc selectOne(int id) {
		return mapper.selectOne(id);
	}

	// insert
	public void insertOne(DateCalc dateCalc) {
		mapper.insertOne(dateCalc);
	}

	// update
	public void updateOne(DateCalc dateCalc) {
		mapper.updateOne(dateCalc);
	}

	// delete
	public void deleteOne(DateCalc dateCalc) {
		mapper.deleteOne(dateCalc);
	}

}
