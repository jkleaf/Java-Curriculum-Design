package org.core;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.bean.ColumnInfo;
import org.bean.TableInfo;
import org.tools.ReflectUtils;


public class MySQLQuery implements Query{
	
	public static void main(String[] args) {
//		Emp emp=new Emp();
//		emp.setId(2);
//		emp.setDname("3545dsv");
//		new MySQLQuery().update(emp, new String[]{"dname"});
//		new MySQLQuery().insert(emp);
//		new MySQLQuery().delete(emp);
//		new MySQLQuery().delete(Dept.class, 2);
		//////////////////////////////////////////////
//		List<Emp> list=new MySQLQuery().queryRows("select id,dname,age from emp where id>=? and age<?",
//				Emp.class, new Object[]{3,23});
//		System.out.println(list);
//		for (Emp emp : list) {
//			System.out.println(emp.getId()+" "+emp.getDname()+" "+emp.getAge());
//		}
		//////////////////////////////////////////////
//		Number num=new MySQLQuery().queryNumber("select count(*) from emp where id>?", new Object[]{1});
//		System.out.println(num.doubleValue());
	}
	
	@Override
	public int executeSQL(String sql, Object[] params) {
		Connection conn=DBmanager.getConn();
		int count=0;
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setObject(1+i, params[i]);
				}
			}
			count=ps.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e){
			System.err.println("主键值为空或主键值已重复!");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBmanager.Closeall(ps,conn);
		}
		return count;
	}

	@Override
	public void insert(Object obj) {
		Class<?> c=obj.getClass();
		List<Object> params=new ArrayList<>();
		TableInfo tableInfo=TableContext.poClassTableMap.get(c);
		StringBuilder sql=new StringBuilder("insert into "+tableInfo.getTname()+" (");
		int countNotNullFields=0;
		Field[] fields=c.getDeclaredFields();
		for (Field field : fields) {
			String fieldName=field.getName();
			Object fieldValue=ReflectUtils.invokeGetMethod(fieldName, obj);
			if(fieldName!=null){
				countNotNullFields++;
				sql.append(fieldName+",");
				params.add(fieldValue);
			}
		}
		sql.setCharAt(sql.length()-1, ')');
		sql.append(" values(");
		for(int i=0;i<countNotNullFields;i++){
			sql.append("?,");
		}
		sql.setCharAt(sql.length()-1, ')');
		executeSQL(sql.toString(), params.toArray());
	}

	@Override
	public void delete(Class clazz, Object id) {
		TableInfo tableInfo=TableContext.poClassTableMap.get(clazz);
		if(tableInfo==null)
			System.out.println("null");
		ColumnInfo onlyPriKey=tableInfo.getOnlyPriKey();
		String sql="delete from "+tableInfo.getTname()
			+" where "+onlyPriKey.getName()+"=? ";
		executeSQL(sql, new Object[]{id});
//		System.out.println("删除成功!");
	}

	@Override
	public void delete(Object obj) {
		Class<? extends Object> c=obj.getClass();
		TableInfo tableInfo=TableContext.poClassTableMap.get(c);
		ColumnInfo onlyPriKey=tableInfo.getOnlyPriKey();
//		if(onlyPriKey!=null)
//			System.out.println(onlyPriKey.getName());
//		else 
//			System.out.println("null");
		Object priKeyValue=ReflectUtils.invokeGetMethod(onlyPriKey.getName(), obj);
		delete(c,priKeyValue);
	}

	@Override
	public int update(Object obj, String[] fieldNames) {
		Class c=obj.getClass();
		List<Object> params=new ArrayList<>();
		TableInfo tableInfo=TableContext.poClassTableMap.get(c);
		ColumnInfo priKey=tableInfo.getOnlyPriKey();
		StringBuilder sql=new StringBuilder("update "+tableInfo.getTname()+" set ");
		for (String fname : fieldNames) {
			Object fValue=ReflectUtils.invokeGetMethod(fname, obj);
			params.add(fValue);
			sql.append(fname+"=?,");
		}
		sql.setCharAt(sql.length()-1, ' ');
		sql.append(" where ");
		sql.append(priKey.getName()+"=? ");
		params.add(ReflectUtils.invokeGetMethod(priKey.getName(), obj));
		return executeSQL(sql.toString(), params.toArray());
	}

	@Override
	public List queryRows(String sql, Class clazz, Object[] params) {//返回javabean
		Connection conn=DBmanager.getConn();
		List<Object> list=null;//存储查询结果
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setObject(1+i, params[i]);
				}
			}
//			System.out.println(ps);//
			rs=ps.executeQuery();
			ResultSetMetaData metaData=rs.getMetaData();
			//多行多列
			while(rs.next()){
				if(list==null)
					list=new ArrayList<>();
				Object rowObj=clazz.newInstance();
				for(int i=0;i<metaData.getColumnCount();i++){
					String columnName=metaData.getColumnLabel(i+1);
					Object columnValue=rs.getObject(i+1);
					ReflectUtils.invokeSetMethod(rowObj, columnName, columnValue);
				}
				list.add(rowObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBmanager.Closeall(ps,conn);
		}
		return list;
	}

	@Override
	public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
		List list=queryRows(sql, clazz, params);
		return (list!=null&&list.size()>0)?list.get(0):null;
	}

	@Override
	public Object queryValue(String sql, Object[] params) {
		Connection conn=DBmanager.getConn();
		Object value=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setObject(1+i, params[i]);
				}
			}
			rs=ps.executeQuery();
			while(rs.next()){
				value=rs.getObject(1);//select count(*) from table
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBmanager.Closeall(ps,conn);
		}
		return value;
	}
	
	@Override
	public Number queryNumber(String sql, Object[] params) {
		return (Number)queryValue(sql, params);
	}


}
