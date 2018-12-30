package application;

import java.util.List;

import org.core.MySQLQuery;
import org.po.Register;

public class LoginModel {
	
	public static boolean isLogin(String username,String password){
		@SuppressWarnings("unchecked")
		List<Register> list=new MySQLQuery().queryRows("select r_id,r_password from register"
				+ " where r_id=? and r_password=?", Register.class, new Object[]{username,password});
		return !(list==null||list.isEmpty());
	}
}
