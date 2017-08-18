	tpa = 1;
	var vId;
	var times;
	var timee;
	var dName;

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
						html += '<dt ><i><img src="img/u380.png"></i>' + one+ '</dt>';
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
								html +='<dt><i><img src="img/u380.png"></i>' +two+ '</dt>';
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

	function driverOfTable() {
		$.ajax(config.url + "forms/driverOfTable", {
			data: {
				"currentPage": tpa,
				"startTime": times,
				"endTime": timee,
				"simNo": vId,
				"dName": dName
			},
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
						if(data.beanList[i].id == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].id + '</td>';
						};
						if(data.beanList[i].dName == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].dName + '</td>';
						};
						if(data.beanList[i].professionNum == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].professionNum + '</td>';
						};
						if(data.beanList[i].institutionName == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].institutionName + '</td>';
						};
						if(data.beanList[i].professionValidDate == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].professionValidDate + '</td>';
						};
						if(data.beanList[i].defaultVehicleNum == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].defaultVehicleNum + '</td>';
						};
						if(data.beanList[i].plateColor == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].plateColor + '</td>';
						}
						if(data.beanList[i].onlineTime == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].onlineTime + '</td>';
						}
						if(data.beanList[i].offlineTime == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].offlineTime + '</td>';
						}
						html += '</tr>';
					}
					$(".list_xk tbody").html(html);
					fenye_lv(data.tp, function() {
						driverOfTable();
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
						vId = $("#carList img:eq(" + i + ")").parent("b").attr("vId");
					}
				}
			}
			times = $(".time_plugin_lv_onclick:eq(0)").val();
			timee = $(".time_plugin_lv_onclick:eq(1)").val();
			dName = $("input[placeholder='驾驶员姓名']").val();
			driverOfTable();
		});
	};

	function exportExcel() {
		$(".inquirybox_xk .inquiry_xk a.button:eq(1)").click(function() {
			vId = "";
			for(var i = 0; i < $("#carList img").length; i++) {
				if($("#carList img:eq(" + i + ")").attr("src") == "img/cherked.jpg") {
					if($("#carList img:eq(" + i + ")").parent("b").attr("vId") != undefined && $("#carList img:eq(" + i + ")").parent("b").attr("vId") != null) {
						vId = $("#carList img:eq(" + i + ")").parent("b").attr("vId");
					}
				}
			}
			location.href = config.url + "forms/getDriverFormExcel?simNo=" + vId + "&startTime=" + $(".time_plugin_lv_onclick:eq(0)").val() + "&endTime=" + $(".time_plugin_lv_onclick:eq(1)").val() + "&dName=" + $(".inquiry_xk .query").val();
		});
	}
	exportExcel();
	getCarList();
	driverOfTable();
	searchFresh();
