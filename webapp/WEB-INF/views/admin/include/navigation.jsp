<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>

<div id="navigation">
	<ul>
		<li id = "rentJ"><a href="${pageContext.request.contextPath }/admin">대여 관리</a></li>
		<li id = "reserveJ"><a href="${pageContext.request.contextPath }/admin/reserve">예약 관리</a></li>
	</ul>
</div>

<script>
	$(function() {

		$("#navigation ul li[class='selected']").attr("class", "");
		$("#navigation ul li[id='${param.menu}J']").attr("class", "selected");

	});
</script>

<div id="footer">&nbsp;</div>