$(document).ready(function() {
	config = {
		url: "http://192.168.0.116:8080/GuangTong/"
	}

	function findVehicleByAdmin() {
		//获取当前用户可见车辆的信息
		$.ajax(config.url + "realTime/findVehicleByAdmin", {
			data: {},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(JSON.stringify(data));
			}
		});
	}

	function addEnclosure() {

		//添加区域
		$.ajax(config.url + "realTime/addEnclosure", {
			data: {
				"ecName": "1",
				"tenantId": "1",
				"centerlat": "1",
				"centerlng": "1",
				"byTime": "1",
				"delay": "1",
				"limitSpeed": "1",
				"lineWidth": "1",
				"maxSpeed": "1",
				"offsetdelay": "1",
				"radius": "1",
				"sN": "1",
				"alarmType": "1",
				"enclosureType": "1",
				"startDate": "2017-02-02 00:00:00",
				"endDate": "2017-02-02 00:00:01",
				"points": "1",
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(JSON.stringify(data));
			}
		});
	}

	function addEnclosureTerminalCommand() {

		//设置区域指令
		$.ajax(config.url + "realTime/addEnclosureTerminalCommand", {
			data: {
				"cmd": "1",
				"cmdType": "34304",
				"plateNo": '123',
				"simNo": "98765432101",
				"vehicleId": "11",
				"cmdData": "2017/02/02",
				"tenantId": "1234",

			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(JSON.stringify(data));
			}
		});
	}

	function getRealDatasByOnline() {
		//获取当前在线车辆信息
		$.ajax(config.url + "realTime/getRealDatasByOnline", {
			data: {

			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(JSON.stringify(data));
			}
		});
	}

	function getRealDatasBySimNo() {

		//根据simNo(卡号)精准查询车辆信息
		$.ajax(config.url + "realTime/getRealDatasBySimNo", {
			data: {
				"simNo": "98765432101",
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(JSON.stringify(data));
			}
		});

	}

	function getRealDatasByObsSimNo() {
		//根据simNo(卡号)精准查询车辆信息
		$.ajax(config.url + "realTime/getRealDatasByObsSimNo", {
			data: {
				"simNo": "98765432101",
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(JSON.stringify(data));
			}
		});
	}

	function getRealDatasByPlateNo() {
		//根据PlateNo(车牌号)查询车辆信息
		$.ajax(config.url + "realTime/getRealDatasByPlateNo", {
			data: {
				"plateNo": "测B00098",
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(JSON.stringify(data));
			}
		});
	}

	function findVehicleByAdminAndSpecialType() {
		//根据当前用户,车辆运营类型,查询可见所有车辆的信息
		$.ajax(config.url + "realTime/findVehicleByAdminAndSpecialType", {
			data: {
				"specialType": "1",
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(JSON.stringify(data));
			}
		});
	}

	function vPositionTimeReport() {
		//定时定距上传(终端参数设置) 
		$.ajax(config.url + "port/vPositionTimeReport", {
			data: {
				"tenantid ": "01",
				"plateno": "123",
				"simno": "98765432101",
				"vehicleid": "101",
				"timeinterval":"500",
				"distanceinterval":"300",
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(JSON.stringify(data));
			}
		});
	}

	function getMediaItemByPage() {
		// 分页显示媒体信息  
		$.ajax(config.url + "port/getMediaItemByPage", {
			data: {
				"simNo": "98765432101",
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(JSON.stringify(data));
			}
		});
	}
	
	function getEnclosure() {
		// 查询所有区域
		$.ajax(config.url + "realTime/getEnclosure", {
			data: {
			
			},
			dataType: "json",
			type: "POST",
			timeout: 10000,
			success: function(data) {
				//console.log(JSON.stringify(data));
			}
		});
	}
	
	//	findVehicleByAdmin();
	//	addEnclosure();
	//	addEnclosureTerminalCommand();
	//	getRealDatasByOnline();
	//	getRealDatasByObsSimNo();
	//	getRealDatasByPlateNo();
	//findVehicleByAdminAndSpecialType();
	//vPositionTimeReport();
	//getMediaItemByPage();
	//getEnclosure();
})