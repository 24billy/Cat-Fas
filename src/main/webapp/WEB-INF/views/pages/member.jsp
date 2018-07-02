<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Begin Content -->
<div class="row">
	<div class="col-md-12">
		<ol class="breadcrumb">
			<li class="breadcrumb-item active"><span class="cht-lan">訪員管理</span>
				<span class="en-lan" style="display: none;">User Management</span>
			</li>
		</ol>
	</div>

	<div class="col-md-12">
		<div class="card">
			<div class="card-body">
				<button class="btn btn-primary" onclick="showInsert()">
					<span class="cht-lan">新增訪員</span> <span class="en-lan"
						style="display: none;">New User</span>
				</button>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table id="examinerTable" class="table table-bordered table-hover"
						style="width: 100%;">
						<thead>
							<tr>
								<td><span class="cht-lan">代碼</span> <span class="en-lan"
									style="display: none;"> ID</span></td>
								<td><span class="cht-lan">帳號</span> <span class="en-lan"
									style="display: none;">User Name</span></td>
								<td><span class="cht-lan">姓名</span><span class="en-lan"
									style="display: none;">Full Name</span></td>
								<td><span class="cht-lan">所屬單位</span><span class="en-lan"
									style="display: none;">Unit</span></td>
								<td><span class="cht-lan">身份</span><span class="en-lan"
									style="display: none;">Title</span></td>
								<td><span class="cht-lan">角色</span><span class="en-lan"
									style="display: none;">Role</span></td>
								<td><span class="cht-lan">密碼</span><span class="en-lan"
									style="display: none;">Password</span></td>
								<td><span class="cht-lan">功能</span><span class="en-lan"
									style="display: none;"></span></td>
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
	data-target="#insertMember" id="insertButton">
	<span class="cht-lan">新增訪員</span> <span class="en-lan"
		style="display: none;">New User</span>
</button>

<div class="modal fade" id="insertMember" tabindex="-1" role="dialog"
	aria-labelledby="insertMemberLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="insertMemberLabel">
					<span class="cht-lan">新增訪員</span> <span class="en-lan"
						style="display: none;">New User</span>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="row">
					<div id="memberId" style="display: none;"></div>
					<div class="form-group col-md-6">
						<label for="account" class="form-control-label"><span
							class="cht-lan">帳號:</span> <span class="en-lan"
							style="display: none;">UserName:</span></label> <input type="text"
							class="form-control" id="account">
					</div>
					<div class="form-group col-md-6">
						<label for="name" class="form-control-label">
							<span class="cht-lan">姓名:</span> <span class="en-lan"
							style="display: none;">Full Name</span>
						</label> 
						<input type="text" class="form-control" id="name">
					</div>
					<div class="form-group col-md-6">
						<label for="organization" class="form-control-label"><span
							class="cht-lan">所屬單位:</span> <span class="en-lan"
							style="display: none;">Unit</span></label> <select
							class="form-control" id="organization">
						</select>
					</div>

					<div class="form-group col-md-6">
						<label for="identity" class="form-control-label"><span
							class="cht-lan">身份:</span> <span class="en-lan"
							style="display: none;">Title:</span></label> <input type="text"
							class="form-control" id="identity">
					</div>
					<div class="form-group col-md-6">
						<label for="role" class="form-control-label"><span
							class="cht-lan">角色:</span> <span class="en-lan"
							style="display: none;">Role:</span></label> 
						<select class="form-control"
							id="role">
							<option class="cht-lan" value="2">主試者</option> 
							<option class="cht-lan" value="1">管理員</option>
							<option class="en-lan" style="display: none;" value="2">Administator</option> 
							<option class="en-lan" style="display: none;" value="1">Manager</option>
						</select>

					</div>
					<div class="form-group col-md-6">
						<label for="verifyCode" class="form-control-label">
							<span class="cht-lan">密碼:</span> <span class="en-lan"
							style="display: none;">Password:</span>
							</label> <input type="password"
							class="form-control" id="verifyCode">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">
					<span class="cht-lan">取消</span> <span class="en-lan"
						style="display: none;">Cancel</span>
				</button>
				<button type="button" class="btn btn-primary"
					onclick="member.insert()">
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
	data-toggle="modal" data-target="#updateMember"></button>
<div class="modal fade" id="updateMember" tabindex="-1" role="dialog"
	aria-labelledby="updateMemberLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="updateMemberLabel">
				<span class="cht-lan">編輯訪員:</span> <span class="en-lan"
							style="display: none;">Edit User Information</span>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="row">

					<div class="form-group col-md-6">
						<div id="updateMemberId" style="display: none;"></div>
						<label for="updateAccount" class="form-control-label">
							<span class="cht-lan">帳號:</span> <span class="en-lan"
							style="display: none;">UserName:</span>
						</label>
						<input type="text" class="form-control" id="updateAccount"
							readonly>
					</div>
					<div class="form-group col-md-6">
						<label for="updateName" class="form-control-label">
							<span class="cht-lan">姓名:</span> <span class="en-lan"
							style="display: none;">Full Name</span>
						</label> 
						<input type="text" class="form-control" id="updateName">
					</div>
					<div class="form-group col-md-6">
						<label for="updateOrganization" class="form-control-label">
							<span class="cht-lan">所屬單位:</span> <span class="en-lan"
							style="display: none;">Unit</span>
						</label>
						<select class="form-control" id="updateOrganization">
						</select>
					</div>

					<div class="form-group col-md-6">
						<label for="updateIdentity" class="form-control-label">
							<span class="cht-lan">身份:</span> <span class="en-lan"
							style="display: none;">Title:</span>
						</label>
						<input type="text" class="form-control" id="updateIdentity">
					</div>
					<div class="form-group col-md-6">
						<label for="updateRole" class="form-control-label">
							<span class="cht-lan">角色:</span> <span class="en-lan"
							style="display: none;">Role:</span>
						</label> 
						<select
							class="form-control" id="updateRole">
							<option class="cht-lan" value="2">主試者</option> 
							<option class="cht-lan" value="1">管理員</option>
							<option class="en-lan" style="display: none;" value="2">Administator</option> 
							<option class="en-lan" style="display: none;" value="1">Manager</option>
						</select>

					</div>
					<div class="form-group col-md-6">
						<label for="updateVerifyCode" class="form-control-label">
							<span class="cht-lan">密碼:</span> <span class="en-lan"
							style="display: none;">Password:</span>
						</label>
						<input type="password" class="form-control" id="updateVerifyCode">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">
					<span class="cht-lan">取消</span> <span class="en-lan"
						style="display: none;">Cancel</span>
				</button>
				<button type="button" class="btn btn-primary"
					onclick="member.update()">
					<span class="cht-lan">儲存</span> <span class="en-lan"
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
						style="display: none;">Delete User</span>				
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>
					<span class="cht-lan">確定要刪除此資料？</span> <span class="en-lan"
						style="display: none;">Delete this User？</span>				
				</p>
				<div style="display: none;" id="deleteId"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">
				<span class="cht-lan">取消</span> <span class="en-lan"
						style="display: none;">Cancel</span>
				</button>
				<button type="button" class="btn btn-primary"
					onclick="member.remove()">
					<span class="cht-lan">確定</span> <span class="en-lan"
						style="display: none;">Confirm</span>	
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
					<span class="cht-lan">錯誤</span> 
					<span class="en-lan" style="display: none;">Warning</span>
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
						<span class="cht-lan">確定</span> 
						<span class="en-lan" style="display: none;">Close</span>
					</button>
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

		var lengMenuStr = '<span class="cht-lan">顯示</span> <span class="en-lan" style="display: none;">Display</span> _MENU_ <span class="cht-lan">筆資料</span> <span class="en-lan" style="display: none;">records per page</span>';
		var zeroRecordsStr = '<span class="cht-lan">查無資料</span> <span class="en-lan" style="display: none;">Nothing found - sorry</span>';
		var infoStr = '<span class="cht-lan">顯示第 _START_ 至 _END_ 筆資料，總共 _TOTAL_ 筆資料</span> <span class="en-lan" style="display: none;">Showing _START_ to _END_ of _TOTAL_</span>';
		var ifoEmptyStr = '<span class="cht-lan">無顯示資料</span> <span class="en-lan" style="display: none;">No records available</span>';
		var searchStr = '<span class="cht-lan">搜尋</span> <span class="en-lan" style="display: none;">Search</span>';
		var perviousStr = '<span class="cht-lan">上一頁</span> <span class="en-lan" style="display: none;">Prev</span>';
		var nextStr = '<span class="cht-lan">下一頁</span> <span class="en-lan" style="display: none;">Next</span>';
		
		$("#examinerTable").DataTable({
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
		});

		showCurrentLan();

		generateOrganization();
		
		$('input[type="search"]').on("keyup", function(){
			showCurrentLan();	
		});
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
				$('#updateOrganization').append($option);
				; // 編輯				
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
			console.log(memberObject);
			var id = memberObject.memberId;
			var account = memberObject.account;
			var name = memberObject.name;
			var organizationId = memberObject.organization.organizationid;
			var organization = memberObject.organization.name;
			var identity = memberObject.identity;
			var verifyCode = memberObject.verifyCode;
			var roleId = memberObject.role.roleId;
			var role = memberObject.role.roleName;

			var roleObject ='';
			if (2 == roleId) {
				roleObject = '<span class="cht-lan">主試者</span> <span class="en-lan" style="display: none;">Administrator</span>';
			} else if (1 == roleId) {
				roleObject = '<span class="cht-lan">管理者</span> <span class="en-lan" style="display: none;">Manager</span>';
			}
			
			var $tr = '<tr id="data-' + id + '" organizationid="' + organizationId +  '">';
			var $td = '<td>';
			$td += id + '</td>';
			$td += '<td>' + account + '</td>';
			$td += '<td>' + name + '</td>';
			$td += '<td>' + organization + '</td>';
			$td += '<td>' + identity + '</td>';
			$td += '<td>' + roleObject + '</td>';
			$td += '<td>' + verifyCode + '</td>';
			$td += '<td>';
			$td += '<button class="btn-warning marginButton" onclick="showUpdateRow(this)"><span class="cht-lan">編輯</span>';
			$td += '<span class="en-lan" style="display: none;">Edit</span></button>';
			$td += '<button class="btn-danger marginButton" onclick="showDeleteRow(this)"><span class="cht-lan">刪除</span>';
			$td += '<span class="en-lan" style="display: none;">Delete</span></button>';
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
					errorMessage += '<p><span class="cht-lan">帳號不得為空</span>';
					errorMessage += '<span class="en-lan" style="display: none;">Account cannot be empty</span></p>';
					hasError = true;
				}
				if (name == "") {
					errorMessage += '<p><span class="cht-lan">姓名不得為空</span>';
					errorMessage += '<span class="en-lan" style="display: none;">Name cannot be empty</span></p>';
					hasError = true;
				}
				if (verifyCode == "") {
					errorMessage += '<p><span class="cht-lan">密碼不得為空</span>';
					errorMessage += '<span class="en-lan" style="display: none;">Password cannot be empty</span></p>';
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
					errorMessage += '<p><span class="cht-lan">姓名不得為空</span>';
					errorMessage += '<span class="en-lan" style="display: none;">Name cannot be empty</span></p>';
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
		$("#verifyCode").val("");
		
		if ("cht" == currentLanguage) {
			$("#role").prop("selectedIndex", 0);
		} else if ("en" == currentLanguage) {
			$("#role").prop("selectedIndex", 2);
		}

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
		//$("#updateRole").val(role);
		$("#updateIverifyCode").val(verifyCode);

		if ("cht" == currentLanguage) {
			$("#updateRole").prop("selectedIndex", 0);
		} else if ("en" == currentLanguage) {
			$("#updateRole").prop("selectedIndex", 2);
		}
		
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
		showCurrentLan();
		$('#messageButton').trigger("click");
	}
</script>