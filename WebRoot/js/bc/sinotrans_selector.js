/*
用法
$("#id").sinotrans_Selector({
	path:'<%=request.getContextPath()%>',上下文根	(系统上下文根)	必须
	types:,查询数据的类型	(查询数据类型、根据CommonUtil.java 定义为基准)	必须
	key:,选择后返回赋值的id (接受赋值对象ID )	
	value:,选择后返回赋值的name (接受赋值对象ID )
	functionName:,选择后返回赋值的对象（方法名称 function(obj){}）
	divId:,展示层的ID(页面唯一)	必须
	param: 参数 从业务页面传送参数到选代码页面作为过滤条件
});

 */
$.fn.sinotrans_Selector = function(opt) {
	var selectObj = this;
	selectObj.option = {};
	selectObj.option = $.extend(selectObj.option, opt);
	var divId = selectObj.option.divId;
	var singleton = false;//单例模式
	if(divId==null || '' == divId){
		divId = new UUID().id;
	}
	var load = function() {
		var url = selectObj.option.path
				+ "/jsp/components/selectCode.jsp?divId=" + divId + "&types="
				+ selectObj.option.types + "&key=" + selectObj.option.key
				+ "&value=" + selectObj.option.value + "&functionName="
				+ selectObj.option.functionName + "&param="+selectObj.option.param;

		if ($("#" + divId).length > 0) {
			$("#" + divId).empty();
		} else {
			$('body')
					.append(
							'<div id="' + divId + '" class="easyui-window" closed="true" modal="true" title="选择" collapsible="false"></div>');
		}

		$("#" + divId)
				.append(
						'<iframe scrolling="no" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>');
		$('#' + divId + '').window( {
			width : 650,
			height : 410,
			top : ($(window).height() - 410) * 0.5,
			left : ($(window).width() - 650) * 0.5,
			maximizable : false,
			minimizable : false
		});
	}
	$(selectObj).unbind("click").click(function() {
		if(!singleton){
			load();
		}
		singleton = true;
		$('#' + divId + '').window('open');
	});

	return this;
}
function UUID() {
	this.id = this.createUUID();
}
UUID.prototype.valueOf = function() {
	return this.id;
}

UUID.prototype.toString = function() {
	return this.id;
}

UUID.prototype.createUUID = function() {
	var dg = new Date(1582, 10, 15, 0, 0, 0, 0);
	var dc = new Date();
	var t = dc.getTime() - dg.getTime();
	var tl = UUID.getIntegerBits(t, 0, 31);
	var tm = UUID.getIntegerBits(t, 32, 47);
	var thv = UUID.getIntegerBits(t, 48, 59) + '1'; // version 1, security
	var csar = UUID.getIntegerBits(UUID.rand(4095), 0, 7);
	var csl = UUID.getIntegerBits(UUID.rand(4095), 0, 7);
	var n = UUID.getIntegerBits(UUID.rand(8191), 0, 7)
			+ UUID.getIntegerBits(UUID.rand(8191), 8, 15)
			+ UUID.getIntegerBits(UUID.rand(8191), 0, 7)
			+ UUID.getIntegerBits(UUID.rand(8191), 8, 15)
			+ UUID.getIntegerBits(UUID.rand(8191), 0, 15); // this last number
	// is two
	return tl + tm + thv + csar + csl + n;
}
UUID.getIntegerBits = function(val, start, end) {
	var base16 = UUID.returnBase(val, 16);
	var quadArray = new Array();
	var quadString = '';
	var i = 0;
	for (i = 0; i < base16.length; i++) {
		quadArray.push(base16.substring(i, i + 1));
	}
	for (i = Math.floor(start / 4); i <= Math.floor(end / 4); i++) {
		if (!quadArray[i] || quadArray[i] == '')
			quadString += '0';
		else
			quadString += quadArray[i];
	}
	return quadString;
}
UUID.returnBase = function(number, base) {
	return (number).toString(base).toUpperCase();
}
UUID.rand = function(max) {
	return Math.floor(Math.random() * (max + 1));
}
// end of UUID class file
/*
 * for(var i = 0; i < 10; i++){ document.write(new UUID().id); document.write("<br/>"); }
 */