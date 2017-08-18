$(document).ready(function(){
	//设置疲劳驾驶
	function driverTired(){
		var data_json = {
			"simno" : "98765432103 ",
			"vehicleid" : "102",
			"plateno" : " 测B00098"
		}
		$.ajax(config.url + "DangerGoods/driverTired", {
				data: data_json,
				dataType: "json",
				type: "POST",
				timeout: 10000,
				success: function(data) {
					//console.log(data);
				}
			});
	}
	//拍照  
	function monitorByCamera(){
		var data_json = {
			"tenantid" : "0",
			"plateno" : "测102",
			"simno" : "98765432103",
			"vehicleid" : "12"
		}
		$.ajax(config.url + "lineHau/monitorByCamera", {
				data: data_json,
				dataType: "json",
				type: "POST",
				timeout: 10000,
				success: function(data) {
					//console.log(data);
				}
			});
	}
	//位置上报策略  
	function vPositionTReport(){
		var data_json = {
			"tenantid" : "9",
			"plateno" : "102",
			"simno" : "98765432103",
			"vehicleid" : "121"
		}
		$.ajax(config.url + "port/vPositionTReport", {
				data: data_json,
				dataType: "json",
				type: "POST",
				timeout: 10000,
				success: function(data) {
					//console.log(data);
				}
			});
	}
	//临时位置追踪
	function vTimeInterval(){
		var data_json = {
			"tenantid" : "9",
			"plateno" : "102",
			"simno" : "98765432103",
			"vehicleid" : "12"
		}
		$.ajax(config.url + "port/vTimeInterval", {
				data: data_json,
				dataType: "json",
				type: "POST",
				timeout: 10000,
				success: function(data) {
					//console.log(data);
				}
			});
	}
	//插入线路 ！！！跨域
	function rolw(){
		var data_json = {
			"tenantid" : "98",
			"plateno" : "102",
			"simno" : "98765432103",
			"vehicleid" : "12"
		}
		$.ajax(config.url + "linesegment/rolw", {
				data: data_json,
				dataType: "json",
				type: "POST",
				timeout: 10000,
				success: function(data) {
					//console.log(data);
				}
			});
	}
	//设置区域指定 
	function addEnclosureTerminalCommand(){
		var data_json = {
			"cmd" : "0",
			"cmdType" : "34304",
			"tenantId" : "987",
			"plateNo" : "102",
			"simNo" : "98765432103",
			"vehicleId" : "12",
			"cmdData" : "1223"
		}
		$.ajax(config.url + "realTime/addEnclosureTerminalCommand", {
				data: data_json,
				dataType: "json",
				type: "POST",
				timeout: 10000,
				success: function(data) {
					//console.log(data);
				}
			});
	}
	//删除区域指定 
	function delEnclosureTerminalCommand (){
		var data_json = {
			"cmd" : "0",
			"cmdType" : "34304",
			"tenantId" : "983",
			"plateNo" : "102",
			"simNo" : "98765432103",
			"vehicleId" : "12",
			"cmdData" : "1223"
		}
		$.ajax(config.url + "realTime/delEnclosureTerminalCommand ", {
				data: data_json,
				dataType: "json",
				type: "POST",
				timeout: 10000,
				success: function(data) {
					//console.log(data);
				}
			});
	}
	//在线监控车辆状态
	function onlineAlarmInformation (){
		$.ajax(config.url + "realTime/onlineAlarmInformation ", {
				dataType: "json",
				type: "POST",
				timeout: 10000,
				success: function(data) {
					//console.log(data);
				}
			});
	}
	//拍视频 
	function onlineDriverMonitoring (){
		var data_json = {
			"beginTime" : "2017-01-01",
			"endTime" : "2017-03-01",
			"tenantId" : "98",
			"plateNo" : "102",
			"simNo" : "98765432103",
			"vehicleId" : "12",
			"loginAdmin" : "1223"
		}
		$.ajax(config.url + "realTime/onlineDriverMonitoring ", {
				data: data_json,
				dataType: "json",
				type: "POST",
				timeout: 10000,
				success: function(data) {
					//console.log(data);
				}
			});
	}
	//根据当前用户,车辆运营类型,查询可见所有车辆的信息
	function findVehicleByAdminAndSpecialType (){
		var data_json = {
			"specialType " : "1"
		}
		$.ajax(config.url + "realTime/findVehicleByAdminAndSpecialType", {
				data: data_json,
				dataType: "json",
				type: "POST",
				timeout: 10000,
				success: function(data) {
					//console.log(data);
				}
			});
	}
//	driverTired();
//	monitorByCamera();
//	vPositionTReport();
//	vTimeInterval();
//	rolw();
//	addEnclosureTerminalCommand();
//	delEnclosureTerminalCommand();
//	onlineAlarmInformation();
//	onlineDriverMonitoring();
//	findVehicleByAdminAndSpecialType();
})
