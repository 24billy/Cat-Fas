<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Begin Content -->
<div class="row">
	<div class="card col-md-12">
		<div class="card-header bg-info text-white text-center">
			<span>測驗結果</span> <span id="examType"></span> <span id="itemLength"></span><span id="elapsedTime"></span>
		</div>
		<div class="card-body">
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
		<div class="card-body text-center">
			<button class="btn-success btn-lg" onclick="finish()">完成，回到歷程管理</button>
		</div>
	</div>
</div>
<!-- Begin Content -->

<script>
	var recordStr = '${record}';
	var abilityVO = '${abilityVO}';
	var itemLength = '${itemLength}';
	var elapsedTime = '${elapsedTime}';
	$(document).ready(function() {
		generateResult(abilityVO);

		var record = JSON.parse(recordStr);
		var examType = record.examType;

		if (examType && "hv" == examType) {
			$("#examType").html("高效率測驗");
		} else if (examType && "hr" == examType) {
			$("#examType").html("高信度測驗");
		}

		if (itemLength && itemLength != "") {
			$("#itemLength").html(itemLength + " 題");
		}
		
		if (elapsedTime && elapsedTime != "") {
			$("#elapsedTime").html(" 測驗時間：" + elapsedTime + "毫秒");
		}
	});

	function generateResult(abilityVO) {
		var result = JSON.parse(abilityVO);

		for (var i = 1; i <= 4; i++) {
			// TScore
			var tscore = new Number(result.tScore[i - 1]);
			$("tr#category" + i + " td:nth-child(2)").html(tscore.toFixed(2));
			// 95%信賴區間上限
			var criUpper = new Number(result.criUpper[i - 1]);
			$("tr#category" + i + " td:nth-child(3)").html(criUpper.toFixed(2));
			// 95%信賴區間下限
			var criLower = new Number(result.criLower[i - 1]);
			$("tr#category" + i + " td:nth-child(4)").html(criLower.toFixed(2));
			// 百分等級
			var percentileLevel = new Number(result.percentileLevel[i - 1]);
			$("tr#category" + i + " td:nth-child(5)").html(percentileLevel);
			// 信度
			var reliability = new Number(result.reliability[i - 1]);
			$("tr#category" + i + " td:nth-child(6)").html(
					reliability.toFixed(2));
		}

	}

	function finish() {
		var record = JSON.parse(recordStr);

		showProgressManagement(record.subject.subjectId);
	}
	
</script>