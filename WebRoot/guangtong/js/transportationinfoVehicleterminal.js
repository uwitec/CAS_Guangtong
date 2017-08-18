var thispopup = {
	"编辑":"popupEditvehicleterminal.html",
	"删除":"deleteconfirm.html",
	"+增加":"popupAddvehicleterminal.html",
	"查询":"",
	"sider":"popupDetailsvehicleterminal.html"
};
$(".gt_wl_right a.button").click(function() {
	$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
});
$(".gt_wl_right .list_xk tbody tr").dblclick(function(){
	$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
});
