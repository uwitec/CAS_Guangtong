tpa = 1;

//双击弹窗
function getOrderById_dblclick(id) {
	$.ajax(config.url + "order/getOrderById", {
		data: {
			"id": id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				$(".poptable2_xk_zyl ul  li:eq(0) span").text(data.t.orderNum);
				switch(data.t.orderStatus) {
					case "0":
						$(".poptable2_xk_zyl ul  li:eq(1) span").text("待分配");
						break;
					case "1":
						$(".poptable2_xk_zyl ul  li:eq(1) span").text("已完成");
						break;
					case "2":
						$(".poptable2_xk_zyl ul  li:eq(1) span").text("改派");
						break;
					case "3":
						$(".poptable2_xk_zyl ul  li:eq(1) span").text("待取货");
						break;
					case "4":
						$(".poptable2_xk_zyl ul  li:eq(1) span").text("待送达");
						break;
					case "5":
						$(".poptable2_xk_zyl ul  li:eq(1) span").text("异常");
						break;
					case "6":
						$(".poptable2_xk_zyl ul  li:eq(1) span").text("已关闭");
						break;
					case "7":
						$(".poptable2_xk_zyl ul  li:eq(1) span").text("已取消");
						break;
					case "8":
						$(".poptable2_xk_zyl ul  li:eq(1) span").text("待结算");
						break;
					default:
						break;
				};
				if(data.t.deliver == null){
					$(".poptable2_xk_zyl ul  li:eq(2) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(2) span").text(data.t.deliver);
				};
				if(data.t.sCompany == null){
					$(".poptable2_xk_zyl ul  li:eq(3) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(3) span").text(data.t.sCompany);
				};
				if(data.t.rCompany == null){
					$(".poptable2_xk_zyl ul  li:eq(4) span").text();
				}else{
					$(".poptable2_xk_zyl ul  li:eq(4) span").text(data.t.rCompany);
				};
				if(data.t.num == null) {
					$(".poptable2_xk_zyl ul  li:eq(5) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(5) span").text(data.t.num);
				};
				if(data.t.dName == null) {
					$(".poptable2_xk_zyl ul  li:eq(6) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(6) span").text(data.t.dName);
				};
				if(data.t.description == null){
					$(".poptable2_xk_zyl ul  li:eq(7) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(7) span").text(data.t.description);
				};
				$(".poptable2_xk_zyl ul  li:eq(8) span").text(data.t.length + "*" + data.t.wide + "*" + data.t.tall);
				if(data.t.volume == null){
					$(".poptable2_xk_zyl ul  li:eq(9) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(9) span").text(data.t.volume);
				};
				if(data.t.weight == null){
					$(".poptable2_xk_zyl ul  li:eq(10) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(10) span").text(data.t.weight);
				};
				if(data.t.goodsNum == null){
					$(".poptable2_xk_zyl ul  li:eq(11) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(11) span").text(data.t.goodsNum);
				};
				if(data.t.insuranceFee == null) {
					$(".poptable2_xk_zyl ul  li:eq(12) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(12) span").text(data.t.insuranceFee);
				};
				if(data.t.floorFee == null) {
					$(".poptable2_xk_zyl ul  li:eq(13) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(13) span").text(data.t.floorFee);
				};
				if(data.t.handlingFee == null) {
					$(".poptable2_xk_zyl ul  li:eq(14) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(14) span").text(data.t.handlingFee);
				};
				if(data.t.freightFee == null) {
					$(".poptable2_xk_zyl ul  li:eq(15) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(15) span").text(data.t.freightFee);
				};
				if(data.t.totalMoney == null) {
					$(".poptable2_xk_zyl ul  li:eq(16) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(16) span").text(data.t.totalMoney);
				};
				if(data.t.payMethod == 1){
					$(".poptable2_xk_zyl ul  li:eq(17) span").text("现金支付");
				}else if(data.t.payMethod == 2){
					$(".poptable2_xk_zyl ul  li:eq(17) span").text("在线支付");
				}else if(data.t.payMethod == 3){
					$(".poptable2_xk_zyl ul  li:eq(17) span").text("协议结算");
				};
				if(data.t.payTime == null) {
					$(".poptable2_xk_zyl ul  li:eq(18) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(18) span").text(data.t.payTime);
				};
				if(data.t.payment == null) {
					$(".poptable2_xk_zyl ul  li:eq(19) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(19) span").text(data.t.payment);
				};
				if(data.t.addRequir == null){
					$(".poptable2_xk_zyl ul  li:eq(20) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(20) span").text(data.t.addRequir);
				};
				if(data.t.exceptionReason == null){
					$(".poptable2_xk_zyl ul  li:eq(21) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(21) span").text(data.t.exceptionReason);
				};
				$(".poptable2_xk_zyl ul  li:eq(22) span").append('<img src="' + config.url + data.t.exceptionImg + '" width="60px" alt=""/>');
				if(data.t.createTime == null){
					$(".poptable2_xk_zyl ul  li:eq(23) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(23) span").text(data.t.createTime);
				};
				if(data.t.takersTime == null){
					$(".poptable2_xk_zyl ul  li:eq(24) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(24) span").text(data.t.takersTime);
				};
				if(data.t.pickupTime == null){
					$(".poptable2_xk_zyl ul  li:eq(25) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(25) span").text(data.t.pickupTime);
				};
				if(data.t.leadTime == null){
					$(".poptable2_xk_zyl ul  li:eq(26) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(26) span").text(data.t.leadTime);
				};
				if(data.t.reassignTime == null){
					$(".poptable2_xk_zyl ul  li:eq(27) span").text("");
				}else{
					$(".poptable2_xk_zyl ul  li:eq(27) span").text(data.t.reassignTime);
				};
				if(data.t.hasGrade == 0) {
					$(".poptable2_xk_zyl ul  li:eq(28) span").text("未评分");
				} else if(data.t.hasGrade == 1) {
					$(".poptable2_xk_zyl ul  li:eq(28) span").text("客户已评分");
				} else if(data.t.hasGrade == 2) {
					$(".poptable2_xk_zyl ul  li:eq(28) span").text("超时自动评分");
				};
				if(data.t.assessment == null) {
					$(".poptable2_xk_zyl ul  li:eq(29) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(29) span").text(data.t.assessment);
				};
				if(data.t.assessTime == null) {
					$(".poptable2_xk_zyl ul  li:eq(30) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(30) span").text(data.t.assessTime);
				};
				if(data.t.receipt == null) {
					$(".poptable2_xk_zyl ul  li:eq(31) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(31) span").text(data.t.receipt);
				}
				if(data.t.orderFrom == null) {
					$(".poptable2_xk_zyl ul  li:eq(32) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(32) span").text(data.t.orderFrom);
				}
			}
		}
	});
}

$(document).ready(function() {
	$("#orderStatus_zyl").change(function() {
		getIndex();
	})
})

// 查询全部
function getIndex() {
	var queryVal = "";
	var textVal = $(".list_xk thead input[name='orderStatus']").val();
	switch(textVal) {
		case "待分配":
		case "分配":
			queryVal = "0";
			break;
		case "已完成":
		case "完成":
			queryVal = "1";
			break;
		case "改派":
			queryVal = "2";
			break;
		case "待取货":
		case "取货":
			queryVal = "3";
			break;
		case "待送达":
		case "送达":
			queryVal = "4";
			break;
		case "异常":
			queryVal = "5";
			break;
		case "已关闭":
		case "关闭":
			queryVal = "6";
			break;
		case "已取消":
		case "取消":
			queryVal = "7";
			break;
		case "待结算":
		case "结算":
			queryVal = "8";
			break;
		default:
			break;
	};
	//if($("#orderStatus_zyl").focus()) {

	/*	data_json = {
			"currentPage": tpa,
			"pageCount": 10,
			//"type":$("#orderStatus_zyl option:selected").val(),
			"ordernum": $(".list_xk thead input[name='ordernum']").val(),
			"callTime": $(".list_xk thead input[name='callTime']").val(),
			"deliverAddr": $(".list_xk thead input[name='deliverAddr']").val(),
			"receiptAddr": $(".list_xk thead input[name='receiptAddr']").val(),
			"vehicleId": $(".list_xk thead input[name='vehicleId']").val(),
			"title": $(".list_xk thead input[name='title']").val(),
			"orderStatus": $("#orderStatus_zyl option:selected").val(),*/
	//}
	//} else if($(".list_xk thead input[name='orderStatus']").blur()) {
	data_json = {
		"currentPage": tpa,
		"pageCount": 10,
		"type": $("#orderStatus_zyl option:selected").val(),
		"ordernum": $(".list_xk thead input[name='ordernum_z']").val(),
		"callTime": $(".list_xk thead input[name='callTime_z']").val(),
		"deliverAddr": $(".list_xk thead input[name='deliverAddr_z']").val(),
		"receiptAddr": $(".list_xk thead input[name='receiptAddr_z']").val(),
		"vehicleId": $(".list_xk thead input[name='vehicleId_z']").val(),
		"orderStatus": $(".status_z option:selected").val(),
	}
	//};
	$.ajax(config.url + "order/index", {
		data: data_json,
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				$(".list_xk tfoot tr td").html("第" + data.t.currentPage + "页 共" + data.t.tp + "页 共" + data.t.totalCount + "条")
				html = "";
				for(var i = 0; i < data.t.beanList.length; i++) {
					html += '<tr zyl_id="' + data.t.beanList[i].id + '">';
					html += '<td>' + (i + 1) + '</td>';
					if(data.t.beanList[i].orderNum == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].orderNum + '</td>';
					};

					if(data.t.beanList[i].callTime == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].callTime + '</td>';
					};

					if(data.t.beanList[i].deliverAddr == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].deliverAddr + '</td>';
					};

					if(data.t.beanList[i].receiptAddr == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].receiptAddr + '</td>';
					};

					if(data.t.beanList[i].num == null) {
						//if(data.t.beanList[i].orderStatus == 1 || data.t.beanList[i].orderStatus == 7){
							html += '<td></td>';
						//}
					} else {
						html += '<td>' + data.t.beanList[i].num + '</td>';
					};
					/*if(data.t.beanList[i].title == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.t.beanList[i].title + '</td>';
					};*/
					switch(data.t.beanList[i].orderStatus) {
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
					
					if(data.t.beanList[i].orderStatus==0){
						//待分配
						if(data.t.beanList[i].orderFrom==1){
							//APP下单
							if(data.t.beanList[i].settle==0){
								//未支付
								html += '<td>';
								html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;"  zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
								html += '<a class="button blue cancel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消</a>';
								html += '</td>';
							}else{
								//已支付
								html += '<td>';
								html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;" zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">指派</a>';
								html += '</td>';
							}
						}else{
							//平台下单
							html += '<td>';
							html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;"  zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
							html += '<a class="button blue cancel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消</a>';
							html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;" zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">指派</a>';
							html += '</td>';
						}
					}else if(data.t.beanList[i].orderStatus==8){
						//待结算
						html += '<td>';
						html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;"  zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
						html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">异常</a>';
						html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">完成</a>';
						html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">扣款</a>';
						html += '</td>';
					}else if(data.t.beanList[i].orderStatus==1||data.t.beanList[i].orderStatus==7){
						//已取消  已完成
						html += '<td></td>';
					}else if(data.t.beanList[i].orderStatus==3){
						//待取货
						html += '<td>';
						html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;"  zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
						html += '<a class="button blue cancel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消</a>';
						html += '</td>';
					}else{
						//其它
						html += '<td>';
						html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;"  zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
						html += '<a class="button blue cancel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消</a>';
						html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;"  zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">指派</a>';
						html += '</td>';
					}
					/*
					switch(data.t.beanList[i].orderStatus) {
						case 0:
							html += '<td>';
							html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
							html += '<a class="button blue cancel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消</a>';
							html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;" zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">指派</a>';
							html += '</td>';
							break;
						case 1:
							html += '<td>';
							html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
							html += '</td>';
							break;
						case 2:
							html += '<td>';
							html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
							html += '<a class="button blue cancel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消</a>';
							html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;" zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">指派</a>';
							html += '</td>';
							break;
						case 3:
							html += '<td>';
							html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
							html += '<a class="button blue cancel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消</a>';
							html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;" zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">指派</a>';
							html += '</td>';
							break;
						case 4:
							html += '<td>';
							html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
							html += '<a class="button blue cancel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消</a>';
							html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;" zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">指派</a>';
							html += '</td>';
							break;
						case 5:
							html += '<td>';
							html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
							html += '<a class="button blue cancel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消</a>';
							html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;" zyl_di="'+data.t.beanList[i].orderNum+'" zyl_id="' + data.t.beanList[i].id + '">指派</a>';
							html += '</td>';
							break;
						case 6:
							html += '<td>';
							html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
							html += '<a class="button blue cancel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消</a>';
							html += '</td>';
							break;
						case 7:
							html += '<td>';
							html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
							html += '</td>';
							break;
						case 8:
							html += '<td>';
							html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
							html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">异常</a>';
							html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">完成</a>';
							html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">扣款</a>';
							html += '</td>';
							break;
						default:
							html += '<td></td>';
							break;
					};*/
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"编辑": "popupEditorder.html",
					"删除": "deleteconfirm.html",
					"+增加": "popupAddorder.html",
					"查询": "",
					"sider": "popupDetailsorder.html",
					"扣款": "popupReductmoney.html",
					"异常": "popupAbnormal.html",
					//"取消": "Manageorders_cancelconfirm.html",
					"指派": "popupAssigndriver.html",
					"重建": "popupRebuildorder.html",
					"订单交易额比例分析":"popupChart.html",
					"订单交易额数据统计":"popupChart.html"
				};
				$(".gt_wl_right a.button").unbind('click').bind('click', function() {
					char_wd = {
							"charType": $(this).attr("chartype"),
							"charName": $(this).attr("charname"),
						}
					var this_id = $(this).attr("zyl_id");
					var this_di = $(this).attr("zyl_di");
					$(".popwindow_xk .popup_xk").attr("this_id", this_id);
					$(".popwindow_xk .popup_xk").attr("this_di", this_di);
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
					$(".popwindow_xk .popup_xk").append("<div  zyl_di=" + this_di + " zyl_id=" + this_id + " ></div>");
					
				});
				$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
					getOrderById_dblclick(this_id);
				});
				fenye_lv(data.t.tp, function() {
					getIndex();
				});
			}
		}
	});
};


//取消
function cancelOrder() {
	$(document).on("click",".cancel",function() {
		var this_id = $(".popwindow_xk .popup_xk").attr("this_id");
		$.ajax(config.url + "order/cancelOrder", {
			data: {
				"id": this_id,
				//"msg": $(".msg").val(),
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				alert(data.msg);
				$("popwindow_xk .popup_xk").html("");
				$(".popwindow_xk").css("visibility", "hidden");
				getIndex();
			}
		});
		
	})
}
cancelOrder();
//模糊搜索
function fuzzyQuery() {
	$(".list_xk thead input").bind('input propertychange', function() {
		getIndex();
	});
	$(".status_z").change(function() {
		getIndex();
	});
	$(".list_xk thead input[name='callTime']").blur(function(){
		getIndex();
	});
};
fuzzyQuery();
if($(".list_xk tbody tr").length == 0) {
	getIndex();
};