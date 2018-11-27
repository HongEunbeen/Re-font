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

public class Download_my extends JPanel{
	JPanel background;
	JPanel list;

	FontDTO MainFont;
	FontDAO dao = new FontDAO();
	FontDTO dto = new FontDTO();
	DownloadDAO ddao = new DownloadDAO();
	DownloadDTO ddto = new DownloadDTO();
	connDTO cdto = new connDTO();
	connDAO cdao = new connDAO();
	
	String Menu_string[] = {"num","font","intro","date","tag1","tag2","mood"};
	DefaultTableModel model = new DefaultTableModel(Menu_string, 0){  //셀 수정 못하게 하는 부분
		 public boolean isCellEditable(int row, int column){
			    return false;
			 }
	};
	JTable fontTable = new JTable(model);
	JScrollPane  pane = new JScrollPane(fontTable);
	
	Download_my(){
		setBounds(14, 0, 782, 407);
		setLayout(null);
		fontTable.getTableHeader().setReorderingAllowed(false); // 이동 불가 
		fontTable.getTableHeader().setResizingAllowed(false); // 크기 조절 불가 

		list = new JPanel();
		list.setBounds(90, 105, 643, 267);
		list.add(pane);
		cdto = cdao.getConn();
		dao.getdown(model,cdto.getID());
		
		add(list);      
	}
}


