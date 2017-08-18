var thispopup = {
	"编辑":"popupEditvehicletype.html",
	"删除":"deleteconfirm.html",
	"+增加":"popupAddvehicletype.html",
	"查询":"",
	"sider":"popupDetailsvehicletype.html"
};
$(".gt_wl_right a.button").click(function() {
	$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
});
$(".gt_wl_right .list_xk tbody tr").dblclick(function(){
	$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
});
