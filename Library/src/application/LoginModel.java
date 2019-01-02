package application;

import java.util.List;

import org.jdbccore.MySQLQuery;
import org.po.Admin;
import org.po.Register;

public class LoginModel {
	
	@SuppressWarnings("unchecked")
	public static boolean isLogin(String userid,String password){
		List<Register> list=new MySQLQuery().queryRows("select r_id,r_password from register"
				+ " where r_id=? and r_password=?", Register.class, new Object[]{userid,password});
		return !(list==null||list.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	public static boolean adminIslogin(String id,String psw){
		List<Admin> list=new MySQLQuery().queryRows("select id,psw from admin"
				+ " where id=? and psw=?", Admin.class, new Object[]{id,psw});
		return !(list==null||list.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	public static boolean checkBorrowPsw(String psw){
		List<Register> list=new MySQLQuery().queryRows("select r_password from register where r_password=?",
				Register.class, new Object[]{psw});
		return !(list==null||list.isEmpty());
	}
	
}
