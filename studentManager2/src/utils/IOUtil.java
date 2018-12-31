package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dao.StudentDao;
import model.Student;
import model.User;

public class IOUtil {

	public static User loadUser() { // 读取用户
		User user = null;
		try {
			String filename = "user.txt";
			File file = new File(IOUtil.class.getResource(filename).getFile());
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String temp;
			while ((temp = reader.readLine()) != null) {
				String username = temp.split(",")[0];
				String password = temp.split(",")[1];
				user = new User(username, password);
				reader.close();
				return user;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}

	//filename修改为自己电脑的路径
	public void loadStudent(StudentDao studentDao) { // 读取学生
		Student stud = null;
		try {
			String filename = "C:\\Users\\zsh\\eclipse-workspace\\studentManager2\\src\\data\\student.txt";
			File file = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String temp;
			while ((temp = reader.readLine()) != null) {
				int id = Integer.parseInt(temp.split(",")[0]);
				String number = temp.split(",")[1];
				String name = temp.split(",")[2];
				String sex = temp.split(",")[3];
				int age = Integer.parseInt(temp.split(",")[4]);
				stud = new Student(id, number, name, sex, age);
				studentDao.studentList.add(stud);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//filename修改为自己电脑的路径
	public void saveStudent(StudentDao studentDao) {//写入文件
		String filename = "C:\\Users\\zsh\\eclipse-workspace\\studentManager2\\src\\data\\student.txt";
		String all="";
		for(int i = 0; i < studentDao.studentList.size(); i++){
			Student s = (Student)studentDao.studentList.get(i);
			String temp = s.getId() + "," + s.getNumber() + "," + s.getName()+","+ s.getSex() + "," + s.getAge() + ",\n";
			all += temp;
		}
		try {
			FileWriter writer;
			writer = new FileWriter(filename);
			writer.write(all);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
