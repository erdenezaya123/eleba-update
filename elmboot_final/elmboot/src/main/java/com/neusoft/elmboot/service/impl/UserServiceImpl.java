package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.po.UserImg;
import com.neusoft.elmboot.po.UserPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserByIdByPass(User user) {
		return userMapper.getUserByIdByPass(user);
	}
	
	@Override
	public User getUserById(String userId) {
		return userMapper.getUserById(userId);
	}
	
	@Override
	public int saveUser(User user) {
		return userMapper.saveUser(user);
	}

	@Override
	public int modifyPassword(UserPassword userPassword) {
		return userMapper.modifyPassword(userPassword);
	}

	@Override
	public int changeUserPortrait(UserImg userImg) {//base64
		String userId = userImg.getUserId();
		String base64 = userImg.getBase64();
		userMapper.prechangeUserPortrait(userId);
		return userMapper.changeUserPortrait(userId, base64);
	}

	@Override
	public int removeUser(User user) {
		userMapper.removeCart(user);
		userMapper.removeOrders(user);
		userMapper.removeDeliveryaddress(user);
		return userMapper.removeUser(user);
	}

	@Override
	public int updateUserById(User user) {
		return userMapper.updateUserById(user);
	}

}
