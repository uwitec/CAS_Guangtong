$(document).ready(function() {
	var config = {
		url: "http://192.168.0.180:8080/GuangTong/notices/getHistoryNoticesAll"
	}
	var data_this = {
		"currentPage": 3,
		"pageCount": 10,
	};

	$.ajax(config.url, {
		data: data_this,
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			//			if(code == 1) {
			//console.log(JSON.stringify(data));
			//			}
		}
	});
})