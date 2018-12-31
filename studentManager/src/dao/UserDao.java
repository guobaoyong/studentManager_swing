package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

/**
 * 
 * @author zsh
 *	用户数据访问层
 */
public class UserDao {
	
	//用户登录方法
	public User login(Connection con,User user) throws Exception {
		User result = null;
		//sql语句
		String sql = "select * from t_user where userName = ? and password = ?";
		//创建PreparedStatement
		PreparedStatement p = con.prepareStatement(sql);
		//设置参数
		p.setString(1, user.getUserName());
		p.setString(2, user.getPassWord());
		//返回结果集
		ResultSet executeQuery = p.executeQuery();
		if(executeQuery.next()) {
			result=new User();
			result.setId(executeQuery.getInt("id"));
			result.setUserName(executeQuery.getString("userName"));
			result.setPassWord(executeQuery.getString("password"));
		}
		return result;
	}
}
