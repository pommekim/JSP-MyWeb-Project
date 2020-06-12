package kr.co.jsp.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;

public class ContentServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bId = Integer.parseInt(request.getParameter("bId"));
		String boardId = request.getParameter("bId");
		
		//조회수 중복 방지용 쿠키를 하나 생성.
		Cookie hitCoo = new Cookie("boardId", boardId);
		hitCoo.setMaxAge(15);
		response.addCookie(hitCoo);
		
		//중복 방지용 쿠키를 얻기.
		Cookie[] cookies = request.getCookies();
		String bNum = "";
		for(Cookie c : cookies) {
			if(c.getValue().equals(boardId)) {
				bNum = c.getValue();
			}
		}
		
		//조회수 올려주는 메서드 호출.
		if(!bNum.equals(boardId)) {
			BoardDAO.getInstance().upHit(bId);
		}
		
		
		BoardVO vo = BoardDAO.getInstance().contentBoard(bId);
		
		request.setAttribute("contentBoard", vo);

	}

}
