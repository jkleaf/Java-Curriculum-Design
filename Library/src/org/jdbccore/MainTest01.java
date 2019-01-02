package org.jdbccore;

import java.io.IOException;

import org.po.Book;
import org.tools.Export_excel;

import jxl.write.WriteException;

public class MainTest01 {

	public static void main(String[] args) {
		try {
			Export_excel.export(new MySQLQuery().queryRows("select *from book", Book.class, null), "book");
		} catch (WriteException | IOException e) {
			e.printStackTrace();
		}
	}

}
