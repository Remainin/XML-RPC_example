package com.zhangmingshuai;
import java.io.IOException;
import java.net.InetAddress;

import org.apache.xmlrpc.XmlRpcHandler;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

public class FileServer {

	public WebServer    web_server;//ʵ����һ�����÷�����

    public FileServer()
    {
        try
        {
            this.web_server = new WebServer(10080,//���÷�������IP��ַ�Ͷ˿ں�
                    InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 }));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void initServer()
    {
        XmlRpcServer xmlRpcServer = web_server.getXmlRpcServer(); //��ȡһ��XML-RPC��ʵ��
        PropertyHandlerMapping phm = new PropertyHandlerMapping(); //����Handlerʵ��
        try
        {
        	//ע��FileData�࣬���ñ���ΪfileHandler
            phm.addHandler("fileHandler", FileData.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        xmlRpcServer.setHandlerMapping(phm);
        try
        {
        	// ����������
        	System.out.println("Attempting to start XML-RPC Server...");
            web_server.start();
            System.out.println("Server is ready, waiting for client calling...");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

   
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
    	FileServer  mySer = new FileServer();
        mySer.initServer();
    }
}
