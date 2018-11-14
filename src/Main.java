import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Main extends JFrame implements ActionListener{
	JMenuBar menuBar;
	JMenu Menu_1, Menu_2, Menu_3, Menu_4;
	JMenuItem Menu_1_1,Menu_1_2,Menu_2_1,Menu_2_2,Menu_2_3,Menu_2_4,Menu_3_1,Menu_3_2,Menu_4_1,Menu_4_2,Menu_4_3;
	
	public static CardLayout card = new CardLayout();
	
	login Login = new login();
	Enter Enter = new Enter();
	Introduce Introduce = new Introduce();
	Introduce_my Introduce_My = new Introduce_my();
	Mainpage Mainpage = new Mainpage();
	RivewPage RivewPage = new RivewPage();
	//PeopleNodify PeopleNodify = new PeopleNodify();
	public static People MainUser;
	
	public Main() {
		setLayout(card);
		setTitle("은빈이의 귀여운 페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Menu();
		add(Mainpage,"Mainpage");
		add(Login,"Login");
		add(Enter,"Enter");
		add(Introduce,"Introduce");
		add(Introduce_My,"Introduce_My");
		add(RivewPage,"RivewPage");
		//add(PeopleNodify,"PeopleNodify");
	}
	public void Menu(){
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(240, 240, 240));
		
		setJMenuBar(menuBar);
		Menu_1 = new JMenu("소개 및 정보");
		Menu_2 = new JMenu("글꼴");
		Menu_3 = new JMenu("내 보관함");
		Menu_4 = new JMenu("로그인");
		
		menuBar.add(Menu_1);
		menuBar.add(Menu_2);
		menuBar.add(Menu_3);
		menuBar.add(Menu_4);
		
		Menu_1_1 = new JMenuItem("홈페이지 소개");
		Menu_1_2 = new JMenuItem("개발자 소개");
		Menu_2_1  = new JMenuItem("글꼴");
		Menu_2_2 = new JMenuItem("검색");
		Menu_2_3 = new JMenuItem("순위");
		Menu_2_4 = new JMenuItem("평가");
		Menu_3_1 = new JMenuItem("다운로드 글꼴 조회");
		Menu_3_2 = new JMenuItem("내 글 찾기");
		Menu_4_1 = new JMenuItem("로그인");
		Menu_4_2 = new JMenuItem("회원가입");
		Menu_4_3 = new JMenuItem("내 정보");
		Menu_1_1.addActionListener(this);
		Menu_1_2.addActionListener(this);
		Menu_2_1.addActionListener(this);
		Menu_2_2.addActionListener(this);
		Menu_2_3.addActionListener(this);
		Menu_2_4.addActionListener(this);
		Menu_3_1.addActionListener(this);
		Menu_3_2.addActionListener(this);
		Menu_4_1.addActionListener(this);
		Menu_4_2.addActionListener(this);
		Menu_4_3.addActionListener(this);
		
		Menu_1.add(Menu_1_1);
		Menu_1.add(Menu_1_2);
		Menu_2.add(Menu_2_1);
		Menu_2.add(Menu_2_2);
		Menu_2.add(Menu_2_3);
		Menu_2.add(Menu_2_4); 
		Menu_3.add(Menu_3_1);
		Menu_3.add(Menu_3_2);
		Menu_4.add(Menu_4_1);
		Menu_4.add(Menu_4_2);
		Menu_4.add(Menu_4_3);
	}
	public static Connection conn = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					
					frame.setSize(1280,700);
					frame.setVisible(true);
					
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
	});	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(Menu_1_1)){
			card.show(getContentPane(), "Introduce");
		}else if(e.getSource().equals(Menu_1_2)) {
			card.show(getContentPane(), "Introduce_My");
		}else if(e.getSource().equals(Menu_2_1)) {
			card.show(getContentPane(), "");
		}else if(e.getSource().equals(Menu_2_2)) {
			card.show(getContentPane(), "");
		}else if(e.getSource().equals(Menu_2_3)) {
			card.show(getContentPane(), "");
		}else if(e.getSource().equals(Menu_2_4)) {
			card.show(getContentPane(), "RivewPage");
		}else if(e.getSource().equals(Menu_3_1)) {
			card.show(getContentPane(), "");
		}else if(e.getSource().equals(Menu_3_2)) {
			card.show(getContentPane(), "");
		}else if(e.getSource().equals(Menu_4_1)) {
			if(MainUser == null) {
				card.show(getContentPane(), "Login");
			}else {
				JOptionPane.showMessageDialog(null,"이미 로그인 되어있습니다.","Seccues Enter", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource().equals(Menu_4_2)) {
			if(MainUser == null) {
				card.show(getContentPane(), "Enter");
			}else {
				JOptionPane.showMessageDialog(null,"이미 로그인 되어있습니다.","Seccues Enter", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource().equals(Menu_4_3)) {
			if(MainUser == null) {
				JOptionPane.showMessageDialog(null,"로그인을 해주세요","Seccues Enter", JOptionPane.INFORMATION_MESSAGE);
				card.show(getContentPane(), "Login");
			}
			//card.show(getContentPane(), "UserNodify");
		}
		
	}
	
	
}
class Mainpage extends JPanel implements ActionListener{
	JLabel MainImage;
	JButton InfoButton;
	Mainpage(){
		setBounds(0, 0, 782, 407);
		setLayout(null);
		MainImage = new JLabel(new ImageIcon("image_!/MainImage.jpg"));
		MainImage.setBounds(60, 12, 670, 317);
		add(MainImage);
		
		InfoButton = new JButton("저작권?");
		InfoButton.setBounds(635, 341, 95, 39);
		add(InfoButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
