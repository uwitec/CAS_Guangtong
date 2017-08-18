tpa = 1;
//编辑
function getTerminalByTermNo(this_id) {
	$.ajax(config.url + "terminal/getTerminalByTermNo", {
		data: {
			"termId": this_id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				data.obj = data.t;
				$(".poptable2_xk_zyl tbody tr td span").text(data.obj.termNo);
				$(".poptable2_xk_zyl tbody tr:eq(1) td input").val(data.obj.devNo);
				$(".poptable2_xk_zyl tbody tr:eq(2) td input").val(data.obj.termType);
				$(".poptable2_xk_zyl tbody tr:eq(3) td input").val(data.obj.instAlladdress);
				$(".poptable2_xk_zyl tbody tr:eq(4) td input").val(data.obj.owner);
				$(".poptable2_xk_zyl tbody tr:eq(5) td input").val(data.obj.updateTime);
				upDateTerminal(this_id);
			}
		}
	});
}
/*更新*/
function upDateTerminal(this_id) {
	$(".popcomfirm_xk .save").click(function() {
		data_json = {
			"termId": this_id,
			"devNo": $(".poptable2_xk_zyl tbody tr:eq(1) td input").val(),
			"termType": $(".poptable2_xk_zyl tbody tr:eq(2) td input").val(),
			"instAlladdress": $(".poptable2_xk_zyl tbody tr:eq(3) td input").val(),
			"termType": $(".poptable2_xk_zyl tbody tr:eq(4) td input").val(),
			"updateTime": $(".poptable2_xk_zyl tbody tr:eq(5) td input").val(),
		}
		
		$.ajax(config.url + "terminal/updateTerminal", {
			data: data_json,
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(data.success);
				alert(data.msg);
				$("popwindow_xk .popup_xk").html("");
				$(".popwindow_xk").css("visibility", "hidden");
				getTerminalAll();
			}
		});
		
	});
}

//双击弹窗
function getTerminalByTermNo_dblclick(this_id) {
	$.ajax(config.url + "terminal/getTerminalByTermNo", {
		data: {
			"termId": this_id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				data.obj = data.t;
				$(".poptable2_xk_zyl ul  li:eq(0) span").text(data.obj.termNo);
				$(".poptable2_xk_zyl ul  li:eq(1) span").text(data.obj.devNo);
				$(".poptable2_xk_zyl ul  li:eq(2) span").text(data.obj.termType);
				$(".poptable2_xk_zyl ul  li:eq(3) span").text(data.obj.instAlladdress);
				$(".poptable2_xk_zyl ul  li:eq(4) span").text(data.obj.updateTime);
			}
		}
	});
}
//查询
function getTerminalAll() {
	$.ajax(config.url + "terminal/getTerminalAll", {
		data: {
			"currentPage": tpa,
			"pageCount": 10,
			"termNo":$(".list_xk thead input[name='termNo']").val(),
			"devNo":$(".list_xk thead input[name='devNo']").val(),
			"termType":$(".list_xk thead input[name='termType']").val(),
			"instAlladdress":$(".list_xk thead input[name='instAlladdress']").val(),
			"owner":$(".list_xk thead input[name='owner']").val(),
			"updateTime":$(".list_xk thead input[name='updateTime']").val(),
			
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				data.obj = data.t;

				$(".list_xk tfoot tr td").html("第" + data.obj.currentPage + "页 共" + data.obj.tp + "页 共" + data.obj.totalCount + "条")
				html = "";
				for(var i = 0; i < data.obj.beanList.length; i++) {
					html += '<tr zyl_id="' + data.obj.beanList[i].termId + '">';
					html += '<td>' + (i + 1) + '</td>';
					if(data.obj.beanList[i].termNo == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].termNo + '</td>';
					};
					if(data.obj.beanList[i].devNo == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].devNo + '</td>';
					};
					if(data.obj.beanList[i].termType == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].termType + '</td>';	
					};
					if(data.obj.beanList[i].instAlladdress == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].instAlladdress + '</td>';
					};
					if(data.obj.beanList[i].termType == null ){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].owner + '</td>';	
					};
					if(data.obj.beanList[i].updateTime == null ){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].updateTime + '</td>';
					};
					html += '<td>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].termId + '">编辑</a>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].termId + '">删除</a>';
					html += '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"编辑": "popupEditvehicleterminal.html",
					"删除": "transportationinfoVehicleterminal_del.html",
					"+增加": "popupAddvehicleterminal.html",
					"查询": "",
					"sider": "popupDetailsvehicleterminal.html"
				};
				$(".gt_wl_right a.button").unbind('click').bind('click', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").attr("this_id", this_id);
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
					if($(this).text() == "编辑") {
						getTerminalByTermNo(this_id);
					}
				});
				$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
					getTerminalByTermNo_dblclick(this_id);
				});
				fenye_lv(data.obj.tp, function() {
					getTerminalAll();
				});
			}
		}
	});
};
if($(".list_xk tbody tr").length == 1) {
	getTerminalAll();
}

//模糊搜索
function terminalSearch(){
	$(".list_xk thead input").bind('input propertychange',function(){
		getTerminalAll();
	})
};
terminalSearch();