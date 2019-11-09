<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Bootstrap core CSS -->
<link href='${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.css' rel="stylesheet" type='text/css'>

	<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
		<a class="navbar-brand" href=" ">Dappei</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
				<a class="nav-link" href="index">Home<span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">購物</a></li>
				<li class="nav-item">
					<c:if test="${ funcName != 'SHO' }"><a class="nav-link" href="events">活動</a></c:if>
					<c:if test="${ funcName == 'SHO' }"><a class="nav-link">活動</a></c:if>
					</li>
				<li class="nav-item"><a class="nav-link" href="#">部落格</a></li>
				<li class="nav-item dropdown">
        		  <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">後台</a>
        			<div class="dropdown-menu" aria-labelledby="dropdown01">
          			<a class="dropdown-item" href="events/add">新增活動</a>
          			<a class="dropdown-item" href="events/maintain">活動管理</a>
        			</div>
      			</li>

			</ul>
			<div class="form-inline my-2 my-lg-0">
				<c:if test="${empty LoginOK}"><a class="btn btn-secondary my-2 my-sm-0" href="login">登入</a></c:if>
				<c:if test="${ ! empty LoginOK }"><a class="btn btn-secondary my-2 my-sm-0" href="logout">登出 </a></c:if>
			</div>
		</div>
	</nav>
