package com.ssafy.user.model.dto;

public class PreferDto {
	String type;
	String typename;
	String image;
	String title;
	String addr1;
	String latitude;
	String longitude;

	public PreferDto() {

	}

	public PreferDto(String type, String typename, String image, String title, String addr1, String latitude,
			String longitude) {

		this.type = type;
		this.typename = typename;
		this.image = image;
		this.title = title;
		this.addr1 = addr1;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
		return "PreferDto [type=" + type + ", typename=" + typename + ", image=" + image + ", title=" + title
				+ ", addr1=" + addr1 + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
