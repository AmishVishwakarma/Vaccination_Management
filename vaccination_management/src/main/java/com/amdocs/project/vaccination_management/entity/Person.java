package com.amdocs.project.vaccination_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.sql.Date;
@Entity
@Table(name="vac_details")
@SequenceGenerator(name="MyS",sequenceName="vacc_sequence",initialValue=50,allocationSize=1)

public class Person {
	@Id
	@Column(name="C_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="MyS")
	private int id;
	@Column(name="FIRST_NAME")
	private String first_name;
	@Column(name="LAST_NAME")
	private String last_name;
	@Column(name="AGE")
	private int age;
	@Column(name="AADHAR_NUM")
	private long aadhar_num;
	@Column(name="PHONE_NUM")
	private long phone_num;
	@Column(name="DOSE1")
	private String dose1 ;
	@Column(name="DOSE2")
	private String dose2 ;
	@Column(name="BOOSTER_DOSE")
	private String booster_dose ;
	@Column(name="DATE1")
	private Date date1 ;
	@Column(name="DATE2")
	private Date date2 ;	
	@Column(name="DATE3")
	private Date date3 ;
	@Column(name="STATUS")
	private String status;
	public void updateStatus() {
        if (dose1 != null && dose2 != null) {
            this.status = "fully";
        } else {
            this.status = "partially" ;
        }
	}
	
	public Person() {}

	public Person(int id, String first_name, String last_name,int age, long aadhar_num,long phone_num, String dose1,
			String dose2, String booster_dose,Date date1,Date date2,Date date3, String status) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age=age;
		this.aadhar_num = aadhar_num;
		this.phone_num = phone_num;
		this.dose1 = dose1;
		this.dose2 = dose2;
		this.booster_dose = booster_dose;
		this.date1 = date1;
		this.date2 = date2;
		this.date3 = date3;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age=age;
	}

	public long getAadhar_num() {
		return aadhar_num;
	}

	public void setAadhar_num(long aadhar_num) {
		this.aadhar_num = aadhar_num;
	}

	public long getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(long phone_num) {
		this.phone_num = phone_num;
	}

	public String getDose1() {
		return dose1;
	}

	public void setDose1(String dose1) {
		this.dose1 = dose1;
	}

	public String getDose2() {
		return dose2;
	}

	public void setDose2(String dose2) {
		this.dose2 = dose2;
	}

	public String getBooster_dose() {
		return booster_dose;
	}

	public void setBooster_dose(String booster_dose) {
		this.booster_dose = booster_dose;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Date getDate3() {
		return date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}