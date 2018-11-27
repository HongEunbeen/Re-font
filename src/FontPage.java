import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FontPage extends JDialog{
	private ClientBackground client = new ClientBackground();
    private static String nickName;
	JLabel FontTitle;
	JTextArea InputText;
	JSlider SizeSlider;
	JButton DownButton, InfoButton;
	JLabel LabelTitle, LabelContent;
	DownloadDTO ddto = new DownloadDTO();
	DownloadDAO ddao = new DownloadDAO();
	connDTO cdto = new connDTO();
	connDAO cdao = new connDAO();
	FontDTO fdto = new FontDTO();
	FontDAO fdao = new FontDAO();
	FontPage(FontDTO fontdto){
		
		setSize(800,500);
		pack();
		setVisible(true);
		setLayout(null);
		System.out.println(fontdto.getNum());
		System.out.println();
		FontTitle = new JLabel(fontdto.getFont());
		setBounds(295, 12, 175, 48);
		add(FontTitle);
		
		InputText = new JTextArea();
		InputText.setFont(new Font(fontdto.getFont(),Font.PLAIN,50));
		InputText.setBounds(70, 67, 643, 165);
		add(InputText);
		
		SizeSlider = new JSlider();
		
		SizeSlider.setPaintLabels(true);
		SizeSlider.setPaintTicks(true);
		SizeSlider.setPaintTrack(true);
		SizeSlider.setMajorTickSpacing(50);
		SizeSlider.setMinorTickSpacing(10);
		SizeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				InputText.setFont(new Font(fontdto.getFont(),Font.PLAIN,SizeSlider.getValue()));
			}
		});
		SizeSlider.setBounds(70, 238, 642, 26);
		add(SizeSlider);
		
		DownButton = new JButton("down");
		DownButton.setBounds(324, 355, 118, 40);
		add(DownButton);
		
		InfoButton = new JButton("info");
		InfoButton.setBounds(608, 12, 105, 27);
		add(InfoButton);
		
		
		LabelTitle = new JLabel();
		LabelTitle.setText(fontdto.getFont());
		LabelTitle.setFont(new Font(fontdto.getFont(),Font.PLAIN,20));
		LabelTitle.setBounds(300, 270, 200, 50);
		add(LabelTitle);
		
		LabelContent = new JLabel();
		LabelContent.setText(fontdto.getIntro());
		LabelContent.setFont(new Font(fontdto.getFont(),Font.PLAIN,10));
		LabelContent.setBounds(238, 302, 300, 48);
		add(LabelContent);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		
		DownButton.addActionListener(new ActionListener() {
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				downloader downloader = new downloader(fontdto.getNum());
				FontDTO UserDTO = fdao.getUser(fontdto.getFont());
				int like = UserDTO.getLike();
				System.out.println(like);
				++like;
				try {
					cdto = cdao.getConn();
					ddto.setID(cdto.getID());
					ddto.setFont(UserDTO.getFont());
					ddao.DownloadDTO_add(ddto);
					System.out.println(UserDTO.getFont());
					System.out.println(like);
					fdao.plusLike(UserDTO.getFont(), like);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		InfoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"폰트는 컴퓨터 프로그램으로서만 저작권을 갖는다\n."
						+ " 글씨체(폰트 도안) 자체가 보호되는 저작물은 아니다.\n"
						+ " 폰트를 활용해 제작된 출력물이나 이미지 같은 2차 저작물에는 해당 폰트의 저작권 효력이 미치지 않는다.\n"
						+ " 다만 폰트 ‘파일’을 무단 복제하거나 불법으로 내려받기 한 경우에는 저작권 침해로 죄를 물을 수 있다.\n","IMFOMATRION", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
	}	
	public void connClient() {
		 client.setGui(this);
	     client.setNickname(nickName);
	     client.connect();
	}
	
}
