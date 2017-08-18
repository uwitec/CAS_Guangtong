tpa = 1;

$(document).ready(function(){
	$(".inquiry_xk select").change(function(){
		var driverSideAppStr = "司机端APP";
		var userSideAppStr ="用户端APP";
		var textVal = $(".inquiry_xk select option:selected").text();
		if(textVal == driverSideAppStr){
			getInformations();//司机
			//alert('司机端APP');
		}else if(textVal == userSideAppStr){
			getCustomerInformations();//用户
			//alert('用户端APP');
		}
	})
});

//司机--查询全部
function getInformations() {
	$.ajax(config.url + "cms/getInformations", {
		data: {
			"currentPage": tpa,
			"pageCount" : 10,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			$(".list_xk tfoot tr td").html("第" + data.t.currentPage + "页 共" + data.t.tp + "页 共" + data.t.totalCount + "条")
			html = "";
			for(var i = 0; i < data.t.beanList.length; i++) {
				html += '<tr>';
				html += '<td>' + (i + 1) + '</td>';
				html += '<td>' + data.t.beanList[i].title + '</td>';
				if(data.t.beanList[i].smallimg != null && data.t.beanList[i].smallimg != 'NULL' ){
					html += '<td style="text-align:center;"><img src="'+ data.t.beanList[i].smallimg + '"height="64px"></td>';
				}else{
					html += '<td style="text-align:center;">无</td>';
				}
				if(data.t.beanList[i].bigimg != null && data.t.beanList[i].bigimg != 'NULL' ){
					html += '<td style="text-align:center;"><img src="'+ data.t.beanList[i].bigimg + '"height="64px"></td>';
				}else{
					html += '<td style="text-align:center;">无</td>';
				}
				if(data.t.beanList[i].bigimg != "NULL"){
					html += '<td style="text-align:center;">是</td>';
				}else{
					html += '<td style="text-align:center;">否</td>';
				};
				html += '<td>' + data.t.beanList[i].createtime + '</td>';
				html += '<td>';
				html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
				html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">删除</a>';
				if(data.t.beanList[i].bigimg != "NULL"){
					html += '<a class="button blue carousel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消轮播</a>';
				}else{
					html += '<a class="button blue carousel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">设为轮播</a>';
				}
				html += '</td>';
				html += '</tr>';
			}
			$(".list_xk tbody").html(html);
			var thispopup = {
				"编辑": "popupEditAPPinfo.html",
				"删除": "APPinfo_deleteInformation_del.html",
				"+增加": "popupAddAPPinfo.html",
				"设为轮播": "popupAddAPPinfo_carousel.html",
			};
			$(".gt_wl_right a.button").unbind('click').bind('click',function(){
				var this_id = $(this).attr("zyl_id");
				$(".popwindow_xk .popup_xk").attr("this_id", this_id);
				$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
				if($(this).text() == "编辑"){
					getInformationById(this_id);
				}else if($(this).text() == "设为轮播"){
					updateInformation_z(this_id);
				}else if($(this).text() == "取消轮播"){
					noCarouselInformation(this_id);
				}
			});
			$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick',function(){
				$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
			});
			fenye_lv(data.t.tp, function() {
				getInformations();
			});
		}
	});
};

//根据id查询资讯信息 -- 司机
function getInformationById(id){
	$(".popbody_xk").on('click','img[alt="上传照片"]', function() {
		$('img[alt="上传照片"]').attr("class","");
		$(this).attr("class","thisname_lv_img_z");
			$(this).next("input:file").click();
	});

	$(".popbody_xk").on('change','input:file', function() {
		UploadImg_zyl($(this).attr("id"));
		
	});
	$.ajax(config.url + "cms/getInformationById", {
		data : {
			"id" : id,
		},
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
			if(data.success){
				$(".poptable2_xk_zyl tbody tr:eq(0) td input").val(data.t.title);
				$(".poptable2_xk_zyl tbody tr:eq(1)  td img").attr({"src": data.t.smallimg+"","img_id_z":data.t.smallimg});
				$(".poptable2_xk_zyl tbody tr:eq(2)  td img").attr({"src": data.t.detailsfigure+"","img_id_z":data.t.detailsfigure});
				$(".poptable2_xk_zyl tbody tr:eq(3)  td ").val(UE.getEditor('editor').setContent(data.t.content, true));
			//	$(".poptable2_xk_zyl tbody tr:eq(4)  td img").attr({"src": data.t.bigimg+"","img_id_z":data.t.bigimg});;
				updateInformation(id);
			}
		}
	});
};
//根据id更新资讯信息 -- 司机
function updateInformation(id){
	$(".popcomfirm_xk .save").click(function() {
			data_json = {
					"id" : id,
					"title" : $(".poptable2_xk_zyl tbody tr:eq(0)  td input").val(),
					"smallimg" : $(".poptable2_xk_zyl tbody tr:eq(1)  td img").attr("img_id_z"),
					"detailsfigure" : $(".poptable2_xk_zyl tbody tr:eq(2)  td img").attr("img_id_z"),
					"content" : UE.getEditor('editor').getContentTxt(),
					//"bigimg" : $(".poptable2_xk_zyl tbody tr:eq(4)  td img").attr("img_id_z")
				}
		$.ajax(config.url + "cms/updateInformation", {
			data : data_json,
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				//console.log(data.success);
				alert(data.msg);
				 UE.getEditor("editor").destroy();
				$("popwindow_xk .popup_xk").html("");
				$(".popwindow_xk").css("visibility", "hidden");
				getInformations();
			}
		});
		
	});
};
function updateInformation_z(id){
	$(".popbody_xk").on('click','img[alt="上传照片"]', function() {
		$('img[alt="上传照片"]').attr("class","");
		$(this).attr("class","thisname_lv_img_z");
			$(this).next("input:file").click();
	});

	$(".popbody_xk").on('change','input:file', function() {
		UploadImg_zyl($(this).attr("id"));
		
	});
	$(".popcomfirm_xk .save").click(function() {
		data_json = {
			"id" : id,
			"bigimg" : $(".poptable2_xk_zyl tbody tr:eq(0)  td img").attr("img_id_z")
		};
		$.ajax(config.url + "cms/updateInformation", {
			data : data_json,
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				alert(data.msg);
				
				$("popwindow_xk .popup_xk").html("");
				$(".popwindow_xk").css("visibility", "hidden");
				getInformations();
			}
		});
		
	});
}

function noCarouselInformation(id){
		data_json = {
			"id" : id,
		}
		$.ajax(config.url + "cms/noCarouselInformation", {
			data : data_json,
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				alert(data.msg);
				
				$("popwindow_xk .popup_xk").html("");
				$(".popwindow_xk").css("visibility", "hidden");
				getInformations();
			}
		});
		
}


//---------------------------------------分割线-------------------------------------------
//用户--查询全部
function getCustomerInformations() {
	$.ajax(config.url + "cms/getCustomerInformations", {
		data: {
			"currentPage": tpa,
			"pageCount" : 10,
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			$(".list_xk tfoot tr td").html("第" + data.t.currentPage + "页 共" + data.t.tp + "页 共" + data.t.totalCount + "条")
			html = "";
			for(var i = 0; i < data.t.beanList.length; i++) {
				html += '<tr>';
				html += '<td>' + (i + 1) + '</td>';
				html += '<td>' + data.t.beanList[i].title + '</td>';
				if(data.t.beanList[i].smallimg != null && data.t.beanList[i].smallimg != 'NULL' ){
					html += '<td style="text-align:center;"><img src="'+ data.t.beanList[i].smallimg + '"height="64px"></td>';
				}else{
					html += '<td style="text-align:center;">无</td>';
				}
				if(data.t.beanList[i].bigimg != null && data.t.beanList[i].bigimg != 'NULL' ){
					html += '<td style="text-align:center;"><img src="'+ data.t.beanList[i].bigimg + '"height="64px"></td>';
				}else{
					html += '<td style="text-align:center;">无</td>';
				}
				if(data.t.beanList[i].bigimg != "NULL"){
					html += '<td style="text-align:center;">是</td>';
				}else{
					html += '<td style="text-align:center;">否</td>';
				};
				html += '<td>' + data.t.beanList[i].createtime + '</td>';
				html += '<td>';
				html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">编辑</a>';
				html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">删除</a>';
				if(data.t.beanList[i].bigimg != "NULL"){
					html += '<a class="button blue carousel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">取消轮播</a>';
				}else{
					html += '<a class="button blue carousel" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.t.beanList[i].id + '">设为轮播</a>';
				}
				
				html += '</td>';
				html += '</tr>';
			}
			$(".list_xk tbody").html(html);
			var thispopup = {
				"编辑": "popupEditAPPinfo.html",
				"删除": "APPinfo_deleteCustomerInformation_del.html",
				"+增加": "popupAddAPPinfo.html",
				"设为轮播": "popupAddAPPinfo_carousel.html",
			};
			$(".gt_wl_right a.button").unbind('click').bind('click',function(){
				var this_id = $(this).attr("zyl_id");
				$(".popwindow_xk .popup_xk").attr("this_id", this_id);
				$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
				if($(this).text() == "编辑"){
					getCustomerInformationById(this_id);
				}else if($(this).text() == "设为轮播"){
					updateCustomerInformation_z(this_id);
				}else if($(this).text() == "取消轮播"){
					noCarouselCustomerInformation(this_id);
				}
			});
			$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick',function(){
				$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
			});
			fenye_lv(data.t.tp, function() {
				getCustomerInformations();
			});
		}
	});
};

//根据id更新资讯信息 -- 用户
function getCustomerInformationById(id){
	$(".popbody_xk").on('click','img[alt="上传照片"]', function() {
		$('img[alt="上传照片"]').attr("class","");
		$(this).attr("class","thisname_lv_img_z");
			$(this).next("input:file").click();
	});

	$(".popbody_xk").on('change','input:file', function() {
		UploadImg_zyl($(this).attr("id"));
		
	});
	$.ajax(config.url + "cms/getCustomerInformationById", {
		data : {
			"id" : id,
		},
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
			if(data.success){
				$(".poptable2_xk_zyl tbody tr:eq(0) td input").val(data.t.title);
				$(".poptable2_xk_zyl tbody tr:eq(1)  td img").attr({"src": data.t.smallimg+"","img_id_z":data.t.smallimg});
				$(".poptable2_xk_zyl tbody tr:eq(2)  td img").attr({"src": data.t.detailsfigure+"","img_id_z":data.t.detailsfigure});
				$(".poptable2_xk_zyl tbody tr:eq(3)  td ").val(UE.getEditor('editor').setContent(data.t.content,true));;
				//$(".poptable2_xk_zyl tbody tr:eq(4)  td img").attr({"src": data.t.bigimg+"","img_id_z":data.t.bigimg});;
				updateCustomerInformation(id);
			}
		}
	});
};
//更新
function updateCustomerInformation(id){
	$(".popcomfirm_xk .save").click(function() {
			data_json = {
					"id" : id,
					"title" : $(".poptable2_xk_zyl tbody tr:eq(0)  td input").val(),
					"smallimg" : $(".poptable2_xk_zyl tbody tr:eq(1)  td img").attr("img_id_z"),
					"detailsfigure" : $(".poptable2_xk_zyl tbody tr:eq(2)  td img").attr("img_id_z"),
					"content" : UE.getEditor('editor').getContentTxt(),
					//"bigimg" : $(".poptable2_xk_zyl tbody tr:eq(4)  td img").attr("img_id_z")
				}

		$.ajax(config.url + "cms/updateCustomerInformation", {
			data : data_json,
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				//console.log(data.success);
				alert(data.msg);
				
				$("popwindow_xk .popup_xk").html("");
				$(".popwindow_xk").css("visibility", "hidden");
				getCustomerInformations();
			}
		});
		
	});
};

function updateCustomerInformation_z(id){
	$(".popbody_xk").on('click','img[alt="上传照片"]', function() {
		$('img[alt="上传照片"]').attr("class","");
		$(this).attr("class","thisname_lv_img_z");
			$(this).next("input:file").click();
	});

	$(".popbody_xk").on('change','input:file', function() {
		UploadImg_zyl($(this).attr("id"));
		
	});
	$(".popcomfirm_xk .save").click(function() {
		data_json = {
			"id" : id,
			"bigimg" : $(".poptable2_xk_zyl tbody tr:eq(0)  td img").attr("img_id_z")
		};
			$.ajax(config.url + "cms/updateCustomerInformation", {
				data : data_json,
				dataType : "json",
				type : "POST",
				timeout : 10000,
				success : function(data) {
					alert(data.msg);
					
					$("popwindow_xk .popup_xk").html("");
					$(".popwindow_xk").css("visibility", "hidden");
					getCustomerInformations();
				}
			});
			
		});
}


//取消
function noCarouselCustomerInformation(id){
		data_json = {
			"id" : id,
		}
		$.ajax(config.url + "cms/noCarouselCustomerInformation", {
			data : data_json,
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				//console.log(data.success);
				alert(data.msg);
				
				$("popwindow_xk .popup_xk").html("");
				$(".popwindow_xk").css("visibility", "hidden");
				getCustomerInformations();
			}
		});
}

getInformations();