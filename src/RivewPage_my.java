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

public class RivewPage_my extends JPanel {
	JPanel background;
	JTextField searchText;
	JButton searchButton, InputButton;
	JComboBox searchCombo;
	RivewDTO MainRivew;
	RivewDAO dao = new RivewDAO();
	String ID = "ID";
	String user;
	String Menu_string[] = {"num","font","title","ID","name","rivew","like"};
	DefaultTableModel model = new DefaultTableModel(Menu_string, 0);
	JTable rivewTable = new JTable(model);
	JScrollPane  pane = new JScrollPane(rivewTable);
	RivewDAO RivewDAO = new RivewDAO();
	
    RivewPage_my(){
		setBounds(14, 0, 782, 407);
		setLayout(null);
        dao.getUserSearch(model, ID, "ID");
        if (model.getRowCount() > 0)
            rivewTable.setRowSelectionInterval(0, 0);
         
		background = new JPanel();
		background.setBounds(90, 56, 643, 316);
        background.add(pane);
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

		
		