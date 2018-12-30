package org.tools;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class create_file_excel {
	public static String generateSuffix() {
        // ��õ�ǰʱ��
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // ת��Ϊ�ַ���
        String formatDate = format.format(new Date());
        // ��������ļ����
        int random = new Random().nextInt(10000);
        return new StringBuffer().append(formatDate).append(
                random).toString();
    }

    public static File createFileWithCurDate(File from){
        String[] fileInfo = getFileInfo(from);
        String toPrefix=fileInfo[0]+generateSuffix();
        String toSuffix=fileInfo[1];
        return new File(from.getParent(),toPrefix+toSuffix);
    }
    
    public static String[] getFileInfo(File from){
        String fileName=from.getName();
        int index = fileName.lastIndexOf(".");
        String toPrefix="";
        String toSuffix="";
        if(index==-1){
            toPrefix=fileName;
        }else{
            toPrefix=fileName.substring(0,index);
            toSuffix=fileName.substring(index,fileName.length());
        }
        return new String[]{toPrefix,toSuffix};
    }
}
