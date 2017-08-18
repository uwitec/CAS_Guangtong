function showParam(){
	if(paramSet){
		if(paramSet.toolbar == 0){
			$("select[name='toolbar'] option").eq(1).attr("selected",true);
		};
		if(paramSet.mapcalloutlayer == 0){
			$("select[name='mapcalloutlayer'] option").eq(1).attr("selected",true);
		};
		if(paramSet.scale == 0){
			$("select[name='scale'] option").eq(1).attr("selected",true);
		};
		if(paramSet.maptoolbar == 0){
			$("select[name='maptoolbar'] option").eq(1).attr("selected",true);
		};
		if(paramSet.ruler == 0){
			$("select[name='ruler'] option").eq(1).attr("selected",true);
		};
		if(paramSet.mapcentercoordinates == 0){
			$("select[name='mapcentercoordinates'] option").eq(1).attr("selected",true);
		};
		if(paramSet.vehicleicon == 0){
			$("select[name='vehicleicon'] option").eq(1).attr("selected",true);
		};
		if(paramSet.generalinformation == 0){
			$("select[name='generalinformation'] option").eq(1).attr("selected",true);
		};
		if(paramSet.commandreplyinformation == 0){
			$("select[name='commandreplyinformation'] option").eq(1).attr("selected",true);
		};
		if(paramSet.vehicleinformation == 0){
			$("select[name='vehicleinformation'] option").eq(1).attr("selected",true);
		};
		if(paramSet.alarmlamp == 0){
			$("select[name='alarmlamp'] option").eq(1).attr("selected",true);
		};
		if(paramSet.alarmsound == 0){
			$("select[name='alarmsound'] option").eq(1).attr("selected",true);
		};
		if(paramSet.vehicleonlineinformation == 0){
			$("select[name='vehicleonlineinformation'] option").eq(1).attr("selected",true);
		};
		if(paramSet.rulerproportions){
			$("input[name=rulerproportions]").val(paramSet.rulerproportions);
		}
	}
}
function paramSetUp(){
	$(".popcomfirm_xk a").eq(0).click(function(){
		data_json = {
				"toolbar" : $("select[name='toolbar'] option:selected").val(),
				"mapcalloutlayer" : $("select[name='mapcalloutlayer'] option:selected").val(),
				"scale" : $("select[name='scale'] option:selected").val(),
				"maptoolbar" : $("select[name='maptoolbar'] option:selected").val(),
				"ruler" : $("select[name='ruler'] option:selected").val(),
				"mapcentercoordinates" : $("select[name='mapcentercoordinates'] option:selected").val(),
				"vehicleicon" : $("select[name='vehicleicon'] option:selected").val(),
				"generalinformation" : $("select[name='generalinformation'] option:selected").val(),
				"commandreplyinformation" : $("select[name='commandreplyinformation'] option:selected").val(),
				"vehicleonlineinformation" : $("select[name='vehicleonlineinformation'] option:selected").val(),
				"vehicleinformation" : $("select[name='vehicleinformation'] option:selected").val(),
				"alarmlamp" : $("select[name='alarmlamp'] option:selected").val(),
				"alarmsound" : $("select[name='alarmsound'] option:selected").val(),
				"rulerproportions" : $("input[name='rulerproportions']").val(),
				"id" : paramSet.id,
				"userid" : paramSet.userid
		}
		$.ajax(config.url + "port/updateStyle", {
			data:data_json,
			dataType: "json",
			type: "POST",
			async:false,
			timeout: 10000,
			success: function(data) {
				if(data.success == true){
					paramSetting();
					$("img[src='img/close.png']").click();
					$(".gt_wl_left dt.on_xk").siblings("dd").children("span.on_xk").click();
				}
			}
		});
	})
}
showParam();
paramSetUp();