package org.jdbccore;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.jdbcbean.ColumnInfo;
import org.jdbcbean.TableInfo;
import org.jdbcjoin.BookAndTypes;
import org.po.Book;
import org.tools.DialogDisplay;
import org.tools.ReflectUtils;

import application1.MainPanel;


public class MySQLQuery implements Query{
	
	
	public void demo1(){
//		Book book=new Book();
//		emp.setId(2);
//		emp.setDname("3545dsv");
//		new MySQLQuery().update(emp, new String[]{"dname"});
//		new MySQLQuery().insert(emp);
//		new MySQLQuery().delete(emp);
//		new MySQLQuery().delete(Dept.class, 2);
	}
	
	public static void main(String[] args) {
//		List<Book> list=new MySQLQuery().queryRows("select b_id,b_name,b_type from book where b_id>? and b_type<?",
//				Book.class, new Object[]{1001,4});
////		System.out.println(list);
//		for (Book book : list) {
//			System.out.println(book.getB_id()+" "+book.getB_name()+" "+book.getB_type());
//		}
//		Number num=new MySQLQuery().queryNumber("select count(*) from emp where id>?", new Object[]{1});
//		System.out.println(num.doubleValue());
		List<BookAndTypes> list=new MySQLQuery().queryRows("select b_id,b_name,type_name"
				+ " from book,types where book.b_type=types.type",
				BookAndTypes.class, null);
		for (BookAndTypes bt : list) {
			System.out.println(bt.getB_id()+" "+bt.getB_name()+" "+bt.getType_name());
		}
	}
	
	@Override
	public int executeSQL(String sql, Object[] params){
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
		}catch (SQLIntegrityConstraintViolationException e){
			System.err.println("主键值为空或主键值已重复!");
//			DialogDisplay.errorDialog("错误", "主键值为空或主键值已重复!");
//			MainPanel.textId.clear();//
			return -1;
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
		TableInfo tableInfo=TableContext.classTableMap.get(c);
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
		TableInfo tableInfo=TableContext.classTableMap.get(clazz);
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
		TableInfo tableInfo=TableContext.classTableMap.get(c);
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
		TableInfo tableInfo=TableContext.classTableMap.get(c);
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
