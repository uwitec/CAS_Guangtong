var port;
var data_json;
switch(char_wd.charType) {
	case "mix":
		mixChar(char_wd.charName);
		break;
	case "pie":
		piePic(char_wd.charName);
		break;
	case "line":
		linePic(char_wd.charName);
		break;
	case "column":
		columnPic(char_wd.charName);
	default:
		// alert("none");
}
// 混合统计图
function mixChar(content) {
	// 订单统计
	if(content == "orderForm") {
		port = "vehicle/chartsOfTotal";
		data_json = {
			"startTime": char_wd.startTime,
			"endTime": char_wd.endTime,
			"simNo": char_wd.simNo
		}
	} else if(content == "Onlinerate") {
		port = "vehicle/chartsOfTotal";
		data_json = {
			"startTime": char_wd.startTime,
			"endTime": char_wd.endTime,
			"coop": char_wd.cIdArr,
			"vehArr": char_wd.vIdArr,
			"type": char_wd.type
		}
	}
	$
		.ajax(
			config.url + port, {
				data: data_json,
				dataType: "json",
				type: "POST",
				async: false,
				timeout: 10000,
				success: function(data) {
					if(data.success == true) {
						data.obj == null ? data = data.t :
							data = data.obj;
						$('#container_wd')
							.highcharts({
								title: {
									text: data.other.text
								},
								xAxis: {
									categories: data.other.xAxis
								},
								labels: {
									items: [{
										html: data.other.html,
										style: {
											left: '100px',
											top: '18px',
											color: (Highcharts.theme && Highcharts.theme.textColor) ||
												'black'
										}
									}]
								},
								series: data.data
							});
					}
				}
			})
}

// 饼状图
function piePic(content) {
	if(content == "CustomersPie"){
		port = "customer/getCustomersPie";
		$.ajax(
			config.url + port, {
				data: data_json,
				dataType: "json",
				type: "POST",
				async: false,
				timeout: 10000,
				success: function(data) {
					if(data.success == true) {
						data.obj == null ? data = data.t :
							data = data.obj;
						$('#container_wd')
							.highcharts({
								chart: {
									plotBackgroundColor: null,
									plotBorderWidth: null,
									plotShadow: false
								},
								title: {
									text: data.title
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
												color: (Highcharts.theme && Highcharts.theme.contrastTextColor) ||
													'black'
											}
										}
									}
								},
								series: [{
									type: 'pie',
									name: '地区占比',
									data: data.data
								}]
							});
					}
				}
			})
	}else if(content == "VehiclesPie"){
		port = "vehicle/getVehiclesPie";
		$.ajax(
			config.url + port, {
				data: data_json,
				dataType: "json",
				type: "POST",
				async: false,
				timeout: 10000,
				success: function(data) {
					if(data.success == true) {
						data.obj == null ? data = data.t :
							data = data.obj;
						$('#container_wd')
							.highcharts({
								chart: {
									plotBackgroundColor: null,
									plotBorderWidth: null,
									plotShadow: false
								},
								title: {
									text: data.title
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
												color: (Highcharts.theme && Highcharts.theme.contrastTextColor) ||
													'black'
											}
										}
									}
								},
								series: [{
									type: 'pie',
									name: '地区占比',
									data: data.data
								}]
							});
					}
				}
			})
	
	}else if(content == "OrderPie"){
		 $('#container_wd').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        title: {
		            text: '订单比例分析'
		        },
		        tooltip: {
		            headerFormat: '{series.name}<br>',
		            pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: false
		                },
		                showInLegend: true
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: '财务占比',
		            data: [
		                ['集港物流',   20],
		                ['危险品',  30],
		                ['干线运输', 30],
		                ['冷链运输', 10],
		                ['仓储',10]
		            ]
		        }]
		    });
	}
	else{
		// 终端报警
		data_json = {
			"startTime": char_wd.startTime,
			"endTime": char_wd.endTime,
			"simNo": char_wd.simNo
		}
		if(content == 'Terminalalarm') {
			port = "charts/getTerminalChartsTotal";
		} else if(content == 'Platformalarm') {
			port = "charts/getPlatformAlarmsTotal";
		}
		$
			.ajax(
				config.url + port, {
					data: data_json,
					dataType: "json",
					type: "POST",
					async: false,
					timeout: 10000,
					success: function(data) {
						if(data.success == true) {
							data.obj == null ? data = data.t :
								data = data.obj;
							$('#container_wd')
								.highcharts({
									chart: {
										plotBackgroundColor: null,
										plotBorderWidth: null,
										plotShadow: false
									},
									title: {
										text: data.title
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
													color: (Highcharts.theme && Highcharts.theme.contrastTextColor) ||
														'black'
												}
											}
										}
									},
									series: [{
										type: 'pie',
										name: '报警占比',
										data: data.data
									}]
								});
						}
					}
				})
	}
}
// 折现图
function linePic(content) {
	var text;
	var unit;
	if(content == "Datareport") {
		text = "数据上报统计";
		unit = "条数";
		port = "charts/getChartOfDataReport";
		data_json = {
			"startTime": char_wd.startTime,
			"endTime": char_wd.endTime,
			"type": char_wd.type,
			"simNo": char_wd.simNo
		}
	} else if(content == "Onlinerate") {
		text = "上线率统计";
		unit = "%(百分比)";
		port = "charts/getChartOfOnlineRate";
		data_json = {
			"startTime": char_wd.startTime,
			"endTime": char_wd.endTime,
			"type": char_wd.type,
			"simNo": char_wd.vIdArr
		}
	} else {
		text = "油量和油耗情况统计";
		unit = "油量 (L)";
		port = "charts/findFuleStatisticsCharts";
		data_json = {
			"startTime": char_wd.startTime,
			"endTime": char_wd.endTime,
			"type": char_wd.type,
			"simNo": char_wd.simNo
		}

	}
	$.ajax(config.url + port, {
		data: data_json,
		dataType: "json",
		type: "POST",
		async: false,
		timeout: 10000,
		success: function(data) {
			if(data.success == true) {
				data.obj == null ? data = data.t : data = data.obj;
				if(text == "上线率统计") {
					$('#container_wd').highcharts({
						title: {
							text: text,
							x: -20
						},
						xAxis: {
							categories: data.data.xAxis
						},
						yAxis: {
							title: {
								text: unit
							},
							plotLines: [{
								value: 0,
								width: 1,
								color: '#808080'
							}],
							max: 100
						},
						tooltip: {
							valueSuffix: unit
						},
						legend: {
							layout: 'vertical',
							align: 'right',
							verticalAlign: 'middle',
							borderWidth: 0
						},
						series: data.data.series
					});

				} else {
					$('#container_wd').highcharts({
						title: {
							text: text,
							x: -20
						},
						xAxis: {
							categories: data.data.xAxis
						},
						yAxis: {
							title: {
								text: unit
							},
							plotLines: [{
								value: 0,
								width: 1,
								color: '#808080'
							}],
						},
						tooltip: {
							valueSuffix: unit
						},
						legend: {
							layout: 'vertical',
							align: 'right',
							verticalAlign: 'middle',
							borderWidth: 0
						},
						series: data.data.series
					});
				}
			}
		}
	})
}
function columnPic(content){
	 $('#container_wd').highcharts({
	        chart: {
	            type: 'bar'
	        },
	        title: {
	            text: '各车型交易额条形图'
	        },
//	        subtitle: {
//	            text: '数据来源: Wikipedia.org'
//	        },
	        xAxis: {
	            categories: ['集港物流', '危险品车', '干线运输', '冷链配送','底舱配送'],
	            title: {
	                text: null
	            }
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: '收入总数 (万)',
	                align: 'high'
	            },
	            labels: {
	                overflow: 'justify'
	            }
	        },
	        tooltip: {
	            valueSuffix: '万'
	        },
	        plotOptions: {
	            bar: {
	                dataLabels: {
	                    enabled: true,
	                    allowOverlap: true
	                }
	            }
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'top',
	            x: -40,
	            y: 140,
	            floating: true,
	            borderWidth: 1,
	            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
	            shadow: true
	        },
	        credits: {
	            enabled: false
	        },
	        series: [{
	            name: '2015年',
	            data: [905, 1250, 1220,21, 30]
	        }, {
	            name: '2016 年',
	            data: [1100, 2000, 2234, 25, 40]
	        }, {
	            name: '2017 年',
	            data: [840,1400, 1450, 18, 35]
	        }]
	    });
	
}