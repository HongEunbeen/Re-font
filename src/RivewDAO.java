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

public class RivewDAO {
	private static Connection conn = null;
	private static final String USER = "root";
	private static final String PW = "dmsqls124";
	private static final String URL = "jdbc:mysql://localhost:3306/re-font";
	private static final String TABLE_NAME = "rivew";
	RivewPage mList;
	// 싱글턴 패턴 적용하여 생성자 접근 불가
	public RivewDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}
	}
	public RivewDAO(RivewPage mList){
	       this.mList = mList;
	       System.out.println("DAO=>"+mList);
	}
	public static void init(Connection c) {
		conn = c;
	}
	public RivewDTO getMemberDTO(String id){
	       
		RivewDTO dto = new RivewDTO();
       
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
       
        try {
        	conn = DriverManager.getConnection(URL, USER, PW);
            String sql = "select * from rivew where ID=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
           
            rs = ps.executeQuery();
           
            if(rs.next()){
            	dto.setNum(rs.getInt("num"));
                dto.setFont(rs.getString("font"));
                dto.setTitle(rs.getString("title"));
                dto.setID(rs.getString("ID"));
                dto.setName(rs.getString("name"));
                dto.setRivew(rs.getString("rivew"));
                dto.setLike(rs.getInt("like"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }      
       
        return dto;    
    }
	public static RivewDTO RivewDTO_add(RivewDTO rivewDTO) throws SQLException {
		conn = DriverManager.getConnection(URL, USER, PW);
		PreparedStatement ps = conn.prepareStatement(String.format("INSERT INTO %s VALUES(?, ?, ?, ?, ?, ?, ?)", TABLE_NAME));
		ps.setInt(1, rivewDTO.getNum());
		ps.setString(2, rivewDTO.getFont());
		ps.setString(3, rivewDTO.getTitle());
		ps.setString(4, rivewDTO.getID());
		ps.setString(5, rivewDTO.getName());
		ps.setString(6, rivewDTO.getRivew());
		ps.setInt(7, rivewDTO.getLike());
		int res = ps.executeUpdate();
		ps.close();
		
		return res == 1 ? rivewDTO : null;
}
	
    public void userSelectAll(DefaultTableModel t_model) {
    	 Statement st = null; //명령
         ResultSet rs = null;  
        try {
        	conn = DriverManager.getConnection(URL, USER, PW);
            st = conn.createStatement();
            rs = st.executeQuery("select * from rivew order by ID");
 
            // DefaultTableModel에 있는 기존 데이터 지우기
            for (int i = 0; i < t_model.getRowCount();) {
                t_model.removeRow(0);
            }
 
            while (rs.next()) {
                Object data[] = { rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7) };
 
                t_model.addRow(data); //DefaultTableModel에 레코드 추가
            }
 
        } catch (SQLException e) {
            System.out.println(e + "=> userSelectAll fail");
        }
    }//userSelectAll()

    
    public void getUserSearch(DefaultTableModel dt, String fieldName,
            String word) {
        String sql = "SELECT * FROM rivew WHERE " + fieldName.trim()
                + " LIKE '%" + word.trim() + "%'";
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
                        rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7) };
 
                dt.addRow(data);
            }
 
        } catch (SQLException e) {
            System.out.println(e + "=> getUserSearch fail");
        }
 
    }//getUserSearch()
}
	
