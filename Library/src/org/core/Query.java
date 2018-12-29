package org.core;

import java.util.List;

import org.tools.ExeUtils;

public interface Query extends ExeUtils{
	
	public void insert(Object obj);
	
	/**
	 * ɾ��clazz��ʾ���Ӧ�ı��еļ�¼��id������ֵ��
	 * @param clazz
	 * @param id
	 * @return	
	 */
	public void delete(Class clazz,Object id);
	
	/**
	 * ɾ�����������ݿ��ж�Ӧ�ļ�¼
	 * @param obj
	 */
	public void delete(Object obj);
	
	/**
	 * ���¶����Ӧ�ļ�¼
	 * @param obj 
	 * @param fieldNames
	 * @return
	 */
	public int update(Object obj,String[] fieldNames);

	/**
	 * ��ѯ���ض�����¼������ÿ�м�¼��װ��clazzָ������Ķ�����
	 * @param sql 
	 * @param clazz
	 * @param params
	 * @return
	 */
	public List queryRows(String sql,Class clazz,Object[] params);//���ж���
	
	/**
	 * ��ѯ����һ����¼������ÿ�м�¼��װ��clazzָ������Ķ�����
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Object queryUniqueRow(String sql,Class clazz,Object[] params);//һ�ж���
	
	/**
	 * ����һ����ѯ����Ķ���
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object queryValue(String sql,Object[] params);

	/**
	 * ����һ������
	 * @param sql
	 * @param params
	 * @return
	 */
	public Number queryNumber(String sql,Object[] params);//һ��һ��
}
