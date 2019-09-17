<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Begin Content -->
<div class="container" id="content">
	<div class="row">
		<div class="col-md-12 text-center">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a onclick="showDataManagement()"><span class="cht-lan">資料管理</span> <span class="en-lan"
									style="display: none;">Data management</span></a>
				</li>
				<li class="breadcrumb-item active"><span class="cht-lan">測驗歷程管理</span> <span class="en-lan"
									style="display: none;">Unit management</span></li>
			</ol>
		</div>

		<div class="col-md-12">
			<div class="card">
				<div class="card-body">
					<button class="btn btn-primary" onclick="addExam()">
					<span class="cht-lan">新增測驗</span> <span class="en-lan"
									style="display: none;">New Unit</span>
					</button>
					<button class="btn btn-info" onclick="exportDataBySubject()">
					<span class="cht-lan">受試者資料匯出</span> <span class="en-lan"
									style="display: none;">Export data</span>
					</button>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table id="testProgressTable"
							class="table table-bordered table-hover" style="width: 100%;">
							<thead>
								<tr>
									<td>
									<span class="cht-lan">recordId</span> <span class="en-lan"
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
									<span class="cht-lan">建立日期</span> <span class="en-lan"
									style="display: none;">Date</span>
									</td>
									
									<td>
									<span class="cht-lan">進行測驗</span> <span class="en-lan"
									style="display: none;">Mode of administation</span>
									</td>
									
									<td>
									<span class="cht-lan">測驗結果</span> <span class="en-lan"
									style="display: none;"></span>
									</td>
									
									<td>
									<span class="cht-lan">刪除紀錄</span> <span class="en-lan"
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
				<h5 class="modal-title">
				<span class="cht-lan">即將刪除</span> <span class="en-lan"
									style="display: none;">Delete unit</span>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>
				<span class="cht-lan">確定要刪除此資料？</span> <span class="en-lan"
									style="display: none;">Delete this unit?</span>
				</p>
				<div style="display: none;" id="deleteId"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">
				<span class="cht-lan">取消</span> <span class="en-lan"
									style="display: none;">Cancel</span>
				</button>
				<button type="button" class="btn btn-primary"
					onclick="testProgress.remove()">
					<span class="cht-lan">確定</span> <span class="en-lan"
									style="display: none;">Delete</span>
					</button>
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
				<h5 class="modal-title cht-lan">
					<span>
						測驗結果
					</span>
					<span id="examType"></span>
					<span id="itemLength"></span>
					<span id="reactionTime"></span>
				</h5>
				<h5 class="modal-title en-lan" style="display:none;">
					<span>
						Result
					</span>
					<span id="examTypeEn"></span>
					<br>
					<span id="itemLengthEn"></span>
					<span id="reactionTimeEn"></span>
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
								<th>
								<span class="cht-lan">向度</span> <span class="en-lan"
									style="display: none;">Function</span>
								</th>
								
								<th>
								<span class="cht-lan">T分數</span> <span class="en-lan"
									style="display: none;">T-score</span>
								</th>
								
								<th>
								<span class="cht-lan">95%信賴區間上限</span> <span class="en-lan"
									style="display: none;">95% CI(upper limit)</span>
								</th>
								
								<th>
								<span class="cht-lan">95%信賴區間下限</span> <span class="en-lan"
									style="display: none;">95% CI(lower limit)</span>
								</th>
								
								<th>
								<span class="cht-lan">百分等級</span> <span class="en-lan"
									style="display: none;">Percentile Rank</span>
								</th>
								
								<th>
								<span class="cht-lan">信度</span> <span class="en-lan"
									style="display: none;">Reliability</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="category1">
								<td class="table-info">
								<span class="cht-lan">上肢動作</span> <span class="en-lan"
									style="display: none;">UE motor </span>
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr id="category2">
								<td class="table-info">
								<span class="cht-lan">下肢動作</span> <span class="en-lan"
									style="display: none;">LE motor </span>
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr id="category3">
								<td class="table-info">
								<span class="cht-lan">平衡</span> <span class="en-lan"
									style="display: none;">Postural control </span>
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr id="category4">
								<td class="table-info">
								<span class="cht-lan">日常生活活動</span> <span class="en-lan"
									style="display: none;">Basic activities of daily living
</span>
								</td>
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
					<button type="button" class="btn btn-primary" data-dismiss="modal">
					<span class="cht-lan">回到歷程管理</span> <span class="en-lan"
									style="display: none;">Back</span>
					</button>
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
				<p id="messageText"></p>
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
	var data = '${responseList}';
	var subjectId = '${subjectId}';

	$(document).ready(function() {
		testProgress.dataList = JSON.parse(data);
		//testProgress.init();

		for (key in testProgress.dataList) {
			testProgress.renderHtml(testProgress.dataList[key]);
		}

		var lengMenuStr = '<span class="cht-lan">顯示</span> <span class="en-lan" style="display: none;">Display</span> _MENU_ <span class="cht-lan">筆資料</span> <span class="en-lan" style="display: none;">records per page</span>';
		var zeroRecordsStr = '<span class="cht-lan">查無資料</span> <span class="en-lan" style="display: none;">Nothing found - sorry</span>';
		var infoStr = '<span class="cht-lan">顯示第 _START_ 至 _END_ 筆資料，總共 _TOTAL_ 筆資料</span> <span class="en-lan" style="display: none;">Showing _START_ to _END_ of _TOTAL_</span>';
		var ifoEmptyStr = '<span class="cht-lan">無顯示資料</span> <span class="en-lan" style="display: none;">No records available</span>';
		var searchStr = '<span class="cht-lan">搜尋</span> <span class="en-lan" style="display: none;">Search</span>';
		var perviousStr = '<span class="cht-lan">上一頁</span> <span class="en-lan" style="display: none;">Prev</span>';
		var nextStr = '<span class="cht-lan">下一頁</span> <span class="en-lan" style="display: none;">Next</span>';

		var table = $("#testProgressTable").DataTable({
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

			var highReliabilityBtn = '<span class="cht-lan">高信度</span>';
			highReliabilityBtn +='<span class="en-lan" style="display: none;">High reliability</span>';
			var highValidityBtn = '<span class="cht-lan">高效率</span>';
			highValidityBtn +='<span class="en-lan" style="display: none;">High efficiency</span>';
			
			if (complete || isDelete) {
				$td += '<button class="btn-default marginButton" onclick="startHighReliabilityTest('
						+ recordId + ')" disabled>' + highReliabilityBtn + '</button>';
				$td += '<button class="btn-default marginButton" onclick="startHighValidityTest('
						+ recordId + ')" disabled>' + highValidityBtn + '</button>';
			} else {
				$td += '<button class="btn-success marginButton" onclick="startHighReliabilityTest('
						+ recordId + ')">' + highReliabilityBtn + '</button>';
				$td += '<button class="btn-success marginButton" onclick="startHighValidityTest('
						+ recordId + ')">' + highValidityBtn + '</button>';
			}

			$td += '</td>';
			$td += '<td>';

			var resultBtn = '<span class="cht-lan">結果</span>';
			resultBtn +='<span class="en-lan" style="display: none;">Show result</span>';
			
			if (complete && !isDelete) {
				$td += '<button class="btn-info marginButton" onclick="showResult('
						+ recordId + ')">' + resultBtn + '</button>';
			} else {
				$td += '<button class="btn-default marginButton" disabled>' + resultBtn + '</button>';
			}

			$td += '</td>';
			$td += '<td>';

			var deleteBtn = '<span class="cht-lan">刪除</span>';
			deleteBtn +='<span class="en-lan" style="display: none;">Delete</span>';
			
			
			if (isDelete) {
				$td += '<button class="btn-default marginButton" onclick="showDeleteRow(this)" disabled>' + deleteBtn + '</button>';
			} else {
				$td += '<button class="btn-danger marginButton" onclick="showDeleteRow(this)">' + deleteBtn + '</button>';
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
					$("#examType").html("高效率測驗");
					$("#examTypeEn").html("(Mode: high efficiency)");
				} else if ("hr" == examType) {
					$("#examType").html("高信度測驗");
					$("#examTypeEn").html("(Mode: high reliability)");
				}
				
				if (reactionTime && reactionTime != "") {
					$("#reactionTime").html(" 測驗時間：" + reactionTime + "毫秒");
					$("#reactionTimeEn").html(" Time of assessment：" + reactionTime + " ms");
				}
				
				$("#itemLength").html(itemLength + "題");
				$("#itemLengthEn").html(" Number of items：" + itemLength + "；");
				
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
