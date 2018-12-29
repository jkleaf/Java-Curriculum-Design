package org.po;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Borrow_table {

    private String u_id;
    private String b_id;
    private Date borrow_date;
    private Date return_date;

	public String getU_id(){
		return u_id;
	}

	public String getB_id(){
		return b_id;
	}

	public Date getBorrow_date(){
		return borrow_date;
	}

	public Date getReturn_date(){
		return return_date;
	}

	public void setU_id(String u_id){
		this.u_id=u_id;
	}

	public void setB_id(String b_id){
		this.b_id=b_id;
	}

	public void setBorrow_date(Date borrow_date){
		this.borrow_date=borrow_date;
	}

	public void setReturn_date(Date return_date){
		this.return_date=return_date;
	}

}