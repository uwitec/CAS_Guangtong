var thispopup = {
	"查看统计图":"popupChart.html"
};
$(".gt_wl_right a.button").click(function() {
	var vId;
	for(var i = 0; i < $("#carList img").length; i++) {
		if($("#carList img:eq(" + i + ")").attr("src") == "img/cherked.jpg") {
			if($("#carList img:eq(" + i + ")").parent("b").attr("vId") != undefined && $("#carList img:eq(" + i + ")").parent("b").attr("vId") != null) {
				vId = $("#carList img:eq(" + i + ")").parent("b").attr("vId");
			}
		}
	}
	char_wd = {
		"charType": $(this).attr("chartype"),
		"charName": $(this).attr("charname"),
		"startTime": $("input[placeholder='开始时间']").val(),
		"endTime": $("input[placeholder='结束时间']").val(),
		"type" :$(".select").val(),
		"simNo": vId
	}
	$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
});
$(".gt_wl_right .list_xk tbody tr").dblclick(function(){
	$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
});
