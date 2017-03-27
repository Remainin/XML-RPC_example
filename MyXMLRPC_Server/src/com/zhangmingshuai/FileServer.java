package com.zhangmingshuai;
import java.io.IOException;
import java.net.InetAddress;

import org.apache.xmlrpc.XmlRpcHandler;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

public class FileServer {

	public WebServer    web_server;//实例化一个内置服务器

    public FileServer()
    {
        try
        {
            this.web_server = new WebServer(10080,//配置服务器的IP地址和端口号
                    InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 }));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void initServer()
    {
        XmlRpcServer xmlRpcServer = web_server.getXmlRpcServer(); //获取一个XML-RPC的实例
        PropertyHandlerMapping phm = new PropertyHandlerMapping(); //建立Handler实例
        try
        {
        	//注册FileData类，设置别名为fileHandler
            phm.addHandler("fileHandler", FileData.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        xmlRpcServer.setHandlerMapping(phm);
        try
        {
        	// 启动服务器
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
