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
	//报警列表
	function callList() {
		$.ajax(config.url + "forms/getAllPlatFormAlarm", {
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					data.obj == null ? data = data.t : data = data.obj;
					for(one in data.platform) {
						html = "";
						html += '<option value="' + data.platform[one] + '">' + data.platform[one] + '</option>';
						$(".select").append(html);
					}
				}
			}
		});
	}

	function getPlatFormsByPage() {
		var data_json = {
			"currentPage": tpa,
			"startTime": times,
			"endTime": timee,
			"coop": cId,
			"vehArr": vId,
			"type": callType
		}
		$.ajax(config.url + "forms/getPlatFormsByPage", {
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
						if(data.beanList[i].driverName == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].driverName + '</td>';
						};
						if(data.beanList[i].plateColor == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].plateColor + '</td>';
						};
						if(data.beanList[i].longitude == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].longitude + '</td>';
						};
						if(data.beanList[i].latitude ==null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].latitude + '</td>';
						};
						if(data.beanList[i].location == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].location + '</td>';
						};
						if(data.beanList[i].velocity == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].velocity + '</td>';
						};
						if(data.beanList[i].type == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].type + '</td>';
						};
						if(data.beanList[i].solution == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].solution + '</td>';
						};
						if(data.beanList[i].adminId == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].adminId + '</td>';
						};
						if(data.beanList[i].dealWay == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].dealWay + '</td>';	
						};
						if(data.beanList[i].dealTime == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].dealTime + '</td>';
						};
						html += '</tr>';
					}
					$(".list_xk tbody").html(html);
					fenye_lv(data.tp, function() {
						getPlatFormsByPage();
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
			getPlatFormsByPage();
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
			location.href = config.url + "forms/getPlatFormsByPageExcel?simNos=" + vId + "&startTime=" + $(".time_plugin_lv_onclick:eq(0)").val() + "&endTime=" + $(".time_plugin_lv_onclick:eq(1)").val()+"&type="+$(".inquiry_xk select option:selected").val()
;
		});
	}
	exportExcel();
	getCarList();
	callList();
	getPlatFormsByPage();
	searchFresh();

});