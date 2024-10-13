// UserServiceTest.java
package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

/**
 * UserService测试类
 * @author Y7993
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest // 加载 Spring 上下文
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserMapper userMapper;

	private User user1;
	private User user2;

	@BeforeEach
	public void setUp() {
		user1 = new User();
		user1.setId(1L);
		user1.setName("Alice");
		user1.setAge(25);

		user2 = new User();
		user2.setId(2L);
		user2.setName("Bob");
		user2.setAge(30);

		// 模拟UserMapper行为
		Mockito.when(userMapper.selectById(1L)).thenReturn(user1);
		Mockito.when(userMapper.selectById(2L)).thenReturn(user2);
		Mockito.when(userMapper.selectAll()).thenReturn(Arrays.asList(user1, user2));
		Mockito.when(userMapper.insert(Mockito.any(User.class))).thenAnswer(invocation -> {
			User user = invocation.getArgument(0);
			System.out.println("模拟插入用户：" + user);
			return null;
		});
		Mockito.when(userMapper.update(Mockito.any(User.class))).thenAnswer(invocation -> {
			User user = invocation.getArgument(0);
			System.out.println("模拟更新用户：" + user);
			return null;
		});
		Mockito.when(userMapper.deleteById(Mockito.anyLong())).thenAnswer(invocation -> {
			Long id = invocation.getArgument(0);
			System.out.println("模拟删除用户ID：" + id);
			return null;
		});
	}

	@Test
	public void testSaveUser() {
		User newUser = new User();
		newUser.setId(3L);
		newUser.setName("Charlie");
		newUser.setAge(28);
		userService.saveUser(newUser);
	}

	@Test
	public void testDeleteUser() {
		userService.deleteUser(1L);
	}

	@Test
	public void testUpdateUser() {
		User updatedUser = new User();
		updatedUser.setId(1L);
		updatedUser.setName("Alice Updated");
		updatedUser.setAge(26);
		userService.updateUser(updatedUser);
	}

	@Test
	public void testGetUserById() {
		User user = userService.getUserById(1L);
		System.out.println("获取用户： " + user);
	}

	@Test
	public void testGetAllUsers() {
		List<User> users = userService.getAllUsers();
		System.out.println("所有用户： " + users);
	}

	@Test
	public void testGetUserInfo() {
		String info = userService.getUserInfo();
		System.out.println("用户信息： " + info);
	}

	@Test
	public void testThrowException() {
		// 模拟一个方法抛出异常以测试异常通知
		Mockito.doThrow(new RuntimeException("测试异常")).when(userMapper).deleteById(999L);
		try {
			userService.deleteUser(999L);
		} catch (Exception e) {
			// 异常已被AOP通知捕获，无需处理
		}
	}
}
