<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link
	href="https://bootstrap.hexschool.com/docs/4.2/dist/css/bootstrap.css"
	rel="stylesheet">

<title>login</title>
<style>
fieldset {
	border: 1px solid rgb(255, 232, 57);
	width: 400px;
	margin: auto;
}
</style>
<script type="text/javascript">
	document.addEventListener("DOMContentLoaded",function(){
		document.getElementById("account").addEventListener("blur",function(){
			if(this.value != ""){
				document.getElementById("account").className = "form-control is-valid";
			}else{
				document.getElementById("account").className = "form-control is-invalid";
					}
			});
		document.getElementById("password").addEventListener("blur",function(){
			if(this.value != ""){
				document.getElementById("password").className = "form-control is-valid";
			}else{
				document.getElementById("password").className = "form-control is-invalid";
					}
			});
})
</script>
</head>
<body>
	<section>
		<div class="container">
			<h1 style="text-align: center">會員登入</h1>
		</div>
	</section>
	<section class="container">
		<form:form method="POST" modelAttribute="memberBean2"
			class="form-horizontal" enctype="multipart/form-data">
			<fieldset>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="account">帳號</label>
					<div class="col-lg-10">
						<form:input id="account" path="account" type="text"
							class="form-control is-invalid" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="password">密碼</label>
					<div class="col-lg-10">
						<form:input id="password" path="password" type="password"
							class="form-control is-invalid" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10">
						<button type="submit" class="btn btn-primary">登入</button>
					</div>
				</div>
				<div style="color: red">${error}</div>

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
