
public class PeopleDTO {
	public int  gender, age;
	public String name, ID ,PWD, intro,  phone;
	
	public PeopleDTO() {
		// TODO Auto-generated constructor stub
	}
	public PeopleDTO(String name, int gender, int age, String phone, String ID, String PWD, String intro) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.ID = ID;
		this.PWD = PWD;
		this.intro = intro;

	}
	
	// 필요한 메서드 포함 가능 (완전히 일반적인 객체처럼 사용 가능이 장점)

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getPWD() {
		return PWD;
	}
	
	public void setPWD(String PWD) {
		this.PWD = PWD;
	}
	
	public String getIntro() {
		return intro;
	}
	
	public void setIntor(String intro) {
		this.intro = intro;
	}
	
}
