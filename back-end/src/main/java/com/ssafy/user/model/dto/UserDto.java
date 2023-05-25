package com.ssafy.user.model.dto;

public class UserDto {
	String id;
	String pw;
	String name;
	int age;
	String local;
	String type;

	public UserDto(String id, String pw, String name, int age, String local) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.local = local;
	}

	public UserDto(String pw, String name, int age, String local) {
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.local = local;
	}

	public UserDto() {
	}
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", pw=" + pw + ", name=" + name + ", age=" + age + ", local=" + local + "]";
	}

}
