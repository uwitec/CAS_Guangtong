$(document).ready(function() {
	var tpa = 1;

	function getVehicleOperationReport() {
		$.ajax(config.url + "forms/getVehicleOperationReport", {
			data: {
				"currentPage": tpa,
				"times" : "2017-01-01",
				"timee" : "2018-01-01"
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(data);
				var html = "";
				html += '<li>首页</li>'
				html += '<li>上一页</li>'
				if((tpa - 3) < 1) {
					for(k = 1; k < tpa; k++) {
						html += '<li>' + k + '</li>'
					};
				} else {
					for(q = tpa - 3; q < tpa; q++) {
						html += '<li>' + q + '</li>'
					};
				};
				if((tpa + 3) > data.tp) {
					for(w = tpa + 1; w < data.tp + 1; w++) {
						html += '<li>' + w + '</li>'
					};
				} else {
					for(e = tpa + 1; e < tpa + 4; e++) {
						html += '<li>' + e + '</li>'
					};
				};
				html += '<li>下一页</li>'
				html += '<li style="border: none !important;" tpa=' + data.tp + ' >尾页</li>'
				$(".page_right_xk").html(html);
				$(".list_xk tfoot tr td").html("第" + data.currentPage + "页 共" + data.tp + "页 共" + data.totalCount + "条")
				html = "";
				for(var i = 0; i < data.beanList.length; i++) {
					html += '<tr>';
					html += '<td>' + (i + 1) + '</td>';
					html += '<td>' + data.beanList[i].a1+ '</td>';
					html += '<td>' + data.beanList[i].a2 + '</td>';
					html += '<td>' + data.beanList[i].a3 + '</td>';
					html += '<td>' + data.beanList[i].a4 + '</td>';
					html += '<td>' + data.beanList[i].a5 + '</td>';
					html += '<td>' + data.beanList[i].a6 + '</td>';
					html += '<td>' + data.beanList[i].a7 + '</td>';
					html += '<td>' + data.beanList[i].a8 + '</td>';
					html += '<td>' + data.beanList[i].a9 + '</td>';
					html += '<td>' + data.beanList[i].a10 + '</td>';
					html += '<td>' + data.beanList[i].a11 + '</td>';
					html += '<td>' + data.beanList[i].a12 + '</td>';
					html += '<td>' + data.beanList[i].a13 + '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				$(".page_right_xk li").click(function() {
					var tpa_end = $(".page_right_xk li[tpa]").attr("tpa");
					if($(this).text() == "首页") {
						tpa = 1;
					} else if($(this).text() == "尾页") {
						tpa = tpa_end;
					} else if($(this).text() == "上一页" && tpa != 1) {
						tpa = tpa - 1;
					} else if($(this).text() == "下一页" && tpa != tpa_end) {
						tpa = (tpa + 1);
					} else if($(this).text() != "下一页"&&$(this).text() != "上一页"&&$(this).text() != "首页"&&$(this).text() != "尾页") {
						tpa = Number($(this).text());
					}
					getVehicleOperationReport()
				});
			}
		});
	};
	getVehicleOperationReport()
});