package com.mandy.api.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mandy.api.pojo.Eg1TablePo;

public interface Eg1RestDao {
	
	public List<Eg1TablePo> selectCmpyList();
	
	public List<Eg1TablePo> str2List(@Param("caseType") String caseType);
	
	public String str2String(@Param("str1") String str1);
	
	public LinkedList<Eg1TablePo> str2LinkedList(@Param("str1") String str1);
	
	public List<Eg1TablePo> map2List(Map<String, String> map);
	
}
