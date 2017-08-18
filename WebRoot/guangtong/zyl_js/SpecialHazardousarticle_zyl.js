eObject = "";
drawType = "";
path = [];
figure = "";

function map_wd() {
	$("#mapbottomtab").hide();
	var map = new AMap.Map('container', {
		resizeEnable: true,
		zoom: 11,
	});
	AMap.plugin(['AMap.ToolBar', 'AMap.Scale', 'AMap.OverView'], function() {
		map.addControl(new AMap.ToolBar());
		map.addControl(new AMap.Scale());
	});
	var mouseTool = new AMap.MouseTool(map);
	$(document).on('click', 'a.button.draw', function() {
		map.remove(eObject);
		eObject = "";
		path = [];
		drawType = $(this).text();
		switch(drawType) {
			case "绘制多边形":
				mouseTool.polygon();
				break;
			case "绘制线":
				mouseTool.polyline();
				break;
			case "绘制矩形":
				mouseTool.rectangle();
				break;
			case "绘制圆形":
				mouseTool.circle();
				break;
			default:
				alert("111");
				break;
		}
	});
	$(document).on('click', 'a.button.save', function() {
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
		var manageType = $(".map_top_xk li.on_xk").text();
		//console.log(manageType);
		switch(manageType) {
			case "特别区域管理":
				var mapPort = "";
				var data_json = {
					"name": $(".tab_main_xk .specialleft_xk .select:eq(1)").val(),
					"alarmType": $(".tab_main_xk .specialleft_xk input[name='alarm']").val(),
					"onoff": $(".tab_main_xk .specialleft_xk input[name='onoff']").val(),
					"startTime": $(".tab_main_xk .specialleft_xk input[placeholder='开始时间']").val(),
					"endTime": $(".tab_main_xk .specialleft_xk input[placeholder='结束时间']").val(),
					"speed": $(".tab_main_xk .specialleft_xk input[name='speed']").val(),
				}
				break;
			default:
				break;
		}
	});
	AMap.event.addListener(mouseTool, "draw", function callback(e) {
		eObject = e.obj; //obj属性就是鼠标事件完成所绘制的覆盖物对象。
		mouseTool.close();
		if(drawType == "绘制圆形") {
			path.center = e.obj.getCenter();
			path.radius = e.obj.getRadius();
		} else {
			path = e.obj.getPath();
		}

	});

	function findVehicleByAdmin() {
		$.ajax(config.url + "realTime/findVehicleByAdmin", {
			data: {},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				getVehicles(data)
			}
		});
	}

	function getVehicles(content) {
		markers = [];
		for(var i = 0; i < content.length; i += 1) {
			var vehicles = content[i].vehicles
			for(var j = 0; j < vehicles.length; j += 1) {
				var marker = createMarker(vehicles[j]);
				markers.push(marker);
				AMap.event.addListener(marker, 'click', _onClick); //点击事件
			}
		}
		map.setFitView();
	}

	function createMarker(data) {
		var lnglat = [];
		lnglat[0] = data.longitude;
		lnglat[1] = data.latitude;
		var marker = new AMap.Marker({
			draggable: false,
			cursor: "move",
			position: lnglat,
			label: { content: '<span style="font-size:10px">' + data.num + '</span>', offset: new AMap.Pixel(-20, 25) }
		});
		var markerContent = document.createElement("div");
		var markerImg = document.createElement("img");
		markerImg.src = "img/car.png";
		markerImg.style.transform = "rotate(" + data.direction + "deg)"; //设置图片的方向
		markerImg.style.width = "24px";
		markerImg.style.height = "24px";
		markerContent.appendChild(markerImg);
		marker.setContent(markerContent);
		marker.simno = data.simNo;
		marker.vid = data.id;
		marker.setMap(map);
		return marker;
	}
	_onClick = function(e) {
		var simno = e.target.simno;
		var vid = e.target.vid;
		getRealDatasBySimNo(simno, false);
	}

	function getRealDatasBySimNo(simno, center) {
		//根据simNo(卡号)精准查询车辆信息
		$.ajax(config.url + "realTime/getRealDatasBySimNo", {
			data: {
				"simNo": simno
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				vehicleInfo = data;
				$("#mapbottomtab").show();
				$("#mapbottomtab .tab_xk li:eq(0)").click();
				if(center == true) {
					var lnglat = [];
					lnglat[0] = vehicleInfo.longitude;
					lnglat[1] = vehicleInfo.latitude;
					map.setCenter(lnglat);
				}
			},
			error: function() {
				map.setFitView();
			}
		});
	}

	function getCarList() {
		$.ajax(config.url + "vehicle/getVehicleByAdmin", {
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				html = "";
				for(one in data) {
					html += '<dl>';
					html += '<dt><i><img src="img/u380.png"/></i><i><img src="img/团队.png" /></i>' + data[one].cName + '</dt>';
					html += '<dd>';
					for(two in data[one].vehicles) {
						html += '<dl>';
						html += '<dt simno="' + data[one].vehicles[two].simNo + '"><i><img src="img/线路车辆 (1).png"/></i>' + data[one].vehicles[two].num + '</dt>';
						html += '</dl>';
					}
					html += '</dd>';
					html += '</dl>';
				}
				$("dd#carLists").html(html);
				carListClick();
			}
		});
	}

	function carListClick() {
		$("dd#carLists dt").click(function() {
			var simNo = $(this).attr("simno")
			if(simNo != undefined) {
				getRealDatasBySimNo(simNo, true);
			}
		})
	}

	function changeCard() {
		$(".mapbtn_xk li").click(function() {
			mouseTool.close();
			if(eObject) {
				map.remove(eObject);
			}
			if(figure) {
				map.remove(figure);
			}
		})
	}

	$(".map_xk").on("change", ".changeUI", function() {
		var text_zyl = $(this).children("option:selected").text();
		if(text_zyl == "管理已有区域") {
			getEnclosure();
		}
	});
	//危险品零售供应链子系统-重点区域管理
	function getEnclosure() {
		$.ajax(config.url + "realTime/getEnclosure", {
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				var html = "";
				for(var z = 0; z < data.length; z++) {
					html += '<option zyl_xx="' + z + '"  zyl_id="' + data[z].enclosureId + '">' + data[z].name + '</option>';
				}
				$(".add_area_zyl").html(html);

				$(".add_area_zyl").change(function() {
					var this_xx = data[$(this).children("option:selected").attr("zyl_xx")];
					drawPath(this_xx.enclosureType, this_xx.centerLng, this_xx.centerLat, this_xx.radius, this_xx.points, this_xx.linesegments);
					$(".set_zyl label").each(function() {
						if($(this).text() == this_xx.alarmType) {
							$(this).prev("input:radio").prop("checked", true);
						} else { $(this).prev("input:radio").prop("checked", false) };
					});

					$(".set_time_zyl input:text:eq(0)").val(this_xx.startDate);
					$(".set_time_zyl input:text:eq(1)").val(this_xx.endDate);
					switch(this_xx.status) {
						case "0":
							$(".set_time_zyl input:radio:eq(0)").prop("checked", false);
							$(".set_time_zyl input:radio:eq(1)").prop("checked", true);
							break;
						case "1":
							$(".set_time_zyl input:radio:eq(0)").prop("checked", true);
							$(".set_time_zyl input:radio:eq(1)").prop("checked", false);

							break;
						default:
							console.log("-----");
							break;
					};

					$(".set_speed_zyl input:text:eq(0)").val(this_xx.maxSpeed);
					$(".set_speed_zyl input:text:eq(1)").val(this_xx.delay);
					switch(this_xx.limitSpeed) {
						case 0:
							$(".set_speed_zyl input:radio:eq(0)").prop("checked", false);
							$(".set_speed_zyl input:radio:eq(1)").prop("checked", true);
							break;
						case 1:
							$(".set_speed_zyl input:radio:eq(0)").prop("checked", true);
							$(".set_speed_zyl input:radio:eq(1)").prop("checked", false);
							break;
					}

				});
			}
		});
	};
	//根据图形类别画图
	function drawPath(type, centerlng, centerlat, radius, path, linesegments) {
		map.remove(figure);
		switch(type) {
			case "route":
				var newPath = [];
				var spath = [];
				for(i in linesegments) {
					spath.push(linesegments[i].latitude1);
					spath.push(linesegments[i].longitude1);
					newPath.push(spath);
					spath = [];
				}
				figure = new AMap.Polyline({
					path: newPath, //设置线覆盖物路径
					strokeColor: "#3366FF", //线颜色
					strokeOpacity: 1, //线透明度
					strokeWeight: 3, //线宽
					strokeStyle: "solid", //线样式
					strokeDasharray: [10, 5] //补充线样式
				});
				break;
			case "circle":
				var center_zyl = [];
				center_zyl.push(centerlng);
				center_zyl.push(centerlat);
				figure = new AMap.Circle({
					center: center_zyl, // 圆心位置
					radius: radius, //半径
					strokeColor: "#3366FF", //线颜色
					strokeOpacity: 1, //线透明度
					strokeWeight: 3, //线粗细度
					fillColor: "#1791fc", //填充颜色
					fillOpacity: 0.35 //填充透明度
				});
				break;
			default:
				var pathArr_zyl = path.split(";");
				var newPathArr = [];
				for(one in pathArr_zyl) {
					newPathArr.push(pathArr_zyl[one].split(","));
				}
				figure = new AMap.Polygon({
					path: newPathArr, //设置多边形边界路径
					strokeColor: "#3366FF", //线颜色
					strokeOpacity: 0.2, //线透明度
					strokeWeight: 3, //线宽
					fillColor: "#1791fc", //填充色
					fillOpacity: 0.35 //填充透明度
				});
				break;
		}
		figure.setMap(map);
		map.setFitView();
	}

	getCarList();
	findVehicleByAdmin();
	changeCard();
}
map_wd();