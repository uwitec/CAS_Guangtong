var thispopup = {
	"编辑": "popupEditorder.html",
	"删除": "deleteconfirm.html",
	"+增加": "popupAddorder.html",
	"查询": "",
	"sider": "popupDetailsorder.html",
	"扣款": "popupReductmoney.html",
	"异常": "popupAbnormal.html",
	"取消": "cancelconfirm.html",
	"指派": "popupAssigndriver.html",
	"重建": "popupRebuildorder.html"
};
$(".gt_wl_right a.button").click(function() {
		$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
});
$(".gt_wl_right .list_xk tbody tr").dblclick(function() {
	$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
});