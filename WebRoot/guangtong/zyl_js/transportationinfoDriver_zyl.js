tpa = 1;
var query_zyl = "";

$(".popbody_xk").on('click','img[alt="上传照片"]', function() {
	$('img[alt="上传照片"]').attr("class","");
	$(this).attr("class","thisname_lv_img");
		$(this).next("input:file").click();
});

$(".popbody_xk").on('change','input:file', function() {
	UploadImg($(this).attr("id"));
	
});
// 编辑
function sDriverInfoOne(this_id) {
	$.ajax(config.url + "driver/sDriverInfoOne", {
		data : {
			"id" : this_id,
		},
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
				$(".poptable2_xk_zyl tbody tr td span").text(data.obj[1].id);
				$(".poptable2_xk_zyl tbody tr:eq(1)  td input").val(data.obj[1].dName);
				for(var i=0;i<data.obj[0].length;i++){
					$(".poptable2_xk_zyl tbody tr:eq(2)  td select").append('<option value="' + data.obj[0][i].id + '" zyl_id="' + data.obj[0][i].id + '">' + data.obj[0][i].cName + '</option>');
				}
				$(".poptable2_xk_zyl tbody tr:eq(2) td select option").each(function() {
					if ($(this).text() == data.obj[1].cName) {
								$(this).attr("selected","selected");
							} else {
								$(this).removeAttr("selected");
							}
						});
				//$(".poptable2_xk_zyl tbody tr:eq(2)  td input").val(data.obj[1].cName);
				$(".poptable2_xk_zyl tbody tr:eq(3)  td input").val(data.obj[1].tel);
				$(".poptable2_xk_zyl tbody tr:eq(4)  td input").val(data.obj[1].addr);
				$(".poptable2_xk_zyl tbody tr:eq(5)  td input").val(data.obj[1].idCardNum);
				$(".poptable2_xk_zyl tbody tr:eq(6)  td input").val(data.obj[1].idCardValidDate);
				$(".poptable2_xk_zyl tbody tr:eq(7)  td input").val(data.obj[1].defaultVehicleNum);
				$(".poptable2_xk_zyl tbody tr:eq(8)  td input").val(data.obj[1].DLNum);
				$(".poptable2_xk_zyl tbody tr:eq(9)  td input").val(data.obj[1].DLValidDate);
				$(".poptable2_xk_zyl tbody tr:eq(10)  td input").val(data.obj[1].driverClass);
				$(".poptable2_xk_zyl tbody tr:eq(11)  td input").val(data.obj[1].professionNum);
				$(".poptable2_xk_zyl tbody tr:eq(12)  td input").val(data.obj[1].institutionName);
				$(".poptable2_xk_zyl tbody tr:eq(13)  td input").val(data.obj[1].professionValidDate);
				$(".poptable2_xk_zyl tbody tr:eq(14)  td input").val(data.obj[1].professionYearCheck);
				$(".poptable2_xk_zyl tbody tr:eq(15)  td input").val(data.obj[1].professionCount);
				$(".poptable2_xk_zyl tbody tr:eq(16)  td img").attr({"src":config.url+ data.obj[1].idCardFrontImg+"","img_id":data.obj[1].idCardFrontImg});
				$(".poptable2_xk_zyl tbody tr:eq(17)  td img").attr({"src":config.url+ data.obj[1].idCardBackImg+"","img_id":data.obj[1].idCardBackImg});
				$(".poptable2_xk_zyl tbody tr:eq(18)  td img").attr({"src":config.url+ data.obj[1].DLImg+"","img_id":data.obj[1].DLImg});
				$(".poptable2_xk_zyl tbody tr:eq(19)  td img").attr({"src":config.url+ data.obj[1].DLImg2+"","img_id":data.obj[1].DLImg2});
				$(".poptable2_xk_zyl tbody tr:eq(20)  td img").attr({"src":config.url+ data.obj[1].professionImg+"","img_id":data.obj[1].professionImg});
				$(".poptable2_xk_zyl tbody tr:eq(21)  td img").attr({"src":config.url+ data.obj[1].professionimg2+"","img_id":data.obj[1].professionimg2});
				uDriverInfo(this_id);
		}
	});
};
/* 更新 */
function uDriverInfo(this_id) {
	$(".popcomfirm_xk .save").click(function() {
		data_json = {
			"id" : this_id,
			"dName" : $(".poptable2_xk_zyl tbody tr:eq(1)  td input").val(),
			"cooperationId" : $(".poptable2_xk_zyl tbody tr:eq(2)  td select option:selected").val(),
			"tel" : $(".poptable2_xk_zyl tbody tr:eq(3)  td input").val(),
			"addr" : $(".poptable2_xk_zyl tbody tr:eq(4)  td input").val(),
			"idCardNum" : $(".poptable2_xk_zyl tbody tr:eq(5)  td input").val(),
			"idCardValidDate" : $(".poptable2_xk_zyl tbody tr:eq(6)  td input").val(),
			"defaultVehicleNum" : $(".poptable2_xk_zyl tbody tr:eq(7)  td input").val(),
			"DLNum" : $(".poptable2_xk_zyl tbody tr:eq(8)  td input").val(),
			"DLValidDate" : $(".poptable2_xk_zyl tbody tr:eq(9)  td input").val(),
			"driverClass" : $(".poptable2_xk_zyl tbody tr:eq(10)  td input").val(),
			"professionNum" : $(".poptable2_xk_zyl tbody tr:eq(11)  td input").val(),
			"institutionName" : $(".poptable2_xk_zyl tbody tr:eq(12)  td input").val(),
			"professionValidDate" : $(".poptable2_xk_zyl tbody tr:eq(13)  td input").val(),
			"professionYearCheck" : $(".poptable2_xk_zyl tbody tr:eq(14)  td input").val(),
			"professionCount" : $(".poptable2_xk_zyl tbody tr:eq(15)  td input").val(),
			"idCardFrontImg" : $(".poptable2_xk_zyl tbody tr:eq(16)  td img").attr("img_id"),
			"idCardBackImg" : $(".poptable2_xk_zyl tbody tr:eq(17)  td img").attr("img_id"),
			"DLImg" : $(".poptable2_xk_zyl tbody tr:eq(18)  td img").attr("img_id"),
			"DLImg2" : $(".poptable2_xk_zyl tbody tr:eq(19)  td img").attr("img_id"),
			"professionImg" : $(".poptable2_xk_zyl tbody tr:eq(20)  td img").attr("img_id"),
			"professionImg2" : $(".poptable2_xk_zyl tbody tr:eq(21)  td img").attr("img_id")
		}
		$.ajax(config.url + "driver/uDriverInfo", {
			data : data_json,
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				//console.log(data.success);
				alert(data.msg);
				
				$("popwindow_xk .popup_xk").html("");
				$(".popwindow_xk").css("visibility", "hidden");
				sDriverInfoA();
			}
		});
		
	});
};
// 双击弹窗
function sDriverInfoOne_dblclick(this_id) {
	$.ajax(config.url + "driver/sDriverInfoOne", {
		data : {
			"id" : this_id,
		},
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
			var driverarray = [];
			driverarray.push(data.obj[1].id);
			driverarray.push(data.obj[1].dName);
			driverarray.push(data.obj[1].cName);
			driverarray.push(""); // 性别
			driverarray.push(data.obj[1].tel);
			driverarray.push(data.obj[1].addr);
			driverarray.push(data.obj[1].DLNum);
			driverarray.push("");
			driverarray.push(data.obj[1].driverClass);
			driverarray.push(data.obj[1].idCardNum);
			driverarray.push(data.obj[1].idCardValidDate);
			driverarray.push(data.obj[1].professionNum);
			driverarray.push(data.obj[1].professionValidDate);
			driverarray.push(data.obj[1].institutionName);
			driverarray.push(data.obj[1].professionCount);
			driverarray.push(data.obj[1].idCardFrontImg);
			driverarray.push(data.obj[1].idCardBackImg);
			driverarray.push(data.obj[1].DLImg);
			driverarray.push(data.obj[1].DLImg2);
			driverarray.push(data.obj[1].professionImg);

			for (var i = 0; i < driverarray.length; i++) {
				if ($(".poptable2_xk_zyl ul li").eq(i).find("span").hasClass("insertimg")) {
					$(".poptable2_xk_zyl ul li").eq(i).find("span").html('<img height="60px" src="' + config.url + driverarray[i] + '">');
				} else {
					$(".poptable2_xk_zyl ul li").eq(i).find("span").text(driverarray[i]);
				}
				;
			}
			;
		}
	});
}
// 查询全部-获取表格内容
function sDriverInfoA() {
	$.ajax(config.url + "driver/sDriverInfoA", {
		data : {
			"currentPage" : tpa,
			"pageCount" : 10,
			"condition" : query_zyl,
			"id" : $(".list_xk thead input[name='id']").val(),
			"dName" : $(".list_xk thead input[name='dName']").val(),
			"tel" : $(".list_xk thead input[name='tel']").val(),
			"cName": $(".list_xk thead input[name='cName']").val(),
			"defaultVehicleNum" : $(".list_xk thead input[name='defaultVehicleNum']").val(),
			"driverClass" : $(".list_xk thead input[name='driverClass']").val(),
		},
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
			if (data.success) {
				data.obj = data.t;

				$(".list_xk tfoot tr td").html("第" + data.obj.currentPage + "页 共" + data.obj.tp + "页 共" + data.obj.totalCount + "条")
				html = "";
				for (var i = 0; i < data.obj.beanList.length; i++) {
					html += '<tr zyl_id="' + data.obj.beanList[i].id + '">';
					html += '<td class="cherked_xk"><img src="img/uncherked.jpg" alt="" /></td>';
					html += '<td>' + (i + 1) + '</td>';
					html += '<td>' + data.obj.beanList[i].id + '</td>';
					if (data.obj.beanList[i].dName == undefined ) {
						html += '<td>' + data.obj.beanList[i].dName + '</td>';
					} else {
						html += '<td>' + data.obj.beanList[i].dName + '</td>';
					};
					if(data.obj.beanList[i].tel == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].tel + '</td>';
					}
					if (data.obj.beanList[i].cName == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].cName + '</td>';
					};
					if(data.obj.beanList[i].defaultVehicleNum == null){
						html += '<td></td>';
					}else{
						html += '<td>' + data.obj.beanList[i].defaultVehicleNum + '</td>';	
					};
					if (data.obj.beanList[i].driverClass == undefined) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].driverClass + '</td>';
					}
					html += '<td>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">编辑</a>';
					if (data.obj.beanList[i].available == 1) {
						html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">冻结</a>';
					} else {
						html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">解冻</a>';
					}
					html += '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"编辑" : "popupEditdriver.html",
					"删除" : "deleteconfirm.html",
					"+增加" : "popupAdddriver.html",
					"查询" : "",
					"冻结" : "transportationinfoDriver_freezeconfirm.html",
					"批量冻结" : "transportationinfoDriver_m_freezeconfirm.html",
					"解冻" : "transportationinfoDriver_unfreezeconfirm.html",
					"批量解冻" : "transportationinfoDriver_m_unfreezeconfirm.html",
					"导入" : "importdriver.html",
					"sider" : "popupDetailsdriver.html"
				};
				$(".gt_wl_right a.button").unbind('click').bind('click', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").attr("this_id", this_id);
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
					if ($(this).text() == "编辑") {
						sDriverInfoOne(this_id);
					}
				});
				$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
					sDriverInfoOne_dblclick(this_id);
				});
				fenye_lv(data.obj.tp, function() {
					sDriverInfoA();
				});
			}
		}
	});
};

$(".listgrp_xk").on("click",".import",function(){
	getDriversExcelImport();
});
// 导出
function driver_export() {
	$(".listtitle_xk ").on('click',".driver_export",function(){
		//location.href = config.url + "driver/excel";
		location.href = config.url + "driver/excel?id=" + $(".list_xk thead input[name='id']").val() + "&dName=" + $(".list_xk thead input[name='dName']").val() + "&tel=" +  $(".list_xk thead input[name='tel']").val() +"&cName="+  $(".list_xk thead input[name='cName']").val() +"&defaultVehicleNum=" +  $(".list_xk thead input[name='defaultVehicleNum']").val() +"&driverClass=" +  $(".list_xk thead input[name='driverClass']").val();
	})
	/*$(".driver_export").click(function() {
		location.href = config.url + "driver/excel";
	})*/
};

//导出模版
function exportTemplate(){
	$(".listtitle_xk ").on('click',".template",function(){
		location.href = config.url + "driver/exportTemplate";
	})
};
driver_export();
exportTemplate();
// getDriversExcelImport();
if ($(".list_xk tbody tr").length == 1) {
	sDriverInfoA();
};
//模糊搜索
function fuzzySearch(){
	$(".list_xk thead input").bind('input propertychange',function(){
			sDriverInfoA();
	})
};
fuzzySearch();