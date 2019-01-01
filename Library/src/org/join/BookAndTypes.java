package org.join;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;

public class BookAndTypes {

    private String b_name;
    private String b_id;
    private Date p_date;
    private Integer b_type;
    private String pub;
    private String type_name;
    private Integer type;
    private String filepath;
    
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getB_id() {
		return b_id;
	}
	public void setB_id(String b_id) {
		this.b_id = b_id;
	}
	public Date getP_date() {
		return p_date;
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
	public String getFilepath() {
		return filepath;
	}
	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}
	public Integer getB_type() {
		return b_type;
	}
	public void setB_type(Integer b_type) {
		this.b_type = b_type;
	}
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
//	public void setContent(String filepath) {
//		this.filepath=filepath;
//	}
	public void setFilepath(String filepath) {
		this.filepath=filepath;
	}

}
