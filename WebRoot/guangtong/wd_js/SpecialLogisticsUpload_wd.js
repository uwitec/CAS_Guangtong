$(document).ready(function(){
	var map = new AMap.Map('container',{
				resizeEnable : true,
				zoom : 11,
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
    arr.push([117.197996,39.180593,20,1,'津A1234']);
    arr.push([117.150633,39.168011,40,2,'津B1234']);
    arr.push([117.138211,39.152305,90,2,'津A2DF2']);
    arr.push([117.147356,39.121354,60,1,'津F34R1']);
    arr.push([117.163273,39.10522,120,1,'津A2132']);
    arr.push([117.190053,39.09273,140,1,'津D23e4']);
    arr.push([117.228629,39.097698,160,1,'津Q1243']);
    arr.push([117.252256,39.129937,180,2,'津Ade36']);
    arr.push([117.253692,39.150167,240,1,'津H23de']);
    arr.push([117.252319,39.178916,320,2,'津A23de8']);
    for(one in arr){
    	var lnglat = new Array();
    	lnglat.push(arr[one][0]);
    	lnglat.push(arr[one][1]);
    	var marker = new AMap.Marker({});
		var markerContent = document.createElement("div");
		var markerImg = document.createElement("img");
		markerImg.className = "markerlnglat";
		markerImg.setAttribute("vehicle",arr[one][4]);
//				markerImg.addEventListener('click',alertTest(lnglat));
		(arr[one][3] == 1) ? markerImg.src = "/guangtong_ceshi/img/car.png" : markerImg.src = "/guangtong_ceshi/img/car.png"//根据内容变换图片
		markerImg.style.transform = "rotate(" + arr[one][2] + "deg)";//设置图片的方向
		markerImg.style.width = "24px";
		markerImg.style.height = "24px";
		markerContent.appendChild(markerImg);
		marker.setContent(markerContent);
		marker.setPosition(lnglat);
		marker.setMap(map);
		 AMap.event.addListener(marker,'click',function(e){
		 	 infoWindow.open(map, marker.getPosition());
		 });
    };
    //自适应显示多个标记
    var newCenter = map.setFitView();
    function alertTest_wd(e){
    	var cNum_wd = $(this).attr("vehicle");
    	alert(cNum_wd);
	}
})
