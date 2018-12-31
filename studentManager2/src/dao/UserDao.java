package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utils.IOUtil;

/**
 * 
 * @author zsh
 *	用户数据访问层
 */
public class UserDao {
	
	//用户登录方法
	public User login(User user) throws Exception {
		User result = null;
		if(user.getUserName().equals(IOUtil.loadUser().getUserName()) &&
				user.getPassWord().equals(IOUtil.loadUser().getPassWord())) {
			result = IOUtil.loadUser();
		}
		return result;
	}
}
