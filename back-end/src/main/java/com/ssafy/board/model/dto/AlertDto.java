package com.ssafy.board.model.dto;

public class AlertDto {
	int tno;
	int bno;
	String writer;
	String id;
	String content;
	String registerTime;
	int type;
	
	public AlertDto() {
	}
	
	@Override
	public String toString() {
		return "AlertDto [tno=" + tno + ", bno=" + bno + ", id=" + id + ", content=" + content + ", registerTime="
				+ registerTime + "]";
	}
	

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
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
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	
}
