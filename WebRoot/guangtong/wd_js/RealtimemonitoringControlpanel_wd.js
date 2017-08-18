eObject = "";
drawType = "";
path = [];
figure = "";
cId = [];
vId = [];
simnoArr = [];
numArr = [];
ServerStatus();
mapName_wd = "控制面板";
markers = [];
alarmPointMarkers = [];
stopPointMarkers = [];
vehiclePath = [];
vehiclePolyline = "";
startMarker = "";
endMarker = "";
drivingRoundObj = "";
dragPath = [];
dragRouteObj = "";
var oData= new Date();
var year = oData .getFullYear(); //获取完整的年份(4位,1970-????)
var month = oData.getMonth()+1; //获取当前月份(0-11,0代表1月)
var data = oData.getDate(); //获取当前日(1-31)
var day = oData.getDay(); //获取当前星期X(0-6,0代表星期天)
var h = oData.getHours(); //获取当前小时数(0-23)
var m = oData.getMinutes(); //获取当前分钟数(0-59)
var s = oData.getSeconds(); //获取当前秒数(0-59)
var data_zyl = year+"-" +month+"-" + data+ " "+ h+":"+ m +":" + s;
function map_wd() {
	$("#mapbottomtab").hide();
	var map = new AMap.Map('container', {
		resizeEnable: true,
		zoom: 12
	});
	// 标尺
	if(paramSet.ruler == 1) {
		AMap.plugin(['AMap.ToolBar'], function() {
			map.addControl(new AMap.ToolBar());
		});
	}
	// 比例尺
	if(paramSet.scale == 1) {
		AMap.plugin(['AMap.Scale'], function() {
			map.addControl(new AMap.Scale());
		});
	}
	// 标尺比例
	if(paramSet.rulerproportions != 0) {
		map.setZoom(paramSet.rulerproportions);
	}
	// 地图标注层
	if(paramSet.mapcalloutlayer == 1) {
		map.plugin(['AMap.MapType'], function() {
			var mapType = new AMap.MapType();
			map.addControl(mapType);
		});
	}
	var mouseTool = new AMap.MouseTool(map);
	// 点击画图
	$(".map_xk").on('click', 'a.button.draw', function() {
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
			case "绘制导航路线":
				if($("#startPonit").val() != null && $("#endPonit").val() != null) {
					dragRoute();
				} else {
					alert("请填写起始点位置！");
				}
				break;
			default:
				alert("111");
				break;
		}
	});
	function dragRoute(){
		if(dragRouteObj){
			dragRouteObj.destroy();
			dragRouteObj = "";
		}
		dragRouteObj = new AMap.DragRoute(map, dragPath, AMap.DrivingPolicy.LEAST_DISTANCE); //构造拖拽导航类
		dragRouteObj.search(); //查询导航路径并开启拖拽导航
	}
//	  var driving = new AMap.Driving({
//	        map: map,
//	    }); 
//	function drivingRound() {
//		driving.clear();
//		// 根据起终点坐标规划驾车路线
//		driving.search([{
//			keyword: $("#startPonit").val()
//		}, {
//			keyword: $("#endPonit").val()
//		}], function(status, result) {
//			console.log(result);
//			console.log(result.routes[0].steps);
//			//                console.log(result.)
//		});
//	}
	// 输入关键词位置定位
	var auto = new AMap.Autocomplete({
		input: "positionsearch"
	});
	var startPoint = new AMap.Autocomplete({
		input: "startPonit"
	});
	var endPoint = new AMap.Autocomplete({
		input: "endPonit"
	});
	AMap.event.addListener(auto, 'select', function(e) {
		map.setCenter(e.poi.location)
	});
	AMap.event.addListener(startPoint, 'select', function(e) {
		dragPath[0] = e.poi.location;
	});
	AMap.event.addListener(endPoint, 'select', function(e) {
		dragPath[1] = e.poi.location;
	});
	// 点击保存按钮
	$(".map_xk")
		.on(
			'click',
			'a.button.save',
			function() {
				// 判空
				var SubSpaceResult = SubSpace();
				if(SubSpaceResult == false) {
					return;
				};
				// 获取车辆
				cId = [];
				vId = [];
				simnoArr = [];
				numArr = [];
				for(var i = 0; i < $("#carList img").length; i++) {
					if($("#carList img:eq(" + i + ")").attr("src") == "img/cherked.jpg") {
						if($("#carList img:eq(" + i + ")").parent("b")
							.attr("vId") != undefined &&
							$("#carList img:eq(" + i + ")")
							.parent("b").attr("vId") != null) {
							vId.push($("#carList img:eq(" + i + ")")
								.parent("b").attr("vid"));
							simnoArr.push($(
									"#carList img:eq(" + i + ")")
								.parent("b").attr("simno"));
							numArr.push($("#carList img:eq(" + i + ")")
								.parent("b").attr("num"));
						} else {
							cId.push($("#carList img:eq(" + i + ")")
								.parent("b").attr("cId"));
						}
					}
				}
				// 判断保存还是编辑
				var saveType = $(
						".tab_main_xk .specialleft_xk:eq(0) option:selected")
					.text();
				if(saveType.indexOf("创建") >= 0) {
					addPath();
				} else {
					editPath();
				}
			});
	// 判空
	var SubSpace = function() {
		var result = false;
		switch($(".map_top_xk li.on_xk").text()) {
			case "特殊区域管理":
			case "线路关键点管理":
				if(path.length == 0 &&
					$(".tab_main_xk .specialleft_xk:eq(0) option:selected")
					.text().indexOf("创建") >= 0) {
					alert("请画区域！");
				} else if($(".tab_main_xk .specialleft_xk:eq(0) input.search")
					.val() == "") {
					alert("请填写" +
						$(".tab_main_xk .specialleft_xk:eq(0) input.search")
						.prev("label").text() + "！");
					$(".tab_main_xk .specialleft_xk:eq(0) input.search").focus();
				} else if($(".set_time_zyl input[name='onoff']:checked").val() == 1 &&
					($(".time_plugin_lv_onclick.startTime").val() == "" || $(
						".time_plugin_lv_onclick.endTime").val() == "")) {
					alert("请填写时间段！");
				} else if($(".set_speed_zyl input[name='speed']:checked").val() == 1 &&
					$(".set_speed_zyl input[name='max_speed']").val() == "") {
					alert("请填写最高速度！");
				} else {
					result = true;
				}
				break;
			case "路线管理":
				if($("#startPonit").val() == "" && $(".tab_main_xk .specialleft_xk:eq(0) option:selected")
						.text().indexOf("创建") >= 0){
					alert("请填写起点！");
				}else if($("#endPonit").val() == "" && $(".tab_main_xk .specialleft_xk:eq(0) option:selected")
						.text().indexOf("创建") >= 0){
					alert("请填写起点！");
				}else if(!dragRouteObj && $(".tab_main_xk .specialleft_xk:eq(0) option:selected")
						.text().indexOf("创建") >= 0){
					alert("请点击绘制！");
				}else if($(".set_time_zyl input[name='onoff']:checked").val() == 1 &&
					($(".time_plugin_lv_onclick.startTime").val() == "" || $(
						".time_plugin_lv_onclick.endTime").val() == "")) {
					alert("请填写时间段！");
				} else if($(".set_speed_zyl input[name='speed']:checked").val() == 1 &&
					$(".set_speed_zyl input[name='max_speed']").val() == "") {
					alert("请填写最高速度！");
				}else{
					result = true;
				}
				break;
			default:
				if($(".tab_main_xk .specialleft_xk:eq(0) input.search").val() == "") {
					alert("请填写" +
						$(".tab_main_xk .specialleft_xk:eq(0) input.search")
						.prev("label").text() + "！");
					$(".tab_main_xk .specialleft_xk:eq(0) input.search").focus();
				} else if($(".set_zyl input:eq(0)").val() == "") {
					alert("请填写超速阈值！");
					$(".set_zyl input:eq(0)").focus();
				} else if($(".time_plugin_lv_onclick.startTime").val() == "" ||
					$(".time_plugin_lv_onclick.endTime").val() == "") {
					alert("请填写时间段！");
				} else {
					result = true;
				}
				break;
		}
		return result;
	};
	$(".map_xk").on('click', 'a.button.cancel', function() {
		if(eObject) {
			map.remove(eObject);
		}
		if(figure) {
			map.remove(figure);
		}
		$(".map_top_xk li.on_xk").click();
	});
	// 新增路线
	function addPath() {
		var manageType = $(".map_top_xk li.on_xk").text();
		if(manageType == "路线管理"){
			//console.log(dragRouteObj.getRoute());
			path = dragRouteObj.getRoute();
		}
		var point = [];
		if(path.center) {
			var centerlat = path.center.lat;
			var centerlng = path.center.lng;
			var radius = path.radius;
			point.push(path.center.lng + ',' + path.center.lat);
			point = point.join(";")
		} else {
			var mapCenter = map.getCenter();
			var centerlat = mapCenter['lat'];
			var centerlng = mapCenter['lng'];
			var radius = "0";
			if(manageType == "路线管理" || manageType == "线路关键点管理") {
				for(one in path) {
					if(!isNaN(one)){
						point.push(path[one].lng + ';' + path[one].lat);
					}
				}
			} else {
				for(one in path) {
					point.push(path[one].lng + ',' + path[one].lat);
				}
				point = point.join(";");
			}
		}
		switch(manageType) {
			case "特殊区域管理":
				var mapport = "realTime/insertEnclosure",
					data_json = {
						"name": $(".tab_main_xk .specialleft_xk:eq(0) input.search")
							.val(),
						"byTime": $(".set_time_zyl input[name=onoff]:checked").val(),
						"delay": $(".set_speed_zyl input[name='continued_time']")
							.val(),
						"enclosureType": $(
								".tab_main_xk .specialleft_xk:eq(0) select:eq(1)")
							.val(),
						"startDate": $("input.time_plugin_lv_onclick.startTime").val(),
						"endDate": $("input.time_plugin_lv_onclick.endTime").val(),
						"limitSpeed": $(".set_speed_zyl input[name='speed']:checked")
							.val(),
						"maxSpeed": $(".set_speed_zyl input[name='max_speed']").val(),
						"alarmType": $(
								".tab_main_xk .specialleft_xk:eq(1) input[name='alarm']:checked")
							.val(),
						"points": point,
						"radius": radius,
						"centerLat": centerlat,
						"centerLng": centerlng,
						"plateNoS": numArr,
						"vehicleIdS": vId,
						"simNoS": simnoArr,
						"time":data_zyl,
					};
				break;
			case "路线管理":
				var mapport = "linesegment/insert",
					data_json = {
						"name": $(".tab_main_xk .specialleft_xk:eq(0) input.search")
							.val(),
						"byTime": $(".set_time_zyl input[name=onoff]:checked").val(),
						"delay": $(".set_speed_zyl input[name='continued_time']")
							.val(),
						"enclosureType": $(
								".tab_main_xk .specialleft_xk:eq(0) select:eq(1)")
							.val(),
						"startDate": $("input.time_plugin_lv_onclick.startTime").val(),
						"endDate": $("input.time_plugin_lv_onclick.endTime").val(),
						"limitSpeed": $(".set_speed_zyl input[name='speed']:checked")
							.val(),
						"maxSpeed": $(".set_speed_zyl input[name='max_speed']").val(),
						"alarmType": $(
								".tab_main_xk .specialleft_xk:eq(1) input[name='alarm']:checked")
							.val(),
						"radius": radius,
						"centerLat": centerlat,
						"centerLng": centerlng,
						"plateNoS": numArr,
						"vehicleIdS": vId,
						"simNoS": simnoArr,
						"vehArr": point,
					};
				break;
			case "线路关键点管理":
				var mapport = "linesegment/insertkeyPoint";
				data_json = {
					"name": $(".tab_main_xk .specialleft_xk:eq(0) input.search")
						.val(),
					"byTime": $(".set_time_zyl input[name=onoff]:checked").val(),
					"delay": $(".set_speed_zyl input[name='continued_time']")
						.val(),
					"enclosureType": $(
							".tab_main_xk .specialleft_xk:eq(0) select:eq(1)")
						.val(),
					"startDate": $("input.time_plugin_lv_onclick.startTime").val(),
					"endDate": $("input.time_plugin_lv_onclick.endTime").val(),
					"limitSpeed": $(".set_speed_zyl input[name='speed']:checked")
						.val(),
					"maxSpeed": $(".set_speed_zyl input[name='max_speed']").val(),
					"alarmType": $(
							".tab_main_xk .specialleft_xk:eq(1) input[name='alarm']:checked")
						.val(),
					"radius": radius,
					"centerLat": centerlat,
					"centerLng": centerlng,
					"plateNoS": numArr,
					"vehicleIdS": vId,
					"simNoS": simnoArr,
					"vehArr": point,
				};
				break;
			case "全路段限速管理":
				var mapport = "road/insertWholeSection";
				data_json = {
					"roadName": $(
							".tab_main_xk .specialleft_xk:eq(0) input.search")
						.val(),
					"speedLimit": $(
						".tab_main_xk .specialleft_xk:eq(1) input:eq(0)").val(),
					"startTime": $("input.time_plugin_lv_onclick.startTime").val(),
					"endTime": $("input.time_plugin_lv_onclick.endTime").val(),
					"simNo": simnoArr
				};
				break;
			case "道路限速":
				var mapport = "road/insertRoadGrade";
				data_json = {
					"roadName": $(
							".tab_main_xk .specialleft_xk:eq(0) input.search")
						.val(),
					"speedLimit": $(
						".tab_main_xk .specialleft_xk:eq(1) input:eq(0)").val(),
					"startTime": $("input.time_plugin_lv_onclick.startTime").val(),
					"endTime": $("input.time_plugin_lv_onclick.endTime").val(),
					"simNo": simnoArr,
					"roadGrade": $(
							".tab_main_xk .specialleft_xk:eq(0) select[name=roadLevel] option:selected")
						.val(),
				};
				break;
			default:
				break;
		}
		// 保存路线
		$.ajax(config.url + mapport, {
			data: data_json,
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				path = [];
				if(data.success == true) {
					alert("添加成功!");
					$(".map_top_xk li.on_xk").click();
				} else {
					alert("添加失败！请稍后再试！")
				}
				if(dragRouteObj){
					dragRouteObj.destroy();
					dragRouteObj = "";
				}
				$("#startPonit").val("");
				$("#endPonit").val("");
			}
		});
	};
	// 编辑路线
	function editPath() {
		var manageType = $(".map_top_xk li.on_xk").text();
		switch(manageType) {
			case "特殊区域管理":
				var mapport = "realTime/updateEnclosure",
					data_json = {
						"enclosureId": $(".add_area_zyl option:selected").attr(
							"zyl_id"),
						"byTime": $(
								".tab_main_xk .specialleft_xk:eq(1) input[name='onoff']:checked")
							.val(),
						"delay": $(
								".tab_main_xk .specialleft_xk:eq(1) input[name='continued_time']")
							.val(),
						"limitSpeed": $(
								".tab_main_xk .specialleft_xk:eq(1) input[name='speed']:checked")
							.val(),
						"enclosureType": $(".add_area_zyl option:selected").attr(
							"zyl_type"),
						"maxSpeed": $(
								".tab_main_xk .specialleft_xk:eq(1) input[name='max_speed']")
							.val(),
						"offsetdelay": "10",
						"alarmType": $(
								".tab_main_xk .specialleft_xk:eq(1) input[name='alarm']:checked")
							.val(),
						"startDate": $("input.time_plugin_lv_onclick.startTime").val(),
						"endDate": $("input.time_plugin_lv_onclick.endTime").val(),
						"simNoS": simnoArr,
						"plateNoS": numArr,
						"vehicleIdS": vId,
					};
				break;
			case "路线管理":
				var mapport = "realTime/updateEnclosure",
					data_json = {
						"enclosureId": $(".add_area_zyl option:selected").attr(
							"zyl_id"),
						"byTime": $(
								".tab_main_xk .specialleft_xk:eq(1) input[name='onoff']:checked")
							.val(),
						"delay": $(
								".tab_main_xk .specialleft_xk:eq(1) input[name='continued_time']")
							.val(),
						"limitSpeed": $(
								".tab_main_xk .specialleft_xk:eq(1) input[name='speed']:checked")
							.val(),
						"enclosureType": $(".add_area_zyl option:selected").attr(
							"zyl_type"),
						"maxSpeed": $(
								".tab_main_xk .specialleft_xk:eq(1) input[name='max_speed']")
							.val(),
						"offsetdelay": "10",
						"alarmType": $(
								".tab_main_xk .specialleft_xk:eq(1) input[name='alarm']:checked")
							.val(),
						"startDate": $("input.time_plugin_lv_onclick.startTime").val(),
						"endDate": $("input.time_plugin_lv_onclick.endTime").val(),
						"isTime":$(".tab_main_xk .specialleft_xk:eq(1) input[name='isTime']:checked").val(),
						"maxtimelimit":$(".maxtimelimit_z").val(),
						"mintimelimit":$(".mintimelimit_z").val(),
						"simNoS": simnoArr,
						"plateNoS": numArr,
						"vehicleIdS": vId,
					};
				break;
			case "线路关键点管理":
				var mapport = "realTime/updateEnclosure";
				data_json = {
					"enclosureId": $(".add_area_zyl option:selected").attr(
						"zyl_id"),
					"byTime": $(
							".tab_main_xk .specialleft_xk:eq(1) input[name='onoff']:checked")
						.val(),
					"delay": $(
							".tab_main_xk .specialleft_xk:eq(1) input[name='continued_time']")
						.val(),
					"limitSpeed": $(
							".tab_main_xk .specialleft_xk:eq(1) input[name='speed']:checked")
						.val(),
					"enclosureType": $(".add_area_zyl option:selected").attr(
						"zyl_type"),
					"maxSpeed": $(
							".tab_main_xk .specialleft_xk:eq(1) input[name='max_speed']")
						.val(),
					"offsetdelay": "10",
					"alarmType": $(
							".tab_main_xk .specialleft_xk:eq(1) input[name='alarm']:checked")
						.val(),
					"startDate": $("input.time_plugin_lv_onclick.startTime").val(),
					"endDate": $("input.time_plugin_lv_onclick.endTime").val(),
					"isTime":$(".tab_main_xk .specialleft_xk:eq(1) input[name='isTime']:checked").val(),
					"maxtimelimit":$(".maxtimelimit_z").val(),
					"mintimelimit":$(".mintimelimit_z").val(),
					"simNoS": simnoArr,
					"plateNoS": numArr,
					"vehicleIdS": vId,
				};
				break;
			case "全路段限速管理":
				var mapport = "road/updateWholeSection";
				data_json = {
					"id": $(".add_area_zyl option:selected").attr("zyl_id"),
					"speedLimit": $(".set_zyl:eq(0) input").val(),
					"startTime": $("input.time_plugin_lv_onclick.startTime").val(),
					"endTime": $("input.time_plugin_lv_onclick.endTime").val(),
					"simNo": simnoArr,
				};
				break;
			case "道路限速":
				var mapport = "road/updateRoadGrade";
				data_json = {
					"id": $(".add_area_zyl option:selected").attr("zyl_id"),
					"speedLimit": $(".set_zyl:eq(0) input").val(),
					"startTime": $("input.time_plugin_lv_onclick.startTime").val(),
					"endTime": $("input.time_plugin_lv_onclick.endTime").val(),
					"simNo": simnoArr,
				};
				break;
			default:
				break;
		}
		// 保存路线
		$.ajax(config.url + mapport, {
			data: data_json,
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success) {
					alert("更新成功");
					$(".map_top_xk li.on_xk").click();
				} else {
					alert("更新失败！请稍后再试！");
				}
			}
		});
	}
	// 监听画图结束
	AMap.event.addListener(mouseTool, "draw", function callback(e) {
		eObject = e.obj; // obj属性就是鼠标事件完成所绘制的覆盖物对象。
		mouseTool.close();
		if(drawType == "绘制圆形") {
			path.center = e.obj.getCenter();
			path.radius = e.obj.getRadius();
			path.path = e.obj.getCenter();
		} else {
			path = e.obj.getPath();
		}

	});

	function findVehicleByAdmin() {
		$.ajax(config.url + "realTime/findVehicleByAdmin", {
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
//					if(markers.length != undefined && markers.length >= 0) {
						map.remove(markers);
//						markers = [];
//					}
					data.obj == null ? data = data.t : data = data.obj;
					getVehicles(data);
				}
			}
		});
	};

	function updateVehicle() {
		realtimetimeout = setTimeout(function() {
			if(mapName_wd == "控制面板" && $(".map_xk").length != 0 &&
				$(".map_top_xk").css('display') != "none") {
				findVehicleByAdmin();
				updateVehicle();
			} else {
				return;
			}
		}, 5000);
	};

	function getVehicles(content) {
//		markers = [];
		for(one in content) {
			for(two in content[one]) {
				for(three in content[one][two]) {
					if(content[one][two][three].isRead == 1) {
						if(vehicleStatus == content[one][two][three].state || vehicleStatus == "0"){
							var marker = createMarker(content[one][two][three]);
							markers.push(marker);
							AMap.event.addListener(marker, 'click', _onClick); // 点击事件
						}
					}
				}
			}
		}
		// for (var i = 0; i < content.length; i += 1) {
		// var vehicles = content[i].vehicles
		// for (var j = 0; j < vehicles.length; j += 1) {
		// var marker = createMarker(vehicles[j]);
		// markers.push(marker);
		// AMap.event.addListener(marker, 'click', _onClick); // 点击事件
		// }
		// }
		// map.setFitView();
	}

	function createMarker(data) {
//		console.log(data);
		var lnglat = [];
		lnglat[0] = data.longitude;
		lnglat[1] = data.latitude;
		var marker = new AMap.Marker({
			draggable: false,
			cursor: "move",
			position: lnglat,
			label: {
				content: '<span style="font-size:10px">' + data.num +
					'</span>',
				offset: new AMap.Pixel(-15, 30)
			}
		});
		var markerContent = document.createElement("div");
		markerContent.style.width = "36px";
		markerContent.style.height = "36px";
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
		switch(data.sName) {
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
		markerImg.style.transform = "rotate(" + data.direction + "deg)"; // 设置图片的方向
		markerImg.style.width = "36px";
		markerImg.style.height = "36px";
		markerContent.appendChild(markerImg);
		marker.setContent(markerContent);
		marker.num = data.num;
		marker.simno = data.simNo;
		marker.vid = data.id;
		marker.setMap(map);
		return marker;
	};
	_onClick = function(e) {
		map.remove(alarmPointMarkers);
		map.remove(stopPointMarkers);
		map.remove(startMarker);
		map.remove(endMarker);
		map.remove(vehiclePolyline);
		if(paramSet.generalinformation == 1) {
			var simno = e.target.simno;
			var vid = e.target.vid;
			var num = e.target.num;
			if(vid == null){
				getRealDatasBySimNo(vid, num, false);
			}else{
				getRealDatasBySimNo(vid, simno, false);
			}
		}
	}

	function getRealDatasBySimNo(vid, simno, center) {
		// 根据simNo(卡号)精准查询车辆信息
		$.ajax(config.url + "realTime/getRealDatasBySimNo", {
			data: {
				"simNo": simno
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					data.obj == null ? data = data.t : data = data.obj;
					vehicleInfo = data;
					vehicleInfo_wd();
					if(center == true) {
						var lnglat = [];
						lnglat[0] = vehicleInfo.longitude;
						lnglat[1] = vehicleInfo.latitude;
						map.setCenter(lnglat);
					}else{
						$("#mapbottomtab").show();
						$("#mapbottomtab .tab_xk li:eq(0)").click();
					}
				}
			},
			error: function() {
				map.setFitView();
			}
		});
	}

	function getCarList() {
		$.ajax(
				config.url + "realTime/findVehicleByAdmin", {
					dataType: "json",
					type: "POST",
					timeout: 10000,
					success: function(data) {
						if(data.success == true) {
							data.obj == null ? data = data.t :
								data = data.obj;
							html = "";
							// for (one in data) {
							// html += '<dl>';
							// html += '<dt><i><img src="img/u380.png"/></i><b cId="' + data[one].cId +
							// '"><img src="img/uncherked.jpg"/></b><i><img src="img/td.png" /></i>'
							// + data[one].cName + '</dt>';
							// html += '<dd>';
							// for (two in data[one].vehicles) {
							// html += '<dl>';
							// html += '<dt simno="'
							// + data[one].vehicles[two].simNo
							// + '" vid="'
							// + data[one].vehicles[two].id
							// + '"><i><img src="img/xlcl (1).png"/></i><b><img
							// src="img/uncherked.jpg"/></b>'
							// + data[one].vehicles[two].num
							// + '</dt>';
							// html += '</dl>';
							// }
							// html += '</dd>';
							// html += '</dl>';
							// }
							for(one in data) {
								html += "<dl>";
								html += '<dt ><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg" carListClick /></b>' + one + '</dt>';
								html += "<dd>";
								for(two in data[one]) {
									if(two.indexOf("TEAM")!=-1){
										for(three in data[one][two]) {
											html += "<dl>";
											if(data[one][two][three].isRead == 1) {
												html += '<dt simno="' +
													data[one][two][three].simNo +
													'" vid="' +
													data[one][two][three].id +
													'"><i><img src="img/xlcl (1).png"/></i><b><img src="img/cherked.jpg" defaultChecked carListClick /></b>' +
													data[one][two][three].num +
													'</dt>';
											} else {
												html += '<dt simno="' +
													data[one][two][three].simNo +
													'" vid="' +
													data[one][two][three].id +
													'"><i><img src="img/xlcl (1).png"/></i><b><img src="img/uncherked.jpg" defaultChecked carListClick/></b>' +
													data[one][two][three].num +
													'</dt>';
											}
											html += "</dl>";
										}
									}else{
										html += "<dl>";
										html += '<dt><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg" carListClick /></b>' + two + '</dt>';
										html += "<dd>";
										for(three in data[one][two]) {
											html += "<dl>";
											if(data[one][two][three].isRead == 1) {
												html += '<dt simno="' +
													data[one][two][three].simNo +
													'" vid="' +
													data[one][two][three].id +
													'"><i><img src="img/xlcl (1).png"/></i><b><img src="img/cherked.jpg" defaultChecked carListClick /></b>' +
													data[one][two][three].num +
													'</dt>';
											} else {
												html += '<dt simno="' +
													data[one][two][three].simNo +
													'" vid="' +
													data[one][two][three].id +
													'"><i><img src="img/xlcl (1).png"/></i><b><img src="img/uncherked.jpg" defaultChecked carListClick/></b>' +
													data[one][two][three].num +
													'</dt>';
											}
											html += "</dl>";
										}
										html += "</dd>";
										html += "</dl>";
									}
								}
								html += "</dd>";
								html += "</dl>";
							}
							$("dd#carLists").html(html);
						}
					}
				});
	}

	carListClick = function() {
		vId = [];
		numArr = [];
		for(var i = 0; i < $("#carLists img").length; i++) {
			if($("#carLists img:eq(" + i + ")").attr("src") == "img/cherked.jpg") {
				if($("#carLists img:eq(" + i + ")").parent("b").parent("dt").attr("vId") != undefined && $("#carLists img:eq(" + i + ")").parent("b").parent("dt").attr("vId") != null) {
					vId.push($("#carLists img:eq(" + i + ")").parent("b").parent("dt").attr("vId"));
					numArr.push($("#carLists img:eq(" + i + ")").parent("b").parent("dt").text());
				}
			}
		}
		$.ajax(config.url + "vehiclemenu/isread", {
			data: {
				"vehicleArr": numArr
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				findVehicleByAdmin();
			},
			error: function() {
				map.setFitView();
			}
		});
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
		switch(text_zyl) {
			case "管理已有区域":
				getEnclosure();
				break;
			case "管理已有路线":
				if(dragRouteObj){
					dragRouteObj.destroy();
					dragRouteObj = "";
				}
				getLinesegmentAll();
				break;
			case "管理已有线点":
				getLinesegmentkeyPointAll();
				break;
			case "管理限定":
				getALLSection();
				break;
			case "管理等级":
				getAllRoute();
				break;
			default:
				break;
		}
	});
	// 特殊区域管理
	function getEnclosure() {
		$
			.ajax(
				config.url + "realTime/getEnclosure", {
					dataType: "json",
					type: "POST",
					timeout: 10000,
					success: function(data) {
						if(data.success) {
							data.obj == null ? data = data.t :
								data = data.obj;
							var html = "";
							for(var z = 0; z < data.length; z++) {
								html += '<option zyl_xx="' + z +
									'"  zyl_id="' +
									data[z].enclosureId +
									'" zyl_type="' +
									data[z].enclosureType + '">' +
									data

								[z].name + '</option>';
							}
							$(".add_area_zyl").html(html);

							$(".add_area_zyl")
								.change(
									function() {
										var this_xx = data[$(
												this)
											.children(
												"option:selected")
											.attr("zyl_xx")];
										drawPath(
											this_xx.enclosureType,
											this_xx.centerLng,
											this_xx.centerLat,
											this_xx.radius,
											this_xx.points,
											this_xx.linesegments);
										$(".set_zyl label")
											.each(
												function() {
													if($(
															this)
														.text() == this_xx.alarmType) {
														$(
																this)
															.prev(
																"input:radio")
															.prop(
																"checked",
																true);
													} else {
														$(
																this)
															.prev(
																"input:radio")
															.prop(
																"checked",
																false)
													};
												});

										$(
												".set_time_zyl input:text:eq(0)")
											.val(
												this_xx.startDate);
										$(
												".set_time_zyl input:text:eq(1)")
											.val(
												this_xx.endDate);
										switch(this_xx.byTime) {
											case 0:
												$(
														".set_time_zyl input:radio:eq(0)")
													.prop(
														"checked",
														false);
												$(
														".set_time_zyl input:radio:eq(1)")
													.prop(
														"checked",
														true);
												break;
											case 1:
												$(
														".set_time_zyl input:radio:eq(0)")
													.prop(
														"checked",
														true);
												$(
														".set_time_zyl input:radio:eq(1)")
													.prop(
														"checked",
														false);

												break;
											default:
												console
													.log("-----");
												break;
										};

										$(
												".set_speed_zyl input:text:eq(0)")
											.val(
												this_xx.maxSpeed);
										$(
												".set_speed_zyl input:text:eq(1)")
											.val(
												this_xx.delay);
										switch(this_xx.limitSpeed) {
											case 0:
												$(
														".set_speed_zyl input:radio:eq(0)")
													.prop(
														"checked",
														false);
												$(
														".set_speed_zyl input:radio:eq(1)")
													.prop(
														"checked",
														true);
												break;
											case 1:
												$(
														".set_speed_zyl input:radio:eq(0)")
													.prop(
														"checked",
														true);
												$(
														".set_speed_zyl input:radio:eq(1)")
													.prop(
														"checked",
														false);
												break;
										}
										getEnclosureBindByEnclosureid();
									});
						}

					}
				});
	};

	// 路线管理
	function getLinesegmentAll() {
		$
			.ajax(
				config.url + "linesegment/getLinesegmentAll", {
					dataType: "json",
					type: "POST",
					timeout: 10000,
					success: function(data) {
						if(data.success == true) {
							data.obj == null ? data = data.t :
								data = data.obj;
							var html = "";
							for(var z = 0; z < data.length; z++) {
								html += '<option zyl_xx="' + z +
									'" zyl_id="' +
									data[z].enclosureId +
									'" zyl_type="' +
									data[z].enclosureType + '">' +
									data

								[z].name + '</option>';
							}
							$(".add_area_zyl").html(html);

							$(".add_area_zyl")
								.change(
									function() {
										var this_xx = data[$(
												this)
											.children(
												"option:selected")
											.attr("zyl_xx")];
										drawPath(
											this_xx.enclosureType,
											this_xx.centerLng,
											this_xx.centerLat,

											this_xx.radius,
											this_xx.points,
											this_xx.linesegments);
										$(".set_zyl label")
											.each(
												function() {
													if($(
															this)
														.text() == this_xx.alarmType) {
														$(
																this)
															.prev(
																"input:radio")
															.prop(
																"checked",
																true);
													} else {
														$(
																this)
															.prev(
																"input:radio")
															.prop(
																"checked",
																false)
													};
												});

										$(
												".set_time_zyl input:text:eq(0)")
											.val(
												this_xx.startDate);
										$(
												".set_time_zyl input:text:eq(1)")
											.val(
												this_xx.endDate);
										switch(this_xx.byTime) {
											case 0:
												$(
														".set_time_zyl input:radio:eq(0)")
													.prop(
														"checked",
														false);
												$(
														".set_time_zyl input:radio:eq(1)")
													.prop(
														"checked",
														true);
												break;
											case 1:
												$(
														".set_time_zyl input:radio:eq(0)")
													.prop(
														"checked",
														true);
												$(
														".set_time_zyl input:radio:eq(1)")
													.prop(
														"checked",
														false);
												break;
											default:
												console.log("----");
												break;
										};

										$(".set_speed_zyl input:text:eq(0)").val(this_xx.maxSpeed);
										$(".set_speed_zyl input:text:eq(1)").val(this_xx.delay);
										switch(this_xx.limitSpeed) {
											case 0:
												$(".set_speed_zyl input:radio:eq(0)").prop("checked",false);
												$(".set_speed_zyl input:radio:eq(1)").prop("checked",true);
												break;
											case 1:
												$(".set_speed_zyl input:radio:eq(0)").prop("checked",true);
												$(".set_speed_zyl input:radio:eq(1)").prop("checked",false);
												break;
										}
										$(".timelimit_zyl input:text:eq(0)").val(this_xx.linesegments[0].maxtimelimit);
										$(".timelimit_zyl input:text:eq(1)").val(this_xx.linesegments[0].mintimelimit);
										if(this_xx.linesegments[0].maxtimelimit != 0 && this_xx.linesegments[0].mintimelimit != 0){
											$(".timelimit_zyl input:radio:eq(0)").prop("checked",true);
										}else{
											$(".timelimit_zyl input:radio:eq(1)").prop("checked",true);
										}
										
										/*switch(this_xx.isTime) {
											case 0:
												$(".timelimit_zyl input:radio:eq(0)").prop("checked",false);
												$(".timelimit_zyl input:radio:eq(1)").prop("checked",true);
												break;
											case 1:
												$(".timelimit_zyl input:radio:eq(0)").prop("checked",true);
												$(".timelimit_zyl input:radio:eq(1)").prop("checked",false);
												break;
										}*/
										
										getEnclosureBindByEnclosureid();
									});
						}
					}
				})
	};

	// 线路关键点管理
	function getLinesegmentkeyPointAll() {
		$
			.ajax(
				config.url + "linesegment/getLinesegmentkeyPointAll", {
					dataType: "json",
					type: "POST",
					timeout: 10000,
					success: function(data) {
						if(data.success == true) {
							data.obj == null ? data = data.t :
								data = data.obj;
							var html = "";
							for(var z = 0; z < data.length; z++) {
								html += '<option zyl_xx="' + z +
									'" zyl_id="' +
									data[z].enclosureId +
									'" zyl_type="' +
									data[z].enclosureType + '">' +
									data

								[z].name + '</option>';
							}
							$(".add_area_zyl").html(html);

							$(".add_area_zyl")
								.change(
									function() {
										var this_xx = data[$(
												this)
											.children(
												"option:selected")
											.attr("zyl_xx")];
										drawPath(
											this_xx.enclosureType,
											this_xx.centerLng,
											this_xx.centerLat,

											this_xx.radius,
											this_xx.points,
											this_xx.linesegments);
										$(".set_zyl label")
											.each(
												function() {
													if($(
															this)
														.text() == this_xx.alarmType) {
														$(
																this)
															.prev(
																"input:radio")
															.prop(
																"checked",
																true);
													} else {
														$(
																this)
															.prev(
																"input:radio")
															.prop(
																"checked",
																false)
													};
												});

										$(
												".set_time_zyl input:text:eq(0)")
											.val(
												this_xx.startDate);
										$(
												".set_time_zyl input:text:eq(1)")
											.val(
												this_xx.endDate);
										switch(this_xx.byTime) {
											case 0:
												$(
														".set_time_zyl input:radio:eq(0)")
													.prop(
														"checked",
														false);
												$(
														".set_time_zyl input:radio:eq(1)")
													.prop(
														"checked",
														true);
												break;
											case 1:
												$(
														".set_time_zyl input:radio:eq(0)")
													.prop(
														"checked",
														true);
												$(
														".set_time_zyl input:radio:eq(1)")
													.prop(
														"checked",
														false);

												break;
											default:
												console
													.log("-----");
												break;
										};

										$(
												".set_speed_zyl input:text:eq(0)")
											.val(
												this_xx.maxSpeed);
										$(
												".set_speed_zyl input:text:eq(1)")
											.val(
												this_xx.delay);
										switch(this_xx.limitSpeed) {
											case 0:
												$(
														".set_speed_zyl input:radio:eq(0)")
													.prop(
														"checked",
														false);
												$(
														".set_speed_zyl input:radio:eq(1)")
													.prop(
														"checked",
														true);
												break;
											case 1:
												$(
														".set_speed_zyl input:radio:eq(0)")
													.prop(
														"checked",
														true);
												$(
														".set_speed_zyl input:radio:eq(1)")
													.prop(
														"checked",
														false);
												break;
										}
										$(".timelimit_zyl input:text:eq(0)").val(this_xx.linesegments[0].maxtimelimit);
										$(".timelimit_zyl input:text:eq(1)").val(this_xx.linesegments[0].mintimelimit);
										if(this_xx.linesegments[0].maxtimelimit != 0 && this_xx.linesegments[0].mintimelimit != 0){
											$(".timelimit_zyl input:radio:eq(0)").prop("checked",true);
										}else{
											$(".timelimit_zyl input:radio:eq(1)").prop("checked",true);
										}
										/*$(".timelimit_zyl input:text:eq(0)").val(this_xx.maxtimelimit);
										$(".timelimit_zyl input:text:eq(1)").val(this_xx.mintimelimit);
										switch(this_xx.isTime) {
											case 0:
												$(".timelimit_zyl input:radio:eq(0)").prop("checked",false);
												$(".timelimit_zyl input:radio:eq(1)").prop("checked",true);
												break;
											case 1:
												$(".timelimit_zyl input:radio:eq(0)").prop("checked",true);
												$(".timelimit_zyl input:radio:eq(1)").prop("checked",false);
												break;
										}*/
										getEnclosureBindByEnclosureid();
									});
						}
					}
				})
	};
	// 全路段限速管理
	function getALLSection() {
		$.ajax(config.url + "road/getALLSection", {
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					data.obj == null ? data = data.t : data = data.obj;
					var html = "";
					for(var z = 0; z < data.length; z++) {
						html += '<option zyl_xx="' + z + '" zyl_id="' +
							data[z].id + '">' + data[z].roadName +
							'</option>';
					}
					$(".add_area_zyl").html(html);

					$(".add_area_zyl").change(
						function() {
							var this_xx = data[$(this).children(
								"option:selected").attr("zyl_xx")];
							$(".set_zyl input:text")
								.val(this_xx.speedLimit);
							$(".set_time_zyl input:text:eq(0)").val(
								this_xx.startTime);
							$(".set_time_zyl input:text:eq(1)").val(
								this_xx.endTime);
							getSimNoById();
						});
				}
			}
		})
	};

	// 道路限速
	function getAllRoute() {
		$.ajax(config.url + "road/getAllRoute", {
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					data.obj == null ? data = data.t : data = data.obj;
					var html = "";
					for(var z = 0; z < data.length; z++) {
						html += '<option zyl_xx="' + z + '" zyl_id="' +
							data[z].id + '">' + data[z].roadName +
							'</option>';
					}
					$(".add_area_zyl").html(html);

					$(".add_area_zyl").change(
						function() {
							var this_xx = data[$(this).children(
								"option:selected").attr("zyl_xx")];
							$(".set_zyl input:text")
								.val(this_xx.speedLimit);
							$(".set_time_zyl input:text:eq(0)").val(
								this_xx.startTime);
							$(".set_time_zyl input:text:eq(1)").val(
								this_xx.endTime);
							getSimNoById();
						});
				}
			}
		})
	}
	// 根据图形类别画图
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
					path: newPath, // 设置线覆盖物路径
					strokeColor: "#3366FF", // 线颜色
					strokeOpacity: 1, // 线透明度
					strokeWeight: 3, // 线宽
					strokeStyle: "solid", // 线样式
					strokeDasharray: [10, 5]
					// 补充线样式
				});
				break;
			case "circle":
				var center_zyl = [];
				center_zyl.push(centerlng);
				center_zyl.push(centerlat);
				figure = new AMap.Circle({
					center: center_zyl, // 圆心位置
					radius: radius, // 半径
					strokeColor: "#3366FF", // 线颜色
					strokeOpacity: 1, // 线透明度
					strokeWeight: 3, // 线粗细度
					fillColor: "#1791fc", // 填充颜色
					fillOpacity: 0.35
					// 填充透明度
				});
				break;
			default:
				var pathArr_zyl = path.split(";");
				var newPathArr = [];
				for(one in pathArr_zyl) {
					newPathArr.push(pathArr_zyl[one].split(","));
				}
				figure = new AMap.Polygon({
					path: newPathArr, // 设置多边形边界路径
					strokeColor: "#3366FF", // 线颜色
					strokeOpacity: 0.2, // 线透明度
					strokeWeight: 3, // 线宽
					fillColor: "#1791fc", // 填充色
					fillOpacity: 0.35
					// 填充透明度
				});
				break;
		}
		figure.setMap(map);
		map.setFitView();
	}

	function GetDateStr(AddDayCount) {
		var dd = new Date();
		dd.setDate(dd.getDate() + AddDayCount); // 获取AddDayCount天后的日期
		var y = dd.getFullYear();
		var m = dd.getMonth() + 1; // 获取当前月份的日期
		var d = dd.getDate();
		return y + "-" + m + "-" + d;
	}
	$('.map_xk')
		.on(
			'click',
			'.searchPath',
			function() {
				map.remove(alarmPointMarkers);
				map.remove(stopPointMarkers);
				map.remove(startMarker);
				map.remove(endMarker);
				map.remove(vehiclePolyline);
				var day = $("input[name='whichday']:checked").val();
				switch(day) {
					case "today":
						var startTime = GetDateStr(0) + " 00:00:00";
						var endTime = GetDateStr(0) + " 23:59:59";
						break;
					case "ytoday":
						var startTime = GetDateStr(-1) + " 00:00:00";
						var endTime = GetDateStr(-1) + " 23:59:59";
						break;
					default:
						var startTime = $(".startTime").val();
						var endTime = $(".endTime").val();
				}
//				console.log(vehicleInfo);
				data_json = {
					"num":vehicleInfo.plateNo,
					"simNo": vehicleInfo.simNo,
					"startTime": startTime,
					"endTime": endTime,
					"parkingTime": $("input[name='parkingTime']").val()
				}
//				console.log(data_json);
				$.ajax(
						config.url + "realTime/mapTrajectory", {
							data: data_json,
							dataType: "json",
							type: "POST",
							timeout: 10000,
							success: function(data) {
								if(data.success == true) {
									data = data.t;
									var stop = false;
									var alarm = false;
									if(data.mileage != undefined) {
										if(data.trajectoryList.length > 0) {
											$(
													"input[name='point[]']:checked")
												.each(
													function() {
														if($(
																this)
															.val() == "stop") {
															stop = true;
														}
														if($(
																this)
															.val() == "alarm") {
															alarm = true;
														}
													});
											stopPointMarkers = [];
											alarmPointMarkers = [];
											vehiclePath = [];
											for(one in data.trajectoryList) {
												if(one != 0 &&
													one != data.trajectoryList.length - 1) {
													if(data.trajectoryList[one].type == "1" &&
														stop == true) {
														stopPointMarkers
															.push(drawStopPoint(data.trajectoryList[one]));
													} else if(data.trajectoryList[one].type == "2" &&
														alarm == true) {
														alarmPointMarkers
															.push(drawAlarmPoint(data.trajectoryList[one]));
													}
												}
												var lnglat = [];
												lnglat
													.push(data.trajectoryList[one].longitude);
												lnglat
													.push(data.trajectoryList[one].latitude);
												vehiclePath
													.push(lnglat);
											}
											drawOneVechilePath(vehiclePath);
										}
									}
								}
							}
						})

			});

	function drawStopPoint(point) {
		var lnglat = [];
		lnglat[0] = point.longitude;
		lnglat[1] = point.latitude;
		var stopPointMarker = new AMap.Marker({
			draggable: false,
			cursor: "move",
			position: lnglat,
			icon: new AMap.Icon({
				size: new AMap.Size(24, 24), // 图标大小
				image: "img/p.png",
			}),
			offset: new AMap.Pixel(-12, -12),
		});
		stopPointMarker.gpsId = point.gpsId;
		stopPointMarker.setMap(map);
		AMap.event.addListener(stopPointMarker, 'click', _onClickPoint);
		return stopPointMarker;

	}

	function drawAlarmPoint(point) {
		var lnglat = [];
		lnglat[0] = point.longitude;
		lnglat[1] = point.latitude;
		var alarmPointMarker = new AMap.Marker({
			draggable: false,
			cursor: "move",
			position: lnglat,
			icon: new AMap.Icon({
				size: new AMap.Size(30, 30), // 图标大小
				image: "img/gth.png",
			}),
			offset: new AMap.Pixel(-15, -30),
		});
		alarmPointMarker.gpsId = point.gpsId;
		alarmPointMarker.setMap(map);
		AMap.event.addListener(alarmPointMarker, 'click', _onClickPoint);
		return alarmPointMarker;

	}

	function drawOneVechilePath(vehiclePath) {
		startMarker = "";
		endMarker = "";
		startMarker = new AMap.Marker({
			draggable: false,
			cursor: "move",
			position: vehiclePath[0],
			icon: new AMap.Icon({
				size: new AMap.Size(40, 40), // 图标大小
				image: "img/qidian.png",
			}),
			offset: new AMap.Pixel(-20, -20),
		});
		startMarker.gpsId = vehiclePath.gpsId;
		startMarker.setMap(map);
		endMarker = new AMap.Marker({
			draggable: false,
			cursor: "move",
			position: vehiclePath[vehiclePath.length - 1],
			icon: new AMap.Icon({
				size: new AMap.Size(40, 40), // 图标大小
				image: "img/end.png",
			}),
			offset: new AMap.Pixel(-20, -20),
		});
		endMarker.gpsId = vehiclePath.gpsId;
		endMarker.setMap(map);
		AMap.event.addListener(startMarker, 'click', _onClickPoint);
		AMap.event.addListener(endMarker, 'click', _onClickPoint);
		vehiclePolyline = new AMap.Polyline({
			path: vehiclePath, // 设置线覆盖物路径
			strokeColor: "#3366FF", // 线颜色
			strokeOpacity: 1, // 线透明度
			strokeWeight: 3, // 线宽
			strokeStyle: "solid", // 线样式
			strokeDasharray: [10, 5]
			// 补充线样式
		});
		vehiclePolyline.setMap(map);
	}
	// 在指定位置打开信息窗体
	function openInfo(data, lnglat) {
		// 构建信息窗体中显示的内容
		var info = [];
		info.push("<div>");
		info.push("<div><b>车辆信息</b>");
		info.push("车牌号: " + data.plateNo);
		info.push("车载设备ID: " + data.simNo);
		info.push("车辆类型: " + data.desc);
		info.push("车辆颜色: " + data.vmcolor);
		info.push("速度与方向: " + data.velocity + "km/s," + startMove(data.direction));
		info.push("位置信息: " + data.location);
		info.push("接收时间: " + data.createDate);
		info.push("状态:" + data.status);
		info.push("报警状态:" + data.alarmState);
		infoWindow = new AMap.InfoWindow({
			content: info.join("<br/>")
			// 使用默认信息窗体框样式，显示信息内容
		});
		infoWindow.open(map, lnglat);
	}
	_onClickPoint = function(e) {
		getLocusInfoById(e.target.gpsId, e.lnglat);
	}

	function getLocusInfoById(id, lnglat) {
		data_json = {
			"id": id,
			"num":vehicleInfo.plateNo
		};
		$.ajax(config.url + "realTime/getLocusInfoById", {
			data: data_json,
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					openInfo(data.t, lnglat);
				}
			}
		})
	};
	$(".map_xk").on('click', '.tab_closebtn_xk', function() {
		map.remove(alarmPointMarkers);
		map.remove(stopPointMarkers);
		map.remove(startMarker);
		map.remove(endMarker);
		map.remove(vehiclePolyline);
	});
	$(".map_xk").on('click', '.downExcel', function() {
		var day = $("input[name='whichday']:checked").val();
		switch(day) {
			case "today":
				var startTime = GetDateStr(0) + " 00:00:00";
				var endTime = GetDateStr(0) + " 23:59:59";
				break;
			case "ytoday":
				var startTime = GetDateStr(-1) + " 00:00:00";
				var endTime = GetDateStr(-1) + " 23:59:59";
				break;
			default:
				var startTime = $(".startTime").val();
				var endTime = $(".endTime").val();
		}
		location.href = config.url + "realTime/mapTrajectoryExcel?simNo=" + vehicleInfo.simNo + "&startTime=" + startTime + "&endTime=" + endTime+"&num="+vehicleInfo.plateNo+"&parkingTime=0";
	});
	if(paramSet.vehicleicon == 1) {
		findVehicleByAdmin();
		try{clearTimeout(realtimetimeout);}
		catch(e){
			
		}
		updateVehicle();
		getCarList();
	}
	$(".map_xk").on('change', '.tab_main_xk i.specialleft_xk:eq(0) select.changeUI', function() {
		map.remove(eObject);
		map.remove(figure);
		if(dragRouteObj){
			dragRouteObj.destroy();
			dragRouteObj = "";
		}
		$("#startPonit").val("");
		$("#endPonit").val("");
		$("i.specialleft_xk:eq(1) input[type='text']").each(function() {
			$(this).val("");
		});
		$("dd#carList b img").each(function() {
			$(this).attr('src', "img/uncherked.jpg");
		});
		$("input[name='alarm']:eq(0)").prop("checked", true);
		$("input[name='onoff']:eq(0)").prop("checked", true);
		$("input[name='speed']:eq(0)").prop("checked", true);
	});
	$(".map_xk").on("click",".ctrlgrptabbtn li:not('.hideandshow')",function(){
		clearInterval(findPanelVehicleTimeOut);
		$(this).siblings("li:not('.hideandshow')").removeClass("on_xk");
		$(this).addClass("on_xk");
		var stateName = $(".ctrlgrptabbtn li.on_xk span").text();
		var state = "0";
		switch(stateName) {
			case "全部":
				state = "0";
				break;
			case "运行":
				state = "1";
				break;
			case "停车":
				state = "2";
				break;
			case "报警":
				state = "3";
				break;
			case "离线":
				state = "4";
				break;
			default:
				break;
		}
		vehicleStatus = state;
		findVehicleByAdmin();
		findPanelVehicle(state);
		findPanelVehicleTimeOut = setInterval("findPanelVehicle("+state+")", 5000);
	});
	changeCard();
	function tableTrClick() {
		$(".map_xk").on('dblclick','#tabcommoninfo tbody .tablistcontent_xk table tr',function() {
			var simNo = $(this).attr("simno");
			if(simNo != undefined) {
				getRealDatasBySimNo("", simNo, true);
			}
		})
	}
	tableTrClick();
	$(".map_xk").on("click","#tabtextmenu1 div:not('.righttriangle')",function(){
		var thissimno = $(this).parents("#tabtextmenu1").attr("simno");
		switch($(this).text()){
		case "轨迹回放":
			window.open("pastTrajectory.html?"+thissimno);
			break;
		case "详细信息":
			getRealDatasBySimNo($(this).parents("ul").attr("vid"),$(this).parents("ul").attr("simno"),false);
			break;
		case "导出":
			var stateName = $(".ctrlgrptabbtn li.on_xk span").text();
			var state = "0";
			switch(stateName) {
				case "全部":
					state = "0";
					break;
				case "运行":
					state = "1";
					break;
				case "停车":
					state = "2";
					break;
				case "报警":
					state = "3";
					break;
				case "离线":
					state = "4";
					break;
				default:
					break;
			}
			location.href = config.url + "panel/panelVehicleExcel?state=" + state;
		default:
			if($(this).is("[displaycount]")){
				if($(this).is(".on_xk")){
					$(this).removeClass("on_xk");
					$("#tabcommoninfo td[displayname='"+$(this).attr("displaycount")+"']").addClass("off_wd");
				}else{
					$(this).addClass("on_xk");
					$("#tabcommoninfo td[displayname='"+$(this).attr("displaycount")+"']").removeClass("off_wd");
				}
				$.ajax(config.url + "panel/upAdminCommon", {
					dataType: "json",
					type: "POST",
					data:getlistArr(1),
					timeout: 10000,
					success: function(data) {
						getAdminCommon();
					}
				});
			}
			break;
		}
	});
	function getlistArr(num){
		data_json = "{";
		$("#tabtextmenu"+num+" li").each(function(i){
			if($(this).find("div").attr("name") != undefined && $(this).find("div").attr("name") != ""){
				var onoff = 0;
				if($(this).find('div').is(".on_xk")){
					onoff = 1;
				}
				data_json += '"'+$(this).find('div').attr('name')+'"'+':'+'"'+onoff+'"'+',';
			}
		});
		data_json=data_json.substring(0,data_json.length-1);
		data_json+= "}";
		data_json = $.parseJSON(data_json);
		return data_json;
	}
	$(".map_xk").on("click","#tabtextmenu2 div:not('.righttriangle')",function(){
		var thisid = $(this).parents("#tabtextmenu2").attr("id_z");
		switch($(this).text()){
		case "删除":
			$("#tabcommandreply tr[id_z='"+thisid+"']").remove();
			break;
		case "清空":
			$("#tabcommandreply .tablistcontent_xk tbody tr[id_z]").remove();
			break;
		default:
			if($(this).is("[displaycount]")){
				//alert($(this).attr("displaycount"));
				if($(this).is(".on_xk")){
					$(this).removeClass("on_xk");
					$("#tabcommandreply td[displayname='"+$(this).attr("displaycount")+"']").addClass("off_wd");
				}else{
					$(this).addClass("on_xk");
					$("#tabcommandreply td[displayname='"+$(this).attr("displaycount")+"']").removeClass("off_wd");
				}
				$.ajax(config.url + "panel/upAdminCommand", {
					dataType: "json",
					type: "POST",
					data:getlistArr(2),
					timeout: 10000,
					success: function(data) {
						getAdminCommand();
					}
				});
			}
			break;
		}
	});
	function listOnChange(){
		if(listCommon){
			if(listCommon.plateNo == 0){
				$("#tabtextmenu1 li:eq(2) div").removeClass("on_xk");
			};
			if(listCommon.simNo == 0){
				$("#tabtextmenu1 li:eq(3) div").removeClass("on_xk");
			};
			if(listCommon.type == 0){
				$("#tabtextmenu1 li:eq(4) div").removeClass("on_xk");
			};
			if(listCommon.vColor == 0){
				$("#tabtextmenu1 li:eq(5) div").removeClass("on_xk");			
			};
			if(listCommon.driver == 0){
				$("#tabtextmenu1 li:eq(6) div").removeClass("on_xk");	
			};
			if(listCommon.velDir == 0){
				$("#tabtextmenu1 li:eq(7) div").removeClass("on_xk");		
			};
			if(listCommon.localtion == 0){
				$("#tabtextmenu1 li:eq(8) div").removeClass("on_xk");		
			};
			if(listCommon.sendTime == 0){
				$("#tabtextmenu1 li:eq(9) div").removeClass("on_xk");	
			};
			if(listCommon.status == 0){
				$("#tabtextmenu1 li:eq(10) div").removeClass("on_xk");
			};
			if(listCommon.alarm == 0){
				$("#tabtextmenu1 li:eq(11) div").removeClass("on_xk");		
			};
			if(listCommon.coopId == 0){
				$("#tabtextmenu1 li:eq(12) div").removeClass("on_xk");
			};
		}
		
	};
	listOnChange();
	function replayOnChange(){
		if(replayCommon){
			if(replayCommon.plateNo == 0){
				$("#tabtextmenu2 li:eq(3) div").removeClass("on_xk");
			};
			if(listCommon.vColor == 0){
				$("#tabtextmenu2 li:eq(4) div").removeClass("on_xk");
			};
			if(listCommon.sendTime == 0){
				$("#tabtextmenu2 li:eq(5) div").removeClass("on_xk");
			};
			if(listCommon.cmdId == 0){
				$("#tabtextmenu1 li:eq(6) div").removeClass("on_xk");			
			};
			if(listCommon.cmdDate == 0){
				$("#tabtextmenu1 li:eq(7) div").removeClass("on_xk");	
			};
			if(listCommon.sendResult == 0){
				$("#tabtextmenu1 li:eq(8) div").removeClass("on_xk");		
			};
			if(listCommon.sn == 0){
				$("#tabtextmenu1 li:eq(9) div").removeClass("on_xk");		
			};
			if(listCommon.confirmTime == 0){
				$("#tabtextmenu1 li:eq(10) div").removeClass("on_xk");	
			};
			if(listCommon.restore == 0){
				$("#tabtextmenu1 li:eq(11) div").removeClass("on_xk");
			};
			if(listCommon.resData == 0){
				$("#tabtextmenu1 li:eq(12) div").removeClass("on_xk");		
			};
		}
		
	};
	replayOnChange();
}
map_wd();