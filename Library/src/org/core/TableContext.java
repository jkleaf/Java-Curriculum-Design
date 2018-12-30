package org.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bean.ColumnInfo;
import org.bean.TableInfo;
import org.tools.StringUtils;

public class TableContext {
	
	private static Map<String, TableInfo> tables=new HashMap<>();
	
	public static Map<Class, TableInfo> classTableMap=new HashMap<>();
	
	private TableContext(){}
	
	static{
		try {
			Connection conn=DBmanager.getConn();
			DatabaseMetaData dbmd=conn.getMetaData();
			ResultSet tableRet =dbmd.getTables(conn.getCatalog(), null, null, new String[]{"TABLE"});
			while (tableRet.next()) {
				String tableName=tableRet.getString("TABLE_NAME").toLowerCase();
				TableInfo ti=new TableInfo(tableName,new HashMap<String,ColumnInfo>(),
						new ArrayList<ColumnInfo>());	
				tables.put(tableName, ti);
				ResultSet set=dbmd.getColumns(null, "%", tableName, "%");//查询表中所有字段
				while(set.next()){
					ColumnInfo ci=new ColumnInfo(set.getString("COLUMN_NAME"),
							set.getString("TYPE_NAME"), 0);
					ti.getColumns().put(set.getString("COLUMN_NAME"),ci);
				}
				ResultSet set2=dbmd.getPrimaryKeys(conn.getCatalog(), "%", tableName);//查询表中的主键
				while (set2.next()) {
					ColumnInfo ci2=(ColumnInfo)ti.getColumns().get(set2.getString("COLUMN_NAME"));
					ci2.setKeyType(1);//设为主键类型
					ti.getPriKeys().add(ci2);
				}
				if(ti.getPriKeys().size()>0){//取唯一主键，方便使用。
					ti.setOnlyPriKey(ti.getPriKeys().get(0));
				}
			}
			for (TableInfo tableInfo : tables.values()) {
				try {
					Class<?> c=Class.forName(DBmanager.getConf().getPoPackage()
							+"."+StringUtils.firstChar2UpperCase(tableInfo.getTname()));
					classTableMap.put(c, tableInfo);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void printTablesName(){
		Map<String, TableInfo> map=TableContext.getTableInfos();
		for (TableInfo t : map.values()) {
			System.out.println("tableName:"+t.getTname()+"\n");
			Map<String, ColumnInfo> columns=t.getColumns();
			for (Entry<String, ColumnInfo> cInfo : columns.entrySet()) {
				ColumnInfo cValue=cInfo.getValue();
				System.out.println(cInfo.getKey()+"\n");
				System.out.println("Name:"+cValue.getName());
				System.out.println("DataType:"+cValue.getDataType());
				System.out.println("KeyType:"+cValue.getKeyType()+"\n");
				System.out.println("*****************************");
			}
//			System.out.println("-----------------------------");
//			System.out.println("OnlyPriKey:");
//			System.out.print(t.getOnlyPriKey().getName()+":");
//			System.out.println(t.getOnlyPriKey().getKeyType());
//			System.out.println("PriKeys:");
//			List<ColumnInfo> list=t.getPriKeys();
//			for (ColumnInfo columnInfo : list) {
//				System.out.print(columnInfo.getName()+":");
//				System.out.println(columnInfo.getKeyType());
//			}
			System.out.println("-----------------------------");
		}
	}
	
	public static Map<String, TableInfo> getTableInfos(){
		return tables;
	}
	
	public static void main(String[] args) {
		printTablesName();
//		loadPoTables();
	}
	
}
