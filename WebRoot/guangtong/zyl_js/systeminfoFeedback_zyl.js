$(document).ready(function() {

	config = {
		url: "http://192.168.3.18:8080/GuangTong/"
	}

	$(".loginbtn").click(function() {

		data_zyl = {
			"username": "leon",
			"password": "123456"
			//"username":$("input[type='text']").val()
			//"password": $("input[type='password']").val()
		}

		$.ajax(config.url + "cms/toLogin", {
			data: data_zyl,
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(JSON.stringify(data));
				//设置cookie
				//$.cookie("adminId", data.adminId);
			}
		});
	})

	data_zyl = {
		//获取
		"adminId": $.cookie("adminId")
	}
	$.ajax(config.url + "cms/toUpdateMenuPermission", {
		data: data_zyl,
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			//console.log(JSON.stringify(data));
		}
	});
});