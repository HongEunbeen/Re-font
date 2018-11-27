
public class DownloadDTO {
	private String ID;
	private String font;
	
	public DownloadDTO() {
	
	}
	public DownloadDTO(String iD, String font) {
		super();
		ID = iD;
		this.font = font;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFont() {
		return font;
	}
	public void setFont(String font) {
		this.font = font;
	}
	
	
	
}
