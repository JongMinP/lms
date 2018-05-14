<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>lms</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/admin/rent.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/admin/include/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>타이틀</th>
						<th>카테고리</th>
						<th>대여일</th>
						<th>반납일</th>
						<th>빌린사람</th>
					</tr>
					
				<c:forEach items="${rentPage.content }" var="rent" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td>${rent.item.title }</td>
						<td>${rent.item.category.title }</td>
						<td>${rent.rentDate }</td>
						<td>${rent.returnDate}</td>
						<td>${rent.user.name }</td>
					</tr>
				</c:forEach>	
				</table>
				<div class="pager">
					
					<ul>
						<c:choose>
							<c:when test="${pager.prev }">
								<li style="color: black;"><a
									href="${ctx}/admin/rent?page=${(pager.blockPage-1) * pager.pageSize}&kwd=${kwd}">◀</a></li>
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
									<li><a href="${ctx }/admin/rent?page=${page-1 }&kwd=${kwd}">${page }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${pager.next }">
								<li style="color: black;"><a
									href="${ctx}/admin/rent?page=${pager.blockPage * pager.pageSize + 1  }&kwd=${kwd}">▶</a></li>
							</c:when>
							<c:otherwise>
								<li style="color: #EAEAEA;">▶</li>
							</c:otherwise>
						</c:choose>
					</ul>
					
				</div>	
			</div>
			<c:import url="/WEB-INF/views/admin/include/navigation.jsp" >
				<c:param name="menu" value="rent" />
			</c:import>
		</div>
	</div>
</body>
</html>