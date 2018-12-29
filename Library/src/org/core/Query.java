package org.core;

import java.util.List;

import org.tools.ExeUtils;

public interface Query extends ExeUtils{
	
	public void insert(Object obj);
	
	/**
	 * 删除clazz表示类对应的表中的记录（id：主键值）
	 * @param clazz
	 * @param id
	 * @return	
	 */
	public void delete(Class clazz,Object id);
	
	/**
	 * 删除对象在数据库中对应的记录
	 * @param obj
	 */
	public void delete(Object obj);
	
	/**
	 * 更新对象对应的记录
	 * @param obj 
	 * @param fieldNames
	 * @return
	 */
	public int update(Object obj,String[] fieldNames);

	/**
	 * 查询返回多条记录，并将每行记录封装到clazz指定的类的对象中
	 * @param sql 
	 * @param clazz
	 * @param params
	 * @return
	 */
	public List queryRows(String sql,Class clazz,Object[] params);//多行多列
	
	/**
	 * 查询返回一条记录，并将每行记录封装到clazz指定的类的对象中
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Object queryUniqueRow(String sql,Class clazz,Object[] params);//一行多列
	
	/**
	 * 返回一个查询结果的对象
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object queryValue(String sql,Object[] params);

	/**
	 * 返回一个数字
	 * @param sql
	 * @param params
	 * @return
	 */
	public Number queryNumber(String sql,Object[] params);//一行一列
}
