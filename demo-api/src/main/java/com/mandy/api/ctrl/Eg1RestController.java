package com.mandy.api.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mandy.api.dao.Eg1RestDao;
import com.mandy.api.pojo.Eg1TablePo;
import com.mandy.api.util.CSV;

@RestController
@RequestMapping("eg1/rest")
public class Eg1RestController {

	@Autowired
	private Eg1RestDao dao;
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Eg1TablePo> queryAll(@RequestParam(value = "caseType", required = false) String caseType) {
		
		if (caseType != null) {
			return dao.str2List(caseType);
		}

		return dao.selectCmpyList();
	}
	
	@PostMapping(value = "/aa", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Eg1TablePo> post1Path1Param(@RequestParam(value = "A", required = false) String A, 
											@RequestParam(value = "B", required = false) String B) {
		
		Map<String, String> map = new HashMap<>();
		map.put("case", A);
		
		if (B != null) {
			map.put("B", B);
		}

		return dao.map2List(map);
	}
	
	@GetMapping(value = "/{A}/{B}.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Eg1TablePo> get2Path2Json(@PathVariable("format") String A) {

		return dao.str2List(A);
	}

	@GetMapping(value = "/{A}/{B}.csv")
	public void get2Path2Csv(@PathVariable("A") String A, HttpServletResponse response) {

		List<Eg1TablePo> list = dao.str2List(A);
		new CSV<Eg1TablePo>(Eg1TablePo.class).output(list, response);
	}
	
}