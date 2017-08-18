$(document).ready(function() {
	//console.log($.cookie('username'));
	
	if($.cookie('username') == "null" || $.cookie('username') == undefined){
		location.href = "login.html";
		return;
	}
	date_now_xk = new Date();
	date_year_xk = date_now_xk.getFullYear();
	date_month_xk = date_now_xk.getMonth() + 1;
	date_date_xk = date_now_xk.getDate();
	date_day_xk = new Date(date_year_xk, date_month_xk - 1, "1");
	date_day_xk = date_day_xk.getDay();
	date_days_xk = new Date(date_year_xk, date_month_xk, 0);
	date_days_xk = date_days_xk.getDate();
	//左边距调整
	data_zyl = {
		//获取
		"username": $.cookie("username")
	}
	$(".gt_wl_top_right .user").text(data_zyl.username);
	$(".popalarm_xk").css("right",$(".gt_wl_top_right a span").width()-46);
	$(".popmessage_xk").css("right",$(".gt_wl_top_right a span").width()+24);
	$(".popalarmbtn_xk").css("right",$(".gt_wl_top_right a span").width()+158);
	$(".popmessagebtn_xk").css("right",$(".gt_wl_top_right a span").width()+224);
	$.ajax(config.url + "cms/indexGetMenu", { //toUpdateMenuPermission
		data: data_zyl,
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			var path_left = ["left.html"];
			$.get(path_left[0]).success(function(content) {
				if(content.indexOf("leftupdate") > 0) {
					leftload_xk(data.obj);
					
				} else {
					$(".gt_wl_left").html(content);
				}
				//左边距调整
				$(".gt_wl_left,.gt_wl_right").height($(window).height()-70);
				$(".gt_wl_left dl").height($(".gt_wl_left  dl dt").outerHeight());
				$(".gt_wl_left dl dd").height($(".gt_wl_left dl dd span").outerHeight());
				
				//左侧载入
				//书写日期标题
				$(".datetitle_xk .datetitletxt_xk").html(date_year_xk + "年&nbsp;" + date_month_xk + "月")
				//生成日历
				date_xk();
				//日历内容
				//获取未读消息
				getNoReadNoticesNum();
				alarmShow();
				dropLoading();
				$(".gt_wl_left dt").each(function(){
					if($(this).text()=="车辆实时监控"){
						$(this).addClass("on_xk");
						$(this).parent("dl").css("height","auto");
						$(this).siblings("dd").children("span").click();
					}
				})
			});
		}
	});
});
function popup(a) {
	if(a != null & a != "") {
		$.ajax(a, { 
			type: "get",
			async:false,
			timeout: 10000,
			success: function(content) {
			$(".popwindow_xk .popup_xk").html(content);
			popupresize();
			$(".popwindow_xk").css("visibility", "visible");
			$(".popwindow_xk").show();
			popupresize();
			}
			});
	};
};

function popup2(a) {
	if(a != null & a != "") {
		$.ajax(a, { 
			type: "get",
			async:false,
			timeout: 10000,
			success: function(content) {
			$(".popwindow2_xk .popup_xk").html(content);
			popupresize();
			$(".popwindow2_xk").css("visibility", "visible");
			$(".popwindow2_xk").show();
			popupresize();
			}
		});
	};
};
$(document).on("click", 'img[src="img/close.png"]', function() {
	$(this).parents(".popwindow_xk,.popwindow2_xk").css("visibility", "hidden");
	$(this).parents(".popwindow_xk,.popwindow2_xk").hide();
	$(this).parents(".popup_xk").html("");
});
$(document).on("click", '.button:contains("放弃")', function() {
	$(this).parents(".popwindow_xk,.popwindow2_xk").css("visibility", "hidden");
	$(this).parents(".popwindow_xk,.popwindow2_xk").hide();
	$(this).parents(".popup_xk").html("");
});
$(document).on("click", '.button:contains("取消")', function() {
	$(this).parents(".popwindow_xk,.popwindow2_xk").css("visibility", "hidden");
	$(this).parents(".popwindow_xk,.popwindow2_xk").hide();
	$(this).parents(".popup_xk").html("");
});

function popupresize() {
	ifHasMap();
	var window_width = $(window).width();
	var window_height = $(window).height();
	$(".popup_xk").each(function() {
		var alert_width = $(this).width();
		var alert_height = $(this).height();
		$(this).css({
			"margin-left": Math.max.apply(Math, [(window_width - alert_width) / 2, 0]),
			"margin-top": Math.max.apply(Math, [(window_height - alert_height) / 2, 0])
		});
	});
	$(".gt_wl_left,.gt_wl_right").height(window_height - 72);
};
$(window).resize(popupresize);
//左侧展开和收起动画
$(document).on("click", ".gt_wl_left dl dd span:not(.on_xk)", function() {
	$(".gt_wl_left dl dd span.on_xk").parent("dd").animate({
		"height": $(this).outerHeight()
	}, 300);
	//没有兄弟的a标签时自身触发函数；
	if($(this).parent().children("a").length == 0) {
		listshowup();
		var htmlkey = $(this).text();
		if(path_right[htmlkey] != null & path_right[htmlkey] != "") {
			$.get(path_right[htmlkey]).success(function(content) {
				$(".gt_wl_right .listbox_xk").html(content);
				ifHasMap();
			});
		};

		var html = '<span><img src="img/u268.png" alt="" /></span><span>主页</span>&gt;<span>' + $(this).parent("dd").siblings("dt").text() + '</span>&gt;<span>' + $(this).text() + '</span>'
		$(".right_top_xk").html(html);

		$(".gt_wl_left dl dd a").removeClass("on_xk");
	};
	$(".gt_wl_left dl dd span.on_xk").removeClass("on_xk");
	$(this).addClass("on_xk");
	var curheight_xk = $(this).height();
	var autoheight_xk = $(this).parent("dd").css('height', 'auto').height();
	$(this).parent("dd").height(curheight_xk).stop(true, true).animate({
		"height": autoheight_xk
	}, 300);
});
$(document).on("click", ".gt_wl_left dl dd span.on_xk", function() {
	if($(this).parent().children("a").length != 0) {
		$(this).removeClass("on_xk");
		$(this).parent("dd").animate({
			"height": $(this).outerHeight()
		}, 300);
	} else {
		var htmlkey = $(this).text();
		if(path_right[htmlkey] != null & path_right[htmlkey] != "") {
			$.get(path_right[htmlkey]).success(function(content) {
				$(".gt_wl_right .listbox_xk").html(content);
				ifHasMap();
			});
		};

	};

});
//调用不同右侧内容的函数组
function listshowup() {
	$(".gt_wl_right .listbox_xk").show();
}
//点击三级菜单切换亮度和右侧内容
$(document).on("click", ".gt_wl_left dl dd a", function() {
	listshowup();
	if(!$(this).hasClass("on_xk")) {
		var html = '<span><img src="img/u268.png" alt="" /></span><span>主页</span>&gt;<span>' + $(this).parent("dd").siblings("dt").text() + '</span>&gt;<span>' + $(this).siblings("span").text() + '</span>&gt;<span>' + $(this).text() + '</span>'
		$(".right_top_xk").html(html);
	};

	$(".gt_wl_left dl dd a").removeClass("on_xk");
	$(this).addClass("on_xk");
	var htmlkey = $(this).text();
	if(path_right[htmlkey] != null & path_right[htmlkey] != "") {
		$.get(path_right[htmlkey]).success(function(content) {
			$(".gt_wl_right .listbox_xk").html(content);
			ifHasMap();
		});
	};

});
//

//
$(document).on("click", ".gt_wl_left dl dt.on_xk", function() {
	$(this).removeClass("on_xk");
	$(this).children("i").children("img").attr("src", "img/u40.png");
	$(this).parent("dl").animate({
		"height": $(this).outerHeight()
	}, 300);
});

$(document).on("click", ".gt_wl_left dl dt:not(.on_xk)", function() {
	$(".gt_wl_left dl dt.on_xk i img").attr("src", "img/u40.png");
	$(".gt_wl_left dl dt.on_xk").parent().stop(true, true).animate({
		"height": $(this).outerHeight()
	}, 300)
	$(".gt_wl_left dl dt").removeClass("on_xk");
	$(this).addClass("on_xk");
	$(this).children("i").children("img").attr("src", "img/u50.png");
	var curheight_xk = $(this).parent("dl").height();
	var autoheight_xk = $(this).parent("dl").css('height', 'auto').height();

	$(this).parent("dl").height(curheight_xk).stop(true, true).animate({
		"min-height": autoheight_xk
	}, 300, function() {
		$(this).attr("style", "height:auto");
	});

});

//点击弹出框外空间自动隐藏弹出框
$(document).on("click", ".bgcover", function() {
	if(!$(this).siblings(".popup_xk").children(".pophead_xk").children("i").children("img[src='img/close.png']").length) {
		$(this).parent(".popwindow_xk,.popwindow2_xk").css("visibility", "hidden");
		$(this).siblings(".popup_xk").html("");
	};
})
//

alarmIsShown = false;
messageIsShown = false;

function alarmShow() {
	$(document).on("click",'.popalarmbtn_xk', function() {
		$(".popmessage_xk .triangle_xk,.popmessage_xk .messagebox_xk").hide();
		$(".popmessagebtn_xk").removeClass("on_xk");
		messageIsShown = false;
		if(alarmIsShown) {
			$(".popalarm_xk .triangle_xk,.popalarm_xk .messagebox_xk,.popalarm_xk .messagebottom_xk").hide();
			$(".popalarmbtn_xk").removeClass("on_xk");
			alarmIsShown = false;
		} else {
			$(".popalarm_xk .triangle_xk,.popalarm_xk .messagebox_xk,.popalarm_xk .messagebottom_xk").show();
			$(".popalarmbtn_xk").addClass("on_xk");
			alarmIsShown = true;
			$(".popalarm_xk .messagebox_xk ul li").attr("isRead", "yes");
		}
	})
}
//
var messageTimeCount;

function messageShow() {
	$(".popmessagebtn_xk").unbind("click").bind("click", function() {
//		$(".popalarm_xk .triangle_xk,.popalarm_xk .messagebox_xk,.popalarm_xk .messagebottom_xk").hide();
//		$(".popalarmbtn_xk").removeClass("on_xk");
//		alarmIsShown = false;
//		clearInterval(messageTimeCount);
//		if(messageIsShown) {
//			messageTimeCount = setInterval("getNoReadNoticesNum()", 3000);
//			$(".popmessage_xk .triangle_xk,.popmessage_xk .messagebox_xk").hide();
//			$(".popmessagebtn_xk").removeClass("on_xk");
//			messageIsShown = false;
//		} else {
//			$(".popmessage_xk .triangle_xk,.popmessage_xk .messagebox_xk").show();
//			$(".popmessagebtn_xk").addClass("on_xk");
//			messageIsShown = true;
//			showMessageList();
//		}
		$(".gt_wl_left dt:contains('公告通知发布'):not('.on_xk')").click();
		$(".gt_wl_left dd span:contains('公告信息')").click();
	})
}


//登出
$(document).on('click', ".popsignoutbtn_xk", function() {
	$.ajax(config.url + "cms/exit", {
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				$.cookie('username', null); 
				location.href = "login.html";
			}
		}
	});
})
//顶部信息和报警弹出栏
$(document).on("click", ".gt_wl_middle:not('.on_xk')", function() {
	$(this).addClass("on_xk");
	$(".gt_wl_left").stop().animate({ "left": "-270px" }, 300);
	$(".gt_wl_right").stop().animate({ "margin-left": "0" }, 300);
});
$(document).on("click", ".gt_wl_middle.on_xk", function() {
	$(this).removeClass("on_xk");
	$(".gt_wl_left").stop().animate({ "left": "0" }, 300);
	$(".gt_wl_right").stop().animate({ "margin-left": "270px" }, 300);
});
//地图更大
function ifHasMap() {
	if($(".map_xk").length == 0) {
		$(".listbox_xk").removeClass("on_xk").height($(window).height() - 150);
		if($(".listgrp_xk .page_xk").length){
			$(".gt_wl_right .page_xk").prev("div").height($(window).height()-150-$(".gt_wl_right .listtitle_xk").length*40-$(".gt_wl_right .page_xk").prev("div").prevAll(".inquirybox_xk").length*56-51);
		}else{
			$(".listgrp_xk>div").height($(window).height()-190);
		}
	} else if($('.map_top_xk').css("display")=="none"){
		$(".listbox_xk").addClass("on_xk");
		$(".listbox_xk").height($(window).height() - 110);
		$(".map_xk").height(800);
		$(".listgrp_xk").css("height","auto");
		$(".map_right_xk").height(792);
		$(".map_right_xk .dendrogram_xk").height(697);
	}else{
		$(".listbox_xk").addClass("on_xk");
		$(".listbox_xk").height($(window).height() - 110);
		$(".map_xk").css("height","100%");
		$(".listgrp_xk").css("height","100%");
		$(".map_right_xk").height($(window).height() - 118);
		$(".map_right_xk .dendrogram_xk").css("overflow-y","auto").height($(window).height() - 209);
	};
};

//setInterval("getNoReadNoticesNum()", 3000);
function getNoReadNoticesNum() {
	$.ajax(config.url + "notices/getNoReadNoticesNum", {
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			//console.log(data);
			if(data.success == true) {
				data.obj == null ? data = data.t : data = data.obj;
				messageShow();
				if(data < 1) {
					$(".popmessage_xk .reddot_xk").css('visibility', 'hidden');
					$("popmessagebtn_xk").unbind("click");
				} else {
					$(".popmessage_xk .reddot_xk").css('visibility', 'visible');
					$(".popmessage_xk .reddot_xk").text(data);
					$("popmessagebtn_xk").bind("click");
				}
			}
		}
	});
};

function showMessageList() {
	$.ajax(config.url + "notices/getNoReadNoticesById", {
		data: {
			"id": 0
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			//console.log(data);
			if(data.success == true) {
				data.obj == null ? data = data.t : data = data.obj;
				var html = "";
				for(one in data) {
					html += '<li mid="' + data[one].id + '"><div><b><img src="img/消息.png"/></b>';
					html += data[one].content;
					html += '</div><i>';
					if(!data[one].isRead){
					html +='<img src="img/new.png"/>';
					};
					html +='</i></li>';
				}
				$(".popmessage_xk .messagebox_xk ul").html(html);
			}
		}
	});
};
var flashLight;

function showAlarmList() {
	$.ajax(config.url + "forms/getALLNewestAlarm", {
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success == true) {
				data.obj == null ? data = data.t : data = data.obj;
				if(paramSet.alarmsound == 1){
					if(data.length >0 ){
						var auto = document.getElementById("alarmSound");
						auto.play();
					}
				}
				clearInterval(flashLight);
				if(data.length != 0) {
					flashLight = setInterval(function() {
						if(paramSet.alarmlamp == 1){
							if($(".gt_wl_top_right a:eq(1) i img").attr("src") == "img/tzldtb_u153.png") {
								$(".gt_wl_top_right a:eq(1) i img").attr("src", "img/tzldtbh.png");
							} else {
								$(".gt_wl_top_right a:eq(1) i img").attr("src", "img/tzldtb_u153.png");
							}
						}
					}, 1000)
					var html = "";
					for(one in data) {
						html += '<li aid="' + data[one].id + '" isRead="no" title="' + data[one].plateNo + data[one].type + '"><div><b><img src="img/报警.png"/></b>';
						html += data[one].plateNo + data[one].alarm;
						html += '</div><i></i></li>';
						$(".popalarm_xk .messagebox_xk ul").append(html);
						if($(".popalarm_xk .messagebox_xk ul li").length > 20) {
							$(".popalarm_xk .messagebox_xk ul li").eq(0).remove();
						}
					}
				}
			}
		}
	});
}
//消息列表滚动动作
function dropLoading() {
	$(".popmessage_xk .messagebox_xk ul").scroll(function() {
		var divHeight = $(this).height();
		var contentHeight = $(this).get(0).scrollHeight;
		var scrollTop = $(this).scrollTop();
		//console.log(contentHeight - divHeight - scrollTop);
		if(contentHeight - divHeight - scrollTop == 0) {
			messageScrollDown();
		}
	});
}
$(document).on("click",".popmessage_xk .messagebox_xk ul li",function(){
	$.ajax(config.url + "notices/getNoticesModelById", {
		data: {
			"id": $(this).attr("mid"),
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success == true) {
				data.obj == null ? data = data.t : data = data.obj;
				$(".popmessage_xk .messagebox_xk ul li[mid="+data.id+"] i>img").remove();
				$(".popmessagebtn_xk.on_xk").click();
				var thislink={
						"删除":"messageDeleteconfirm.html",
						"sider":"popupDetailsmessage.html"
				}
				$(".popwindow_xk .popup_xk").html(popup(thislink["sider"]));
				
				$(".popwindow_xk .popup_xk .popbody_xk ul li a:contains('消息标题：')+span").html(data.title);
				$(".popwindow_xk .popup_xk .popbody_xk ul li a:contains('消息内容：')+span>p").html(data.content);
				$(".popwindow_xk .popup_xk .popbody_xk ul li a:contains('发布者：')+span").html(data.senderAdminName);
				$(".popwindow_xk .popup_xk .popbody_xk ul li a:contains('发布时间：')+span").html(data.issuetime);
				$(".popwindow_xk .popup_xk .pophead_xk i a:contains('删除')").click(function(){
					$(".popwindow2_xk .popup_xk").html(popup2(thislink["删除"]));
					$(".popwindow2_xk .popup_xk a.button:contains('删除')").click(function(){
						var thisbtn_xk=$(this);
						$.ajax(config.url + "notices/deleteAdminNotices", {
							data: {
								"id": data.id,
							},
							dataType: "json",
							type: "POST",
							timeout: 10000,
							success: function(data) {
								if(data.success){
									alert("删除成功！");
									$(thisbtn_xk).parents(".popwindow2_xk").siblings(".popwindow_xk").find(".popup_xk").siblings(".bgcover").click();
									$(thisbtn_xk).parents(".popbody_xk").siblings(".pophead_xk").find("i>img[src='img/关闭.png']").click();
								};
							}});
					});
					
					
				});
			}
		}
	});
});
//消息滚动
function messageScrollDown() {
	$.ajax(config.url + "notices/getNoReadNoticesById", {
		data: {
			"id": $(".popmessage_xk .messagebox_xk li:last").attr("mid"),
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success == true) {
				var html = "";
				for(one in data.obj) {
					html += '<li mid="' + data.obj[one].id + '"><div><b><img src="img/消息.png"/></b>';
					html += data.obj[one].content;
					html += '</div><i>';
					if(!data.obj[one].isRead){
					html +=	'<img src="img/new.png"/>'
					};
					html += '</i></li>';
				}
				$(".popmessage_xk .messagebox_xk ul li:last").after(html);
			}
		}
	});
}
$(document).on('click', '.popalarm_xk .messagebottom_xk', function() {
	$(".popalarm_xk .messagebox_xk ul li").remove();
});
//连接服务器点击事件
linkServer = false;
$(document).on('click', 'img[src="img/sblj.png"]', function() {
	if(linkServer == false) {
		linkOrDisconnectServer("open");
	} else {
		linkOrDisconnectServer("close");
	}
})
//连接断开服务器
function linkOrDisconnectServer(status) {
	$.ajax(config.url + "realTime/connectionServer", {
		data: {
			"status": status,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success == true) {
				linkOrDisconnectServerMethod(status);
			} else {
				alert(data.msg);
			}
		}
	});
}
//连接服务器或断开服务器后执行内容
var alarmCount;

function linkOrDisconnectServerMethod(status) {
	clearInterval(alarmCount);
	clearInterval(flashLight);
	if(status == "open") {
		linkServer = true;
		$('img[src="img/sblj.png"]').attr("title", "断开服务器");
		showAlarmList();
		alarmCount = setInterval(function() {
			showAlarmList();
		}, 30000);
	} else {
		linkServer = false;
		$('img[src="img/sblj.png"]').attr("title", "连接服务器");
		$(".gt_wl_top_right a:eq(1) i img").attr("src", "img/tzldtb_u153.png");
	}
}
//只要设计右侧栏服务器的状态判断
function ServerStatus() {
	if(linkServer == false) {
		$('img[src="img/sblj.png"]').attr("title", "连接服务器");
	} else {
		$('img[src="img/sblj.png"]').attr("title", "断开服务器");
	}
}
//查找地图参数
paramSet = "";
function paramSetting(){
	$.ajax(config.url + "port/getStyleByUserId", {
		dataType: "json",
		type: "POST",
		async:false,
		timeout: 10000,
		success: function(data) {
			if(data.success == true){
				paramSet = data.t;
			}
		}
	});
}
paramSetting();