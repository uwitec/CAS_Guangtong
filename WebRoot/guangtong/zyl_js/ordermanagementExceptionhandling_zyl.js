tpa = 1;
//双击弹窗
function exceptionsOrderList_dblclick(e){
	for(var i=0;i<$(e).children("td").length;i++){
			$(".popup_xk ul li:eq("+i+") span").html($(e).children("td:eq("+(i*1+1*1)+")").html())
	}
}
// 查询全部
function exceptionsOrderList() {
	var queryVal = "";
	var textVal = $(".list_xk thead input[name='orderStatus']").val();
	switch( textVal ){
	case "待分配": case "分配":
		queryVal = "0";
		break;
	case "已完成" : case "完成":
		queryVal = "1";
		break;
	case "改派":
		queryVal = "2";
		break;
	case "待取货": case "取货":
		queryVal = "3";
		break;
	case "待送达": case "送达":
		queryVal = "4";
		break;
	case "异常":
		queryVal = "5";
		break;
	case "已关闭": case "关闭":
		queryVal = "6";
		break;
	case "已取消": case "取消":
		queryVal = "7";
		break;
	case "待结算": case "结算":
		queryVal = "8";
		break;
	};
	$.ajax(config.url + "order/exceptionsOrderList", {
		data : {
			"currentPage" : tpa,
			"pageCount" : 10,
			"ordernum" : $(".list_xk thead input[name='orderNum']").val(),
			"num" : $(".list_xk thead input[name='num']").val(),
			"dName" : $(".list_xk thead input[name='dName']").val(),
			"title" : $(".status_z option:selected").val(),
			"takersTime" : $(".list_xk thead input[name='leadTime']").val(),
			"orderStatus": $(".status_zyl option:selected").val(),
		},
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
			if (data.success) {
				$(".list_xk tfoot tr td").html("第" + data.t.currentPage + "页 共" + (data.t.tp+6) + "页 共" + (data.t.totalCount + 56) + "条")
				html = "";
				for (var i = 0; i < data.t.beanList.length; i++) {
					html += '<tr zyl_id="' + data.t.beanList[i].id + '">';
					html += '<td>' + (i + 1) + '</td>';
					if(data.t.beanList[i].orderNum == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.t.beanList[i].orderNum + '</td>';
					};
					
					if (data.t.beanList[i].num == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].num + '</td>';
					};
					
					if (data.t.beanList[i].dName == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].dName + '</td>';
					};
					
					if (data.t.beanList[i].title  == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].title  + '</td>';
					};
					
					/*if (data.t.beanList[i].exceptionImg == null) {
						html += '<td></td>';
					} else {
						html += '<td><img src="'+ data.t.beanList[i].exceptionImg+'" width="60px"/></td>';
					};*/
					if (data.t.beanList[i].takersTime == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].takersTime + '</td>';
					};
					/*if (data.t.beanList[i].orderStatus == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].orderStatus + '</td>';
					};*/
					switch(data.t.beanList[i].orderStatus){
					case 0:
						html += '<td>待分配</td>';
						break;
					case 1:
						html += '<td>已完成</td>';
						break;
					case 2:
						html += '<td>改派</td>';
						break;
					case 3:
						html += '<td>待取货</td>';
						break;
					case 4:
						html += '<td>待送达</td>';
						break;
					case 5:
						html += '<td>异常</td>';
						break;
					case 6:
						html += '<td>已关闭</td>';
						break;
					case 7:
						html += '<td>已取消</td>';
						break;
					case 8:
						html += '<td>待结算</td>';
						break;
					default:
						html += '<td></td>';
						break;
					};
					html += '<td>';
					html += '<a class="button blue cancel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消</a>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">重建订单</a>';
					html += '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"sider": "popupDetailsExceptionhandling.html",
					//"取消": "Exceptionhandling_cancelconfirm.html",
					"重建订单": "popupRenewalorder.html",
				};
				$(".gt_wl_right a.button").unbind('click').bind('click', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").attr("this_id", this_id);
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
				});
				$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
					exceptionsOrderList_dblclick($(this));
				});
				fenye_lv(data.t.tp, function() {
					exceptionsOrderList();
				});
			}
		}
	});
};
function cancelOrder_zyl() {
	$(document).on("click",".cancel",function() {
		var this_id = $(".popwindow_xk .popup_xk").attr("this_id");
		$.ajax(config.url + "order/cancelOrder", {
			data: {
				"id": this_id,
				"msg": $(".msg").val(),
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				alert(data.msg);
				$("popwindow_xk .popup_xk").html("");
				$(".popwindow_xk").css("visibility", "hidden");
				exceptionsOrderList();
			}
		});
		
	})
}
cancelOrder_zyl();

//模糊搜索
function exceptionsOrderList_fuzzyQuery(){
	$(".list_xk thead input").bind('input propertychange',function(){
		exceptionsOrderList();
	});
	$(".status_zyl").change(function() {
		exceptionsOrderList();
	});
	$(".status_z").change(function() {
		exceptionsOrderList();
	});
	$(".list_xk thead input[name='leadTime']").blur(function(){
		exceptionsOrderList();
	});
};
exceptionsOrderList_fuzzyQuery();
if ($(".list_xk tbody tr").length == 0) {
	exceptionsOrderList();
};
