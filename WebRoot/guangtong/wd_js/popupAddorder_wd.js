$("tfoot .popcomfirm_xk a:eq(0)").click(function() {
	addOrder();
})

function addOrder() {
	if($("input[name='customerId']").val() == "") {
		alert("请填写正确的客户信息！");
		return;
	};
	if($("input[name='customerName']").val() == "") {
		alert("您的客户名称未填写！");
		return;
	}
	if($("input[name='sCompany']").val() == "") {
		alert("您的发货单位未填写！");
		return;
	}
	if($("input[name='deliver']").val() == "") {
		alert("您的发货人信息未填写！");
		return;
	}
	if($("input[name='deliverTel']").val() == "") {
		alert("您的发货手机号未填写！");
		return;
	}
	var deliverTel = $("input[name='deliverTel']").val();
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	if(!myreg.test(deliverTel)) {
		alert('请输入有效的手机号码！');
		return false;
	}
	/*if($("input[name='deliverAddr']").val() == ""){
		alert("您的发货地址未填写！");
		return;
	}*/
	if($("input[name='rCompany']").val() == "") {
		alert("您的收货单位未填写！");
		return;
	}
	if($("input[name='receipt']").val() == "") {
		alert("您的收货人姓名未填写！");
		return;
	}
	if($("input[name='receiptTel']").val() == "") {
		alert("您的收货人手机号未填写！");
		return;
	}
	var receiptTel = $("input[name='deliverTel']").val();
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	if(!myreg.test(receiptTel)) {
		alert('请输入有效的手机号码！');
		return false;
	}
	/*if($("input[name='receiptAddr']").val() == ""){
		alert("您的收货人地址未填写！");
		return;
	}*/
	if($("input[name='description']").val() == "") {
		alert("您的货物描述未填写！");
		return;
	}
	if($("input[name='length']").val() == "") {
		alert("您的长度未填写！");
		return;
	}
	if($("input[name='wide']").val() == "") {
		alert("您的宽度未填写！");
		return;
	}
	if($("input[name='tall']").val() == "") {
		alert("您的高度未填写！");
		return;
	}
	if($("input[name='volume']").val() == "") {
		alert("请填写体积！");
		return;
	}
	if($("input[name='weight']").val() == "") {
		alert("您的重量未填写！");
		return;
	}
	if($("input[name='insuranceFee']").val() == "") {
		alert("请填写保价费！");
		return;
	}
	var insuranceFee = $("input[name='insuranceFee']").val();
	var myreg = /^[+]{0,1}(\d+)$/;
	if(!myreg.test(insuranceFee)) {
		alert('请输入正确的保价费金额！');
		return false;
	}
	if($("input[name='floorFee']").val() == "") {
		alert("您的上楼费未填写！");
		return;
	}
	var floorFee = $("input[name='floorFee']").val();
	var myreg = /^[+]{0,1}(\d+)$/;
	if(!myreg.test(floorFee)) {
		alert('请输入正确的上楼费金额！');
		return false;
	}
	if($("input[name='handlingFee']").val() == "") {
		alert("您的装卸费未填写！");
		return;
	}
	var handlingFee = $("input[name='handlingFee']").val();
	var myreg = /^[+]{0,1}(\d+)$/;
	if(!myreg.test(handlingFee)) {
		alert('请输入正确的装卸费金额！');
		return false;
	}
	if($("input[name='freightFee']").val() == "") {
		alert("您的运输费未填写！");
		return;
	}
	var freightFee = $("input[name='freightFee']").val();
	var myreg = /^[+]{0,1}(\d+)$/;
	if(!myreg.test(freightFee)) {
		alert('请输入正确的运输费金额！');
		return false;
	}
	if($("input[name='totalMoney']").val() == "") {
		alert("您的费用合计未填写！");
		return;
	}
	var val = $('input:radio[name="payMethod"]:checked').val();
	if(val == null) {
		alert("您的付款方式未选中!");
		return;
	}
	if($("input[name='callTime']").val() == "") {
		alert("您的取货时间为空！");
		return;
	}
	$.ajax(config.url + "order/addOrder", {
		data: $("#add_order").serialize(),
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				alert("新增订单成功！");
				$(".pophead_xk img[src='img/close.png']").click();
			} else {
				alert("新增订单失败！");
			}
		}
	});
}
$(".popup_xk").on('input', "input[name='customerName']", function() {
	$("datalist#customerList").remove();
	$.ajax(config.url + "order/getCustomerLikeCname", {
		data: {
			"cName": $(this).val()
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				if(data.obj.length > 0) {
					html = "";
					html += "<datalist id='customerList'>";
					for(one in data.obj) {
						html += "<option value='" + data.obj[one].cname + "' cusid='" + data.obj[one].id + "'>"
					}
					html += "</datalist>";
					$("input[name='customerName']").after(html);
				}
			}
		}
	});
});
$("input[name='customerName']").change(function() {
	var customerName = $(this).val();
	$("datalist#customerList option").each(function() {
		if($(this).val() == customerName) {
			$("input[name='customerId']").val($(this).attr("cusid"));
		}
	})
})

$("tfoot .popcomfirm_xk a:eq(1)").click(function() {
	var thisa = $(this);
	$.ajax(config.url + "order/addOrder", {
		data: $("#add_order").serialize(),
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			//console.log(data);
			if(data.success) {
				var this_di1 = data.t.ordernum;
				var this_id1 = data.t.id;
				thispopup = {
					"完成并指派": "popupAssigndriver.html",
				};
				//var this_di = $(this).attr("zyl_di");
				$(".popwindow_xk .popup_xk").attr("this_di", this_di1);
				$(".popwindow_xk .popup_xk").html(popup(thispopup[$(thisa).text()]));
			//	$(".popwindow_xk .popup_xk").append("<div  zyl_di=" + this_di1 + " zyl_id=" + this_id1 + " ></div>");
			}
		}
	});
})