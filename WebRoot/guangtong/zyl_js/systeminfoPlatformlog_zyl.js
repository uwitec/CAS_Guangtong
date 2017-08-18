tpa = 1;
var query_zyl = "";

function getPlatLogsByPage() {
	$.ajax(config.url + "cms/getPlatLogsByPage", {
		data: {
			"currentPage": tpa,
			"pageCount": 10,
			"condition": query_zyl,
			"startTime": $(".inquiry_xk .startTime").val(),
			"endTime": $(".inquiry_xk .endTime").val()
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
					html += '<tr>';
					html += '<td>' + (i + 1) + '</td>';
					html += '<td>' + data.obj.beanList[i].issueTime + '</td>';
					html += '<td>' + data.obj.beanList[i].username + '</td>';
					html += '<td>' + data.obj.beanList[i].module + '</td>';
					html += '<td>' + data.obj.beanList[i].context + '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				fenye_lv(data.obj.tp, function() {
					getPlatLogsByPage();
				});
			}
		}
	});
};
//查询
$(".button").click(function() {
	if($(this).text() == "查询") {
		query_zyl = $(".query").val();
		getPlatLogsByPage();
	}
});

//导出
function getPlatLogData() {
	$(".inquirybox_xk .inquiry_xk a.button:eq(1)").click(function() {
		location.href = config.url + "cms/getPlatLogData?&startTime=" + $(".time_plugin_lv_onclick:eq(0)").val() + "&endTime=" + $(".time_plugin_lv_onclick:eq(1)").val() + "&condition=" + $(".query").val();
	});
};

if($(".list_xk tbody tr").length == 1) {
	getPlatLogsByPage();
};
getPlatLogData();