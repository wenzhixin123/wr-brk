/*
用法
$("#id").sinotrans_Import({
	path:'<%=request.getContextPath()%>',
	fileType:,//文件类型 '.xls,.xlsx',
	businessType:,//业务类型
	functionName://回调函数
	modelIds:业务ID 
});
 */
$.fn.sinotrans_Import = function(opt) {
	var selectObj = this;
	selectObj.option = {};
	selectObj.option = $.extend(selectObj.option, opt);
	var divId = new UUID().id;
	var singleton = false;//单例模式
	var load = function() {
		var url = selectObj.option.path
				+ "/jsp/components/importFile.jsp?divId=" + divId + "&fileType="+ selectObj.option.fileType+"&businessType="+ selectObj.option.businessType+"&functionName="+selectObj.option.functionName+"&modelIds="+selectObj.option.modelIds+"&versionType="+selectObj.option.versionType;
		$('body')
				.append(
						'<div id="'
								+ divId
								+ '" class="easyui-window" closed="true" modal="true" title="选择" collapsible="false"><iframe scrolling="no" frameborder="0"  src="'
								+ url
								+ '" style="width:100%;height:100%;"></iframe></div>');
		$('#' + divId + '').window( {
			width : 350,
			height : 200,
			top : ($(window).height() - 200) * 0.5,
			left : ($(window).width() - 350) * 0.5,
			maximizable : false,
			minimizable : false
		});
	}
	selectObj.open = function() {
		if(!singleton){
			load();
		}
		singleton = true;
		$('#' + divId + '').window('open');
	}
	selectObj.close = function(){
		$('#' + divId + '').window('close');
	}
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
	var n = UUID.getIntegerBits(UUID.rand(8191), 0, 7) +
	UUID.getIntegerBits(UUID.rand(8191), 8, 15) +
	UUID.getIntegerBits(UUID.rand(8191), 0, 7) +
	UUID.getIntegerBits(UUID.rand(8191), 8, 15) +
	UUID.getIntegerBits(UUID.rand(8191), 0, 15); // this last number is two
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
 * for(var i = 0; i < 10; i++){
 * document.write(new UUID().id);
 * document.write("<br/>"); }
 */