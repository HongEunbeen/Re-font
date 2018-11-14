import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InputRivew extends JDialog implements ActionListener{
	
	JTextField Title_field;
	JPanel Input_panel, Panel_Button;
	JTextArea Input_content;
	JLabel Title_text, Font_text;
	JButton CancelButton, InputButton;
	JSplitPane ButtonGroup;
	JComboBox comboBox, SelectCombo;
	String SelectFont;
	InputRivew(){
		setSize(800,500);
		setModal(true);
		setLayout(null);
		Title_field = new JTextField();
		Title_field.setBounds(169, 61, 443, 32);
		add(Title_field);
		Title_field.setColumns(10);
		
		Input_panel = new JPanel();
		Input_panel.setBounds(90, 105, 643, 267);
		add(Input_panel);
		Input_panel.setLayout(new BoxLayout(Input_panel, BoxLayout.Y_AXIS));
		
		Input_content = new JTextArea("", 40, 50);
		Input_panel.add(new JScrollPane(Input_content));

		
		Vector cbItems = FontDAO.ComboItem();
		final DefaultComboBoxModel cbModel = new DefaultComboBoxModel(cbItems);
		comboBox = new JComboBox(cbModel);
		comboBox.setBounds(169, 12, 132, 32);
		add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectFont = comboBox.getSelectedItem().toString();
				
			}
		});
		
		Title_text = new JLabel("폰트 종류");
		Title_text.setBounds(90, 19, 62, 18);
		add(Title_text);
		
		Font_text = new JLabel("제목");
		Font_text.setBounds(90, 68, 62, 18);
		add(Font_text);

		CancelButton = new JButton("취소하기");
		InputButton = new JButton("작성완료");
		
		CancelButton.addActionListener(this);
		InputButton.addActionListener(this);
		InputButton.setBounds(626, 12, 107, 81);
		add(InputButton);
		
		
		setVisible(true);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(InputButton)){
			String FONT = SelectFont;
			String TITLE = Title_field.getText();
			//String ID = Main.MainUser.getID();
			//String NAME = Main.MainUser.getName();
			String RIVEW = Input_content.getText();
			
			RivewDTO rivew = new RivewDTO(0 ,FONT, TITLE, "d", "d", RIVEW, 0);
			
			try {
				RivewDAO.RivewDTO_add(rivew);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource().equals(CancelButton)){
			Title_field.setText("");
			Input_content.setText("");
			SelectFont = null;
		}
	}
}
