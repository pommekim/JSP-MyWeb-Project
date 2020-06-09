<%@page import="kr.co.jsp.user.model.UserVO"%>
<%@page import="kr.co.jsp.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	request.setCharacterEncoding("utf-8");
    	
   		String id = (String) session.getAttribute("user_id");
   		String checkPw = request.getParameter("check_pw");
   		
    	int result = UserDAO.getInstance().userCheck(id, checkPw);
    	
    	if(result != 0) {
    		UserDAO.getInstance().deleteUser(id);
    		session.invalidate(); %>
    		
    		<script>
    			alert("회원 탈퇴가 정상 처리되었습니다.");
    			location.href="user_login.jsp";
    		</script>
    		
    	<% } else { %>
    	
	    	<script>
	    		alert("비밀번호가 틀렸습니다.");
	    		location.href="user_mypage.jsp";
	    	</script>
    		
    	<% } %>
    	