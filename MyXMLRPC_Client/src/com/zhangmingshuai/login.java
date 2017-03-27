package com.zhangmingshuai;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class login extends JFrame{
		//定义组件
	static JPanel jp1, jp2, jp3,jp4;
	static JLabel jl1, jl2,jl3;
	static JTextField jtf1,jtf2;
	static JButton jb1, jb2;
	static JTextArea ta;
	static JScrollPane js;

		//构造函数 初始化组件
	public login(){
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		jl1 = new JLabel("用户名");
		jl2 = new JLabel("密	     码");
		jl3 = new JLabel("返回信息：");
		
		jtf1 = new JTextField(10);
		jtf2 = new JTextField(10);
		
		jb1 = new JButton("登录");
		jb2 = new JButton("注册");
		
		ta = new JTextArea(2,10);	//这个组件用于显示返回信息
		//js = new JScrollPane(ta);
		
		jp1.add(jl1);
		jp1.add(jtf1);
		jp2.add(jl2);
		jp2.add(jtf2);
		jp3.add(jb1);
		jp3.add(jb2);
		jp4.add(jl3);
		jp4.add(ta);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		
		this.setLayout(new GridLayout(4, 1));		
		this.setTitle("登入");
		this.setSize(200,220);
		this.setLocation(100,200);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ta.setEditable(false);	//设置输出信息为不可编辑状态

	}
	
	public String invoke(String id,String password, String type)
    {
        int result = 0;
        XmlRpcClient client = new XmlRpcClient();	//实例化XML-RPC客户端
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try
        {
            config.setServerURL(new URL("http://127.0.0.1:10080"));	//设置端口，和对应服务器的端口向对应
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        client.setConfig(config);	//配置client服务器
        String out = null;
        try
        {
        	if (type.trim().equals("login")){	//触发“登入”按钮，调用readLog方法，传入两个参数id和password
        		result = (Integer)client.execute("fileHandler.readLog", new Object[] { id,password });
        		if(result == 1){		//根据不同的返回值，输入不同的信息
        			out = "登入成功！";
        		}
        		else if(result == 2){
        			out = "密码与用户名不符合！";
        		}
        		else if(result == 0){
        			out = "该用户不存在！";
        		}
        		else{
        			out = "发生异常！";
        		}
        	}
        	else if (type.trim().equals("register")){	//触发“登入”按钮，调用readLog方法，传入两个参数id和password
        		result = (Integer) client.execute("fileHandler.writeLog", new Object[] { id,password });
        		if(result == 1){	//根据不同的返回值，输入不同的信息
        			out = "注册成功！";
        		}
        		else{
        			out = "发生异常！";
        		}
        	}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return out;
    }
	
	
	public static void main(String[] args) {

		
		final login mylogin = new login();
        
        
        jb1.addActionListener(new ActionListener(){	//判断哪个按钮被触发
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			String id = jtf1.getText();
    			String password = jtf2.getText();
    			 if (id.equals("")||id.contains(" ")||password.equals("")||password.contains(" ")){	//这个条件表示用户输入为空或者含有空格
    				 ta.setText("输入不合法（含空格或为空）");
    			 }else{
    				 String result = mylogin.invoke(id,password, "login");
    				 ta.setText(result);
    			 }   			
    		}        	  
        });
        
        jb2.addActionListener(new ActionListener(){		//判断哪个按钮被除法
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			String id = jtf1.getText();
    			String password = jtf2.getText();
   			 if (id.equals("")||id.contains(" ")||password.equals("")||password.contains(" ")){//这个条件表示用户输入为空或者含有空格
   				 ta.setText("输入不合法（含空格或为空）");
   			 }else{
    				 String result = mylogin.invoke(id,password,"register");
    				 ta.setText(result);
    			 }   			
    		}        	  
        });
	}

}