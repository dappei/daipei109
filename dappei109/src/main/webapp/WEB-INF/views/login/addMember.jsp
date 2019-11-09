<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<style type="text/css">
fieldset {
	border: 1px solid rgb(255, 232, 57);
	width: 400px;
	margin: auto;
}
</style>
<title>會員註冊</title>
</head>

<body>
	<section>
		<div class="container">
			<h1 style="text-align: center">會員註冊</h1>
		</div>
	</section>
	<hr
		style="height: 1px; border: none; color: #333; background-color: #333;">
	<section class="container">
		<form:form method="POST" modelAttribute="memberBean"
			class="form-horizontal" enctype="multipart/form-data">
			<fieldset>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="account">帳號</label>
					<div class="col-lg-10">
						<form:input id="account" path="account" type="text"
							class="form-control is-valid" />
						<!-- 						<div class="valid-feedback">Looks good!</div> -->
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="password">密碼</label>
					<div class="col-lg-10">
						<form:input id="password" path="password" type="password"
							class="form-control is-valid" />
						<!-- 						<div class="valid-feedback">Looks good!</div> -->
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2"
						for="confirmPassword">確認密碼</label>
					<div class="col-lg-10">
						<form:input id="confirmPassword" path="confirmPassword"
							type="password" class="form-control is-valid" />
						<!-- 						<div class="valid-feedback">Looks good!</div> -->
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="username">暱稱</label>
					<div class="col-lg-10">
						<form:input id="username" path="username" type="text"
							class="form-control is-valid" />
						<!-- 						<div class="valid-feedback">Looks good!</div> -->
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="birthday">生日</label>
					<div class="col-lg-10">
						<form:input id="birthday" path="birthday" type="text"
							class="form-control is-valid" />
						<!-- 						<div class="valid-feedback">Looks good!</div> -->
					</div>
				</div>
<!-- 				<div class="form-group"> -->
<!-- 					<label class="control-label col-lg-2 col-lg-2" for='gender'>性別</label> -->
<!-- 					<div class="form-check form-check-inline"> -->
<!-- 						<input class="form-check-input" type="radio" name="male" id="male" -->
<!-- 							value="male" path="gender"> <label -->
<!-- 							class="form-check-label" for="male">男</label> -->
<!-- 					</div> -->
<!-- 					<div class="form-check form-check-inline"> -->
<!-- 						<input class="form-check-input" type="radio" name="female" -->
<!-- 							id="female" value="female" path="gender"> <label -->
<!-- 							class="form-check-label" for="female">女</label> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="email">Email</label>
					<div class="col-lg-10">
						<form:input id="email" path="email" type="email"
							class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="phone">電話</label>
					<div class="col-lg-10">
						<form:input id="phone" path="phone" type="text"
							class="form-control" />
						<small id="emailHelp" class="form-text text-muted">We'll
							never share your phone with anyone else.</small>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10">
						<button type="submit" class="btn btn-primary">送出</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>
</body>
</html>
