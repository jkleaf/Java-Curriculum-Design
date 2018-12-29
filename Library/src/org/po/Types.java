package org.po;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Types {

    private String type_name;
    private String type;

	public String getType_name(){
		return type_name;
	}

	public String getType(){
		return type;
	}

	public void setType_name(String type_name){
		this.type_name=type_name;
	}

	public void setType(String type){
		this.type=type;
	}

}