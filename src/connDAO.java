import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connDAO {
	private static Connection conn = null;
	private static final String USER = "root";
	private static final String PW = "dmsqls124";
	private static final String URL = "jdbc:mysql://localhost:3306/re-font";
	private static final String TABLE_NAME = "conn";
	// 싱글턴 패턴 적용하여 생성자 접근 불가
	public connDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}
	}
	public static void init(Connection c) {
		conn = c;
	}

	public static void connDTO_add(String fieldDate) throws SQLException {
		conn = DriverManager.getConnection(URL, USER, PW);
		PreparedStatement ps = conn.prepareStatement(String.format("INSERT INTO %s VALUES(?, ?)", TABLE_NAME));
		ps.setString(1, fieldDate);
		ps.setInt(2, 1);
		int res = ps.executeUpdate();
		ps.close();
}  
    public connDTO getConn() {
        String sql = "SELECT * FROM conn WHERE conn = 1";
        Statement st = null; //명령
        ResultSet rs = null; //결과
        connDTO dto = null;
        try{
        	conn = DriverManager.getConnection(URL, USER, PW); 
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()) {
            	dto = new connDTO( rs.getString("ID"), rs.getInt("conn"));
    		}else dto = null;
            
        } catch (SQLException e) {
            System.out.println(e + "=> getConn fail");
        }
        return dto;
 
    }//getConn()
    public void update(String fielddate) {
        String sql = "update conn set conn = 0 WHERE ID = '"+fielddate+"'";
        Statement st = null; //명령
        try{
        	System.out.println("update");
        	conn = DriverManager.getConnection(URL, USER, PW); 
            st = conn.createStatement();
            st.executeUpdate(sql);
   
        } catch (SQLException e) {
            System.out.println(e + "=> update fail conn");
        }
    }//update()
    public void delete() {
        String sql = "delete from conn where conn = 0";
        Statement st = null; //명령
        try{
        	System.out.println("delete");
        	conn = DriverManager.getConnection(URL, USER, PW); 
            st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e + "=> delete fail conn");
        }
 
    }//delete()
}
	
