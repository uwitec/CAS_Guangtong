$(document).ready(function() {
	tpa = 1;
	var query_zyl = "";

	function getHistoryNoticesAll() {
		$.ajax(config.url + "notices/getHistoryNoticesAll", {
			data: {
				"currentPage": tpa,
				"pageCount": 10,
				"condition": query_zyl
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success) {
					data = data.t;
					$(".list_xk tfoot tr td").html("第" + data.currentPage + "页 共" + data.tp + "页 共" + data.totalCount + "条")
					html = "";
					$(".list_xk tbody").html("");
					for(var i = 0; i < data.beanList.length; i++) {
						html += '<tr>';
						html += '<td>' + (i + 1) + '</td>';
						html += '<td>' + data.beanList[i].title + '</td>';
						html += '<td>' + data.beanList[i].content + '</td>';
						html += '<td>' + data.beanList[i].issueTime + '</td>';
						html += '<td>' + data.beanList[i].count + '</td>';
						html += '</tr>';
					}
					$(".list_xk tbody").html(html);
					fenye_lv(data.tp, function() {
						getHistoryNoticesAll();
					});
				}
			}
		});
	};
	getHistoryNoticesAll();
	$(".button").click(function() {
		if($(this).text() == "查询") {
			query_zyl = $(".query").val();
			getHistoryNoticesAll();
		}
	});

});