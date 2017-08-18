$(document).ready(function() {
	tpa = 1;
	var timee;
	var times;

	function getOperationLogReport() {
		$.ajax(config.url + "forms/getOperationLogReport", {
			data: {
				"currentPage": tpa,
				"startTime": times,
				"endTime": timee
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					data.obj == null ? data = data.t : data = data.obj;
					$(".list_xk tfoot tr td").html("第" + data.currentPage + "页 共" + data.tp + "页 共" + data.totalCount + "条")
					html = "";
					for(var i = 0; i < data.beanList.length; i++) {
						html += '<tr>';
						html += '<td>' + (i + 1) + '</td>';
						if(data.beanList[i].username == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].username + '</td>';
						};
						if(data.beanList[i].ip == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].ip + '</td>';
						};
						if(data.beanList[i].module == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].module + '</td>';	
						};
						if(data.beanList[i].context == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].context + '</td>';
						};
						if(data.beanList[i].issueTime == null){
							html += '<td></td>';
						}else{
							html += '<td>' + data.beanList[i].issueTime + '</td>';
						};
						html += '</tr>';
					}
					$(".list_xk tbody").html(html);
					fenye_lv(data.tp, function() {
						getOperationLogReport();
					});
				}
			}
		});
	};

	function searchFresh() {
		$("#search").click(function() {
			times = $(".time_plugin_lv_onclick:eq(0)").val();
			timee = $(".time_plugin_lv_onclick:eq(1)").val();
			getOperationLogReport();
		});
	};

	function exportExcel() {
		$(".inquirybox_xk .inquiry_xk a.button:eq(1)").click(function() {
			location.href = config.url + "forms/getOperationLogReportExcel?startTime=" + $(".time_plugin_lv_onclick:eq(0)").val() + "&endTime=" + $(".time_plugin_lv_onclick:eq(1)").val();
		});
	}
	exportExcel();
	getOperationLogReport();
	searchFresh();
});