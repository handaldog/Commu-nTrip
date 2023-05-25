package com.ssafy.board.model.dto;

public class BoardDto {
	int bno;
	int boardType;
	String writer;
	String subject;
	String content;
	String registerTime;
	String tradeName;
	int tradePrice;
	String tradeState;
	int totalPerson;
	int personnelCount;
	
	public BoardDto(int bno, int boardType, String writer, String subject, String content, String registerTime) {
		this.bno = bno;
		this.boardType = boardType;
		this.writer = writer;
		this.subject = subject;
		this.content = content;
		this.registerTime = registerTime;
	}

	public BoardDto() {}

	public int getTotalPerson() {
		return totalPerson;
	}

	public void setTotalPerson(int totalPerson) {
		this.totalPerson = totalPerson;
	}

	public int getPersonnelCount() {
		return personnelCount;
	}

	public void setPersonnelCount(int personnelCount) {
		this.personnelCount = personnelCount;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public int getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(int tradePrice) {
		this.tradePrice = tradePrice;
	}

	public int getBoardType() {
		return boardType;
	}

	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

