import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DownloadDAO {
	private static Connection conn = null;
	private static final String USER = "root";
	private static final String PW = "dmsqls124";
	private static final String URL = "jdbc:mysql://localhost:3306/re-font";
	private static final String TABLE_NAME = "download";
	
	// 싱글턴 패턴 적용하여 생성자 접근 불가
	public DownloadDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}
	}
	public static void init(Connection c) {
		conn = c;
	}
	public static DownloadDTO DownloadDTO_add(DownloadDTO DownloadDTO) throws SQLException {
		conn = DriverManager.getConnection(URL, USER, PW);
		PreparedStatement ps = conn.prepareStatement(String.format("INSERT INTO %s VALUES(?, ?)", TABLE_NAME));
		ps.setString(1, DownloadDTO.getID());
		ps.setString(2, DownloadDTO.getFont());
		int res = ps.executeUpdate();
		ps.close();
		return res == 1 ? DownloadDTO : null;
}  
//    public DownloadDTO getConn() {
//        String sql = "SELECT * FROM conn WHERE conn = 1";
//        Statement st = null; //명령
//        ResultSet rs = null; //결과
//        connDTO dto = null;
//        try{
//        	conn = DriverManager.getConnection(URL, USER, PW); 
//            st = conn.createStatement();
//            rs = st.executeQuery(sql);
//            if(rs.next()) {
//            	dto = new connDTO( rs.getString("ID"), rs.getInt("conn"));
//    		}else dto = null;
//            
//        } catch (SQLException e) {
//            System.out.println(e + "=> getConn fail");
//        }
//        return dto;
// 
//    }//getUserSearch()
}
