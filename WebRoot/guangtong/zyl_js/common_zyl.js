
/*分页*/
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
/*分页2*/
function fenye2_lv(tp, functionname) {
	var html = "";
	html += '<li>首页</li>'
	html += '<li>上一页</li>'
	if((tpa2 - 3) < 1) {
		for(k = 1; k < tpa2; k++) {
			html += '<li>' + k + '</li>'
		};
	} else {
		for(q = tpa2 - 3; q < tpa2; q++) {
			html += '<li>' + q + '</li>'
		};
	};
	if((tpa2 + 3) > tp) {
		for(w = tpa2 + 1; w < tp + 1; w++) {
			html += '<li>' + w + '</li>'
		};
	} else {
		for(e = tpa2 + 1; e < tpa2 + 4; e++) {
			html += '<li>' + e + '</li>'
		};
	};
	html += '<li>下一页</li>'
	html += '<li style="border: none !important;" tpa=' + tp + ' >尾页</li>'
	$(".page_right_xk2").html(html);
	$(".page_right_xk2 li").click(function() {
		var tpa_end = $(".page_right_xk2 li[tpa]").attr("tpa");
		if($(this).text() == "首页") {
			tpa2 = 1;
		} else if($(this).text() == "尾页") {
			tpa2 = tpa_end;
		} else if($(this).text() == "上一页" && tpa2 != 1) {
			tpa2 = tpa2 - 1;
		} else if($(this).text() == "下一页" && tpa2 != tpa_end) {
			tpa2 = (tpa2 + 1);
		} else if($(this).text() != "下一页" && $(this).text() != "上一页" && $(this).text() != "首页" && $(this).text() != "尾页") {
			tpa2 = Number($(this).text());
		}
		functionname();
	});
}
