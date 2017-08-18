$(document).ready(function() {
	tpa = 1;
	var cId = [];

	function getCarList() {
		$.ajax(config.url + "vehicle/getCooperation", {
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					data.obj == null ? data = data.t : data = data.obj;
				//	console.log(data);
					html = "";
					for(one in data) {
					html += '<dl>';
					html += '<dt><b cId="' + data[one].id + '"><img src="img/uncherked.jpg"/></b>' + data[one].cName + '</dt>';
					html += '<dd>';
					html += '</dd>';
					html += '</dl>';
				}
					/*for (one in data) {
						html += "<dl>";
						html += '<dt ><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' + one+ '</dt>';
						html+="<dd>";
						for(two in data[one]){
							html +="<dl>";
							html +='<dt><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg"/></b>' +two+ '</dt>';
							html +="<dd>";
							for(three in data[one][two]){
								html +="<dl>";
									html +='<dt><i><img style="height:18px;" src="img/u426.png"/></i><b class="cherked_xk" vId="' + data[one][two][three].simNo + '"><img src="img/uncherked.jpg" defaultChecked/></b>' + data[one][two][three].num + '</dt>';
								html+="</dl>";
							}
							html+="</dd>";
							html+="</dl>";
							
						}
						html+="</dd>";
						html+="</dl>";
					}*/
					$("dd#carList").html(html);
				}
			}
		});
	}

	function searchFresh() {
		$("#search").click(function() {
			cId = [];
			vId = [];
			for(var i = 0; i < $("#carList img").length; i++) {
				if($("#carList img:eq(" + i + ")").attr("src") == "img/cherked.jpg") {
					if($("#carList img:eq(" + i + ")").parent("b").attr("vId") != undefined && $("#carList img:eq(" + i + ")").parent("b").attr("vId") != null) {
						vId = $("#carList img:eq(" + i + ")").parent("b").attr("vId");
					} else {
						cId.push($("#carList img:eq(" + i + ")").parent("b").attr("cId"));
					}
				}
			}
			credentialPie();
		});
	};
	//饼状图
	function credentialPie() {
		$.ajax(config.url + "charts/validCheck", {
			data: {
				"type": $(".select").val(),
				"coop": cId,
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					data.obj == null ? data = data.t : data = data.obj;
					$('.list_xk').highcharts({
						chart: {
							plotBackgroundColor: null,
							plotBorderWidth: null,
							plotShadow: false
						},
						title: {
							text: data.name
						},
						tooltip: {
							pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
						},
						plotOptions: {
							pie: {
								allowPointSelect: true,
								cursor: 'pointer',
								dataLabels: {
									enabled: true,
									format: '<b>{point.name}</b>: {point.percentage:.1f} %',
									style: {
										color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
									}
								}
							}
						},
						series: [{
							type: 'pie',
							name: '有效期占比',
							data: data.data
						}]
					});
				}
			}
		})
	}
	getCarList();
	credentialPie();
	searchFresh();
});