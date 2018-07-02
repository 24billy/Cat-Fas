<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Cat-Fas</title>

<meta charset="UTF-8">
<meta name="description" content="Cat_Fast">
<meta name="keywords" content="Cat">
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
</head>

<body>
	<!-- BEGIN navbar -->
	<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
		<a class="navbar-brand" href="main_basic">CAT-FAS</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link"
					onclick="showDataManagement()"> <span class="cht-lan">資料管理</span>
						<span class="en-lan" style="display: none;">Data Management</span>
				</a></li>
			</ul>
			<button class="btn btn-outline-success my-2 my-sm-0"
				onclick="logout()">${userName}
				<span class="cht-lan">登出</span> <span class="en-lan"
					style="display: none;">Logout</span>
			</button>
			<button class="btn btn-outline-success my-2 my-sm-0"
				onclick="switchLan()">
				<div class="cht-lan">En</div>
				<div class="en-lan" style="display: none;">中</div>
			</button>
		</div>
	</nav>
	<!-- End navbar -->

	<!-- Begin Content -->
	<div class="container" id="content">
		<div class="row">
			<div class="col col-md-6">
				<div class="card text-center">
					<div class="card-header text-white bg-secondary ">
						<h4>
							<span class="cht-lan">網站說明</span> <span class="en-lan"
								style="display: none;">Information</span>
						</h4>
					</div>
					<div class="card-body">
						<p class="card-text">
							<span class="cht-lan">中風患者重要功能測驗組。</span> <span class="en-lan"
								style="display: none;">Simultaneously assess 4 functions
								in patients with stroke, including motor functions of upper and
								lower extremities, postural control, and basic activities of
								daily living</span>
						</p>
					</div>
				</div>
			</div>

			<br>

			<div class="col col-md-6">
				<div class="card text-center">
					<div class="card-header text-white bg-secondary ">
						<h4>
							<span class="cht-lan">資料管理</span> <span class="en-lan"
								style="display: none;">Data Management</span>
						</h4>
					</div>
					<div class="card-body">
						<p class="card-text">
							<span class="cht-lan">新增、編輯、刪除受試者。進行測驗、觀看結果。</span> <span
								class="en-lan" style="display: none;">Add, edit, delete, administer, and review data</span>
						</p>
						<button onclick="showDataManagement()"
							class="btn btn-primary marginButton">
							<span class="cht-lan">進入</span> <span class="en-lan"
								style="display: none;">Enter</span>
						</button>
					</div>
				</div>
			</div>
		</div>


	</div>
	<!-- Begin Content -->

	<!-- BEGIN footer -->
	<nav class="navbar navbar-dark bg-dark navbar-fixed-bottom hidden-sm"
		role="navigation">
		<div class="container text-center">
			<h5 style="color: white;">Copyright (c) 2017 Billy Shih All
				Rights Reserved.</h5>
		</div>
	</nav>
	<!-- END footer -->
</body>
<script>
	
</script>
</html>