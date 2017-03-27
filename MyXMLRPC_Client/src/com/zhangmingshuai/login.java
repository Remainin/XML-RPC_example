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
		//�������
	static JPanel jp1, jp2, jp3,jp4;
	static JLabel jl1, jl2,jl3;
	static JTextField jtf1,jtf2;
	static JButton jb1, jb2;
	static JTextArea ta;
	static JScrollPane js;

		//���캯�� ��ʼ�����
	public login(){
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		jl1 = new JLabel("�û���");
		jl2 = new JLabel("��	     ��");
		jl3 = new JLabel("������Ϣ��");
		
		jtf1 = new JTextField(10);
		jtf2 = new JTextField(10);
		
		jb1 = new JButton("��¼");
		jb2 = new JButton("ע��");
		
		ta = new JTextArea(2,10);	//������������ʾ������Ϣ
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
		this.setTitle("����");
		this.setSize(200,220);
		this.setLocation(100,200);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ta.setEditable(false);	//���������ϢΪ���ɱ༭״̬

	}
	
	public String invoke(String id,String password, String type)
    {
        int result = 0;
        XmlRpcClient client = new XmlRpcClient();	//ʵ����XML-RPC�ͻ���
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try
        {
            config.setServerURL(new URL("http://127.0.0.1:10080"));	//���ö˿ڣ��Ͷ�Ӧ�������Ķ˿����Ӧ
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        client.setConfig(config);	//����client������
        String out = null;
        try
        {
        	if (type.trim().equals("login")){	//���������롱��ť������readLog������������������id��password
        		result = (Integer)client.execute("fileHandler.readLog", new Object[] { id,password });
        		if(result == 1){		//���ݲ�ͬ�ķ���ֵ�����벻ͬ����Ϣ
        			out = "����ɹ���";
        		}
        		else if(result == 2){
        			out = "�������û��������ϣ�";
        		}
        		else if(result == 0){
        			out = "���û������ڣ�";
        		}
        		else{
        			out = "�����쳣��";
        		}
        	}
        	else if (type.trim().equals("register")){	//���������롱��ť������readLog������������������id��password
        		result = (Integer) client.execute("fileHandler.writeLog", new Object[] { id,password });
        		if(result == 1){	//���ݲ�ͬ�ķ���ֵ�����벻ͬ����Ϣ
        			out = "ע��ɹ���";
        		}
        		else{
        			out = "�����쳣��";
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
        
        
        jb1.addActionListener(new ActionListener(){	//�ж��ĸ���ť������
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			String id = jtf1.getText();
    			String password = jtf2.getText();
    			 if (id.equals("")||id.contains(" ")||password.equals("")||password.contains(" ")){	//���������ʾ�û�����Ϊ�ջ��ߺ��пո�
    				 ta.setText("���벻�Ϸ������ո��Ϊ�գ�");
    			 }else{
    				 String result = mylogin.invoke(id,password, "login");
    				 ta.setText(result);
    			 }   			
    		}        	  
        });
        
        jb2.addActionListener(new ActionListener(){		//�ж��ĸ���ť������
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			String id = jtf1.getText();
    			String password = jtf2.getText();
   			 if (id.equals("")||id.contains(" ")||password.equals("")||password.contains(" ")){//���������ʾ�û�����Ϊ�ջ��ߺ��пո�
   				 ta.setText("���벻�Ϸ������ո��Ϊ�գ�");
   			 }else{
    				 String result = mylogin.invoke(id,password,"register");
    				 ta.setText(result);
    			 }   			
    		}        	  
        });
	}

}