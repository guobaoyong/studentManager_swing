package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @author zsh 数据库连接工具类
 */
public class DBUtil {
	private String dbUrl = "jdbc:mysql://localhost:3306/db_stu"; // 数据库连接地址
	private String dbUserName = "root"; // 用户名
	private String dbPassword = "root"; // 密码
	private String jdbcName = "com.mysql.jdbc.Driver"; // 驱动名称

	/**
	 * 获取数据库连接
	 * 
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getCon() throws Exception {
		//反射加载类
		Class.forName(jdbcName);
		//DriverManager驱动管理器
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}

	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
}
