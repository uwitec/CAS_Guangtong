tpa = 1;

//根据id查询一条信息
function sCustomerInfoById(this_id) {
	$.ajax(config.url + "customer/sCustomerInfoById", {
		data: {
			"id": this_id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			$(".poptable2_xk_zyl tbody tr td span").text(data.obj[0].id);
			$(".poptable2_xk_zyl tbody tr:eq(1) td input").val(data.obj[0].cName);
			$(".poptable2_xk_zyl tbody tr:eq(2) td input").val(data.obj[0].contactName);
			$(".poptable2_xk_zyl tbody tr:eq(3) td input").val(data.obj[0].tel);
			$(".poptable2_xk_zyl tbody tr:eq(4) td input").val(data.obj[0].addr);
			$(".poptable2_xk_zyl tbody tr:eq(5) td input").val(data.obj[0].email);
			$(".poptable2_xk_zyl tbody tr:eq(6) td input").val(data.obj[0].qqAccount);
			$(".poptable2_xk_zyl tbody tr:eq(7) td input").val(data.obj[0].wechatAccount);
			uCustomerInfo(this_id);
		}
	});
};

/*更新*/
function uCustomerInfo(this_id) {
	$(".popcomfirm_xk .save").click(function() {
		var data_json = {
			"id": this_id,
			"cname": $(".poptable2_xk_zyl tbody tr:eq(1)  td input").val(),
			"contactname": $(".poptable2_xk_zyl tbody tr:eq(2)  td input").val(),
			"tel": $(".poptable2_xk_zyl tbody tr:eq(3)  td input").val(),
			"addr": $(".poptable2_xk_zyl tbody tr:eq(4)  td input").val(),
			"email": $(".poptable2_xk_zyl tbody tr:eq(5)  td input").val(),
			"qqaccount": $(".poptable2_xk_zyl tbody tr:eq(6)  td input").val(),
			"wechataccount": $(".poptable2_xk_zyl tbody tr:eq(7)  td input").val(),
		}
		$.ajax(config.url + "customer/uCustomerInfo", {
			data: data_json,
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(data.success);
				alert(data.msg);

			}
		});
		$("popwindow_xk .popup_xk").html("");
		$(".popwindow_xk").css("visibility", "hidden");
		getCustomerInfosByPage();
	});

}
//双击弹窗
function sCustomerInfoById_dblclick(this_id) {
	$.ajax(config.url + "customer/sCustomerInfoById", {
		data: {
			"id": this_id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			$(".poptable2_xk_zyl ul li:eq(0) span").text(data.obj[0].id);
			$(".poptable2_xk_zyl ul li:eq(1) span").text(data.obj[0].cName);
			$(".poptable2_xk_zyl ul li:eq(2) span").text(data.obj[0].cTypeName);
			$(".poptable2_xk_zyl ul li:eq(3) span").text(data.obj[0].contactName);
			$(".poptable2_xk_zyl ul li:eq(4) span").text(data.obj[0].tel);
			$(".poptable2_xk_zyl ul li:eq(5) span").text(data.obj[0].addr);
			$(".poptable2_xk_zyl ul li:eq(6) span").text(data.obj[0].email);
			$(".poptable2_xk_zyl ul li:eq(7) span").text(data.obj[0].qqAccount);
			$(".poptable2_xk_zyl ul li:eq(8) span").text(data.obj[0].wechatAccount);
			$(".poptable2_xk_zyl ul li:eq(9) span").text(data.obj[0].createTime);
		}
	});
}

//查询全部
function getCustomerInfosByPage() {
	$.ajax(config.url + "customer/getCustomerInfosByPage", {
		data: {
			"currentPage": tpa,
			"pageCount": 10,
			"id":$(".list_xk thead input[name='id']").val(),
			"cname":$(".list_xk thead input[name='cname']").val(),
			"ctypename":$(".list_xk thead input[name='ctypename']").val(),
			"contactname":$(".list_xk thead input[name='contactname']").val(),
			"tel":$(".list_xk thead input[name='tel']").val(),
			"addr":$(".list_xk thead input[name='addr']").val(),
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				data.obj = data.t;

				$(".list_xk tfoot tr td").html("第" + data.obj.currentPage + "页 共" + data.obj.tp + "页 共" + data.obj.totalCount + "条")
				html = "";
				for(var i = 0; i < data.obj.beanList.length; i++) {
					html += '<tr zyl_id="' + data.obj.beanList[i].id + '">';
					html += '<td>' + (i + 1) + '</td>';
					html += '<td>' + data.obj.beanList[i].id + '</td>';
					html += '<td>' + data.obj.beanList[i].cname + '</td>';
					html += '<td>' + data.obj.beanList[i].ctypename + '</td>';
					/*if(data.obj.beanList[i].ctypename == 1) {
						html += '<td>后台添加</td>';
					} else {
						html += '<td>用户端注册</td>';
					}*/
					if(data.obj.beanList[i].contactname == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].contactname + '</td>';
					}
					if(data.obj.beanList[i].tel == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].tel + '</td>';
					}
					if(data.obj.beanList[i].addr == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].addr + '</td>';
					}
					html += '<td>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">编辑</a>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">删除</a>';
					html += '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"编辑": "popupEditcustomer.html",
					"删除": "transportationinfoCustomer_del.html",
					"+增加": "popupAddcustomer.html",
					"查询": "",
					"导入": "importcustomer.html",
					"sider": "popupDetailscustomer.html",
					"各级客户地理分析":"popupChart.html"
				};
				$(".gt_wl_right a.button").unbind('click').bind('click', function() {
					char_wd = {
							"charType": $(this).attr("chartype"),
							"charName": $(this).attr("charname"),
						}
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").attr("this_id", this_id);
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
					if($(this).text() == "编辑") {
						sCustomerInfoById(this_id);
					}
				});
				$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
					sCustomerInfoById_dblclick(this_id);
				});

				fenye_lv(data.obj.tp, function() {
					getCustomerInfosByPage();
				})
			}
		}
	});
};

//导出
function getCustomersExcel() {
	$(".listtitle_xk ").on("click",".customersExcel",function(){
		location.href = config.url + "customer/getCustomersExcel";
	})

};
//导出模版
function customer_exportTemplate_zyl(){
	$(".listtitle_xk ").on('click',".template",function(){
		location.href = config.url + "customer/exportTemplate";
	})
};
customer_exportTemplate_zyl();
getCustomersExcel();
if($(".list_xk tbody tr").length == 1) {
	getCustomerInfosByPage()
};

//模糊搜索
function customerSearch(){
	$(".list_xk thead input").bind('input propertychange',function(){
		getCustomerInfosByPage();
	})
};
customerSearch();