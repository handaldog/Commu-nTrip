package com.ssafy.jjim.model.dto;

public class JjimDto {

	private int jno;
	private String id;
	private int content_id;
	private String title;
	private String addr1;
	private String first_image;

	public JjimDto() {
		
	}

	public JjimDto(int jno, String id, int content_id) {
		super();
		this.jno = jno;
		this.id = id;
		this.content_id = content_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getFirst_image() {
		return first_image;
	}

	public void setFirst_image(String first_image) {
		this.first_image = first_image;
	}

	public int getJno() {
		return jno;
	}

	public void setJno(int jno) {
		this.jno = jno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	@Override
	public String toString() {
		return "JjimDto [jno=" + jno + ", id=" + id + ", content_id=" + content_id + ", title=" + title + ", addr1="
				+ addr1 + ", first_image=" + first_image + "]";
	}

}
