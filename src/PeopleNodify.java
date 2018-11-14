import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PeopleNodify extends JPanel implements ActionListener{
	JLabel Enter_name_text, Enter_gender_text, Enter_age_text, Enter_phone_text,Enter_intro_text,
	Enter_ID_text, Enter_PWD_text, Enter_PWD_HAK_text,
	Enter_Title, Enter_ID_text2 ;
	
	private JTextField Enter_name_field,Enter_phone_field,Enter_PWD_field,Enter_PWD_HAK_field;
	
	JPanel Panel_info, Panel_IDPWD, Panel_Button;
	
	JButton Enter_OK_Button, Enter_reset_Button;
	
	ButtonGroup gender_RadioButton;
	
	JSplitPane Enter_Button;
	
	JTextArea Enter_intro_area;
	
	JComboBox Enter_age_combo, changecombo;
	
	JRadioButton Enter_gender_man,Enter_gender_woman;
	
	int age_index;
	
	int gender_index;
	
	String[] age_combo = new String[100];
	
	
	
	PeopleNodify(){
		setLayout(null);
		Panel_info = new JPanel();
		Panel_info.setBounds(14, 90, 377, 234);
		Panel_info.setLayout(null);
		add(Panel_info);
//기본 인적 정보 text
		Enter_name_text = new JLabel("이름");
		Enter_gender_text = new JLabel("성별");
		Enter_age_text = new JLabel("나이");
		Enter_phone_text = new JLabel("전화번호");
		Enter_intro_text = new JLabel("한줄소개");
		
		Enter_name_text.setBounds(31, 29, 62, 18);
		Enter_gender_text.setBounds(31, 59, 62, 18);
		Enter_age_text.setBounds(31, 89, 62, 18);
		Enter_intro_text.setBounds(31, 149, 62, 18);
		
		Panel_info.add(Enter_age_text);
		Panel_info.add(Enter_name_text);
		Panel_info.add(Enter_gender_text);
		Panel_info.add(Enter_phone_text);
		Panel_info.add(Enter_intro_text);

//기본 인적 정보 field	
		String name = Main.MainUser.getName();
		Enter_name_field = new JTextField(name);
		Enter_phone_field = new JTextField();
		//Enter_phone_field.setText(user.getPhone());
		
		Enter_name_field.setBounds(108, 26, 143, 24);
		Enter_phone_field.setBounds(107, 116, 144, 24);
		
		Enter_name_field.setColumns(10);
		Enter_phone_field.setColumns(10);
		
		Panel_info.add(Enter_name_field);
		Panel_info.add(Enter_phone_field);
		
//기본 인적 정보 area	
		Enter_intro_area = new JTextArea(7,20);
		Enter_intro_area.setBounds(107, 147, 144, 75);
		Panel_info.add(new JScrollPane(Enter_intro_area));
		
//기본 인적 정보 combo		
		for(int i = 0; i < age_combo.length; i++) {
			age_combo[i] = String.valueOf(i);
		}
		Enter_age_combo = new JComboBox(age_combo);
		Enter_age_combo.setBounds(124, 86, 34, 24);
		//Enter_age_combo.setSelectedIndex(user.getAge());
		Panel_info.add(Enter_age_combo);
//기본 인적 정보 RadioButton			
		gender_RadioButton = new ButtonGroup();
		
		Enter_gender_man = new JRadioButton("남");
		Enter_gender_woman = new JRadioButton("여");
		
		Enter_gender_man.addActionListener(this);
		Enter_gender_woman.addActionListener(this);
		
		Enter_gender_man.setBounds(114, 55, 45, 27);
		Enter_gender_woman.setBounds(169, 55, 45, 27);
		
		//if(user.getGender() == 0) {
			gender_RadioButton.setSelected(Enter_gender_man.getModel(), true);
		//}else gender_RadioButton.setSelected(Enter_gender_woman.getModel(), true);
		
		gender_RadioButton.add(Enter_gender_man);
		gender_RadioButton.add(Enter_gender_woman);

		Panel_info.add(Enter_gender_man);
		Panel_info.add(Enter_gender_woman);
		
//ID,PWD Panel	
		Panel_IDPWD = new JPanel();
		Panel_IDPWD.setLayout(null);
		Panel_IDPWD.setBounds(391, 90, 377, 234);
		add(Panel_IDPWD);
//ID,PWD text	
		Enter_ID_text = new JLabel("ID");
		Enter_PWD_text = new JLabel("PWD");
		Enter_PWD_HAK_text = new JLabel("PWD_HAK");
		Enter_ID_text.setBounds(31, 29, 62, 18);
		Enter_PWD_text.setBounds(31, 89, 62, 18);
		Enter_PWD_HAK_text.setBounds(31, 136, 62, 18);
		
		Panel_IDPWD.add(Enter_ID_text);
		Panel_IDPWD.add(Enter_PWD_text);
		Panel_IDPWD.add(Enter_PWD_HAK_text);
//ID,PWD field		
		Enter_ID_text2 = new JLabel();
		//Enter_ID_text2.setText(user.getID());
		Enter_PWD_field = new JTextField();
		//Enter_PWD_field.setText(user.getPWD());
		Enter_PWD_HAK_field = new JTextField();
		
		Enter_PWD_field.setColumns(10);
		Enter_PWD_HAK_field.setColumns(10);
		
		Enter_ID_text2.setBounds(108, 26, 143, 24);
		Enter_PWD_field.setBounds(107, 86, 144, 24);
		Enter_PWD_HAK_field.setBounds(108, 133, 144, 24);
		
		Panel_IDPWD.add(Enter_ID_text2);
		Panel_IDPWD.add(Enter_PWD_field);
		Panel_IDPWD.add(Enter_PWD_HAK_field);
//
//회원가입, 리셋  Button	
		Enter_Button = new JSplitPane();
		Enter_Button.setBounds(287, 342, 207, 29);
		add(Enter_Button);
		
		Enter_OK_Button = new JButton("수정하기");
		Enter_reset_Button = new JButton("취소하기");
		
		Enter_OK_Button.addActionListener(this);
		Enter_reset_Button.addActionListener(this);
		
		Enter_Button.setLeftComponent(Enter_OK_Button);
		Enter_Button.setRightComponent(Enter_reset_Button);

//combo 이벤트리스너	
	Enter_age_combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changecombo = (JComboBox)e.getSource();//Action이벤트가 발생한 콤보박스 알아내기
				age_index = changecombo.getSelectedIndex();//콤보박스의 선택된 아이템의 인덱스 번호 알아내기
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(Enter_OK_Button)){
			
			if(!Enter_PWD_field.getText().equals(Enter_PWD_HAK_field.getText())) {
			Enter_PWD_field.setText("");
				Enter_PWD_HAK_field.setText("");
				int Enter_result = JOptionPane.showConfirmDialog(null, "비밀번호를 다시 확인해 주세요","login Enter_result",JOptionPane.YES_OPTION);
			}else if(!Enter_PWD_field.getText().equals(user.getPWD())) {
				int Enter_result = JOptionPane.showConfirmDialog(null, "PWD를 정말 바꾸시겠습니까?","HAK_Enter_result",JOptionPane.YES_NO_OPTION);
				if(Enter_result != JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null,"회원수정에 실패하셨습니다.","fail Nodify", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String NAME = Enter_name_field.getText();
					String ID = Enter_ID_text2.getText();
					String PWD = Enter_PWD_field.getText();
					String INTRO = Enter_intro_area.getText();
					String PHONE = Enter_phone_field.getText();
					
					People person = new People(NAME,gender_index,age_index, PHONE,ID,PWD,INTRO);
					
					try {
						PeopleDAO.People_nodify(person);
					} catch (SQLException e1) {
						System.out.println("실패실패");
					}
				}
			}
			
		}else if(e.getSource().equals(Enter_reset_Button)){
			Enter_name_field.setText("");
			Enter_phone_field.setText("");
			Enter_PWD_field.setText("");
			Enter_PWD_HAK_field.setText("");
			gender_RadioButton.setSelected(Enter_gender_man.getModel(), false);
			gender_RadioButton.setSelected(Enter_gender_woman.getModel(), false);
			Enter_age_combo.setSelectedIndex(0);
		}	
		if (e.getSource() == Enter_gender_man) {
			gender_index = 0;
		} else if (e.getSource() == Enter_gender_woman) {
			gender_index = 1;
		}

		
	} 
	
}




