package kr.co.jsp.board.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;

public class SearchServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String category = request.getParameter("category");
		String keyword = request.getParameter("search");
		List<BoardVO> list = BoardDAO.getInstance().searchBoard(category, keyword);
		
		if(list.size() == 0) {
			//자바 클래스에서 HTML이나 JS 문법을 쓰는 방법 - PrintWriter out 객체를 사용.
			response.setContentType("text/html; charset=UTF-8");
			
			try {
				PrintWriter out = response.getWriter();
				
				String htmlCode = "<script>\r\n"
						+ "alert(\"검색 결과에 따른 게시물이 없습니다.\");\r\n"
						+ "location.href=\"/MyWeb/list.board\";\r\n"
						+ "</script>";
				out.print(htmlCode);
				out.flush(); //버퍼에 저장되어 있는 내용을 클라이언트로 전송하고 버퍼를 비웁니다.
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		request.setAttribute("boardList", list);

	}

}
