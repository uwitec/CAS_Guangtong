tpa = 1;
tpa2 = 1;

var thispopup2 = {
	"sider": "popupDetailsassigndriver.html",
};
$(".popwindow_xk a.button:contains('指派')").click(function() {
	$(this).parents(".popbody_xk").siblings(".pophead_xk").find("img[src='img/close.png']").click();
});
$(".popwindow_xk .popup_xk .list_xk tbody tr").dblclick(function() {
	$(".popwindow_xk .popup_xk").html(popup2(thispopup2["sider"]));
});
/*$(".popwindow_xk .popup_xk a.button:contains(\"配送网络方案分析\")").click(function(){
	$(".popwindow2_xk .popup_xk").html(popup2("popupChart.html"));
});*/
$(".programme_z").unbind('click').bind('click', function() {
	getDriverInfo_programme_zyl()
})

function getDriverInfo_programme_zyl() {
	var programme = $(".popwindow_xk .popup_xk").attr("this_di");
	$.ajax(config.url + "order/getDriverInfo", {
		data: {
			"currentPage": tpa,
			"pageCount": 10,
			"orderNum": programme,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			$(".popup_xk[this_di] .list_xk tfoot tr td").html("第" + data.t.currentPage + "页 共" + data.t.tp + "页 共" + data.t.totalCount + "条");
			var html = "";
			for(i = 0; i < data.t.beanList.length; i++) {
				if(data.t.weight <= data.t.beanList[i].capacity) {
					html += '<tr>';
					if(data.t.beanList[i].driverId == null || data.t.beanList[i].driverId == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].driverId + '</td>';
					};
					if(data.t.beanList[i].dName == null || data.t.beanList[i].dName == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].dName + '</td>';
					};
					if(data.t.beanList[i].num == null || data.t.beanList[i].num == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].num + '</td>';
					};
					if(data.t.beanList[i].remainBulk == null || data.t.beanList[i].remainBulk == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].remainBulk + '</td>';
					};
					if(data.t.beanList[i].latitude == null || data.t.beanList[i].latitude == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].latitude + '</td>';
					};
					html += '<td>';
					html += '<a  zyl_vehicleId="' + data.t.beanList[i].vehicleId + '"  driverId=' + data.t.beanList[i].driverId + ' class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;">指派</a>';
					html += '</td>';
					html += '</tr>';
				};
			}
			$(".popbody_xk.noborder.gray1 .listgrp_xk table.list_xk tbody").html(html);
			if(html == ""){
				$(".popbody_xk.noborder.gray1 .listgrp_xk table.list_xk tbody").html("<tr><td colspan='6' style='color:red;font-size:18px;text-align:center;line-height:100px;'>您所选择的没有最优方案</td></tr>");
			}

			$("a[driverId]").unbind('click').bind('click', function() {
				$.ajax(config.url + "order/appointOrderById", {
					data: {
						id: $("div[zyl_id]").attr("zyl_id"),
						driverId: $(this).attr("driverId"),
						orderNum: $("div[zyl_di]").attr("zyl_di"),
						vehicleId: $(this).attr("zyl_vehicleId")
					},
					dataType: "json",
					type: "POST",
					timeout: 10000,
					success: function(data) {
						alert(data.msg);
						$("popwindow_xk .popup_xk").html("");
						$(".popwindow_xk").css("visibility", "hidden");
						getIndex();
					}
				});
			})
			fenye2_lv(data.t.tp, function() {
				getDriverInfo();
			});

		}
	});
}

function getDriverInfo() {
	var programme = $(".popwindow_xk .popup_xk").attr("this_di");
	$.ajax(config.url + "order/getDriverInfo", {
		data: {
			"currentPage": tpa,
			"pageCount": 10,
			"orderNum": programme,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			$(".popup_xk[this_di] .list_xk tfoot tr td").html("第" + data.t.currentPage + "页 共" + data.t.tp + "页 共" + data.t.totalCount + "条")
			var html = "";
			for(i = 0; i < data.t.beanList.length; i++) {
				html += '<tr>';
				if(data.t.beanList[i].driverId == null || data.t.beanList[i].driverId == undefined) {
					html += '<td></td>';
				} else {
					html += '<td>' + data.t.beanList[i].driverId + '</td>';
				};
				if(data.t.beanList[i].dName == null || data.t.beanList[i].dName == undefined) {
					html += '<td></td>';
				} else {
					html += '<td>' + data.t.beanList[i].dName + '</td>';
				};
				if(data.t.beanList[i].num == null || data.t.beanList[i].num == undefined) {
					html += '<td></td>';
				} else {
					html += '<td>' + data.t.beanList[i].num + '</td>';
				};
				if(data.t.beanList[i].remainBulk == null || data.t.beanList[i].remainBulk == undefined) {
					html += '<td></td>';
				} else {
					html += '<td>' + data.t.beanList[i].remainBulk + '</td>';
				};
				if(data.t.beanList[i].latitude == null || data.t.beanList[i].latitude == undefined) {
					html += '<td></td>';
				} else {
					html += '<td>' + data.t.beanList[i].latitude + '</td>';
				};
				html += '<td>';
				html += '<a  zyl_vehicleId="' + data.t.beanList[i].vehicleId + '"  driverId=' + data.t.beanList[i].driverId + ' class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;">指派</a>';
				html += '</td>';
				html += '</tr>';
			}
			$(".popbody_xk.noborder.gray1 .listgrp_xk table.list_xk tbody").html(html);

			$("a[driverId]").unbind('click').bind('click', function() {
				$.ajax(config.url + "order/appointOrderById", {
					data: {
						id: $("div[zyl_id]").attr("zyl_id"),
						driverId: $(this).attr("driverId"),
						orderNum: $("div[zyl_di]").attr("zyl_di"),
						vehicleId: $(this).attr("zyl_vehicleId")
					},
					dataType: "json",
					type: "POST",
					timeout: 10000,
					success: function(data) {
						alert(data.msg);
						$("popwindow_xk .popup_xk").html("");
						$(".popwindow_xk").css("visibility", "hidden");
						getIndex();
					}
				});
			})
			fenye2_lv(data.t.tp, function() {
				getDriverInfo();
			});

		}
	});
}
getDriverInfo();