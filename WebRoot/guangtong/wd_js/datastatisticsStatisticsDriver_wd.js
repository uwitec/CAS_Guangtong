$(document).ready(function() {
	function getNewDriverCount() {
		$.ajax(config.url + "charts/getNewDriverCount", {
			data: {
				"startTime": $(".time_plugin_lv_onclick:eq(0)").val(),
				"endTime": $(".time_plugin_lv_onclick:eq(1)").val(),
				"type": $(".select").val()
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				if(data.success == true) {
					data.obj == null ? data = data.t : data = data.obj;
					$('.list_xk').highcharts({
						chart: {
							type: 'column'
						},
						title: {
							text: data.title
						},
						xAxis: {
							categories: data.xAxis
						},
						yAxis: {
							min: 0,
							title: {
								text: data.yAxis
							},
							stackLabels: {
								enabled: true,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
								}
							}
						},
						legend: {
							align: 'right',
							x: -30,
							verticalAlign: 'top',
							y: 25,
							floating: true,
							backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
							borderColor: '#CCC',
							borderWidth: 1,
							shadow: false
						},
						tooltip: {
							formatter: function() {
								return '<b>' + this.x + '</b><br/>' +
									this.series.name + ': ' + this.y + '<br/>' +
									'总量: ' + this.point.stackTotal;
							}
						},
						plotOptions: {
							column: {
								stacking: 'normal',
								dataLabels: {
									enabled: true,
									color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
									style: {
										textShadow: '0 0 3px black'
									}
								}
							}
						},
						series: data.data
					});
				}
			}
		})

	}

	function searchFresh() {
		$("#search").click(function() {
			getNewDriverCount();
		});
	};
	getNewDriverCount();
	searchFresh();
})