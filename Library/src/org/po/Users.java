package org.po;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Users {

    private String u_name;
    private Integer u_hasb;
    private String u_sex;
    private String u_email;
    private Date u_createDay;
    private Integer u_age;
    private String u_major;
    private String u_tel;
    private Integer u_canb;
    private String u_id;

	public String getU_name(){
		return u_name;
	}

	public Integer getU_hasb(){
		return u_hasb;
	}

	public String getU_sex(){
		return u_sex;
	}

	public String getU_email(){
		return u_email;
	}

	public Date getU_createDay(){
		return u_createDay;
	}

	public Integer getU_age(){
		return u_age;
	}

	public String getU_major(){
		return u_major;
	}

	public String getU_tel(){
		return u_tel;
	}

	public Integer getU_canb(){
		return u_canb;
	}

	public String getU_id(){
		return u_id;
	}

	public void setU_name(String u_name){
		this.u_name=u_name;
	}

	public void setU_hasb(Integer u_hasb){
		this.u_hasb=u_hasb;
	}

	public void setU_sex(String u_sex){
		this.u_sex=u_sex;
	}

	public void setU_email(String u_email){
		this.u_email=u_email;
	}

	public void setU_createDay(Date u_createDay){
		this.u_createDay=u_createDay;
	}

	public void setU_age(Integer u_age){
		this.u_age=u_age;
	}

	public void setU_major(String u_major){
		this.u_major=u_major;
	}

	public void setU_tel(String u_tel){
		this.u_tel=u_tel;
	}

	public void setU_canb(Integer u_canb){
		this.u_canb=u_canb;
	}

	public void setU_id(String u_id){
		this.u_id=u_id;
	}

}