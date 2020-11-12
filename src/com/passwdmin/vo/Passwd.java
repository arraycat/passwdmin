package com.passwdmin.vo;

public class Passwd {
	
	@Override
	public String toString() {
		return "Passwd [pid=" + pid + ", account_name=" + account_name + ", account_passwd=" + account_passwd
				+ ", account_type=" + account_type + ", account_desc=" + account_desc + ", update_time=" + update_time
				+ "]";
	}
	private int pid;
	private String account_name;
	private String account_passwd;
	private String account_type;
	private String account_desc;
	private java.util.Date update_time;
	
	
	
	public int getPid() {
		return pid;
	}
	public void setPid(int id) {
		this.pid = id;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_passwd() {
		return account_passwd;
	}
	public void setAccount_passwd(String account_passwd) {
		this.account_passwd = account_passwd;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getAccount_desc() {
		return account_desc;
	}
	public void setAccount_desc(String account_desc) {
		this.account_desc = account_desc;
	}
	public java.util.Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(java.util.Date update_time) {
		this.update_time = update_time;
	}
	
	
}
