package com.web.login.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "Member")
public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer memberId;
	String account;
	String password;
	@Transient
	String confirmPassword;
	String username;
	String gender;
	String email;
	String phone;
	String birthday;
	Integer status;
	Blob facepic;
	String filename;
	String location;
	@Column(name = "registerTime", insertable = false, updatable = false)
	Timestamp registerTime;
	@Transient
	MultipartFile memberImage;

	public MemberBean() {
		super();
	}
	
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Blob getFacepic() {
		return facepic;
	}

	public void setFacepic(Blob facepic) {
		this.facepic = facepic;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public MultipartFile getMemberImage() {
		return memberImage;
	}

	public void setMemberImage(MultipartFile memberImage) {
		this.memberImage = memberImage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberBean [memberId=");
		builder.append(memberId);
		builder.append(", account=");
		builder.append(account);
		builder.append(", password=");
		builder.append(password);
		builder.append(", confirmPassword=");
		builder.append(confirmPassword);
		builder.append(", username=");
		builder.append(username);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", status=");
		builder.append(status);
		builder.append(", facepic=");
		builder.append(facepic);
		builder.append(", filename=");
		builder.append(filename);
		builder.append(", location=");
		builder.append(location);
		builder.append(", registerTime=");
		builder.append(registerTime);
		builder.append(", memberImage=");
		builder.append(memberImage);
		builder.append("]");
		return builder.toString();
	}

	public MemberBean(Integer memberId, String account, String password, String confirmPassword, String username,
			String gender, String email, String phone, String birthday, Integer status, Blob facepic, String filename,
			String location, Timestamp registerTime, MultipartFile memberImage) {
		super();
		this.memberId = memberId;
		this.account = account;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.username = username;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.birthday = birthday;
		this.status = status;
		this.facepic = facepic;
		this.filename = filename;
		this.location = location;
		this.registerTime = registerTime;
		this.memberImage = memberImage;
	}

}