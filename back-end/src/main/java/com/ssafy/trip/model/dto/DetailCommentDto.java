package com.ssafy.trip.model.dto;

public class DetailCommentDto {
	int dno;
	int content_id;
	String id;
	String content;
	String registerTime;
	
	public DetailCommentDto() {}

	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "DetailCommentDto [dno=" + dno + ", content_id=" + content_id + ", id=" + id + ", content=" + content
				+ ", registerTime=" + registerTime + "]";
	}
	
	
}
