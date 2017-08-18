tpa = 1;
//编辑
function selectModelInfoById(this_id) {
	$.ajax(config.url + "model/selectModelInfoById", {
		data: {
			"id": this_id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				data.obj = data.t;
				$(".poptable2_xk_zyl tbody tr td span").text(data.obj.id);
				$(".poptable2_xk_zyl tbody tr:eq(1) td input").val(data.obj.desc);
				$(".poptable2_xk_zyl tbody tr:eq(2) td input").val(data.obj.bulk);
				$(".poptable2_xk_zyl tbody tr:eq(3) td input").val(data.obj.lwh);
				$(".poptable2_xk_zyl tbody tr:eq(4) td input").val(data.obj.capacity)
				updateModelInfo(this_id);
			}
		}
	});
}
/*更新*/
function updateModelInfo(this_id) {
	$(".popcomfirm_xk .save").click(function() {
		data_json = {
			"id": this_id,
			"desc": $(".poptable2_xk_zyl tbody tr:eq(1) td input").val(),
			"bulk": $(".poptable2_xk_zyl tbody tr:eq(2) td input").val(),
			"lwh": $(".poptable2_xk_zyl tbody tr:eq(3) td input").val(),
			"capacity": $(".poptable2_xk_zyl tbody tr:eq(4) td input").val()
		}
		$.ajax(config.url + "model/updateModelInfo", {
			data: data_json,
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(data.success);
				alert(data.msg);
				$("popwindow_xk .popup_xk").html("");
				$(".popwindow_xk").css("visibility", "hidden");
				selectModelInfoAll();
			}
		});
		
	});
}
//双击
function selectModelInfoAllById_dblclick(this_id) {
	$.ajax(config.url + "model/selectModelInfoById", {
		data: {
			"id": this_id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				data.obj = data.t;
				$(".poptable2_xk_zyl ul li:eq(0) span").text(data.obj.id);
				$(".poptable2_xk_zyl ul li:eq(1) span").text(data.obj.desc);
				$(".poptable2_xk_zyl ul li:eq(2) span").text(data.obj.bulk);
				$(".poptable2_xk_zyl ul li:eq(3) span").text(data.obj.lwh);
				$(".poptable2_xk_zyl ul li:eq(4) span").text(data.obj.capacity);
			}
		}
	});
}
//查询
function selectModelInfoAll() {
	$.ajax(config.url + "model/selectModelInfoAll", {
		data: {
			"currentPage": tpa,
			"pageCount": 10,
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
					html += '<tr zyl_id="' + data.obj.beanList[i].id + '">';
					html += '<td>' + (i + 1) + '</td>';
					if(data.obj.beanList[i].desc == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].desc + '</td>';
					};
					if(data.obj.beanList[i].bulk == null ){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].bulk + '</td>';
					};
					if(data.obj.beanList[i].lwh == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].lwh + '</td>';
					};
					if(data.obj.beanList[i].capacity == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].capacity + '</td>';
					};
					html += '<td>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">编辑</a>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">删除</a>';
					html += '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"编辑": "popupEditvehicletype.html",
					"删除": "transportationinfoVehicletype_del.html",
					"+增加": "popupAddvehicletype.html",
					"查询": "",
					"sider": "popupDetailsvehicletype.html"
				};
				$(".gt_wl_right a.button").unbind('click').bind('click', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").attr("this_id", this_id);
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
					if($(this).text() == "编辑") {
						selectModelInfoById(this_id);
					}
				});
				$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
					selectModelInfoAllById_dblclick(this_id);
				});
				fenye_lv(data.obj.tp, function() {
					selectModelInfoAll();
				});
			}
		}
	});
};
if($(".list_xk tbody tr").length == 1) {
	selectModelInfoAll();
}