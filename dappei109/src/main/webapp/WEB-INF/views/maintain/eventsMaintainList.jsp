<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動資料</title>
<style>
	table {
	  border-collapse: collapse;
	  width: 100%;
	}
	th, td {
	  text-align: center;
	  padding: 8px;
	  font-family: 微軟正黑體;
	}
	tr:nth-child(even) {background-color: #f2f2f2;}
	input[type=submit] {
	   background-color: #46a3ff;
	   color: white;
	   font-family: 微軟正黑體;
	   font-size: 14px;
	   padding: 8px 15px;
	   border: none;
	   border-radius: 4px;
	   cursor: pointer;
	}
	input[type=submit]:hover {
	   background-color: #84c1ff;
	}
	input[type=button] {
	   background-color: #46a3ff;
	   color: white;
	   font-family: 微軟正黑體;
	   font-size: 20px;
	   width:100%;
	   margin:10px 0px;
	   padding: 12px 20px;
	   border: none;
	   border-radius: 4px;
	   cursor: pointer;
	}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/top.jsp" />
	<br><br><br>
	<div class="container" align="center">
		<h2>活動資料</h2><br>
		<div class="form-inline my-2 my-lg-0 "><a class="btn btn-secondary my-2 my-sm-0" href="pastevents">已結束活動</a></div><br>
		<table border="1" >
		<tr><th>活動圖片<th>活動編號<th>活動名稱<th>活動開始時間<th>活動結束時間<th>數量<th>更新<th>結束
		<c:forEach items="${events}" var="event"  >
			<tr><td><img src="<c:url value='/getEventPicture/${event.eventId}'/>" width="150" height="100"/>
			<td>${event.eventId}
			<td>${event.eventName}
			<td>${event.eventStartTime}
			<td>${event.eventEndTime}
			<td>${event.maxPeople}
			<td><a class="btn btn-secondary my-2 my-sm-0" href="update/${event.eventId}">修改</a>
			<td><a class="btn btn-secondary my-2 my-sm-0" href="close/${event.eventId}">結束</a>
		</c:forEach>
		</table>		
	</div><br>
	<div class="container">                 
		<ul class="pagination list-inline mx-auto justify-content-center">
		 <li class="page-item"><c:if test="${pageNo > 1}"><a class="page-link" href="<spring:url value='/events/maintain?pageNo=${pageNo-1}'/>">&lt;&lt;Previous</a></c:if></li>
		 <c:if test="${pageNo == 1}"><li class="page-item active"><a class="page-link" href="<spring:url value='/events/maintain?pageNo=1' />">1</a></li></c:if>
		 <li class="page-item"><a class="page-link" href="<spring:url value='/events/maintain?pageNo=1' />">1</a></li>
		 <c:if test="${pageNo == 2}"><li class="page-item active"><a class="page-link" href="<spring:url value='/events/maintain?pageNo=2' />">2</a></li></c:if>
		 <li class="page-item"><a class="page-link" href="<spring:url value='/events/maintain?pageNo=2' />">2</a></li>
		 <li class="page-item"><c:if test="${pageNo != totalPages}"><a class="page-link" href="<spring:url value='/events/maintain?pageNo=${pageNo+1}' />">Next&gt;&gt;</a></c:if></li>
		</ul>
	  </div>
	<br><br>
</body>
</html>