<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Begin Content -->
<div class="row">
	<div class="col-md-12 text-center">
		<ol class="breadcrumb">
			<li class="breadcrumb-item active">單位管理</li>
		</ol>
	</div>

	<div class="col-md-12">

		<div class="card">
			<div class="card-body">
				<form id="organizationForm">
					<div class="form-row">
						<div class="col-auto">
							<div class="input-group">
								<div class="input-group-addon">新增單位</div>
								<input type="text" class="form-control" id="addOrganization"
									required>
							</div>
						</div>
						<div class="col-auto">
							<button class="btn btn-primary marginButton">新增</button>
						</div>
					</div>
				</form>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table id="organizationTable"
						class="table table-bordered table-hover dataTable"
						style="width: 100%;">
						<thead>
							<tr>
								<td>單位代碼</td>
								<td>單位名稱</td>
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



<!-- update Modal Start -->
<button id="updateButton" type="button" class="btn btn-primary"
	data-toggle="modal" data-target="#updateModal"></button>
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
	aria-labelledby="updateMemberLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="updateMemberLabel">編輯單位</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div id="nameCheck"
					class="alert alert-danger alert-dismissible fade show" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="hide"></button>
					<div id="errorMessage">錯誤：姓名不得為空！</div>
				</div>
				<div class="col-md-12">
					<form>
						<div style="display: none;" id="updateId"></div>
						<div class="form-row form-group">
							<label for="name" class="form-control-label">單位名稱:</label> <input
								type="text" class="form-control" id="updateName" required>
						</div>

					</form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary"
					onclick="organization.updateRow()">儲存</button>
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
					onclick="organization.deleteRow()">確定</button>
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
				<p id="messageText">此單位已存在</p>
			</div>
			<div class="modal-footer">
				<div>
					<button type="button" class="btn btn-primary" data-dismiss="modal">確定</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var data = '${organizationList}';
	var form = document.getElementById("organizationForm");
	var count = 0;

	$(document).ready(function() {
		organization.dataList = JSON.parse(data);

		for (key in organization.dataList) {
			organization.renderHtml(organization.dataList[key]);
		}

		var table = $("#organizationTable").DataTable();
	});

	form.addEventListener("submit", function(event) {
		event.preventDefault();
		event.stopPropagation();

		organization.addOrganization();
	}, false);

	var organization = {
		dataList : [],
		init : function() {
			var data = {};
			data.id = 1;
			data.name = "台大";
			organization.dataList.push(data);

			var data = {};
			data.id = 2;
			data.name = "中山";
			organization.dataList.push(data);

			var data = {};
			data.id = 3;
			data.name = "中正";
			organization.dataList.push(data);

			for (key in organization.dataList) {
				organization.renderHtml(organization.dataList[key]);
			}
		},
		validate : function(source, id) {
			var name = "";
			var hasError = false;

			if (source == 'insert') {
				name = $("#addOrganization").val();

				for (key in organization.dataList) {
					var bName = organization.dataList[key].name;

					if (bName == name) {
						showMessage();
						return false;
					}
				}
			} else if (source == 'update') {
				name = $("#updateName").val();

				// 空值檢查
				if (name == "") {
					$("#nameCheck").show();
					$("#errorMessage").html("錯誤：姓名不得為空！")
					hasError = true;

					return false;
				}

				for (key in organization.dataList) {
					var bId = organization.dataList[key].id;
					var bName = organization.dataList[key].name;

					if (bId != id && bName == name) {
						$("#nameCheck").show();
						$("#errorMessage").html("錯誤：已存在此單位名稱！")
						hasError = true;

						return false;
					}
				}

				$("#nameCheck").hide();
			} else {
				// not defined
			}

			return true;
		},
		addOrganization : function() {
			if (!organization.validate('insert')) {
				return;
			}

			var name = $("#addOrganization").val();
			$.ajax({
				url : "organization/addOrganization",
				type : "POST",
				data : {
					organizationName : name
				},
				error : function(e) {

				},
				success : function(data) {
					showOrganization();
				}
			});
		},
		renderHtml : function(organizationObject) {
			var isVisible = organizationObject.isVisible;

			// 不可視則隱藏
			if (!isVisible) {
				return;
			}

			var id = organizationObject.organizationid;
			var name = organizationObject.name;

			var $tr = '<tr id="data-' + id + '">';
			var $td = '<td id="td-' + id + '">';
			$td += id + '</td>';
			$td += '<td>' + name + '</td>';

			$td += '<td>';
			$td += '<button class="btn-warning marginButton" onclick="showUpdateRow(this)">編輯</button> ';
			$td += '<button class="btn-danger marginButton" onclick="showDeleteRow(this)">刪除</button>';
			$td += '</td>';
			$tr += $td + '</tr>';

			$("#organizationTable tbody").append($tr);
		},
		updateRow : function() {
			var id = $("#updateId").val();
			var name = $("#updateName").val();

			if (!organization.validate('update', id)) {
				return false;
			}

			$('#updateModal').modal("hide");

			$.ajax({
				url : "organization/updateOrganization",
				type : "POST",
				data : {
					organizationId : id,
					organizationName : name
				},
				error : function(e) {

				},
				success : function(data) {
					showOrganization();
				}
			});
		},
		deleteRow : function() {
			var id = $("#deleteId").val();

			$("#deleteModal").modal("hide");

			$.ajax({
				url : "organization/deleteOrganization",
				type : "POST",
				data : {
					organizationId : id
				},
				error : function(e) {

				},
				success : function(data) {
					showOrganization();
				}
			});
		}
	};

	function showMessage() {
		var name = $("#addOrganization").val();
		var text = '此單位(' + name + ')已存在！';
		$("#messageText").html(text);

		$("#messageButton").trigger("click");
	}

	function showUpdateRow(target) {
		var organizationRow = $(target).parent().parent();
		var id = organizationRow.find('td:nth-child(1)').html();
		var name = organizationRow.find('td:nth-child(2)').html();
		$("#updateId").val(id);
		$("#updateName").val(name);

		// 驗證用提示
		$("#nameCheck").hide();

		$("#updateButton").trigger("click");
	}

	function showDeleteRow(target) {
		var organizationRow = $(target).parent().parent();
		var id = organizationRow.find('td:nth-child(1)').html();
		$("#deleteId").val(id);

		$("#deleteButton").trigger("click");
	}
</script>