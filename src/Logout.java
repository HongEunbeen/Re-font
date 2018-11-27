import java.sql.Connection;
import java.sql.SQLException;

public class Logout{
	
	connDAO dao = new connDAO();
	connDTO dto = new connDTO();
	Logout(){	
		dto = dao.getConn();
		dao.update(dto.getID());
		dao.delete();
		System.out.println("안녕");
	}
}
