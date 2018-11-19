import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class login extends JPanel implements ActionListener{
	private JTextField Input_ID_field;
	
	private JTextField Input_PWD_field;
	
	JButton Login_Button;
	
	JLabel Input_ID_text,Input_PW_text; 
	
	ImageIcon Login_normalIcon, Login_rolloverIcon, Login_pressedIcon;

	public static Connection conn = null;
	
	login(){
		
		setBounds(14, 12, 754, 383);
		setLayout(null);
		Input_ID_text = new JLabel("ID");
		Input_PW_text = new JLabel("PWD");
		Input_ID_field = new JTextField();
		Input_PWD_field = new JTextField();
		
		Input_ID_text.setBounds(266, 131, 62, 18);	
		Input_PW_text.setBounds(266, 165, 62, 18);

		Input_ID_field.setBounds(342, 128, 116, 24);	
		Input_PWD_field.setBounds(342, 162, 116, 24);
		Input_ID_field.setColumns(10);
		Input_PWD_field.setColumns(10);
		
		add(Input_ID_text);
		add(Input_PW_text);
		add(Input_ID_field);
		add(Input_PWD_field);
		
		//JPanel Button = new JPanel();
		//Button.setLayout(null);
		
		Login_normalIcon = new ImageIcon("image\\Login.png");
		Login_rolloverIcon = new ImageIcon("image\\Login hover.png");
		Login_pressedIcon = new ImageIcon("image\\Login active.png");
		
		Login_Button = new JButton(Login_normalIcon);
		Login_Button.setPressedIcon(Login_pressedIcon);
		Login_Button.setRolloverIcon(Login_rolloverIcon);
		Login_Button.setBounds(307, 201, 150, 27);
		
		add(Login_Button);
		
		//add(Button);
		Login_Button.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(Login_Button)) {
			int login_result = JOptionPane.showConfirmDialog(null, "로그인을 하시겠습니까?","login confirm",JOptionPane.YES_NO_OPTION);
			
			if(login_result == JOptionPane.CLOSED_OPTION) {
				JOptionPane.showMessageDialog(null,"로그인을 취소합니다.","Cancle Login", JOptionPane.INFORMATION_MESSAGE);
			}
			
			else if(login_result == JOptionPane.YES_OPTION) {
				if(Input_ID_field != null && Input_PWD_field != null){
					try {
						People user = PeopleDAO.People_get(Input_ID_field.getText());
						if(user != null){
							if(user.PWD.equals(Input_PWD_field.getText())) {
								JOptionPane.showMessageDialog(null,"로그인에 성공하셨습니다.","Seccues Login", JOptionPane.INFORMATION_MESSAGE);
								Main.MainUser = user;
							}
							else {
								JOptionPane.showMessageDialog(null,"로그인에 실패하셨습니다.","fail Login", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"로그인에 실패하셨습니다.","fail Login", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			
		}
		
	}
}

