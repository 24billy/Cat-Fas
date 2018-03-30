<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Begin Content -->
<div class="row">
	<div class="col-md-12 text-center">
		<ol class="breadcrumb">
			<li class="breadcrumb-item active">資料管理</li>
			<!--<li class="breadcrumb-item active">測驗歷程管理</li>-->
		</ol>
	</div>

	<div class="col-md-12">
		<div class="card">
			<div class="card-body">
				<button class="btn btn-primary" onclick="addExaminee()">新增受試者</button>
				<button class="btn btn-info" onclick="exportData()">資料匯出</button>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table id="testDataTable" class="table table-bordered table-hover"
						style="width: 100%;">
						<thead>
							<tr>
								<td>序號</td>
								<td>病歷號碼</td>
								<td>姓名</td>
								<td>性別</td>
								<td>生日</td>
								<td>進行測驗</td>
								<td>功能</td>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Begin Content -->

<!-- insert Modal Start -->
<button class="btn btn-primary" data-toggle="modal"
	data-target="#insertTestData" id="insertButton">新增訪員</button>

<div class="modal fade" id="insertTestData" tabindex="-1" role="dialog"
	aria-labelledby="insertTestDataLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="insertTestDataLabel">新增資料</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="row">
					<div id="" style="display: none;"></div>
					<div class="form-group col-md-6">
						<label for="medicalNumber" class="form-control-label">病歷號碼:</label>
						<input type="text" class="form-control" id="medicalNumber">
					</div>
					<div class="form-group col-md-6">
						<label for="name" class="form-control-label">姓名:</label> <input
							type="text" class="form-control" id="name">

					</div>
					<div class="form-group col-md-6">
						<label for="gender" class="form-control-label">性別:</label> <select
							class="form-control" id="gender">
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</div>
					<div class="form-group col-md-6">
						<label for="birthday" class="form-control-label">生日:</label> <input
							type="text" class="form-control" id="birthday" placeholder="(格式:YYYY-MM-DD)">

					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary"
					onclick="testData.insert()">儲存</button>
			</div>
		</div>
	</div>
</div>
<!-- insert Modal End -->

<!-- update Modal Start -->
<button id="updateButton" type="button" class="btn btn-primary hide"
	data-toggle="modal" data-target="#updateTestData"></button>
<div class="modal fade" id="updateTestData" tabindex="-1" role="dialog"
	aria-labelledby="updateTestDataLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="updateTestDataLabel">編輯資料</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="row">
					<div class="form-group col-md-6">
						<label for="updateMedicalNumber" class="form-control-label">病歷號碼:</label>
						<input type="text" class="form-control" id="updateMedicalNumber">
					</div>
					<div class="form-group col-md-6">
						<label for="updateName" class="form-control-label">姓名:</label> <input
							type="text" class="form-control" id="updateName">

					</div>
					<div class="form-group col-md-6">
						<label for="updateGender" class="form-control-label">性別:</label> <select
							class="form-control" id="updateGender">
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</div>
					<div class="form-group col-md-6">
						<label for="updateBirthday" class="form-control-label">生日:</label>
						<input type="text" class="form-control" id="updateBirthday" placeholder="(格式:YYYY-MM-DD)">

					</div>
					<div style="display: none;" id="updateId"></div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary"
					onclick="testData.update()">儲存</button>
			</div>
		</div>
	</div>
</div>
<!-- insert Modal End -->

<!-- delete modal -->
<button id="deleteButton" type="button" class="btn btn-primary hide"
	data-toggle="modal" data-target="#deleteModal"></button>

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">即將刪除</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>確定要刪除此資料？</p>
				<div style="display: none;" id="deleteId"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary"
					onclick="testData.deleteData()">確定</button>
			</div>
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
				<div id="messageText"></div>
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
	var data = '${subjectList}';

	$(document).ready(function() {
		testData.dataList = JSON.parse(data);

		for (key in testData.dataList) {
			testData.renderHtml(testData.dataList[key]);
		}

		//testData.init();

		var table = $("#testDataTable").DataTable({
			responsive : true
		});
	});

	var currentId = 4;
	var testData = {
		dataList : [],
		init : function() {
			var data = {};
			data.subjectId = 1;
			data.medicalNumber = "101";
			data.name = "John";
			data.gender = "男";
			data.birthday = "1990-11-11";
			testData.dataList.push(data);

			var data = {};
			data.subjectId = 2;
			data.medicalNumber = "102";
			data.name = "Allen";
			data.gender = "男";
			data.birthday = "1990-11-12";
			testData.dataList.push(data);

			var data = {};
			data.subjectId = 3;
			data.medicalNumber = "103";
			data.name = "Billy";
			data.gender = "男";
			data.birthday = "1990-11-13";
			testData.dataList.push(data);

			for (key in testData.dataList) {
				testData.renderHtml(testData.dataList[key]);
			}
		},
		renderHtml : function(testObject) {
			var id = testObject.subjectId;
			var medicalNumber = testObject.medicalNumber;
			var name = testObject.subjectName;
			var gender = testObject.gender;
			var birthday = testObject.birthday;

			var $tr = '<tr id="data-' + id + '">';
			var $td = '<td>' + id + '</td>';
			$td += '<td>' + medicalNumber + '</td>';
			$td += '<td>' + name + '</td>';
			$td += '<td>' + gender + '</td>';
			$td += '<td>' + birthday + '</td>';
			$td += '<td>';
			$td += '<button class="btn-success marginButton" onclick="showProgressManagement('
					+ id + ')">開始</button> ';
			$td += '</td>';
			$td += '<td>';
			$td += '<button class="btn-warning marginButton" onclick="showUpdateRow(this)">編輯</button> ';
			$td += '<button class="btn-danger marginButton" onclick="showDeleteRow(this)">刪除</button>';
			$td += '</td>';
			$tr += $td + '</tr>';

			$("#testDataTable tbody").append($tr);
		},
		insert : function() {
			var medicalNumber = $("#medicalNumber").val();
			var name = $("#name").val();
			var gender = $("#gender").val();
			var birthday = $("#birthday").val();

			if (!testData.validate("insert")) {
				return false;
			}
			
			$.ajax({
				url : "dataManagement/addSubject",
				type : "POST",
				data : {
					medicalNumber : medicalNumber,
					name : name,
					gender : gender,
					birthday : birthday
				},
				error : function(e) {

				},
				success : function(data) {
					showDataManagement();
				}
			});

			$("#insertTestData").modal("hide");
		},
		update : function() {
			var updateId = $("#updateId").val();
			var updateMedicalNumber = $("#updateMedicalNumber").val();
			var updateName = $("#updateName").val();
			var updateGender = $("#updateGender").val();
			var updateBirthday = $("#updateBirthday").val();

			if (!testData.validate("update")) {
				return false;
			}
			
			$("#updateTestData").modal("hide");

			$.ajax({
				url : "dataManagement/updateSubject",
				type : "POST",
				data : {
					subjectId : updateId,
					medicalNumber : updateMedicalNumber,
					name : updateName,
					gender : updateGender,
					birthday : updateBirthday
				},
				error : function(e) {

				},
				success : function(data) {
					showDataManagement();
				}
			});
		},
		deleteData : function() {
			var id = $("#deleteId").html();

			$("#deleteModal").modal("hide");

			$.ajax({
				url : "dataManagement/deleteSubject",
				type : "POST",
				data : {
					subjectId : id
				},
				error : function(e) {

				},
				success : function(data) {
					showDataManagement();
				}
			});
		},
		validate : function(source) {
			if ("insert" == source) {
				var medicalNumber = $("#medicalNumber").val();
				var name = $("#name").val();
				var errorMessage = "";
				var hasError = false;

				if (medicalNumber == "") {
					errorMessage += "<p>病歷號碼不得為空</p>";
					hasError = true;
				}
				if (name == "") {
					errorMessage += "<p>姓名不得為空</p>";
					hasError = true;
				}

				if (hasError) {
					showMessage(errorMessage);
					return false;
				}
			} else if ("update" == source) {
				var updateMedicalNumber = $("#updateMedicalNumber").val();
				var updateName = $("#updateName").val();
				var errorMessage = "";
				var hasError = false;

				if (updateMedicalNumber == "") {
					errorMessage += "<p>病歷號碼不得為空</p>";
					hasError = true;
				}
				if (updateName == "") {
					errorMessage += "<p>姓名不得為空</p>";
					hasError = true;
				}

				if (hasError) {
					showMessage(errorMessage);
					return false;
				}
			} 

			return true;
		}
	}

	function addExaminee() {
		$("#medicalNumber").val("");
		$("#name").val("");

		$("#insertButton").trigger("click");
	}

	function showUpdateRow(target) {
		var testDataRow = $(target).parent().parent();
		var id = testDataRow.find('td:nth-child(1)').html();
		var medicalNumber = testDataRow.find('td:nth-child(2)').html();
		var name = testDataRow.find('td:nth-child(3)').html();
		var gender = testDataRow.find('td:nth-child(4)').html();
		var birthday = testDataRow.find('td:nth-child(5)').html();

		$("#updateId").val(id);
		$("#updateMedicalNumber").val(medicalNumber);
		$("#updateName").val(name);
		$("#updateGender").val(gender);
		$("#updateBirthday").val(birthday);

		$("#updateButton").trigger("click");
	}

	function showDeleteRow(target) {
		var testDataRow = $(target).parent().parent();
		var id = testDataRow.find('td:nth-child(1)').html();
		$("#deleteId").html(id);

		$("#deleteButton").trigger("click");
	}

	function showMessage(message) {
		$("div#messageText").html(message);
		$('#messageButton').trigger("click");
	}

	function exportData() {
		console.log("匯出Excel");
		/**/
		$('<form action="dataManagement/downloadExcel.do" method="get"></form>')
				.appendTo('body').submit().remove();
		 
	}
</script>