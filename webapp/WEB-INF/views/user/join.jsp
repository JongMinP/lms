<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${ctx }/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="${ctx}/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script type="text/javascript">

$(function() {
	$("#check-button").click(function() {
		
		console.log("누르니");
		var email = $("#email").val();
		console.log(email);
		if (email == "") {
			return;
		}
		// 데이터 타입 꼭 들어가야 된다.
		$.ajax({
			url : "${ctx}/api/user/checkemail?email=" + email,
			type : "get",
			data : "",
			dataType : "json",
			success : function(response) {
								console.log(response);				
				if (response.result != "success") {
					console.log(response.message);
					return
				}

				if (response.data == "exist") {
					alert("이미 사용중인 이메일 입니다.");
					$("#email").val("").focus();
					return;
				}

				$("#check-image").show();
				$("#check-button").hide();

			},
			error : function(xhr, status, e) {
				console.error(status + ":" + e);
				// 개발단계에서만 

			}
		});

	});

	
	
	
	$("#email").change(function() {
		$("#img-check").hide();
		$("#btn-checkemail").show();
	});

});
</script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="user">
				<form:form
					modelAttribute="user"
					id="join-form"
					name="joinForm"
					method="post"
					action="${ctx }/user/join">
					
					<label class="block-label" for="name"><spring:message code="name" text="이름"/></label>
					<input id="name" name="name" type="text" value="${user.name }">


					<label class="block-label" for="email">이메일</label>
					<form:input path="email" id="email" />
					<img id="check-image" src="${pageContext.request.contextPath }/assets/images/email-check.png" style="display:none"/>
					<input id="check-button" type="button" value="중복체크" style="display:;">
					<p style="margin:0; padding:0; color:red; text-align:left">
						<form:errors path="email" />		
					</p>
					
					<label class="block-label">패스워드</label>
					<form:password path="password" id = "password" />
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="Female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>