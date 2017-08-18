$(document).ready(function(){
	var map = new AMap.Map('container',{
				resizeEnable : true,
				zoom : 14,
			});
	//开启图层切换
	map.plugin(['AMap.MapType'],function(){
		var mapType = new AMap.MapType;
		map.addControl(mapType);
	});
	//开启比例尺
	map.plugin(['AMap.Scale'],function(){
		var scale = new AMap.Scale;
		map.addControl(scale)
	});
	//点标记
	var arr = new Array();
    arr.push([117.654854,39.025529]);
    arr.push([117.657643,39.027062]);
    arr.push([117.657364,39.029129]);
    arr.push([117.656956,39.030146]);
    arr.push([117.659467,39.031446]);
    arr.push([117.664295,39.032596]);
    arr.push([117.668629,39.03358]);
    var polyline = new AMap.Polyline({
            map: map,
            path: arr,
            strokeColor: "#FF33FF",//线颜色
            strokeOpacity: 1,//线透明度
            strokeWeight: 3,//线宽
            strokeStyle: "solid"//线样式
    });
    var markerArr = new Array();
    markerArr.push([117.657364,39.029129,240]);
    markerArr.push([117.664295,39.032196,50]);
     for(one in markerArr){
     	//console.log(markerArr[one][2]);
    	var lnglat = new Array();
    	lnglat.push(markerArr[one][0]);
    	lnglat.push(markerArr[one][1]);
    	var marker = new AMap.Marker({});
		var markerContent = document.createElement("div");
		var markerImg = document.createElement("img");
		markerImg.className = "markerlnglat";
		markerImg.src = "img/car.png";
		markerImg.style.transform = "rotate(" + markerArr[one][2] + "deg)";//设置图片的方向
		markerImg.style.width = "24px";
		markerImg.style.height = "24px";
		markerContent.appendChild(markerImg);
		marker.setContent(markerContent);
		marker.setPosition(lnglat);
		marker.setMap(map);
    };
	var newCenter = map.setFitView();
    
})
