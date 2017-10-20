package controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleController extends HttpServlet {
	
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String type= request.getParameter("type");
				Object resultObject = null;         //object�� �����°� �߿���!!!!
		if(type ==null || type.equals("greeting")) {
			resultObject="�ȳ��ϼ���"; 
		}else if(type.equals("date")) {
			resultObject = new java.util.Date();
		}else {
			resultObject = "Invalid Type!";
		}
		
		request.setAttribute("result", resultObject);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("./simpleView.jsp");
				//RequestDispatcher : ������ 3��° ���������� �״�� �����ϴ� �뵵 
		dispatcher.forward(request, response);
	}
	

}
