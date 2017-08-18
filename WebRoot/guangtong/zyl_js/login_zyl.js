$(document).ready(
				function() {
				
					$(".loginbtn").click(function() {
						var data_zyl = {
							"username" : $("input[placeholder='登录名']").val(),
							"password" : $("input[type='password']").val()
						};
						loginPost(data_zyl);
					});
					$(document)
							.keydown(
									function(event) {
										if (event.keyCode == 13) {
											var data_zyl = {
												"username" : $(
														"input[placeholder='登录名']")
														.val(),
												"password" : $(
														"input[type='password']")
														.val()
											};
											loginPost(data_zyl);
										}
									});
				});
function loginPost(data_zyl) {
	if($("select[name='systemName']").val() == 1){
		// 设置cookie
		$.cookie("username", data_zyl.username);
		$.ajax(config.url + "cms/login", {
			data : data_zyl,
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				//console.log(data.t.atype);
				if (data.success == true) {
					$.cookie("atype", data.t.atype);
					location.href = "index.html";
				} else {
					alert(data.msg);
				}
			}
		});
	}else{
//		location.href = "http://60.205.151.97:8080/Gspstore/userAction!login.action?cname=admin&cpwd=123456";
		$.ajax("http://60.205.151.97:8080/Gspstore/userAction!login.action", {
			data : {
				"cname":$("input[placeholder='登录名']").val(),
				"cpwd":$("input[type='password']").val()
			},
			type : "post",
			timeout : 10000,
			success : function(data) {
				console.log(data);
				location.href = "http://60.205.151.97:8080/Gspstore/index.jsp";
			}
		});
	}
	
};
