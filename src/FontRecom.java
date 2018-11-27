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

public class FontRecom extends JPanel{
	JPanel background;
	JButton btn1, btn2, btn3, btn4, btn5; 

	JPanel list;
	
	FontDTO MainFont;
	FontDAO FontDAO = new FontDAO();
	FontDAO dao = new FontDAO();
	FontDAO edao = new FontDAO();
	FontDTO dto = new FontDTO();

	String Menu_string[] = {"num","font","intro","date","tag1","tag2","mood"};
	String mood[] = {"OFFICE", "LETTER", "PPT", "HARD", "SOFT"};
	DefaultTableModel model = new DefaultTableModel(Menu_string, 0){  //셀 수정 못하게 하는 부분
		 public boolean isCellEditable(int row, int column){
			    return false;
			 }
	};
	JTable fontTable = new JTable(model);
	JScrollPane  pane = new JScrollPane(fontTable);

	FontRecom(){
		setBounds(14, 0, 782, 407);
		setLayout(null);
		fontTable.getTableHeader().setReorderingAllowed(false); // 이동 불가 
		fontTable.getTableHeader().setResizingAllowed(false); // 크기 조절 불가 
		
		btn1 = new JButton("OFFICE");
		btn1.setBounds(180, 23, 90, 49);
		add(btn1);
		
		btn2 = new JButton("LETTER");
		btn2.setBounds(270, 23, 90, 49);
		add(btn2);
		
		btn3 = new JButton("PPT");
		btn3.setBounds(360, 23, 90, 49);
		add(btn3);
		
		btn4 = new JButton("HARD");
		btn4.setBounds(450, 23, 90, 49);
		add(btn4);
		
		btn5 = new JButton("SOFT");
		btn5.setBounds(540, 23, 90, 49);
		add(btn5);
		
		btn1.addActionListener(new EventHandler());
		btn2.addActionListener(new EventHandler());
		btn3.addActionListener(new EventHandler());
		btn4.addActionListener(new EventHandler());
		btn5.addActionListener(new EventHandler());

		list = new JPanel();
		list.setBounds(90, 105, 643, 267);
		list.add(pane);
		FontDAO.userSelectAll(model);
		add(list);
		
		
		fontTable.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2) {
                	int row = fontTable.getSelectedRow();
                	Object a = fontTable.getValueAt(row, 1);
                	String ac = (String)a;
                	dto = dao.getUser(ac);
                	System.out.println(dto);
                	FontPage FontPage = new FontPage(dto);
                }
		}});
            
		}
	class EventHandler implements ActionListener {		 
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==btn1){
            	edao.getMood(model, mood[0]);
            }else if(e.getSource()==btn2){
            	edao.getMood(model, mood[1]);
            }else if(e.getSource()==btn3){
            	edao.getMood(model, mood[2]);
            }else if(e.getSource()==btn4){
            	edao.getMood(model, mood[3]);
            }else if(e.getSource()==btn5){
            	edao.getMood(model, mood[4]);
            }
        }
 
    }
}

