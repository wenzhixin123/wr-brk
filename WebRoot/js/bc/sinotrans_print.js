/*
用法
$("#id").sinotrans_Print({
	path:'<%=request.getContextPath()%>',
	modelId:
});
 */
$.fn.sinotrans_Print = function(opt) {
	var selectObj = this;
	selectObj.option = {};
	selectObj.option = $.extend(selectObj.option, opt);
	var divId = new UUID().id;
	var templateId = '';
	var load = function() {
		/*var parameterStr = '';
		if(selectObj.option.parameter != null && selectObj.option.parameter != ''){
		   parameterStr = getBASE64Param(selectObj.option.parameter);
		}*/
		
		var url = selectObj.option.path
				+ "/jsp/components/selectPrintReport.jsp?divId=" + divId + "&modelId="+ selectObj.option.modelId+"&templateType="+ selectObj.option.templateType
				+"&controlParam="+ selectObj.option.controlParam //+ "&parameterStr=" + parameterStr;
		$('body')
				.append(
						'<div id="'
								+ divId
								+ '" class="easyui-window" closed="true" modal="true" title="选择" collapsible="false"><iframe scrolling="no" frameborder="0"  src="'
								+ url
								+ '" style="width:100%;height:100%;"></iframe></div>');
		$('#' + divId + '').window( {
			width : 650,
			height : 410,
			top : ($(window).height() - 410) * 0.5,
			left : ($(window).width() - 650) * 0.5,
			maximizable : false,
			minimizable : false
		});
	};

	selectObj.open = function() {
		this.setSessionParam(selectObj.option.parameter);
		templateId = this.getTemplateId();
		if(templateId == '') {
			load();
			$('#' + divId + '').window('open');
		} else {
			var strFeatureInfo = "dialogWidth:1000px;dialogHeight:1000px;resizable:no;scroll:no;center:yes;help:no;status:no;nadorned:no";
			var ver = getInternetExplorerVersion();
			if ( ver > -1 ) {
				window.open(this.getPrintUrl(),null,strFeatureInfo);
			} else {
				var url = this.getPrintUrl();
				window.showModalDialog(url,null,strFeatureInfo);
			}
		}
	};
	
	selectObj.getTemplateId = function() {
		var templateId = '';
		var obj = new Object();
		DWREngine.setAsync(false);
		obj.templateType = selectObj.option.templateType;
		obj.extraCondition1 = selectObj.option.extraCondition1;
		PrintReportTemplateManager.findTemplateUuidByModel(obj, function(listResult){
			if(listResult.length == 1){
				templateId = listResult[0];
			}
		});
		DWREngine.setAsync(true);
		return templateId;
	};
	
	selectObj.getPrintUrl = function(){
//		var parameterStr = '';
//		if(selectObj.option.parameter != null && selectObj.option.parameter != ''){
//		   parameterStr = getBASE64Param(selectObj.option.parameter);
//		}
		var url = selectObj.option.path + "/jsp/system/ireportTemplateAccept.jsp;jsessionid="+ selectObj.option.jsessionid
		+"?modelId="+ selectObj.option.modelId + "&reportId="+ templateId //+ "&parameterStr=" + parameterStr
		+"&templateType="+ selectObj.option.templateType +"&controlParam="+ selectObj.option.controlParam;
		return url;
	};
	
	selectObj.setControlParam = function(param){
		selectObj.option.controlParam = param;
	};
	
	selectObj.setParameters = function(param){
		selectObj.option.parameter = param;
	};
	
	selectObj.setSessionParam = function(params){
		DWREngine.setAsync(false);
		PrintReportTemplateManager.insertSessionParameter(params, function(result){
		});
		DWREngine.setAsync(true);
	};
	
	selectObj.close = function(){
		$('#' + divId + '').window('close');
	};
	
	var getBASE64Param = function(params){
		var str = '';
		if(params != null && params != ''){
			str = window.btoa(JSON.stringify(params));
		}
		return str;
	};
	
	var getInternetExplorerVersion = function() {
		var rv = -1; // Return value assumes failure.
		if (navigator.appName == 'Microsoft Internet Explorer') {
		 	var ua = navigator.userAgent;
		 	var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
		 	if (re.exec(ua) != null)
		   		rv = parseFloat( RegExp.$1 );
		} else if (navigator.appName == 'Netscape') {
		    var ua = navigator.userAgent;
		    var re  = new RegExp("Trident/.*rv:([0-9]{1,}[\.0-9]{0,})");
		    if (re.exec(ua) != null)
		      	rv = parseFloat( RegExp.$1 );
		}
		return rv;
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