package cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setAttribute("hello", "�ȳ��ϼ���~");
		
		return "/view/helloCommand.jsp";
	}
    
}
