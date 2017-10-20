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
				Object resultObject = null;         //object로 보내는게 중요함!!!!
		if(type ==null || type.equals("greeting")) {
			resultObject="안녕하세요"; 
		}else if(type.equals("date")) {
			resultObject = new java.util.Date();
		}else {
			resultObject = "Invalid Type!";
		}
		
		request.setAttribute("result", resultObject);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("./simpleView.jsp");
				//RequestDispatcher : 정보를 3번째 패이지까지 그대로 저장하는 용도 
		dispatcher.forward(request, response);
	}
	

}
