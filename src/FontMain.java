import java.awt.Component;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FontMain extends JPanel{
	JPanel background;
	JTextField hashtag_filed;
	JButton searchButton, resetButton, rivewButton; 
	JComboBox orderCombo;
	JPanel list;
	JLabel hashtag_text, comdo_text, hashtag_input1, hashtag_input2;
	String location = null;
	FontDTO MainFont;
	FontDAO FontDAO = new FontDAO();
	FontDAO dao = new FontDAO();
	FontDTO dto = new FontDTO();
	String comdo_text_string[] = {"최신순","인기순"};
	String Menu_string[] = {"num","font","intro","date","tag1","tag2","mood"};
	String a[] = new String[2]; 
	DefaultTableModel model = new DefaultTableModel(Menu_string, 0){  //셀 수정 못하게 하는 부분
		 public boolean isCellEditable(int row, int column){
			    return false;
			 }
	};
	JTable fontTable = new JTable(model);
	JScrollPane  pane = new JScrollPane(fontTable);
	int i = 0;
	FontMain(){
		setBounds(14, 0, 782, 407);
		setLayout(null);
		fontTable.getTableHeader().setReorderingAllowed(false); // 이동 불가 
		fontTable.getTableHeader().setResizingAllowed(false); // 크기 조절 불가 
		
		hashtag_filed = new JTextField();
		hashtag_filed.setBounds(344, 61, 268, 32);
		add(hashtag_filed);
		hashtag_filed.setColumns(10);
		
		orderCombo = new JComboBox(comdo_text_string);
		orderCombo.setBounds(148, 61, 76, 32);
		add(orderCombo);
		orderCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String fieldName;
				if(orderCombo.getSelectedItem().toString().equals("인기순")) {
					fieldName = "good";
				}else fieldName = "date";    
		        dao.getUserOrder(model, fieldName);			
			}
		});
		hashtag_text = new JLabel("해쉬테크");
		hashtag_text.setBounds(268, 68, 62, 18);
		add(hashtag_text);
		
		hashtag_input1 = new JLabel();
		hashtag_input1.setBounds(344, 40, 100, 20);
		add(hashtag_input1);
		
		hashtag_input2 = new JLabel();
		hashtag_input2.setBounds(400, 40, 100, 20);
		add(hashtag_input2);
		
		
		comdo_text = new JLabel("정렬방법");
		comdo_text.setBounds(90, 68, 62, 18);
		add(comdo_text);
		
		searchButton = new JButton("테그 추가하기");
		searchButton.setBounds(628, 61, 105, 27);
		add(searchButton);
		
		resetButton = new JButton("원래대로");
		resetButton.setBounds(628, 90, 105, 27);
		add(resetButton);
		
		rivewButton = new JButton("리뷰보기");
		rivewButton.setBounds(628, 40, 105, 27);
		add(rivewButton);
		
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			        // JComboBox에 선택된 value 가져오기
			        
			   if (!hashtag_filed.getText().trim().equals("")) {
			       if(i >= 2) {
			    	   JOptionPane.showMessageDialog(null,"태그는 두개까지만 허용 됩니다.","Seccues Login", JOptionPane.INFORMATION_MESSAGE);		 
			        }
			       else {
			    	   a[i] = hashtag_filed.getText();
			    	   if(i == 0) {
			    		   hashtag_input1.setText(a[0]);
			    		   dao.getUserSearch_1(model, a[0]);
			    		   i++;
			    	   }else {
			    		   hashtag_input2.setText(a[1]);
			    		   dao.getUserSearch_2(model,a[0], a[1]);
			    		   i++;	   
			    		   
			    	   }
			       }
			       	                
			   }
			
		}});
		
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				i = 0;
				a[0] = null;
				a[1] = null;
				hashtag_input1.setText("");
				hashtag_input2.setText("");
				dao.userSelectAll(model);
	            if (model.getRowCount() > 0)
	            	fontTable.setRowSelectionInterval(0, 0);
				
			}
		});
		rivewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Rivew_my_dialog Rivew_my_dialog = new Rivew_my_dialog(location);
				
			}
		});

		list = new JPanel();
		list.setBounds(90, 105, 643, 267);
		list.add(pane);
		FontDAO.userSelectAll(model);
		add(list);
		
		
		fontTable.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1) {
					location = fontTable.getValueAt(fontTable.getSelectedRow(), 1).toString();
                }
                if(e.getClickCount()==2) {
                	int row = fontTable.getSelectedRow();
                	Object a = fontTable.getValueAt(row, 1);
                	String ac = (String)a;
                	System.out.println(a);
                	System.out.println((String)a);
                	dto = dao.getUser(ac);
                	FontPage FontPage = new FontPage(dto);
                }
			}
		});
            
		}
}

