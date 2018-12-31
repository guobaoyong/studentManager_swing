package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;
import utils.StringUtil;

/**
 * 登录页面
 * @author zsh
 *
 */
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField UserNameTxt;
	private JPasswordField passwordTxt;
	
	private UserDao userDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		//改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		setResizable(false);
		setTitle("学生管理系统登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("学生管理系统");
		label.setFont(new Font("宋体", Font.BOLD, 25));
		label.setIcon(new ImageIcon(Login.class.getResource("/images/logo.png")));
		
		JLabel lblNewLabel = new JLabel("用户名：");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/user.png")));
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("密  码：");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/images/password.png")));
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		
		UserNameTxt = new JTextField();
		UserNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					toLogin(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(e);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(103)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnNewButton)
									.addComponent(lblNewLabel_1)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(UserNameTxt)
										.addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(50)
									.addComponent(btnNewButton_1)))))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(label)
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(UserNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		//设置窗体默认居中
		this.setLocationRelativeTo(null);
	}

	/**
	 * 登录事件处理
	 * @param e
	 * @throws Exception 
	 */
	private void toLogin(ActionEvent evt) throws Exception {
		String userName = this.UserNameTxt.getText();
		String password = new String(this.passwordTxt.getPassword());
		if(StringUtil.isEmpty(userName)) {
			//弹窗事件
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			//跳出方法
			return;
		}
		if(StringUtil.isEmpty(password)) {
			//弹窗事件
			JOptionPane.showMessageDialog(null, "密码不能为空");
			//跳出方法
			return;
		}
		User user = new User(userName,password);
		User login = userDao.login(user);
		if(login != null) {
			dispose();
			new Main().setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "用户名或密码错误");
		}
	}

	/**
	 * 重置时间处理
	 * @param e
	 */
	private void reset(ActionEvent evt) {
		this.UserNameTxt.setText("");
		this.passwordTxt.setText("");
	}
}
