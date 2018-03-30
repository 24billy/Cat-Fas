<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Begin Content -->
<div class="container" id="content">
	<div class="row">
		<div class="col-md-12 text-center">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a onclick="showDataManagement()">資料管理</a>
				</li>
				<li class="breadcrumb-item active">測驗歷程管理</li>
			</ol>
		</div>

		<div class="col-md-12">
			<div class="card">
				<div class="card-body">
					<button class="btn btn-primary" onclick="addExam()">新增測驗</button>
					<button class="btn btn-info" onclick="exportDataBySubject()">受試者資料匯出</button>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table id="testProgressTable"
							class="table table-bordered table-hover" style="width: 100%;">
							<thead>
								<tr>
									<td>recordId</td>
									<td>病歷號碼</td>
									<td>姓名</td>
									<td>建立日期</td>
									<td>進行測驗</td>
									<td>測驗結果</td>
									<td>刪除紀錄</td>
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
</div>
<!-- Begin Content -->

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
					onclick="testProgress.remove()">確定</button>
			</div>
		</div>
	</div>
</div>

<!-- Large modal -->
<button class="btn btn-primary" data-toggle="modal"
	data-target="#resultModal" id="resultButton">resultModal</button>

<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel" aria-hidden="true" id="resultModal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">
					<span>測驗結果</span>
					<span id="examType"></span>
					<span id="itemLength"></span>
					<span id="reactionTime"></span>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="table-responsive">
					<table id="resultTable" class="table table-bordered table-hover"
						style="width: 100%;">
						<thead class="table-primary">
							<tr>
								<th>向度</th>
								<th>T分數</th>
								<th>95%信賴區間上限</th>
								<th>95%信賴區間下限</th>
								<th>百分等級</th>
								<th>信度</th>
							</tr>
						</thead>
						<tbody>
							<tr id="category1">
								<td class="table-info">上肢動作</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr id="category2">
								<td class="table-info">下肢動作</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr id="category3">
								<td class="table-info">平衡</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr id="category4">
								<td class="table-info">日常生活活動</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<div class="text-center">
					<button type="button" class="btn btn-primary" data-dismiss="modal">回到歷程管理</button>
				</div>
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
<script>
	var data = '${responseList}';
	var subjectId = '${subjectId}';

	$(document).ready(function() {
		testProgress.dataList = JSON.parse(data);
		//testProgress.init();

		for (key in testProgress.dataList) {
			testProgress.renderHtml(testProgress.dataList[key]);
		}

		var table = $("#testProgressTable").DataTable();
	});

	var testProgress = {
		dataList : [],
		init : function() {
			var data = {};
			data.recordId = 1;
			var subject = {};
			subject.medicalNumber = "101";
			subject.subjectName = "John";
			data.subject = subject
			data.startDate = transferDate(new Date("2017-09-01"));
			data.isComplete = true;
			testProgress.dataList.push(data);

			for (key in testProgress.dataList) {
				testProgress.renderHtml(testProgress.dataList[key]);
			}
		},
		renderHtml : function(progressObject) {
			var recordId = progressObject.recordId;
			var medicalNumber = progressObject.subject.medicalNumber;
			var name = progressObject.subject.subjectName;
			var startDate = progressObject.startDate;
			var complete = progressObject.isComplete;
			var isDelete = progressObject.isDelete;

			var $tr = '<tr id="data-' + recordId + '">';
			var $td = '<td>' + recordId + '</td>';
			$td += '<td>' + medicalNumber + '</td>';
			$td += '<td>' + name + '</td>';
			$td += '<td>' + startDate + '</td>';
			$td += '<td>';

			if (complete || isDelete) {
				$td += '<button class="btn-default marginButton" onclick="startHighReliabilityTest('
						+ recordId + ')" disabled>高信度</button>';
				$td += '<button class="btn-default marginButton" onclick="startHighValidityTest('
						+ recordId + ')" disabled>高效率</button>';
			} else {
				$td += '<button class="btn-success marginButton" onclick="startHighReliabilityTest('
						+ recordId + ')">高信度</button>';
				$td += '<button class="btn-success marginButton" onclick="startHighValidityTest('
						+ recordId + ')">高效率</button>';
			}

			$td += '</td>';
			$td += '<td>';

			if (complete && !isDelete) {
				$td += '<button class="btn-info marginButton" onclick="showResult('
						+ recordId + ')">結果</button>';
			} else {
				$td += '<button class="btn-default marginButton" disabled>結果</button>';
			}

			$td += '</td>';
			$td += '<td>';

			if (isDelete) {
				$td += '<button class="btn-default marginButton" onclick="showDeleteRow(this)" disabled>刪除</button>';
			} else {
				$td += '<button class="btn-danger marginButton" onclick="showDeleteRow(this)">刪除</button>';
			}

			$td += '</td>';
			$tr += $td + '</tr>';

			if (!isDelete) {
				$("#testProgressTable tbody").append($tr);
			}
		},
		insert : function() {
			$.ajax({
				url : "progressManagement/addExam",
				type : "POST",
				data : {
					subjectId : subjectId
				},
				error : function(e) {

				},
				success : function(data) {
					showProgressManagement(subjectId);
				}
			});
		},
		remove : function() {
			var recordId = $("#deleteId").html();

			$.ajax({
				url : "progressManagement/deleteExam",
				type : "POST",
				data : {
					recordId : recordId
				},
				error : function(e) {

				},
				success : function(data) {
					showProgressManagement(subjectId);
				}
			});

			$("#deleteModal").modal("hide");
		}
	}

	function addExam() {
		testProgress.insert();
	}

	function showDeleteRow(target) {
		var testProgressRow = $(target).parent().parent();
		var id = testProgressRow.find('td:nth-child(1)').html();
		$("#deleteId").html(id);

		$("#deleteButton").trigger("click");
	}

	function showMessage() {
		$('#messageButton').trigger("click");
	}

	function showResult(id) {
		$.ajax({
			url : "progressManagement/getExamResult",
			type : "POST",
			data : {
				recordId : id
			},
			error : function(e) {

			},
			success : function(data) {
				var resultMap = JSON.parse(data);
				var result  = resultMap.abilityVO;
				var examType = resultMap.examType;
				var itemLength = resultMap.itemLength;
				var reactionTime = resultMap.reactionTime;
				
				if ("hv" == examType) {
					$("#examType").html("高效度測驗");	
				} else if ("hr" == examType) {
					$("#examType").html("高信度測驗");	
				}
				
				if (reactionTime && reactionTime != "") {
					$("#reactionTime").html(" 測驗時間：" + reactionTime + "毫秒");
				}
				
				$("#itemLength").html(itemLength + "題");
				
				for (var i = 1; i <= 4; i++) {
					// TScore
					var tscore = new Number(result.tScore[i - 1]);
					$("tr#category" + i + " td:nth-child(2)").html(
							tscore.toFixed(2));
					// 95%信賴區間上限
					var criUpper = new Number(result.criUpper[i - 1]);
					$("tr#category" + i + " td:nth-child(3)").html(
							criUpper.toFixed(2));
					// 95%信賴區間下限
					var criLower = new Number(result.criLower[i - 1]);
					$("tr#category" + i + " td:nth-child(4)").html(
							criLower.toFixed(2));
					// 百分等級
					var percentileLevel = new Number(
							result.percentileLevel[i - 1]);
					$("tr#category" + i + " td:nth-child(5)").html(
							percentileLevel);
					// 信度
					var reliability = new Number(result.reliability[i - 1]);
					$("tr#category" + i + " td:nth-child(6)").html(
							reliability.toFixed(2));
				}

				$("#resultButton").trigger("click");
				//showProgressManagement(subjectId);
			}
		});

	}

	function exportDataBySubject() {
		console.log("匯出Excel : " + subjectId);
		$('<form action="dataManagement/downloadExcelBySubject.do" method="get"><input type="text" name="subjectId" value="' + subjectId +'"/></form>')
				.appendTo('body').submit().remove();
	}

	function transferDate(input) {
		var year = input.getFullYear();
		var month = input.getMonth() + 1;
		var day = input.getDate();
		var fomatedDate = year + "/" + month + "/" + day;

		return fomatedDate;
	}
</script>
