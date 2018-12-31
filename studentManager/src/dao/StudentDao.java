package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Student;
import utils.StringUtil;

/**
 * 学生数据访问
 * @author zsh
 *
 */
public class StudentDao {

	/*
	 * 学生添加
	 */
	public int add(Connection con,Student student) throws Exception {
		String sql = "insert into t_stu values(null,?,?,?,?)";
		PreparedStatement prepareStatement = con.prepareStatement(sql);
		prepareStatement.setString(1, student.getNumber());
		prepareStatement.setString(2, student.getName());
		prepareStatement.setString(3, student.getSex());
		prepareStatement.setInt(4, student.getAge());
		return prepareStatement.executeUpdate();
	}
	
	/**
	 * 查询学生
	 */
	public ResultSet list(Connection con,Student student)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_stu");
		if(StringUtil.isNotEmpty(student.getNumber())){
			sb.append(" and number like '%"+student.getNumber()+"%'");
		}else if(StringUtil.isNotEmpty(student.getName())){
			sb.append(" and name like '%"+student.getName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * 删除学生
	 */
	public int delete(Connection con,String id)throws Exception{
		String sql="delete from t_stu where id= ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 更新学生
	 */
	public int update(Connection con,Student student)throws Exception{
		String sql="update t_stu set number = ?,name = ? ,sex=?,age=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getNumber());
		pstmt.setString(2, student.getName());
		pstmt.setString(3, student.getSex());
		pstmt.setInt(4, student.getAge());
		pstmt.setInt(5, student.getId());
		return pstmt.executeUpdate();
	}
	
}
