var thispopup = {
	"编辑": "popupEditenterprise.html",
	"删除": "deleteconfirm.html",
	"+增加": "popupAddenterprise.html",
	"冻结": "freezeconfirm.html",
	"批量冻结": "freezeconfirm.html",
	"解冻": "unfreezeconfirm.html",
	"批量解冻": "unfreezeconfirm.html",
	"查询": "",
	"sider": "popupDetailsenterprise.html"
};
$(".gt_wl_right a.button").click(function() {
	$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
});
$(".gt_wl_right .list_xk tbody tr").dblclick(function() {
	$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
});