package org.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Greeting {
	public static String say_hi() {
		String greet=null;
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//�������ڸ�ʽ
        Date date;// new Date()Ϊ��ȡ��ǰϵͳʱ��
        Date morning;
        Date afternoon;
        try {
        	date=df.parse(df.format(new Date()));
        	morning=df.parse("12:00:00");
        	afternoon=df.parse("19:00:00");

        if(date.getTime()-afternoon.getTime()>=0) {
        		greet=" ͬѧ�����Ϻã�";
        }
        else if (date.getTime()-morning.getTime()>=0) {
        	 	greet=" ͬѧ������ã�";
        }
        else	 greet=" ͬѧ�����Ϻã�";
        }
        catch(Exception e) {
        	e.printStackTrace();
        }  		
        return greet;
	
		
	}

}
