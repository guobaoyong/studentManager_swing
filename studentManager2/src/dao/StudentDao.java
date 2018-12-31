package dao;

import java.util.ArrayList;
import java.util.List;

import model.Student;
import utils.IOUtil;
import utils.StringUtil;

/**
 * 学生数据访问
 * @author zsh
 *
 */
public class StudentDao {
	public List<Student> studentList = new ArrayList<>();

	/*
	 * 学生添加
	 */
	public int add(Student student) throws Exception {
		try {
			int max = 0;
			studentList.clear();
			new IOUtil().loadStudent(this);			
			for (Student student2 : studentList) {
				if(student2.getId() > max) {
					max = student2.getId();
				}
			}
			student.setId(max+1);
			studentList.add(student);
			new IOUtil().saveStudent(this);
			return 1;
		}catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 查询学生
	 */
	public List<Student> list(Student student)throws Exception{
		if(student.getAge() == 0 && StringUtil.isEmpty(student.getNumber())) {
			studentList.clear();
			new IOUtil().loadStudent(this);			
			return studentList;
		}else {
			List<Student> result = new ArrayList<Student>();
			studentList.clear();
			new IOUtil().loadStudent(this);
			for (Student student1 : studentList) {
				if(student1.getNumber().contains(student.getNumber()) 
						|| student1.getName().contains(student.getName())) {
					result.add(student1);
					continue;
				}
			}
			return result;
		}
	}
	
	/**
	 * 删除学生
	 */
	public int delete(String id)throws Exception{
		studentList.clear();
		new IOUtil().loadStudent(this);
		for(int i = studentList.size() - 1; i >= 0; i--) {
			Student student = studentList.get(i);
			if(String.valueOf(student.getId()).equals(id)) {
				studentList.remove(student);
				new IOUtil().saveStudent(this);
				return 1;
			}
		}
		return 0;
	}
	
	/**
	 * 更新学生
	 */
	public int update(Student student)throws Exception{
		System.out.println(student);
		studentList.clear();
		new IOUtil().loadStudent(this);
		System.out.println(studentList.size() - 1);
		for(int i = studentList.size() - 1; i >= 0; i--) {
			Student student2 = studentList.get(i);
			if(String.valueOf(student2.getId()).equals(String.valueOf(student.getId()))) {
				studentList.set(studentList.indexOf(student2), student);
				new IOUtil().saveStudent(this);
				return 1;
			}
		}
		return 0;
	}
	
}
