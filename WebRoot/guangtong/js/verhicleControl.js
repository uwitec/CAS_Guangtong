$("#Vehiclecommand dt").click(function() {
	VerhicleOrderChange($(this).text());
	$("#Vehiclecommand dt").removeClass("on_xk");
	$(this).addClass("on_xk");
});

function TerminalControlChange(thiselement) {
	$(thiselement).attr("title", $(thiselement).children("option:selected").text());
	if($(thiselement).is("#Terminalcontrol")) {
		var html = "";
		switch($(thiselement).children("option:selected").text()) {
			case "无线升级":
				html += '<div><label for="">URL地址</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">拨号点名称</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">拨号用户名</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">拨号密码</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">地址</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">TCP端口</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">UDP端口</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">制造商ID</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">硬件版本</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">固件版本</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">连接到指定服务器时限</label><input type="text" class="input_xk"></div>';
				$("#Terminalcommandlist").html(html);
				break;
			case "控制终端连接指定服务器":
				html += '<div><label for="">连接控制</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">监管平台鉴权码</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">拨号点名称</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">拨号用户名</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">地址</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">拨号密码</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">地址</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">TCP端口</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">UDP端口</label><input type="text" class="input_xk"></div>';
				html += '<div><label for="">连接到指定服务器时限</label><input type="text" class="input_xk"></div>';
				$("#Terminalcommandlist").html(html);
				break;
			default:
				$("#Terminalcommandlist").html("");
				break;
		}
	};
};
$("#Vehiclecontrol").on("change", "select", function() {
	thiselement = $(this);
	TerminalControlChange(thiselement);
});
$("#Vehiclecontrol").on("click", ".button:contains('删除')", function() {
	$(this).parent().parent("tr").remove();
});
$("#Vehiclecontrol").on("click", ".button:contains('添加')", function() {
	var addCommand = "";
	addCommand += '<tr>';
	addCommand += '<td>';
	addCommand += '<select name="" class="select">';
	addCommand += '<option value="0" selected="selected"></option>';
	addCommand += '<option value="1">终端心跳发送间隔</option>';
	addCommand += '<option value="2">TCP消息应答超时时间</option>';
	addCommand += '<option value="3">TCP消息重传次数</option>';
	addCommand += '<option value="4">UDP消息应答超时时间</option>';
	addCommand += '<option value="5">UDP消息重传次数</option>';
	addCommand += '<option value="6">SMS消息应答超时时间</option>';
	addCommand += '<option value="7">SMS消息重传次数</option>';
	addCommand += '<option value="16">主服务器APN</option>';
	addCommand += '<option value="17">主服务器拨号用户名</option>';
	addCommand += '<option value="18">主服务器拨号密码</option>';
	addCommand += '<option value="19">主服务器地址IP或域名</option>';
	addCommand += '<option value="20">备份服务器APN</option>';
	addCommand += '<option value="21">备份服务器拨号用户名</option>';
	addCommand += '<option value="22">备份服务器拨号密码</option>';
	addCommand += '<option value="23">备份服务器地址IP或域名</option>';
	addCommand += '<option value="24">服务器TCP端口</option>';
	addCommand += '<option value="25">服务器UDP端口</option>';
	//	addCommand += '<option value="IC卡认证主服务器IP地址或域名">IC卡认证主服务器IP地址或域名</option>';
	//	addCommand += '<option value="IC卡认证主服务器TCP端口">IC卡认证主服务器TCP端口</option>';
	//	addCommand += '<option value="IC卡认证主服务器UDP端口">IC卡认证主服务器UDP端口</option>';
	//	addCommand += '<option value="IC卡认证备份服务器IP地址或域名">IC卡认证备份服务器IP地址或域名</option>';
	addCommand += '<option value="32">位置汇报策略</option>';
	addCommand += '<option value="33">位置汇报方案  </option>';
	addCommand += '<option value="34">驾驶员未登录汇报时间间隔</option>';
	addCommand += '<option value="39">休眠时汇报时间间隔</option>';
	addCommand += '<option value="40">紧急报警时汇报时间间隔</option>';
	addCommand += '<option value="41">缺省时间汇报间隔</option>';
	addCommand += '<option value="44">缺省距离汇报间隔</option>';
	addCommand += '<option value="45">驾驶员未登录汇报距离间隔</option>';
	addCommand += '<option value="46">休眠时汇报距离间隔</option>';
	addCommand += '<option value="47">紧急报警时汇报距离间隔</option>';
	addCommand += '<option value="48">拐点补传角度<180</option>';
	addCommand += '<option value="电子围栏半径">电子围栏半径</option>';
	addCommand += '<option value="64">监控平台电话号码</option>';
	addCommand += '<option value="65">复位电话号码</option>';
	addCommand += '<option value="66">恢复出厂设置电话号码</option>';
	addCommand += '<option value="67">监控平台SMS电话号码</option>';
	addCommand += '<option value="68">接收终端SMS文本报警号码</option>';
	addCommand += '<option value="69">终端电话接听策略</option>';
	addCommand += '<option value="70">每次最长通话时间</option>';
	addCommand += '<option value="71">每月最长通话时间</option>';
	addCommand += '<option value="72">监听电话号码</option>';
	addCommand += '<option value="73">监管平台特权短信号码</option>';
	addCommand += '<option value="80">报警屏蔽字</option>';
	addCommand += '<option value="81">报警发送文本SMS开关</option>';
	addCommand += '<option value="82">报警拍摄开关</option>';
	addCommand += '<option value="83">报警拍摄存储标志</option>';
	addCommand += '<option value="84">关键标志</option>';
	addCommand += '<option value="85">最高速度</option>';
	addCommand += '<option value="86">超速持续时间</option>';
	addCommand += '<option value="87">连续驾驶时间门限</option>';
	addCommand += '<option value="88">当天累计驾驶时间门限</option>';
	addCommand += '<option value="89">最小休息时间</option>';
	addCommand += '<option value="90">最长停车时间</option>';
	//	addCommand += '<option value="超速报警预警差值">超速报警预警差值</option>';
	//	addCommand += '<option value="疲劳驾驶预警差值">疲劳驾驶预警差值</option>';
	//	addCommand += '<option value="碰撞报警参数设置">碰撞报警参数设置</option>';
	//	addCommand += '<option value="侧翻报警参数设置">侧翻报警参数设置</option>';
	//	addCommand += '<option value="定时拍照控制">定时拍照控制</option>';
	//	addCommand += '<option value="定距拍照控制">定距拍照控制</option>';
	addCommand += '<option value="112">图像或视频质量1至10</option>';
	addCommand += '<option value="113">亮度0至255</option>';
	addCommand += '<option value="114">对比度0至127</option>';
	addCommand += '<option value="115">饱和度0至127</option>';
	addCommand += '<option value="116">色度0至255</option>';
	addCommand += '<option value="128">车辆里程表读数，单位：1/10km</option>';
	addCommand += '<option value="129">车辆所在的省域ID</option>';
	addCommand += '<option value="130">车辆所在的市域ID</option>';
	addCommand += '<option value="131">车牌号</option>';
	addCommand += '<option value="132">车牌颜色</option>';
	//	addCommand += '<option value="">GNSS 定位模式</option>';
	//	addCommand += '<option value="">GNSS 波特率</option>';
	//	addCommand += '<option value="">GNSS 模块详细定位数据输出频率</option>';
	//	addCommand += '<option value="">GNSS 模块详细定位数据采集频率</option>';
	//	addCommand += '<option value="">GNSS 模块详细定位数据上传方式</option>';
	//	addCommand += '<option value="">GNSS 模块详细定位数据上传设置</option>';
	//	addCommand += '<option value="">CAN 总线通道1 采集时间间隔(ms)</option>';
	//	addCommand += '<option value="">CAN 总线通道1 上传时间间隔(s)</option>';
	//	addCommand += '<option value="">CAN 总线通道2 采集时间间隔(ms)</option>';
	//	addCommand += '<option value="">CAN 总线通道2 上传时间间隔(s)</option>';
	//	addCommand += '<option value="">CAN 总线ID 单独采集设置</option>';
	addCommand += '</select>';
	addCommand += '</td>';
	addCommand += '<td><input type="text" class="search" /></td>';
	addCommand += '<td>';
	addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
	addCommand += '</td>';
	addCommand += '</tr>';
	$("#Vehiclecontrol tbody").append(addCommand);
})

function VerhicleOrderChange(a) {
	var html = "";
	switch(a) {
		case "设置终端参数":
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="max-height:393px;overflow-y:auto;">';
			html += '<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;overflow:auto;max-height:300px;">';
			html += '<thead><tr>';
			html += '<th style="width:166px;">参数名称</th>';
			html += '<th style="width:164px;">参数值</th>';
			html += '<th style="width:80px;">操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			break;
		case "终端控制":
			html += '<select name="终端控制" class="select" id="Terminalcontrol">';
			html += '<option value="1" selected="selected">无线升级</option>';
			html += '<option value="2">控制终端连接指定服务器</option>';
			html += '<option value="3">终端关机</option>';
			html += '<option value="4">终端复位</option>';
			html += '<option value="5">终端恢复出厂设置</option>';
			html += '<option value="6">关闭数据通信</option>';
			html += '<option value="7">关闭所有无线通信</option>';
			html += '</select>';
			html += '<div id="Terminalcommandlist"></div>';
			break;
		default:
			break;
	}
	$("#Vehiclecontrol").html(html);
	$(this).attr("title", $(this).children("option:selected").text());
	thiselement = $("#Terminalcontrol");
	if($(thiselement).length != 0) {
		TerminalControlChange(thiselement);
	}
};

function getCarList() {
	$.ajax(config.url + "vehicle/getVehicleByAdmin", {
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			if(data.success == true) {
				data.obj == null ? data = data.t : data = data.obj;
				html = "";
				for(one in data) {
					html += '<dl>';
					html += '<dt><i><img src="img/u380.png"/></i><b cId="' + data[one].cId + '"><img src="img/uncherked.jpg"/></b><i><img src="img/团队.png" /></i>' + data[one].cName + '</dt>';
					html += '<dd>';
					for(two in data[one].vehicles) {
						html += '<dl>';
						html += '<dt><i><img src="img/线路车辆 (1).png"/></i><b simno="' + data[one].vehicles[two].simNo + '" num="' + data[one].vehicles[two].num + '" vid="' + data[one].vehicles[two].id + '"><img src="img/uncherked.jpg"/></b>' + data[one].vehicles[two].num + '</dt>';
						html += '</dl>';
					}
					html += '</dd>';
					html += '</dl>';
				}
				$("dd#carList").html(html);
				popupresize();
			}
		}
	});
};
getCarList();
$(".popcomfirm_xk a.button:contains('保存')").click(function() {
	alarmSetUpSave($(".poptable_xk dt.on_xk").text());
});

function alarmSetUpSave(savetype) {
	cId = [];
	vId = [];
	simnoArr = [];
	numArr = [];
	for(var i = 0; i < $(".poptable_xk #carList img").length; i++) {
		if($(".poptable_xk #carList img:eq(" + i + ")").attr("src") == "img/cherked.jpg") {
			if($(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("vId") != undefined && $(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("vId") != null) {
				vId.push($(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("vid"));
				simnoArr.push($(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("simno"));
				numArr.push($(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("num"));
			} else {
				cId.push($(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("cId"));
			}
		}
	}
	switch(savetype) {
		case "设置终端参数":
			var alarmport = "port/insertCommandByterminal";
			var CommandArray = [];
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				var str = "";
				str += $("td#Vehiclecontrol table tr").find("select option:selected").eq(i).val();
				str += "," + $("td#Vehiclecontrol table tr").find("input:text").eq(i).val();
				CommandArray.push(str);
			}
			var CommandStr = CommandArray.join(";");
			var data_json = {
				"Command": CommandStr,
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId
			}
			break;
		default:
			var alarmport = "port/terminalController";
			var cmd = $("select#Terminalcontrol option:selected").val();
			var cmddata = [];
			$("#Vehiclecontrol input").each(function() {
				if($(this).val() == null) {
					cmddata,
					push("");
				}
				else {
					cmddata.push($(this).val());
				}
			})
			cmddata = cmddata.join(";");
			var data_json = {
				"cmd": cmd,
				"cmddata": cmddata,
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId
			}
			break;
	}
	$.ajax(config.url + alarmport, {
		data: data_json,
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			//console.log(data);
			if(data.success == true) {
				alert("添加成功！");
				$(".pophead_xk i img[src='img/关闭.png']").click();
			}
		}
	});
};