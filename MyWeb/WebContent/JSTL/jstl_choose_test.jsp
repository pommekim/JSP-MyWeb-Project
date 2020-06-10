<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		jstl choose 태그와 el을 활용하여
		100점이 넘어가면 "점수를 잘못 입력하셨습니다."
		90점이 넘으면 "당신의 학점은 A입니다."를 출력.
		80점대는 B, 70점대는 C, 60점대는 D, 나머지는 F를 출력하세요.
	 --%>
	 
	 <c:set var="point" value="${param.point}" />
	 # 점수: ${point}점<br>
	 
	 <c:choose>
	 	<c:when test="${point > 100}">
	 		<b>점수를 잘못 입력하셨습니다.</b>
	 	</c:when>
	 	<c:when test="${point >= 90}">
	 		<c:choose>
	 			<c:when test="${point >= 95}">
	 				<b>당신의 학점은 A+입니다.</b>
	 			</c:when>
	 			<c:otherwise>
	 				<b>당신의 학점은 A입니다.</b>
	 			</c:otherwise>
	 		</c:choose>
	 	</c:when>
	 	<c:when test="${point >= 80}">
	 		<b>당신의 학점은 B입니다.</b>
	 	</c:when>
	 	<c:when test="${point >= 70}">
	 		<b>당신의 학점은 C입니다.</b>
	 	</c:when>
	 	<c:when test="${point >= 60}">
	 		<b>당신의 학점은 D입니다.</b>
	 	</c:when>
	 	<c:otherwise>
	 		<b>당신의 학점은 F입니다.</b>
	 	</c:otherwise>
	 </c:choose>

</body>
</html>