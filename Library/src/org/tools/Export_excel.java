package org.tools;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdbcjoin.BookAndTypes;
import org.jdbcjoin.Borrow_Users_Book;
import org.po.Book;
import org.po.Borrow_table;
import org.po.Register;
import org.po.Register_Users;
import org.po.Types;
import org.po.Users;

import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
public class Export_excel {
	public static <T> void export(List<T> list,String a) throws RowsExceededException, WriteException, IOException {
		WritableWorkbook wwb =null;
        try {
            wwb = Workbook.createWorkbook(create_file_excel.createFileWithCurDate(new File("E:\\Book\\export_excel\\export.xls")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        WritableSheet sheet = wwb.createSheet(a,0);
    
		if(list.get(0) instanceof Register) {
			List<Register> l=(List<Register>)list;
			boolean[] f=new boolean[3];
			for(int i=0;i<f.length;i++) f[i]=false;
			for(int i=0;i<l.size();i++) {
				if(l.get(i).getR_id()!=null) f[0]=true;
				if(l.get(i).getR_password()!=null) f[1]=true;
				if(l.get(i).getR_email()!=null) f[2]=true;
			}
			int k=0;
			if(f[0])
				sheet.addCell(new Label(k++,0,"账户"));
			if(f[1])
				sheet.addCell(new Label(k++,0,"密码"));
			if(f[2])
				sheet.addCell(new Label(k,0,"电子邮箱"));
			 for(int i = 0; i<l.size(); i++){
                 //Number对应数据库的int类型数据
				// sheet.setColumnView(i, cellView);
				int n=0;
				if(f[0])
             	sheet.addCell(new Label(n++,i+1,l.get(i).getR_id()));
				if(f[1])
 				sheet.addCell(new Label(n++,i+1,l.get(i).getR_password()));
				if(f[2])
 				sheet.addCell(new Label(n++,i+1,l.get(i).getR_email()));
             }
             wwb.write();
             wwb.close();
		}
		else if(list.get(0) instanceof Users) {
			List<Users> l=(List<Users>)list;
			boolean[] f=new boolean[10];
			for(int i=0;i<f.length;i++) f[i]=false;
			for(int i=0;i<l.size();i++) {
				if(l.get(i).getU_id()!=null) f[0]=true;
				if(l.get(i).getU_name()!=null) f[1]=true;
				if(l.get(i).getU_major()!=null) f[2]=true;
				if(l.get(i).getU_age()!=null) f[3]=true;
				if(l.get(i).getU_sex()!=null) f[4]=true;
				if(l.get(i).getU_email()!=null) f[5]=true;
				if(l.get(i).getU_tel()!=null) f[6]=true;
				if(l.get(i).getU_createDay()!=null) f[7]=true;
				if(l.get(i).getU_hasb()!=null) f[8]=true;
				if(l.get(i).getU_canb()!=null) f[9]=true;
			}
			int k=0;
			if(f[0])
				sheet.addCell(new Label(k++,0,"账户名"));
			if(f[1])
				sheet.addCell(new Label(k++,0,"姓名"));
			if(f[2])
				sheet.addCell(new Label(k++,0,"专业"));
			if(f[3])
				sheet.addCell(new Label(k++,0,"年龄"));
			if(f[4])
				sheet.addCell(new Label(k++,0,"性别"));
			if(f[5])
				sheet.addCell(new Label(k++,0,"电子邮箱"));
			if(f[6])
				sheet.addCell(new Label(k++,0,"电话号码"));
			if(f[7])
				sheet.addCell(new Label(k++,0,"创建日期"));
			if(f[8])
				sheet.addCell(new Label(k++,0,"已经借阅书籍数"));
			if(f[9])
				sheet.addCell(new Label(k,0,"能够借阅书籍数"));
			for(int i=0;i<l.size();i++) {
				int n=0;
				
				if(f[0])
				sheet.addCell(new Label(n++,i+1,l.get(i).getU_id()));
				if(f[1])
				sheet.addCell(new Label(n++,i+1,l.get(i).getU_name()));
				if(f[2])
				sheet.addCell(new Label(n++,i+1,l.get(i).getU_major()));
				if(f[3])
				sheet.addCell(new jxl.write.Number(n++, i+1, l.get(i).getU_age()));
				if(f[4])
				sheet.addCell(new Label(n++,i+1,l.get(i).getU_sex()));
				if(f[5])
				sheet.addCell(new Label(n++,i+1,l.get(i).getU_email()));
				if(f[6])
				sheet.addCell(new Label(n++,i+1,l.get(i).getU_tel()));
				if(f[7])
				sheet.addCell(new Label(n++,i+1,l.get(i).getU_createDay().toString()));
				if(f[8])
				sheet.addCell(new jxl.write.Number(8,i+1,l.get(i).getU_hasb()));
				if(f[9])
				sheet.addCell(new jxl.write.Number(9,i+1,l.get(i).getU_canb()));
			}
			wwb.write();
            wwb.close();
		}
		else if(list.get(0) instanceof Book) {
			List<Book> l=(List<Book>)list;
			boolean[] f=new boolean[6];
			for(int i=0;i<f.length;i++) f[i]=false;
			for(int i=0;i<l.size();i++) {
				if(l.get(i).getB_id()!=null) f[0]=true;
				if(l.get(i).getB_name()!=null) f[1]=true;
				if(l.get(i).getPub()!=null) f[2]=true;
				if(l.get(i).getP_date()!=null) f[3]=true;
				if(l.get(i).getB_type()!=null) f[4]=true;
				if(l.get(i).getContent()!=null) f[5]=true;
			}
			int k=0;
			if(f[0])
				sheet.addCell(new Label(k++,0,"书籍编号"));
			if(f[1])
				sheet.addCell(new Label(k++,0,"书籍名"));
			if(f[2])
				sheet.addCell(new Label(k++,0,"出版社"));
			if(f[3])
				sheet.addCell(new Label(k++,0,"出版日期"));
			if(f[4])
				sheet.addCell(new Label(k++,0,"书籍类型编码"));
			if(f[5])
				sheet.addCell(new Label(k,0,"简介"));
			for(int i=0;i<l.size();i++) {
				int n=0;
			//	sheet.setColumnView(i, cellView);
				if(f[0])
					sheet.addCell(new Label(n++,i+1,l.get(i).getB_id()));
				if(f[1])
					
					sheet.addCell(new Label(n++,i+1,l.get(i).getB_name()));
				if(f[2])
					sheet.addCell(new Label(n++,i+1,l.get(i).getPub()));
				if(f[3])
					sheet.addCell(new Label(n++,i+1,l.get(i).getP_date().toString()));
				if(f[4])
					sheet.addCell(new jxl.write.Number(n++,i+1,l.get(i).getB_type()));
				if(f[5]) 
					sheet.addCell(new Label(n,i+1,l.get(i).getContent()));
			}
			wwb.write();
            wwb.close();
		}
		else if(list.get(0) instanceof Borrow_table) {
			List<Borrow_table> l=(List<Borrow_table>)list;
			boolean[] f=new boolean[4];
			for(int i=0;i<f.length;i++) f[i]=false;
			for(int i=0;i<l.size();i++) {
				if(l.get(i).getU_id()!=null) f[0]=true;
				if(l.get(i).getB_id()!=null) f[1]=true;
				if(l.get(i).getBorrow_date()!=null) f[2]=true;
				if(l.get(i).getReturn_date()!=null) f[3]=true;
			}
			int k=0;
			if(f[0])
				sheet.addCell(new Label(k++,0,"读者编号"));
			if(f[1])
				sheet.addCell(new Label(k++,0,"借阅书籍号"));
			if(f[2])
				sheet.addCell(new Label(k++,0,"借阅日期"));
			if(f[3])
				sheet.addCell(new Label(k++,0,"还书日期"));
				
			for(int i=0;i<l.size();i++) {
				int n=0;
				
				if(f[0])
					sheet.addCell(new Label(n++,i+1,l.get(i).getU_id()));
				if(f[1])
					sheet.addCell(new Label(n++,i+1,l.get(i).getB_id()));
				if(f[2])
					sheet.addCell(new Label(n++,i+1,l.get(i).getBorrow_date().toString()));
				if(f[3])
					sheet.addCell(new Label(n++,i+1,l.get(i).getReturn_date().toString()));
				
			}
			wwb.write();
            wwb.close();
		}
		else if(list.get(0) instanceof Types) {
			List<Types> l=(List<Types>)list;
			boolean[] f=new boolean[2];
			for(int i=0;i<f.length;i++) f[i]=false;
			for(int i=0;i<l.size();i++) {
				if(l.get(i).getType()!=null) f[0]=true;
				if(l.get(i).getType_name()!=null) f[1]=true;
				
			}
			
			int k=0;
			if(f[0])
				sheet.addCell(new Label(k++,0,"类别编号"));
			if(f[1])
				sheet.addCell(new Label(k++,0,"类别名"));
			for(int i=0;i<l.size();i++) {
				int n=0;
				if(f[0])
					sheet.addCell(new jxl.write.Number(n++,i+1,l.get(i).getType()));
				if(f[1])
					sheet.addCell(new Label(n++,i+1,l.get(i).getType_name()));
				
			}
			wwb.write();
            wwb.close();
		}
		else if(list.get(0) instanceof Register_Users) {
			List<Register_Users> l=(List<Register_Users>)list;
			boolean[] f=new boolean[12];
			for(int i=0;i<f.length;i++) {
				f[i]=false;
			}
			for(int i=0;i<l.size();i++) {
				if(l.get(i).getR_id()!=null) f[0]=true;
				if(l.get(i).getU_id()!=null) f[1]=true;
				if(l.get(i).getU_name()!=null) f[2]=true;
				if(l.get(i).getU_sex()!=null) f[3]=true;
				if(l.get(i).getU_age()!=null) f[4]=true;
				if(l.get(i).getU_major()!=null) f[5]=true;
				if(l.get(i).getU_tel()!=null) f[6]=true;
				if(l.get(i).getR_email()!=null) f[7]=true;
				if(l.get(i).getU_email()!=null) f[8]=true;
				if(l.get(i).getU_hasb()!=null) f[9]=true;
				if(l.get(i).getU_createDay()!=null) f[10]=true;
				if(l.get(i).getR_password()!=null) f[11]=true;
			}
			int k=0;
			if(f[0]||f[1])
				sheet.addCell(new Label(k++,0,"用户号"));
			if(f[11])
				sheet.addCell(new Label(k++,0,"用户密码"));
			if(f[2])
				sheet.addCell(new Label(k++,0,"用户姓名"));
			if(f[3])
				sheet.addCell(new Label(k++,0,"用户性别"));
			if(f[4])
				sheet.addCell(new Label(k++,0,"用户年龄"));
			if(f[5])
				sheet.addCell(new Label(k++,0,"专业"));
			if(f[6])
				sheet.addCell(new Label(k++,0,"联系电话"));
			if(f[7]||f[8])
				sheet.addCell(new Label(k++,0,"电子邮箱"));
			if(f[9])
				sheet.addCell(new Label(k++,0,"已借阅书籍数"));
			if(f[10])
				sheet.addCell(new Label(k++,0,"用户创建日期"));
			for(int i=0;i<l.size();i++) {
				int n=0;
				if(f[0]||f[1]) {
					if(f[0])
						sheet.addCell(new Label(n++,i,l.get(i).getR_id()));
					if(f[1])
						sheet.addCell(new Label(n++,i,l.get(i).getU_id()));
				}
				if(f[11])
					sheet.addCell(new Label(n++,i,l.get(i).getR_password()));
				if(f[2])
					sheet.addCell(new Label(n++,i,l.get(i).getU_name()));
				if(f[3])
					sheet.addCell(new Label(n++,i,l.get(i).getU_sex()));
				if(f[4])
					sheet.addCell(new Label(n++,i,l.get(i).getU_age().toString()));
				if(f[5])
					sheet.addCell(new Label(n++,i,l.get(i).getU_major()));
				if(f[6])
					sheet.addCell(new Label(n++,i,l.get(i).getU_tel()));
				if(f[7]||f[8]) {
					if(f[7])
						sheet.addCell(new Label(n++,i,l.get(i).getR_email()));
					if(f[8])
						sheet.addCell(new Label(n++,i,l.get(i).getU_email()));
					}
				if(f[9])
					sheet.addCell(new Label(n++,i,l.get(i).getU_hasb().toString()));
				if(f[10])
					sheet.addCell(new Label(n++,i,l.get(i).getU_createDay().toString()));
			}
			wwb.write();
            wwb.close();
		}
		else if(list.get(0) instanceof BookAndTypes) {
			List<BookAndTypes> l=(List<BookAndTypes>)list;
			boolean[] f=new boolean[8];
			for(int i=0;i<f.length;i++) {
				f[i]=false;
			}
			for(int i=0;i<l.size();i++) {
				if(l.get(i).getB_id()!=null) f[0]=true;
				if(l.get(i).getB_name()!=null) f[1]=true;
				if(l.get(i).getPub()!=null) f[2]=true;
				if(l.get(i).getP_date()!=null) f[3]=true;
				if(l.get(i).getB_type()!=null) f[4]=true;
				if(l.get(i).getType()!=null) f[5]=true;
				if(l.get(i).getType_name()!=null) f[6]=true;
				if(l.get(i).getContent()!=null) f[7]=true;
			}
			
			int k=0;
			if(f[0])
				sheet.addCell(new Label(k++,0,"书籍编号"));
			if(f[1])
				sheet.addCell(new Label(k++,0,"书籍名"));
			if(f[2])
				sheet.addCell(new Label(k++,0,"出版社"));
			if(f[3])
				sheet.addCell(new Label(k++,0,"出版日期"));
			if(f[4]||f[5])
				sheet.addCell(new Label(k++,0,"书籍类型编码"));
			if(f[6])
				sheet.addCell(new Label(k++,0,"书籍类型"));
			if(f[7])
				sheet.addCell(new Label(k++,0,"简介"));
			for(int i=0;i<l.size();i++) {
				int n=0;
				
				if(f[0])
					sheet.addCell(new Label(n++,i+1,l.get(i).getB_id()));
				if(f[1])
					sheet.addCell(new Label(n++,i+1,l.get(i).getB_name()));
				if(f[2])
					sheet.addCell(new Label(n++,i+1,l.get(i).getPub()));
				if(f[3])
					sheet.addCell(new Label(n++,i+1,l.get(i).getP_date().toString()));
				if(f[4]||f[5]) {
					if(f[4])
						sheet.addCell(new jxl.write.Number(n++,i+1,l.get(i).getB_type()));
					else 
						sheet.addCell(new jxl.write.Number(n++,i,l.get(i).getType()));
					}
				if(f[6]) 
					sheet.addCell(new Label(n++,i+1,l.get(i).getType_name()));
				if(f[7])
					sheet.addCell(new Label(n,i+1,l.get(i).getContent()));
			}
			wwb.write();
            wwb.close();
			
		}
		else if(list.get(0) instanceof Borrow_Users_Book) {
			List<Borrow_Users_Book> l=(List<Borrow_Users_Book>)list;
			boolean[] f=new boolean[22];
			for(int i=0;i<f.length;i++) {
				f[i]=false;
			}
			for(int i=0;i<l.size();i++) {
				if(l.get(i).getU_id_from_u()!=null) f[0]=true;
				if(l.get(i).getU_name()!=null) f[1]=true;
				if(l.get(i).getU_age()!=null) f[2]=true;
				if(l.get(i).getU_sex()!=null) f[3]=true;
				if(l.get(i).getU_major()!=null) f[4]=true;
				if(l.get(i).getU_email()!=null) f[5]=true;
				if(l.get(i).getU_tel()!=null)   f[6]=true;
				if(l.get(i).getU_hasb()!=null)  f[7]=true;
				if(l.get(i).getU_canb()!=null)  f[8]=true;
				if(l.get(i).getU_createDay()!=null) f[9]=true;
				if(l.get(i).getB_id_from_b()!=null) f[10]=true;
				if(l.get(i).getB_name()!=null) f[11]=true;
				if(l.get(i).getPub()!=null) f[12]=true;
				if(l.get(i).getP_date()!=null) f[13]=true;
				if(l.get(i).getB_type()!=null) f[14]=true;
				if(l.get(i).getU_id_from_bt()!=null) f[15]=true;//f[0]
				if(l.get(i).getB_id_from_bt()!=null) f[16]=true;//f[10]
				if(l.get(i).getBorrow_date()!=null) f[17]=true;
				if(l.get(i).getReturn_date()!=null) f[18]=true;
				if(l.get(i).getContent()!=null) f[19]=true;
				if(l.get(i).getType()!=null) f[20]=true;//f[14]
				if(l.get(i).getType_name()!=null) f[21]=true;
			}
			int k=0;
			if(f[0]||f[15])
				sheet.addCell(new Label(k++,0,"用户编号"));
			if(f[1])
				sheet.addCell(new Label(k++,0,"用户姓名"));
			if(f[2])
				sheet.addCell(new Label(k++,0,"用户年龄"));
			if(f[3])
				sheet.addCell(new Label(k++,0,"用户性别"));
			if(f[4])
				sheet.addCell(new Label(k++,0,"用户专业"));
			if(f[5])
				sheet.addCell(new Label(k++,0,"用户电子邮箱"));
			if(f[6])
				sheet.addCell(new Label(k++,0,"用户电话"));
			if(f[7])
				sheet.addCell(new Label(k++,0,"已借阅书籍数"));
			if(f[8])
				sheet.addCell(new Label(k++,0,"可借书籍数"));
			if(f[9])
				sheet.addCell(new Label(k++,0,"用户创建日期"));
			if(f[10]||f[16])
				sheet.addCell(new Label(k++,0,"书籍编号"));
			if(f[11])
				sheet.addCell(new Label(k++,0,"书籍名称"));
			if(f[12])
				sheet.addCell(new Label(k++,0,"出版社"));
			if(f[13])
				sheet.addCell(new Label(k++,0,"出版日期"));
			if(f[14]||f[20])
				sheet.addCell(new Label(k++,0,"书籍类型编码"));
			if(f[17])
				sheet.addCell(new Label(k++,0,"借阅日期"));
			if(f[18])
				sheet.addCell(new Label(k++,0,"还书日期"));
			if(f[21])
				sheet.addCell(new Label(k++,0,"书籍类型"));
			if(f[19])
				sheet.addCell(new Label(k++,0,"内容简介"));
			for(int i=0;i<l.size();i++) {
			//	sheet.setColumnView(i, cellView);
				int n=0;
				if(f[0]||f[15]) {
					if(f[0])
						sheet.addCell(new Label(n++,i+1,l.get(i).getU_id_from_u()));
					if(f[15])
					sheet.addCell(new Label(n++,i+1,l.get(i).getU_id_from_bt()));}
				if(f[1])
					sheet.addCell(new Label(n++,i+1,l.get(i).getU_name()));
				if(f[2])
					sheet.addCell(new Label(n++,i+1,l.get(i).getU_age().toString()));
				if(f[3])
					sheet.addCell(new Label(n++,i+1,l.get(i).getU_sex()));
				if(f[4])
					sheet.addCell(new Label(n++,i+1,l.get(i).getU_major()));
				if(f[5])
					sheet.addCell(new Label(n++,i+1,l.get(i).getU_email()));
				if(f[6])
					sheet.addCell(new Label(n++,i+1,l.get(i).getU_tel()));
				if(f[7])
					sheet.addCell(new Label(n++,i+1,l.get(i).getU_hasb().toString()));
				if(f[8])
					sheet.addCell(new Label(n++,i+1,l.get(i).getU_canb().toString()));
				if(f[9])
					sheet.addCell(new Label(n++,i+1,l.get(i).getU_createDay().toString()));
				if(f[10]||f[16]) {
					if(f[10])
						sheet.addCell(new Label(n++,i+1,l.get(i).getB_id_from_b()));
					if(f[16])
						sheet.addCell(new Label(n++,i+1,l.get(i).getB_id_from_bt()));}
				if(f[11])
					sheet.addCell(new Label(n++,i+1,l.get(i).getB_name()));
				if(f[12])
					sheet.addCell(new Label(n++,i+1,l.get(i).getPub()));
				if(f[13])
					sheet.addCell(new Label(n++,i+1,l.get(i).getP_date().toString()));
				if(f[14]||f[20]) {
					if(f[14])
					sheet.addCell(new Label(n++,i+1,l.get(i).getB_type().toString()));
					if(f[20])
						sheet.addCell(new Label(n++,i+1,l.get(i).getType().toString()));}
				if(f[17])
					sheet.addCell(new Label(n++,i+1,l.get(i).getBorrow_date().toString()));
				if(f[18])
					sheet.addCell(new Label(n++,i+1,l.get(i).getReturn_date().toString()));
				if(f[21])
					sheet.addCell(new Label(n++,i+1,l.get(i).getType_name()));
				if(f[19])
					sheet.addCell(new Label(n++,i+1,l.get(i).getContent()));
			}
			wwb.write();
            wwb.close();
				
		}
	}
}
