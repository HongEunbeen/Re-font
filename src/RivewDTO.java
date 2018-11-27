
public class RivewDTO {
	String font;
	public int num;
	public String name, rivew, ID, title;
	public RivewDTO() {
		
	}
	public RivewDTO(int num, String font, String title, String ID, String name, String rivew) {
		this.num = num;
		this.font = font;
		this.title = title;
		this.ID = ID;
		this.name = name;
		this.rivew = rivew;
	}
	
	// 필요한 메서드 포함 가능 (완전히 일반적인 객체처럼 사용 가능이 장점)
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRivew() {
		return rivew;
	}

	public void setRivew(String rivew) {
		this.rivew = rivew;
	}
	
	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
