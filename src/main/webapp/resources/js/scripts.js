function logout() {
	window.location.href = "login/logout";
}

function showMember() {
	$.ajax({
		url : "member/showMember",
		type : "POST",
		error : function(e) {

		},
		success : function(data) {
			setContent(data);
		}
	});
}

function showOrganization() {
	$.ajax({
		url : "organization/showOrganization",
		type : "POST",
		error : function(e) {

		},
		success : function(data) {
			setContent(data);
		}
	});
}

function showDataManagement() {
	$.ajax({
		url : "dataManagement/showDataManagement",
		type : "POST",
		error : function(e) {

		},
		success : function(data) {
			setContent(data);
		}
	});
}

function showProgressManagement(subjectId) {
	$.ajax({
		url : "progressManagement/showProgressManagement",
		type : "POST",
		data : {
			subjectId : subjectId
		},
		error : function(e) {

		},
		success : function(data) {
			setContent(data);
		}
	});
}

function startHighReliabilityTest(recordId) {
	console.log("開始高信度測驗:" + recordId);

	$.ajax({
		url : "cat/beginHighReliabilityExam",
		type : "POST",
		data : {
			recordId : recordId
		},
		error : function(e) {

		},
		success : function(data) {
			setContent(data);
		}
	});
}

function startHighValidityTest(recordId) {
	console.log("開始高效率測驗:" + recordId);

	$.ajax({
		url : "cat/beginHighValidityExam",
		data : {
			recordId : recordId
		},
		type : "POST",
		error : function(e) {

		},
		success : function(data) {
			setContent(data);
		}
	});
}

function chooseItem(answer) {
	$.ajax({
		url : "cat/chooseItem",
		type : "POST",
		data : {
			answer : answer
		},
		error : function(e) {

		},
		success : function(data) {
			setContent(data);
		}
	});
}

function setContent(data) {
	$("div#content").html("");
	$("div#content").html(data);
}