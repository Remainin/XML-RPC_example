package com.zhangmingshuai;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileData{
    //д�ļ���֧�������ַ�
    public int writeLog(String str1,String str2)
    {
        try
        {
        String path="D:/MyXML_RPC.txt";
        File file=new File(path);
        if(!file.exists()){	//���ļ�������ʱ������һ��
            file.createNewFile();
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println("--�û�--����--");// ���ļ���д���ַ���
        }
        FileOutputStream out=new FileOutputStream(file,true); //����׷�ӷ�ʽ��true        
        StringBuffer sb=new StringBuffer();
        sb.append(str1+" "+str2+"\r\n");	//windows�л��з�Ϊ\r\n,linux�л��з�Ϊ\r,Mac���з�Ϊ\n
        out.write(sb.toString().getBytes("utf-8"));//ת����Ӧ���ַ���utf-8,��ֹ�������롣
        out.close();
        return 1;	//����ֵΪ1ʱ��ʾд��ɹ�
        }
        catch(IOException ex)
        {
            System.out.println(ex.getStackTrace());
            return 0;	//����ֵΪ0ʱ��ʾд��ʧ��
        }
    }    
    //���ļ���֧�������ַ�
    public int readLog(String id,String password)
    {
        StringBuffer sb=new StringBuffer();
        String tempstr=null;
        String[] a = null;	//�ַ����������ڱ�����ļ��ж���������
        try
        {
            String path="D:/MyXML_RPC.txt";
            File file=new File(path);
            if(!file.exists()){
            	file.createNewFile();  
            	PrintStream ps = new PrintStream(new FileOutputStream(file));
                ps.println("-�û�- -����-");// ���ļ���д���ַ���
            }
            FileInputStream fis=new FileInputStream(file);	//�ļ�������
            BufferedReader br=new BufferedReader(new InputStreamReader(fis,"utf-8"));//���ñ�����򣬷�����������
            while((tempstr=br.readLine())!=null){
                sb.append(tempstr);
               // System.out.println(tempstr);
            	sb.append(" ");	//����ո�Ϊ�˽��ķָ�
            }
            a = sb.toString().split("\\s"); //�����������ַ����Կո���зָ����a��
            if (a.length == 0){
            	return 0;	//����ֵΪ0��ʱ�����ƥ��ʧ�ܣ����û�δ������ע�����
            }
            for(int i=0;i<a.length-1;i=i+2){ //�ļ��ĸ�ʽΪ���û��� ����\n,�����i=i+2��ƥ���û���
            	//System.out.println(a[i]);
            	if(a[i].equals(id)&&(a[i+1].equals(password))){
            		return 1;	//����ֵΪ1����ƥ��ɹ������ҵ��˸��û�������������ȷ
            	}
            	else if(a[i].equals(id)&&(!a[i+1].equals(password))){
            		return 2;	//����ֵʱ�����ҵ����û����������벻��ȷ
            	}
            }
            return 0;	//δ�ҵ��û��������û�δע��
        }
        catch(IOException ex)
        {
            System.out.println(ex.getStackTrace());
            return -1;	//�������ʱ����-1
        }
    }
   /* public static void main(String[] args) {
        // TODO Auto-generated method stub
        writeLog("this","hi");
        writeLog("����","�õ�");
        int a;
        a = readLog("����","��");
        System.out.println(a);
    }
*/
}