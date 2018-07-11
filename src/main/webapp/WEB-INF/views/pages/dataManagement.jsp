<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Begin Content -->
<div class="row">
	<div class="col-md-12 text-center">
		<ol class="breadcrumb">
			<li class="breadcrumb-item active"><span class="cht-lan">資料管理</span> <span class="en-lan"
									style="display: none;">Data management</span></li>
			<!--<li class="breadcrumb-item active">測驗歷程管理</li>-->
		</ol>
	</div>

	<div class="col-md-12">
		<div class="card">
			<div class="card-body">
				<button class="btn btn-primary" onclick="addExaminee()">
				<span class="cht-lan">新增受試者</span> <span class="en-lan"
									style="display: none;">New subject</span>
				</button>
				<button class="btn btn-info" onclick="exportData()">
				<span class="cht-lan">資料匯出</span> <span class="en-lan"
									style="display: none;">Export data</span>
				</button>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table id="testDataTable" class="table table-bordered table-hover"
						style="width: 100%;">
						<thead>
							<tr>
								<td>
								<span class="cht-lan">序號</span> <span class="en-lan"
									style="display: none;"> ID</span>
								</td>

								<td>
								<span class="cht-lan">病歷號碼</span> <span class="en-lan"
									style="display: none;">Chart Number</span>
								</td>
								
								<td>
								<span class="cht-lan">姓名</span> <span class="en-lan"
									style="display: none;">Full name</span>
								</td>
								
								<td>
								<span class="cht-lan">性別</span> <span class="en-lan"
									style="display: none;">Gender</span>
								</td>
								
								<td>
								<span class="cht-lan">生日</span> <span class="en-lan"
									style="display: none;">Date of birth</span>
								</td>
								
								<td>
								<span class="cht-lan">進行測驗</span> <span class="en-lan"
									style="display: none;"></span>
								</td>
								
								<td>
								<span class="cht-lan">功能</span> <span class="en-lan"
									style="display: none;"></span>
								</td>
								
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
	data-target="#insertTestData" id="insertButton"><span class="cht-lan">新增資料</span> <span class="en-lan"
									style="display: none;">New subject</span></button>

<div class="modal fade" id="insertTestData" tabindex="-1" role="dialog"
	aria-labelledby="insertTestDataLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="insertTestDataLabel"><span class="cht-lan">新增資料</span> <span class="en-lan"
									style="display: none;">New subject</span></h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="row">
					<div id="" style="display: none;"></div>
					<div class="form-group col-md-6">
						<label for="medicalNumber" class="form-control-label">
						<span class="cht-lan">病歷號碼:</span> <span class="en-lan"
									style="display: none;">Chart Number</span>
						</label>
						<input type="text" class="form-control" id="medicalNumber">
					</div>
					<div class="form-group col-md-6">
						<label for="name" class="form-control-label">
						<span class="cht-lan">姓名:</span> <span class="en-lan"
									style="display: none;">Full name</span>
						</label> <input
							type="text" class="form-control" id="name">

					</div>
					<div class="form-group col-md-6">
						<label for="gender" class="form-control-label">
						<span class="cht-lan">性別:</span> <span class="en-lan"
									style="display: none;">Gender:</span>
						</label> <select
							class="form-control" id="gender">
							<option class="cht-lan" value="男">男</option> 
							<option class="cht-lan" value="女">女</option>
							<option class="en-lan" style="display: none;" value="男">male</option> 
							<option class="en-lan" style="display: none;" value="女">female</option>
						</select>
					</div>
					<div class="form-group col-md-6">
						<label for="birthday" class="form-control-label">
						<span class="cht-lan">生日:</span> <span class="en-lan"
									style="display: none;">Date of birth:</span>
						</label> <input
							type="text" class="form-control" id="birthday" placeholder="(格式:YYYY-MM-DD)">

					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">
				<span class="cht-lan">取消</span> <span class="en-lan"
									style="display: none;">Cancel</span>
				</button>
				<button type="button" class="btn btn-primary"
					onclick="testData.insert()">
					<span class="cht-lan">儲存</span> <span class="en-lan"
									style="display: none;">Save</span>
					</button>
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
				<h5 class="modal-title" id="updateTestDataLabel"><span class="cht-lan">編輯資料</span> <span class="en-lan"
									style="display: none;">Edit subject information</span></h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="row">
					<div class="form-group col-md-6">
						<label for="updateMedicalNumber" class="form-control-label">
							<span class="cht-lan">病歷號碼:</span> <span class="en-lan"
									style="display: none;">Chart Number</span>
						</label>
						<input type="text" class="form-control" id="updateMedicalNumber">
					</div>
					<div class="form-group col-md-6">
						<label for="updateName" class="form-control-label">
							<span class="cht-lan">姓名:</span> <span class="en-lan"
									style="display: none;">Full name</span>
						</label> <input
							type="text" class="form-control" id="updateName">

					</div>
					<div class="form-group col-md-6">
						<label for="updateGender" class="form-control-label">
							<span class="cht-lan">性別:</span> <span class="en-lan"
									style="display: none;">Gender:</span>
						</label> <select
							class="form-control" id="updateGender">
							<option class="cht-lan" value="男">男</option> 
							<option class="cht-lan" value="女">女</option>
							<option class="en-lan" style="display: none;" value="male">male</option> 
							<option class="en-lan" style="display: none;" value="female">female</option>
						</select>
					</div>
					<div class="form-group col-md-6">
						<label for="updateBirthday" class="form-control-label">
							<span class="cht-lan">生日:</span> <span class="en-lan"
									style="display: none;">Date of birth:</span>
						</label>
						<input type="text" class="form-control" id="updateBirthday" placeholder="(格式:YYYY-MM-DD)">

					</div>
					<div style="display: none;" id="updateId"></div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">
					<span class="cht-lan">取消</span> <span class="en-lan"
									style="display: none;">Cancel</span>
				</button>
				<button type="button" class="btn btn-primary"
					onclick="testData.update()">
					<span class="cht-lan">確定</span> <span class="en-lan"
									style="display: none;">Save</span>
				</button>
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
				<h5 class="modal-title">
					<span class="cht-lan">即將刪除</span> <span class="en-lan"
									style="display: none;">Delete subject</span>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>
				<span class="cht-lan">確定要刪除此資料？</span> <span class="en-lan"
									style="display: none;">Delete this subject?</span>
				</p>
				<div style="display: none;" id="deleteId"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">
				<span class="cht-lan">取消</span> <span class="en-lan"
									style="display: none;">Cancel</span>
				</button>
				<button type="button" class="btn btn-primary"
					onclick="testData.deleteData()">
					<span class="cht-lan">確定</span> <span class="en-lan"
									style="display: none;">Save</span>
					</button>
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
				<h5 class="modal-title">
				<span class="cht-lan">錯誤</span> <span class="en-lan"
									style="display: none;">Warning</span>
				</h5>
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
					<button type="button" class="btn btn-primary" data-dismiss="modal">
					<span class="cht-lan">確定</span> <span class="en-lan"
									style="display: none;">Confirm</span>
					</button>
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

		var lengMenuStr = '<span class="cht-lan">顯示</span> <span class="en-lan" style="display: none;">Display</span> _MENU_ <span class="cht-lan">筆資料</span> <span class="en-lan" style="display: none;">records per page</span>';
		var zeroRecordsStr = '<span class="cht-lan">查無資料</span> <span class="en-lan" style="display: none;">Nothing found - sorry</span>';
		var infoStr = '<span class="cht-lan">顯示第 _START_ 至 _END_ 筆資料，總共 _TOTAL_ 筆資料</span> <span class="en-lan" style="display: none;">Showing _START_ to _END_ of _TOTAL_</span>';
		var ifoEmptyStr = '<span class="cht-lan">無顯示資料</span> <span class="en-lan" style="display: none;">No records available</span>';
		var searchStr = '<span class="cht-lan">搜尋</span> <span class="en-lan" style="display: none;">Search</span>';
		var perviousStr = '<span class="cht-lan">上一頁</span> <span class="en-lan" style="display: none;">Prev</span>';
		var nextStr = '<span class="cht-lan">下一頁</span> <span class="en-lan" style="display: none;">Next</span>';

		var table = $("#testDataTable").DataTable({
			responsive : true,
			"language" : {
				"lengthMenu" : lengMenuStr,
				"zeroRecords" : zeroRecordsStr,
				"info" : infoStr,
				"infoEmpty" : ifoEmptyStr,
				"infoFiltered" : '(filtered from _MAX_ total records)',
				"search" : searchStr,
				"paginate" : {
					"previous" : perviousStr,
					"next" : nextStr
				}
			}
		})
		
		$('input[type="search"]').on("keyup", function(){
			showCurrentLan();	
		});
		
		showCurrentLan();
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
			if (gender == '男') {
				gender = '<span class="cht-lan">男</span>';
				gender += '<span class="en-lan" style="display: none;">Male</span>';
			} else {
				gender = '<span class="cht-lan">女</span>';
				gender += '<span class="en-lan" style="display: none;">Female</span>';
			}
			
			var birthday = testObject.birthday;

			var $tr = '<tr id="data-' + id + '">';
			var $td = '<td>' + id + '</td>';
			$td += '<td>' + medicalNumber + '</td>';
			$td += '<td>' + name + '</td>';
			$td += '<td>' + gender + '</td>';
			$td += '<td>' + birthday + '</td>';
			$td += '<td>';
			$td += '<button class="btn-success marginButton" onclick="showProgressManagement('
					+ id + ')"><span class="cht-lan">開始</span> <span class="en-lan" style="display: none;">Start</span></button> ';
			$td += '</td>';
			$td += '<td>';
			
			var updateButton = '<span class="cht-lan">編輯</span> <span class="en-lan" style="display: none;">Edit</span>';
			var deleteButton = '<span class="cht-lan">刪除</span> <span class="en-lan" style="display: none;">Delete</span>';
			$td += '<button class="btn-warning marginButton" onclick="showUpdateRow(this)">' + updateButton + '</button> ';
			$td += '<button class="btn-danger marginButton" onclick="showDeleteRow(this)">' + deleteButton + '</button>';
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
			
			if (currentLanguage == 'cht') {
				if (updateGender == '男') {
					updateGender = '男';
				} else {
					updateGender = '女';
				}	
			} else {
				if (updateGender == 'male') {
					updateGender = '男';
				} else {
					updateGender = '女';
				}
			}
			
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
					errorMessage += '<p><span class="cht-lan">病歷號碼不得為空</span>';
					errorMessage += '<span class="en-lan" style="display: none;">ChartNumber cannot be empty</span></p>';

					hasError = true;
				}
				if (name == "") {
					errorMessage += '<p><span class="cht-lan">姓名不得為空</span>';
					errorMessage += '<span class="en-lan" style="display: none;">Name cannot be empty</span></p>';
					
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
					errorMessage += '<p><span class="cht-lan">病歷號碼不得為空</span>';
					errorMessage += '<span class="en-lan" style="display: none;">ChartNumber cannot be empty</span></p>';
					
					hasError = true;
				}
				if (updateName == "") {
					errorMessage += '<p><span class="cht-lan">姓名不得為空</span>';
					errorMessage += '<span class="en-lan" style="display: none;">Name cannot be empty</span></p>';
					
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
		var gender = testDataRow.find('td:nth-child(4) span').html();
		var birthday = testDataRow.find('td:nth-child(5)').html();

		if (currentLanguage == 'cht') {
			if (gender == '男') {
				gender = '男';
			} else {
				gender = '女';
			}	
		} else {
			if (gender == '男') {
				gender = 'male';
			} else {
				gender = 'female';
			}
		}
		
		
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
	
	function reLoadItem() {
		$.ajax({
			url : "item/reloadItem",
			type : "POST",
			error : function(e) {

			},
			success : function(data) {
				console.log(data);
			}
		});
	}
</script>