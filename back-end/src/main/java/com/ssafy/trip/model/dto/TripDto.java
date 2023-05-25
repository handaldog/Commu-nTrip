package com.ssafy.trip.model.dto;

public class TripDto {

	private int content_id;
	private int content_type_id;
	private String title;
	private int sido_code;
	private String first_image;

	private String addr1;
	private String latitude;
	private String longitude;
	private int jno;

	public TripDto() {
	}

	public TripDto(int sido_code, int content_type_id, String title) {
		this.sido_code = sido_code;
		this.content_type_id = content_type_id;
		this.title = title;

	}

	public int getJno() {
		return jno;
	}

	public void setJno(int jno) {
		this.jno = jno;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public int getContent_type_id() {
		return content_type_id;
	}

	public void setContent_type_id(int content_type_id) {
		this.content_type_id = content_type_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSido_code() {
		return sido_code;
	}

	public void setSido_code(int sido_code) {
		this.sido_code = sido_code;
	}

	public String getFirst_image() {
		return first_image;
	}

	public void setFirst_image(String first_image) {
		this.first_image = first_image;
	}

	
	

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "TripDto [content_id=" + content_id + ", content_type_id=" + content_type_id + ", title=" + title
				+ ", sido_code=" + sido_code + ", first_image=" + first_image + ", addr1=" + addr1 + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}

}
