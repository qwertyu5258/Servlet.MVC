package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmd.CommandHandler;
import cmd.NullHandler;

public class ControllerUsingFile extends HttpServlet {
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<String, CommandHandler>();

	public void init() throws ServletException{
		String configFile = getInitParameter("configFile");
		Properties property = new Properties();
		FileInputStream fis = null;
		try {
			String configFilePath = getServletContext().getRealPath(configFile);
			fis = new FileInputStream(configFilePath);
			property.load(fis);
		}catch(IOException e) {
			throw new ServletException(e);
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		Iterator keyIt = property.keySet().iterator();
		while(keyIt.hasNext()) {
			String command = (String) keyIt.next();
			String handlerClassName = property.getProperty(command);
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				
				commandHandlerMap.put(command, handlerInstance);
			}catch(ClassNotFoundException e) {
				throw new ServletException(e);
			}catch(InstantiationException e) {
				throw new ServletException(e);
			}catch(IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("cmd");
		CommandHandler handler = commandHandlerMap.get(command);
		
		
	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

	/*	PrintWriter out = response.getWriter(); //request, response 두개 다쓰니 에러난듯
		
		System.out.println("123123"); 
		out.close();	
*/
	
		System.out.println("들어옴1");
		if (handler == null) {
			handler = new NullHandler();
			
		}
		String viewPage = null;
		
		try {
			viewPage = handler.process(request, response);
		} catch (Throwable e) {
			System.out.println("에러 throw");
			throw new ServletException(e);
		}
		System.out.println("정상");
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);

	}
}
