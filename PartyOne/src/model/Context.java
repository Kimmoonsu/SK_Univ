package model;

import java.util.Date;

public class Context {
	private int context_id;
	private String context_writer;
	private Date date;
	private String context_title;
	private String context_sub;
	private String location;
	private String img;
	private Double latitude;
	private Double longitude;
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public int getContext_id() {
		return context_id;
	}
	public void setContext_id(int context_id) {
		this.context_id = context_id;
	}
	public String getContext_writer() {
		return context_writer;
	}
	public void setContext_writer(String context_writer) {
		this.context_writer = context_writer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContext_title() {
		return context_title;
	}
	public void setContext_title(String context_title) {
		this.context_title = context_title;
	}
	public String getContext_sub() {
		return context_sub;
	}
	public void setContext_sub(String context_sub) {
		this.context_sub = context_sub;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
