tpa = 1;
var query_zyl = "";

// 编辑-获取表格内容
function getCooperationByAdmin(this_id) {
	data_json = {
		"id" : this_id,	
	};
	$.ajax(config.url + "AdminCooperation/getCooperationByAdmin", {
		data: data_json,
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			//console.log(data.success);
			if(data.success){
				for(var i=0; i<data.obj.length;i++){
					//console.log(data.obj[i].cName)
					$(".admin_list_zyl").append('<li value="' + data.obj[i].id + '" zyl_id="' + data.obj[i].id + '">' + data.obj[i].cName + '</li>');
				}
				$(".admin_list_zyl li").click(function(){
					if($(this).attr("class") == "active_zyl"){
						$(this).attr("class","");
					}else{
						$(this).attr("class","active_zyl");
					}
				})
				toUpdateAdmin(this_id);
			}
			
		}
	});
}
function toUpdateAdmin(this_id) {
	$.ajax(config.url + "cms/toUpdateAdmin", {
		data : {
			"id" : this_id,
		},
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
			if (data.success) {
				data.obj = data.t.admin;
				$(".poptable2_xk_zyl tbody tr td span").text(data.obj.id);
				/*if (data.obj.parentName == null) {
					$(".poptable2_xk_zyl tbody tr:eq(1)  td select").append('<option>无</option>');
				} else {
					$(".poptable2_xk_zyl tbody tr:eq(1)  td select").append('<option value="' + data.obj.pid + '">' + data.obj.parentName + '</option>');
				}*/
				$(".poptable2_xk_zyl tbody tr:eq(1)  td input").val(data.obj.username);
				$(".poptable2_xk_zyl tbody tr:eq(2)  td input").val("123456");
				$(".poptable2_xk_zyl tbody tr:eq(3)  td input").val(data.obj.startTime);
				$(".poptable2_xk_zyl tbody tr:eq(4)  td input").val(data.obj.endTime);
				/*for(var i=0;i<data.t.cooperation.length;i++){
					console.log(data.t.cooperation[i].id);
					if($(".poptable2_xk_zyl tbody tr:eq(6) td ul li").attr("zyl_id") == data.t.cooperation[i].id ){
						$(".poptable2_xk_zyl tbody tr:eq(6) td ul li").attr("class","active_zyl");
					}else{
						$(".poptable2_xk_zyl tbody tr:eq(6) td ul li").attr("class","");
					}
				}*/
				$(".poptable2_xk_zyl tbody tr:eq(5) td ul li").each(function() {
					for(one in data.t.cooperation){
						if ($(this).attr("zyl_id") == data.t.cooperation[one].id) {
							$(this).attr("class","active_zyl");
						} 
					}
						
				});
				var updatepassword = "";
				updateAdmin(this_id, updatepassword);
			}
		}
	});
};

/* 更新 */
function updateAdmin(this_id, updatepassword) {
	$(".popbody_xk").on("input",".poptable2_xk_zyl tbody tr:eq(2) td input",function() {
		updatepassword = $(".poptable2_xk_zyl tbody tr:eq(2)  td input").val();
	});
	//多选框
	var arr = [];
	$(".popcomfirm_xk .save").click(function() {
		$(".admin_list_zyl li[class=active_zyl]").each(function(){
			arr.push($(this).attr("zyl_id"));
		})
		data_json = {
			"id" : this_id,
			//"parentId" : $(".poptable2_xk_zyl tbody tr:eq(1)  td select option").val(),
			"username" : $(".poptable2_xk_zyl tbody tr:eq(1)  td input").val(),
			"password" : updatepassword,
			// "atype": $(".poptable2_xk_zyl tbody tr:eq(4) td input").val(),
			"startTime" : $(".poptable2_xk_zyl tbody tr:eq(3)  td input").val(),
			"endTime" : $(".poptable2_xk_zyl tbody tr:eq(4)  td input").val(),
			"cooperationArr[]":$.unique(arr),
		}
		$.ajax(config.url + "cms/updateAdmin", {
			data : data_json,
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				//console.log(data.success);
				if (data.success) {
					alert(data.msg);
					$("popwindow_xk .popup_xk").html("");
					$(".popwindow_xk").css("visibility", "hidden");
					getAdminsByPage();
				}
			}
		});
		
	});

};

// 双击事件弹窗
function toUpdateAdmin_dblclick(this_id) {
	$.ajax(config.url + "cms/toUpdateAdmin", {
		data : {
			"id" : this_id,
		},
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
			if (data.success) {
				data.obj = data.t;
				$(".poptable2_xk_zyl ul li:eq(0)  span").text(data.obj.admin.id);
				$(".poptable2_xk_zyl ul li:eq(1)  span").text(data.obj.admin.username);
				if (data.obj.admin.parentName == null) {
					$(".poptable2_xk_zyl ul li:eq(2)  span").text("无");
				} else {
					$(".poptable2_xk_zyl ul li:eq(2)  span").text(data.obj.admin.parentName);
				}
				$(".poptable2_xk_zyl ul li:eq(3)  span").text(data.obj.admin.endTime);
				$.ajax(config.url + "cms/getAllMenuAndCooperation", {
					data : {
						"adminId" : this_id,
						"selCondition":"",
					},
					dataType : "json",
					type : "POST",
					timeout : 10000,
					success : function(data) {
						if (data.success) {
							data.obj = data.t;

							//车队
							html = "";
							html += "<dl>";
							html += '<dt><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>全部车辆</dt>';
							html += "<dd>";
							for (one in data.obj.cooperations) {
								html += "<dl>";
								html += '<dt ><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + one+ '</dt>';
								html+="<dd>";
								for(two in data.obj.cooperations[one]){
									
									//console.log(two.indexOf("TEAM"));
									if(two.indexOf("TEAM")!=-1){
										for(three in data.obj.cooperations[one][two]){
											html +="<dl>";
											if(data.obj.cooperations[one][two][three].adminid != null){
												html +='<dt zyl_id="' + data.obj.cooperations[one][two][three].id + '" ><i></i><b class="cherked_xk"><img src="img/uncherked.jpg" defaultChecked/></b>' + data.obj.cooperations[one][two][three].num + '</dt>';
											}else{
												html +='<dt zyl_id="' + data.obj.cooperations[one][two][three].id + '" ><i></i><b class="cherked_xk"><img src="img/uncherked.jpg" /></b>' + data.obj.cooperations[one][two][three].num + '</dt>';
											}
											html+="</dl>";
										}
									}
									else{
										html +="<dl>";
										html +='<dt><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' +two+ '</dt>';
										html +="<dd>";
										for(three in data.obj.cooperations[one][two]){
											html +="<dl>";
											if(data.obj.cooperations[one][two][three].adminid != null  ){
												html +='<dt zyl_id="' + data.obj.cooperations[one][two][three].id + '" ><i></i><b class="cherked_xk"><img src="img/uncherked.jpg" defaultChecked/></b>' + data.obj.cooperations[one][two][three].num + '</dt>';
											}else{
												html +='<dt zyl_id="' + data.obj.cooperations[one][two][three].id + '" ><i></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + data.obj.cooperations[one][two][three].num + '</dt>';
											}
											html+="</dl>";
										}
										html+="</dd>";
										html+="</dl>";
									}
								}
								html+="</dd>";
								html+="</dl>";
							}
							html += "</dd>";
							html += "</dl>";
							$("#cooperations").html(html);
							$("#cooperations [defaultChecked]").click().removeAttr("defaultChecked");
							
							html = "";
							// 权限
							for (var i = 0; i < data.obj.menus.length; i++) {
								html += '<dl>';
								if (data.obj.menus[i].isPermissions == 1) {
									html += '<dt zyl_id="' + data.obj.menus[i].id + '"><i><img src="img/u380.png"/></i><b class="cherked_xk"><img src="img/cherked.jpg"/></b>' + data.obj.menus[i].name + '</dt>';
								} else {
									html += '<dt zyl_id="' + data.obj.menus[i].id + '"><i><img src="img/u380.png"/></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + data.obj.menus[i].name + '</dt>';
								}
								;
								html += '<dd><dl>';
								if (data.obj.menus[i].children != null) {
									for (var j = 0; j < data.obj.menus[i].children.length; j++) {
										if (data.obj.menus[i].children[j].isPermissions == 1) {
											html += '<dt style="margin-left:18px;" zyl_id="' + data.obj.menus[i].children[j].id + '"><b class="cherked_xk"><img src="img/cherked.jpg"/></b>' + data.obj.menus[i].children[j].name + '</dt>';
										} else {
											html += '<dt style="margin-left:18px;" zyl_id="' + data.obj.menus[i].children[j].id + '"><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + data.obj.menus[i].children[j].name + '</dt>';
										}
									}
								}
								html += '</dl></dd>';
								html += '</dl>';
							}
							$("#menus").html(html);
							popupresize();
						}
					}
				});
			}
		}
	});
};
// 编辑权限
function getAllMenuAndCooperation(this_id) {
	$.ajax(config.url + "cms/getAllMenuAndCooperation", {
		data : {
			"adminId" : this_id,
			"selCondition":$(".quiry_vehicle").val(),
		},
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
			if (data.success) {
				data.obj = data.t;
				//车队
				html = "";
				html += "<dl>";
				html += '<dt><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>全部车辆</dt>';
				html += "<dd>";
				for (one in data.obj.cooperations) {
					html += "<dl>";
					html += '<dt ><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + one+ '</dt>';
					html+="<dd>";
					for(two in data.obj.cooperations[one]){
						
						//console.log(two.indexOf("TEAM"));
						if(two.indexOf("TEAM")!=-1){
							for(three in data.obj.cooperations[one][two]){
								html +="<dl>";
								if(data.obj.cooperations[one][two][three].adminid != null){
									html +='<dt zyl_id="' + data.obj.cooperations[one][two][three].id + '" ><i></i><b class="cherked_xk"><img src="img/uncherked.jpg" defaultChecked/></b>' + data.obj.cooperations[one][two][three].num + '</dt>';
								}else{
									html +='<dt zyl_id="' + data.obj.cooperations[one][two][three].id + '" ><i></i><b class="cherked_xk"><img src="img/uncherked.jpg" /></b>' + data.obj.cooperations[one][two][three].num + '</dt>';
								}
								html+="</dl>";
							}
						}
						else{
							html +="<dl>";
							html +='<dt><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' +two+ '</dt>';
							html +="<dd>";
							for(three in data.obj.cooperations[one][two]){
								html +="<dl>";
								if(data.obj.cooperations[one][two][three].adminid != null  ){
									html +='<dt zyl_id="' + data.obj.cooperations[one][two][three].id + '" ><i></i><b class="cherked_xk"><img src="img/uncherked.jpg" defaultChecked/></b>' + data.obj.cooperations[one][two][three].num + '</dt>';
								}else{
									html +='<dt zyl_id="' + data.obj.cooperations[one][two][three].id + '" ><i></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + data.obj.cooperations[one][two][three].num + '</dt>';
								}
								html+="</dl>";
							}
							html+="</dd>";
							html+="</dl>";
						}
						
						
					}
					html+="</dd>";
					html+="</dl>";
				}
				html += "</dd>";
				html += "</dl>";
				$("#cooperations").html(html);
				$("#cooperations [defaultChecked]").click().removeAttr("defaultChecked");
				// 权限
				html = "";
			
				for (var i = 0; i < data.obj.menus.length; i++) {
					html += '<dl>';
					if (data.obj.menus[i].isPermissions == 1) {
						html += '<dt zyl_id="' + data.obj.menus[i].id + '"><i><img src="img/u380.png"/></i><b class="cherked_xk"><img src="img/cherked.jpg"/></b>' + data.obj.menus[i].name + '</dt>';
					} else {
						html += '<dt zyl_id="' + data.obj.menus[i].id + '"><i><img src="img/u380.png"/></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + data.obj.menus[i].name + '</dt>';
					}
					;
					html += '<dd><dl>';
					if (data.obj.menus[i].children != null) {
						for (var j = 0; j < data.obj.menus[i].children.length; j++) {
							if (data.obj.menus[i].children[j].isPermissions == 1) {
								//if(data.obj.menus[i].children[j].name != "管理员管理"){
									html += '<dt  zyl_id="' + data.obj.menus[i].children[j].id + '"><i></i><b class="cherked_xk"><img src="img/cherked.jpg"/></b>' + data.obj.menus[i].children[j].name + '</dt>';
								//}
							} else {
								//if(data.obj.menus[i].children[j].name != "管理员管理"){
									html += '<dt  zyl_id="' + data.obj.menus[i].children[j].id + '"><i></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + data.obj.menus[i].children[j].name + '</dt>';
								//}
							}
							if (data.obj.menus[i].children[j].children != null) {
								html += '<dd><dl>';
								for (var k = 0; k < data.obj.menus[i].children[j].children.length; k++) {
									if (data.obj.menus[i].children[j].children[k].isPermissions == 1) {
										html += '<dt zyl_id="' + data.obj.menus[i].children[j].children[k].id + '"><b class="cherked_xk"><img src="img/cherked.jpg"/></b>' + data.obj.menus[i].children[j].children[k].name + '</dt>';
									} else {
										html += '<dt zyl_id="' + data.obj.menus[i].children[j].children[k].id + '"><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + data.obj.menus[i].children[j].children[k].name + '</dt>';
									}
								}
								;
								html += '</dl></dd>';
							}
							;
						}
					}
					html += '</dl></dd>';
					html += '</dl>';
				}
				$("#menus").html(html);
				popupresize();
				batchAddMenuPermission();
			}
		}
	});
};

// 编辑权限-保存(更新)
function batchAddMenuPermission() {
	$(".save").click(function() {
		var this_menuIdArr_zyl = [];
		var this_id = $(".popwindow_xk .popup_xk").attr("this_id");
		var this_cooperationArr_zyl = [];
		$("#menus").find("img[src='img/cherked.jpg']").each(function() {
			this_menuIdArr_zyl.push($(this).parent("b").parent("dt").attr("zyl_id"));
		});
		$("#cooperations").find("img[src='img/cherked.jpg']").each(function() {
			if ($(this).parents("dt").siblings("dd").length == 0) {
				this_cooperationArr_zyl.push($(this).parent("b").parent("dt").attr("zyl_id"));
			}
		});
		$.ajax(config.url + "cms/batchAddMenuPermission", {
			data : {
				"adminId" : this_id,
				"menuIdArr" : this_menuIdArr_zyl,
				"vehicleArr" : this_cooperationArr_zyl
			},
			dataType : "json",
			type : "POST",
			timeout : 10000,
			success : function(data) {
				console.log(data.success);
				alert(data.msg);
				
				$("popwindow_xk .popup_xk").html("");
				$(".popwindow_xk").css("visibility", "hidden");
				getAdminsByPage();
			}
		});
		
	})
};

// 查询全部
function getAdminsByPage() {
	$.ajax(config.url + "cms/getAdminsByPage", {
		data : {
			"currentPage" : tpa,
			"pageCount" : 10,
			"condition" : query_zyl
		},
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
			if (data.success) {
				data.obj = data.t
				//console.log(data.t.beanList[0].id)
				$(".list_xk tfoot tr td").html("第" + data.obj.currentPage + "页 共" + data.obj.tp + "页 共" + data.obj.totalCount + "条")
				html = "";
				for (var i = 0; i < data.obj.beanList.length; i++) {
					html += '<tr zyl_id="' + data.obj.beanList[i].id + '">';
					html += '<td>' + (i + 1) + '</td>';
					html += '<td>' + data.obj.beanList[i].username + '</td>';
					/*
					 * if (data.obj.beanList[i].parentName == null) { html += '<td></td>'; }
					 * else { html += '<td>' + data.obj.beanList[i].parentName + '</td>'; };
					 */
					
					if (data.obj.beanList[i].loginTime == null) {
						html += '<td></td>';
					} else {
						html += '<td>' + data.obj.beanList[i].loginTime + '</td>';
					}
					;
					if (data.obj.beanList[i].atype == 1) {
						html += '<td>超级管理员</td>';
					} else {
						html += '<td>普通管理员</td>';
					}
					;
					if (data.obj.beanList[i].isValid == 1) {
						html += '<td>有效</td>';
					} else {
						html += '<td>无效</td>';
					}
					;
					html += '<td>';
					html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">编辑</a>';
					if(data.obj.beanList[i].atype == 0){
						html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">删除</a>';
						html += '<a class="button blue edit" style="margin:0 1px;padding:0 12px;line-height:24px;" zyl_id="' + data.obj.beanList[i].id + '">编辑权限</a>';
					};
					html += '</td>';
					html += '</tr>';
				}
				$(".list_xk tbody").html(html);
				var thispopup = {
					"编辑权限" : "popupEditadminpermission.html",
					"编辑" : "popupEditadmin.html",
					"删除" : "systeminfoAdmin_del.html",
					"+增加" : "popupAddadmin.html",
					"+增加子账户" : "popupAddadmin.html",
					"查询" : "",
					"sider" : "popupDetailsadmin.html"
				};
				$(".gt_wl_right a.button").unbind('click').bind('click', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").attr("this_id", this_id);
					$(".popwindow_xk .popup_xk").html(popup(thispopup[$(this).text()]));
					if ($(this).text() == "编辑") {
						getCooperationByAdmin(this_id);
					} else if ($(this).text() == "编辑权限") {
						getAllMenuAndCooperation(this_id);
					}
				});
				$(".gt_wl_right .list_xk tbody tr").unbind('dblclick').bind('dblclick', function() {
					var this_id = $(this).attr("zyl_id");
					$(".popwindow_xk .popup_xk").html(popup(thispopup["sider"]));
					toUpdateAdmin_dblclick(this_id);
				});
				fenye_lv(data.obj.tp, function() {
					getAdminsByPage();
				});
			}
		}
	});
};
// 查询
$(".button").click(function() {
	if ($(this).text() == "查询") {
		query_zyl = $(".query").val();
		getAdminsByPage();
	}
});
//模糊查询
	$(".quiry_vehicle_zyl").click(function(){
		var this_id = $(".popwindow_xk .popup_xk").attr("this_id");
		getAllMenuAndCooperation(this_id);
	})

if ($(".list_xk tbody tr").length == 1) {
	getAdminsByPage();
};