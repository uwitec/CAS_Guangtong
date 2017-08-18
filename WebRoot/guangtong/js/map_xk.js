$(document).on("click", ".button:not('.on_xk')", function() {
	switch($(this).text()) {
		case "开启监控":
			$(".button").each(function() {
				switch($(this).text()) {
					case "关闭监控":
						$(this).removeClass("on_xk");
						break;
					case "开启监控":
						$(this).addClass("on_xk");
						break;
					default:
						break;
				};
			});
			$(".map_top_xk").hide();
			$(".map_xk").height(840);
			$(".listgrp_xk").css("height","auto");
			$(".map_right_xk").height(832);
			$(".map_right_xk .dendrogram_xk").height(737);
			$(".tab_xk").hide();
			$(".map_main_xk").remove();
			$("#mapbottomnottab").show();
			monitorByCamera();
			var html = "";
			html += '<div class="map_main_xk" style="height:440px;margin-right:10px;">';
			html += '<div class="camerabox_xk">';
			html += '<div class="camera_xk green"><video style="width:100%;height:100%" autoplay="autoplay"><source src="wd_js/monitor.mp4" type="video/mp4"></video></div>';
			html += '</div>';
			html += '<div class="camerabox_xk">';
			html += '<div class="camera_xk blue"><video style="width:100%;height:100%" autoplay="autoplay"><source src="wd_js/monitor.mp4" type="video/mp4"></video></div>';
			html += '</div>';
			html += '<div class="camerabox_xk">';
			html += '<div class="camera_xk pink"><video style="width:100%;height:100%" autoplay="autoplay"><source src="wd_js/monitor.mp4" type="video/mp4"></video></div>';
			html += '</div>';
			html += '<div class="camerabox_xk">';
			html += '<div class="camera_xk orange"><video style="width:100%;height:100%" autoplay="autoplay"><source src="wd_js/monitor.mp4" type="video/mp4"></video></div>';
			html += '</div>';
			html += '<div class="clear"></div>';
			html += '</div>';
			$(".map_left_xk").prepend(html);
			
			break;
		case "关闭监控":
			$(".button").each(function() {
				switch($(this).text()) {
					case "开启监控":
						$(this).removeClass("on_xk");
						break;
					case "关闭监控":
						$(this).addClass("on_xk");
						break;
					default:
						break;
				};
			});
			$(".map_top_xk").show();
			$(".tab_xk").show();
			$("#mapbottomnottab").remove();
			$(".map_xk").css("height","100%");
			$(".listgrp_xk").css("height","100%");
			$(".map_right_xk").height($(window).height() - 162);
			$(".map_right_xk .dendrogram_xk").css("overflow-y","auto").height($(window).height() - 162);
			var html = '<div class="map_bottom_xk" id="mapbottomnottab" style="display:none;"></div>';
			$("#mapbottomtab").before(html);
			$(".map_main_xk").remove();
			html = '<div class="map_main_xk" id="container" style="margin-right:10px;"></div>';
			$(".map_left_xk").prepend(html);
			map_wd();
			break;
		case "发出命令":
			$(".popwindow_xk .popup_xk").html(popup("popupCommand.html"));
			break;
		default:
			break;
	};

	function map_one_wd() {
		var map = new AMap.Map('container', {
			resizeEnable: true,
			zoom: 9,
		});
		AMap.plugin(['AMap.ToolBar', 'AMap.Scale', 'AMap.OverView'], function() {
			map.addControl(new AMap.ToolBar());
			map.addControl(new AMap.Scale());
		});
		var lnglat = [];
		lnglat[0] = vehicleInfo.longitude;
		lnglat[1] = vehicleInfo.latitude;

		function createMarkerOne() {
			var marker = new AMap.Marker({
				draggable: false,
				cursor: "move",
				position: lnglat,
			});
			
			var markerContent = document.createElement("div");
			markerContent.style.position = "relative";
			var markerImg = document.createElement("img");
			var fontContent = document.createElement("div");
			fontContent.style.textAlign = "center";
			fontContent.style.width = "36px";
			fontContent.style.lineHeight = "36px";
			fontContent.style.position = "absolute";
			fontContent.style.color = "white";
			fontContent.style.fontSize = "14px";
			fontContent.style.zIndex = "2";
			markerImg.style.position = "absolute";
			markerImg.style.zIndex = "1";
			markerContent.appendChild(fontContent);
			switch (vehicleInfo.sName) {
			case "冷链配送":
				markerImg.src = "img/lenglian.png";
				fontContent.innerHTML = "冷";
				break;
			case "干线运输":
				markerImg.src = "img/ganxian.png";
				fontContent.innerHTML = "干";
				break;
			case "长途客车":
				markerImg.src = "img/changtu.png";
				fontContent.innerHTML = "长";
				break;
			case "集港物流":
				markerImg.src = "img/jigang.png";
				fontContent.innerHTML = "集";
				break;
			case "危险品零售":
				markerImg.src = "img/weixianpin.png";
				fontContent.innerHTML = "危";
				break;
			case "仓储配送":
				markerImg.src = "img/cangchu.png";
				fontContent.innerHTML = "仓";
				break;
			default:
				markerImg.src = "img/huoche.png";
				fontContent.innerHTML = "货";
				break;
			}
			markerImg.style.transform = "rotate(" + vehicleInfo.direction + "deg)"; //设置图片的方向
			markerImg.style.width = "36px";
			markerImg.style.height = "36px";
			markerContent.appendChild(markerImg);
			marker.setContent(markerContent);
			marker.simno = vehicleInfo.simNo;
			marker.vid = vehicleInfo.id;
			marker.setMap(map);
			map.setFitView();
		};
		function updateVehicle() {
			setTimeout(function() {
				if($(".map_top_xk").css('display') == "none") {
					createMarkerOne();
					updateVehicle();
				} else {
					return;
				}
			}, 3000);
		};
		createMarkerOne();
		updateVehicle();
	}

	function monitorByCamera() {
		//console.log(vehicleInfo);
		$.ajax(config.url + "port/monitorByCamera", {
			data: {
				"shootingcommand": "65535",
				"plateno": vehicleInfo.plateNo,
				"simno": vehicleInfo.simNo,
				"vehicleid": vehicleInfo.vid,
				"passagewayid": $("table:eq(1) input.input_xk:eq(0)").val(),
				"phtime": "1",
				"saveflag": $("table:eq(1) select:eq(0) option:selected").val(),
				"resolvingpower": $("table:eq(1) select:eq(1) option:selected").val(),
				"imagequality": $("table:eq(1) input.input_xk:eq(4)").val(),
				"brightness": $("table:eq(1) input.input_xk:eq(1)").val(),
				"contrastratio": $("table:eq(1) input.input_xk:eq(3)").val(),
				"saturation": $("table:eq(1) input.input_xk:eq(2)").val(),
				"chroma": $("table:eq(1) input.input_xk:eq(5)").val()
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				openVideo();
			},
			error: function() {}
		});
	};

	function openVideo() {
		//console.log(vehicleInfo);
		$.ajax(config.url + "realTime/getRealDatasBySimNo", {
			data: {
				"simNo": vehicleInfo.simNo
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(data);
				if(data.success == true) {
					data = data.t;
					html = "";
					html += '<div class="tab_main_xk" style="height:220px;text-align: right;margin-bottom:170px;">';
					html += '<i class="specialleft_xk" style="text-align: left;height:210px;">';
					html += '<div style="padding:10px 0;"><label style="display:inline-block;width:60px;">车辆位置</label><input type="text" style="width:232px;" readonly="readonly" class="input_xk" value="' + data.location + '"/></div>';
					html += '<div style="padding-bottom:10px;"><label style="display:inline-block;width:60px;">速度方向</label><input class="input_xk" style="width:122px;" readonly="readonly" type="text" value="' + data.direction + '"/><label style="display:inline-block;width:50px;margin-left:6px;">驾驶员</label><input class="input_xk" style="width:50px;" readonly="readonly" type="text" value="' + data.driverName + '"/></div>';
					html += '<div style="padding-bottom:10px;"><label style="display:inline-block;width:60px;line-height: 87px;vertical-align: top;">车辆状态</label><select name="" size="5" style="width:236px;">';
					for(one in data.terminalStatusInfo) {
						if(data.terminalStatusInfo[one] != null) {
							html += '<option value="">' + data.terminalStatusInfo[one] + '</option>';
						}
					}
					html += '</select></div>';
					html += '<div style="text-align: center;"><a class="button blue on_xk" style="padding:5px 8px;margin-right:20px;"><img src="img/全屏.png" style="height:14px;vertical-align:middle;margin-top:-2px;" alt="" />全屏显示</a><a class="button orange" style="padding:5px 8px;"><img src="img/排名监控.png" style="height:14px;vertical-align:middle;margin-top:-2px;" alt="" />关闭监控</a></div>';
					html += '</i>';
					html += '<b class="specialright_xk" id="container" style="width:50%;height:100%;margin:0;">';
					html += '</b>';
					html += '</div>';
					$("#mapbottomnottab").html(html).show();
					map_one_wd();
				}
			}
		});
	}
});