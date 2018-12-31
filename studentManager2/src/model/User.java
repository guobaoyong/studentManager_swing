package model;

/**
 * 
 * @author zsh
 * 用户实体
 */
public class User {
	
	private Integer id;//id
	private String userName;//用户名
	private String passWord;//密码
	
	//构造方法
	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public User() {
		super();
	}


	//get，set方法
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}
