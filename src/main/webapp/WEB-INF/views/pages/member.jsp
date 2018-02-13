<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Begin Content -->
<div class="row">
	<div class="col-md-12">
		<ol class="breadcrumb">
			<li class="breadcrumb-item active">訪員管理</li>
		</ol>
	</div>

	<div class="col-md-12">
		<div class="card">
			<div class="card-body">
				<button class="btn btn-primary" onclick="showInsert()">新增訪員</button>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table id="examinerTable" class="table table-bordered table-hover"
						style="width: 100%;">
						<thead>
							<tr>
								<td>代碼</td>
								<td>帳號</td>
								<td>姓名</td>
								<td>所屬單位</td>
								<td>身份</td>
								<td>角色</td>
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
	data-target="#insertMember" id="insertButton">新增訪員</button>

<div class="modal fade" id="insertMember" tabindex="-1" role="dialog"
	aria-labelledby="insertMemberLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="insertMemberLabel">新增訪員</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="row">
					<div id="memberId" style="display: none;"></div>
					<div class="form-group col-md-6">
						<label for="account" class="form-control-label">帳號:</label> <input
							type="text" class="form-control" id="account">
					</div>
					<div class="form-group col-md-6">
						<label for="name" class="form-control-label">姓名:</label> <input
							type="text" class="form-control" id="name">
					</div>
					<div class="form-group col-md-6">
						<label for="organization" class="form-control-label">所屬單位:</label>
						<select class="form-control" id="organization">
							<option>台大</option>
							<option>中山</option>
							<option>台北市政府</option>
						</select>
					</div>

					<div class="form-group col-md-6">
						<label for="identity" class="form-control-label">身份:</label> <input
							type="text" class="form-control" id="identity">
					</div>
					<div class="form-group col-md-6">
						<label for="role" class="form-control-label">角色:</label> <select
							class="form-control" id="role">
							<option>主試者</option>
							<option>管理員</option>
						</select>

					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary"
					onclick="member.insert()">儲存</button>
			</div>
		</div>
	</div>
</div>
<!-- insert Modal End -->

<!-- update Modal Start -->
<button id="updateButton" type="button" class="btn btn-primary hide"
	data-toggle="modal" data-target="#updateMember"></button>
<div class="modal fade" id="updateMember" tabindex="-1" role="dialog"
	aria-labelledby="updateMemberLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="updateMemberLabel">編輯訪員</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="row">

					<div class="form-group col-md-6">
						<div id="updateMemberId" style="display: none;"></div>
						<label for="updateAccount" class="form-control-label">帳號:</label>
						<input type="text" class="form-control" id="updateAccount"
							readonly>
					</div>
					<div class="form-group col-md-6">
						<label for="updateName" class="form-control-label">姓名:</label> <input
							type="text" class="form-control" id="updateName">
					</div>
					<div class="form-group col-md-6">
						<label for="updateOrganization" class="form-control-label">所屬單位:</label>
						<select class="form-control" id="updateOrganization">
							<option>台大</option>
							<option>中山</option>
							<option>中正</option>
							<option>台北市政府</option>
						</select>
					</div>

					<div class="form-group col-md-6">
						<label for="updateIdentity" class="form-control-label">身份:</label>
						<input type="text" class="form-control" id="updateIdentity">
					</div>
					<div class="form-group col-md-6">
						<label for="updateRole" class="form-control-label">角色:</label> <select
							class="form-control" id="updateRole">
							<option>主試者</option>
							<option>管理員</option>
						</select>

					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary"
					onclick="member.update()">儲存</button>
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
					onclick="member.remove()">確定</button>
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
				<p id="messageText"></p>
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
	$(document).ready(function() {
		member.init();

		$("#examinerTable").DataTable({
			responsive : true
		});
	});
	var currentId = 5;
	var member = {
		dataList : [],
		init : function() {
			var data = {};
			data.id = "1";
			data.account = "admin";
			data.name = "管理者";
			data.organization = "台大";
			data.identity = "治療師";
			data.role = "管理者";
			member.dataList.push(data);

			var data = {};
			data.id = "2";
			data.account = "kp";
			data.name = "柯p";
			data.organization = "台北市政府";
			data.identity = "市長";
			data.role = "主試者";
			member.dataList.push(data);

			var data = {};
			data.id = "3";
			data.account = "Billy";
			data.name = "Billy";
			data.organization = "中正";
			data.identity = "畢業生";
			data.role = "管理者";
			member.dataList.push(data);

			var data = {};
			data.id = "4";
			data.account = "Allen";
			data.name = "Allen";
			data.organization = "中正";
			data.identity = "畢業生";
			data.role = "管理者";
			member.dataList.push(data);

			for (key in member.dataList) {
				member.renderHtml(member.dataList[key]);
			}
		},
		renderHtml : function(memberObject) {
			var id = memberObject.id;
			var account = memberObject.account;
			var name = memberObject.name;
			var organization = memberObject.organization;
			var identity = memberObject.identity;
			var role = memberObject.role;

			var $tr = '<tr id="data-' + id + '">';
			var $td = '<td>';
			$td += id + '</td>';
			$td += '<td>' + account + '</td>';
			$td += '<td>' + name + '</td>';
			$td += '<td>' + organization + '</td>';
			$td += '<td>' + identity + '</td>';
			$td += '<td>' + role + '</td>';

			$td += '<td>';
			$td += '<button class="btn-warning marginButton" onclick="showUpdateRow(this)">編輯</button> ';
			$td += '<button class="btn-danger marginButton" onclick="showDeleteRow(this)">刪除</button>';
			$td += '</td>';
			$tr += $td + '</tr>';

			$("#examinerTable tbody").append($tr);
		},
		insert : function() {
			var id = currentId;
			var account = $("#account").val();
			var name = $("#name").val();
			var organization = $("#organization").val();
			var identity = $("#identity").val();
			var role = $("#role").val();

			var memberObject = {};
			memberObject.id = id;
			memberObject.account = account;
			memberObject.name = name;
			memberObject.organization = organization;
			memberObject.identity = identity;
			memberObject.role = role;
			member.dataList.push(memberObject);

			var table = $("#examinerTable").DataTable();
			var node = table.row
					.add(
							[
									'<td>' + currentId + '</td>',
									'<td>' + account + '</td>',
									'<td>' + name + '</td>',
									'<td>' + organization + '</td>',
									'<td>' + identity + '</td>',
									'<td>' + role + '</td>',
									function() {
										var $td = '<td>';
										$td += '<button class="btn-warning marginButton" onclick="showUpdateRow(this)">編輯</button> ';
										$td += '<button class="btn-danger marginButton" onclick="showDeleteRow(this)">刪除</button>';
										$td += '</td>';

										return $td;
									} ]).draw(true).node();

			$(node).attr("id", 'data-' + currentId);
			$("#insertMember").modal("hide");

			currentId++;
		},
		update : function() {
			var id = $("#updateMemberId").val();
			var account = $("#updateAccount").val();
			var name = $("#updateName").val();
			var organization = $("#updateOrganization").val();
			var identity = $("#updateIdentity").val();
			var role = $("#updateIRole").val();

			$("#examinerTable tbody tr#data-" + id).find('td:nth-child(2)')
					.html(account);
			$("#examinerTable tbody tr#data-" + id).find('td:nth-child(3)')
					.html(name);
			$("#examinerTable tbody tr#data-" + id).find('td:nth-child(4)')
					.html(organization);
			$("#examinerTable tbody tr#data-" + id).find('td:nth-child(5)')
					.html(identity);
			$("#examinerTable tbody tr#data-" + id).find('td:nth-child(6)')
					.html(role);

			// update
			for (key in member.dataList) {
				if (id == member.dataList[key].id) {
					member.dataList[key].account = account;
					member.dataList[key].name = name;
					member.dataList[key].organization = organization;
					member.dataList[key].identity = identity;
					member.dataList[key].role = role;

					break;
				}
			}

			$("#updateMember").modal("hide");
		},
		remove : function() {
			var id = $("#deleteId").html();
			var table = $("#examinerTable").DataTable();
			var deleteObject = $("#examinerTable tbody tr#data-" + id);

			// 刪除資料
			for (key in member.dataList) {
				if (id == member.dataList[key].id) {
					var array = member.dataList;
					var index = array.indexOf(member.dataList[key]);
					array.splice(index, 1);

					break;
				}
			}

			table.row(deleteObject).remove().draw();
			$("#deleteModal").modal("hide");
		}
	};

	function showInsert() {
		$("#account").val("");
		$("#name").val("");
		$("#identity").val("");

		$("#insertButton").trigger("click");
	}

	function showUpdateRow(target) {
		var memberRow = $(target).parent().parent();
		var id = memberRow.find('td:nth-child(1)').html();
		var account = memberRow.find('td:nth-child(2)').html();
		var name = memberRow.find('td:nth-child(3)').html();
		var organization = memberRow.find('td:nth-child(4)').html();
		var identity = memberRow.find('td:nth-child(5)').html();
		var role = memberRow.find('td:nth-child(6)').html();

		$("#updateMemberId").val(id);
		$("#updateAccount").val(account);
		$("#updateName").val(name);
		$("#updateOrganization").val(organization);
		$("#updateIdentity").val(identity);
		$("#updateIRole").val(role);

		$("#updateButton").trigger("click");
	}

	function showDeleteRow(target) {
		var memberRow = $(target).parent().parent();
		var id = memberRow.find('td:nth-child(1)').html();
		$("#deleteId").html(id);
		$("#deleteButton").trigger("click");
	}

	function showMessage() {
		$('#messageButton').trigger("click");
	}
</script>