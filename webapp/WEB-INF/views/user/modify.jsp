<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${ctx }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="POST" action="${ctx}/user/modify">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${user.name }">

					<label class="block-label" for="email">이메일</label>
					<h3>${user.email }</h3>
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test='${user.gender eq "male" }'>
								<label>여</label> <input type="radio" name="gender" value="Female">
								<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:when>
							<c:otherwise>
								<label>여</label> <input type="radio" name="gender" value="Female" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="male">
							</c:otherwise>
						</c:choose>
					</fieldset>
					<input type="submit" value="수정하기">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>