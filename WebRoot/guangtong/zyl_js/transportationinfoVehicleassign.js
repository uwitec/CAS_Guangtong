tpa = 1;
var query_zyl = "";

// 查询全部
function getForeignfactionByPageBean() {
	$.ajax(config.url + "foreignfaction/getForeignfactionByPageBean", {
		data : {
			"currentPage" : tpa,
			"pageCount" : 10,
			"condition" : query_zyl
		},
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
			if (data.success) {
				data.obj = data.t
				$(".list_xk tfoot tr td").html("第" + data.obj.currentPage + "页 共" + data.obj.tp + "页 共" + data.obj.totalCount + "条")
				html = "";
				for (var i = 0; i < data.obj.beanList.length; i++) {
					html += '<tr zyl_id="' + data.obj.beanList[i].id + '">';
					html += '<td class="cherked_xk"><img src="img/uncherked.jpg" alt="" /></td>';
					html += '<td>' + (i + 1) + '</td>';
					html += '<td>' + data.obj.beanList[i].id + '</td>';
					if (data.obj.beanList[i].nickname == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].nickname + '</td>';
					};
					if (data.obj.beanList[i].num == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].num + '</td>';
					};
					if (data.obj.beanList[i].cName == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].cName + '</td>';
					};
					if (data.obj.beanList[i].endTime == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].endTime + '</td>';
					};
					html += '<td>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">召回</a>';
					html += '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"车辆指派" : "popupEditVehicleassign.html",
				};
				
				$(".gt_wl_right a.button").unbind('click').bind('click', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").attr("this_id", this_id);
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
					switch($(this).text()){
					case "召回":
						if(confirm("确认召回吗？")){
								var this_id = $(".popwindow_xk .popup_xk").attr("this_id");
								$.ajax(config.url + "foreignfaction/delForeignfaction", {
									data: {
										"vehArr[]": new Array(this_id),
									},
									dataType: "json",
									type: "POST",
									timeout: 10000,
									success: function(data) {
										//console.log(data.success);
										alert(data.msg);
									}
								});
								$("popwindow_xk .popup_xk").html("");
								$(".popwindow_xk").css("visibility", "hidden");
								getForeignfactionByPageBean();
						}
					break;
					case "批量召回":
						if(confirm("确认召回吗？")){
							var this_id = [];
							$(".list_xk tbody").find("img[src='img/cherked.jpg']").each(function() {
								this_id.push($(this).parent("td").parent("tr").attr("zyl_id"))
							});
							$.ajax(config.url + "foreignfaction/delForeignfaction", {
								data: {
									"vehArr[]": this_id,
								},
								dataType: "json",
								type: "POST",
								async:false,
								timeout: 10000,
								success: function(data) {
									//console.log(data.success);
									alert(data.msg);
									
									$("popwindow_xk .popup_xk").html("");
									$(".popwindow_xk").css("visibility", "hidden");
									getForeignfactionByPageBean();
									$(".cherked_xk img").attr("src",'img/uncherked.jpg');
								}
							});
						}
					break;
					default:
						console.log("---");
						break;
					}
				});
				
				fenye_lv(data.obj.tp, function() {
					getForeignfactionByPageBean();
				});
			}
		}
	});
};
//// 查询
//$(".button").click(function() {
//	if ($(this).text() == "查询") {
//		query_zyl = $(".query").val();
//		getForeignfactionByPageBean();
//	}
//});

if ($(".list_xk tbody tr").length == 1) {
	getForeignfactionByPageBean();
};
