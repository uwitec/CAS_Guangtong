tpa = 1;
$(".popbody_xk").on('click','img[alt="上传照片"]', function() {
	$('img[alt="上传照片"]').attr("class","");
	$(this).attr("class","thisname_lv_img");
		$(this).next("input:file").click();
})

$(".popbody_xk").on('change','input:file', function() {
	UploadImg($(this).attr("id"));
	
})
//编辑
function getCooperationInfo(this_id) {
	$.ajax(config.url + "cooperation/getCooperationInfo", {
		data: {
			"id": this_id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				data.obj = data.t;
				$(".poptable2_xk_zyl tbody tr td span").text(data.obj.id);
				$(".poptable2_xk_zyl tbody tr:eq(1) td input").val(data.obj.cname);
				$(".poptable2_xk_zyl tbody tr:eq(2) td input").val(data.obj.contact);
				$(".poptable2_xk_zyl tbody tr:eq(3) td input").val(data.obj.tel);
				$(".poptable2_xk_zyl tbody tr:eq(4) td input").val(data.obj.addr);
				$(".poptable2_xk_zyl tbody tr:eq(5) td input").val(data.obj.email);
				$(".poptable2_xk_zyl tbody tr:eq(6) td input").val(data.obj.cnum);
				$(".poptable2_xk_zyl tbody tr:eq(7) td img").attr({"src":config.url+ data.t.cimg+"","img_id":data.t.cimg});
				uCooperationInfo(this_id);
			}
		}
	});
}
/*更新*/
function uCooperationInfo(this_id) {
	$(".popcomfirm_xk .save").click(function() {
		data_json = {
			"id": this_id,
			"cname": $(".poptable2_xk_zyl tbody tr:eq(1) td input").val(),
			"contact": $(".poptable2_xk_zyl tbody tr:eq(2) td input").val(),
			"tel": $(".poptable2_xk_zyl tbody tr:eq(3) td input").val(),
			"addr": $(".poptable2_xk_zyl tbody tr:eq(4) td input").val(),
			"email": $(".poptable2_xk_zyl tbody tr:eq(5) td input").val(),
			"cnum": $(".poptable2_xk_zyl tbody tr:eq(6) td input").val(),
			"cimg": $(".poptable2_xk_zyl tbody tr:eq(7) td img").attr("img_id"),
		}
		$.ajax(config.url + "cooperation/uCooperationInfo", {
			data: data_json,
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					//console.log(data.success);
					alert(data.msg);
					
					$("popwindow_xk .popup_xk").html("");
					$(".popwindow_xk").css("visibility", "hidden");
					sCooperationInfoA();
				}
				//console.log(JSON.stringify(data));
			}
		});
		
	});
}
//双击弹窗
function getCooperationInfo_dblclick(this_id) {
	$.ajax(config.url + "cooperation/getCooperationInfo", {
		data: {
			"id": this_id,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success) {
				data.obj = data.t;
				
				$(".poptable2_xk_zyl ul  li:eq(0) span").text(data.obj.id);
				$(".poptable2_xk_zyl ul  li:eq(1) span").text(data.obj.cname);
				$(".poptable2_xk_zyl ul  li:eq(2) span").text(data.obj.contact);
				$(".poptable2_xk_zyl ul  li:eq(3) span").text(data.obj.tel);
				if(data.obj.addr == null) {
					$(".poptable2_xk_zyl ul  li:eq(4) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(4) span").text(data.obj.addr);
				}
				if(data.obj.email == null) {
					$(".poptable2_xk_zyl ul  li:eq(5) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(5) span").text(data.obj.email);
				}
				$(".poptable2_xk_zyl ul  li:eq(6) span").text(data.obj.cnum);
				if(data.obj.createtime == null) {
					$(".poptable2_xk_zyl ul  li:eq(7) span").text("");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(7) span").text(data.obj.createtime);
				}
				if(data.obj.available == 1) {
					$(".poptable2_xk_zyl ul  li:eq(8) span").text("可用");
				} else {
					$(".poptable2_xk_zyl ul  li:eq(8) span").text("不可用");
				}
				$(".poptable2_xk_zyl ul  li:eq(9) span").after("<img height='60px' src='"+ config.url + data.obj.cimg + "' alt='图片'>");
			}
		}
	});
}
//查询
function sCooperationInfoA() {
	$.ajax(config.url + "cooperation/sCooperationInfoA", {
		data: {
			"currentPage": tpa,
			"pageCount": 10,
			"id" : $(".list_xk thead input[name='id']").val(),
			"contact" : $(".list_xk thead input[name='contact']").val(),
			"tel" : $(".list_xk thead input[name='tel']").val(),
			"cName": $(".list_xk thead input[name='cName']").val(),
			"cNum" : $(".list_xk thead input[name='cNum']").val(),
			"createTime" : $(".list_xk thead input[name='createTime']").val(),
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
					html += '<td class="cherked_xk"><img src="img/uncherked.jpg" alt="" /></td>';
					html += '<td>' + (i + 1) + '</td>';
					html += '<td>' + data.obj.beanList[i].id + '</td>';
					if(data.obj.beanList[i].cName == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].cName + '</td>';
					}
					if(data.obj.beanList[i].contact == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].contact + '</td>';
					}
					if(data.obj.beanList[i].tel == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].tel + '</td>';
					}
					if(data.obj.beanList[i].cNum == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].cNum + '</td>';
					}
					if(data.obj.beanList[i].createTime == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].createTime + '</td>';
					}

					html += '<td>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;"zyl_id="' + data.obj.beanList[i].id + '">编辑</a>';
					if(data.obj.beanList[i].available) {
						html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">冻结</a>';
					} else {
						html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">解冻</a>';
					}
					html += '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"编辑": "popupEditenterprise.html",
					"删除": "deleteconfirm.html",
					"+增加": "popupAddenterprise.html",
					"冻结": "transportationinfoEnterprise_freezeconfirm.html",
					"批量冻结": "transportationinfoEnterprise_m_freezeconfirm.html",
					"解冻": "transportationinfoEnterprise_unfreezeconfirm.html",
					"批量解冻": "transportationinfoEnterprise_m_unfreezeconfirm.html",
					"查询": "",
					"sider": "popupDetailsenterprise.html"
				};
				$(".gt_wl_right a.button").unbind('click').bind('click', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").attr("this_id", this_id);
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
					if($(this).text() == "编辑") {
						getCooperationInfo(this_id);
					}
				});
				$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
					getCooperationInfo_dblclick(this_id);
				});
				fenye_lv(data.obj.tp, function() {
					sCooperationInfoA();
				});
			}
		}
	});
};
if($(".list_xk tbody tr").length == 1) {
	sCooperationInfoA()
}
//模糊搜索
function fuzzySearch(){
	$(".list_xk thead input").bind('input propertychange',function(){
			sCooperationInfoA();
	})
};
fuzzySearch();