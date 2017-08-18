$(document).ready(function() {
	tpa = 1;
	var cId = [];
	var vId = [];
	var times;
	var timee;
	var callType;
	//车辆列表
	function getCarList() {
		$.ajax(config.url + "vehicle/getVehicleByAdmin", {
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					data.obj == null ? data = data.t : data = data.obj;
					html = "";
					for (one in data) {
						html += "<dl>";
						html += '<dt ><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + one+ '</dt>';
						html+="<dd>";
						for(two in data[one]){
							if(two.indexOf("TEAM")!=-1){
								for(three in data[one][two]){
									html +="<dl>";
									html +='<dt><i><img style="height:18px;" src="img/u426.png"/></i><b class="cherked_xk" vId="' + data[one][two][three].simNo + '"><img src="img/uncherked.jpg" defaultChecked/></b>' + data[one][two][three].num + '</dt>';
									html+="</dl>";
								}
							}else{
								html +="<dl>";
								html +='<dt><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' +two+ '</dt>';
								html +="<dd>";
								for(three in data[one][two]){
									html +="<dl>";
										html +='<dt><i><img style="height:18px;" src="img/u426.png"/></i><b class="cherked_xk" vId="' + data[one][two][three].simNo + '"><img src="img/uncherked.jpg" defaultChecked/></b>' + data[one][two][three].num + '</dt>';
									html+="</dl>";
								}
								html+="</dd>";
								html+="</dl>";
							}	
						}
						html+="</dd>";
						html+="</dl>";
					}
					$("dd#carList").html(html);
				}
			}

		});
	}

	function getGpsInfosData() {
		var data_json = {
			"currentPage": tpa,
			"startTime": times,
			"endTime": timee,
			"coop": cId,
			"vehArr": vId,
			"type": callType
		}
		$.ajax(config.url + "charts/getGpsInfosData", {
			data: data_json,
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					data.obj == null ? data = data.t : data = data.obj;
					$(".list_xk tfoot tr td").html("第" + data.currentPage + "页 共" + data.tp + "页 共" + data.totalCount + "条")
					html = "";
					for(var i = 0; i < data.beanList.length; i++) {
						html += '<tr>';
						html += '<td>' + (i + 1) + '</td>';
						if(data.beanList[i].plateNo == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].plateNo + '</td>';
						};
						if(data.beanList[i].plateColor == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].plateColor + '</td>';
						};
						if(data.beanList[i].percentage == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].percentage + '</td>';	
						};
						html += '</tr>';
					}
					$(".list_xk tbody").html(html);
					fenye_lv(data.tp, function() {
						getGpsInfosData();
					});
				}
			}
		});
	};

	function searchFresh() {
		$("#search").click(function() {
			cId = [];
			vId = [];
			for(var i = 0; i < $("#carList img").length; i++) {
				if($("#carList img:eq(" + i + ")").attr("src") == "img/cherked.jpg") {
					if($("#carList img:eq(" + i + ")").parent("b").attr("vId") != undefined && $("#carList img:eq(" + i + ")").parent("b").attr("vId") != null) {
						vId.push($("#carList img:eq(" + i + ")").parent("b").attr("vId"));
					} else {
						cId.push($("#carList img:eq(" + i + ")").parent("b").attr("cId"));
					}
				}
			}
			times = $(".time_plugin_lv_onclick:eq(0)").val();
			timee = $(".time_plugin_lv_onclick:eq(1)").val();
			callType = $(".select").val();
			getGpsInfosData();
		});
	};

	function exportExcel() {
		$(".inquirybox_xk .inquiry_xk a.button:eq(1)").click(function() {
			cId = [];
			vId = [];
			for(var i = 0; i < $("#carList img").length; i++) {
				if($("#carList img:eq(" + i + ")").attr("src") == "img/cherked.jpg") {
					if($("#carList img:eq(" + i + ")").parent("b").attr("vId") != undefined && $("#carList img:eq(" + i + ")").parent("b").attr("vId") != null) {
						vId.push($("#carList img:eq(" + i + ")").parent("b").attr("vId"));
					} else {
						cId.push($("#carList img:eq(" + i + ")").parent("b").attr("cId"));
					}
				}
			}
			location.href = config.url + "charts/OnlineRateExcel?simNo=" + vId + "&startTime=" + $(".time_plugin_lv_onclick:eq(0)").val() + "&endTime=" + $(".time_plugin_lv_onclick:eq(1)").val()+"&type="+$(".select option:selected").val();
		});
	}
	exportExcel();
	getCarList();
	getGpsInfosData();
	searchFresh();
});