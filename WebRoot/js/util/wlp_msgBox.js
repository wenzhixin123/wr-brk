
/* Name:		wlp_msgBox.js
 * Note:		扩展easyUI MsgBox
 * UNC:			通用命名规范,无
 * Author:		hth 
 * Date :		create on 2011/11/14
 * 
 */
wlpAlert = function () {
	var title = "提醒";
	var message = "";
	if (arguments.length == 1) {
		message = arguments[0];
	} else {
		if (arguments.length == 2) {
			title = arguments[0];
			message = arguments[1];
		}
	}
	$.messager.alert(title, message);
	//按确定键关闭easyUI alert窗口,并focus到弹窗之前光标所在的控件
	//支持IE,firefox,chrome,opera浏览器
	$("div.messager-button a").focus();
	var opts=$("div.messager-body").window("options");
	opts.onClose=function(){
		//该行代码依赖myUtil.js里对input..控件的缓冲
		var dd = $.data(document.body,"currentFocusElement");
		if(dd){
				dd.focus();
		}
	}
	$("div.messager-button a").keydown(function(even){
		if(even.keyCode==13){
			$("div.messager-body").window("close");
		}
	});
};
error = function () {
	var title = "异常提示";
	var message = "";
	if (arguments.length == 1) {
		message = arguments[0];
	} else {
		if (arguments.length == 2) {
			title = arguments[0];
			message = arguments[1];
		}
	}
	$.messager.alert(title, message, "error");
};
info = function () {
	var title = "信息";
	var message = "";
	if (arguments.length == 1) {
		message = arguments[0];
	} else {
		if (arguments.length == 2) {
			title = arguments[0];
			message = arguments[1];
		}
	}
	$.messager.alert(title, message, "info");
};
warning = function () {
	var title = "警告";
	var message = "";
	if (arguments.length == 1) {
		message = arguments[0];
	} else {
		if (arguments.length == 2) {
			title = arguments[0];
			message = arguments[1];
		}
	}
	$.messager.alert(title, message, "warning");
};
window.confirm = function (title, message, fn) {
	$.messager.confirm(title, message, fn);
};
window.prompt = function (title, message, fn) {
	$.messager.prompt(title, message, fn);
};