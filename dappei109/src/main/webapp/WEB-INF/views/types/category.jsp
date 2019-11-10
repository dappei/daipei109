<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='css/styles.css' type="text/css" />
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div style="text-align: center">
		<h1>分類查詢</h1>
		<c:forEach items="${categoryList}" var="category" >
			<a href='products/${category.categoryId}'>${category.categoryName}</a>
		</c:forEach>
	</div>
</body>
</html>