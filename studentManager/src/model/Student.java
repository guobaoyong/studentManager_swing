package model;

/**
 * 学生实体
 * @author zsh
 *
 */
public class Student {
	private Integer id;
	private String number; //学号
	private String name; //姓名
	private String sex; //性别
	private int age; //年龄
	
	
	
	public Student(String number, String name, String sex, int age) {
		super();
		this.number = number;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	
	
	public Student(Integer id, String number, String name, String sex, int age) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}



	public Student() {
		super();
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
