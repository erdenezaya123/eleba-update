package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.UserPassword;
import org.apache.ibatis.annotations.*;

import com.neusoft.elmboot.po.User;

@Mapper
public interface UserMapper {

	@Select("select * from user where userId=#{userId} and password=#{password}")
	public User getUserByIdByPass(User user);

	@Select("select * from user where userId=#{userId}")
	public User getUserById(String userId);
	
	@Insert("insert into user values(#{userId},#{password},#{userName},#{userSex},null,1)")
	public int saveUser(User user);
	public int updateUserById(User user);

	@Update("update user set password=#{newPassword} where userId=#{userId} and password=#{oldPassword}")
	public int modifyPassword(UserPassword userPassword);

	@Update("UPDATE user u SET u.userImg =null WHERE u.userId =#{userId}")
	public int prechangeUserPortrait(@Param("userId") String userId);

	@Update("UPDATE user u SET u.userImg =#{base64} WHERE u.userId =#{userId}")
	public int changeUserPortrait(@Param("userId") String userId, @Param("base64") String base64);

	@Delete("delete from user where userId=#{userId}")
	public int removeUser(User user);
	@Delete("delete from orders where userId=#{userId}")
	public int removeOrders(User user);
	@Delete("delete from cart where userId=#{userId}")
	public int removeCart(User user);
	@Delete("delete from deliveryaddress where userId=#{userId}")
	public int removeDeliveryaddress(User user);
}
