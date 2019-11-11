<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<html lang="UTF-8">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Event</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/top.jsp" />
	<br><br><br>
		
		<div class="container">
			<img src="<c:url value='/getEventPicture/${event.eventId}'/>" width=100% height=100%/>
		</div>
	
	<main id="main" class="container">

	<div class="row" style="background-color:#F0F0F0">
		<div class="col-md-8 blog-main">
			<br><br>
			<h3 class="pb-3 mb-2 font-weight-bold">${event.eventName}</h3>
			<p class="font-weight-bold"><img src="${pageContext.request.contextPath}/image/calendar.png" width="40" height="40">活動時間</p>
			<p>${event.eventStartTime}~${event.eventEndTime}
			<p class="font-weight-bold"><img src="${pageContext.request.contextPath}/image/place.jpg" width="40" height="40">活動地點</p>
			<p>${event.location}
		</div>
		<aside class="col-md-4">
			<br>
			<div class="container m-22" style="width: 200px; height: 300px;background-color: #DDDDDD"><br>
				<p class="text-center font-weight-bold">${event.eventName}</p>
				<p class="text-center">${event.eventStartTime}</p>
				<p class="text-center">NT$ ${event.price}</p>
				<p class="text-center">剩下數量:${event.maxPeople}</p>
				<FORM  class="text-center" 
				 action="<c:url value='buyEvent' />" 
				method="POST">
                               	 購買數量:
	               <select name='qty'>
	                    <option value="1">1</option>
	                    <option value="2">2</option>
	                    <option value="3">3</option>
	                    <option value="4">4</option>
	                    <option value="5">5</option>
	               </select>
	               <!-- 這些隱藏欄位都會送到後端 -->
	               <Input type='hidden' name='eventId' value='${EventBean.eventId}'>
	               <Input type='hidden' name='eventPic' value='${EventBean.eventPic}'>
	               <Input type='hidden' name='name' value='${EventBean.eventName}'>
	               <Input type='hidden' name='startTime' value='${EventBean.eventStartTime}'>
	               <Input type='hidden' name='location' value='${EventBean.location}'>
	               <Input type='hidden' name='price' value='${EventBean.price}'>
	               <Input type='hidden' name='pageNo' value='${param.pageNo}'><br><br>
	               <Input type='submit' class="btn btn-primary btn-block btn-arrow-right" value='報名'>
	       		</FORM>
				<div class="container">
				
				</div>
			</div>
		</aside>
			
		<div class="col-12 blog-main">
			<h5 class="pb-3 mb-4 border-bottom">活動介紹</h5>
			<div>
				${event.aboutEvent}
				<h5 class="pb-3 border-bottom">活動地圖</h5>
				<iframe class="container" width="600" height="450" frameborder="0" style="border:0" 
				      src="https://www.google.com/maps/embed/v1/place?key=AIzaSyBTbNH68e_IKHysXjR2EkcfnAdCVjY6eKA&q=${event.location}" 
				      allowfullscreen>
				</iframe>				
			</div>
			<!-- /.blog-post -->
		</div>
		<!-- /.blog-main -->
	</div>
	<!-- /.row --> </main>
	<!-- /.container -->
	<br><br><br>

</body>
</html>