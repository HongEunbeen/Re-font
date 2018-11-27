import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class PeopleDAO {
	private static Connection conn = null;
	private static PeopleDTO people;
	private static final String TABLE_NAME = "people";
	private static final String USER = "root";
	private static final String PW = "dmsqls124";
	private static final String URL = "jdbc:mysql://localhost:3306/re-font";

	// 싱글턴 패턴 적용하여 생성자 접근 불가
	public PeopleDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}
	}
	public static void init(Connection c) {
		conn = c;
	}
	
	public static boolean getIdByCheck(String id) {
        boolean result = true;
 
        try {
        	conn = DriverManager.getConnection(URL, USER, PW);
        	PreparedStatement ps = conn.prepareStatement(String.format("SELECT * FROM %s WHERE id=?", TABLE_NAME));
            ps.setString(1, id.trim());
            ResultSet rs = ps.executeQuery(); //실행
            if (rs.next())
                result = false; //레코드가 존재하면 false
        } catch (SQLException e) {
            System.out.println(e + "=>  getIdByCheck fail");
        }
        return result;
 
    }//getIdByCheck()

	public static PeopleDTO People_add(PeopleDTO people) throws SQLException {
			conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement ps = conn.prepareStatement(String.format("INSERT INTO %s VALUES(?, ?, ?, ?, ?, ?, ?)", TABLE_NAME));
			ps.setString(1, people.getName());
			ps.setInt(2, people.getGender());
			ps.setInt(3, people.getAge());
			ps.setString(4, people.getPhone());
			ps.setString(5, people.getID());
			ps.setString(6, people.getPWD());
			ps.setString(7, people.getIntro());
			int res = ps.executeUpdate();
			ps.close();
			
			return res == 1 ? people : null;
	}
	public static PeopleDTO People_get(String ID) throws SQLException {
		conn = DriverManager.getConnection(URL, USER, PW);
		PeopleDTO user = null;
		PreparedStatement ps = conn.prepareStatement(String.format("SELECT * FROM %s WHERE ID = ?", TABLE_NAME));
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			user = new PeopleDTO( rs.getString("name"), rs.getInt("gender"),rs.getInt("age"), rs.getString("phone"), rs.getString("ID"), rs.getString("PWD"), rs.getString("intro"));
		}
		ps.close();
		rs.close();
		return user;
	}
	public static PeopleDTO People_nodify(PeopleDTO people) throws SQLException{
		conn = DriverManager.getConnection(URL, USER, PW);
        try {
        	PreparedStatement ps = conn.prepareStatement(String.format("UPDATE people SET name=?, gender=? , age=? , phone=? , PWD=? , intro=? WHERE ID=?", TABLE_NAME));
        	ps.setString(1, people.getName());
        	ps.setInt(2, people.getGender());
        	ps.setInt(3, people.getAge());
        	ps.setString(4, people.getPhone());
        	ps.setString(5, people.getPWD());
        	ps.setString(6, people.getIntro());
        	ps.setString(7, people.getID());
        	int res = ps.executeUpdate();
			ps.close();
			
			return res == 1 ? people : null;
        	
        }catch(SQLException e) {
        	e.printStackTrace();
        }
		
		return null;
	}
	
}
