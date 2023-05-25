package com.ssafy.board.model.dto;

public class CommentDto {
	int cno;
	int bno;
	String id;
	String content;
	String regist_time;
	
	public CommentDto() {}
	
	public CommentDto(int cno, int bno, String id, String content, String regist_time) {
		this.cno = cno;
		this.bno = bno;
		this.id = id;
		this.content = content;
		this.regist_time = regist_time;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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

	public String getRegist_time() {
		return regist_time;
	}

	public void setRegist_time(String regist_time) {
		this.regist_time = regist_time;
	}

	@Override
	public String toString() {
		return "CommentDto [cno=" + cno + ", bno=" + bno + ", id=" + id + ", content=" + content + ", regist_time="
				+ regist_time + "]";
	}
	
}
