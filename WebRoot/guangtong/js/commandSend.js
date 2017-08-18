var oData= new Date();
var year = oData .getFullYear(); //获取完整的年份(4位,1970-????)
var month = oData.getMonth()+1; //获取当前月份(0-11,0代表1月)
var data = oData.getDate(); //获取当前日(1-31)
var day = oData.getDay(); //获取当前星期X(0-6,0代表星期天)
var h = oData.getHours(); //获取当前小时数(0-23)
var m = oData.getMinutes(); //获取当前分钟数(0-59)
var s = oData.getSeconds(); //获取当前秒数(0-59)
var data_zyl = year+"-" +month+"-" + data+ " "+ h+":"+ m +":" + s;
$("#Vehiclecommand dt[commandID]").click(function() {
	VerhicleOrderChange($(this).text());
	$("#Vehiclecommand dt[commandID]").removeClass("on_xk");
	$(this).addClass("on_xk");
});

function TerminalControlChange(thiselement) {
	$(thiselement).attr("title", $(thiselement).children("option:selected").text());
	if($(thiselement).is("#Terminalcontrol")) {
		var html = "";
		switch($(thiselement).children("option:selected").text()) {
			case "无线升级":
				html += '<div><label for="">URL地址</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">拨号点名称</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">拨号用户名</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">拨号密码</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">地址</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">TCP端口</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">UDP端口</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">制造商ID</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">硬件版本</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">固件版本</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">连接到指定服务器时限</label><input type="text" class="input_xk required red_wd"value="0"></div>';
				$("#Terminalcommandlist").html(html);
				break;
			case "控制终端连接指定服务器":
				html += '<div><label for="">连接控制</label><input type="text" class="input_xk required red_wd" value="0"></div>';
				html += '<div><label for="">监管平台鉴权码</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">拨号点名称</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">拨号用户名</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">地址</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">拨号密码</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">地址</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">TCP端口</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">UDP端口</label><input type="text" class="input_xk required red_wd"></div>';
				html += '<div><label for="">连接到指定服务器时限</label><input type="text" class="input_xk required red_wd" value="0"></div>';
				$("#Terminalcommandlist").html(html);
				break;
			default:
				$("#Terminalcommandlist").html("");
				break;
		}
	}
	
};
$("#Vehiclecontrol").on("change", "select", function() {
	thiselement = $(this);
	TerminalControlChange(thiselement);
});
$("#Vehiclecontrol").on("click", ".button:contains('删除')", function() {
	$(this).parent().parent("tr").remove();
});
$("#Vehiclecontrol").on("click", ".button:contains('添加')", function() {
	if($(".red_wd").length == 0){
		var addCommand = "";
		addCommand += '<tr>';
		switch($("#Vehiclecommand").find("dt.on_xk").text()){
		case "设置终端参数":
			addCommand += '<td>';
			addCommand += '<select name="" class="select required red_wd">';
			addCommand += '<option selected="selected"></option>';
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
			addCommand += '<td><input type="text" class="search required red_wd" /></td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			addCommand += '</tr>';
			break;
		case "查询指定终端参数":
			addCommand += '<td>';
			addCommand += '<select name="" class="select required red_wd">';
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
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		case "事件设置":
			addCommand += '<td>';
			addCommand += '<select name="" class="select ev_set_zyl required red_wd">';
			addCommand += '<option value="0" selected="selected"></option>';
			addCommand += '<option value="1">事件0</option>';
			addCommand += '<option value="2">阴雨天气</option>';
			addCommand += '<option value="3">路滑</option>';
			addCommand += '<option value="4">路面坍塌</option>';
			addCommand += '<option value="5">明天有雨</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		case "提问下发":
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search"></td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		case "信息点播菜单设置":
			addCommand += '<td>';
			addCommand += '<select name="" class="select required red_wd">';
			addCommand += '<option value="0" selected="selected"></option>';
			addCommand += '<option value="1">天气预报</option>';
			addCommand += '<option value="2">路况情况</option>';
			addCommand += '<option value="3">开会时间</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		case "设置电话本":
			addCommand += '<td>';
			addCommand += '<select name="" class="select set_add_z required red_wd">';
			addCommand += '<option value="0" selected="selected"></option>';
			addCommand += '<option value="1">呼入</option>';
			addCommand += '<option value="2">呼出</option>';
			addCommand += '<option value="3">呼入或呼出</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td><input type="text" class="search tel_z required red_wd"></td>';
			addCommand += '<td><input type="text" class="search contacts_z"></td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		case "设置圆形区域":
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td><input class="input_xk time_plugin_lv_onclick search required red_wd" time_plugin_lv_type="2" ></td>';
			addCommand += '<td><input class="input_xk time_plugin_lv_onclick search required red_wd" time_plugin_lv_type="2" ></td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>允许开门</option>';
			addCommand += '<option value="0">禁止开门</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="0" selected>进区域关闭通信模块</option>';
			addCommand += '<option value="1">进区域开启通信模块</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="0" selected>进区域不采集GNSS详细定位数据</option>';
			addCommand += '<option value="1">进区域采集GNSS详细定位数据</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="0" selected>北纬</option>';
			addCommand += '<option value="1">南纬</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="0" selected>东经</option>';
			addCommand += '<option value="1">西经</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		case "删除圆形区域":
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		case "设置矩形区域":
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>'; 
			addCommand += '<td><input class="input_xk time_plugin_lv_onclick search required red_wd" time_plugin_lv_type="2" ></td>';
			addCommand += '<td><input class="input_xk time_plugin_lv_onclick search required red_wd" time_plugin_lv_type="2" ></td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>允许开门</option>';
			addCommand += '<option value="0">禁止开门</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="0" selected>进区域关闭通信模块</option>';
			addCommand += '<option value="1">进区域开启通信模块</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="0" selected>进区域不采集GNSS详细定位数据</option>';
			addCommand += '<option value="1">进区域采集GNSS详细定位数据</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="0" selected>北纬</option>';
			addCommand += '<option value="1">南纬</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="0" selected>东经</option>';
			addCommand += '<option value="1">西经</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		case "删除矩形区域":
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		case "删除多边形区域":
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		case "删除路线":
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		case "设置多边形区域":
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		case "设置路线":
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="0" selected>北纬</option>';
			addCommand += '<option value="1">南纬</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="0" selected>东经</option>';
			addCommand += '<option value="1">西经</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td><input type="text" class="search required red_wd"></td>';
			addCommand += '<td>';
			addCommand += '<select name="" class="select">';
			addCommand += '<option value="1" selected>是</option>';
			addCommand += '<option value="0">否</option>';
			addCommand += '</select>';
			addCommand += '</td>';
			addCommand += '<td>';
			addCommand += '<a class="button red" style="padding:3px 10px;margin:6px;white-space: nowrap;">删除</a>';
			addCommand += '</td>';
			break;
		default:
			break;
		}
		addCommand += '</tr>';
		$("#Vehiclecontrol tbody").append(addCommand);
	}
})

function VerhicleOrderChange(a) {
	var html = "";
	switch(a) {
		case "设置终端参数":
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="max-height:393px;overflow-y:auto;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;max-height:300px;">';
			html += '<thead><tr>';
			html += '<th>参数名称</th>';
			html += '<th>参数值</th>';
			html += '<th>操作</th>';
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
		case "查询终端参数":
			html += '<div style="white-space: nowrap;">';
			html += '<span>查询终端参数</span>';
			html += '</div>';
			break;
		case "查询终端属性":
			html += '<div style="white-space: nowrap;">';
			html += '<span>查询终端属性</span>';
			html += '</div>';
			break;
		case "位置信息查询":
			html += '<div style="white-space: nowrap;">';
			html += '<span>位置信息查询</span>';
			html += '</div>';
			break;
		case "查询指定终端参数":
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="max-height:393px;overflow-y:auto;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;max-height:300px;">';
			html += '<thead><tr>';
			html += '<th>参数名称</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			break;
		case "临时位置跟踪控制":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;">';
			html += '<div><label>时间间隔</label><input class="input_xk time_interval_z" type="text" value="0"></div>';
			html += '<div><label>临时跟踪有效期</label><input class="input_xk validity_z" type="text" value="0"></div>';
			html += '</div>';
			break;
		case "文本信息下发":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;">';
			html += '<div><label>紧急</label><select class="input_xk urgent_z" ><option value="0" selected="selected">否</option><option value="1">是</option></select></div>';
			html += '<div><label>终端显示器设置</label><select class="input_xk terminal_display_z" ><option value="0" selected="selected">否</option><option value="1">是</option></select></div>';
			html += '<div><label>终端TTS播读</label><select class="input_xk terminal_read" ><option value="0" selected="selected">否</option><option value="1">是</option></select></div>';
			html += '<div><label>广告屏显示</label><select class="input_xk ad_display_z" ><option value="0" selected="selected">否</option><option value="1">是</option></select></div>';
			html += '<div><label>中心导航CAN故障码信息</label><select class="input_xk central_nav" ><option value="0" selected="selected">中心导航信息</option><option value="1">CAN故障码信息</option></select></div>';
			html += '<div><label>文本信息</label><textarea class="textarea information_z required red_wd" title="该项为必输项" style="width:130px;height:80px;"></textarea></div>';
			html += '</div>';
			break;
		case "事件设置":
			html += '<select name="事件设置" class="select  ev_set">';
			html += '<option value="2" selected="selected">追加事件</option>';
			html += '<option value="1">更新事件</option>';
			html += '<option value="3">修改事件</option>';
			html += '<option value="4">删除特定几项事件</option>';
			html += '<option value="0">删除终端所有事件</option>';
			html += '</select>';
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="max-height:363px;overflow-y:auto;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;max-height:300px;">';
			html += '<thead><tr>';
			html += '<th>事件</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			break;
		case "提问下发":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;">';
			html += '<div><label>紧急</label><select class="input_xk questions_urgent_z" ><option value="0" selected="selected">否</option><option value="1">是</option></select></div>';
			html += '<div><label>终端TTS播读</label><select class="input_xk questions_terminal_z" ><option value="0" selected="selected">否</option><option value="1">是</option></select></div>';
			html += '<div><label>广告屏显示</label><select class="input_xk questions_ad_z" ><option value="0" selected="selected">否</option><option value="1">是</option></select></div>';
			html += '<div><label>问题</label><textarea class="textarea  questions_zyl required red_wd" title="该项为必输项" style="width:130px;height:70px;"></textarea></div>';
			html += '</div>';
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="max-height:223px;overflow-y:auto;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;max-height:300px;">';
			html += '<thead><tr>';
			html += '<th>序号</th>';
			html += '<th>答案</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			break;
		case "信息点播菜单设置":
			html += '<select name="信息点播菜单设置" class="select  info_demand">';
			html += '<option value="2" selected="selected">追加菜单</option>';
			html += '<option value="1">更新菜单</option>';
			html += '<option value="3">修改菜单</option>';
			html += '<option value="0">删除终端全部信息项</option>';
			html += '</select>';
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="max-height:363px;overflow-y:auto;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;max-height:300px;">';
			html += '<thead><tr>';
			html += '<th>信息服务</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			break;
		case "信息服务":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;">';
			html += '<div><label>信息序号</label><input class="input_xk info_num_z" type="text"></div>';
			html += '<div><label>信息服务</label><input class="input_xk info_service_z required red_wd " type="text" title="该输入项为必输项"></div>';
			html += '</div>';
			break;
		case "电话回拨":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;">';
			html += '<div><label>通话类型</label><select class="input_xk call_type_z" type="text"><option value="0">普通电话</option><option value="1">监听</option></select></div>';
			html += '<div><label>电话号码</label><input class="input_xk phone_num_z required red_wd" type="text" title="该输入项为必输项"></div>';
			html += '</div>';
			break;
		case "设置电话本":
			html += '<select name="设置类型" class="select set_phone_z">';
			html += '<option value="2" selected="selected">追加电话本</option>';
			html += '<option value="1">更新联系人（删除全部联系人并追加）</option>';
			html += '<option value="3">修改联系人</option>';
			html += '<option value="0">删除终端全部联系人</option>';
			html += '</select>';
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="max-height:363px;overflow-y:auto;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;max-height:300px;">';
			html += '<thead><tr>';
			html += '<th>标志</th>';
			html += '<th>电话</th>';
			html += '<th>联系人</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			break;
		case "车辆控制":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;">';
			html += '<div><label>车辆控制</label><select class="input_xk vehicle_zyl" ><option value="0" selected="selected">车门解锁</option><option value="1">车门加锁</option></select></div>';
			html += '</div>';
			break;
		case "摄像头立即拍摄命令":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;">';
			html += '<div><label>通道ID</label><input class="input_xk pass_z" value="1"></div>';
			html += '<div><label>拍摄命令</label><input class="input_xk shot_z" value="1"></div>';
			html += '<div><label>拍摄间隔/录像时间</label><input class="input_xk interval_z" value="3"></div>';
			html += '<div><label>保存标志</label><select class="input_xk upload_z" >';
			html +=	'<option value="0" selected="selected">实时上传</option><option value="1">保存</option>';
			html +=	'</select></div>';
			html += '<div><label>分辨率</label><select class="input_xk resolution_z" >';
			html +=	'<option value="1" selected="selected">320*240</option>';
			html +=	'<option value="2">640*480</option>';
			html +=	'<option value="3">800*600</option>';
			html +=	'<option value="4">1024*768</option>';
			html +=	'<option value="5">176*144[Qcif]</option>';
			html +=	'<option value="6">352*288[Cif]</option>';
			html +=	'<option value="7">704*288[HALF_D1]</option>';
			html +=	'<option value="8">704*576[D1]</option>';
			html +=	'</select></div>';
			html += '<div><label>图片/视频质量</label><input class="input_xk pic_quality" value="1"></div>';
			html += '<div><label>亮度</label><input class="input_xk brightness_z" value="1"></div>';
			html += '<div><label>对比度</label><input class="input_xk contrast_z" value="1"></div>';
			html += '<div><label>饱和度</label><input class="input_xk saturation_z" value="1"></div>';
			html += '<div><label>色度</label><input class="input_xk chroma_z" value="1"></div>';
			html += '</div>';
			break;
		case "存储多媒体数据检索":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;">';
			html += '<div><label>多媒体类型</label><select class="input_xk retrieval_z" >';
			html +=	'<option value="0" selected="selected">图像</option>';
			html +=	'<option value="1" selected="selected">音频</option>';
			html +=	'<option value="2" selected="selected">视频</option>';
			html +=	'</select></div>';
			html += '<div><label>通道ID</label><input class="input_xk passageway_z" value="1"></div>';
			html += '<div><label>事件项编码</label><select class="input_xk event_z" >';
			html +=	'<option value="0" selected="selected">平台下发命令</option>';
			html +=	'<option value="1">定时动作</option>';
			html +=	'<option value="2">抢劫报警触发</option>';
			html +=	'<option value="3">碰撞侧翻报警触发</option>';
			html +=	'</select></div>';
			html += '<div><label>起始时间</label><input class="input_xk time_plugin_lv_onclick startTime_z" time_plugin_lv_type="2" ></div>';
			html += '<div><label>结束时间</label><input class="input_xk time_plugin_lv_onclick endTime_z" time_plugin_lv_type="2" ></div>';
			html += '</div>';
			break;
		case "单条存储多媒体数据检索上传":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;">';
			html += '<div><label>多媒体ID</label><input class="input_xk required red_wd" value="1"></div>';
			html += '<div><label>删除标志</label><select class="input_xk" >';
			html +=	'<option value="0" selected="selected">保留</option>';
			html +=	'<option value="1">删除</option>';
			html +=	'</select></div>';
			html += '</div>';
			break;
		case "存储多媒体数据上传":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;">';
			html += '<div><label>多媒体类型</label><select class="input_xk mul_type_zyl" >';
			html +=	'<option value="0" selected="selected">图像</option>';
			html +=	'<option value="1">音频</option>';
			html +=	'<option value="2">视频</option>';
			html +=	'</select></div>';
			html += '<div><label>通道ID</label><input class="input_xk passway_zyl" value="1"></div>';
			html += '<div><label>事件项编码</label><select class="input_xk e_item_zyl" >';
			html +=	'<option value="0" selected="selected">平台下发命令</option>';
			html +=	'<option value="1">定时动作</option>';
			html +=	'<option value="2">抢劫报警触发</option>';
			html +=	'<option value="3">碰撞侧翻报警触发</option>';
			html +=	'</select></div>';
			html += '<div><label>起始时间</label><input class="input_xk time_plugin_lv_onclick startTime_zyl" time_plugin_lv_type="2"   ></div>';
			html += '<div><label>结束时间</label><input class="input_xk time_plugin_lv_onclick endTime_zyl" time_plugin_lv_type="2"  ></div>';
			html += '<div><label>删除标志</label><select class="input_xk del_zyl" >';
			html +=	'<option value="0" selected="selected">保留</option>';
			html +=	'<option value="1">删除</option>';
			html +=	'</select></div>';
			html += '</div>';
			break;
		case "录音开始命令":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;">';
			html += '<div><label>保存标志</label><select class="input_xk save_sign_z"  >';
			html +=	'<option value="0" selected="selected">实时上传</option>';
			html +=	'<option value="1">保存</option>';
			html +=	'</select></div>';
			html += '<div><label>录音命令</label><select class="input_xk tape_z" >';
			html +=	'<option value="1" selected="selected">开始录音</option>';
			html +=	'<option value="0">停止录音</option>';
			html +=	'</select></div>';
			html += '<div><label>录音时间</label><input value="1"  class="input_xk tape_time_z"></div>';
			html += '<div><label>采样频率</label><select class="input_xk sampling_z" >';
			html +=	'<option value="0">_8k</option>';
			html +=	'<option value="1" selected="selected">_11k</option>';
			html +=	'<option value="2">_23k</option>';
			html +=	'<option value="3">_32k</option>';
			html +=	'</select></div>';
			html += '</div>';
			break;
		case "数据透传":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;">';
			html += '<div><label>透传消息类型</label><select class="input_xk required red_wd transparent_z" >';
			html +=	'<option value="0" selected="selected">GNSS模块详细定位数据</option>';
			html +=	'<option value="1" selected="selected">道路运输证IC卡信息</option>';
			html +=	'<option value="2" selected="selected">串口1透传</option>';
			html +=	'<option value="3" selected="selected">串口2透传</option>';
			html +=	'<option value="4" selected="selected">用户自定义透传</option>';
			html +=	'</select></div>';
			html += '<div><label>透传消息内容</label><input class="news_z required red_wd" type="text" title="该输入项为必输项"></div>';
			html += '</div>';
			break;
		case "设置圆形区域":
			html += '<select name="设置区域" class="select set_area_z">';
			html += '<option value="1" selected="selected">追加区域</option>';
			html += '<option value="0">更新区域</option>';
			html += '<option value="2">修改区域</option>';
			html += '</select>';
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="height:363px;overflow:auto;max-width:310px;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;max-height:300px;white-space:nowrap;">';
			html += '<thead><tr>';
			html += '<th>区域ID</th>';
			html += '<th>根据时间</th>';
			html += '<th>起始时间</th>';
			html += '<th>结束时间</th>';
			html += '<th>限速</th>';
			html += '<th>最高速度</th>';
			html += '<th>超速持续时间</th>';
			html += '<th>进区域报警给驾驶员</th>';
			html += '<th>进区域报警给平台</th>';
			html += '<th>出区域报警给驾驶员</th>';
			html += '<th>出区域报警给平台</th>';
			html += '<th>禁止/允许开门</th>';
			html += '<th>进区域关闭通信模块</th>';
			html += '<th>进区域是否采集GNSS详细定位数据</th>';
			html += '<th>纬度</th>';
			html += '<th>经度</th>';
			html += '<th>中心点纬度</th>';
			html += '<th>中心点经度</th>';
			html += '<th>半径</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			break;
		case "删除圆形区域":
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="max-height:393px;overflow-y:auto;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;max-height:300px;">';
			html += '<thead><tr>';
			html += '<th>删除区域ID</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			break;
		case "设置矩形区域":
			html += '<select name="设置区域" class="select setting_area_z">';
			html += '<option value="1" selected="selected">追加区域</option>';
			html += '<option value="0">更新区域</option>';
			html += '<option value="2">修改区域</option>';
			html += '</select>';
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="height:363px;overflow:auto;max-width:310px;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;max-height:300px;white-space:nowrap;">';
			html += '<thead><tr>';
			html += '<th>区域ID</th>';
			html += '<th>根据时间</th>';
			html += '<th>起始时间</th>';
			html += '<th>结束时间</th>';
			html += '<th>限速</th>';
			html += '<th>最高速度</th>';
			html += '<th>超速持续时间</th>';
			html += '<th>进区域报警给驾驶员</th>';
			html += '<th>进区域报警给平台</th>';
			html += '<th>出区域报警给驾驶员</th>';
			html += '<th>出区域报警给平台</th>';
			html += '<th>禁止/允许开门</th>';
			html += '<th>进区域关闭通信模块</th>';
			html += '<th>进区域是否采集GNSS详细定位数据</th>';
			html += '<th>纬度</th>';
			html += '<th>经度</th>';
			html += '<th>中心点纬度</th>';
			html += '<th>中心点经度</th>';
			html += '<th>左上点纬度</th>';
			html += '<th>左上点经度</th>';
			html += '<th>右下点纬度</th>';
			html += '<th>右下点经度</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			break;
		case "删除矩形区域":
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="max-height:393px;overflow-y:auto;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;max-height:300px;">';
			html += '<thead><tr>';
			html += '<th>删除区域ID</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			break;
		case "删除多边形区域":
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="max-height:393px;overflow-y:auto;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;max-height:300px;">';
			html += '<thead><tr>';
			html += '<th>删除区域ID</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			break;
		case "删除路线":
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="max-height:393px;overflow-y:auto;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;max-height:300px;">';
			html += '<thead><tr>';
			html += '<th>删除区域ID</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			break;
		case "驾驶员信息采集":
			html += '<div style="white-space: nowrap;">';
			html += '<span>驾驶员信息采集</span>';
			html += '</div>';
			break;
		case "驾采集指定位置信息记录":
			html += '<div style="white-space: nowrap;">';
			html += '<div><label>起始时间</label><input class="input_xk time_plugin_lv_onclick startTime_zyl" time_plugin_lv_type="2" ></div>';
			html += '<div><label>结束时间</label><input class="input_xk time_plugin_lv_onclick endTime_zyl" time_plugin_lv_type="2" ></div>';
			html += '</div>'; 
			break;
		case "设置多边形区域":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;max-height:410px;overflow-y:auto;">';
			html += '<div><label>区域ID</label><input class="input_xk region_z" value="0"></div>';
			html += '<div><label>是否根据时间</label><select class="input_xk isTime_z" ><option value="0">否</option><option value="1" selected="selected">是</option></select></div>';
			html += '<div><label>起始时间</label><input class="input_xk time_plugin_lv_onclick startTime_z" time_plugin_lv_type="2"></div>';
			html += '<div><label>终止时间</label><input class="input_xk time_plugin_lv_onclick endTime_z" time_plugin_lv_type="2" ></div>';
			html += '<div><label>进区域报警给驾驶员</label><select class="input_xk inregion_driver_z" ><option value="0">否</option><option value="1" selected="selected">是</option></select></div>';
			html += '<div><label>进区域报警给平台</label><select class="input_xk inregion_platform_z" ><option value="0">否</option><option value="1" selected="selected">是</option></select></div>';
			html += '<div><label>出区域报警给驾驶员</label><select class="input_xk outregion_driver_z" ><option value="0">否</option><option value="1" selected="selected">是</option></select></div>';
			html += '<div><label>出区域报警给平台</label><select class="input_xk outregion_platform_z" ><option value="0">否</option><option value="1" selected="selected">是</option></select></div>';
			html += '<div><label>纬度</label><select class="input_xk lon_z" ><option value="0" selected="selected">北纬</option><option value="1">南纬</option></select></div>';
			html += '<div><label>经度</label><select class="input_xk lat_z" ><option value="0" selected="selected">东经</option><option value="1">西经</option></select></div>';
			html += '<div><label>是否允许开门</label><select class="input_xk door_z" ><option value="1" selected="selected">允许开门</option><option value="0">禁止开门</option></select></div>';
			html += '<div><label>进区域是否开启通信模块</label><select class="input_xk signal_z" ><option value="1" selected="selected">进区域开启通信模块</option><option value="0">进区域关闭通信模块</option></select></div>';
			html += '<div><label>进区域采集GNSS定位数据</label><select class="input_xk location_z" ><option value="0">否</option><option value="1" selected="selected">是</option></select></div>';
			html += '<div><label>限速</label><select class="input_xk speedlimit_z" ><option value="0">否</option><option value="1" selected="selected">是</option></select></div>';
			html += '<div><label>自高速度</label><input class="input_xk speed_z " value="100"></div>';
			html += '<div><label>超速持续时间</label><input class="input_xk Speeding_z" value="10"></div>';
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div>';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;">';
			html += '<thead><tr>';
			html += '<th>顶点经度</th>';
			html += '<th>顶点纬度</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			html += '</div>';
			break;
		case "设置路线":
			html += '<div id="Terminalcommandlist" style="white-space: nowrap;max-height:410px;overflow-y:auto;">';
			html += '<div><label>路线ID</label><input class="input_xk route_zl" value="0"></div>';
			html += '<div><label>是否根据时间</label><select class="input_xk is_time_zl" ><option value="0">否</option><option value="1" selected="selected">是</option></select></div>';
			html += '<div><label>起始时间</label><input class="input_xk time_plugin_lv_onclick startTime_zl"  time_plugin_lv_type="2"></div>';
			html += '<div><label>终止时间</label><input class="input_xk time_plugin_lv_onclick endTime_zl"  time_plugin_lv_type="2"></div>';
			html += '<div><label>进路线报警给驾驶员</label><select class="input_xk inpolice_driver_zl" ><option value="0">否</option><option value="1" selected="selected">是</option></select></div>';
			html += '<div><label>进路线报警给平台</label><select class="input_xk inpolice_platform_zl" ><option value="0">否</option><option value="1" selected="selected">是</option></select></div>';
			html += '<div><label>出路线报警给驾驶员</label><select class="input_xk outpolice_driver_zl" ><option value="0">否</option><option value="1" selected="selected">是</option></select></div>';
			html += '<div><label>出路线报警给平台</label><select class="input_xk outpolice_platform_zl" ><option value="0">否</option><option value="1" selected="selected">是</option></select></div>';
			html += '<div style="white-space: nowrap;">';
			html += '<a class="button blue" style="padding:3px 10px;margin:6px;">添加</a>';
			html += '</div>';
			html += '<div style="width:310px;overflow-x:auto;">';
			html += '<table id="fulllength" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;">';
			html += '<thead><tr>';
			html += '<th>拐点ID</th>';
			html += '<th>路段ID</th>';
			html += '<th>纬度</th>';
			html += '<th>拐点纬度</th>';
			html += '<th>经度</th>';
			html += '<th>拐点经度</th>';
			html += '<th>路段宽度</th>';
			html += '<th>行驶时间</th>';
			html += '<th>路段行驶过长阈值</th>';
			html += '<th>路段行驶不足阈值</th>';
			html += '<th>限速</th>';
			html += '<th>操作</th>';
			html += '</tr></thead>';
			html += '<tbody>';
			html += '</tbody>';
			html += '</table>';
			html += '</div>';
			html += '</div>';
			break;
		default:
			break;
	}
	$("#Vehiclecontrol").html(html);
	$(this).attr("title", $(this).children("option:selected").text());
	thiselement = $("#Terminalcontrol");
	popupresize();
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
					html += "<dl>";
					html += '<dt ><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg" carListClick /></b>' + one + '</dt>';
					html += "<dd>";
					for(two in data[one]) {
						if(two.indexOf("TEAM")!=-1){
							for(three in data[one][two]) {
								html += "<dl>";
								html += '<dt><i><img src="img/xlcl (1).png"/></i><b simno="' + data[one][two][three].simNo + '" num="' + data[one][two][three].num + '" vid="' + data[one][two][three].id + '" vcolor="' + data[one][two][three].vmcolor +'"><img src="img/uncherked.jpg"/></b>' + data[one][two][three].num + '</dt>';
								html += "</dl>";
							}
						}else{
							html += "<dl>";
							html += '<dt><i><img src="img/u380.png"></i><b class="cherked_xk"><img src="img/uncherked.jpg" carListClick /></b>' + two + '</dt>';
							html += "<dd>";
							for(three in data[one][two]) {
								html += "<dl>";
								html += '<dt><i><img src="img/xlcl (1).png"/></i><b simno="' + data[one][two][three].simNo + '" num="' + data[one][two][three].num + '" vid="' + data[one][two][three].id + '" vcolor="' + data[one][two][three].vmcolor + '"><img src="img/uncherked.jpg"/></b>' + data[one][two][three].num + '</dt>';
								html += "</dl>";
							}
							html += "</dd>";
							html += "</dl>";
						}
					}
					html += "</dd>";
					html += "</dl>";
				}
				$("dd#carList").html(html);
				popupresize();
			}
		}
	});
};

getCarList();
$(".popcomfirm_xk a.button:contains('保存')").click(function() {
	//alert($(".poptable_xk dt.on_xk").attr("commandid"));
	alarmSetUpSave($(".poptable_xk dt.on_xk").text(),$(".poptable_xk dt.on_xk").attr("commandid"));
});

function alarmSetUpSave(savetype,commandid) {
	var cId = [];
	var vId = [];
	var simnoArr = [];
	var numArr = [];
	var colorArr = [];
	for(var i = 0; i < $(".poptable_xk #carList img").length; i++) {
		if($(".poptable_xk #carList img:eq(" + i + ")").attr("src") == "img/cherked.jpg") {
			if($(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("vId") != undefined && $(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("vId") != null) {
				vId.push($(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("vid"));
				simnoArr.push($(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("simno"));
				numArr.push($(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("num"));
				colorArr.push($(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("vcolor"));
			} else {
				cId.push($(".poptable_xk #carList img:eq(" + i + ")").parent("b").attr("cId"));
			}
		}
	}
	if($(".dendrogram_xk .on_xk").length !=1){
		alert("请选择指令！");
		return;
	}
	if(vId.length == 0){
		alert("请选择车辆！");
		return;
	};
	if($(".red_wd").length != 0  ){
		alert("请填写完整信息！");
		return;
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
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			}
			var data_json = {
					"Command": CommandStr,
					"plateNo": numArr,
					"simNo": simnoArr,
					"vehicleId": vId,
					"createDate" : data_zyl
			}
			break;
		case "查询终端参数":
			var alarmport = "port/selectCommandByterminal";
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
			}
			break;
		case "查询指定终端参数":
			var alarmport = "port/selectCommandByterminal";
			var CommandArray_z = [];
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				var str = "";
				str += $("td#Vehiclecontrol table tr").find("select option:selected").eq(i).val();
				CommandArray_z.push(str);
			}
			var CommandStr = CommandArray_z.join(";");
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			}
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmdData":CommandStr,
			}
			break;
		case "位置信息查询":
			var alarmport = "port/insertTerminalController";
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":"",
				"cmd":"",
				"cmdType":"33281",
				"createDate" : data_zyl
			}
			break;
		case "临时位置跟踪控制":
			var alarmport = "port/insertTerminalController";
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":$(".time_interval_z").val()+ ";" + $(".validity_z").val(),
				"cmd":"",
				"cmdType":"33282",
				"createDate" : data_zyl
			}
			break;
		case "文本信息下发":
			var alarmport = "port/insertTerminalController";
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":$(".urgent_z option:selected").val()+ ";" + $(".terminal_display_z  option:selected").val()+ ";" + $(".terminal_read option:selected").val()+ ";" + $(".ad_display_z option:selected").val()+ ";" + $(".information_z").val(),
				"cmd":"",
				"cmdType":"33536",
				"createDate" : data_zyl
			}
			break;
		case "事件设置":
			var alarmport = "port/insertTerminalController";
			var CommandArray_z = [];
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				var str = "";
				str += $("td#Vehiclecontrol table tr").find("select option:selected").eq(i).text();
				CommandArray_z.push(str);
			}
			var CommandStr = CommandArray_z.join(";");
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			}
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":CommandStr,
				"cmd":$(".ev_set option:selected").val(),
				"cmdType":"33537",
				"createDate" : data_zyl
			}
			break;
		case "提问下发":
			var alarmport = "port/insertTerminalController";
			var CommandArray = [];
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				var str = "";
				str += $("td#Vehiclecontrol table tr").find("input:text").eq(i).val();
				CommandArray.push(str);
			}
			var CommandStr = CommandArray.join(":");
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			}
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":$(".questions_urgent_z option:selected").val()+";"+$(".questions_terminal_z  option:selected").val()+";"+$(".questions_ad_z  option:selected").val() + ";" + $(".questions_zyl").val() + ";" + CommandStr,
				"cmd":"",
				"cmdType":"33538",
				"createDate" : data_zyl
			}
			break;
		case "信息点播菜单设置":
			var alarmport = "port/insertTerminalController";
			var CommandArray = [];
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				var str = "";
				str += $("td#Vehiclecontrol table tr").find("select option:selected").eq(i).text();
				CommandArray.push(str);
			}
			var CommandStr = CommandArray.join(";");
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			}
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":CommandStr,
				"cmd":$(".info_demand option:selected").val() ,
				"cmdType":"33539",
				"createDate" : data_zyl
			}
			break;
		case "信息服务":
			var alarmport = "port/insertTerminalController";
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":$(".info_num_z").val()+ ";" + $(".info_service_z").val(),
				"cmd":"",
				"cmdType":"33282",
				"createDate" : data_zyl
			}
			break;
		case "电话回拨":
			var alarmport = "port/insertTerminalController";
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":$(".call_type_z option:selected").val()+ ";" + $(".phone_num_z").val(),
				"cmd":"",
				"cmdType":"33792",
				"createDate" : data_zyl
			}
			break;
		case "设置电话本":
			var alarmport = "port/insertTerminalController";
			var CommandArray = [];
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				var str = "";
				str += $("td#Vehiclecontrol table tr").find("select option:selected").eq(i).val();
				str += "," + $("td#Vehiclecontrol table tr").find("input:text").eq(i).val();
				str += "," + $("td#Vehiclecontrol table tr").find("input:text").eq(i).val();
				CommandArray.push(str);
			}
			var CommandStr = CommandArray.join(";");
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			}
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":CommandStr,
				"cmd":$(".set_phone_z").val(),
				"cmdType":"33793",
				"createDate" : data_zyl
			}
			break;
		case "车辆控制":
			var alarmport = "port/insertTerminalController";
			var CommandArray = [];
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				var str = "";
				str += $("td#Vehiclecontrol table tr").find("select option:selected").eq(i).val();
				str += "," + $("td#Vehiclecontrol table tr").find("input:text").eq(i).val();
				str += "," + $("td#Vehiclecontrol table tr").find("input:text").eq(i).val();
				CommandArray.push(str);
			}
			var CommandStr = CommandArray.join(";");
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":"",
				"cmd":$(".vehicle_zyl").val(),
				"cmdType":"34048",
				"createDate" : data_zyl
			}
			break;
		case "摄像头立即拍摄命令":
			var alarmport = "port/insertTerminalController";
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":$(".pass_z").val()+";"+$(".shot_z").val()+";"+$(".interval_z").val()+";"+$(".upload_z option:selected").val()+";"+$(".resolution_z option:selected").val()+";"+$(".pic_quality").val()+";"+$(".brightness_z").val()+";"+$(".contrast_z").val()+";"+$(".saturation_z").val()+";"+$(".chroma_z").val(),
				"cmd":"",
				"cmdType":"34817",
				"createDate" : data_zyl
			}
			break;
		case "存储多媒体数据检索":
			var alarmport = "port/insertTerminalController";
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":$(".retrieval_z option:selected").val()+";"+$(".passageway_z").val()+";"+$(".event_z option:selected").val()+";"+$(".startTime_z").val()+";"+$(".endTime_z").val(),
				"cmd":"",
				"cmdType":"34818",
				"createDate" : data_zyl
			}
			break;
		case "单条存储多媒体数据检索上传":
			var alarmport = "port/insertTerminalController";
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":"",
				"cmd":"",
				"cmdType":"",
				"createDate" : data_zyl
			}
			break;
		case "存储多媒体数据上传":
			var alarmport = "port/insertTerminalController";
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":$(".mul_type_zyl option:selected").val()+";"+ $(".passway_zyl").val()+";"+$(".e_item_zyl option:selected").val()+";"+$(".startTime_zyl").val()+";"+$(".endTime_zyl").val()+";"+$(".del_zyl option:selected").val() ,
				"cmd":"",
				"cmdType":"34819",
				"createDate" : data_zyl
			}
			break;
		case "录音开始命令":
			var alarmport = "port/insertTerminalController";
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":$(".save_sign_z option:selected").val()+";"+ $(".tape_z option:selected").val()+";"+$(".tape_time_z").val()+";"+$(".sampling_z option:selected").val(),
				"cmd":"",
				"cmdType":"34820",
				"createDate" : data_zyl
			}
			break;
		case "数据透传":
			var alarmport = "port/insertTerminalController";
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":$(".transparent_z option:selected").val()+";"+ $(".news_z").val(),
				"cmd":"",
				"cmdType":"35072",
				"createDate" : data_zyl
			}
			break;
		case "设置圆形区域":
			var alarmport = "port/insertTerminalController";
			var str = "";
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				str += $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(0).find("input:text").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(1).find("select option:selected").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(2).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(3).find("input:text").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(4).find("select option:selected").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(5).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(6).find("input:text").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(7).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(8).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(9).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(10).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(11).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(12).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(13).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(14).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(15).find("select option:selected").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(16).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(17).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(18).find("input:text").val();
				str += ",";
			};
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			}
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":str,
				"cmd":$(".set_area_z option:selected").val(),
				"cmdType":"34304",
				"createDate" : data_zyl
			}
			break;
		case "删除圆形区域":
			var alarmport = "port/insertTerminalController";
			var str = "";
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				//str += $("td#Vehiclecontrol table tr").find("input:text").eq(i).val();
				str += $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(0).find("input:text").val()+";";
			};
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			}
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":str,
				"cmd":"",
				"cmdType":"34305",
				"createDate" : data_zyl
			}
			break;
		case "设置矩形区域":
			var alarmport = "port/insertTerminalController";
			var str = "";
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				str += $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(0).find("input:text").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(1).find("select option:selected").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(2).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(3).find("input:text").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(4).find("select option:selected").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(5).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(6).find("input:text").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(7).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(8).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(9).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(10).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(11).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(12).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(13).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(14).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(15).find("select option:selected").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(16).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(17).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(18).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(19).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(20).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(21).find("input:text").val();
				str += ",";
			};
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			};
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":str,
				"cmd":$(".setting_area_z option:selected").val(),
				"cmdType":"34306",
				"createDate" : data_zyl
			}
			break;
		case "删除矩形区域":
			var alarmport = "port/insertTerminalController";
			var str = "";
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				str += $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(0).find("input:text").val()+";";
			};
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			};
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":str,
				"cmd":"",
				"cmdType":"34307",
				"createDate" : data_zyl
			}
			break;
		case "设置多边形区域":
			var alarmport = "port/insertTerminalController";
			var str = "";
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				str += $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(0).find("input:text").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(1).find("input:text").val();
				str += ",";
			};
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			}
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":$(".region_z").val()+";"+$(".isTime_z option:selected").val()+";"+$(".startTime_z").val()+";"+$(".endTime_z").val()+";"+$(".inregion_driver_z option:selected").val()+";"+$(".inregion_platform_z option:selected").val()+";"+$(".outregion_driver_z option:selected").val()+";"+$(".outregion_platform_z").val()+";"+$(".lon_z option:selected").val()+";"+$(".lat_z option:selected").val()+";"+$(".door_z option:selected").val()+";"+$(".signal_z option:selected").val()+";"+$(".location_z option:selected").val()+";"+$(".speedlimit_z option:selected").val()+";"+$(".speed_z").val()+";"+$(".Speeding_z").val()+str,
				"cmd":"",
				"cmdType":"34308",
				"createDate" : data_zyl
			}
			break;
		case "删除多边形区域":
			var alarmport = "port/insertTerminalController";
			var str = "";
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				str += $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(0).find("input:text").val()+";";
			};
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			}
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":str,
				"cmd":"",
				"cmdType":"34309",
				"createDate" : data_zyl
			}
			break;
		case "设置路线":
			var alarmport = "port/insertTerminalController";
			var str = "";
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				str += $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(0).find("input:text").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(1).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(2).find("select option:selected").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(3).find("input:text").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(4).find("select option:selected").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(5).find("input:text").val();
				str += ";" + $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(6).find("input:text").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(7).find("select option:selected").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(8).find("input:text").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(9).find("input:text").val();
				str +=";" +  $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(10).find("select option:selected").val();
				str += ",";
			};
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			}
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":$(".route_zl").val()+";"+$(".is_time_zl option:selected").val()+";"+$(".startTime_zl").val()+";"+$(".endTime_zl").val()+";"+$(".inpolice_driver_zl option:selected").val()+";"+$(".inpolice_platform_zl option:selected").val()+";"+$(".outpolice_driver_zl option:selected").val()+";"+$(".outpolice_platform_zl option:selected").val()+","+ str,
				"cmd":"",
				"cmdType":"34307",
				"createDate" : data_zyl
			}
			break;
		case "删除路线":
			var alarmport = "port/insertTerminalController";
			var str = "";
			for(var i = 0; i < $("td#Vehiclecontrol table tbody tr").length; i++) {
				str += $("td#Vehiclecontrol table tbody tr").eq(i).children("td").eq(0).find("input:text").val()+";";
			};
			if($("#fulllength tbody tr").length == 0){
				alert("请选择参数类型，添加相应的参数值！");
				return false;
			}
			var data_json = {
				"plateNo": numArr,
				"simNo": simnoArr,
				"vehicleId": vId,
				"cmddata":str,
				"cmd":"",
				"cmdType":"34311",
				"createDate" : data_zyl
			}
			break;
		case "驾驶员信息采集":
			var alarmport = "port/insertTerminalController";
			var data_json = {
					"plateNo": numArr,
					"simNo": simnoArr,
					"vehicleId": vId,
					"cmddata":str,
					"cmd":"",
					"cmdType":"34560",
					"createDate" : data_zyl
			}
			break;
		case "驾采集指定位置信息记录":
			var alarmport = "port/insertTerminalController";
			if($("#Vehiclecontrol .startTime_zyl").val() == "" || $("#Vehiclecontrol .endTime_zyl").val() == ""){
				alert("请添加相应的参数值！");
				return false;
			}
			var data_json = {
					"plateNo": numArr,
					"simNo": simnoArr,
					"vehicleId": vId,
					"cmddata":$("#Vehiclecontrol .startTime_zyl").val()+";"+$("#Vehiclecontrol .endTime_zyl").val(),
					"cmd":"",
					"cmdType":"34560",
					"createDate" : data_zyl
			}
			break;
		default:
			var alarmport = "port/terminalController";//终端控制
			var cmd = $("select#Terminalcontrol option:selected").val();
			var cmddata = [];
			$("#Vehiclecontrol input").each(function() {
				if($(this).val() == null) {
					cmddata.push("");
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
				"vehicleId": vId,
				"createDate" : data_zyl
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
				addSendBefore(data.obj,vId,simnoArr,numArr,colorArr,savetype,commandid,data_zyl);
				$("img[src='img/close.png']").click();
			}
		}
	});
};


function addSendBefore(data,vId,simnoArr,numArr,colorArr,savetype,commandid,data_zyl){
	//console.log(vId);
	for(one in vId){
		//console.log(one);
		html = "<tr id_z='"+data[one]+"'>";
		if(replayCommon.plateNo == 0){
			$("#tabcommandreply table tbody tr td:eq(0)").addClass("off_wd");
			html +="<td displayName='12' title='" + numArr[one] + "' class='off_wd'>"+numArr[one]+"</td>";
		}else{
			html +="<td displayName='12' title='" + numArr[one] + "'>"+numArr[one]+"</td>";
		};
		if(replayCommon.vColor == 0){
			$("#tabcommandreply table tbody tr td:eq(1)").addClass("off_wd");
			html +="<td displayName='13' title='" + colorArr[one] + "' class='off_wd'>"+colorArr[one]+"</td>";
		}else{
			html +="<td displayName='13' title='" + colorArr[one] + "'>"+colorArr[one]+"</td>";
		};
		if(replayCommon.sendTime == 0){
			$("#tabcommandreply table tbody tr td:eq(2)").addClass("off_wd");
			html +="<td displayName='14' title='" + data_zyl + "' class='off_wd'>"+data_zyl+"</td>";
		}else{
			html +="<td displayName='14' title='" + data_zyl + "'>"+data_zyl+"</td>";
		};
		if(replayCommon.cmdId == 0){
			$("#tabcommandreply table tbody tr td:eq(3)").addClass("off_wd");
			html +="<td displayName='15' title='" + commandid + "'class='off_wd'>"+commandid+"</td>";
		}else{
			html +="<td displayName='15' title='" + commandid + "'>"+commandid+"</td>";
		};
		if(replayCommon.cmdDate == 0){
			$("#tabcommandreply table tbody tr td:eq(4)").addClass("off_wd");
			html +="<td displayName='16' title='" + savetype + "' class='off_wd'>"+savetype+"</td>";
		}else{
			html +="<td displayName='16' title='" + savetype + "'>"+savetype+"</td>";
		};
		if(replayCommon.sendResult == 0){
			$("#tabcommandreply table tbody tr td:eq(5)").addClass("off_wd");
			html +="<td displayName='17' title='' class='off_wd'></td>";
		}else{
			html +="<td displayName='17' title=''></td>";
		};
		if(replayCommon.sn == 0){
			$("#tabcommandreply table tbody tr td:eq(6)").addClass("off_wd");
			html +="<td displayName='18' title='' class='off_wd'></td>";
		}else{
			html +="<td displayName='18' title=''></td>";
		};
		if(replayCommon.confirmTime	 == 0){
			$("#tabcommandreply table tbody tr td:eq(7)").addClass("off_wd");
			html +="<td displayName='19' title='' class='off_wd'></td>";
		}else{
			html +="<td displayName='19' title=''></td>";
		};
		if(replayCommon.restore == 0){
			$("#tabcommandreply table tbody tr td:eq(8)").addClass("off_wd");
			html +="<td displayName='20' title='' class='off_wd'></td>";
		}else{
			html +="<td displayName='20' title=''></td>";
		};
		if(replayCommon.resData == 0){
			$("#tabcommandreply table tbody tr td:eq(9)").addClass("off_wd");
			html +="<td displayName='21' title='' class='off_wd'></td>";
		}else{
			html +="<td displayName='21' title=''></td>";
		};
		html +="</tr>";
		$(".tablist_z table").append(html);
		var id = data[one];
		addSendAfter(id);
	}
}
function addSendAfter(id){
	$.ajax(config.url + "realTime/getTerminalCommandInfo", {
		data: {
			"cmdid" : [id],
		},
		dataType: "json",
		type: "POST",
		timeout: 10000,
		success: function(data) {
			//console.log(data.obj[0].plateColor);
			//console.log(aTr);
				var aTr =  $(".tablist_z table tr[id_z="+id+"]");
				aTr.children("td").eq(5).text(data.msg).attr("title",data.msg);
				aTr.children("td").eq(6).text(data.obj[0].sn).attr("title",data.obj[0].sn);
				if(data.obj[0].updatedate == null){
					aTr.children("td").eq(7).text("");
				}else{
					aTr.children("td").eq(7).text(data.obj[0].updatedate).attr("title",data.obj[0].updatedate);
				}
				if(data.obj[0].replyId == null){
					aTr.children("td").eq(8).text("");
				}else{
					aTr.children("td").eq(8).text(data.obj[0].replyId).attr("title",data.obj[0].replyId);
				}
				if(data.obj[0].replyParameter == null){
					aTr.children("td").eq(9).text("");
				}else{
					aTr.children("td").eq(9).text(data.obj[0].replyParameter).attr("title",data.obj[0].replyParameter);
				}
		}
	});
}
