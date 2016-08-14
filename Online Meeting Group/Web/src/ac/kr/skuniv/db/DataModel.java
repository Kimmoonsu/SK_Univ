package ac.kr.skuniv.db;

import java.util.ArrayList;

public class DataModel {
	private String id;
	private String passwd;
	private String name;
	private String department;
	private String position;
	private String email;
	private String URL;
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	//private Timestamp reg_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	// 모든 정보
	public ArrayList<String> array_name = new ArrayList<>();
	public ArrayList<String> array_dept = new ArrayList<>();
	public ArrayList<String> array_position = new ArrayList<>();
	public ArrayList<String> array_email = new ArrayList<>();
	public ArrayList<String> array_id = new ArrayList<>();
	public ArrayList<String> array_url = new ArrayList<>();
	public ArrayList<String> getArray_url() {
		return array_url;
	}
	public ArrayList<String> getArray_id() {
		return array_id;
	}
	public ArrayList<String> getArray_name() {
		return array_name;
	}
	public ArrayList<String> getArray_dept() {
		return array_dept;
	}
	public ArrayList<String> getArray_position() {
		return array_position;
	}
	public ArrayList<String> getArray_email() {
		return array_email;
	}
	
	
	// 회의 정보
	public ArrayList<String> array_title = new ArrayList<>();
	public ArrayList<String> array_owner = new ArrayList<>();
	public ArrayList<String> array_guest = new ArrayList<>();
	public ArrayList<String> array_guest2 = new ArrayList<>();
	public ArrayList<String> array_guest3 = new ArrayList<>();
	public ArrayList<String> array_guest4 = new ArrayList<>();
	public ArrayList<String> array_guest5 = new ArrayList<>();
	public ArrayList<String> array_date = new ArrayList<>();
	public ArrayList<String> array_encode = new ArrayList<>();
	public ArrayList<Integer> array_state = new ArrayList<>();
	public ArrayList<Integer> getArray_state() {
		return array_state;
	}
	public ArrayList<String> getArray_encode() {
		return array_encode;
	}
	public ArrayList<String> getArray_date() {
		return array_date;
	}
	public ArrayList<String> getArray_title() {
		return array_title;
	}
	public ArrayList<String> getArray_owner() {
		return array_owner;
	}
	public ArrayList<String> getArray_guest() {
		return array_guest;
	}
	public ArrayList<String> getArray_guest2() {
		return array_guest2;
	}
	public ArrayList<String> getArray_guest3() {
		return array_guest3;
	}
	public ArrayList<String> getArray_guest4() {
		return array_guest4;
	}
	public ArrayList<String> getArray_guest5() {
		return array_guest5;
	}

	// chatting data
	public ArrayList<String> chat_name = new ArrayList<>();
	public ArrayList<String> chat_msg = new ArrayList<>();
	public ArrayList<String> chat_time = new ArrayList<>();
	public ArrayList<String> chat_url = new ArrayList<>();
	public ArrayList<String> getChat_url() {
		return chat_url;
	}
	public ArrayList<String> getChat_time() {
		return chat_time;
	}
	public ArrayList<String> getChat_name() {
		return chat_name;
	}
	public ArrayList<String> getChat_msg() {
		return chat_msg;
	}
	
	
}
