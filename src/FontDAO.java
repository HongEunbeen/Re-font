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
	private static PeopleDTO people;
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
		PreparedStatement ps = conn.prepareStatement(String.format("INSERT INTO %s VALUES(?,?,?,?,?,?,?,?)", TABLE_NAME));
		ps.setInt(1, fontDTO.getNum());
		ps.setString(2, fontDTO.getFont());
		ps.setString(3, fontDTO.getIntro());
		ps.setInt(4, fontDTO.getDate());
		ps.setString(5, fontDTO.getTag1());
		ps.setString(6, fontDTO.getTag2());
		ps.setString(7, fontDTO.getMood());
		ps.setInt(8, fontDTO.getLike());
		int res = ps.executeUpdate();
		ps.close();
		
		return res == 1 ? fontDTO : null;
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
	            Object data[] = { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8)};
	            t_model.addRow(data); //DefaultTableModel에 레코드 추가
	        }
	
	    } catch (SQLException e) {
	        System.out.println(e + "=> userSelectAll fail Font");
	    }
	}//userSelectAll()
	public void getUserSearch_1(DefaultTableModel dt, String word) {
	    String sql = "SELECT * FROM font WHERE tag1 LIKE '%" + word.trim() + "%'" + "or tag1 LIKE '%" + word.trim() + "%'";
	    Statement st = null; //명령
	    ResultSet rs = null;         //결과
	   
	    try{
	    	conn = DriverManager.getConnection(URL, USER, PW); 
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);
	
	        // DefaultTableModel에 있는 기존 데이터 지우기
	        for (int i = 0; i < dt.getRowCount();) {
	            dt.removeRow(0);
	        }
	
	        while (rs.next()) {
	            Object data[] = {  rs.getInt(1), rs.getString(2),
	                    rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8)};
	
	            dt.addRow(data);
	        }
	
	    } catch (SQLException e) {
	        System.out.println(e + "=> getUserSearch_1 fail Font");
	    }
	
	}//getUserSearch()
	public void getUserSearch_2(DefaultTableModel dt, String word1, String word2) {
	    String sql = "SELECT * FROM font WHERE (tag1 LIKE '%" + word1.trim() + "%'" + "and tag2 LIKE '%" + word2.trim() + "%')"
	    		+ "or (tag1 LIKE '%" + word2.trim() + "%'" + "and tag2 LIKE '%" + word1.trim() + "%')";
	    Statement st = null; //명령
	    ResultSet rs = null;         //결과
	    
	    try{
	    	conn = DriverManager.getConnection(URL, USER, PW); 
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);
	        if(rs == null) {
	        	return;
	        }
	        // DefaultTableModel에 있는 기존 데이터 지우기
	        for (int i = 0; i < dt.getRowCount();) {
	            dt.removeRow(0);
	        }
	
	        while (rs.next()) {
	            Object data[] = {  rs.getInt(1), rs.getString(2),
	                    rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8)};
	
	            dt.addRow(data);
	        }
	
	    } catch (SQLException e) {
	        System.out.println(e + "=> getUserSearch_2 fail Font");
	    }
	
	}//getUserSearch()
	public void getUserOrder(DefaultTableModel dt, String fieldName) {
	    String sql = "SELECT * FROM font order by " + fieldName.trim() + " desc";
	    Statement st = null; //명령
	    ResultSet rs = null;         //결과
	   
	    try{
	    	conn = DriverManager.getConnection(URL, USER, PW); 
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);
	
	        // DefaultTableModel에 있는 기존 데이터 지우기
	        for (int i = 0; i < dt.getRowCount();) {
	            dt.removeRow(0);
	        }
	
	        while (rs.next()) {
	            Object data[] = {  rs.getInt(1), rs.getString(2),
	                    rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8)};
	            dt.addRow(data);
	        }
	
	    } catch (SQLException e) {
	        System.out.println(e + "=> getUserOrder fail Font");
	    }
	
	}//getUserOrder()
	public FontDTO getUser(String fieldName) {
	    String sql = "SELECT * FROM font where font = " + "'"+fieldName.trim()+"'";
	    Statement st = null; //명령
	    ResultSet rs = null;         //결과
	    FontDTO dto = null;
	    try{
	    	conn = DriverManager.getConnection(URL, USER, PW); 
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);
	        while (rs.next()) {
	            dto = new FontDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8));
	        }
	
	    } catch (SQLException e) {
	        System.out.println(e + "=> getUser fail font");
	    }
	    return dto;
	}//getUser()
	public void getMood(DefaultTableModel dt, String fieldName) {
	    String sql = "SELECT * FROM font where mood = " + "'"+fieldName.trim()+"'";
	    Statement st = null; //명령
	    ResultSet rs = null;         //결과
	    FontDTO dto = null;
	    try{
	    	conn = DriverManager.getConnection(URL, USER, PW); 
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);
	        for (int i = 0; i < dt.getRowCount();) {
	            dt.removeRow(0);
	        }
	
	        while (rs.next()) {
	            Object data[] = {  rs.getInt(1), rs.getString(2),
	                    rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8)};
	            dt.addRow(data);
	        }
	
	    } catch (SQLException e) {
	        System.out.println(e + "=> getMood fail font");
	    }
	}//getUser()
	public void plusLike(String fieldName, int like) {
	    String sql = "update font set good = "+ like +" where font = "+ "'"+fieldName.trim()+"'";
	    System.out.println(sql);
	    Statement st = null; //명령
	    try{
	    	conn = DriverManager.getConnection(URL, USER, PW); 
	        st = conn.createStatement();
	        st.executeUpdate(sql);
	
	    } catch (SQLException e) {
	        System.out.println(e + "=> plusLike fail font");
	    }
	    
	}//plusLike()
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
	    	System.out.println(e + "=> Vector fail Font");
	    }
		return cbItems;
}
	public void getdown(DefaultTableModel dt, String fieldName) {
	    String sql = "SELECT * FROM font where font in (select distinct font from download where ID = '"+ fieldName +"')";
	    Statement st = null; //명령
	    ResultSet rs = null;         //결과
	    FontDTO dto = null;
	    try{
	    	conn = DriverManager.getConnection(URL, USER, PW); 
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);
	        for (int i = 0; i < dt.getRowCount();) {
	            dt.removeRow(0);
	        }
	        while (rs.next()) {
	            Object data[] = {  rs.getInt(1), rs.getString(2),
	                    rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8)};
	            dt.addRow(data);
	        }
	        
	    } catch (SQLException e) {
	        System.out.println(e + "=> getdown fail font");
	    }
	}//getdown()
}
