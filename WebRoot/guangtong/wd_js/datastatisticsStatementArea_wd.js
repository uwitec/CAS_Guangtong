$(document).ready(function() {
	tpa = 1;
	var times;
	var timee;
	var area;
	var ioArea;
	//报警列表
	function areaList(){
		$.ajax(config.url + "forms/getAllPlatFormAlarm", {
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
			//	console.log(data.platform);
				for(one in data.platform) {
					html ="";
					html += '<option value="'+data.platform[one]+'">'+data.platform[one]+'</option>';
					$(".select").append(html);
				}
			}

		});
	}
	function getMileageReport() {
		$.ajax(config.url + "forms/getMileageReport", {
			data: {
				"currentPage": tpa,
				"startTime": timers,
				"endTime": timee,
				"coopArr" : cId,
				"vehArr" : vId
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				$(".list_xk tfoot tr td").html("第" + data.currentPage + "页 共" + data.tp + "页 共" + data.totalCount + "条")
				html = "";
				for(var i = 0; i < data.beanList.length; i++) {
					html += '<tr>';
					html += '<td>' + (i + 1) + '</td>';
					html += '<td>' + data.beanList[i].plateNo + '</td>';
					html += '<td>' + data.beanList[i].plateColor + '</td>';
					html += '<td>' + data.beanList[i].times + '</td>';
					html += '<td>' + data.beanList[i].timee + '</td>';
					html += '<td>' + data.beanList[i].mileage + '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				fenye_lv(data.tp, function() {
					getMileageReport();
				});
			}
		});
	};
	function searchFresh(){
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
			area = $("select[name='area']").val();
			ioArea = $("select[name='ioArea']").val();
			getMileageReport();
		});
	};
	areaList();
	getMileageReport();
	searchFresh();
	
});