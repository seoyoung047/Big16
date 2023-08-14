package vo;

public class Member {
	private String name;
	private String phonenumber;
	private String address;
	private String group;
	
	public Member() {//디폴트 생성자, 초기화x => setter 필요 <=연락처 수정
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "회원정보 : 이름 = " + name 
				+ ", 전화번호 = " + phonenumber
				+ ", 주소 = " + address 
				+ ", 그룹 = " + group;
	}
	
}
