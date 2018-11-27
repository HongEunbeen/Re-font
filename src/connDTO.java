
public class connDTO {
	public int conn;
	public String ID;
	
	public connDTO() {
	}
	public connDTO(String ID, int conn) {
		this.conn = conn;
		this.ID = ID;
	}
	public int getConn() {
		return conn;
	}
	public void setConn(int conn) {
		this.conn = conn;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}

	
}
