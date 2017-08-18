tpa = 1;
// 编辑
$(".popbody_xk").on('click', 'img[alt="上传照片"]', function() {
	$('img[alt="上传照片"]').attr("class", "");
	$(this).attr("class", "thisname_lv_img");
	$(this).next("input:file").click();
})

$(".popbody_xk").on('change', 'input:file', function() {
	UploadImg($(this).attr("id"));

});
//编辑-下拉列表
function sVehicleInfoQuery(this_id) {

	$.ajax(config.url + "vehicle/sVehicleInfoQuery", {
		dataType : "json",
		type : "POST",
		timeout : 10000,
		success : function(data) {
			for (var i = 0; i < data.obj[0].length; i++) {
				$(".poptable2_xk_zyl tbody tr:eq(5) td select").append(
						'<option value="' + data.obj[0][i].id + '" zyl_id="'
								+ data.obj[0][i].id + '">'
								+ data.obj[0][i].desc + '</option>');
			}
			for (var j = 0; j < data.obj[1].length; j++) {
				$(".poptable2_xk_zyl tbody tr:eq(6) td select").append(
						'<option value="' + data.obj[1][j].id + '">'
								+ data.obj[1][j].sName + '</option>');
			}
			for(var k = 0; k < data.obj[2].length; k++){
				$(".poptable2_xk_zyl tbody tr:eq(9) td select").append('<option value="' + data.obj[2][k].id + '" zyl_id="' + data.obj[2][k].id + '">' + data.obj[2][k].cName + '</option>');
			}
			
			sVehicleInfoOne(this_id);
			$(".poptable2_xk_zyl tbody tr:eq(9) td select").change(
					function() {
						getVehicleTeamByCooperationId($(".poptable2_xk_zyl tbody tr:eq(9) td select option:selected")
								.attr("zyl_id"));
					}

				);
			
		}
	});
};
/**/
function getVehicleTeamByCooperationId(this_id,tId_z) {
	data_json_zyl = {
		"cooperationId": this_id,
	}
	$.ajax(config.url + "vehicleteam/getVehicleTeamByCooperationId", {
		data: data_json_zyl,
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			//console.log(data.success);
			if(data.success) {
				$(".poptable2_xk_zyl tbody tr:eq(10) td select").find(
					"option").remove();
				$(".poptable2_xk_zyl tbody tr:eq(10) td select").append(
						'<option value="">' + "请选择车队" + '</option>');
				for(var j = 0; j < data.obj.length; j++) {
					$(".poptable2_xk_zyl tbody tr:eq(10) td select").append(
						'<option value="' + data.obj[j].id + '"zyl_id="' + data.obj[j].id + '">' +
						data.obj[j].tName + '</option>');
				};
				$(".poptable2_xk_zyl tbody tr:eq(10) td select option").each(
						function() {
							if ($(this).val() == tId_z) {
								$(this).attr(
										"selected",
										"selected");
							} else {
								$(this).removeAttr(
										"selected");
							}
						});
			}
			
			
		}
	});
}
/**/
function sVehicleInfoOne(this_id) {
	$.ajax(
					config.url + "vehicle/sVehicleInfoOne",
					{
						data : {
							"id" : this_id,
						},
						dataType : "json",
						type : "POST",
						timeout : 10000,
						success : function(data) {
							if (data.success) {
								data.obj = data.t;
								teamId_z = 	data.obj.teamId
								//console.log(teamId_z);
								$(".poptable2_xk_zyl tbody tr td span").text(
										data.obj.id);
								$(".poptable2_xk_zyl tbody tr:eq(1) td input")
										.val(data.obj.num);
								$(".poptable2_xk_zyl tbody tr:eq(2) td input")
								.val(data.obj.nickname);
								$(".poptable2_xk_zyl tbody tr:eq(3) td input")
										.val(data.obj.vin);
								$(".poptable2_xk_zyl tbody tr:eq(4) td input")
										.val(data.obj.engineNum);
								$(
										".poptable2_xk_zyl tbody tr:eq(5) td select option")
										.each(
												function() {
													if ($(this).val() == data.obj.modelId) {
														$(this).attr(
																"selected",
																"selected");
													} else {
														$(this).removeAttr(
																"selected");
													}
												});
								$(
										".poptable2_xk_zyl tbody tr:eq(6) td select option")
										.each(
												function() {
													if ($(this).val() == data.obj.specialId) {
														$(this).attr(
																"selected",
																"selected");
													} else {
														$(this).removeAttr(
																"selected");
													}
												});

								$(".poptable2_xk_zyl tbody tr:eq(7) td input")
										.val(data.obj.simNo);
								$(".poptable2_xk_zyl tbody tr:eq(8) td input")
										.val(data.obj.vmcolor);
								$(
										".poptable2_xk_zyl tbody tr:eq(9) td select option")
										.each(
												function() {
													if ($(this).val() == data.obj.cooperationId) {
														$(this).attr(
																"selected",
																"selected");
													} else {
														$(this).removeAttr(
																"selected");
													}
												});
								/*$(".poptable2_xk_zyl tbody tr:eq(10) td select option").each(
										function() {
											if ($(this).val() == data.obj.teamId) {
												$(this).attr(
														"selected",
														"selected");
											} else {
												$(this).removeAttr(
														"selected");
											}
										});*/
								$(".poptable2_xk_zyl tbody tr:eq(11) td input")
										.val(data.obj.defaultDriverName);
								$(".poptable2_xk_zyl tbody tr:eq(12) td input")
										.val(data.obj.vmavalidDate);
								$(".poptable2_xk_zyl tbody tr:eq(13) td input")
										.val(data.obj.operationLicenseDate);
								$(".poptable2_xk_zyl tbody tr:eq(14) td input")
										.val(data.obj.operationYearCheck);
								$(".poptable2_xk_zyl tbody tr:eq(15) td input")
										.val(data.obj.secondMaintainDate);
								$(".poptable2_xk_zyl tbody tr:eq(16) td input")
										.val(data.obj.tcicompany);
								$(".poptable2_xk_zyl tbody tr:eq(17) td input")
										.val(data.obj.tcinum);
								$(".poptable2_xk_zyl tbody tr:eq(18) td input")
										.val(data.obj.tcivalidDate);
								$(".poptable2_xk_zyl tbody tr:eq(19) td input")
										.val(data.obj.tcitel);
								$(".poptable2_xk_zyl tbody tr:eq(20) td input")
										.val(data.obj.vcicompany);
								$(".poptable2_xk_zyl tbody tr:eq(21) td input")
										.val(data.obj.vcivalidDate);
								$(".poptable2_xk_zyl tbody tr:eq(22) td input")
										.val(data.obj.vcitype);
								$(".poptable2_xk_zyl tbody tr:eq(23) td input")
										.val(data.obj.vcinum);
								$(".poptable2_xk_zyl tbody tr:eq(24) td input")
										.val(data.obj.vcitel);
								$(
										".poptable2_xk_zyl tbody tr:eq(25) td select option")
										.each(
												function() {
													if ($(this).val() == data.obj.operationType) {
														$(this).attr(
																"selected",
																"selected");
													} else {
														$(this).removeAttr(
																"selected");
													}
												});
								$(".poptable2_xk_zyl tbody tr:eq(26) td input")
										.val(data.obj.reviewTime);
								$(
										".poptable2_xk_zyl tbody tr:eq(27) td select option")
										.each(
												function() {
													if ($(this).val() == data.obj.reviewStatus) {
														$(this).attr(
																"selected",
																"selected");
													} else {
														$(this).removeAttr(
																"selected");
													}
												});
								$(".poptable2_xk_zyl tbody tr:eq(28) td input")
										.val(data.obj.reviewNote);
								$(
										".poptable2_xk_zyl tbody tr:eq(29) td select option")
										.each(
												function() {
													if ($(this).val() == data.obj.available) {
														$(this).attr(
																"selected",
																"selected");
													} else {
														$(this).removeAttr(
																"selected");
													}
												});
								$(
										".poptable2_xk_zyl tbody tr:eq(30) td select option")
										.each(
												function() {
													if ($(this).val() == data.obj.receiveAutoSend) {
														$(this).attr(
																"selected",
																"selected");
													} else {
														$(this).removeAttr(
																"selected");
													}
												});
								$(
										".poptable2_xk_zyl tbody tr:eq(31) td select option")
										.each(
												function() {
													if ($(this).val() == data.obj.cooperationAvailable) {
														$(this).attr(
																"selected",
																"selected");
													} else {
														$(this).removeAttr(
																"selected");
													}
												});
								$(".poptable2_xk_zyl tbody tr:eq(32) td img")
										.attr(
												{
													"src" : config.url
															+ data.obj.vehicleImg
															+ "",
													"img_id" : data.obj.vehicleImg
												});
								$(".poptable2_xk_zyl tbody tr:eq(33) td img")
										.attr(
												{
													"src" : config.url
															+ data.obj.vehicleLicenseImg
															+ "",
													"img_id" : data.obj.vehicleLicenseImg
												});
								$(".poptable2_xk_zyl tbody tr:eq(34) td img")
										.attr(
												{
													"src" : config.url
															+ data.obj.operationLicenseImg
															+ "",
													"img_id" : data.obj.vehicleLicenseImg
												});
								getVehicleTeamByCooperationId(data.obj.cooperationId,teamId_z);
								uVehicleInfo(this_id);
							}
						}
					});
}
/* 更新 */
function uVehicleInfo(this_id) {
	//console.log(this_id);
	$(".popcomfirm_xk .save")
			.click(
					function() {
						data_json = {
							"id" : this_id,
							"num" : $(
									".poptable2_xk_zyl tbody tr:eq(1) td input")
									.val(),
							"nickname": $(
							".poptable2_xk_zyl tbody tr:eq(2) td input")
							.val(),
							"VIN" : $(
									".poptable2_xk_zyl tbody tr:eq(3) td input")
									.val(),
							"engineNum" : $(
									".poptable2_xk_zyl tbody tr:eq(4) td input")
									.val(),
							"modelId" : $(
									".poptable2_xk_zyl tbody tr:eq(5) td select option:selected")
									.val(),
							"specialId" : $(
									".poptable2_xk_zyl tbody tr:eq(6) td select option:selected")
									.val(),
							"simNo" : $(
									".poptable2_xk_zyl tbody tr:eq(7) td input")
									.val(),
							"VMColor" : $(
									".poptable2_xk_zyl tbody tr:eq(8) td input")
									.val(),
							"cooperationId" : $(
									".poptable2_xk_zyl tbody tr:eq(9) td select option:selected")
									.val(),
							"teamId" : $(
									".poptable2_xk_zyl tbody tr:eq(10) td select option:selected")
									.val(),
							"defaultDriverName" : $(
									".poptable2_xk_zyl tbody tr:eq(11) td input")
									.val(),
							"VMAvalidDate" : $(
									".poptable2_xk_zyl tbody tr:eq(12) td input")
									.val(),
							"operationLicenseDate" : $(
									".poptable2_xk_zyl tbody tr:eq(13) td input")
									.val(),
							"operationYearCheck" : $(
									".poptable2_xk_zyl tbody tr:eq(14) td input")
									.val(),
							"secondMaintainDate" : $(
									".poptable2_xk_zyl tbody tr:eq(15) td input")
									.val(),
							"TCICompany" : $(
									".poptable2_xk_zyl tbody tr:eq(16) td input")
									.val(),
							"TCINum" : $(
									".poptable2_xk_zyl tbody tr:eq(17) td input")
									.val(),
							"TCIValidDate" : $(
									".poptable2_xk_zyl tbody tr:eq(18) td input")
									.val(),
							"TCITel" : $(
									".poptable2_xk_zyl tbody tr:eq(19) td input")
									.val(),
							"VCICompany" : $(
									".poptable2_xk_zyl tbody tr:eq(20) td input")
									.val(),
							"VCIValidDate" : $(
									".poptable2_xk_zyl tbody tr:eq(21) td input")
									.val(),
							"VCIType" : $(
									".poptable2_xk_zyl tbody tr:eq(22) td input")
									.val(),
							"VCINum" : $(
									".poptable2_xk_zyl tbody tr:eq(23) td input")
									.val(),
							"VCITel" : $(
									".poptable2_xk_zyl tbody tr:eq(24) td input")
									.val(),
							"operationType" : $(
									".poptable2_xk_zyl tbody tr:eq(25) td select option:selected")
									.val(),
							"reviewTime" : $(
									".poptable2_xk_zyl tbody tr:eq(26) td input")
									.val(),
							"reviewStatus" : $(
									".poptable2_xk_zyl tbody tr:eq(27) td select option:selected")
									.val(),
							"reviewNote" : $(
									".poptable2_xk_zyl tbody tr:eq(28) td input")
									.val(),
							"available" : $(
									".poptable2_xk_zyl tbody tr:eq(29) td select option:selected")
									.val(),
							"receiveAutoSend" : $(
									".poptable2_xk_zyl tbody tr:eq(30) td select option:selected")
									.val(),
							"cooperationAvailable" : $(
									".poptable2_xk_zyl tbody tr:eq(31) td select option:selected")
									.val(),
							"vehicleImg" : $(
									".poptable2_xk_zyl tbody tr:eq(32) td img")
									.attr("img_id"),
							"vehicleLicenseImg" : $(
									".poptable2_xk_zyl tbody tr:eq(33) td img")
									.attr("img_id"),
							"operationLicenseImg" : $(
									".poptable2_xk_zyl tbody tr:eq(34) td img")
									.attr("img_id"),
						}
						$.ajax(config.url + "vehicle/uVehicleInfo", {
							data : data_json,
							dataType : "json",
							type : "POST",
							timeout : 10000,
							success : function(data) {
								//console.log(data.success);
								alert(data.msg);
								
								$("popwindow_xk .popup_xk").html("");
								$(".popwindow_xk").css("visibility", "hidden");
								sVehicleInfoA();
							}
						});
					
					});
}

// 双击弹窗
function sVehicleInfoOne_dblclick(this_id) {
	$
			.ajax(
					config.url + "vehicle/sVehicleInfoOne",
					{
						data : {
							"id" : this_id,
						},
						dataType : "json",
						type : "POST",
						timeout : 10000,
						success : function(data) {
							if (data.success) {
								data.obj = data.t;

								$(".poptable2_xk_zyl ul  li:eq(0) span").text(
										data.obj.id);
								$(".poptable2_xk_zyl ul  li:eq(1) span").text(
										data.obj.num);
								if(data.obj.nickname == null){
									$(".poptable2_xk_zyl ul  li:eq(2) span").text(
											"");
								}else{
									$(".poptable2_xk_zyl ul  li:eq(2) span").text(
											data.obj.nickname);
								}
								$(".poptable2_xk_zyl ul  li:eq(3) span").text(
										data.obj.engineNum);
								$(".poptable2_xk_zyl ul  li:eq(4) span").text(
										data.obj.desc);
								if (data.obj.specialId == 1) {
									$(".poptable2_xk_zyl ul  li:eq(5) span ")
											.text('集港物流');
								} else if (data.obj.specialId == 2) {
									$(".poptable2_xk_zyl ul  li:eq(5) span ")
											.text('危险品零售');
								} else if (data.obj.specialId == 3) {
									$(".poptable2_xk_zyl ul  li:eq(5) span ")
											.text('仓储配送');
								} else if (data.obj.specialId == 4) {
									$(".poptable2_xk_zyl ul  li:eq(5) span ")
											.text('冷链配送');
								} else if (data.obj.specialId == 5) {
									$(".poptable2_xk_zyl ul  li:eq(5) span ")
											.text('干线运输');
								} else if (data.obj.specialId == 6) {
									$(".poptable2_xk_zyl ul  li:eq(5) span ")
											.text('长途客车');
								} else if (data.obj.specialId == 7) {
									$(".poptable2_xk_zyl ul  li:eq(5) span ")
											.text('其他');
								}
								$(".poptable2_xk_zyl ul  li:eq(6) span").text(
										data.obj.simNo);
								if (data.obj.vmcolor == null) {
									$(".poptable2_xk_zyl ul  li:eq(7) span")
											.text("");
								} else {
									$(".poptable2_xk_zyl ul  li:eq(7) span")
											.text(data.obj.vmcolor);
								}
								if (data.obj.teamId == 1) {
									$(".poptable2_xk_zyl ul  li:eq(8) span")
											.text("常运");
								} else if (data.obj.teamId == 2) {
									$(".poptable2_xk_zyl ul  li:eq(8) span")
											.text("液压");
								} else {
									$(".poptable2_xk_zyl ul  li:eq(8) span")
											.text("待报废");
								}
								if(data.obj.cName == null){
									$(".poptable2_xk_zyl ul  li:eq(9) span").text(
											"");
								}else{
									$(".poptable2_xk_zyl ul  li:eq(9) span").text(
											data.obj.cName);
								}
								$(".poptable2_xk_zyl ul  li:eq(10) span").text(
										data.obj.defaultDriverName);
								$(".poptable2_xk_zyl ul  li:eq(11) span").text(
										data.obj.vmavalidDate);
								$(".poptable2_xk_zyl ul  li:eq(12) span").text(
										"");
								$(".poptable2_xk_zyl ul  li:eq(13) span").text(
										data.obj.operationLicenseDate);
								if (data.obj.secondMaintainDate == null) {
									$(".poptable2_xk_zyl ul  li:eq(14) span")
											.text(" ");
								} else {
									$(".poptable2_xk_zyl ul  li:eq(14) span")
											.text(data.obj.secondMaintainDate);
								}
								$(".poptable2_xk_zyl ul  li:eq(15) span").text(
										data.obj.tcicompany);
								$(".poptable2_xk_zyl ul  li:eq(16) span").text(
										data.obj.tcinum);
								$(".poptable2_xk_zyl ul  li:eq(17) span").text(
										data.obj.tcivalidDate);
								$(".poptable2_xk_zyl ul  li:eq(18) span").text(
										data.obj.tcitel);
								$(".poptable2_xk_zyl ul  li:eq(19) span").text(
										data.obj.vcicompany);
								$(".poptable2_xk_zyl ul  li:eq(20) span").text(
										data.obj.vcitype);
								$(".poptable2_xk_zyl ul  li:eq(21) span").text(
										data.obj.vcinum);
								$(".poptable2_xk_zyl ul  li:eq(22) span").text(
										data.obj.vcitel);
								if (data.obj.receiveAutoSend == 1) {
									$(".poptable2_xk_zyl ul  li:eq(23) span")
											.text("接收");
								} else {
									$(".poptable2_xk_zyl ul  li:eq(23) span")
											.text("不接收");
								}
								if (data.obj.available == 1) {
									$(".poptable2_xk_zyl ul  li:eq(24) span")
											.text("可用");
								} else {
									$(".poptable2_xk_zyl ul  li:eq(24) span")
											.text("冻结");
								}
								$(".poptable2_xk_zyl ul  li:eq(25) span")
										.append(
												"<img width='60px' src='"
														+ config.url
														+ data.obj.vehicleImg
														+ "'/>");
								$(".poptable2_xk_zyl ul  li:eq(26) span")
										.append(
												"<img width='60px' src='"
														+ config.url
														+ data.obj.vehicleLicenseImg
														+ "'/>");
								$(".poptable2_xk_zyl ul  li:eq(27) span")
										.append(
												"<img width='60px' src='"
														+ config.url
														+ data.obj.operationLicenseImg
														+ "'/>");
							}
						}
					});
}
// 查询全部
function sVehicleInfoA() {
	$
			.ajax(
					config.url + "vehicle/sVehicleInfoA",
					{
						data : {
							"currentPage" : tpa,
							"pageCount" : 10,
							"id" : $(".list_xk thead input[name='id']").val(),
							"dName" : $(".list_xk thead input[name='dName']")
									.val(),
							"num" : $(".list_xk thead input[name='num']").val(),
							"cName" : $(".list_xk thead input[name='cName']")
									.val(),
							"desc" : $(".list_xk thead input[name='desc']")
									.val(),
							"nickname" : $(".list_xk thead input[name='nickname']").val(),
							"tName" : $(".list_xk thead input[name='tName']").val(),
						},
						dataType : "json",
						type : "POST",
						timeout : 10000,
						success : function(data) {
							if (data.success) {
								data.obj = data.t;

								$(".list_xk tfoot tr td").html(
										"第" + data.obj.currentPage + "页 共"
												+ data.obj.tp + "页 共"
												+ data.obj.totalCount + "条")
								html = "";
								for (var i = 0; i < data.obj.beanList.length; i++) {
									html += '<tr zyl_id="'
											+ data.obj.beanList[i].id + '">';
									html += '<td class="cherked_xk"><img src="img/uncherked.jpg" alt="" /></td>';
									html += '<td>' + (i + 1) + '</td>';
									html += '<td>' + data.obj.beanList[i].id
											+ '</td>';
									if (data.obj.beanList[i].num == undefined) {
										html += '<td></td>';
									} else {
										html += '<td>'
												+ data.obj.beanList[i].num
												+ '</td>';
									}
									if(data.obj.beanList[i].nickname == undefined){
										html += '<td></td>';	
									}else{
										html += '<td>'+ data.obj.beanList[i].nickname + '</td>';	
									}
									if (data.obj.beanList[i].desc == undefined) {
										html += '<td></td>';
									} else {
										html += '<td>'
												+ data.obj.beanList[i].desc
												+ '</td>';
									}
									if (data.obj.beanList[i].cName == undefined) {
										html += '<td></td>	';
									} else {
										html += '<td>'
												+ data.obj.beanList[i].cName
												+ '</td>';
									}
									if(data.obj.beanList[i].tName == undefined || data.obj.beanList[i].tName == null || data.obj.beanList[i].tName.indexOf("TEAM")!=-1 ){
										html += '<td></td>';
									}else{
										html += '<td>'+ data.obj.beanList[i].tName + '</td>';
									}
									html += '<td></td>';
									html += '<td><div class="QR_code"></div></td>';
									html += '<td>';
									html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="'
											+ data.obj.beanList[i].id
											+ '">编辑</a>';
									if (data.obj.beanList[i].available == 1) {
										html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="'
												+ data.obj.beanList[i].id
												+ '">冻结</a>';
									} else {
										html += '<a class="button blue" style="margin:0 10px;padding:0 12px;line-height:24px;" zyl_id="'
												+ data.obj.beanList[i].id
												+ '">解冻</a>';
									}

									html += '</td>';
									html += '</tr>';
								}
								$(".list_xk tbody").html(html);
								var thispopup = {
									"编辑" : "popupEditvehicle.html",
									"删除" : "deleteconfirm.html",
									"+增加" : "popupAddvehicle.html",
									"冻结" : "transportationinfoVehicle_freezeconfirm.html",
									"批量冻结" : "transportationinfoVehicle_m_freezeconfirm.html",
									"解冻" : "transportationinfoVehicle_unfreezeconfirm.html",
									"批量解冻" : "transportationinfoVehicle_m_unfreezeconfirm.html",
									"查询" : "",
									"导入" : "importvehicle.html",
									"sider" : "popupDetailsvehicle.html",
									"运输能力模型分析":"popupChart.html"
									
								};
								$(".gt_wl_right a.button")
										.unbind('click')
										.bind(
												'click',
												function() {
													char_wd = {
															"charType": $(this).attr("chartype"),
															"charName": $(this).attr("charname"),
														}
													var this_id = $(this).attr(
															"zyl_id");
													$(".popwindow_xk .popup_xk")
															.attr("this_id",
																	this_id);
													$(".popwindow_xk .popup_xk")
															.html(
																	popup(thispopup[$(
																			this)
																			.text()]));
													if ($(this).text() == "编辑") {
														sVehicleInfoQuery(this_id);
													}
												});
								$(".gt_wl_right .list_xk tbody tr").unbind(
										'dblclick').bind(
										'dblclick',
										function() {
											var this_id = $(this)
													.attr("zyl_id");
											$(".popwindow_xk .popup_xk").html(
													popup(thispopup["sider"]));
											sVehicleInfoOne_dblclick(this_id);
										});
								for (var k = 0; k < data.obj.beanList.length; k++) {
									$('.QR_code:eq(' + k + ')').qrcode({
										render: "canvas",
										text : ""+data.obj.beanList[k].id,
										type : 'cn',
										width : 100,
										height : 100
									});
								}
								fenye_lv(data.obj.tp, function() {
									sVehicleInfoA();
								});
							}
						}
					});
};
// 导出
function vehicle_excel() {
	$(".listtitle_xk ").on(
			"click",
			".vehicle_excel",
			function() {
				// location.href = config.url + "vehicle/excel";
				location.href = config.url + "vehicle/excel?id="
						+ $(".list_xk thead input[name='id']").val() + "&dName"
						+ $(".list_xk thead input[name='dName']").val()
						+ "&num=" + $(".list_xk thead input[name='num']").val()
						+ "&cName="
						+ $(".list_xk thead input[name='cName']").val()
						+ "&desc="+ $(".list_xk thead input[name='desc']").val()
						+ "&nickname="+ $(".list_xk thead input[name='nickname']").val()
						+ "&tName="+ $(".list_xk thead input[name='tName']").val();
			})
};
// 导出模版
function exportTemplate_zyl() {
	$(".listtitle_xk ").on('click', ".template", function() {
		location.href = config.url + "vehicle/exportTemplate";
	})
};
exportTemplate_zyl();
vehicle_excel();
if ($(".list_xk tbody tr").length == 0) {
	sVehicleInfoA();
}
// 模糊搜索
function fuzzySearch() {
	$(".list_xk thead input").bind('input propertychange', function() {
		sVehicleInfoA();
	})
};
fuzzySearch();