eObject = "";
drawType = "";
path = [];
figure = "";
cId = [];
vId = [];
simnoArr = [];
numArr = [];
ServerStatus();
mapName_wd = "长途客车";
markers = [];
alarmPointMarkers = [];
stopPointMarkers = [];
vehiclePath = [];
vehiclePolyline = "";
startMarker = "";
endMarker = "";
function map_wd() {
	$("#mapbottomtab").hide();
	var map = new AMap.Map('container', {
		resizeEnable : true,
		zoom : 12,
	});
	//标尺
	if(paramSet.ruler == 1){
		AMap.plugin([ 'AMap.ToolBar'], function() {
			map.addControl(new AMap.ToolBar());
		});
	}
	//比例尺
	if(paramSet.scale == 1){
		AMap.plugin([ 'AMap.Scale'], function() {
			map.addControl(new AMap.Scale());
		});
	}
	//标尺比例
	if(paramSet.rulerproportions != 0){
		map.setZoom(paramSet.rulerproportions);
	}
	//地图标注层
	if(paramSet.mapcalloutlayer == 1){
		 map.plugin(['AMap.MapType'],function(){
             var mapType = new AMap.MapType();
             map.addControl(mapType);
         });
	}
	// 输入关键词位置定位
	var auto = new AMap.Autocomplete({
		input : "positionsearch"
	});
	AMap.event.addListener(auto, 'select', function(e) {
		map.setCenter(e.poi.location)
	});
	var mouseTool = new AMap.MouseTool(map);
	$(".map_xk").on('click', 'a.button.draw', function() {
		map.remove(eObject);
		eObject = "";
		path = [];
		drawType = $(this).text();
		switch (drawType) {
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

	$(".map_xk")
			.on(
					'click',
					'a.button.save',
					function() {
						var SubSpaceResult = SubSpace();
						if (SubSpaceResult == false) {
							return;
						}
						;
						cId = [];
						vId = [];
						simnoArr = [];
						numArr = [];
						for (var i = 0; i < $("#carList img").length; i++) {
							if ($("#carList img:eq(" + i + ")").attr("src") == "img/cherked.jpg") {
								if ($("#carList img:eq(" + i + ")").parent("b")
										.attr("vId") != undefined
										&& $("#carList img:eq(" + i + ")")
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
						var saveType = $(
								".tab_main_xk .specialleft_xk:eq(0) option:selected")
								.text();
						if (saveType.indexOf("创建") >= 0) {
							addPath();
						} else {
							editPath();
						}
					});
	// 判空
	var SubSpace = function() {
		var result = false;
		if (path.length == 0
				&& $(".tab_main_xk .specialleft_xk:eq(0) option:selected")
						.text().indexOf("创建") >= 0) {
			alert("请画区域！");
		} else if ($(".tab_main_xk .specialleft_xk:eq(0) input.search").val() == "") {
			alert("请填写"
					+ $(".tab_main_xk .specialleft_xk:eq(0) input.search")
							.prev("label").text() + "！");
			$(".tab_main_xk .specialleft_xk:eq(0) input.search").focus();
		} else if ($(".set_time_zyl input[name='onoff']:checked").val() == 1
				&& ($(".time_plugin_lv_onclick.startTime").val() == "" || $(
						".time_plugin_lv_onclick.endTime").val() == "")) {
			alert("请填写时间段！");
		} else if ($(".set_speed_zyl input[name='speed']:checked").val() == 1
				&& $(".set_speed_zyl input[name='max_speed']").val() == "") {
			alert("请填写最高速度！");
		} else {
			result = true;
		}
		return result;
	};
	AMap.event.addListener(mouseTool, "draw", function callback(e) {
		eObject = e.obj; // obj属性就是鼠标事件完成所绘制的覆盖物对象。
		mouseTool.close();
		if (drawType == "绘制圆形") {
			path.center = e.obj.getCenter();
			path.radius = e.obj.getRadius();
			path.path = e.obj.getCenter();
		} else {
			path = e.obj.getPath();
		}

	});

	function findVehicleByAdmin() {
		var data_json = {
			"specialType" : "6"
		}
		$.ajax(config.url + "realTime/findVehicleByAdminAndSpecialType", {
			data : data_json,
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				if (data.success == true) {
//					if (markers.length != undefined && markers.length >= 0) {
						map.remove(markers);
//					}
					data.obj == null ? data = data.t : data = data.obj;
					getVehicles(data);
				}
			}
		});
	}
	;

	function updateVehicle() {
		realtimetimeout = setTimeout(function() {
			if (mapName_wd == "长途客车" && $(".map_xk").length != 0
					&& $(".map_top_xk").css('display') != "none") {
				findVehicleByAdmin();
				updateVehicle();
			} else {
				return;
			}
		}, 5000);
	}
	;

	function getVehicles(content) {
//		markers = [];
		for(one in content){
			for(two in content[one]){
				for(three in content[one][two]){
					if(content[one][two][three].isRead == 1){
						var marker = createMarker(content[one][two][three]);
						markers.push(marker);
						AMap.event.addListener(marker, 'click', _onClick); // 点击事件
					}
				}
			}
		}
		// map.setFitView();
	}

	function createMarker(data) {
		var lnglat = [];
		lnglat[0] = data.longitude;
		lnglat[1] = data.latitude;
		var marker = new AMap.Marker({
			draggable : false,
			cursor : "move",
			position : lnglat,
			label : {
				content : '<span style="font-size:10px">' + data.num
						+ '</span>',
				offset : new AMap.Pixel(-15, 30)
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
//		switch (data.sName) {
//		case "冷链配送":
//			markerImg.src = "img/lenglian.png";
//			fontContent.innerHTML = "冷";
//			break;
//		case "干线运输":
//			markerImg.src = "img/ganxian.png";
//			fontContent.innerHTML = "干";
//			break;
//		case "长途客车":
			markerImg.src = "img/changtu.png";
			fontContent.innerHTML = "长";
//			break;
//		case "集港物流":
//			markerImg.src = "img/jigang.png";
//			fontContent.innerHTML = "集";
//			break;
//		case "危险品零售":
//			markerImg.src = "img/weixianpin.png";
//			fontContent.innerHTML = "危";
//			break;
//		case "仓储配送":
//			markerImg.src = "img/cangchu.png";
//			fontContent.innerHTML = "仓";
//			break;
//		default:
//			markerImg.src = "img/huoche.png";
//			fontContent.innerHTML = "货";
//			break;
//		}
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
	}
	_onClick = function(e) {
		map.remove(alarmPointMarkers);
		map.remove(stopPointMarkers);
		map.remove(startMarker);
		map.remove(endMarker);
		map.remove(vehiclePolyline);
		if(paramSet.generalinformation == 1){
			var simno = e.target.simno;
			var vid = e.target.vid;
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
			data : {
				"simNo" : simno
			},
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				if (data.success == true) {
					data.obj == null ? data = data.t : data = data.obj;
					vehicleInfo = data;
					vehicleInfo_wd();
					$("#mapbottomtab").show();
					$("#mapbottomtab .tab_xk li:eq(0)").click();
					if (center == true) {
						var lnglat = [];
						lnglat[0] = vehicleInfo.longitude;
						lnglat[1] = vehicleInfo.latitude;
						map.setCenter(lnglat);
					}
				}
			},
			error : function() {
				map.setFitView();
			}
		});
	}

	function getCarList() {
		var data_json = {
			"specialType" : "6"
		}
		$
				.ajax(
						config.url
								+ "realTime/findVehicleByAdminAndSpecialType",
						{
							data : data_json,
							type : "POST",
							timeout : 10000,
							success : function(data) {
								if (data.success == true) {
									data.obj == null ? data = data.t
											: data = data.obj;
									html = "";
									for (one in data) {
										html += "<dl>";
										html += '<dt ><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg" carListClick /></b>' + one+ '</dt>';
										html+="<dd>";
										for(two in data[one]){
											if(two.indexOf("TEAM")!=-1){
												for(three in data[one][two]){
													html +="<dl>";
													if(data[one][two][three].isRead == 1){
														html += '<dt simno="'
															+ data[one][two][three].simNo
															+ '" vid="'
															+ data[one][two][three].id
															+ '"><i><img src="img/xlcl (1).png"/></i><b><img src="img/cherked.jpg" defaultChecked carListClick /></b>'
															+ data[one][two][three].num
															+ '</dt>';
													}else{
														html += '<dt simno="'
															+ data[one][two][three].simNo
															+ '" vid="'
															+ data[one][two][three].id
															+ '"><i><img src="img/xlcl (1).png"/></i><b><img src="img/uncherked.jpg" defaultChecked carListClick/></b>'
															+ data[one][two][three].num
															+ '</dt>';
													}
													html+="</dl>";
												}
											}else{
												html +="<dl>";
												html +='<dt><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg" carListClick /></b>' +two+ '</dt>';
												html +="<dd>";
												for(three in data[one][two]){
													html +="<dl>";
													if(data[one][two][three].isRead == 1){
														html += '<dt simno="'
															+ data[one][two][three].simNo
															+ '" vid="'
															+ data[one][two][three].id
															+ '"><i><img src="img/xlcl (1).png"/></i><b><img src="img/cherked.jpg" defaultChecked carListClick /></b>'
															+ data[one][two][three].num
															+ '</dt>';
													}else{
														html += '<dt simno="'
															+ data[one][two][three].simNo
															+ '" vid="'
															+ data[one][two][three].id
															+ '"><i><img src="img/xlcl (1).png"/></i><b><img src="img/uncherked.jpg" defaultChecked carListClick/></b>'
															+ data[one][two][three].num
															+ '</dt>';
													}
													html+="</dl>";
												}
												html+="</dd>";
												html+="</dl>";
											}
										}
										html+="</dd>";
										html+="</dl>";
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
			data : {
				"vehicleArr" : numArr
			},
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				findVehicleByAdmin();
			},
			error : function() {
				map.setFitView();
			}
		});
}

	function changeCard() {
		$(".mapbtn_xk li").click(function() {
			mouseTool.close();
			if (eObject) {
				map.remove(eObject);
			}
			if (figure) {
				map.remove(figure);
			}
		})
	}
	// 新增路线
	function addPath() {
		var manageType = $(".map_top_xk li.on_xk").text();
		var point = [];
		if (path.center) {
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
			for (one in path) {
				point.push(path[one].lng + ';' + path[one].lat);
			}
		}
		var mapport = "linesegment/insertkeyPoint", data_json = {
			"name" : $(".tab_main_xk .specialleft_xk:eq(0) input.search").val(),
			"byTime" : $(".set_time_zyl input[name=onoff]:checked").val(),
			"delay" : $(".set_speed_zyl input[name='continued_time']").val(),
			"enclosureType" : $(
					".tab_main_xk .specialleft_xk:eq(0) select:eq(1)").val(),
			"startDate" : $("input.time_plugin_lv_onclick.startTime").val(),
			"endDate" : $("input.time_plugin_lv_onclick.endTime").val(),
			"limitSpeed" : $(".set_speed_zyl input[name='speed']:checked")
					.val(),
			"maxSpeed" : $(".set_speed_zyl input[name='max_speed']").val(),
			"alarmType" : $(
					".tab_main_xk .specialleft_xk:eq(1) input[name='alarm']:checked")
					.val(),
			"radius" : radius,
			"centerLat" : centerlat,
			"centerLng" : centerlng,
			"plateNoS" : numArr,
			"vehicleIdS" : vId,
			"simNoS" : simnoArr,
			"vehArr" : point,
			"isTime":$(".set_speed_zyl input[name=open]:checked").val(),
			"maxtimelimit":$(".set_speed_zyl input[name='maxtimelimit']").val(),
			"mintimelimit":$(".set_speed_zyl input[name='mintimelimit']").val(),
		};
		// 保存路线
		$.ajax(config.url + mapport, {
			data : data_json,
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				if (data.success == true) {
					alert("添加成功!");
					$(".map_top_xk li.on_xk").click();
				} else {
					alert("添加失败！请稍后再试！")
				}
			}
		});
	}
	;
	// 编辑路线
	function editPath() {
		var manageType = $(".map_top_xk li.on_xk").text();
		if (manageType == "客车路线站点管理") {
			data_json = {
				"enclosureId" : $(".add_area_zyl option:selected").attr(
						"zyl_id"),
				"byTime" : $(
						".tab_main_xk .specialleft_xk:eq(1) input[name='onoff']:checked")
						.val(),
				"delay" : $(
						".tab_main_xk .specialleft_xk:eq(1) input[name='continued_time']")
						.val(),
				"limitSpeed" : $(
						".tab_main_xk .specialleft_xk:eq(1) input[name='speed']:checked")
						.val(),
				"enclosureType" : $(".add_area_zyl option:selected").attr(
						"zyl_type"),
				"maxSpeed" : $(
						".tab_main_xk .specialleft_xk:eq(1) input[name='max_speed']")
						.val(),
				"offsetdelay" : "10",
				"alarmType" : $(
						".tab_main_xk .specialleft_xk:eq(1) input[name='alarm']:checked")
						.val(),
				"startDate" : $("input.time_plugin_lv_onclick.startTime").val(),
				"endDate" : $("input.time_plugin_lv_onclick.endTime").val(),
				"isTime":$(".tab_main_xk .specialleft_xk:eq(1) input[name='isTime']:checked").val(),
				"maxtimelimit":$(".maxtimelimit_z").val(),
				"mintimelimit":$(".mintimelimit_z").val(),
				"simNoS" : simnoArr,
				"plateNoS" : numArr,
				"vehicleIdS" : vId,
			};
			// 保存路线
			$.ajax(config.url + "realTime/updateEnclosure", {
				data : data_json,
				dataType : "json",
				type : "POST",
				timeout : 10000,
				success : function(data) {
					if (data.success == true) {
						alert("更新成功!");
						$(".map_top_xk li.on_xk").click();
					} else {
						alert("更新失败！请稍后再试！")
					}
				}
			});
		}
	}

	$(".map_xk").on("change", ".changeUI", function() {
		var text_zyl = $(this).children("option:selected").text();
		if (text_zyl == "管理已有路线") {
			getLinesegmentAll();
		}
	});

	function getLinesegmentAll() {
		$
				.ajax(
						config.url + "linesegment/getLinesegmentAll",
						{
							dataType : "json",
							type : "POST",
							timeout : 10000,
							success : function(data) {
								if (data.success == true) {
									data.obj == null ? data = data.t
											: data = data.obj;
									var html = "";
									for (var z = 0; z < data.length; z++) {
										html += '<option zyl_xx="' + z
												+ '" zyl_id="'
												+ data[z].enclosureId
												+ '" zyl_type="'
												+ data[z].enclosureType + '">'
												+ data[z].name + '</option>';
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
																			if ($(
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
																			}
																			;
																		});

														$(
																".set_time_zyl input:text:eq(0)")
																.val(
																		this_xx.startDate);
														$(
																".set_time_zyl input:text:eq(1)")
																.val(
																		this_xx.endDate);
														switch (this_xx.byTime) {
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
														}
														;
														$(
																".set_speed_zyl input:text:eq(0)")
																.val(
																		this_xx.maxSpeed);
														$(
																".set_speed_zyl input:text:eq(1)")
																.val(
																		this_xx.delay);
														switch (this_xx.limitSpeed) {
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
	}
	;
	// 根据图形类别画图
	function drawPath(type, centerlng, centerlat, radius, path, linesegments) {
		map.remove(figure);
		switch (type) {
		case "route":
			var newPath = [];
			var spath = [];
			for (i in linesegments) {
				spath.push(linesegments[i].latitude1);
				spath.push(linesegments[i].longitude1);
				newPath.push(spath);
				spath = [];
			}
			figure = new AMap.Polyline({
				path : newPath, // 设置线覆盖物路径
				strokeColor : "#3366FF", // 线颜色
				strokeOpacity : 1, // 线透明度
				strokeWeight : 3, // 线宽
				strokeStyle : "solid", // 线样式
				strokeDasharray : [ 10, 5 ]
			// 补充线样式
			});
			break;
		case "circle":
			var center_zyl = [];
			center_zyl.push(centerlng);
			center_zyl.push(centerlat);
			figure = new AMap.Circle({
				center : center_zyl, // 圆心位置
				radius : radius, // 半径
				strokeColor : "#3366FF", // 线颜色
				strokeOpacity : 1, // 线透明度
				strokeWeight : 3, // 线粗细度
				fillColor : "#1791fc", // 填充颜色
				fillOpacity : 0.35
			// 填充透明度
			});
			break;
		default:
			var pathArr_zyl = path.split(";");
			var newPathArr = [];
			for (one in pathArr_zyl) {
				newPathArr.push(pathArr_zyl[one].split(","));
			}
			figure = new AMap.Polygon({
				path : newPathArr, // 设置多边形边界路径
				strokeColor : "#3366FF", // 线颜色
				strokeOpacity : 0.2, // 线透明度
				strokeWeight : 3, // 线宽
				fillColor : "#1791fc", // 填充色
				fillOpacity : 0.35
			// 填充透明度
			});
			break;
		}
		figure.setMap(map);
		map.setFitView();
	}
	function GetDateStr(AddDayCount) {
		var dd = new Date();
		dd.setDate(dd.getDate() + AddDayCount);// 获取AddDayCount天后的日期
		var y = dd.getFullYear();
		var m = dd.getMonth() + 1;// 获取当前月份的日期
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
						switch (day) {
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
						data_json = {
							"num":vehicleInfo.plateNo,
							"simNo" : vehicleInfo.simNo,
							"startTime" : startTime,
							"endTime" : endTime,
							"parkingTime" : $("input[name='parkingTime']")
									.val()
						}
						$
								.ajax(
										config.url + "realTime/mapTrajectory",
										{
											data : data_json,
											dataType : "json",
											type : "POST",
											timeout : 10000,
											success : function(data) {
												if (data.success == true) {
													data = data.t;
													var stop = false;
													var alarm = false;
													if (data.mileage != undefined) {
														if (data.trajectoryList.length > 0) {
															$(
																	"input[name='point[]']:checked")
																	.each(
																			function() {
																				if ($(
																						this)
																						.val() == "stop") {
																					stop = true;
																				}
																				if ($(
																						this)
																						.val() == "alarm") {
																					alarm = true;
																				}
																			});
															stopPointMarkers = [];
															alarmPointMarkers = [];
															vehiclePath = [];
															for (one in data.trajectoryList) {
																if (one != 0
																		&& one != data.trajectoryList.length - 1) {
																	if (data.trajectoryList[one].type == "1"
																			&& stop == true) {
																		stopPointMarkers
																				.push(drawStopPoint(data.trajectoryList[one]));
																	} else if (data.trajectoryList[one].type == "2"
																			&& alarm == true) {
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
			draggable : false,
			cursor : "move",
			position : lnglat,
			icon : new AMap.Icon({
				size : new AMap.Size(24, 24), // 图标大小
				image : "img/p.png",
			}),
			offset : new AMap.Pixel(-12, -12),
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
			draggable : false,
			cursor : "move",
			position : lnglat,
			icon : new AMap.Icon({
				size : new AMap.Size(30, 30), // 图标大小
				image : "img/gth.png",
			}),
			offset : new AMap.Pixel(-15, -30),
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
			draggable : false,
			cursor : "move",
			position : vehiclePath[0],
			icon : new AMap.Icon({
				size : new AMap.Size(40, 40), // 图标大小
				image : "img/qidian.png",
			}),
			offset : new AMap.Pixel(-20, -20),
		});
		startMarker.gpsId = vehiclePath.gpsId;
		startMarker.setMap(map);
		endMarker = new AMap.Marker({
			draggable : false,
			cursor : "move",
			position : vehiclePath[vehiclePath.length - 1],
			icon : new AMap.Icon({
				size : new AMap.Size(40, 40), // 图标大小
				image : "img/end.png",
			}),
			offset : new AMap.Pixel(-20, -20),
		});
		endMarker.gpsId = vehiclePath.gpsId;
		endMarker.setMap(map);
		AMap.event.addListener(startMarker, 'click', _onClickPoint);
		AMap.event.addListener(endMarker, 'click', _onClickPoint);
		vehiclePolyline = new AMap.Polyline({
			path : vehiclePath, // 设置线覆盖物路径
			strokeColor : "#3366FF", // 线颜色
			strokeOpacity : 1, // 线透明度
			strokeWeight : 3, // 线宽
			strokeStyle : "solid", // 线样式
			strokeDasharray : [ 10, 5 ]
		// 补充线样式
		});
		vehiclePolyline.setMap(map);
	}
	// 在指定位置打开信息窗体
	function openInfo(data, lnglat) {
		//console.log(data);
		// 构建信息窗体中显示的内容
		var info = [];
		info.push("<div>");
		info.push("<div><b>车辆信息</b>");
		info.push("车牌号: " + data.plateNo);
		info.push("车载设备ID: " + data.simNo);
		info.push("车辆类型: " + data.desc);
		info.push("车辆颜色: " + data.vmcolor);
		info.push("速度与方向: " + data.velocity + "km/s," +  startMove(data.direction));
		info.push("位置信息: " + data.location);
		info.push("接收时间: " + data.createDate);
		info.push("状态:" + data.status);
		info.push("报警状态:" + data.alarmState);
		infoWindow = new AMap.InfoWindow({
			content : info.join("<br/>")
		// 使用默认信息窗体框样式，显示信息内容
		});
		infoWindow.open(map, lnglat);
	}
	_onClickPoint = function(e) {
		getLocusInfoById(e.target.gpsId, e.lnglat);
	}
	function getLocusInfoById(id, lnglat) {
		//console.log(lnglat);
		data_json = {
			"id" : id,
			"num":vehicleInfo.plateNo
		};
		$.ajax(config.url + "realTime/getLocusInfoById", {
			data : data_json,
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				if (data.success == true) {
					//console.log(lnglat);
					openInfo(data.t, lnglat);
				}
			}
		})
	}
	;
	$(".map_xk").on('click', '.tab_closebtn_xk', function() {
		map.remove(alarmPointMarkers);
		map.remove(stopPointMarkers);
		map.remove(startMarker);
		map.remove(endMarker);
		map.remove(vehiclePolyline);
	});
	$(".map_xk").on('click','.downExcel',function() {
		var day = $("input[name='whichday']:checked").val();
		switch (day) {
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
	if(paramSet.vehicleicon == 1){
		findVehicleByAdmin();
		try{clearTimeout(realtimetimeout);}
		catch(e){
			
		}
		updateVehicle();
		getCarList();
	};
	$(".map_xk").on('change','.tab_main_xk i.specialleft_xk:eq(0) select.changeUI',function(){
		map.remove(eObject);
		map.remove(figure);
		$("i.specialleft_xk:eq(1) input[type='text']").each(function(){
			$(this).val("");
		});
		$("dd#carList b img").each(function(){
			$(this).attr('src',"img/uncherked.jpg");
		});
		$("input[name='alarm']:eq(0)").prop("checked",true);
		$("input[name='onoff']:eq(0)").prop("checked",true);
		$("input[name='speed']:eq(0)").prop("checked",true);
	});
	changeCard();
}
map_wd();