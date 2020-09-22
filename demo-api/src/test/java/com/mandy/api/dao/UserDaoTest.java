package com.mandy.api.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mandy.api.config.AppConfig;
import com.mandy.api.config.AppInitializer;
import com.mandy.api.config.DevDbConfig;
import com.mandy.api.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfig.class, AppInitializer.class, DevDbConfig.class})
@WebAppConfiguration
public class UserDaoTest {
	
	@Autowired
	private UserDao dao;
 
	@Test
	public void testInsertUser() {
		User user = new User("admin", "123", 1);
		int count = dao.insertUser(user);
		Assert.assertEquals(1, count);
	}
 
	@Test
	public void testUpdateUser() {
		User user = new User("admin", "123", 1);
		dao.insertUser(user);
		
		user.setUsername("user1");
		int count = dao.updateUser(user);
		Assert.assertEquals(1, count);
		
		User queryUser = dao.selectUserById(user.getId());
		Assert.assertEquals("user1", queryUser.getUsername());
	}
	 
	@Test
	public void testDeleteUserById() {
		User user = new User("admin", "123", 1);
		dao.insertUser(user);
		
		int count = dao.deleteUserById(user.getId());
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void testSelectUserById() {
		User user = new User("admin", "123", 1);
		dao.insertUser(user);
		
		User queryUser = dao.selectUserById(user.getId());
		Assert.assertEquals("admin", queryUser.getUsername());
		Assert.assertEquals("123", queryUser.getPassword());
		Assert.assertEquals(1, queryUser.getStatus());
	}
 
	@Test
	public void testSelectUserList() {
		User user = new User("admin", "123", 1);
		dao.insertUser(user);
		
		List<User> users = dao.selectUserList();
		Assert.assertTrue(!users.isEmpty());
	}
}