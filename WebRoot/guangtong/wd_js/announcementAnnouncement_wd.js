$(document).ready(function() {
	var aIdArr = [];
	// 管理员列表
	function adminList() {
		$.ajax(config.url + 'notices/getAllAdminByPid', {
			type : "POST",
			timeout : 10000,
			success : function(data) {
				//console.log(data);
				if (data.success == true) {
					data.obj == null ? data = data.t : data = data.obj;
					html = "";
					/*for (one in data) {
						html += '<dl>';
						html += '<dt aId="' + data[one].id + '"><i><img src="img/u380.png"/></i><i><img style="height:18px;" src="img/u87.png"/></i><b><img src="img/uncherked.jpg"/></b>' + data[one].name + '</dt>';
						html += '<dd>';
						for (two in data[one].children) {
							html += '<dl>';
							html += '<dt aId="' + data[one].children[two].id + '"><i><img style="height:18px;" src="img/u91.png"/></i><b><img src="img/uncherked.jpg"/></b>' + data[one].children[two].name + '</dt>';
							html += '</dl>';
						}
						html += '</dd>';
						html += '</dl>';
					}*/
					for (one in data) {
						//console.log(data[one]);
						html += "<dl>";
						html += '<dt aId="' + data[one].id + '"><i><img src="img/u380.png"></i><i><img style="height:18px;" src="img/u87.png"/></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + data[one].name + '</dt>';
						html+="<dd>";
						for(two in data[one].children){
							//console.log(data[one][two].name);
							html +="<dl>";
							html +='<dt aId="' + data[one].children[two].id + '"><i><img src="img/u380.png"></i><i><img style="height:18px;" src="img/u91.png"/></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + data[one].children[two].name + '</dt>';
							html +="<dd>";
							for(three in data[one].children[two].children){
								html +="<dl>";
									html +='<dt aId="' + data[one].children[two].children[three].id + '"><i><img src="img/u380.png"></i><i><img style="height:18px;" src="img/u91.png"/></i><b class="cherked_xk"><img src="img/uncherked.jpg" defaultChecked/></b>' + data[one].children[two].children[three].name + '</dt>';
								html+="</dl>";
							}
							html+="</dd>";
							html+="</dl>";
							
						}
						html+="</dd>";
						html+="</dl>";
					}
					html += "</dd>";
					html += "</dl>";
					$(".dendrogram_xk dl dd").html(html);
				}
			}
		});
	}
	;

	function getAdminIdArr() {
		$("a.button.blue").click(function() {
			aIdArr = [];
			for (var i = 0; i < $("#adminList dt").length; i++) {
				if ($("#adminList dt:eq(" + i + ") b img").attr("src") == "img/cherked.jpg") {
					if ($("#adminList dt:eq(" + i + ")").attr("aId") != undefined && $("#adminList dt:eq(" + i + ")").attr("aId") != null) {
						aIdArr.push($("#adminList dt:eq(" + i + ")").attr("aId"));
					}
				}
			}
			sendAnnouncement();

		});
	}

	function sendAnnouncement() {
		data = {
			"receivers" : "" + aIdArr + "",
			"title" : $('input[placeholder="请输入标题"]').val(),
			"content" : $('textarea[placeholder="请输入发布内容"]').val(),
		// "issueTime": $('input[placeholder="发布时间"]').val()
		}
		$.ajax(config.url + 'notices/insertNotices', {
			data : data,
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				alert(data.msg);
			}
		});
	}
	adminList();
	getAdminIdArr();
});
