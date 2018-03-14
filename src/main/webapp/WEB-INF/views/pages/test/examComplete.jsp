<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Begin Content -->
<div class="row">
	<div class="card col-md-12">
		<div class="card-header bg-info text-white text-center">測驗結果</div>
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

	$(document).ready(function() {
		generateResult(abilityVO);
		console.log(JSON.parse(recordStr));
	});

	function generateResult(abilityVO) {
		var result = JSON.parse(abilityVO);

		for (var i = 1; i <= 4; i++) {
			// TScore
			$("tr#category" + i + " td:nth-child(2)")
					.html(result.tScore[i - 1]);
			// 95%信賴區間上限
			$("tr#category" + i + " td:nth-child(3)").html(
					result.criUpper[i - 1]);
			// 95%信賴區間下限
			$("tr#category" + i + " td:nth-child(4)").html(
					result.criLower[i - 1]);
			// 百分等級
			$("tr#category" + i + " td:nth-child(5)").html(
					result.percentileLevel[i - 1]);
			// 信度
			$("tr#category" + i + " td:nth-child(6)").html(
					result.reliability[i - 1]);
		}

	}

	function finish() {
		var record = JSON.parse(recordStr);

		showProgressManagement(record.subject.subjectId);
	}
</script>