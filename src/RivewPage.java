import java.awt.Color;
import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.util.List;
import java.util.Vector;

public class RivewPage extends JPanel {
	JPanel background;
	JTextField searchText;
	JButton searchButton, InputButton;
	JComboBox searchCombo;
	RivewDTO MainRivew;
	RivewDAO dao = new RivewDAO();
	
	String serachCombo_string[] = {"ALL","ID","name","font"};
	String Menu_string[] = {"num","font","title","ID","name","rivew","like"};
DefaultTableModel model = new DefaultTableModel(Menu_string, 0);
   JTable rivewTable = new JTable(model);
   JScrollPane  pane = new JScrollPane(rivewTable);
   RivewDAO RivewDAO = new RivewDAO();
	
    RivewPage(){
		setBounds(14, 0, 782, 407);
		setLayout(null);
		
		searchText = new JTextField();
		searchText.setBounds(187, 12, 461, 32);
		add(searchText);
		searchText.setColumns(10);
		
		searchCombo = new JComboBox(serachCombo_string);
		searchCombo.setBounds(92, 12, 91, 32);
		add(searchCombo);
		
		searchButton = new JButton("검색");
		searchButton.setBounds(662, 12, 71, 29);
		add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			        // JComboBox에 선택된 value 가져오기
			        String fieldName = searchCombo.getSelectedItem().toString();
			        System.out.println(fieldName);
			        if (fieldName.trim().equals("ALL")) {// 전체검색
			            dao.userSelectAll(model);
			            if (model.getRowCount() > 0)
			            	rivewTable.setRowSelectionInterval(0, 0);
			        } else {
			            if (searchText.getText().trim().equals("")) {
			            	JOptionPane.showMessageDialog(null,"검색어를 입력해 주세요. ","None SearchTExt", JOptionPane.INFORMATION_MESSAGE);
			                searchText.requestFocus();
			            } else {// 검색어를 입력했을경우
			                dao.getUserSearch(model, fieldName, searchText.getText());
			                if (model.getRowCount() > 0)
			                	rivewTable.setRowSelectionInterval(0, 0);
			            }
			        }
			}
			
		});
		background = new JPanel();
		background.setBounds(90, 56, 643, 316);
        background.add(pane);
        RivewDAO.userSelectAll(model);
        add(background);
        
        InputButton = new JButton("글쓰기");
        InputButton.setBounds(630, 380, 105, 27);
        add(InputButton);
        InputButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InputRivew InputRivew = new InputRivew();
				
			}
		});
        
          
	}

}

		
		