package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.StudentDao;
import model.Student;
import utils.DBUtil;
import utils.StringUtil;

/**
 * 添加内部窗体
 * @author zsh
 *
 */
public class Add extends JInternalFrame {
	private JTextField numberTxt;
	private JTextField nameTxt;
	private JTextField sexTxt;
	private JTextField ageTxt;
	
	private DBUtil dbUtil=new DBUtil();
	private StudentDao studentDao=new StudentDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add frame = new Add();
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
	public Add() {
		setIconifiable(true);
		setClosable(true);
		setTitle("学生添加");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("学号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		
		numberTxt = new JTextField();
		numberTxt.setColumns(10);
		
		JLabel label = new JLabel("姓名：");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("性别：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JLabel label_2 = new JLabel("年龄：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		
		sexTxt = new JTextField();
		sexTxt.setColumns(10);
		
		ageTxt = new JTextField();
		ageTxt.setColumns(10);
		
		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(e);
			}
		});
		
		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(56)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(ageTxt, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(sexTxt, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(numberTxt, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(102)
							.addComponent(button)
							.addGap(18)
							.addComponent(button_1)))
					.addContainerGap(177, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(numberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(sexTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(ageTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
	}

	//添加
	private void add(ActionEvent e) {
		String number=this.numberTxt.getText();
		String name=this.nameTxt.getText();
		String sex=this.sexTxt.getText();
		String age=this.ageTxt.getText();
		if(StringUtil.isEmpty(number)){
			JOptionPane.showMessageDialog(null, "学号不能为空！");
			return;
		}
		if(StringUtil.isEmpty(name)){
			JOptionPane.showMessageDialog(null, "姓名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(sex)){
			JOptionPane.showMessageDialog(null, "性别不能为空！");
			return;
		}
		if(StringUtil.isEmpty(age)){
			JOptionPane.showMessageDialog(null, "年龄不能为空！");
			return;
		}
		Student stu = new Student(number,name,sex,Integer.parseInt(age));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int n=studentDao.add(con, stu);
			if(n==1){
				JOptionPane.showMessageDialog(null, "学生添加成功！");
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "学生添加失败！");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "学生添加失败！");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
		}
		
	}

	private void reset(ActionEvent e) {
		resetValue();
	}

	/**
	 * 重置表单
	 */
	private void resetValue() {
		this.numberTxt.setText("");
		this.nameTxt.setText("");
		this.sexTxt.setText("");
		this.ageTxt.setText("");
	}
	
	
}
