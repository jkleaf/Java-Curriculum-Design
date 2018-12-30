package org.join;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;

public class Borrow_Users_Book {
	private String b_name;
    private String b_id_from_b;
    private Date p_date;
    private Integer b_type;
    private String pub;
    private String filepath;
    
    
    private String u_name;
    private Integer u_hasb;
    private String u_sex;
    private String u_email;
    private Date u_createDay;
    private Integer u_age;
    private String u_major;
    private String u_tel;
    private Integer u_canb;
    private String u_id_from_u;
    
    
    private String u_id_from_bt;
    private String b_id_from_bt;
    private Date borrow_date;
    private Date return_date;
    private String type_name;
    private Integer type;

	public String getType_name(){
		return type_name;
	}

	public Integer getType(){
		return type;
	}

	public void setType_name(String type_name){
		this.type_name=type_name;
	}

	public void setType(int  type){
		this.type=type;
	}
    public String getB_name(){
		return b_name;
	}

	public String getB_id_from_b(){
		return b_id_from_b;
	}
	public String getB_id_from_bt() {
		return b_id_from_bt;
	}

	public Date getP_date(){
		return p_date;
	}

	public Integer getB_type(){
		return b_type;
	}

	public String getPub(){
		return pub;
	}
	public String getContent() {
		 StringBuilder result = new StringBuilder();
	        try {
	        	if(filepath==null) {
	        		String a=null;
	        		return a;
	        	}
	            BufferedReader bfr = new BufferedReader(new FileReader(new File(filepath)));
	            String lineTxt = null;
	            while ((lineTxt = bfr.readLine()) != null) {
	                result.append(lineTxt).append("\n");
	            }
	            bfr.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result.toString();
	    }

	

	public void setB_name(String b_name){
		this.b_name=b_name;
	}

	public void setB_id_from_b(String b_id){
		this.b_id_from_b=b_id;
	}
	public void setB_id_from_bt(String b_id) {
		this.b_id_from_bt=b_id;
	}
	public void setP_date(Date p_date){
		this.p_date=p_date;
	}

	public void setB_type(Integer b_type){
		this.b_type=b_type;
	}

	public void setPub(String pub){
		this.pub=pub;
	}
	public void setContent(String filepath) {
		this.filepath=filepath;
	}
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

	public String getU_id_from_bt(){
		return u_id_from_bt;
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

	public void setU_id_from_bt(String u_id){
		this.u_id_from_bt=u_id;
	}
	public Date getBorrow_date(){
		return borrow_date;
	}

	public Date getReturn_date(){
		return return_date;
	}
	public void setU_id_from_u(String u_id) {
		this.u_id_from_u=u_id;
	}
	public String getU_id_from_u() {
		return u_id_from_u;
	}
}
