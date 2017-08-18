
picHead = "http://192.168.0.168:8080/webgis/vehiclePicture/";
char_wd = {
	"charType":"",
	"charName": "",
	"simNo": "",
	"startTime" : "",
	"endTime" : "",
	"type" : "",
	"cIdArr" : "",
	"vIdArr" : ""

}
function fenye_lv(tp, functionname) {
	var html = "";
	html += '<li>首页</li>'
	html += '<li>上一页</li>'
	if((tpa - 3) < 1) {
		for(k = 1; k < tpa; k++) {
			html += '<li>' + k + '</li>'
		};
	} else {
		for(q = tpa - 3; q < tpa; q++) {
			html += '<li>' + q + '</li>'
		};
	};
	if((tpa + 3) > tp) {
		for(w = tpa + 1; w < tp + 1; w++) {
			html += '<li>' + w + '</li>'
		};
	} else {
		for(e = tpa + 1; e < tpa + 4; e++) {
			html += '<li>' + e + '</li>'
		};
	};
	html += '<li>下一页</li>'
	html += '<li style="border: none !important;" tpa=' + tp + ' >尾页</li>'
	$(".page_right_xk").html(html);
	$(".page_right_xk li").click(function() {
		var tpa_end = $(".page_right_xk li[tpa]").attr("tpa");
		if($(this).text() == "首页") {
			tpa = 1;
		} else if($(this).text() == "尾页") {
			tpa = tpa_end;
		} else if($(this).text() == "上一页" && tpa != 1) {
			tpa = tpa - 1;
		} else if($(this).text() == "下一页" && tpa != tpa_end) {
			tpa = (tpa + 1);
		} else if($(this).text() != "下一页" && $(this).text() != "上一页" && $(this).text() != "首页" && $(this).text() != "尾页") {
			tpa = Number($(this).text());
		}
		functionname();
	});
}