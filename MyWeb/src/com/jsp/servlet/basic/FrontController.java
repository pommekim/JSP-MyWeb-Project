package com.jsp.servlet.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("요청이 들어옴!");
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String conPath = request.getContextPath();
		System.out.println(conPath);
		
		//String 메서드 substring: 시작 인덱스와 끝 인덱스를 알려주면 그 사이 부분만 추출해서 돌려줌!!!
		uri = uri.substring(conPath.length() + 1, uri.lastIndexOf("."));
		System.out.println(uri);
		
		switch(uri) {
		case "write":
			System.out.println("글쓰기 요청!");
			break;
		case "list":
			System.out.println("글목록 요청!");
			break;
		case "update":
			System.out.println("글수정 요청!");
			break;
		}
		
		
		
		
	}

}
