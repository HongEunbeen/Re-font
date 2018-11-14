
public class FontDTO {
	public int num;
	public String font, intro, owner, view, tag1, tag2, tag3;
	
	public FontDTO() {
		
	}
	public FontDTO(int num, String font, String intro, String owner, String view, String tag1, String tag2, String tag3) {
		this.num = num;
		this.font = font;
		this.intro = intro;
		this.owner = owner;
		this.view = view;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.tag3 = tag3;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getFont() {
		return font;
	}
	public void setFont(String font) {
		this.font = font;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getTag1() {
		return tag1;
	}
	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}
	public String getTag2() {
		return tag2;
	}
	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}
	public String getTag3() {
		return tag3;
	}
	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}
	
}
