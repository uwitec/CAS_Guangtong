//获取当前日期
date_xk = new Date();
thisDate_xk = date_xk.getFullYear();

if((date_xk.getMonth() < 9)) {
	thisDate_xk += "-0";
} else {
	thisDate_xk_xk += "-";
};
thisDate_xk += (date_xk.getMonth() + 1);

if((date_xk.getDate() < 10)) {
	thisDate_xk += "-0";
} else {
	thisDate_xk += "-";
};
thisDate_xk += date_xk.getDate();
//
$(document).on("click", ".submit_xk", function() {
	if(0) {} else {
		var thisjson_reg_xk = {
			"command": "regist",
			"tel": $(".tel_xk").val(),
			"contactName": $(".contactName_xk").val(),
			"password": $(".password_xk").val(),
			"face": "a.jpg",
			"addr": $(".addr_xk").val(),
			"wechatAccount": $(".wechatAccount_xk").val(),
			"qqAccount": $(".qqAccount_xk").val(),
			"isDel": 0,
			"gender": $(".gender_xk").val(),
			"createTime": thisDate_xk,
			"cType": $(".cType_xk").val(),
			"balance": 0.00
		};

		//console.log(JSON.stringify(thisjson_reg_xk));
		var config = {
			url: "http://192.168.0.122:8080/Tjson/"
		}
		$.ajax(config.url + "loginServlet", {
			data: thisjson_reg_xk,
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(code == 1) {
					console.log(data.msg);
				}

			}
		});
	};

});

})