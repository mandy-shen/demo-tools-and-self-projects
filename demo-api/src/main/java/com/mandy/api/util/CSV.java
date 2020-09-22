package com.mandy.api.util;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;


public class CSV<T> {
	
	private static final String CLASS_SUFFIX = "Po"; 
	
	private final Class<T> tClass;
	
	private StringBuilder sb;
	
	public CSV(Class<T> tClass) {
		this.tClass = tClass;
	}
	
	public void output(List<T> list, HttpServletResponse response) {
		try {
			isError(list);
			
			sb = new StringBuilder();
			gen(list);
			response.setContentType("text/plain; charset=utf-8");
			response.getWriter().print(sb.toString());
			sb.setLength(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void isError(List<T> list) throws NamingException {
		if (list == null) {
			throw new NullPointerException("csv list is null.");
		}
		if (!tClass.getSimpleName().endsWith(CLASS_SUFFIX)) {
			throw new NamingException("Suffix naming of " + tClass.getSimpleName() + " is wrong, need " + CLASS_SUFFIX + ".");
		}
	}
	
	private void gen(List<T> list) {
		head();
		body(list);
	}
	
	private void head() {
		fields().forEach(field -> sb.append(field.getName()).append(","));
		sb.deleteCharAt(sb.length() - 1).append("\n");
	}
	
	private void body(List<T> list) {
		if (list.isEmpty()) {
			return;
		}
		list.forEach(vo -> line(vo));
	}
	
	private void line(T vo) {
		String str = fields().stream().map(field -> val(vo, field.getName())).collect(Collectors.joining(","));
		sb.append(str).append("\n");
	}
	
	private List<Field> fields() {
		return Arrays.asList(tClass.getDeclaredFields());
	}
	
	private String val(T obj, String fieldName) {
		Field field = null;
		String str = "";
		try {
			field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			str = field.get(obj).toString();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return str;
	}
}
