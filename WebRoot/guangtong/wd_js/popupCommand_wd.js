
	tpa = 1;
	var timee;
	var times;

	function monitorByCamera() {
		//console.log(vehicleInfo);
		$.ajax(config.url + "port/monitorByCamera", {
			data: {
				"shootingcommand": "1",
				"plateno": vehicleInfo.plateNo,
				"simno": vehicleInfo.simNo,
				"vehicleid": vehicleInfo.vid,
				"passagewayid": $("table:eq(0) input.input_xk:eq(0)").val(),
				"phtime": $("table:eq(0) input.input_xk:eq(4)").val(),
				"saveflag": $("table:eq(0) select:eq(0) option:selected").val(),
				"resolvingpower": $("table:eq(0) select:eq(1) option:selected").val(),
				"imagequality": $("table:eq(0) input.input_xk:eq(6)").val(),
				"brightness": $("table:eq(0) input.input_xk:eq(1)").val(),
				"contrastratio": $("table:eq(0) input.input_xk:eq(5)").val(),
				"saturation": $("table:eq(0) input.input_xk:eq(3)").val(),
				"chroma": $("table:eq(0) input.input_xk:eq(7)").val()
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				getMediaItemByPage(vehicleInfo.simNo);
			},
		});
	};

	function getMediaItemByPage(simno) {
		$.ajax(config.url + "port/getMediaItemByPage", {
			data: {
				"currentPage": tpa,
				"pageCount": 10,
				"simNo": "98765432101",
				"startTime": $(".time_plugin_lv_onclick:eq(0)").val(),
				"endTime": $(".time_plugin_lv_onclick:eq(1)").val(),
				"mediaType": "0"

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
						html += '<li>';
						html += '<img src="' + picHead + data.beanList[i].filename + '" alt="" style="height:40px;" />';
						html += '<span style="line-height: 40px;">' + data.beanList[i].filename + '</span>';
						html += '<a style="line-height: 40px;">下载</a>';
						html += '</li>';
					}
					$(".scrollimg_xk").html(html);
					fenye_lv(data.tp, function() {
						getMediaItemByPage();
					});
				}
			}
		});
	}

	function searchFresh() {
		$("#search").click(function() {
			times = $(".time_plugin_lv_onclick:eq(0)").val();
			timee = $(".time_plugin_lv_onclick:eq(1)").val();
			getMediaItemByPage();
		});
	};
	monitorByCamera();
	searchFresh();
