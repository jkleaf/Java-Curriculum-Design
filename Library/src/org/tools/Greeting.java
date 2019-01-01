package org.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Greeting {
	public static String say_hi() {
		String greet=null;
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        Date date;// new Date()为获取当前系统时间
        Date morning;
        Date afternoon;
        try {
        	date=df.parse(df.format(new Date()));
        	morning=df.parse("12:00:00");
        	afternoon=df.parse("19:00:00");

        if(date.getTime()-afternoon.getTime()>=0) {
        		greet=" 同学，晚上好！";
        }
        else if (date.getTime()-morning.getTime()>=0) {
        	 	greet=" 同学，下午好！";
        }
        else	 greet=" 同学，早上好！";
        }
        catch(Exception e) {
        	e.printStackTrace();
        }  		
        return greet;
	
		
	}

}
