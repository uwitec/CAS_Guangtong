tpa = 1;
// 编辑
// 编辑-下拉
function getCooperations(this_id) {
	$.ajax(config.url + "vehicleteam/getCooperations", {
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			for(var i = 0; i < data.obj.length; i++) {
				if(data.obj[i].cName == undefined){
					$(".poptable2_xk_zyl tbody tr:eq(0) td select").append('<option value="' + data.obj[i].id + '" zyl_id="' + data.obj[i].id + '"></option>');
				}else{
					$(".poptable2_xk_zyl tbody tr:eq(0) td select").append('<option value="' + data.obj[i].id + '" zyl_id="' + data.obj[i].id + '">' + data.obj[i].cName + '</option>');
				}
			}
			getVehicleTeamById(this_id);
		}
	});
};
//编辑
function getVehicleTeamById(this_id) {
	$.ajax(config.url + "vehicleteam/getVehicleTeamById", {
		data: {
			"vehicleTeamId": this_id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				data.obj = data.t;
				$(".poptable2_xk_zyl tbody tr:eq(0) td select option").each(
					function() {
						if($(this).val() == data.obj.cooperationId) {
							$(this).attr("selected", "selected");
						} else {
							$(this).removeAttr("selected");
						}
					});
				$(".poptable2_xk_zyl tbody tr:eq(1) td input").val(data.obj.tName);
				update_zyl(this_id);
			}
		}
	});
}
/* 更新 */
function update_zyl(this_id) {
	$(".popcomfirm_xk .save").click(function() {
		data_json = {
			"id": this_id,
			"cooperationId": $(".poptable2_xk_zyl tbody tr:eq(0) td select option:selected").val(),
			"tName": $(".poptable2_xk_zyl tbody tr:eq(1) td input").val(),
		}
		$.ajax(config.url + "vehicleteam/update", {
			data: data_json,
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(data.success);
				alert(data.msg);
				
				$("popwindow_xk .popup_xk").html("");
				$(".popwindow_xk").css("visibility", "hidden");
				getVehicleTeamByPageBean();
			}
		});
		
	});
}
// 双击弹窗
function getVehicleTeamById_dblclick(this_id) {
	$.ajax(config.url + "vehicleteam/getVehicleTeamById", {
		data: {
			"vehicleTeamId": this_id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				data.obj = data.t;
				$(".poptable2_xk_zyl ul li:eq(0) span").text(data.obj.id);
				if(data.obj.cName == null){
					$(".poptable2_xk_zyl ul li:eq(1) span").text("");
				}else{
					$(".poptable2_xk_zyl ul li:eq(1) span").text(data.obj.cName);
				}
				if(data.obj.tName == null){
					$(".poptable2_xk_zyl ul li:eq(2) span").text("");
				}else{
					$(".poptable2_xk_zyl ul li:eq(2) span").text(data.obj.tName);
				}
				
			}
		}
	});
}
// 查询
function getVehicleTeamByPageBean() {
	$.ajax(config.url + "vehicleteam/getVehicleTeamByPageBean", {
		data: {
			"currentPage": tpa,
			"pageCount": 10,
			"id" : $(".list_xk thead input[name='id']").val(),
			"cName" : $(".list_xk thead input[name='cName']").val(),
			"tName": $(".list_xk thead input[name='tName']").val(),
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
					if(data.obj.beanList[i].id == null){
						html += '<td style="text-align:center"></td>';
					}else{
						html += '<td style="text-align:center">' + data.obj.beanList[i].id + '</td>';	
					}
					
					if(data.obj.beanList[i].cName == null) {
						html += '<td></td>';
					} else {
						html += '<td style="text-align:center">' + data.obj.beanList[i].cName + '</td>';
					};
					if(data.obj.beanList[i].tName == null) {
						html += '<td></td>';
					} else {
						html += '<td style="text-align:center">' + data.obj.beanList[i].tName + '</td>';
					};
					html += '<td style="text-align:center">';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">编辑</a>';
					html += '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"编辑": "popupEditFleetManagement.html",
					"删除": "transportationinfoFleetManagement_del.html",
					"+增加": "popupAddFleetManagement.html",
					"查询": "",
					"sider": "popupDetailsFleetManagement.html"
				};
				$(".gt_wl_right a.button").unbind('click').bind('click', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").attr("this_id", this_id);
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
					if($(this).text() == "编辑") {
						getCooperations(this_id);
					}
				});
				$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
					getVehicleTeamById_dblclick(this_id);
				});
				fenye_lv(data.obj.tp, function() {
					getVehicleTeamByPageBean();
				});
			}
		}
	});
};
if($(".list_xk tbody tr").length == 1) {
	getVehicleTeamByPageBean();
}
//模糊搜索
function VehicleTeamSearch(){
	$(".list_xk thead input").bind('input propertychange',function(){
		getVehicleTeamByPageBean();
	})
};
VehicleTeamSearch();
