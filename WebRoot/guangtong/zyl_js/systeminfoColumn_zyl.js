$(document).ready(function() {
	tpa = 1;
	var query_zyl = "";
	//增加 --接口名未改
	/*addSystemSetting();
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
		});
	}*/
	//编辑
	function toUpdateMenu(this_id) {
		$.ajax(config.url + "cms/toUpdateMenu", {
			data: {
				"id": this_id,
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				$(".poptable2_xk_zyl tbody tr td span").text(data.id);
				if(data.parent == null || data.parent == '') {
					$(".poptable2_xk_zyl tbody tr:eq(1)  td select option").attr('id',data.parentId);
				} else {
					$(".poptable2_xk_zyl tbody tr:eq(1)  td select option").attr('id',data.parentId);
					$(".poptable2_xk_zyl tbody tr:eq(1)  td select option").text(data.parent.name);
				}
				$(".poptable2_xk_zyl tbody tr:eq(2)  td input").val(data.name);
				$(".poptable2_xk_zyl tbody tr:eq(3)  td input").val(data.contentDiv);
				$(".poptable2_xk_zyl tbody tr:eq(4)  td input").val(data.mSort);
				updateMenu(this_id);
			}
		});
	};
	/*更新(保存)*/
	function updateMenu(this_id) {
		$(".popcomfirm_xk .save").click(function() {
			alert(this_id);
			$.ajax(config.url + "cms/updateMenu", {
				data: {
					"id": this_id,
					"parentId": $(".poptable2_xk_zyl tbody tr:eq(1)  td select option").text(),
					"name": $(".poptable2_xk_zyl tbody tr:eq(2)  td input").val(),
					"contentDiv": $(".poptable2_xk_zyl tbody tr:eq(3)  td input").val(),
					"mSort": $(".poptable2_xk_zyl tbody tr:eq(4)  td input").val()
				},
				dataType: "json",
				type: "POST",
				timeout: 10000,
				success: function(data) {
					console.log(JSON.stringify(data));
				}
			});
		});
	};

	//双击事件弹窗
	function toUpdateMenu_dblclick(this_id) {
		$.ajax(config.url + "cms/toUpdateMenu", {
			data: {
				"id": this_id,
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				$(".poptable2_xk_zyl ul li:eq(0)  span").text(data.id);
				$(".poptable2_xk_zyl ul li:eq(1)  span").text(data.parent.name);
				$(".poptable2_xk_zyl ul li:eq(2)  span").text(data.name);
				$(".poptable2_xk_zyl ul li:eq(3)  span").text(data.contentDiv);
				$(".poptable2_xk_zyl ul li:eq(4)  span").text(data.mSort);
			}
		});
	};
	//获取表单
	function getMenusByPage() {
		$.ajax(config.url + "cms/getMenusByPage", {
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
					html += '<tr zyl_id="' + data.beanList[i].id + '">';
					html += '<td>' + (i + 1) + '</td>';
					html += '<td>' + data.beanList[i].name + '</td>';
					if(data.beanList[i].parent == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.beanList[i].parent.name + '</td>';
					};
					if(data.beanList[i].contentDiv == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.beanList[i].contentDiv + '</td>';
					};
					html += '<td>' + data.beanList[i].mSort + '</td>';
					html += '<td>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.beanList[i].id + '">编辑</a>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;">删除</a>';
					html += '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"编辑": "popupEditcolumn.html",
					"删除": "deleteconfirm.html",
					"+增加": "popupAddcolumn.html",
					"+增加子账户": "popupAddcolumn.html",
					"查询": "",
					"sider": "popupDetailscolumn.html"
				};
				$(".gt_wl_right a.button").click(function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
					if($(this).text() == "编辑") {
						toUpdateMenu(this_id);
					}
				});
				$(".gt_wl_right .list_xk tbody tr").dblclick(function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
					toUpdateMenu_dblclick(this_id);
				});
				fenye_lv(data.tp, function() {
					getMenusByPage();
				});
			}
		});
	};
	getMenusByPage();
	//模糊查询
	$(".button").click(function() {
		if($(this).text() == "查询") {
			alert('a');
			query_zyl = $(".query").val();
			getMenusByPage();
		}
	});
});