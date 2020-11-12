package com.passwdmin.vo;
import java.util.Date;
//管理员表
public class User {
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", last_logintime=" + last_logintime + ", last_ipaddr=" + last_ipaddr + ", create_time=" + create_time
				+ ", aes_key=" + aes_key + ", level=" + level + "]";
	}
	private int uid;//管理员id
	private String username;
	private String email;
	private String password;
	private java.util.Date last_logintime;//上次登录时间
	private String last_ipaddr;//上次登录的IP地址
	private java.util.Date create_time;
	private String aes_key;
	private int level;
	
	public String getAse_key() {
		return aes_key;
	}
	public void setAse_key(String ase_key) {
		this.aes_key = ase_key;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public java.util.Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(java.util.Date create_time) {
		this.create_time = create_time;
	}
	//以下为对应的Getter和Setter
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastTime() {
		return last_logintime;
	}
	public void setLastTime(Date string) {
		this.last_logintime = string;
	}
	public String getLast_ipaddr() {
		return last_ipaddr;
	}
	public void setLast_ipaddr(String last_ipaddr) {
		this.last_ipaddr = last_ipaddr;
	}
	
}
