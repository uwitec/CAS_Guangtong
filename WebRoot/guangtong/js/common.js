config = {
	 url: "http://60.205.151.97:8080/GuangTong/"
	//url: "http://60.205.151.97:8080/GuangTong/"
}

function date_xk() {
	var html_xk = "";	
	var setnum_xk = 0;
	for(var i = 0; i < date_day_xk; i++) {
		html_xk += '<span></span>';
		setnum_xk++;
	};

	for(var i = 1; i < date_days_xk + 1; i++) {
		html_xk += '<span class="num_xk'
		if(i == date_date_xk) {
			html_xk += ' on_xk';
		}
		html_xk += '">' + i + '</span>';
		setnum_xk++;
	};
	for(var i = 0; i < setnum_xk % 7; i++) {
		html_xk += '<span></span>';
		setnum_xk++;
	};
	$(".datenum_xk").html(html_xk);
};
// 表格行点击变绿
$(document).on("click", ".list_xk tbody tr", function() {
	$(this).siblings("tr").removeClass("highgreen");
	$(this).addClass("highgreen");
});
//禁用右键菜单
$(document).ready(function(){
    $(document).bind("contextmenu",function(e){
        return false;
    });
});
//
// 使用each方法在按钮点击时提交选中内容数据。
// $(document).on("click", ".button.peach", function() {
// var jojo = [];
// $(".list_xk tbody img[src='img/cherked.jpg']").each(function() {
// var html = $(this).parent().siblings(".num_xk").text();
// jojo.push(html);
// });
// alert(jojo);
// })
// 切换表单复选框
$(document).on("click", "img[src='img/uncherked.jpg']", function() {
	if($(this).parent("td").parent("tr").is(".listheader_xk")) {
		var thisindex_xk = $(this).parent("td").index();
		$(this).attr("src", "img/cherked.jpg");
		$(this).parent("td").parent("tr").parent("thead").siblings("tbody").children("tr").each(function() {
			$(this).children("td").eq(thisindex_xk).children("img").attr("src", "img/cherked.jpg")
		});
	} else if($(this).parent().is("b")) {
		// 如果只有子集有复选框，父集没有，则变更为单选，否则正常
		var type_xk = true;
		$(this).parents(".dendrogram_xk").find("b").each(function() {
			type_xk *= ($(this).parent("dt").siblings("dd").length == 0);
		});
		if(type_xk) {
			$(this).parents(".dendrogram_xk").find("img[src='img/cherked.jpg']").attr("src", "img/uncherked.jpg");
		}
		$(this).attr("src", "img/cherked.jpg");
		$(this).parent("b").parent("dt").siblings("dd").find("img[src='img/uncherked.jpg']").attr("src", "img/cherked.jpg");
		$(this).parent("b").parent("dt").parent("dl").parents("dl").children("dt").children("b").children('img[src="img/uncherked.jpg"]').attr("src", "img/cherked.jpg");
	} else {
		$(this).attr("src", "img/cherked.jpg");
	};
	if($(this).is("[carListClick]")) {
		try {
			carListClick();
		} catch(e) {
			console.log(e);
		}

	}
});
//
$(document).on("click", "img[src='img/cherked.jpg']", function() {
	if($(this).parent("td").parent("tr").is(".listheader_xk")) {
		var thisindex_xk = $(this).parent("td").index();
		$(this).attr("src", "img/uncherked.jpg");
		$(this).parent("td").parent("tr").parent("thead").siblings("tbody").children("tr").each(function() {
			$(this).children("td").eq(thisindex_xk).children("img").attr("src", "img/uncherked.jpg")
		});
	} else if($(this).parent().is("b")) {
		$(this).attr("src", "img/uncherked.jpg");
		$(this).parent("b").parent("dt").siblings("dd").find("img[src='img/cherked.jpg']").attr("src", "img/uncherked.jpg");
		// 筛选所有祖辈的选中状态的栏目分别进行判断是不是要变更为未选中
		$(this).parent("b").parent("dt").parent("dl").parents("dl").children("dt").children("b").children('img[src="img/cherked.jpg"]')
			.each(function() {
				var shoulduncherked = true;
				// 为每个当前栏目寻找后辈的所有dt
				$(this).parent("b").parent("dt").siblings("dd").find("dt").each(function() {
					// 判断这些dt当中没有子栏目的那些如果未选中则返回true否则返回false;结果相乘，也就是当所有的结果都是true时才会返回true
					shoulduncherked *= (!$(this).siblings("dd").length == 0 || $(this).children("b")
						.children("img[src='img/cherked.jpg']").length == 0);
				});
				// 如果是true，则该栏目变更为未选中状态;
				if(shoulduncherked) {
					$(this).attr("src", "img/uncherked.jpg");
				};
			});
	} else {
		$(this).attr("src", "img/uncherked.jpg");
	};
	if($(this).is("[carListClick]")) {
		try {
			carListClick();
		} catch(e) {

		}

	}
});
// 展开收缩树状图 -- 显示、隐藏
$(document).on("click", 'img[src="img/u380.png"]', function() {
	$(this).parent("i").parent("dt").siblings("dd").hide();
	$(this).attr("src", "img/u388.png");
});
$(document).on("click", 'img[src="img/u388.png"]', function() {
	$(this).parent("i").parent("dt").siblings("dd").show();
	$(this).attr("src", "img/u380.png");
});

// 点击选项卡弹出内容
$(document).on("click", ".tab_xk li.tab_clicktab_xk:not('.on_xk')", function() {
	$(this).siblings("li.tab_clicktab_xk.on_xk").removeClass("on_xk");
	$(this).addClass("on_xk");
	$(this).parent(".tab_xk").next(".tab_main_xk").html(tabctrl_xk[$(this).text()]);
	if($(this).siblings('.tab_closebtn_xk').is(":not(':animated')")) {
		if($(this).siblings('.tab_closebtn_xk').length > 0 &&
			$(this).siblings('.tab_closebtn_xk').css("display") == "none") {
			$(this).parent(".tab_xk").parent("#mapbottomtab").stop().animate({
				"bottom": 0
			}, 300);
			$(this).siblings('.tab_closebtn_xk').fadeIn(300);
		};

	};
});
// 点击选项卡关闭框，自动隐藏
$(document).on("click", ".tab_xk li.tab_closebtn_xk", function() {
	$(this).siblings(".tab_clicktab_xk.on_xk").removeClass("on_xk");
	$("#mapbottomtab").hide();
	// $(this).parent(".tab_xk").parent("#mapbottomtab").stop().animate({
	// "bottom":
	// -$(this).parent(".tab_xk").parent(".map_bottom_xk").outerHeight() },
	// 300);
	// $(this).fadeOut(300);
});
$(document).on("click", ".tab_xk li.tab_minimize_xk:not('.on_xk')", function() {
	$(this).addClass("on_xk");
	$(this).children("img").attr("src", "img/hf.png")
	$(this).parent(".tab_xk").parent("#mapbottomtab").stop().animate({
		"bottom": -$(this).parent(".tab_xk").parent(".map_bottom_xk").outerHeight()
	}, 300);
	// $(this).fadeOut(300);
});
$(document).on("click", ".tab_xk li.tab_minimize_xk.on_xk", function() {
	$(this).removeClass("on_xk");
	$(this).children("img").attr("src", "img/zxh.png");
	$(this).parent(".tab_xk").parent("#mapbottomtab").stop().animate({
		"bottom": "0"
	}, 300);
	// $(this).fadeOut(300);
});
// 点击控制模组按钮切换内容
$(document).on("click", '.positionbtn:not(".on_xk")', function() {
	$(this).siblings(".positionbtn").removeClass("on_xk");
	$(this).addClass("on_xk");
	var thisindex_xk = Math.max($(this).index(
		'#mapdefaulttab .positionbtn'), $(this).index(
		'#mapbottomtab .positionbtn'));
	$(this).siblings(".ctrlgrp").children("table").removeClass(
		"on_xk");
	$(this).siblings(".ctrlgrp").children("table").eq(
		thisindex_xk).addClass("on_xk");
});
// 双击监控自动只显示当前监控并放大
$(document).on("dblclick", ".camerabox_xk:not('.on_xk')", function() {
	$(this).siblings(".camerabox_xk").hide();
	$(this).css({
		"width": "100%",
		"height": "100%"
	}).show().addClass("on_xk");
});
// 双击放大的监控自动恢复正常
$(document).on("dblclick", ".camerabox_xk.on_xk", function() {
	$(this).css({
		"width": "50%",
		"height": "50%"
	}).removeClass("on_xk");
	$(this).siblings(".camerabox_xk").show();
});
// 当右侧为非静态时，鼠标移入和移出会自动展开和收缩
$(document).on("mouseenter", ".map_right_xk:not('.static')", function() {
	$(this).stop().animate({
		"right": "0",
		"opacity": "1",
		"-ms-filter": "progid:DXImageTransform.Microsoft.Alpha(opacity=100)"
	}, 300);
	$(".map_left_xk .map_main_xk").stop().animate({
		"margin-right": 2 + $(this).outerWidth()
	}, 300);
	$(".map_bottom_xk .tab_main_xk").stop().animate({
		"margin-right": 2 + $(this).outerWidth()
	}, 300);
});
$(document).on("mouseleave", ".map_right_xk:not('.static')", function() {
	$(this).stop().animate({
		"right": -$(this).outerWidth() + 8,
		"opacity": "0",
		"-ms-filter": "progid:DXImageTransform.Microsoft.Alpha(opacity=0)"
	}, 300);
	$(".map_left_xk .map_main_xk").stop().animate({
		"margin-right": 10
	}, 300);
	$(".map_bottom_xk .tab_main_xk").stop().animate({
		"margin-right": 10
	}, 300);
});

/*// 当点击地图顶部按钮时，自动隐藏选项卡组，生成相应的功能
$(document).on("click", ".mapbtn_xk li:not('.on_xk')", function() {
	if($(this).text() == "路线管理") {
		$("#startPonit").show();
		$("#endPonit").show();
	}
	// 隐藏选项卡组
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomtab").children(".tab_xk").stop().fadeOut(300);
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomtab").children(".tab_main_xk").stop().fadeOut(300);
	// 移除任何一个没有选项卡标签的界面
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomnottab").children(".tab_main_xk").remove();
	// 其他按钮半透明化
	$(this).siblings(".on_xk").removeClass("on_xk");
	$(this).addClass("on_xk");
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomnottab").html(mapbtn($(this).text()));
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomnottab").stop().fadeIn(300);
});*/
// 点击已经显示的顶部按钮，则隐藏内容，并且显示默认选项卡
$(document).on("click", ".mapbtn_xk li.on_xk", function() {
	if($(this).text() == "路线管理") {
		$("#startPonit").hide();
		$("#endPonit").hide();
	}
	$(this).removeClass("on_xk");
	// 移除任何一个没有选项卡标签的界面
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomnottab").stop().fadeOut(300);
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomtab").children(".tab_xk").stop().fadeIn(300);
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomtab").children(".tab_main_xk").stop().fadeIn(300);
});
// 右侧顶部按钮调入功能
// 当点击地图顶部按钮时，自动隐藏选项卡组，生成相应的功能
$(document).on("click", ".mapbtn_xk li:not('.on_xk')", function() {
	if($(this).text() == "路线管理" && ($(".tab_main_xk .specialleft_xk:eq(0) option:selected").text().indexOf("创建") >= 0 || $(".tab_main_xk .specialleft_xk:eq(0) option:selected").text()== "")) {
		$("#startPonit").show();
		$("#endPonit").show();
	} else {
		$("#startPonit").hide();
		$("#endPonit").hide();
	}
	// 隐藏选项卡组
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomtab").children(".tab_xk").stop().fadeOut(300);
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomtab").children(".tab_main_xk").stop().fadeOut(300);
	// 移除任何一个没有选项卡标签的界面
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomnottab").children(".tab_main_xk").remove();
	// 其他按钮半透明化
	$(this).siblings(".on_xk").removeClass("on_xk");
	$(this).addClass("on_xk");
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomnottab").html(mapbtn($(this).text()));
	$(this).parent(".mapbtn_xk").parent(".map_top_xk").siblings("#mapbottomnottab").stop().fadeIn(300);
});

// 右侧顶部按钮调入功能
$(document).on("click", 'img[src="img/sz.png"]', function() {
	$(".popwindow_xk .popup_xk").html(popup("popupMapsetup.html"));
});
$(document).on("click", 'img[src="img/zl.png"]', function() {
	$(".popwindow_xk .popup_xk").html(popup("popupVehiclecontrol.html"));
});
$(document).on("click", 'img[src="img/cmd.png"]', function() {
	if(linkServer == false){
		alert("服务器断开，请链接服务器!");
	}else{
		$(".popwindow_xk .popup_xk").html(popup("popupCommandSend.html"));
	}
});
$(document).on("click", 'img[src="img/sx.png"]', function() {
	$(".popwindow_xk .popup_xk").html(popup("popupSiftvehicle.html"));
});
$(document).on("click", 'img[src="img/bjxx.png"]', function() {
	$(".popwindow_xk .popup_xk").html(popup("popupAlarmsetup.html"));
});
$(document).on("click", 'img[src="img/sscl.png"]', function() {
	window.open("areaSearch.html");
});
$(document).on("click", 'img[src="img/dw.png"]', function() {
	// if($("#positionsearch").length == 0) {
	// $(".map_main_xk").append("<input type='text' class='search'
	// id='positionsearch' placeholder='输入地址'/>");
	// } else {
	// $("#positionsearch").remove();
	// };
	$("#positionsearch").toggle();
	$("#positionsearch").val("");

});
//
function mapbtn(a) {
	var html = "";
	switch(a) {
		case "客车路线站点管理":
			html += '<div class="tab_main_xk" style="height:300px;text-align: center;">';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align: left;"><label for="" style="margin-right:5px;">线点</label><select name="" class="select changeUI" name="线点管理" style="margin-right:5px;width:110px;">';
			html += '<option value="1" selected="selected">创建路线</option>';
			html += '<option value="0">管理已有路线</option>';
			html += '</select>'
			html += '<div>';
			html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路线类型</label><select name="drawType" class="select" style="width:86px;">';
			html += '<option value="route">线</option>';
			html += '</select></div>';
			html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">路线名称</label><input class="search" type="text" style="width:80px;"/></div>';
			html += '</div>';
			html += '<a class="button pink draw route" style="padding:5px 8px;margin-left:5px;margin-top:26px;">绘制线</a>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align:left;"><label for="" style="margin-right:5px;">规则设定</label><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存规则</a><a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a>';
			html += '<div class="set_zyl" style="margin-top:6px;padding:2px;height:70px;background: #eee;"><label for="">报警设置</label><div style="margin-top:3px;font-size:12px;"><input type="radio" value="进区域报警给驾驶员" name="alarm" checked="checked" /><label for="">进区域报警给驾驶员</label><input type="radio" name="alarm"value="进区域报警给平台"  /><label for="">进区域报警给平台</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="alarm" value="出区域报警给驾驶员" /><label for="">出区域报警给驾驶员</label><input type="radio" value="出区域报警给平台" name="alarm"  /><label for="">出区域报警给平台</label></div></div>';
			html += '<div class="set_time_zyl" style="margin-top:6px;padding:2px;height:76px;background: #eee;"><label for="">时间段</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="onoff" value="1" checked="checked" /><label for="" style="margin-right:3px;">开启</label><label for="">开始时间</label><input class="time_plugin_lv_onclick startTime" time_plugin_lv_type="2" type="text" style="width:120px;"/></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" value="0" name="onoff" /><label for="" style="margin-right:3px;">关闭</label><label for="">结束时间</label><input class="time_plugin_lv_onclick endTime" time_plugin_lv_type="2" type="text" style="width:120px;"/></div>';
			html += '</div>';
			html += '<div class="set_speed_zyl" style="margin-top:6px;position:relative;padding:2px;height:76px;background: #eee;"><div style="float:left;"><label for="">限速</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="1" checked="checked" /><label for="" style="margin-right:3px;">是</label><label for="">最高速度</label><input type="text" name="max_speed"style="width:30px;"/><label for="">km/h</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="0" /><label for="" style="margin-right:3px;">否</label><label for="">持续时间</label><input type="text"name="continued_time" style="width:30px;"/><label for="">S</label></div></div>';
			html += '<div class="timelimit_zyl" style="float:left;height:76px;"><label for="">时间限制</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="isTime" value="1" checked="checked" /><label for="" style="margin-right:3px;">是</label><label for="" style="margin-right:3px;">最长时间</label><input class="maxtimelimit_z" name="maxtimelimit" type="text" style="width:50px;"/></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="isTime" value="0" /><label for="" style="margin-right:3px;">否</label><label for="" style="margin-right:3px;">最短时间</label><input class="mintimelimit_z"  type="text" style="width:50px;" name="mintimelimit" /></div>';
			html += '</div>';
			html += '<div class="clear"></div>'
			html += '</div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;text-align: left;height:280px;"><label for="" style="margin-right:5px;">关联车辆</label>';
			html += '<div style="padding:10px 0;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</i>';
			html += '</div>';
			break;
		case "定时定距上传":
			html += '<div class="tab_main_xk" style="height:260px;">';
			html += '<i class="specialleft_xk">';
			html += '<div style="text-align: left;margin-bottom:20px;">信息上报设置</div>';
			html += '<div style="text-align: left;"><input type="radio" name="bywhat" value="time" checked="checked"/><label style="display:inline-block;width:70px;">按时间</label><label style="display:inline-block;width:100px;">时间间隔（s）</label><input type="text" style="width:100px;" class="input_xk"/></div>';
			html += '<div style="text-align: left;"><input type="radio" value="distance" name="bywhat"/><label style="display:inline-block;width:70px;">按距离</label><label style="display:inline-block;width:100px;">距离间隔（m）</label><input type="text" style="width:100px;" class="input_xk"/></div>';
			html += '<div style="margin-top:10px;text-align: left;">';
			html += '<a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存设置</a>';
			html += '</div>';
			html += '</i>';
			html += '<b class="specialright_xk" style="height:240px;">';
			html += '<div style="text-align: left;">选择车辆</div><div style="padding:10px 0;text-align: left;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</b>';
			html += '</div>';
			break;
		case "重点区域管理":
			html += '<div class="tab_main_xk" style="height:300px;text-align: center;">';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align: left;"><label for="" style="margin-right:5px;">区域</label><select name="区域管理" class="select changeUI" style="margin-right:5px;width:110px;">';
			html += '<option value="1" selected="selected">创建区域</option>';
			html += '<option value="0">管理已有区域</option>';
			html += '</select>';
			html += '<div>';
			html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路线类型</label><select name="drawType" class="select" style="width:86px;">';
			html += '<option value="polygon">多边形</option>';
			html += '<option value="rect">矩形</option>';
			html += '<option value="circle">圆</option>';
			html += '</select></div>';
			html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">路线名称</label><input class="search" type="text" style="width:80px;"/></div>';
			html += '</div>';
			html += '<a class="button pink draw polygon " style="padding:5px 8px;margin-left:5px;margin-top:26px;">绘制多边形</a>';
			html += '<a class="button pink draw circle " style="padding:5px 8px;margin-left:5px;margin-top:26px;display:none">绘制圆形</a>';
			html += '<a class="button pink draw rect" style="padding:5px 8px;margin-left:5px;margin-top:26px;display:none">绘制矩形</a>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align:left;"><label for="" style="margin-right:5px;">规则设定</label><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存规则</a><a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a>';
			html += '<div class="set_zyl" style="margin-top:6px;padding:2px;height:70px;background: #eee;"><label for="">报警设置</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="alarm" value="进区域报警给驾驶员" checked="checked" /><label for="">进区域报警给驾驶员</label><input type="radio" name="alarm" value="进区域报警给平台"  /><label for="">进区域报警给平台</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="alarm" value="出区域报警给驾驶员" /><label for="">出区域报警给驾驶员</label><input type="radio" name="alarm" value="出区域报警给平台"  /><label for="">出区域报警给平台</label></div></div>';
			html += '<div class="set_time_zyl" style="margin-top:6px;padding:2px;height:76px;background: #eee;"><label for="">时间段</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="onoff" value="1" checked="checked" /><label for="" style="margin-right:3px;">开启</label><label for="">开始时间</label><input class="time_plugin_lv_onclick startTime" type="text" style="width:120px;"  time_plugin_lv_type="2"/></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="onoff" value="0" /><label for="" style="margin-right:3px;">关闭</label><label for="">结束时间</label><input type="text" style="width:120px;" class="time_plugin_lv_onclick endTime" time_plugin_lv_type="2"/></div>';
			html += '</div>';
			html += '<div class="clear"></div>';
			html += '<div class="set_speed_zyl" style="margin-top:6px;padding:2px;height:76px;background: #eee;"><label for="">限速</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="1" checked="checked" /><label for="" style="margin-right:3px;">是</label><label for="">最高速度</label><input type="text" name="max_speed"style="width:120px;"/><label for="">km/h</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="0" /><label for="" style="margin-right:3px;">否</label><label for="">持续时间</label><input type="text"name="continued_time" style="width:120px;"/><label for="">S</label></div>';
			html += '</div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;text-align: left;height:280px;"><label for="" style="margin-right:5px;">关联车辆</label>';
			html += '<div style="padding:10px 0;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</i>';
			html += '</div>';
			break;
		case "在途路线监控":
			html += '<div class="tab_main_xk" style="height:300px;text-align: center;">';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align: left;"><label for="" style="margin-right:5px;">路线</label><select name="" class="select changeUI" style="margin-right:5px;width:110px;">';
			html += '<option value="1" selected="selected">创建路线</option>';
			html += '<option value="0">管理已有路线</option>';
			html += '</select>'
			html += '<div>';
			html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路线类型</label><select name="drawType" class="select" style="width:86px;">';
			html += '<option value="route">线</option>';
			html += '</select></div>';
			html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">路线名称</label><input class="search" type="text" style="width:80px;"/></div>';
			html += '</div>';
			html += '<a class="button pink draw route" style="padding:5px 8px;margin-left:5px;margin-top:26px;">绘制线</a>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align:left;"><label for="" style="margin-right:5px;">规则设定</label><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存规则</a><a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a>';
			html += '<div class="set_zyl" style="margin-top:6px;padding:2px;height:70px;background: #eee;"><label for="">报警设置</label><div style="margin-top:3px;font-size:12px;"><input type="radio" value="进区域报警给驾驶员" name="alarm" checked="checked" /><label for="">进区域报警给驾驶员</label><input type="radio" name="alarm"value="进区域报警给平台"  /><label for="">进区域报警给平台</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="alarm" value="出区域报警给驾驶员" /><label for="">出区域报警给驾驶员</label><input type="radio" value="出区域报警给平台" name="alarm"  /><label for="">出区域报警给平台</label></div></div>';
			html += '<div class="set_time_zyl" style="margin-top:6px;padding:2px;height:76px;background: #eee;"><label for="">时间段</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="onoff" value="1" checked="checked" /><label for="" style="margin-right:3px;">开启</label><label for="">开始时间</label><input class="time_plugin_lv_onclick startTime" time_plugin_lv_type="2" type="text" style="width:120px;"/></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" value="0" name="onoff" /><label for="" style="margin-right:3px;">关闭</label><label for="">结束时间</label><input class="time_plugin_lv_onclick endTime" time_plugin_lv_type="2" type="text" style="width:120px;"/></div>';
			html += '</div>';
			html += '<div class="set_speed_zyl" style="margin-top:6px;position:relative;padding:2px;height:76px;background: #eee;"><div style="float:left;"><label for="">限速</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="1" checked="checked" /><label for="" style="margin-right:3px;">是</label><label for="">最高速度</label><input type="text" name="max_speed"style="width:30px;"/><label for="">km/h</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="0" /><label for="" style="margin-right:3px;">否</label><label for="">持续时间</label><input type="text"name="continued_time" style="width:30px;"/><label for="">S</label></div></div>';
			html += '<div class="timelimit_zyl" style="float:left;height:76px;"><label for="">时间限制</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="isTime" value="1" checked="checked" /><label for="" style="margin-right:3px;">是</label><label for="" style="margin-right:3px;">最长时间</label><input class="maxtimelimit_z" name="maxtimelimit" type="text" style="width:50px;"/></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="isTime" value="0" /><label for="" style="margin-right:3px;">否</label><label for="" style="margin-right:3px;">最短时间</label><input class="mintimelimit_z"  type="text" style="width:50px;" name="mintimelimit" /></div>';
			html += '</div>';
			html += '<div class="clear"></div>'
			html += '</div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;text-align: left;height:280px;"><label for="" style="margin-right:5px;">关联车辆</label>';
			html += '<div style="padding:10px 0;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</i>';
			html += '</div>';
			break;
		case "固定路线管理":
			html += '<div class="tab_main_xk" style="height:300px;text-align: center;">';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align: left;"><label for="" style="margin-right:5px;">路线</label><select name="" class="select changeUI" style="margin-right:5px;width:110px;">';
			html += '<option value="1" selected="selected">创建路线</option>';
			html += '<option value="0">管理已有路线</option>';
			html += '</select>'
			html += '<div>';
			html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路线类型</label><select name="drawType" class="select" style="width:86px;">';
			html += '<option value="route">线</option>';
			html += '</select></div>';
			html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">路线名称</label><input class="search" type="text" style="width:80px;"/></div>';
			html += '</div>';
			html += '<a class="button pink draw route" style="padding:5px 8px;margin-left:5px;margin-top:26px;">绘制线</a>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align:left;"><label for="" style="margin-right:5px;">规则设定</label><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存规则</a><a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a>';
			html += '<div class="set_zyl" style="margin-top:6px;padding:2px;height:70px;background: #eee;"><label for="">报警设置</label><div style="margin-top:3px;font-size:12px;"><input type="radio" value="进区域报警给驾驶员" name="alarm" checked="checked" /><label for="">进区域报警给驾驶员</label><input type="radio" name="alarm"value="进区域报警给平台"  /><label for="">进区域报警给平台</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="alarm" value="出区域报警给驾驶员" /><label for="">出区域报警给驾驶员</label><input type="radio" value="出区域报警给平台" name="alarm"  /><label for="">出区域报警给平台</label></div></div>';
			html += '<div class="set_time_zyl" style="margin-top:6px;padding:2px;height:76px;background: #eee;"><label for="">时间段</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="onoff" value="1" checked="checked" /><label for="" style="margin-right:3px;">开启</label><label for="">开始时间</label><input class="time_plugin_lv_onclick startTime" time_plugin_lv_type="2" type="text" style="width:120px;"/></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" value="0" name="onoff" /><label for="" style="margin-right:3px;">关闭</label><label for="">结束时间</label><input class="time_plugin_lv_onclick endTime" time_plugin_lv_type="2" type="text" style="width:120px;"/></div>';
			html += '</div>';
			html += '<div class="set_speed_zyl" style="margin-top:6px;position:relative;padding:2px;height:76px;background: #eee;"><div style="float:left;"><label for="">限速</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="1" checked="checked" /><label for="" style="margin-right:3px;">是</label><label for="">最高速度</label><input type="text" name="max_speed"style="width:30px;"/><label for="">km/h</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="0" /><label for="" style="margin-right:3px;">否</label><label for="">持续时间</label><input type="text"name="continued_time" style="width:30px;"/><label for="">S</label></div></div>';
			html += '<div class="timelimit_zyl" style="float:left;height:76px;"><label for="">时间限制</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="isTime" value="1" checked="checked" /><label for="" style="margin-right:3px;">是</label><label for="" style="margin-right:3px;">最长时间</label><input class="maxtimelimit_z" name="maxtimelimit" type="text" style="width:50px;"/></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="isTime" value="0" /><label for="" style="margin-right:3px;">否</label><label for="" style="margin-right:3px;">最短时间</label><input class="mintimelimit_z"  type="text" style="width:50px;" name="mintimelimit" /></div>';
			html += '</div>';
			html += '<div class="clear"></div>'
			html += '</div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;text-align: left;height:280px;"><label for="" style="margin-right:5px;">关联车辆</label>';
			html += '<div style="padding:10px 0;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</i>';
			html += '</div>';
			break;
		case "温度控制":
			html += '<div class="tab_main_xk" style="height:260px;text-align: center;">';
			html += '<i class="specialleft_xk" style="width:48%;height:auto;text-align:left;"><label for="" style="margin-right:5px;">规则设定</label><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存规则</a><a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a>';
			html += '<div style="margin-top:20px;"><label for=""style="margin-right:10px;">温度最大值</label><input type="text" style="width:120px;"/></div>';
			html += '<div style="margin-top:20px;"><label for=""style="margin-right:10px;">湿度最大值</label><input type="text" style="width:120px;"/></div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:48%;text-align: left;height:240px;"><label for="" style="margin-right:5px;">关联车辆</label>';
			html += '<div style="padding:10px 0;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</i>';
			html += '</div>';
			break;
		case "特殊区域管理":
			html += '<div class="tab_main_xk" style="height:300px;text-align: center;">';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align: left;"><label for="" style="margin-right:5px;">区域</label><select name="区域管理" class="select changeUI" style="margin-right:5px;width:110px;">';
			html += '<option value="1" selected="selected">创建区域</option>';
			html += '<option value="0">管理已有区域</option>';
			html += '</select>';
			html += '<div>';
			html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路线类型</label><select name="drawType" class="select" style="width:86px;">';
			html += '<option value="polygon">多边形</option>';
			html += '<option value="rect">矩形</option>';
			html += '<option value="circle">圆</option>';
			html += '</select></div>';
			html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">区域名称</label><input class="search" type="text" style="width:80px;"/></div>';
			html += '</div>';
			html += '<a class="button pink draw polygon " style="padding:5px 8px;margin-left:5px;margin-top:26px;">绘制多边形</a>';
			html += '<a class="button pink draw circle " style="padding:5px 8px;margin-left:5px;margin-top:26px;display:none">绘制圆形</a>';
			html += '<a class="button pink draw rect" style="padding:5px 8px;margin-left:5px;margin-top:26px;display:none">绘制矩形</a>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align:left;"><label for="" style="margin-right:5px;">规则设定</label><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存规则</a><a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a>';
			html += '<div class="set_zyl" style="margin-top:6px;padding:2px;height:70px;background: #eee;"><label for="">报警设置</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="alarm" value="进区域报警给驾驶员" checked="checked" /><label for="">进区域报警给驾驶员</label><input type="radio" name="alarm" value="进区域报警给平台"  /><label for="">进区域报警给平台</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="alarm" value="出区域报警给驾驶员" /><label for="">出区域报警给驾驶员</label><input type="radio" name="alarm" value="出区域报警给平台"  /><label for="">出区域报警给平台</label></div></div>';
			html += '<div class="set_time_zyl" style="margin-top:6px;padding:2px;height:76px;background: #eee;"><label for="">时间段</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="onoff" value="1" checked="checked" /><label for="" style="margin-right:3px;">开启</label><label for="">开始时间</label><input class="time_plugin_lv_onclick startTime" type="text" style="width:120px;"  time_plugin_lv_type="2"/></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="onoff" value="0" /><label for="" style="margin-right:3px;">关闭</label><label for="">结束时间</label><input type="text" style="width:120px;" class="time_plugin_lv_onclick endTime" time_plugin_lv_type="2"/></div>';
			html += '</div>';
			html += '<div class="clear"></div>';
			html += '<div class="set_speed_zyl" style="margin-top:6px;padding:2px;height:76px;background: #eee;"><label for="">限速</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="1" checked="checked" /><label for="" style="margin-right:3px;">是</label><label for="">最高速度</label><input type="text" name="max_speed"style="width:120px;"/><label for="">km/h</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="0" /><label for="" style="margin-right:3px;">否</label><label for="">持续时间</label><input type="text"name="continued_time" style="width:120px;"/><label for="">S</label></div>';
			html += '</div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;text-align: left;height:280px;"><label for="" style="margin-right:5px;">关联车辆</label>';
			html += '<div style="padding:10px 0;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</i>';
			html += '</div>';
			break;
		case "路线管理":
			html += '<div class="tab_main_xk" style="height:300px;text-align: center;">';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align: left;"><label for="" style="margin-right:5px;">路线</label><select name="" class="select changeUI" style="margin-right:5px;width:110px;">';
			html += '<option value="1" selected="selected">创建路线</option>';
			html += '<option value="0">管理已有路线</option>';
			html += '</select>'
			html += '<div>';
			html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路线类型</label><select name="drawType" class="select" style="width:86px;">';
			html += '<option value="route">线</option>';
			html += '</select></div>';
			html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">路线名称</label><input class="search" type="text" /></div>';
			html += '</div>';
			html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">起点：</label><input class="search" type="text" id="startingPoint">';
			html += '</div>';
			html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">终点：</label><input class="search" type="text" id="endPoint">';
			html += '</div>';
			html += '<a class="button pink draw startEnd" style="padding:5px 8px;margin-left:5px;margin-top:26px;">绘制导航路线</a>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align:left;"><label for="" style="margin-right:5px;">规则设定</label><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存规则</a><a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a>';
			html += '<div class="set_zyl" style="margin-top:6px;padding:2px;height:70px;background: #eee;"><label for="">报警设置</label><div style="margin-top:3px;font-size:12px;"><input type="radio" value="进区域报警给驾驶员" name="alarm" checked="checked" /><label for="">进区域报警给驾驶员</label><input type="radio" name="alarm"value="进区域报警给平台"  /><label for="">进区域报警给平台</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="alarm" value="出区域报警给驾驶员" /><label for="">出区域报警给驾驶员</label><input type="radio" value="出区域报警给平台" name="alarm"  /><label for="">出区域报警给平台</label></div></div>';
			html += '<div class="set_time_zyl" style="margin-top:6px;padding:2px;height:76px;background: #eee;"><label for="">时间段</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="onoff" value="1" checked="checked" /><label for="" style="margin-right:3px;">开启</label><label for="">开始时间</label><input class="time_plugin_lv_onclick startTime" time_plugin_lv_type="2" type="text" style="width:120px;"/></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" value="0" name="onoff" /><label for="" style="margin-right:3px;">关闭</label><label for="">结束时间</label><input class="time_plugin_lv_onclick endTime" time_plugin_lv_type="2" type="text" style="width:120px;"/></div>';
			html += '</div>';
			html += '<div class="set_speed_zyl" style="margin-top:6px;position:relative;padding:2px;height:76px;background: #eee;"><div style="float:left;"><label for="">限速</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="1" checked="checked" /><label for="" style="margin-right:3px;">是</label><label for="">最高速度</label><input type="text" name="max_speed"style="width:30px;"/><label for="">km/h</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="0" /><label for="" style="margin-right:3px;">否</label><label for="">持续时间</label><input type="text"name="continued_time" style="width:30px;"/><label for="">S</label></div></div>';
			html += '<div class="timelimit_zyl" style="float:left;height:76px;"><label for="">时间限制</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="isTime" value="1" checked="checked" /><label for="" style="margin-right:3px;">是</label><label for="" style="margin-right:3px;">最长时间</label><input class="maxtimelimit_z" name="maxtimelimit" type="text" style="width:50px;"/></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="isTime" value="0" /><label  for="" style="margin-right:3px;">否</label><label for="" style="margin-right:3px;">最短时间</label><input class="mintimelimit_z"  type="text" style="width:50px;" name="mintimelimit" /></div>';
			html += '</div>';
			html += '<div class="clear"></div>'
			html += '</div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;text-align: left;height:280px;"><label for="" style="margin-right:5px;">关联车辆</label>';
			html += '<div style="padding:10px 0;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</i>';
			html += '</div>';
			break;
		case "线路关键点管理":
			html += '<div class="tab_main_xk" style="height:300px;text-align: center;">';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align: left;"><label for="" style="margin-right:5px;">线点</label><select name="" class="select changeUI" name="线点管理" style="margin-right:5px;width:110px;">';
			html += '<option value="1" selected="selected">创建线点</option>';
			html += '<option value="0">管理已有线点</option>';
			html += '</select>';
			html += '<div>';
			html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路线类型</label><select name="drawType" class="select" style="width:86px;">';
			html += '<option value="route">线</option>';
			html += '</select></div>';
			html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">路线名称</label><input class="search" type="text" style="width:80px;"/></div>';
			html += '</div>';
			html += '<a class="button pink draw route" style="padding:5px 8px;margin-left:5px;margin-top:26px;">绘制线</a>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align:left;"><label for="" style="margin-right:5px;">规则设定</label><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存规则</a><a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a>';
			html += '<div class="set_zyl" style="margin-top:6px;padding:2px;height:70px;background: #eee;"><label for="">报警设置</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="alarm" value="进区域报警给驾驶员" /><label for="">进区域报警给驾驶员</label><input type="radio" name="alarm" value="进区域报警给平台"  /><label for="">进区域报警给平台</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="alarm" value="出区域报警给驾驶员" /><label for="">出区域报警给驾驶员</label><input type="radio" name="alarm" value="出区域报警给平台"  /><label for="">出区域报警给平台</label></div></div>';
			html += '<div class="set_time_zyl" style="margin-top:6px;padding:2px;height:76px;background: #eee;"><label for="">时间段</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="onoff" value="1" /><label for="" style="margin-right:3px;">开启</label><label for="">开始时间</label><input class="time_plugin_lv_onclick startTime"  type="text" style="width:120px;"  time_plugin_lv_type="2"/></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="onoff" value="0" /><label for="" style="margin-right:3px;">关闭</label><label for="">结束时间</label><input type="text" style="width:120px;" class="time_plugin_lv_onclick endTime" time_plugin_lv_type="2"/></div>';
			html += '</div>';
			html += '<div class="set_speed_zyl" style="margin-top:6px;position:relative;padding:2px;height:76px;background: #eee;"><div style="float:left;"><label for="">限速</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="1" checked="checked" /><label for="" style="margin-right:3px;">是</label><label for="">最高速度</label><input type="text" name="max_speed"style="width:30px;"/><label for="">km/h</label></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="speed" value="0" /><label for="" style="margin-right:3px;">否</label><label for="">持续时间</label><input type="text"name="continued_time" style="width:30px;"/><label for="">S</label></div></div>';
			html += '<div class="timelimit_zyl" style="float:left;height:76px;"><label for="">时间限制</label><div style="margin-top:3px;font-size:12px;"><input type="radio" name="isTime" value="1" checked="checked" /><label for="" style="margin-right:3px;">是</label><label for="" style="margin-right:3px;">最长时间</label><input class="maxtimelimit_z" name="maxtimelimit" type="text" style="width:50px;"/></div>';
			html += '<div style="margin-top:3px;font-size:12px;"><input type="radio" name="isTime" value="0" /><label for="" style="margin-right:3px;">否</label><label for="" style="margin-right:3px;">最短时间</label><input class="mintimelimit_z"  type="text" style="width:50px;" name="mintimelimit" /></div>';
			html += '</div>';
			html += '<div class="clear"></div>'
			html += '</div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;text-align: left;height:280px;"><label for="" style="margin-right:5px;">关联车辆</label>';
			html += '<div style="padding:10px 0;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</i>';
			html += '</div>';
			break;
		case "点辐射区域管理":
			html += '<div class="tab_main_xk" style="height:220px;text-align: center;">';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align: left;"><label for="" style="margin-right:5px;">关键点</label><select name="" class="select changeUI" name="关键点管理" style="margin-right:5px;width:110px;">';
			html += '<option value="1" selected="selected">创建关键点</option>';
			html += '<option value="0">管理关键点</option>';
			html += '</select>';
			html += '<a class="button pink draw" style="padding:5px 8px;margin-right:5px;">绘制工具</a>';
			html += '<div>';
			html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">关键点类型</label><select name="" class="select" style="width:86px;">';
			html += '<option value="">关键点类型1</option>';
			html += '</select></div>';
			html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">关键点名称</label><input class="search" type="text" style="width:80px;"/></div>';
			html += '</div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align: left;"><label for="" style="margin-right:5px;">规则设定</label><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存规则</a><a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a>';
			html += '<div style="margin-top:6px;padding:2px;height:50px;"><label for="">关键点</label><div style="margin-top:3px;font-size:12px;"><label for="">辐射范围（m）</label><input type="text" /></div></div>';
			html += '<div style="margin-top:10px;"><label for="">时间段</label>';
			html += '<div style="margin-top:10px;"><label for="" style="margin-right:8px;">开始时间</label><input type="time" /></div>';
			html += '<div style="margin-top:10px;"><label for="" style="margin-right:8px;">结束时间</label><input type="time" /></div></div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;text-align: left;height:200px;"><label for="" style="margin-right:5px;">关联车辆</label>';
			html += '<div style="padding:10px 0;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</i>';
			html += '</div>';
			break;
		case "路段限速管理":
			html += '<div class="tab_main_xk" style="height:220px;text-align: center;">';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align: left;"><label for="" style="margin-right:5px;">路段</label><select name="" class="select changeUI" style="margin-right:5px;width:110px;">';
			html += '<option value="1" selected="selected">创建路段</option>';
			html += '<option value="0">管理路段</option>';
			html += '</select>'
			html += '<a class="button pink draw" style="padding:5px 8px;margin-right:5px;">绘制工具</a>';
			html += '<div>';
			html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路段类型</label><select name="" class="select" style="width:86px;">';
			html += '<option value="">路段类型1</option>';
			html += '</select></div>';
			html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">路段名称</label><input class="search" type="text" style="width:80px;"/></div>';
			html += '</div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align:left;"><label for="" style="margin-right:5px;">规则设定</label><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存规则</a><a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a>';
			html += '<div style="margin-top:6px;padding:2px;height:50px;"><label for="">报警设置</label><div style="margin-top:3px;font-size:12px;"><label for="" style="margin-right:8px;">超速阈值</label><input type="text"/>km/h</div></div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;text-align: left;height:200px;"><label for="" style="margin-right:5px;">关联车辆</label>';
			html += '<div style="padding:10px 0;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</i>';
			html += '</div>';
			break;
		case "全路段限速管理":
			html += '<div class="tab_main_xk" style="height:220px;text-align: center;">';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align: left;"><label for="" style="margin-right:5px;">限定</label><select name="" class="select changeUI" style="margin-right:5px;width:110px;">';
			html += '<option value="1" selected="selected">创建限定</option>';
			html += '<option value="0">管理限定</option>';
			html += '</select>'
			html += '<div>';
			html += '<div style="padding:6px 0;"><label style="margin-right:10px;vertical-align: middle;" for="">名称</label><input class="search" type="text" style="width:80px;"/></div>';
			html += '</div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align:left;"><label for="" style="margin-right:5px;">规则设定</label><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存规则</a><a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a>';
			html += '<div class="set_zyl" style="margin-top:6px;padding:2px;height:50px;"><label for="">报警设置</label><div style="margin-top:3px;font-size:12px;"><label for="" style="margin-right:8px;">超速阈值</label><input type="text"/>km/h</div></div>';
			html += '<div class="set_time_zyl" style="margin-top:10px;"><label for="">时间段</label>';
			html += '<div style="margin-top:10px;"><label for="" style="margin-right:8px;font-size:12px;">开始时间</label><input class="time_plugin_lv_onclick startTime" time_plugin_lv_type="2" type="text" style="width:120px;"/></div>';
			html += '<div style="margin-top:10px;"><label for="" style="margin-right:8px;font-size:12px;">结束时间</label><input class="time_plugin_lv_onclick endTime" time_plugin_lv_type="2" type="text" style="width:120px;"/></div></div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;text-align: left;height:200px;"><label for="" style="margin-right:5px;">关联车辆</label>';
			html += '<div style="padding:10px 0;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</i>';
			html += '</div>';
			break;
		case "道路限速":
			html += '<div class="tab_main_xk" style="height:220px;text-align: center;">';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align: left;"><label for="" style="margin-right:5px;">限定</label><select name="" class="select changeUI" style="margin-right:5px;width:110px;">';
			html += '<option value="1" selected="selected">创建等级</option>';
			html += '<option value="0">管理等级</option>';
			html += '</select>'
			html += '<div>';
			html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">道路等级</label><select name="roadLevel" class="select" style="width:86px;">';
			html += '<option value="高级">高速</option>';
			html += '<option value="普通">普路</option>';
			html += '</select></div>';
			html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">名称</label><input class="search" type="text" style="width:80px;"/></div>';
			html += '</div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;height:auto;text-align:left;"><label for="" style="margin-right:5px;">规则设定</label><a class="button green save" style="padding:5px 8px;margin-right:5px;">保存规则</a><a class="button orange cancel" style="padding:5px 8px;margin-right:5px;">取消修改</a>';
			html += '<div class="set_zyl" style="margin-top:6px;padding:2px;height:50px;"><label for="">报警设置</label><div style="margin-top:3px;font-size:12px;"><label for="" style="margin-right:8px;">超速阈值</label><input type="text"/>km/h</div></div>';
			html += '<div class="set_time_zyl" style="margin-top:10px;"><label for="">时间段</label>';
			html += '<div style="margin-top:10px;"><label for="" style="margin-right:8px;font-size:12px;">开始时间</label><input class="time_plugin_lv_onclick startTime" time_plugin_lv_type="2" type="text" style="width:120px;"/></div>';
			html += '<div style="margin-top:10px;"><label for="" style="margin-right:8px;font-size:12px;">结束时间</label><input class="time_plugin_lv_onclick endTime" time_plugin_lv_type="2" type="text" style="width:120px;"/></div></div>';
			html += '</i>';
			html += '<i class="specialleft_xk" style="width:32%;text-align: left;height:200px;"><label for="" style="margin-right:5px;">关联车辆</label>';
			html += '<div style="padding:10px 0;"><label style="display:inline-block;width:80px;">搜索车辆</label><input type="text" class="input_xk checkCar" list="checkCar"/></div>';
			html += '<div>';
			html += '<div class="dendrogram_xk" style="margin-top:10px;margin-left:0;text-align:left;width:100%;">';
			html += '<dl>';
			html += '<dt><i><img src="img/u380.png"/></i><b><img src="img/uncherked.jpg"/></b><i><img src="img/qb.png" style="height:16px;"/></i>全部车辆</dt>';
			html += '<dd id="carList">';
			getCarList();
			html += '</dd>';
			html += '</dl>';
			html += '</div>';
			html += '</div>';
			html += '</i>';
			html += '</div>';
			break;
		default:
			break;
	}
	return html;
};
$(document).on("change", ".changeUI", function() {
	if($(this).children("option:selected").val() == 0) {
		$(this).children("option:not(':selected')").val("0");
		$(this).children("option:selected").val("1");
		$(this).nextAll().remove();
		var html = "";
		switch($(this).children("option:selected").text()) {
			case "创建区域":
				html += '<div>';
				html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路线类型</label><select name="drawType" class="select" style="width:86px;">';
				html += '<option value="polygon">多边形</option>';
				html += '<option value="rect">矩形</option>';
				html += '<option value="circle">圆</option>';
				html += '</select></div>';
				html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">区域名称</label><input class="search" type="text" style="width:80px;"/></div>';
				html += '</div>';
				html += '<a class="button pink draw polygon " style="padding:5px 8px;margin-left:5px;margin-top:26px;">绘制多边形</a>';
				html += '<a class="button pink draw circle " style="padding:5px 8px;margin-left:5px;margin-top:26px;display:none">绘制圆形</a>';
				html += '<a class="button pink draw rect" style="padding:5px 8px;margin-left:5px;margin-top:26px;display:none">绘制矩形</a>';
				break;
			case "管理已有区域":
				html += '<a class="button orange cancel del" style="padding:5px 8px;margin-right:5px;">删除选定区域</a><br />';
				html += '<select  class="add_area_zyl" name="" size="10" style="margin-top:20px; min-width:200px">';
				/*
				 * html += '<option value="">天津市区分时段限速区域</option>'; html += '<option
				 * value="">天津市区分时段限行区域</option>'; html += '<option
				 * value="">天津市区分车型限行区域</option>'; html += '<option
				 * value="">天津市区分车牌限行区域</option>'; html += '<option
				 * value="">秦皇岛市区分车牌限行区域</option>'; html += '<option
				 * value="">秦皇岛市区分车牌限行区域</option>'; html += '<option
				 * value="">秦皇岛市区分车牌限行区域</option>'; html += '<option
				 * value="">秦皇岛市区分车牌限行区域</option>';
				 */
				html += '</select>';
				html += '<script>deleteEnclosure()</script>';
				break;
			case "创建线点":
				html += '<div>';
				html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路线类型</label><select name="drawType" class="select" style="width:86px;">';
				html += '<option value="route">线</option>';
				html += '</select></div>';
				html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">路线名称</label><input class="search" type="text" style="width:80px;"/></div>';
				html += '</div>';
				html += '<a class="button pink draw route" style="padding:5px 8px;margin-left:5px;margin-top:26px;">绘制线</a>';
				break;
			case "管理已有线点":
				html += '<a class="button orange cancel del" style="padding:5px 8px;margin-right:5px;">删除选定线点</a><br />';
				html += '<select class="add_area_zyl" name="" size="10" style="margin-top:20px; min-width:200px">';
				/*
				 * html += '<option value="">天津市区分时段限速区域</option>'; html += '<option
				 * value="">天津市区分时段限行区域</option>'; html += '<option
				 * value="">天津市区分车型限行区域</option>'; html += '<option
				 * value="">天津市区分车牌限行区域</option>'; html += '<option
				 * value="">秦皇岛市区分车牌限行区域</option>'; html += '<option
				 * value="">秦皇岛市区分车牌限行区域</option>'; html += '<option
				 * value="">秦皇岛市区分车牌限行区域</option>'; html += '<option
				 * value="">秦皇岛市区分车牌限行区域</option>';
				 */
				html += '</select>';
				html += '<script>deleteEnclosure()</script>';
				break;
			case "创建路线":
				html += '<div>';
				html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路线类型</label><select name="drawType" class="select" style="width:86px;">';
				html += '<option value="route">线</option>';
				html += '</select></div>';
				html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">路线名称</label><input class="search" type="text" /></div>';
				html += '</div>';
				html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">起点：</label><input class="search" type="text" id="startingPoint">';
				html += '</div>';
				html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">终点：</label><input class="search" type="text" id="endPoint">';
				html += '</div>';
				html += '<a class="button pink draw startEnd" style="padding:5px 8px;margin-left:5px;margin-top:26px;">绘制导航路线</a>';
				break;
			case "管理已有路线":
				html += '<a class="button orange cancel del" style="padding:5px 8px;margin-right:5px;">删除选定路线</a><br />';
				html += '<select class="add_area_zyl" name="" size="10" style="margin-top:20px; min-width:200px">';
				/*
				 * html += '<option value="">天津市区分时段限速区域</option>'; html += '<option
				 * value="">天津市区分时段限行区域</option>'; html += '<option
				 * value="">天津市区分车型限行区域</option>'; html += '<option
				 * value="">天津市区分车牌限行区域</option>'; html += '<option
				 * value="">秦皇岛市区分车牌限行区域</option>'; html += '<option
				 * value="">秦皇岛市区分车牌限行区域</option>'; html += '<option
				 * value="">秦皇岛市区分车牌限行区域</option>'; html += '<option
				 * value="">秦皇岛市区分车牌限行区域</option>';
				 */
				html += '</select>';
				html += '<script>deleteEnclosure()</script>';
				break;
			case "创建关键点":
				html += '<div>';
				html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路线类型</label><select name="drawType" class="select" style="width:86px;">';
				html += '<option value="route">线</option>';
				html += '</select></div>';
				html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">路线名称</label><input class="search" type="text" style="width:80px;"/></div>';
				html += '</div>';
				html += '<a class="button pink draw route" style="padding:5px 8px;margin-left:5px;margin-top:26px;">绘制线</a>';
				break;
			case "管理关键点":
				html += '<a class="button orange cancel del" style="padding:5px 8px;margin-right:5px;">删除关键点</a><br />';
				html += '<input name="" style="margin-top:20px;" placeholder="查找关键点"/><br/>';
				html += '<select name="" size="6" style="margin-top:20px;width:150px;">';
				html += '<option value="">第一个关键点</option>';
				html += '<option value="">第二个关键点</option>';
				html += '<option value="">第三个关键点</option>';
				html += '<option value="">第四个关键点</option>';
				html += '</select>';
				html += '<script>deleteEnclosure()</script>';
				break;
			case "创建路段":
				html += '<a class="button pink draw" style="padding:5px 8px;margin-right:5px;">绘制工具</a>';
				html += '<div>';
				html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">路段类型</label><select name="" class="select" style="width:86px;">';
				html += '<option value="">路段类型1</option>';
				html += '</select></div>';
				html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">路段名称</label><input class="search" type="text" style="width:80px;"/></div>';
				html += '</div>';
				break;
			case "管理路段":
				html += '<a class="button orange cancel del" style="padding:5px 8px;margin-right:5px;">删除选定路段</a><br />';
				html += '<input name="" style="margin-top:20px;" placeholder="筛选路段"/><br/>';
				html += '<select name="" size="6" style="margin-top:20px;width:150px;">';
				html += '<option value="">危险品运输冀津运输线路1</option>';
				html += '<option value="">危险品运输冀津运输线路2</option>';
				html += '<option value="">危险品运输冀津运输线路3</option>';
				html += '<option value="">危险品运输冀津运输线路4</option>';
				html += '</select>';
				html += '<script>deleteEnclosure()</script>';
				break;
			case "创建限定":
				html += '<div>';
				html += '<div style="padding:6px 0;"><label style="margin-right:10px;vertical-align: middle;" for="">名称</label><input class="search" type="text" style="width:80px;"/></div>';
				html += '</div>';
				break;
			case "管理限定":
				html += '<a class="button orange cancel del" style="padding:5px 8px;margin-right:5px;">删除选定限定</a><br />';
				html += '<select class="add_area_zyl" name="" size="6" style="margin-top:20px;width:150px;">';
				html += '<option value="">危险品运输冀津运输线路1</option>';
				html += '<option value="">危险品运输冀津运输线路2</option>';
				html += '<option value="">危险品运输冀津运输线路3</option>';
				html += '<option value="">危险品运输冀津运输线路4</option>';
				html += '</select>';
				html += '<script>deleteWholeSection()</script>';
				break;
			case "创建等级":
				html += '<div style="padding:6px 0;"><label for="" style="margin-right:10px;vertical-align: middle;">道路等级</label><select name="roadLevel" class="select" style="width:86px;">';
				html += '<option value="高速">高速</option>';
				html += '<option value="普路">普路</option>';
				html += '</select></div>';
				html += '<div><label style="margin-right:10px;vertical-align: middle;" for="">名称</label><input class="search" type="text" style="width:80px;"/></div>';
				html += '</div>';
				break;
			case "管理等级":
				html += '<a class="button orange cancel del" style="padding:5px 8px;margin-right:5px;">删除选定限定</a><br />';
				html += '<select class="add_area_zyl" name="" size="6" style="margin-top:20px;width:150px;">';
				html += '<option value="">危险品运输冀津运输线路1</option>';
				html += '<option value="">危险品运输冀津运输线路2</option>';
				html += '<option value="">危险品运输冀津运输线路3</option>';
				html += '<option value="">危险品运输冀津运输线路4</option>';
				html += '</select>';
				html += '<script>deleteRoadGrade()</script>';
				break;
			default:
				break;
		};
		$(this).after(html);
	};
	if($(".map_top_xk .mapbtn_xk li.on_xk").text() == "路线管理" && $(".tab_main_xk .specialleft_xk:eq(0) option:selected").text().indexOf("创建") >= 0) {
		$("#startPonit").show();
		$("#endPonit").show();
	} else {
		$("#startPonit").hide();
		$("#endPonit").hide();
	}

});
$(document).on('change', 'select[name=drawType]', function() {
	$(".draw").hide();
	switch($(this).val()) {
		case "rect":
			$(".draw.rect").show();
			break;
		case "polygon":
			$(".draw.polygon").show()
			break;
		case "circle":
			$(".draw.circle").show()
			break;
		default:
			$(".draw.route").show()
			break;
	}
})
$(document).on('input', ".input_xk.checkCar", function(event) {
	$("datalist").remove();
	html = "";
	html += "<datalist id='checkCar'>";
	for(var i = 0; i < $("#carList dt").length; i++) {
		if($('#carList dt').eq(i).text().indexOf($(this).val()) >= 0) {
			html += "<option value='" + $('#carList dt').eq(i).text() + "'>";
		}

	}
	html += "</datalist>";
	$(this).after(html);

	var input_val = $(this).val();
	$("#carList dt").removeClass("dt_on").each(function() {
		if($(this).text() == input_val) {
			$(this).addClass("dt_on");
		}
	});
});
$(document).on('input', "input.search[placeholder=搜索车辆]", function(event) {

	$("datalist").remove();
	html = "";
	html += "<datalist id='checkCar'>";
	for(var i = 0; i < $("#carList dt").length; i++) {
		if($('#carList dt').eq(i).text().indexOf($(this).val()) >= 0) {
			html += "<option value='" + $('#carList dt').eq(i).text() + "'>";
		}

	}
	html += "</datalist>";
	$(this).after(html);

	var input_val = $(this).val();
	$("#carList dt").removeClass("dt_on").each(function() {
		if($(this).text() == input_val) {
			$(this).addClass("dt_on");
		}
	});
});
/* 侧边栏 */
$(document).on('input', "input.search[placeholder=找车]", function(event) {
	$("datalist").remove();
	html = "";
	html += "<datalist id='checkCar'>";
	for(var i = 0; i < $("#carLists dt").length; i++) {
		if($('#carLists dt').eq(i).text().indexOf($(this).val()) >= 0) {
			html += "<option value='" + $('#carLists dt').eq(i).text() + "'>";
		}

	}
	html += "</datalist>";
	$(this).after(html);

	var input_val = $(this).val();
	$("#carLists dt").removeClass("dt_on").each(function() {
		if($(this).text() == input_val) {
			$(this).addClass("dt_on");
		}
	});
});
/* 侧边栏 */
$(document).on('input', ".input_xk.checkCar", function(event) {
	$("datalist").remove();
	html = "";
	html += "<datalist id='checkCar'>";
	for(var i = 0; i < $("#carLists dt").length; i++) {
		if($('#carLists dt').eq(i).text().indexOf($(this).val()) >= 0) {
			html += "<option value='" + $('#carLists dt').eq(i).text() + "'>";
		}

	}
	html += "</datalist>";
	$(this).after(html);

	var input_val = $(this).val();
	$("#carLists dt").removeClass("dt_on").each(function() {
		if($(this).text() == input_val) {
			$(this).addClass("dt_on");
		}
	});
});

function getCarList() {
	var leftName = $(".gt_wl_left dt.on_xk").text();
	if(leftName == "车辆实时监控") {
		var scarList = "vehicle/getVehicleByAdmin";
		var data_json = {};
	} else {
		var scarList = "realTime/findVehicleByAdminAndSpecialType";
		var threeName = $(".gt_wl_left dd span.on_xk").text();
		switch(threeName) {
			case "集港物流信息子系统":
				var data_json = {
					"specialType": "1"
				}
				break;
			case "危险品零售供应链子系统":
				var data_json = {
					"specialType": "2"
				}
				break;
			case "仓储配送一体化子系统":
				var data_json = {
					"specialType": "3"
				}
				break;
			case "冷链配送一体化子系统":
				var data_json = {
					"specialType": "4"
				}
				break;
			case "干线运输信息化子系统":
				var data_json = {
					"specialType": "5"
				}
				break;
			default:
				var data_json = {
					"specialType": "6"
				}
				break;
		}
	}
	$.ajax(config.url + scarList, {
		data: data_json,
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success == true) {
				data = data.t;
				html = "";
				// for(one in data) {
				// html += '<dl>';
				// html += '<dt><i><img
				// src="img/u380.png"/></i><b cId="' +
				// data[one].cId + '"><img
				// src="img/uncherked.jpg"/></b><i><img
				// src="img/td.png" /></i>' + data[one].cName +
				// '</dt>';
				// html += '<dd>';
				// for(two in data[one].vehicles) {
				// html += '<dl>';
				// html += '<dt><i><img src="img/xlcl
				// (1).png"/></i><b simno="' +
				// data[one].vehicles[two].simNo + '" num="' +
				// data[one].vehicles[two].num + '" vid="' +
				// data[one].vehicles[two].id + '"><img
				// src="img/uncherked.jpg"/></b>' +
				// data[one].vehicles[two].num + '</dt>';
				// html += '</dl>';
				// }
				// html += '</dd>';
				// html += '</dl>';
				// }
				for(one in data) {
					html += "<dl>";
					html += '<dt ><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg" carListClick /></b>' +
						one + '</dt>';
					html += "<dd>";
					for(two in data[one]) {
						if(two.indexOf("TEAM")!=-1){
							for(three in data[one][two]) {
								html += "<dl>";
								html += '<dt><i><img src="img/xlcl (1).png"/></i><b simno="' +
									data[one][two][three].simNo +
									'" num="' +
									data[one][two][three].num +
									'" vid="' +
									data[one][two][three].id +
									'"><img src="img/uncherked.jpg"/></b>' +
									data[one][two][three].num +
									'</dt>';
								html += "</dl>";
							}
						}else{
							html += "<dl>";
							html += '<dt><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg" carListClick /></b>' +
								two + '</dt>';
							html += "<dd>";
							for(three in data[one][two]) {
								html += "<dl>";
								html += '<dt><i><img src="img/xlcl (1).png"/></i><b simno="' +
									data[one][two][three].simNo +
									'" num="' +
									data[one][two][three].num +
									'" vid="' +
									data[one][two][three].id +
									'"><img src="img/uncherked.jpg"/></b>' +
									data[one][two][three].num +
									'</dt>';
								html += "</dl>";
							}
							html += "</dd>";
							html += "</dl>";
						}
					}
					html += "</dd>";
					html += "</dl>";
				}
				$("dd#carList").html(html);
			}
		}
	});
}
$(document).on('input', "input[placeholder=输入车牌号并回车]", function(event) {
	$("datalist").remove();
	html = "";
	html += "<datalist id='checkCars'>";
	for(var i = 0; i < $("#carLists dt").length; i++) {
		if($('#carLists dt').eq(i).text().indexOf($(this).val()) >= 0) {
			html += "<option value='" + $('#carLists dt').eq(i).text() + "'>";
		}

	}
	html += "</datalist>";
	$(this).after(html);

	var input_val = $(this).val();
	$("#carLists dt").removeClass("dt_on").each(function() {
		if($(this).text() == input_val) {
			$(this).addClass("dt_on");
		}
	});
});
// 车辆实时监控下车辆选中状态（特殊，路线，线路）
function getEnclosureBindByEnclosureid() {
	var this_id = $(".add_area_zyl option:selected").attr("zyl_id");
	$.ajax(config.url + "realTime/getEnclosureBindByEnclosureid", {
		data: {
			"enclosureId": this_id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				data.obj == null ? data = data.t : data = data.obj;
				for(var i = 0; i < $(".specialleft_xk:eq(2) dd#carList dl").length; i++) {
					var test = $(".specialleft_xk:eq(2) dd#carList dl").eq(i).children("dt").children("b").attr("vid");
					if(test != undefined) {
						if($.inArray(test, data) >= 0) {
							$(".specialleft_xk:eq(2) dd#carList dl").eq(i).find("img[src='img/uncherked.jpg']").attr("src", "img/cherked.jpg");
						}
					}
				}
			}
		}
	});
};

function getSimNoById() {
	var this_id = $(".add_area_zyl option:selected").attr("zyl_id");
	$.ajax(config.url + "road/getSimNoById", {
		data: {
			"roadId": this_id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success == true) {
				data.obj == null ? data = data.t : data = data.obj;
				for(var i = 0; i < $(".specialleft_xk:eq(2) dd#carList dl").length; i++) {
					var simNoId_zyl = $(".specialleft_xk:eq(2) dd#carList dl").eq(i).children("dt").children("b").attr("simno");
					if(simNoId_zyl != undefined) {
						if($.inArray(simNoId_zyl, data) >= 0) {
							$(".specialleft_xk:eq(2) dd#carList dl").eq(i).find("img[src='img/uncherked.jpg']").attr("src", "img/cherked.jpg");
						}
					}
				}
			}
		}
	});
}

// 删除选定区域
function deleteEnclosure() {
	$(".del").click(function() {
		var this_id = ($(".add_area_zyl option:selected").attr("zyl_id"));
		$.ajax(config.url + "realTime/deleteEnclosure", {
			data: {
				"enclosureid": this_id,
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(data.success);
				alert(data.msg);
			}
		});
	})
};

function deleteWholeSection() {
	$(".del").click(function() {
		var this_id = ($(".add_area_zyl option:selected").attr("zyl_id"));
		$.ajax(config.url + "road/deleteWholeSection", {
			data: {
				"id": this_id,
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					//console.log(data.success);
					alert(data.msg);
				}
			}
		});
	})
};

function deleteRoadGrade() {
	$(".del").click(function() {
		var this_id = ($(".add_area_zyl option:selected").attr("zyl_id"));
		$.ajax(config.url + "road/deleteRoadGrade", {
			data: {
				"id": this_id,
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					//console.log(data.success);
					alert(data.msg);
				}
			}
		});
	})
}

thisname_lv_img = "";
img_id = "";

function UploadImg(id) {
	$.ajaxFileUpload({
		url: config.url + 'upload/img',
		secureuri: false,
		fileElementId: id,
		dataType: 'json',
		type: 'post',
		success: function(data, status) {
			$(".thisname_lv_img").attr("src", config.url + data.t + "");
			$(".thisname_lv_img").attr("img_id", data.t);
		}
	})
};

thisname_lv_img_z = "";
img_id_z = "";
function UploadImg_zyl(id) {
	$.ajaxFileUpload({
		url: config.url + 'upload/file',
		secureuri: false,
		fileElementId: id,
		dataType: 'json',
		type: 'post',
		success: function(data, status) {
			$(".thisname_lv_img_z").attr("src", "http://"+data.t + "");
			$(".thisname_lv_img_z").attr("img_id_z", "http://"+data.t);
		}
	})
};

function DriversExcelImport(id) {
	$.ajaxFileUpload({
		url: config.url + 'driver/DriversExcelImport',
		secureuri: false,
		fileElementId: id,
		dataType: 'json',
		type: 'post',
		success: function(data, status) {
			console.log(data);
		},
		error: function(data, status, e) {
			alert(e);
			console.log(e);
		}
	})
};

function VehicelsExcelImport(id) {
	$.ajaxFileUpload({
		url: config.url + 'vehicle/VehicelsExcelImport',
		secureuri: false,
		fileElementId: id,
		dataType: 'json',
		type: 'post',
		success: function(data, status) {
			console.log(data);
		},
		error: function(data, status, e) {
			alert(e);
			console.log(e);
		}
	})
};

function CustomersExcelImport(id) {
	$.ajaxFileUpload({
		url: config.url + 'customer/CustomersExcelImport',
		secureuri: false,
		fileElementId: id,
		dataType: 'json',
		type: 'post',
		success: function(data, status) {
			console.log(data);
		},
		error: function(data, status, e) {
			alert(e);
			console.log(e);
		}
	})
};
listCommon = [];
function getAdminCommon(){
	$.ajax(config.url + "panel/getAdminCommon", {
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			//console.log(data);
			if(data.success){
				listCommon = data.t;
			}
		}
	});
};
replayCommon = [];
function getAdminCommand(){
	$.ajax(config.url + "panel/getAdminCommand", {
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success){
				replayCommon = data.t;
			}
		}
	});
};
getAdminCommand();
getAdminCommon();
// 实时监控控制面板
vehicleStatus = "0";
findPanelVehicleTimeOut = "0";
function findPanelVehicle(state) {
	data_json = {
		"state": state
	};
	$.ajax({
		url: config.url + "panel/findPanelVehicle",
		type: "post",
		data: data_json,
		dataType: "json",
		// timeout: 10000,
		success: function(data) {
			if(data.success) {
				$(".ctrlgrptabbtn li").eq(0).find("i").text(data.t.Num*10);
				$(".ctrlgrptabbtn li").eq(1).find("i").text(data.t.onlineNum*10);
				$(".ctrlgrptabbtn li").eq(2).find("i").text(data.t.parkingNum*10);
				$(".ctrlgrptabbtn li").eq(3).find("i").text(data.t.alarmNum*10);
//				$(".ctrlgrptabbtn li").eq(4).find("i").text(data.t.offlineNum);
				$(".ctrlgrptabbtn li").eq(4).find("i").text(data.t.Num*10-data.t.onlineNum*10-data.t.parkingNum*10-data.t.alarmNum*10);
				data = data.t.data;
				html = "";
				for(one in data) {
					html += "<tr simno='"+data[one].simNo + "'vid="+ data[one].vehicleId+"'>";
					if(listCommon.plateNo == 0){
						$("#tabcommoninfo table tbody tr td:eq(0)").addClass("off_wd");
						html += "<td displayName='1' title='" + data[one].num + "' class='off_wd'>" + data[one].num + "</td>";
					}else{
						html += "<td displayName='1' title='" + data[one].num + "'>" + data[one].num + "</td>";
					}
					if(listCommon.simNo == 0){
						$("#tabcommoninfo table tbody tr td:eq(1)").addClass("off_wd");
						html += "<td displayName='2' title='" + data[one].simNo + "' class='off_wd'>" + data[one].simNo + "</td>";
					}else{
						html += "<td displayName='2' title='" + data[one].simNo + "'>" + data[one].simNo + "</td>";
					}
					if(listCommon.type == 0){
						$("#tabcommoninfo table tbody tr td:eq(2)").addClass("off_wd");
						html += "<td displayName='3' title='" + data[one].sName + "' class='off_wd'>" + data[one].sName + "</td>";
					}else{
						html += "<td displayName='3' title='" + data[one].sName + "'>" + data[one].sName + "</td>";
					}
					if(listCommon.vColor == 0){
						$("#tabcommoninfo table tbody tr td:eq(3)").addClass("off_wd");
						html += "<td displayName='4' title='" + data[one].vmColor + "' class='off_wd'>" + data[one].vmColor + "</td>";
					}else{
						html += "<td displayName='4' title='" + data[one].vmColor + "'>" + data[one].vmColor + "</td>";
					}
					if(listCommon.driver == 0){
						$("#tabcommoninfo table tbody tr td:eq(4)").addClass("off_wd");
						if(data[one].driverName == null) {
							html += "<td displayName='5' title='" + data[one].driverName + "' class='off_wd'>" + "未知驾驶员" + "</td>";
						} else {
							html += "<td displayName='5' title='" + data[one].driverName + "' class='off_wd'>" + data[one].driverName + "</td>";
						}
					}else{
						if(data[one].driverName == null) {
							html += "<td displayName='5' title='" + data[one].driverName + "'>" + "未知驾驶员" + "</td>";
						} else {
							html += "<td displayName='5' title='" + data[one].driverName + "'>" + data[one].driverName + "</td>";
						}
					}
					if(listCommon.velDir == 0){
						$("#tabcommoninfo table tbody tr td:eq(5)").addClass("off_wd");
						html += "<td displayName='6' title='" + data[one].velocity + "km/h," + data[one].direction + "' class='off_wd'>" + data[one].velocity + "km/h," + data[one].direction + "</td>";
					}else{
						html += "<td displayName='6' title='" + data[one].velocity + "km/h," + data[one].direction + "'>" + data[one].velocity + "km/h," + data[one].direction + "</td>";
					}
					if(listCommon.localtion == 0){
						$("#tabcommoninfo table tbody tr td:eq(6)").addClass("off_wd");
						if(data[one].location == null){
							html += "<td displayName='7' title='" + data[one].location + "' class='off_wd'></td>";
						}else{
							html += "<td displayName='7' title='" + data[one].location + "' class='off_wd'>" + data[one].location + "</td>";
						}
					}else{
						if(data[one].location == null){
							html += "<td displayName='7' title='" + data[one].location + "'></td>";
						}else{
							html += "<td displayName='7' title='" + data[one].location + "'>" + data[one].location + "</td>";
						}
					}
					if(listCommon.sendTime == 0){
						$("#tabcommoninfo table tbody tr td:eq(7)").addClass("off_wd");
						html += "<td displayName='8' title='" + data[one].sendTime + "' class='off_wd'>" + data[one].sendTime + "</td>";
					}else{
						html += "<td displayName='8' title='" + data[one].sendTime + "'>" + data[one].sendTime + "</td>";
					}
					if(listCommon.status == 0){
						$("#tabcommoninfo table tbody tr td:eq(8)").addClass("off_wd");
						html += "<td displayName='9' title='" + data[one].status + "'  class='off_wd'>" + data[one].status + "</td>";
					}else{
						html += "<td displayName='9' title='" + data[one].status + "'>" + data[one].status + "</td>";
					}
					if(listCommon.alarm == 0){
						$("#tabcommoninfo table tbody tr td:eq(9)").addClass("off_wd");
						html += "<td displayName='10' title='" + data[one].alarmState + "' class='off_wd'>" + data[one].alarmState + "</td>";
					}else{
						html += "<td displayName='10' title='" + data[one].alarmState + "'>" + data[one].alarmState + "</td>";
					}
					if(listCommon.coopId == 0){
						$("#tabcommoninfo table tbody tr td:eq(10)").addClass("off_wd");
						html += "<td displayName='11' title='" + 1 + "' class='off_wd'>" + 1 + "</td>";
					}else{
						html += "<td displayName='11' title='" + 1 + "'>" + 1 + "</td>";
					}
					html += "</tr>";
				}
				$("#tabcommoninfo tbody .tablistcontent_xk table").html(html);
			}
		}
	})
}
$(document).on("mousedown", ".map_resizeline_xk", function() {
	if($(".hideandshow").is(".shown")) {
		var yy = event.pageY;
		mouseupresize(yy);
	}
})

function mouseupresize(yy) {
	$(document).mouseup(function() {
		yy = yy - event.pageY;
		$("#mapdefaulttab>.tab_main_xk").height($("#mapdefaulttab>.tab_main_xk").height() + yy);
		$("#mapdefaulttab>.tab_main_xk>.ctrlgrp").height($("#mapdefaulttab>.tab_main_xk>.ctrlgrp").height() + yy);
		$(document).off("mouseup");
	})
}
(function(){
	var thisbottombtntop ="";
	$(document).on("click", ".hideandshow", function(){
		if(thisbottombtntop==""){
			thisbottombtntop=$(this).css("top");
		}
		
		if($(this).is(".shown")) {
			$(this).removeClass("shown");
			$(this).children("img").attr("src", "img/showarrow.png");
			var thisbtn_xk = $(this)
			$("#mapdefaulttab").stop(true, true).animate({
				"bottom": -$("#mapdefaulttab").outerHeight()
			}, 250, function() {
				$(thisbtn_xk).animate({
					"top": "-30px"
				}, 200);
			});
		} else {
			$(this).addClass("shown");
			$(this).animate({
				"top": thisbottombtntop
			}, 200, function() {
				$(this).children("img").attr("src", "img/hidearrow.png");
				$("#mapdefaulttab").stop(true, true).animate({
					"bottom": 0
				}, 250);
			});
		}
	})	
}());



$(document).on("mousedown","#mapdefaulttab .tablistcontent_xk td",function(e){
	var key = e.which;
	if(key==3){
		
		var xx=e.clientX;
		xx =Math.min(Math.max(10,xx),$(window).width()-$(this).parents("#mapdefaulttab").children(".tabtextmenu").width()-10);
		var yy=e.clientY;
		yy =Math.min(Math.max(10,yy-100),$(window).height()-$(this).parents("#mapdefaulttab").children(".tabtextmenu").height()-10);
		if($(this).is("#tabcommoninfo td")){
			var thissimno = $(this).parent("tr").attr("simno");
			var thisvid = $(this).parent("tr").attr("vid");
			$(this).parents("#mapdefaulttab").children("#tabtextmenu1").attr({"simno":thissimno,"vid":thisvid}).css({"left":xx,"top":yy,"visibility":"visible"});
		}else if($(this).is("#tabcommandreply td")){
			var thisid =$(this).parent("tr").attr("id_z");
			$(this).parents("#mapdefaulttab").children("#tabtextmenu2").attr({"id_z":thisid}).css({"left":xx,"top":yy,"visibility":"visible"});
		}
		
	}
})
$(document).on("mouseleave",".tabtextmenu div.righttriangle",function(){
	$(this).children(".tabtextmenu").css("visibility","hidden");
});
$(document).on("mouseenter",".tabtextmenu div.righttriangle",function(){
	var xx=$(this).parents(".tabtextmenu").offset().left-$(document).scrollLeft();
	//console.log(xx+$(this).children(".tabtextmenu").width()*2-10);
	//console.log($(window).width());
	if(xx+$(this).children(".tabtextmenu").width()*2-10>$(window).width()){
		xx=xx-$(this).children(".tabtextmenu").width()+10;
	}else{
		xx=xx+$(this).children(".tabtextmenu").width()-10;
	}
	var yy=$(this).offset().top-$(document).scrollTop();
	yy =Math.min(Math.max(10,yy),$(window).height()-$(this).children(".tabtextmenu").height()-10);
	$(this).children(".tabtextmenu").css({"visibility":"visible","left":xx,"top":yy});
	
});
$(document).click(function(){
	$(".tabtextmenu").css("visibility","hidden");
});
$(document).on('input','.required',function(){
	if($(this).val() != null && $(this).val() != ""){
		$(this).removeClass("red_wd");
	}else{
		$(this).addClass("red_wd");
	}
});