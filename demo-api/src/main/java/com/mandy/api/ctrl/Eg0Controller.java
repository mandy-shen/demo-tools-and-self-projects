package com.mandy.api.ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mandy.api.pojo.Eg0CmpyPo;

@RestController
@RequestMapping("item")
public class Eg0Controller {

	@Autowired
//	private ItemDao itemDao;

	@GetMapping(value = "/test.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Eg0CmpyPo> testJson() {
//		List<Eg3CmpyPo> list = itemDao.list("20828393");
		
		Eg0CmpyPo po = new Eg0CmpyPo();
		po.setBanNo(20828393);
		po.setCmpyName("Acer.Inc");
		po.setInvestAmt("10000000000000000");
		
		List<Eg0CmpyPo> list = new ArrayList<>();
		list.add(po);
		
		return list;
	}
	
	@GetMapping(value = "test.csv")
	public void csv(HttpServletResponse response) throws IOException {
//		List<Eg3CmpyPo> list = itemDao.list("20828393");

		Eg0CmpyPo po = new Eg0CmpyPo();
		po.setBanNo(20828393);
		po.setCmpyName("Acer.Inc");
		po.setInvestAmt("10000000000000000");
		
		List<Eg0CmpyPo> list = new ArrayList<>();
		list.add(po);
		
		StringBuilder sb = new StringBuilder();
	    for (Eg0CmpyPo pojo: list){
	        sb.append(pojo.getBanNo());
	        sb.append(",");
	        sb.append(pojo.getCmpyName());
	        sb.append(",");
	        sb.append(pojo.getInvestAmt());
	        sb.append("\n");
	    }
		
	    response.setContentType("text/plain; charset=utf-8");
	    response.getWriter().print(sb.toString());
	}
	
}