
import java.awt.Color;
import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.util.List;
import java.util.Vector;

public class Rivew_my_dialog extends JDialog {
	JPanel background;
	JTextField searchText;
	JButton searchButton, InputButton;
	JComboBox searchCombo;
	RivewDTO MainRivew;
	RivewDAO dao = new RivewDAO();
	RivewDTO dto = new RivewDTO();
	connDTO cdto = new connDTO();
	connDAO cdao = new connDAO();
	PeopleDTO pdto = new PeopleDTO();
	PeopleDAO pdao = new PeopleDAO();
	
	
	String user;
	String Menu_string[] = {"num","font","title","ID","name","rivew","like"};
	DefaultTableModel model = new DefaultTableModel(Menu_string, 0){  //셀 수정 못하게 하는 부분
		 public boolean isCellEditable(int row, int column){
			    return false;
			 }
	};
	JTable rivewTable = new JTable(model);
	JScrollPane  pane = new JScrollPane(rivewTable);
	RivewDAO RivewDAO = new RivewDAO();
	
    Rivew_my_dialog(String location){
		setBounds(14, 0, 782, 407);
		setLayout(null);
		setVisible(true);
		rivewTable.getTableHeader().setReorderingAllowed(false); // 이동 불가 
		rivewTable.getTableHeader().setResizingAllowed(false); // 크기 조절 불가 
		
		try {
			cdto = cdao.getConn();
			pdto = pdao.People_get(cdto.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        dao.userSelectfont(location,model);
        if (model.getRowCount() > 0)
            rivewTable.setRowSelectionInterval(0, 0);
         
		background = new JPanel();
		background.setBounds(90, 56, 643, 316);
        background.add(pane);
        add(background);
        
        rivewTable.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2) {
                	int row = rivewTable.getSelectedRow();
                	Object a = rivewTable.getValueAt(row, 0);
                	int ac = (int)a;
                	dto = dao.getUserSearch_num(ac);
                	RivewShow rivew = new RivewShow(dto);
                }
		}});
        
	}

}

		
		