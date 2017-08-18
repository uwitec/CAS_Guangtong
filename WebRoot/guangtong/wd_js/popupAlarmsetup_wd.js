function getAlarmsInfo(){
	$.ajax(config.url + "port/getAlarmsInfo", {
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success == true) {
				data = data.t;
				for(one in data){
					if(data[one] == 1){
						$("input[name='"+ one+"']:checkbox").attr("checked","checked");
						$("input[name='"+ one+"']:checkbox").val(1);
					}
				}
				$("input[name='id']").val(data.id);
				$("input[name='userId']").val(data.userId);
			}
		}
	});
}
getAlarmsInfo();
$("input:checkbox").click(function(){
	if($(this).val() == 0){
		$(this).val(1);
	}else{
		$(this).val(0);
	}
})
$(".popcomfirm_xk a.button:contains('确定')").click(function() {
	data_json = "{";
			$("input:checkbox").each(function() {
				data_json += '"'+$(this).attr('name')+'"'+':'+'"'+$(this).val()+'"'+',';
			});
			data_json += '"id"'+":"+'"'+$("input[name='id']:hidden").val()+'"'+",";
			data_json += '"userId"'+":"+'"'+$("input[name='userId']:hidden").val()+'"';
			data_json+= "}";
			data_json = $.parseJSON(data_json)
	$.ajax(config.url + "port/updateAlarmsInfo", {
		data: data_json,
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success == true) {
				alert("添加成功！");
				$(".pophead_xk i img[src='img/close.png']").click();
			} else {
				alert("添加失败！请稍后再试！")
			}
		}
	});
})