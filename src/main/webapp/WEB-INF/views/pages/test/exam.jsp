<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Begin Content -->
<div class="row">
	<div class="card col-md-12">
		<div class="card-header bg-info text-white text-center" id="itemIndex">
			<span class="cht-lan">第${itemIndex}題</span> <span class="en-lan"
								style="display: none;">No. ${itemIndex}</span>
			
		</div>
		<div class="card-body bg-dark">
			<ul class="list-group">
				<li class="list-group-item" id="itemDimension">向度：</li>
				<li class="list-group-item" id="title">題目：</li>
				<li class="list-group-item" id="itemIntroduction">指導語：</li>
				<li class="list-group-item" id="itemStartPose">起始姿勢：</li>
			</ul>
		</div>
		<div class="card-body bg-dark">
			<form>
				<ul class="list-group" id="itemList">
				</ul>
			</form>
		</div>
		<div class="card-body text-center">
			<button class="btn-success btn-lg" onclick="sendAnswer()">
				<span class="cht-lan">確定</span> <span class="en-lan"
								style="display: none;">Next ></span>
			</button>
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
				<h5 class="modal-title">
					<span class="cht-lan">提醒</span> <span class="en-lan"
								style="display: none;">Warning</span>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<h4 id="messageText">
					<span class="cht-lan">請選擇一個選項</span> <span class="en-lan"
								style="display: none;">Please choose an option.</span>
				</h4>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">
					<span class="cht-lan">確定</span> <span class="en-lan"
								style="display: none;">Confirm</span>
				</button>
			</div>
		</div>
	</div>
</div>

<script>
	var examType = '${examType}';
	var itemStr = '${item}';
	var abilityStr = '${ability}';
	var record = '${record}';

	$(document).ready(function() {
		if (abilityStr && abilityStr != "") {
			var ability = JSON.parse(abilityStr);
			console.log(ability);
		}

		generateItem(itemStr);
		showCurrentLan();
	});

	function generateItem(itemStr) {
		console.log(itemStr);
		var item = JSON.parse(itemStr);
		console.log(item);
		
		var startPose = item.startPose;
		var title = '<span class="cht-lan">';
			title += '題目：';
			title += item.chtTitle || "";
			title += '</span>';
			title += '<span class="en-lan" style="display: none;">';
			title += 'Item：';
			title += item.engTitle || "";
			title += '</span>';
		$("#title").html(title);
		
		var dimension = item.dimension;
		var dimensionStr = "";
		if (1 == dimension) {
			dimensionStr ='<span class="cht-lan">';
			dimensionStr += "向度：上肢動作";
			dimensionStr += '</span>';
			dimensionStr +='<span class="en-lan" style="display: none;">';
			dimensionStr += "Function：UE motor";
			dimensionStr += '</span>';
		} else if (2 == dimension) {
			dimensionStr ='<span class="cht-lan">';
			dimensionStr += "向度：下肢動作";
			dimensionStr += '</span>';
			dimensionStr +='<span class="en-lan" style="display: none;">';
			dimensionStr += "Function：LE motor";
			dimensionStr += '</span>';
		} else if (3 == dimension) {
			dimensionStr ='<span class="cht-lan">';
			dimensionStr += "向度：平衡";
			dimensionStr += '</span>';
			dimensionStr +='<span class="en-lan" style="display: none;">';
			dimensionStr += "Function：Postural control";
			dimensionStr += '</span>';
		} else if (4 == dimension) {
			dimensionStr ='<span class="cht-lan">';
			dimensionStr += "向度：日常生活活動";
			dimensionStr += '</span>';
			dimensionStr +='<span class="en-lan" style="display: none;">';
			dimensionStr += "Function：Basic activities of dialy living";
			dimensionStr += '</span>';
		}
		
		$("#itemDimension").html(dimensionStr);
		
		
		if (item.startPose) {
			var startPoseSpan = '<span class="cht-lan">';
			startPoseSpan += '起始姿勢：' + item.startPose.split('|')[0];
			startPoseSpan += '</span>';
			startPoseSpan +='<span class="en-lan" style="display: none;">';
			startPoseSpan += 'Start position：' + item.startPose.split('|')[1];
			startPoseSpan += '</span>';
			$("#itemStartPose").html(startPoseSpan);
		} else {
			$("#itemStartPose").html("");
		}
		
		
		if (item.introduction) {
			var introductionSpan = '<span class="cht-lan">';
			introductionSpan += '指導語：' + item.introduction.split('|')[0];
			introductionSpan += '</span>';
			introductionSpan +='<span class="en-lan" style="display: none;">';
			introductionSpan += 'Introduction/Note：' + item.introduction.split('|')[1];
			introductionSpan += '</span>';
			$("#itemIntroduction").html(introductionSpan);	
		}
		
		$("#itemList").html("");

		if (item.option1) {
			var $option1 = '<li class="list-group-item border exam-item" onclick="changeColor(this)">';
			$option1 += '<label class="custom-control custom-radio">';
			$option1 += '<input name="answer" type="radio" class="custom-control-input" id="option1" value="0">';
			$option1 += '<span class="custom-control-indicator"></span>';
			$option1 += '<span class="custom-control-description"><span class="cht-lan">';
			$option1 += item.option1.split("|")[0] + '</span><span class="en-lan" style="display: none;">';
			$option1 += item.option1.split("|")[1] + '</span>';
			$option1 += '</span></label></li>';

			$("#itemList").append($option1);
		}

		if (item.option2) {
			var $option2 = '<li class="list-group-item border exam-item" onclick="changeColor(this)">';
			$option2 += '<label class="custom-control custom-radio">';
			$option2 += '<input name="answer" type="radio" class="custom-control-input" id="option2" value="1">';
			$option2 += '<span class="custom-control-indicator"></span>';
			$option2 += '<span class="custom-control-description"><span class="cht-lan">';
			$option2 += item.option2.split("|")[0] + '</span><span class="en-lan" style="display: none;">';
			$option2 += item.option2.split("|")[1] + '</span>';
			$option2 += '</span></label></li>';

			$("#itemList").append($option2);
		}

		if (item.option3) {
			var $option3 = '<li class="list-group-item border exam-item" onclick="changeColor(this)">';
			$option3 += '<label class="custom-control custom-radio">';
			$option3 += '<input name="answer" type="radio" class="custom-control-input" id="option3" value="2">';
			$option3 += '<span class="custom-control-indicator"></span>';
			$option3 += '<span class="custom-control-description"><span class="cht-lan">';
			$option3 += item.option3.split("|")[0] + '</span><span class="en-lan" style="display: none;">';
			$option3 += item.option3.split("|")[1] + '</span>';
			$option3 += '</span></label></li>';

			$("#itemList").append($option3);
		}

		if (item.option4) {
			var $option4 = '<li class="list-group-item border exam-item" onclick="changeColor(this)">';
			$option4 += '<label class="custom-control custom-radio">';
			$option4 += '<input name="answer" type="radio" class="custom-control-input" id="option4" value="3">';
			$option4 += '<span class="custom-control-indicator"></span>';
			$option4 += '<span class="custom-control-description"><span class="cht-lan">';
			$option4 += item.option4.split("|")[0] + '</span><span class="en-lan" style="display: none;">';
			$option4 += item.option4.split("|")[1] + '</span>';
			$option4 += '</span></label></li>';
			
			$("#itemList").append($option4);
		}
	}

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
			chooseItem($checkedItem.val());

			//window.location.href = "exam2.html";
		} else {
			$("#messageButton").trigger("click");
		}
	}
</script>