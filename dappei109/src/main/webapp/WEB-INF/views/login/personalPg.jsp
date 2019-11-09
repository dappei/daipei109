<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="TW">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${currentUser.username}</title>

<!-- Font awesome -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- SmartMenus jQuery Bootstrap Addon CSS -->
<link href="css/jquery.smartmenus.bootstrap.css" rel="stylesheet">
<!-- Product view slider -->
<link rel="stylesheet" type="text/css" href="css/jquery.simpleLens.css">
<!-- slick slider -->
<link rel="stylesheet" type="text/css" href="css/slick.css">
<!-- price picker slider -->
<link rel="stylesheet" type="text/css" href="css/nouislider.css">
<!-- Theme color -->
<link id="switcher" href="css/theme-color/default-theme.css"
	rel="stylesheet">
<!-- <link id="switcher" href="css/theme-color/bridge-theme.css" rel="stylesheet"> -->
<!-- Top Slider CSS -->
<link href="css/sequence-theme.modern-slide-in.css" rel="stylesheet"
	media="all">

<!-- Main style sheet -->
<link href="css/style.css" rel="stylesheet">

<!-- Google Font -->
<link href='https://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Raleway'
	rel='stylesheet' type='text/css'>


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<!-- wpf loader Two -->
	<div id="wpf-loader-two">
		<div class="wpf-loader-two-inner">
			<span>載入中</span>
		</div>
	</div>
	<!-- / wpf loader Two -->
	<!-- SCROLL TOP BUTTON -->
	<a class="scrollToTop" href="#"><i class="fa fa-chevron-up"></i></a>
	<!-- END SCROLL TOP BUTTON -->
	<!-- Start header section -->
	<header id="aa-header">
		<!-- start header top  -->
		<div class="aa-header-top">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="aa-header-top-area">
							<!-- start header top left -->
							<div class="aa-header-top-left">
								<!-- start language -->
								<div class="aa-language">
									<div class="dropdown">
										<a class="btn dropdown-toggle" href="#" type="button"
											id="dropdownMenu1" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="true"> <img
											src="img/flag/english.jpg" alt="english flag">英文 <span
											class="caret"></span>
										</a>
										<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
											<li><a href="#"><img src="img/flag/french.jpg"
													alt="">德國</a></li>
											<li><a href="#"><img src="img/flag/english.jpg"
													alt="">英國</a></li>
										</ul>
									</div>
								</div>
								<!-- / language -->

								<!-- start currency -->
								<div class="aa-currency">
									<div class="dropdown">
										<a class="btn dropdown-toggle" href="#" type="button"
											id="dropdownMenu1" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="true"> <i
											class="fa fa-usd"></i>美金 <span class="caret"></span>
										</a>
										<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
											<li><a href="#"><i class="fa fa-euro"></i>歐元</a></li>
											<li><a href="#"><i class="fa fa-jpy"></i>日元</a></li>
										</ul>
									</div>
								</div>
								<!-- / currency -->
								<!-- start cellphone -->
								<div class="cellphone hidden-xs">
									<p>
										<span class="fa fa-phone"></span>02-6631-6666
									</p>
								</div>
								<!-- / cellphone -->
							</div>
							<!-- / header top left -->
							<div class="aa-header-top-right">
								<ul class="aa-head-top-nav-right">
									<li class="hidden-xs"><a href="wishlist.html">公告</a></li>
									<li class="hidden-xs"><a href="cart.html">購物車</a></li>
									<li><a href="members/add">註冊</a></li>
									<li class="hidden-xs"><a href="login">登入</a></li>
									<li class="hidden-xs"><a href="logout">登出</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



	</header>
	<!-- / header section -->
	<div style="text-align:center">
<img class="cover-image" src="https://images.dappei.com/default/user_profile_cover/large/cover.jpg?timestamp=1572413671" alt="Cover">
	</div>
	<ul class="nav nav-tabs aa-products-tab">
		<li class="active"><a href="#men" data-toggle="tab">個人資料</a></li>
		<li><a href="" data-toggle="tab">我的穿搭</a></li>
		<li><a href="" data-toggle="tab">我的活動</a></li>
		<li><a href="" data-toggle="tab">訂單查詢</a></li>
		<li><a href="" data-toggle="tab">問題回報</a></li>
	</ul>


	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.js"></script>
	<!-- SmartMenus jQuery plugin -->
	<script type="text/javascript" src="js/jquery.smartmenus.js"></script>
	<!-- SmartMenus jQuery Bootstrap Addon -->
	<script type="text/javascript" src="js/jquery.smartmenus.bootstrap.js"></script>
	<!-- To Slider JS -->
	<script src="js/sequence.js"></script>
	<script src="js/sequence-theme.modern-slide-in.js"></script>
	<!-- Product view slider -->
	<script type="text/javascript" src="js/jquery.simpleGallery.js"></script>
	<script type="text/javascript" src="js/jquery.simpleLens.js"></script>
	<!-- slick slider -->
	<script type="text/javascript" src="js/slick.js"></script>
	<!-- Price picker slider -->
	<script type="text/javascript" src="js/nouislider.js"></script>
	<!-- Custom js -->
	<script src="js/custom.js"></script>

</body>
</html>
