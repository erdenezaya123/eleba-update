package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.po.UserImg;

import com.neusoft.elmboot.po.UserPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

@RestController
@RequestMapping("/UserController")
public class UserController {
	/*
	controller里面只能有一个requestmapping
	下面的方法表示前端的请求地址和请求方式

	这才是正确的请求 每一次请求后端都会打印数据
	你的代码要优化
	bug已经帮你解决了，剩下的是你自己代码的问题
	你先把getMapping postMapping putMapping deleteMapping搞会，再搞这个
	 */
	
	@Autowired
	private UserService userService;

	@RequestMapping("/getUserByIdByPass")
	public User getUserByIdByPass(User user) throws Exception{
		return userService.getUserByIdByPass(user);//登录
	}

	@RequestMapping("/getUserById")
	public User getUserById(User user) throws Exception {
		return userService.getUserById(user.getUserId());
	}
	
	@RequestMapping("/saveUser")
	public int saveUser(User user) throws Exception{
		return userService.saveUser(user);
	}

	@RequestMapping("/updateUserById")
	public int updateUserById(User user) throws Exception{
		return userService.updateUserById(user);
	}

	@RequestMapping("/modifyPassword")
	public int modifyPassword(UserPassword userPassword) throws Exception {
		return userService.modifyPassword(userPassword);
	}

	@RequestMapping("/changeUserPortrait")//base64
	public int changeUserPortrait( UserImg userImg) throws Exception {
		return userService.changeUserPortrait(userImg);
	}

	@RequestMapping("/removeUser")
	public int removeUser(User user) throws Exception{
		return userService.removeUser(user);
	}
}
