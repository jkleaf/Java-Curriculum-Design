package org.tools;

import java.lang.reflect.Method;

public class ReflectUtils {
	
	public static Object invokeGetMethod(String fieldName,Object obj){
		try {
			Method m=obj.getClass().getDeclaredMethod("get"+StringUtils.firstChar2UpperCase(fieldName),null);
			Object priKeyValue=m.invoke(obj, null);
			return m.invoke(obj, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void invokeSetMethod(Object obj,String columnName,Object columnValue){
		try {
			Method m=obj.getClass().getDeclaredMethod("set"+StringUtils.firstChar2UpperCase(columnName),
					columnValue.getClass());
			m.invoke(obj, columnValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
