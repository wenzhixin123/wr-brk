$(function() {
	//缓冲当前光标所在的控件,目前只支持form中可见的input框
	$.each($("form :visible:input"),function(){
		$(this).focus(function(){
			$.data(document.body,"currentFocusElement",$(this));
		});
	});
	$("form :visible:input").keydown(function(event) {
		if (event.keyCode == 13) {
			var elms = $("form :visible:input");
			var index = elms.index(this);

			if (index < elms.length - 1) {
				elms[index + 1].focus();
				// elms[index+1].select();
			}
		}
	});
});
// 主要是推荐这个函数。它将jquery系列化后的值转为name:value的形式。
function convertArray(arrays) {
	var object = {};
	for ( var key in arrays) {
		if (typeof (object[arrays[key].name]) == 'undefined') {
			object[arrays[key].name] = arrays[key].value;
		} else {
			object[arrays[key].name] += "," + arrays[key].value;
		}
	}
	return object;
}
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// dateFormat(new Date(),'yyyy-MM-dd') ==> 2006-07-02
// (new Date()).format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
// author: huangth
dateFormat = function(date,fmt) {
	var o = {
			"M+" : date.getMonth() + 1, // 月份
			"d+" : date.getDate(), // 日
			"h+" : date.getHours(), // 小时
			"m+" : date.getMinutes(), // 分
			"s+" : date.getSeconds(), // 秒
			"q+" : Math.floor((date.getMonth() + 3) / 3), // 季度
			"S" : date.getMilliseconds()
		// 毫秒
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
}