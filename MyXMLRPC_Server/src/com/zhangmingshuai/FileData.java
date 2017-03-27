package com.zhangmingshuai;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileData{
    //写文件，支持中文字符
    public int writeLog(String str1,String str2)
    {
        try
        {
        String path="D:/MyXML_RPC.txt";
        File file=new File(path);
        if(!file.exists()){	//当文件不存在时，创建一个
            file.createNewFile();
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println("--用户--密码--");// 往文件里写入字符串
        }
        FileOutputStream out=new FileOutputStream(file,true); //采用追加方式用true        
        StringBuffer sb=new StringBuffer();
        sb.append(str1+" "+str2+"\r\n");	//windows中换行符为\r\n,linux中换行符为\r,Mac换行符为\n
        out.write(sb.toString().getBytes("utf-8"));//转换对应的字符集utf-8,防止中文乱码。
        out.close();
        return 1;	//返回值为1时表示写入成功
        }
        catch(IOException ex)
        {
            System.out.println(ex.getStackTrace());
            return 0;	//返回值为0时表示写入失败
        }
    }    
    //读文件，支持中文字符
    public int readLog(String id,String password)
    {
        StringBuffer sb=new StringBuffer();
        String tempstr=null;
        String[] a = null;	//字符串数组用于保存从文件中读出的内容
        try
        {
            String path="D:/MyXML_RPC.txt";
            File file=new File(path);
            if(!file.exists()){
            	file.createNewFile();  
            	PrintStream ps = new PrintStream(new FileOutputStream(file));
                ps.println("-用户- -密码-");// 往文件里写入字符串
            }
            FileInputStream fis=new FileInputStream(file);	//文件输入流
            BufferedReader br=new BufferedReader(new InputStreamReader(fis,"utf-8"));//设置编码规则，放置中文乱码
            while((tempstr=br.readLine())!=null){
                sb.append(tempstr);
               // System.out.println(tempstr);
            	sb.append(" ");	//加入空格为了今后的分割
            }
            a = sb.toString().split("\\s"); //将缓冲区的字符串以空格进行分割保存至a中
            if (a.length == 0){
            	return 0;	//返回值为0的时候代表匹配失败，即用户未存在于注册表中
            }
            for(int i=0;i<a.length-1;i=i+2){ //文件的格式为：用户名 密码\n,因此以i=i+2来匹配用户名
            	//System.out.println(a[i]);
            	if(a[i].equals(id)&&(a[i+1].equals(password))){
            		return 1;	//返回值为1代表匹配成功，即找到了该用户且密码输入正确
            	}
            	else if(a[i].equals(id)&&(!a[i+1].equals(password))){
            		return 2;	//返回值时代表找到该用户，但是密码不正确
            	}
            }
            return 0;	//未找到用户，即此用户未注册
        }
        catch(IOException ex)
        {
            System.out.println(ex.getStackTrace());
            return -1;	//意外情况时返回-1
        }
    }
   /* public static void main(String[] args) {
        // TODO Auto-generated method stub
        writeLog("this","hi");
        writeLog("啊啊","好的");
        int a;
        a = readLog("啊啊","好");
        System.out.println(a);
    }
*/
}