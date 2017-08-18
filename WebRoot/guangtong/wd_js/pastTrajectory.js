alarmPointMarkers = [];
stopPointMarkers = [];
vehiclePolyline = "";
startMarker = "";
endMarker = "";
vesimNo = location.href.split("?")[1];
function GetDateStr(AddDayCount){
	var oData= new Date();
	oData.setDate(oData.getDate()+AddDayCount)
	var year = oData .getFullYear(); //获取完整的年份(4位,1970-????)
	var month = oData.getMonth()+1; //获取当前月份(0-11,0代表1月)
	var data = oData.getDate(); //获取当前日(1-31)
	var day = oData.getDay(); //获取当前星期X(0-6,0代表星期天)
	return year+"-" +month+"-" + data;
}
var map = new AMap.Map('container', {
	resizeEnable : true,
	zoom : 12
});
$(".startTime").val(GetDateStr(0) + "　00:00:00");
$(".endTime").val(GetDateStr(0) + "　23:59:59");
$("input[name='ddddd']").click(function(){
	if($(this).val() == 1){
		$(".startTime").val(GetDateStr(0) + "　00:00:00");
		$(".endTime").val(GetDateStr(0) + "　23:59:59");
		$(".startTime").attr("disabled",true);
		$(".endTime").attr("disabled",true);
	}else if($(this).val() == 2){
		$(".startTime").val(GetDateStr(-1) + "　00:00:00");
		$(".endTime").val(GetDateStr(-1) + "　23:59:59");
		$(".startTime").attr("disabled",true);
		$(".endTime").attr("disabled",true);
	}else{
		$(".startTime").attr("disabled",false);
		$(".endTime").attr("disabled",false);
	}
});
function carListAnother(){
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
						for(one in data) {
							html += "<dl>";
							html += '<dt ><i><img src="img/u380.png"></i>' + one + '</dt>';
							html += "<dd>";
							for(two in data[one]) {
								if(two.indexOf("TEAM")!=-1){
									for(three in data[one][two]) {
										html += "<dl>";
										if(data[one][two][three].simNo == location.href.split("?")[1]) {
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
									html += '<dt><i><img src="img/u380.png"></i>' + two + '</dt>';
									html += "<dd>";
									for(three in data[one][two]) {
										html += "<dl>";
										if(data[one][two][three].simNo == location.href.split("?")[1]) {
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
						imgClick();
					}
				}
			});
}
carListAnother();
function imgClick(){
	$("img[src='img/uncherked.jpg']").click(function(){
		vesimNo = $(this).parent().parent().attr("simno");
	});
};
$('.searchPath').click(function() {
		map.remove(alarmPointMarkers);
		map.remove(stopPointMarkers);
		map.remove(startMarker);
		map.remove(endMarker);
		map.remove(vehiclePolyline);
		data_json = {
			"simNo": vesimNo,
			"startTime": $(".startTime").val(),
			"endTime": $(".endTime").val(),
			"parkingTime": $("input[name='parkingTime']").val()
		}
		$.ajax(config.url + "realTime/mapTrajectory", {
					data: data_json,
					dataType: "json",
					type: "POST",
					timeout: 10000,
					success: function(data) {
						if(data.success == true) {
							data = data.t;
							var stop = false;
							var alarm = false;
							$("input[name='timeCounts']").val(data.timeCounts);
							if(data.mileage != undefined) {
								if(data.trajectoryList.length > 0) {
									$("input[name='point[]']:checked").each(function() {
										alert("1111");
												if($(this)
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
											} else if(data.trajectoryList[one].type == "0" &&
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
									pointList(data.trajectoryList);
								}
							}
						}
					}
				})

	});
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
	map.setFitView();
	$("input[name='vehiclePolyline']").val(vehiclePolyline.getLength());
};
_onClickPoint = function(e) {
	getLocusInfoById(e.target.gpsId, e.lnglat);
}

function getLocusInfoById(id, lnglat) {
	data_json = {
		"id": id
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

};
function pointList(data){
	//console.log(data);
	html = "";
	for(one in data){
		html += "<tr>";
		html += "<td title='"+ data[one].portTime+"'>"+ data[one].portTime+"</td>";
		html += "<td title='"+ data[one].velocity+"km/h,"+data[one].direction+"'>"+ data[one].velocity+"km/h,"+data[one].direction+"</td>";
		html += "<td title='"+ data[one].location+"'>"+ data[one].location+"</td>";
		html += "<td title='"+ data[one].status+"'>"+ data[one].status+"</td>";
		html += "<td title='"+ data[one].alarmState+"'>"+ data[one].alarmState+"</td>";
		if(one != 0){
			var lnglat = new AMap.LngLat(data[one-1].longitude,data[one-1].latitude);
			var lnglatNext = [];
			lnglatNext.push(data[one].longitude);
			lnglatNext.push(data[one].latitude);
			html += "<td title='"+ lnglat.distance(lnglatNext)+"'>"+ lnglat.distance(lnglatNext)+"</td>";
		}else{
			html += "<td title='"+0+"'>"+ 0+"</td>";
		}
		html += "</tr>";
	}
	$(".areaSearch_bottom_content div table tbody").html(html);
};
function mapTrajectoryExcel (){
	$(".searchPath").next().click(function(){
		location.href = config.url + "realTime/mapTrajectoryExcel?simNo="+vesimNo+"&startTime="+$(".startTime").val()+"&endTime="+$(".endTime").val()+"&parkingTime="+$("input[name='parkingTime']").val();
	});
};
mapTrajectoryExcel();
