import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class RivewShow extends JDialog{
	JLabel Title, TitleText, Writer, WriterText, Font, FontText, Num;
	JTextPane Contect;
	
	RivewShow(RivewDTO rivew){
		setSize(800,500);
		setLayout(null);
		
		Title = new JLabel("제목");
		Title.setBounds(83, 12, 118, 27);
		add(Title);
		
		TitleText = new JLabel(rivew.getTitle());
		TitleText.setBounds(215, 12, 118, 27);
		add(TitleText);
		
		Writer = new JLabel("글쓴이");
		Writer.setBounds(433, 51, 118, 27);
		add(Writer);
		
		WriterText = new JLabel(rivew.getName());
		WriterText.setBounds(585, 51, 118, 27);
		add(WriterText);
		
		Font = new JLabel("글꼴");
		Font.setBounds(83, 51, 118, 27);
		add(Font);
		
		FontText = new JLabel(rivew.getFont());
		FontText.setBounds(215, 51, 118, 27);
		add(FontText);
		
		Contect = new JTextPane();
		Contect.setText(rivew.getRivew());
		Contect.setBounds(70, 111, 632, 270);
		add(Contect);
		
		Num = new JLabel(String.valueOf(rivew.getNum()));
		Num.setBounds(671, 16, 32, 27);
		add(Num);
	
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		 
	}
}
