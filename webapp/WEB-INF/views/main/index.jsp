<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>LMS</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${ctx}/assets/css/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${ctx }/assets/js/jquery/jquery-1.9.0.js"></script>	
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript"
	src="${ctx }/assets/js/jquery/jquery.pagenavigator.js"></script>	

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${ctx }" method="get">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>타이틀</th>
						<th>카테고리</th>
						<th>예약/대여</th>
					</tr>
					
					
				<c:forEach items="${itemPage.content}" var="item" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td>${item.title}</td>
						<td>${item.category.title }</td>
						<td>
							<c:choose>
							<c:when test="${'ABLE' eq item.status  }">
								<a href="${ctx }/rent?no=${item.no}" class="btn">대여</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/reservation?no=${item.no}" class="btn">예약</a>
							</c:otherwise>
							
							</c:choose>
						</td>
					</tr>
				</c:forEach>		
					
				</table>
				
			
				
				
				<div class="pager">
					
					<ul>
						<c:choose>
							<c:when test="${pager.prev }">
								<li style="color: black;"><a
									href="${ctx}?page=${(pager.blockPage-1) * pager.pageSize}&kwd=${kwd}">◀</a></li>
							</c:when>
							<c:otherwise>
								<li style="color: #EAEAEA;">◀</li>
							</c:otherwise>
						</c:choose>

						<c:forEach begin="${pager.pageStart }" end="${pager.pageStart + pager.pageSize - 1 }" var="page">  
							<c:choose>
								<c:when test="${pager.pageEnd < page }">
									<li style="color: #EAEAEA;" >${page }</li>
								</c:when>
								<c:when test="${pager.page eq page }">
									<li class="selected">${page }</li>
								</c:when>
								<c:otherwise>
									<li><a href="${ctx}?page=${page-1 }&kwd=${kwd}">${page }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${pager.next }">
								<li style="color: black;"><a
									href="${ctx}?page=${pager.blockPage * pager.pageSize + 1  }&kwd=${kwd}">▶</a></li>
							</c:when>
							<c:otherwise>
								<li style="color: #EAEAEA;">▶</li>
							</c:otherwise>
						</c:choose>
					</ul>
					
				</div>				
				
		
				<div class="bottom">
				</div>
			</div>
			<div id="dialog-message" title="" style="display: none">
				<p></p>
			</div>
		</div>
	
<%-- 		<c:import url="/WEB-INF/views/include/footer.jsp" /> --%>
	</div>
			
	
<script type="text/javascript">
$(function(){
	
	var messageBox = function(title, message) {

		$("#dialog-message").attr("title", title);
		$("#dialog-message p").text(message);

		$("#dialog-message").dialog({
			modal : true,
			buttons : {
				"확인" : function() {
					$(this).dialog("close");
				}
			},
			close : function() {
				location.replace(self.location); 
			}
		});

	};
	
	 var result = '${msg}';	 
	 
	 if(result == "rent"){
		 messageBox("대여 불가", "대여가 불가능한 상품입니다.");
	 }else if(result == "reservation"){
		 messageBox("예약 불가", "예약이 불가능한 상품입니다.");
	 }
	 
});
</script>
	
</body>
</html>