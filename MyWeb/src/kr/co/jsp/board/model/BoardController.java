package kr.co.jsp.board.model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.service.ContentServiceImpl;
import kr.co.jsp.board.service.GetListServiceImpl;
import kr.co.jsp.board.service.IBoardService;
import kr.co.jsp.board.service.ModifyServiceImpl;
import kr.co.jsp.board.service.RegistServiceImpl;
import kr.co.jsp.board.service.SearchServiceImpl;
import kr.co.jsp.board.service.UpdateServiceImpl;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	IBoardService sv;
	RequestDispatcher dp;
	
    public BoardController() {
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
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		uri = uri.substring(conPath.length()+1, uri.lastIndexOf("."));
		System.out.println(uri);
		
		switch(uri) {
		case "write":
			System.out.println("글 쓰기 요청이 들어옴!");
			response.sendRedirect("board/board_write.jsp");
			break;
		
		case "regist":
			System.out.println("글 등록 요청이 들어옴!");
			sv = new RegistServiceImpl();
			sv.execute(request, response);
			
			response.sendRedirect("/MyWeb/list.board");
			break;
			
		case "list":
			System.out.println("글 목록 요청이 들어옴!");
			sv = new GetListServiceImpl();
			sv.execute(request, response);
			
			//request객체를 다음 화면까지 운반하기 위한 forward기능을 제공하는 객채
			//-> requestDispatcher
			dp = request.getRequestDispatcher("board/board_list.jsp");
			dp.forward(request, response);
			break;
			
		case "content":
			System.out.println("글 상세보기 요청이 들어옴!");
			sv = new ContentServiceImpl();
			sv.execute(request, response);
			
			request.getRequestDispatcher("board/board_content.jsp").forward(request, response);
			break;
		
		case "modify":
			System.out.println("글 수정 페이지로 이동 요청!");
			sv = new ModifyServiceImpl();
			sv.execute(request, response);
			
			request.getRequestDispatcher("board/board_modify.jsp").forward(request, response);
			break;
			
		case "update":
			System.out.println("글 수정 요청이 들어옴!");
			sv = new UpdateServiceImpl();
			sv.execute(request, response);
			
			response.sendRedirect("/MyWeb/list.board");
			break;
			
		case "delete":
			System.out.println("글 삭제 요청이 들어옴!");
			sv = new DeleteServiceImpl();
			sv.execute(request, response);
			
			response.sendRedirect("/MyWeb/list.board");
			break;
			
		case "search":
			System.out.println("글 검색 요청이 들어옴!");
			sv = new SearchServiceImpl();
			sv.execute(request, response);
			
			//null이 아닐 때만 포워딩을 하고 null일 때는 아무것도 하지 말아라(중복 응답 차단)
			if(request.getAttribute("boardList") != null) {
				request.getRequestDispatcher("board/board_list.jsp").forward(request, response);
			}
			
			break;
		}
		
		
		
		
		
		
		
	}

}
