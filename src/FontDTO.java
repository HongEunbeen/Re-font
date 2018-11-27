
public class FontDTO {
	public int num, date, like;
	public String font, intro, tag1, tag2, mood;
	public FontDTO( int num, String font, String intro, int date, String tag1, String tag2, String mood, int like) {
		super();
		this.num = num;
		this.date = date;
		this.font = font;
		this.intro = intro;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.mood = mood;
		this.like = like;
	}

	public FontDTO() {
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
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

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}
	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like =like;
	}

}