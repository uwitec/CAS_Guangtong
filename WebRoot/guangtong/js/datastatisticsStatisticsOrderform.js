var thispopup = {
	"查看统计图":"popupChart.html"
};
$(".gt_wl_right a.button").click(function() {
	$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
});
$(".gt_wl_right .list_xk tbody tr").dblclick(function(){
	$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
});
