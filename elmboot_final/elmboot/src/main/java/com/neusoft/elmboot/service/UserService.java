package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.po.UserImg;
import com.neusoft.elmboot.po.UserPassword;

public interface UserService {

	public User getUserByIdByPass(User user);
	public User getUserById(String userId);
	public int saveUser(User user);
	public int modifyPassword(UserPassword userPassword);
	public int changeUserPortrait(UserImg userImg);
	public int removeUser(User user);

	public int updateUserById(User user);
}
