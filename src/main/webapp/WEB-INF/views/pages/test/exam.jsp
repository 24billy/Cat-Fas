<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Begin Content -->
<div class="row">
	<div class="card col-md-12">
		<div class="card-header bg-info text-white text-center">第1題</div>
		<div class="card-body bg-dark">
			<ul class="list-group">
				<li class="list-group-item">向度：平衡</li>
				<li class="list-group-item">題目：無扶持下坐立</li>
				<li class="list-group-item">指導語：腳須踩在地板上</li>
			</ul>
		</div>
		<div class="card-body bg-dark">
			<form>
				<ul class="list-group">
					<li class="list-group-item border exam-item"
						onclick="changeColor(this)"><label
						class="custom-control custom-radio"> <input name="answer"
							type="radio" class="custom-control-input" id="option1" value="0">
							<span class="custom-control-indicator"></span> <span
							class="custom-control-description">0 無法坐立</span>
					</label></li>
					<li class="list-group-item border exam-item"
						onclick="changeColor(this)"><label
						class="custom-control custom-radio"> <input name="answer"
							type="radio" class="custom-control-input" id="option2" value="1">
							<span class="custom-control-indicator"></span> <span
							class="custom-control-description">1 需些微扶持下始能坐立，如他人以單手幫忙</span>
					</label></li>
					<li class="list-group-item border exam-item"
						onclick="changeColor(this)"><label
						class="custom-control custom-radio"> <input name="answer"
							type="radio" class="custom-control-input" id="option3" value="2">
							<span class="custom-control-indicator"></span> <span
							class="custom-control-description">2 沒有扶持下，可以坐立超過10秒鐘</span>
					</label></li>
					<li class="list-group-item border exam-item"
						onclick="changeColor(this)"><label
						class="custom-control custom-radio"> <input name="answer"
							type="radio" class="custom-control-input" id="option4" value="3">
							<span class="custom-control-indicator"></span> <span
							class="custom-control-description">3 沒有扶持下，可以坐立超過5分鐘</span>
					</label></li>
				</ul>
			</form>
		</div>
		<div class="card-body text-center">
			<button class="btn-success btn-lg" onclick="sendAnswer()">確定</button>
		</div>
	</div>
</div>
<!-- Begin Content -->

<!-- message modal -->
<button id="messageButton" type="button" class="btn btn-primary hide"
	data-toggle="modal" data-target="#messageModal"></button>

<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">提醒</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<h4 id="messageText">請選擇一個選項</h4>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">確定</button>
			</div>
		</div>
	</div>
</div>

<script>
	function changeColor(target) {
		// remove and add selected option style
		clearColor();
		$(target).addClass("bg-success").addClass("text-white");

		// get id and checked value
		var $checkedItem = $(target).find("input");
		$checkedItem.prop("checked", true);
		$checkedItem.val();
	}

	function clearColor() {
		$(".exam-item").removeClass("bg-success").removeClass("text-white");
	}

	function sendAnswer() {
		var $checkedItem = $('input[name="answer"]:checked');

		if ($checkedItem.val() && $checkedItem.val() != undefined) {
			console.log("送出選項：" + $checkedItem.val());
			//window.location.href = "exam2.html";
		} else {
			$("#messageButton").trigger("click");
		}
	}
</script>