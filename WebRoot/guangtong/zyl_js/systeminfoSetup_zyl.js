$(document).ready(function() {
	tpa = 1;
	var query_zyl = "";

	//增加
	addSystemSetting();

	function addSystemSetting() {
		$(".popcomfirm_xk .save").click(function() {
			if($(this).text() == '保存') {
				$.ajax(config.url + "cms/addSystemSetting", {
					data: {
						"description": $(".poptable2_xk_zyl tbody tr:eq(0)  td input").val(),
						"settingkey": $(".poptable2_xk_zyl tbody tr:eq(1)  td input").val(),
						"settingvalue": $(".poptable2_xk_zyl tbody tr:eq(2)  td input").val()
					},
					dataType: "json",
					type: "POST",
					timeout: 10000,
					success: function(data) {
						//console.log(JSON.stringify(data));
					}
				});
			}
			btn_zyl();
		});
		
	}

	//编辑-获取表格内容
	function toUpdateSystemSetting(this_id) {
		$.ajax(config.url + "cms/toUpdateSystemSetting", {
			data: {
				"id": this_id,
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				$(".poptable2_xk_zyl tbody tr td span").text(data.id);
				$(".poptable2_xk_zyl tbody tr:eq(1)  td input").val(data.description);
				$(".poptable2_xk_zyl tbody tr:eq(2)  td input").val(data.settingkey);
				$(".poptable2_xk_zyl tbody tr:eq(3)  td input").val(data.settingvalue);
				updateSystemSetting(this_id);
			}
		});
	};

	//双击事件弹窗
	function toUpdateSystemSetting_dblclick(this_id) {
		$.ajax(config.url + "cms/toUpdateSystemSetting", {
			data: {
				"id": this_id,
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				$(".poptable2_xk_zyl ul li:eq(0)  span").text(data.id);
				$(".poptable2_xk_zyl ul li:eq(1)  span").text(data.description);
				$(".poptable2_xk_zyl ul li:eq(2)  span").text(data.settingkey);
				$(".poptable2_xk_zyl ul li:eq(3)  span").text(data.settingvalue);
			}
		});
	};
	/*更新*/
	function updateSystemSetting(this_id) {
		$(".popcomfirm_xk .save").click(function() {
			$.ajax(config.url + "cms/updateSystemSetting", {
				data: {
					"id": this_id,
					"description": $(".poptable2_xk_zyl tbody tr:eq(1)  td input").val(),
					"settingkey": $(".poptable2_xk_zyl tbody tr:eq(2)  td input").val(),
					"settingvalue": $(".poptable2_xk_zyl tbody tr:eq(3)  td input").val()
				},
				dataType: "json",
				type: "POST",
				timeout: 10000,
				success: function(data) {
					//console.log(JSON.stringify(data));
				}
			});
		});
	};
	//获取表单
	function getSystemSettingsByPage() {
		$.ajax(config.url + "cms/getSystemSettingsByPage", {
			data: {
				"currentPage": tpa,
				"pageCount": 10,
				"condition": query_zyl
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				$(".list_xk tfoot tr td").html("第" + data.currentPage + "页 共" + data.tp + "页 共" + data.totalCount + "条")
				html = "";
				for(var i = 0; i < data.beanList.length; i++) {
					html += '<tr zyl_id="' + data.beanList[i].id + '" zyl_xx="' + i + '">';
					html += '<td>' + (i + 1) + '</td>';
					html += '<td>' + data.beanList[i].description + '</td>';
					html += '<td>' + data.beanList[i].settingkey + '</td>';
					html += '<td>' + data.beanList[i].settingvalue + '</td>';
					html += '<td>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.beanList[i].id + '" zyl_xx="' + i + '">编辑</a>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.beanList[i].id + '" zyl_xx="' + i + '">删除</a>';
					html += '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var data_json = data;
				var thispopup = {
					"编辑": "popupEditsystem.html",
					"删除": "deleteconfirm.html",
					"+增加": "popupAddsystem.html",
					"+增加子账户": "popupAddsystem.html",
					"查询": "",
					"sider": "popupDetailssystem.html"
				};
				$(".gt_wl_right a.button").click(function() {
					var this_id = $(this).attr("zyl_id");
					var this_i = $(this).attr("zyl_xx");
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
					$(".popwindow_xk .popup_xk").attr("this_id", this_id)
					if($(this).text() == "编辑") {
						//toUpdateSystemSetting(data_json,this_id, this_i);
						toUpdateSystemSetting(this_id);
					}
				});
				$(".gt_wl_right .list_xk tbody tr").dblclick(function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
					toUpdateSystemSetting_dblclick(this_id);
				});
				fenye_lv(data.tp, function() {
					getSystemSettingsByPage();
				});
				/*
				function toUpdateSystemSetting(data_json,this_id, this_i) {
					$(".poptable2_xk_zyl tbody tr:eq(0) td span").text(data_json.beanList[this_i].id);
					$(".poptable2_xk_zyl tbody tr:eq(1)  td input").val(data_json.beanList[this_i].description);
					$(".poptable2_xk_zyl tbody tr:eq(2)  td input").val(data_json.beanList[this_i].settingkey);
					$(".poptable2_xk_zyl tbody tr:eq(3)  td input").val(data_json.beanList[this_i].settingvalue);
					updateSystemSetting(this_id)
				};*/
			}
		});
	};
	getSystemSettingsByPage();

	//查询
	$(".button").click(function() {
		if($(this).text() == "查询") {
			query_zyl = $(".query").val();
			getSystemSettingsByPage();
		}
	});
});