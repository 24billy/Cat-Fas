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
								<td>密碼</td>
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
						</select>
					</div>

					<div class="form-group col-md-6">
						<label for="identity" class="form-control-label">身份:</label> <input
							type="text" class="form-control" id="identity">
					</div>
					<div class="form-group col-md-6">
						<label for="role" class="form-control-label">角色:</label> <select
							class="form-control" id="role">
							<option value="2">主試者</option>
							<option value="1">管理員</option>
						</select>

					</div>
					<div class="form-group col-md-6">
						<label for="verifyCode" class="form-control-label">密碼:</label> <input
							type="password" class="form-control" id="verifyCode">
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
						</select>
					</div>

					<div class="form-group col-md-6">
						<label for="updateIdentity" class="form-control-label">身份:</label>
						<input type="text" class="form-control" id="updateIdentity">
					</div>
					<div class="form-group col-md-6">
						<label for="updateRole" class="form-control-label">角色:</label> <select
							class="form-control" id="updateRole">
							<option value="2">主試者</option>
							<option value="1">管理員</option>
						</select>

					</div>
					<div class="form-group col-md-6">
						<label for="updateVerifyCode" class="form-control-label">密碼:</label> <input
							type="password" class="form-control" id="updateVerifyCode">
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

<script type="text/javascript">
	var data = '${memberList}';
	var organizationData = '${organizationList}';
	
	$(document).ready(function() {
		member.dataList = JSON.parse(data);

		for (key in member.dataList) {
			member.renderHtml(member.dataList[key]);
		}
		
		$("#examinerTable").DataTable({
			responsive : true
		});
		
		generateOrganization();
	});
	
	function generateOrganization() {
		var organizationList = JSON.parse(organizationData);

		for (key in organizationList) {
			var organizationid = organizationList[key].organizationid;
			var name = organizationList[key].name;
			var isVisible = organizationList[key].isVisible;

			var $option = '<option value=' + organizationid + '>';
			$option += name;
			$option += '</option>';
			
			if (isVisible) {
				$('#organization').append($option); // 新增
				$('#updateOrganization').append($option);; // 編輯				
			}
		}
	}
	
	var currentId = 5;
	var member = {
		dataList : [],
		init : function() {
			var data = {};
			data.memberId = "1";
			data.account = "admin";
			data.name = "管理者";
			data.organization.name = "台大";
			data.identity = "治療師";
			data.role.roleName = "管理者";
			member.dataList.push(data);

			var data = {};
			data.memberId = "2";
			data.account = "kp";
			data.name = "柯p";
			data.organization.name = "台北市政府";
			data.identity = "市長";
			data.role.roleName = "主試者";
			member.dataList.push(data);

			var data = {};
			data.memberId = "3";
			data.account = "Billy";
			data.name = "Billy";
			data.organization.name = "中正";
			data.identity = "畢業生";
			data.role.roleName = "管理者";
			member.dataList.push(data);

			for (key in member.dataList) {
				member.renderHtml(member.dataList[key]);
			}
		},
		renderHtml : function(memberObject) {
			var id = memberObject.memberId;
			var account = memberObject.account;
			var name = memberObject.name;
			var organizationId = memberObject.organization.organizationid;
			var organization = memberObject.organization.name;
			var identity = memberObject.identity;
			var verifyCode = memberObject.verifyCode;
			var role = memberObject.role.roleName;

			var $tr = '<tr id="data-' + id + '" organizationid="' + organizationId +  '">';
			var $td = '<td>';
			$td += id + '</td>';
			$td += '<td>' + account + '</td>';
			$td += '<td>' + name + '</td>';
			$td += '<td>' + organization + '</td>';
			$td += '<td>' + identity + '</td>';
			$td += '<td>' + role + '</td>';
			$td += '<td>' + verifyCode + '</td>';
			$td += '<td>';
			$td += '<button class="btn-warning marginButton" onclick="showUpdateRow(this)">編輯</button> ';
			$td += '<button class="btn-danger marginButton" onclick="showDeleteRow(this)">刪除</button>';
			$td += '</td>';
			$tr += $td + '</tr>';

			$("#examinerTable tbody").append($tr);
		},
		validate : function(source) {
			if ("insert" == source) {
				var account = $("#account").val();
				var name = $("#name").val();
				var organization = $("#organization").val();
				var identity = $("#identity").val();
				var role = $("#role").val();
				var verifyCode = $("#verifyCode").val();
				var errorMessage = "";
				var hasError = false;
				
				if (account == "") {
					errorMessage += "<p>帳號不得為空</p>";
					hasError = true;
				}
				if (name == "") {
					errorMessage += "<p>姓名不得為空</p>";
					hasError = true;
				}
				if (verifyCode == "") {
					errorMessage += "<p>密碼不得為空</p>";
					hasError = true;
				}
				
				if (hasError) {
					showMessage(errorMessage);
					
					return false;
				}
				
				return true;
			}
			if ("update" == source) {
				var updateName = $("#updateName").val();
				var updateVerifyCode = $("#updateVerifyCode").val();
				var errorMessage = "";
				var hasError = false;
				
				if (updateName == "") {
					errorMessage += "<p>姓名不得為空</p>";
					hasError = true;
				}
				
				if (updateVerifyCode == "") {
					//errorMessage += "<p>密碼不得為空</p>";
					//hasError = true;
				}
				
				if (hasError) {
					showMessage(errorMessage);
					
					return false;
				}
				
				return true;
			}
			
			
			return true;
		},
		insert : function() {
			var account = $("#account").val();
			var name = $("#name").val();
			var organization = $("#organization").val();
			var identity = $("#identity").val();
			var role = $("#role").val();
			var verifyCode = $("#verifyCode").val();

			if (!member.validate("insert")) {
				return;
			}
			
			$("#insertMember").modal("hide");

			$.ajax({
				url : "member/addMember",
				type : "POST",
				data : {
					organizationId : organization,
					roleId : role,
					account : account,
					name : name,
					identity : identity,
					verifyCode : verifyCode
				},
				error : function(e) {

				},
				success : function(data) {
					showMember();
				}
			});
		},
		update : function() {
			var updateId = $("#updateMemberId").val();
			var updateName = $("#updateName").val();
			var updateOrganization = $("#updateOrganization").val();
			var updateIdentity = $("#updateIdentity").val();
			var updateRole = $("#updateRole").val();
			var updateVerifyCode = $("#updateVerifyCode").val();

			if (!member.validate("update")) {
				return;
			}
			
			$("#updateMember").modal("hide");
			
			$.ajax({
				url : "member/updateMember",
				type : "POST",
				data : {
					memberId : updateId,
					name : updateName,
					organizationId : updateOrganization,
					roleId : updateRole,
					identity : updateIdentity,
					verifyCode : updateVerifyCode
				},
				error : function(e) {

				},
				success : function(data) {
					showMember();
				}
			});
		},
		remove : function() {
			var id = $("#deleteId").html();
			$("#deleteModal").modal("hide");
			console.log(id);
			$.ajax({
				url : "member/deleteMember",
				type : "POST",
				data : {
					memberId : id
				},
				error : function(e) {

				},
				success : function(data) {
					showMember();
				}
			});
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
		var identity = memberRow.find('td:nth-child(5)').html();
		var role = memberRow.find('td:nth-child(6)').html();
		var verifyCode = memberRow.find('td:nth-child(7)').html();
		var organization = memberRow.attr("organizationid");
		
		$("#updateMemberId").val(id);
		$("#updateAccount").val(account);
		$("#updateName").val(name);
		$("#updateOrganization").val(organization);
		$("#updateIdentity").val(identity);
		$("#updateIRole").val(role);
		$("#updateIverifyCode").val(verifyCode);

		$("#updateButton").trigger("click");
	}

	function showDeleteRow(target) {
		var memberRow = $(target).parent().parent();
		var id = memberRow.find('td:nth-child(1)').html();
		$("#deleteId").html(id);
		$("#deleteButton").trigger("click");
	}

	function showMessage(message) {
		$("div#messageText").html(message);
		$('#messageButton').trigger("click");
	}
</script>