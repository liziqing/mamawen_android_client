/**  
 * @Title: Doctor.java 
 * @Package cn.net_show.customer.model 
 * @author 王帅
 * @date 2015年3月14日 下午8:23:38  
 */
package cn.net_show.customer.model;

import java.io.Serializable;

/**
 * @ClassName: Doctor
 * @author 王帅
 * @date 2015年3月14日 下午8:23:38
 */
public class Doctor implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * "name": "xxx", "title": "xxx", //医生的职称 "department": "xxx", "cellPhone":
	 * "xxx", "hospital": "xxxx", "achievement": "xxx", "level": 1
	 * //这个医生在这个app中的级别
	 */
	public String name;
	public String title;
	public int doctorID;
	public String department;
	public String cellPhone;
	public String hospital;
	public String achievement;// 成就
	public int level;// 这个医生在这个app中的级别

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
