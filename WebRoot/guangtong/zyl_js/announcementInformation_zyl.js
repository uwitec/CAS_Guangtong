tpa = 1;
var query_zyl = "";

// 双击弹窗
function getNoticesModelById_dblclick(this_id) {
	$.ajax(config.url + "notices/getNoticesModelById", {
		data: {
			"id": this_id
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				$(".poptable2_xk_zyl ul  li:eq(0) span").text(data.t.title);
				$(".poptable2_xk_zyl ul  li:eq(1) span").text(data.t.content);
				$(".poptable2_xk_zyl ul  li:eq(2) span").text(data.t.issuetime);
				$(".poptable2_xk_zyl ul  li:eq(3) span").text(data.t.senderAdminName);
				getNoticesAll();
			}
		}
	})
}
// 查询全部
function getNoticesAll() {
	$.ajax(config.url + "notices/getNoticesAll", {
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
				for(var i = 0; i < data.beanList.length; i++) {
					html += '<tr zyl_id="' + data.beanList[i].id + '">';
					html += '<td class="cherked_xk"><img src="img/uncherked.jpg" alt="" /></td>';
					html += '<td>' + (i + 1) + '</td>';
					if(data.beanList[i].title == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.beanList[i].title + '</td>';
					};
					if(data.beanList[i].content == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.beanList[i].content + '</td>';
					};
					if(data.beanList[i].issuetime == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.beanList[i].issuetime + '</td>';
					};
					if(data.beanList[i].senderAdminName == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.beanList[i].senderAdminName + '</td>';
					};
					if(data.beanList[i].isRead == 1){
						html += '<td>已读</td>';
					}else{
						html += '<td>未读</td>';
					}
					html += '<td><a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.beanList[i].id + '">删除</a></td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"删除": "announcementInformation_deleteconfirm.html",
					"批量删除": "announcementInformation_m_deleteconfirm.html",
					"sider": "announcementInformation_sider.html"
				};
				$(".gt_wl_right a.button").unbind('click').bind('click', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").attr("this_id", this_id);
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));

				});
				$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
					getNoticesModelById_dblclick(this_id);
				});
				fenye_lv(data.tp, function() {
					getNoticesAll();
				});
			}
		}
	});
};
getNoticesAll();
function check_zyl() {
	$(".button").click(function() {
		if($(this).text() == "查询") {
			query_zyl = $(".query").val();
			getNoticesAll();
		}
	});
}
