<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<meta name="description" content="Cat_Fast">
<meta name="keywords" content="Cat_Fas">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- jquery-3.2.1 -->
<script src="/Cat-FAS/resources/js/jquery/jquery-3.2.1.js"></script>

<!-- popper-1.11.0 -->
<script src="/Cat-FAS/resources/js/popper/popper.min.js"></script>

<!-- bootstrap-4.0.0-beta-->
<script src="/Cat-FAS/resources/js/bottstrap/bootstrap.js"></script>
<link href="/Cat-FAS/resources/css/bootstrap/bootstrap.css"
	rel="stylesheet">

<!-- datatable-1.10.15-->
<script src="/Cat-FAS/resources/js/datatable/jquery.dataTables.min.js"></script>
<link href="/Cat-FAS/resources/css/datatable/jquery.dataTables.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="/Cat-FAS/resources/css/font-awesome.css" rel="stylesheet">

<!-- custom scripts style -->
<script src="/Cat-FAS/resources/js/scripts.js"></script>
<link href="/Cat-FAS/resources/css/style.css" rel="stylesheet">

<title>Cat-FAS登入頁</title>
</head>

<body id="loginPage">
	<div class="container-fluid">
		<div class="row justify-content-md-center justify-content-sm-center">
			<div class="col-sm-12">
				<h1 class="text-center">
					<div class="text-light">
						<i class="fa fa-fw fa-bolt"></i> 登入Cat-Fas
					</div>
				</h1>
			</div>
			<div class="col-md-6 col-sm-8 col-xs-12">
				<form id="needs-validation">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<i class="fa fa-fw fa-user"></i>
							</div>
							<input name="username" type="text" class="form-control"
								placeholder="username" required>
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<i class="fa fa-fw fa-key"></i>
							</div>
							<input name="password" type="password" class="form-control"
								placeholder="password" required>
						</div>
					</div>

					<button class="btn btn-success pull-right">
						<i class="fa fa-fw fa-sign-in"></i> 登入
					</button>
				</form>
			</div>
		</div>
	</div>

	<!-- message modal -->
	<button id="messageButton" type="button" class="btn btn-primary hide"
		data-toggle="modal" data-target="#messageModal"></button>

	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">錯誤</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p id="messageText">登入失敗，請檢查帳號密碼是否正確。</p>
				</div>
				<div class="modal-footer">
					<div>
						<button type="button" class="btn btn-primary" data-dismiss="modal">確定</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		(function() {
			"use strict";
			window.addEventListener("load", function() {
				var form = document.getElementById("needs-validation");

				form.addEventListener("submit", function(event) {
					if (form.checkValidity() == false) {
						event.preventDefault();
						event.stopPropagation();
					} else {
						event.preventDefault();

						login();
					}

					form.classList.add("was-validated");
				}, false);
			}, false);
		}());

		// 決定登入角色
		function login() {
			var userName = $("input[name=username]").val();
			var password = $("input[name=password]").val();

			$.ajax({
				url : "login/login",
				data : {
					userName : userName,
					password : password
				},
				type : "POST",
				error : function(e) {

				},
				success : function(data) {
					var result = data;

					if (result && result == 'true') {
						window.location.href = "login/showMainView";
					} else {
						$("#messageButton").trigger("click");
					}
				}
			});
		}

		function redirectPage() {
			$.ajax({
				url : "login/showMainView",
				type : "POST",
				error : function(e) {

				},
				success : function(data) {
					console.log(data);
				}
			});
		}
	</script>
</body>

</html>