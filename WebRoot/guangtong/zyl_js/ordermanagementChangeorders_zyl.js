tpa = 1;
//双击弹窗
function reassignOrderList_dblclick(e){
	for(var i=0;i<$(e).children("td").length;i++){
			$(".popup_xk ul li:eq("+i+") span").html($(e).children("td:eq("+(i*1+1*1)+")").html())
	}
}
// 查询全部
function reassignOrderList() {
	$.ajax(config.url + "order/reassignOrderList", {
		data : {
			"currentPage" : tpa,
			"pageCount" : 10,
			"ordernum" : $(".list_xk thead input[name='ordernum']").val(),
			"title" : $(".list_xk thead input[name='title']").val(),
			"totalMoney" : $(".list_xk thead input[name='totalMoney']").val(),
			"num" : $(".list_xk thead input[name='num']").val(),
			"deliverAddr" : $(".list_xk thead input[name='deliverAddr']").val(),
			"receiptAddr" : $(".list_xk thead input[name='receiptAddr']").val(),
			"reassignTime" :  $(".list_xk thead input[name='reassignTime']").val(),
		},
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
			if (data.success) {
				$(".list_xk tfoot tr td").html("第" + data.t.currentPage + "页 共" + data.t.tp + "页 共" + data.t.totalCount + "条")
				html = "";
				for (var i = 0; i < data.t.beanList.length; i++) {
					html += '<tr zyl_id="' + data.t.beanList[i].id + '">';
					html += '<td>' + (i + 1) + '</td>';
					if(data.t.beanList[i].orderNum == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.t.beanList[i].orderNum + '</td>';
					};
					
					if (data.t.beanList[i].title == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].title + '</td>';
					};
					
					if (data.t.beanList[i].totalMoney == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].totalMoney + '</td>';
					};
					
					if (data.t.beanList[i].num == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].num + '</td>';
					};
					
					if (data.t.beanList[i].deliverAddr == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].deliverAddr + '</td>';
					};
					if (data.t.beanList[i].receiptAddr == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].receiptAddr + '</td>';
					};
					if (data.t.beanList[i].reassignTimeFrom == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].reassignTimeFrom + '</td>';
					};
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"sider": "popupDetailsManageorders.html",
				};
				$(".gt_wl_right a.button").unbind('click').bind('click', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").attr("this_id", this_id);
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
					/*if ($(this).text() == "编辑") {
						//getCooperationByAdmin(this_id);
					}*/ 
				});
				$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
						reassignOrderList_dblclick($(this));
				});
				fenye_lv(data.t.tp, function() {
					reassignOrderList();
				});
			}
		}
	});
};


//模糊搜索
function reassignOrderList_fuzzyQuery(){
	$(".list_xk thead input").bind('input propertychange',function(){
		reassignOrderList();
	});
	$(".list_xk thead input[name='reassignTime']").blur(function(){
		reassignOrderList();
	});
};
reassignOrderList_fuzzyQuery();
if ($(".list_xk tbody tr").length == 0) {
	reassignOrderList();
};