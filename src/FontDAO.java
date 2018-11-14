import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class FontDAO {
	private static Connection conn = null;
	private static People people;
	private static final String TABLE_NAME = "font";
	private static final String USER = "root";
	private static final String PW = "dmsqls124";
	private static final String URL = "jdbc:mysql://localhost:3306/re-font";

	// 싱글턴 패턴 적용하여 생성자 접근 불가
	public FontDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}
	}
	public static void init(Connection c) {
		conn = c;
	}
	
	public static FontDTO Font_add(FontDTO fontDTO) throws SQLException {
		conn = DriverManager.getConnection(URL, USER, PW);
		PreparedStatement ps = conn.prepareStatement(String.format("INSERT INTO %s VALUES(?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME));
		ps.setInt(1, fontDTO.getNum());
		ps.setString(2, fontDTO.getFont());
		ps.setString(3, fontDTO.getIntro());
		ps.setString(4, fontDTO.getOwner());
		ps.setString(5, fontDTO.getView());
		ps.setString(6, fontDTO.getTag1());
		ps.setString(7, fontDTO.getTag2());
		ps.setString(8, fontDTO.getTag3());
		int res = ps.executeUpdate();
		ps.close();
		
		return res == 1 ? fontDTO : null;
}
	public static Vector ComboItem() {
		Vector cbItems = new Vector();
		Statement st = null; //명령
	    ResultSet rs = null;
	    try {
	    	conn = DriverManager.getConnection(URL, USER, PW);
			st = conn.createStatement();
	        rs = st.executeQuery("select * from font order by num");
	        while(rs.next()) {
	        	cbItems.add(rs.getString("font"));
	        }

	    }catch(SQLException e) {
	    	System.out.println(e + "=> userSelectAll fail");
	    }
		return cbItems;
}
	
	public void userSelectAll(DefaultTableModel t_model) {
	 Statement st = null; //명령
     ResultSet rs = null;  
    try {
    	conn = DriverManager.getConnection(URL, USER, PW);
        st = conn.createStatement();
        rs = st.executeQuery("select * from font order by num");

        // DefaultTableModel에 있는 기존 데이터 지우기
        for (int i = 0; i < t_model.getRowCount();) {
            t_model.removeRow(0);
        }

        while (rs.next()) {
            Object data[] = { rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8) };

            t_model.addRow(data); //DefaultTableModel에 레코드 추가
        }

    } catch (SQLException e) {
        System.out.println(e + "=> userSelectAll fail");
    }
}//userSelectAll()
}
