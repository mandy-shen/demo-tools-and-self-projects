package com.mandy.api.dao;

import java.util.List;

import com.mandy.api.pojo.User;

public interface UserDao {
	
	public User selectUserById(Integer id);
	
	public List<User> selectUserList();
	
	public int insertUser(User user);
	
	public int updateUser(User user);

	public int deleteUserById(int id);
}
