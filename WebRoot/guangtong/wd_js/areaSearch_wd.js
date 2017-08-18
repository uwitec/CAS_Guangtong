var eObject = "";
var path = [];
var vehicleArr = [];
markerObj = [];
var infoWindow = [];
var map = new AMap.Map('container', {
	resizeEnable : true,
	zoom : 12
});
var mouseTool = new AMap.MouseTool(map);
//点击画图
$(".areaDraw").click(function() {
	map.remove(eObject);
	map.remove(markerObj);
	map.remove(infoWindow);
	eObject = "";
	path = [];
	mouseTool.polygon();
});
//监听画图结束
AMap.event.addListener(mouseTool, "draw", function callback(e) {
	eObject = e.obj; 
	mouseTool.close();
	path = e.obj.getPath();
});
function IsAccessArea(){
	$(".areaSearch_right ul li:last input[value='查询车辆']").click(function(){
		if(path == ""){
			alert("请画区域！");
			return;
		}
		var point = [];
		for(one in path) {
			point.push(path[one].lng + ',' + path[one].lat);
		}
		var points = point.join(";");
		$.ajax(config.url + "realTime/IsAccessArea", {
			data: {
				"startTime" : $(".startTime").val(),
				"endTime" : $(".endTime").val(),
				"points" : points,
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(data.obj);
				vehicleArr = data.obj
				html = "";
				var html = "";
				for(var i = 0; i < data.obj.length; i++) {
					html += '<li indexNum='+ i +'>' +data.obj[i].num + '</li>';
				}
				$(".IsAccessArea_list_zyl").html(html);
				dbClickVsehicle();
			}
		});
	})
}
IsAccessArea();
function dbClickVsehicle(){
	$(".IsAccessArea_list_zyl li").dblclick(function(){
		map.remove(markerObj);
		markerObj = createMarker($(this).attr("indexNum"));
	});
}
function createMarker(indexNum) {
	var lnglat = [];
	lnglat[0] = vehicleArr[indexNum].longitude;
	lnglat[1] = vehicleArr[indexNum].latitude;
	//console.log(lnglat);
	var marker = new AMap.Marker({
		draggable: false,
		cursor: "move",
		position: lnglat,
	});
	marker.setMap(map);
	openInfo(indexNum,lnglat)
	return marker;
};
function openInfo(indexNum,lnglat) {
    //构建信息窗体中显示的内容
    var info = [];
    info.push("<div>");
    info.push("车牌号 :"+vehicleArr[indexNum].num);
    info.push("车辆颜色 :"+vehicleArr[indexNum].vmcolor);
    info.push("经纬度 :"+vehicleArr[indexNum].longitude+","+vehicleArr[indexNum].latitude);
    info.push("位置信息 :"+vehicleArr[indexNum].location);
    info.push("接收时间 :"+vehicleArr[indexNum].vmcolor);
    info.push("速度与方向"+vehicleArr[indexNum].velocity+"km/h,"+vehicleArr[indexNum].direction+"</div>");
    infoWindow = new AMap.InfoWindow({
        content: info.join("<br/>"),
        offset: new AMap.Pixel(0, -30)
    });
    infoWindow.open(map,lnglat);
}
$(".areaSearch_right ul li:first input").click(function(){
	if($(this).is(":checked")){
		$(".startTime,.endTime").attr("disabled",false);
	}else{
		$(".startTime,.endTime").attr("disabled",true);
	}
});