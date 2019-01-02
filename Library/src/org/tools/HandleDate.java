package org.tools;

import java.sql.Date;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jdbccore.MySQLQuery;

public class HandleDate {
	
	public static void main(String[] args) {
//		for (Integer integer : getLocalTime()) {
//			System.out.print(integer+" ");
//		}
//		System.out.println(getAge("1998-1-2"));
//		System.out.println(string2Date("2016-12-21"));
//		System.out.println(getDuration("2018-3-2", LocalDate.now().toString()).toString());
//		System.out.println(new MySQLQuery().queryValue("select borrow_date from borrow_table"
//						+" where u_id=?", new Object[]{1}).toString());
	}
	
	public static List<Integer> getLocalTime() {
		List<Integer> list=new ArrayList<>();
		Calendar calendar=Calendar.getInstance();
		list.add(calendar.get(Calendar.YEAR));
		list.add(calendar.get(Calendar.MONTH)+1);
		list.add(calendar.get(Calendar.DATE));
		list.add(calendar.get(Calendar.HOUR_OF_DAY));
		list.add(calendar.get(Calendar.MINUTE));
		list.add(calendar.get(Calendar.SECOND));
		return list;
	}
	
	public static Date string2Date(String date) {
		return new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date,new ParsePosition(0)).getTime());
	}
	
	public static Integer getAge(String birthday) {
		return getLocalTime().get(0)-Integer.valueOf(birthday.substring(0, birthday.indexOf("-")));
	}
	
	public static Integer getDuration(String fromDate,String toDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			return (int)(((sdf.parse(toDate).getTime()-sdf.parse(fromDate).getTime()))/(1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			e.printStackTrace();
			return null; 
		}
	}
}
