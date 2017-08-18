$("tfoot .popcomfirm_xk a:eq(0)").click(function(){
	rebuildOrder();
});
function rebuildOrder(){
	if($("input[name='customerId']").val() == ""){
		alert("请填写正确的客户信息！");
		return;
	};
	if($("input[name='customerName']").val() == ""){
		alert("您的客户名称未填写！");
		return;
	}
	if($("input[name='sCompany']").val() == ""){
		alert("您的发货单位未填写！");
		return;
	}
	if($("input[name='deliver']").val() == ""){
		alert("您的发货人信息未填写！");
		return;
	}	
	if($("input[name='deliverTel']").val() == ""){
		alert("您的发货手机号未填写！");
		return;
	}
	var deliverTel = $("input[name='deliverTel']").val();
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
    if(!myreg.test(deliverTel)) 
    { 
        alert('请输入有效的手机号码！'); 
        return false; 
    } 
	/*if($("input[name='deliverAddr']").val() == ""){
		alert("您的发货地址未填写！");
		return;
	}*/
	if($("input[name='rCompany']").val() == ""){
		alert("您的收货单位未填写！");
		return;
	}
	if($("input[name='receipt']").val() == ""){
		alert("您的收货人姓名未填写！");
		return;
	}
	if($("input[name='receiptTel']").val() == ""){
		alert("您的收货人手机号未填写！");
		return;
	}
	var receiptTel = $("input[name='deliverTel']").val();
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
    if(!myreg.test(receiptTel)) 
    { 
        alert('请输入有效的手机号码！'); 
        return false; 
    } 
	/*if($("input[name='receiptAddr']").val() == ""){
		alert("您的收货人地址未填写！");
		return;
	}*/
	if($("input[name='description']").val() == ""){
		alert("您的货物描述未填写！");
		return;
	}
	if($("input[name='length']").val() == ""){
		alert("您的长度未填写！");
		return;
	}
	if($("input[name='wide']").val() == ""){
		alert("您的宽度未填写！");
		return;
	}
	if($("input[name='tall']").val() == ""){
		alert("您的高度未填写！");
		return;
	}
	if($("input[name='volume']").val() == ""){
		alert("请填写体积！");
		return;
	}
	if($("input[name='weight']").val() == ""){
		alert("您的重量未填写！");
		return;
	}
	if($("input[name='insuranceFee']").val() == ""){
		alert("请填写保价费！");
		return;
	}
	var insuranceFee= $("input[name='insuranceFee']").val();
	var myreg = /^[+]{0,1}(\d+)$/;
	if(!myreg.test(insuranceFee)) 
    { 
        alert('请输入正确的保价费金额！'); 
        return false; 
    } 
	if($("input[name='floorFee']").val() == ""){
		alert("您的上楼费未填写！");
		return;
	}
	var floorFee= $("input[name='floorFee']").val();
	var myreg = /^[+]{0,1}(\d+)$/;
	if(!myreg.test(floorFee)) 
    { 
        alert('请输入正确的上楼费金额！'); 
        return false; 
    } 
	if($("input[name='handlingFee']").val() == ""){
		alert("您的装卸费未填写！");
		return;
	}
	var handlingFee= $("input[name='handlingFee']").val();
	var myreg = /^[+]{0,1}(\d+)$/;
	if(!myreg.test(handlingFee)) 
    { 
        alert('请输入正确的装卸费金额！'); 
        return false; 
    } 
	if($("input[name='freightFee']").val() == ""){
		alert("您的运输费未填写！");
		return;
	}
	var freightFee= $("input[name='freightFee']").val();
	var myreg = /^[+]{0,1}(\d+)$/;
	if(!myreg.test(freightFee)) 
    { 
        alert('请输入正确的运输费金额！'); 
        return false; 
    } 
	if($("input[name='totalMoney']").val() == ""){
		alert("您的费用合计未填写！");
		return;
	}
	 var val=$('input:radio[name="payMethod"]:checked').val();
     if(val==null){
         alert("您的付款方式未选中!");
         return;
     }
	if($("input[name='callTime']").val() == ""){
		alert("您的取货时间为空！");
		return;
	}
	$.ajax(config.url + "order/rebuildsOrder", {
		data: $("#rebuild_order").serialize(),
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success){
				alert("重建订单成功！");
				$(".pophead_xk img[src='img/close.png']").click();
				exceptionsOrderList();
			}else{
				alert("重建订单失败！");
			}
		}
	});
}
function orderDetail(){
	$.ajax(config.url + "order/getOrderById", {
		data:{
			"id":$(".popup_xk").attr("this_id")
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			//console.log(data);
			if(data.success){
				$("#rebuild_order tbody tr:eq(0) td span").html(data.t.orderNum);
				$("input[name='ordernum']").val(data.t.orderNum);
				$("input[name='customerid']").val(data.t.customerId);
				$("input[name='id']").val(data.t.id);
				$("input[name='scompany']").val(data.t.sCompany);
				$("input[name='deliver']").val(data.t.deliver);
				$("input[name='delivertel']").val(data.t.deliverTel);
				$("input[name='deliveraddr']").val(data.t.deliverAddr);
				$("input[name='rcompany']").val(data.t.rCompany);
				$("input[name='receipt']").val(data.t.receipt);
				$("input[name='receipttel']").val(data.t.receiptTel);
				$("input[name='receiptaddr']").val(data.t.receiptAddr);
				$("input[name='description']").val(data.t.description);
				$("input[name='length']").val(data.t.length);
				$("input[name='wide']").val(data.t.wide);
				$("input[name='tall']").val(data.t.tall);
				$("input[name='volume']").val(data.t.volume);
				$("input[name='weight']").val(data.t.weight);
				$("input[name='insurancefee']").val(data.t.insuranceFee);
				$("input[name='floorfee']").val(data.t.floorFee);
				$("input[name='handlingfee']").val(data.t.handlingFee);
				$("input[name='freightfee']").val(data.t.freightFee);
				$("input[name='totalmoney']").val(data.t.totalMoney);
				$("input[name='paymethod']").each(function(){
					if($(this).val() == data.t.payMethod){
						$(this).attr("checked","checked");
					}
				});
				$("input[name='callTime']").val(data.t.callTime);
			}
		}
	});
};
orderDetail();