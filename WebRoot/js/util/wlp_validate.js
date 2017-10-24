/* Name:		wlp_validate.js
 * Note:		扩展easyUI validateBox的校验规则
 * UNC:			通用命名规范,无
 * Author:		hth 
 * ChangeLog:	1. 	2011/11/10加入了唯一性校验isUnique
 * Feature:		1).	最少长度验证 minLength ,使用方法 validType="minLength[5]"
 * 				2).	唯一性校验 	isUnique  ,使用方法 validType="isUnique"
 * LastModify:	huangkf 2011/11/10
 * Date :		create on 2011/11/09
 * 
 */
//数组去重
(function($){
function removeRepater(array) {
    array = array || [];
    var a = {};
    for (var i = 0; i < array.length; i++) {
        var v = array[i];
        if (a[v] == undefined) {
            a[v] = 1;
        }
    }
     
    array.length = 0;
    for (var i in a) {
        array[array.length] = i;
    }
 
    return array;
}
$.extend($.fn.validatebox.defaults.rules,
				{
					minLength : {
						validator : function(value, param) {
							return value.length >= param[0];
						},
						message : "请输入 {0} 个字符."
					},
					maxLength : {
						validator : function(value, param) {
							return value.length <= param[0];
						},
						message : "请输入不大于 {0} 个字符."
					},
					isUnique : {
						validator : function(value, param) {
						//同时输入多个值不能有重复的验证
						var th = $("th:hidden").filter(function(){
							if(Boolean($(this).attr("editor"))&&/isUnique/.test($(this).attr("editor"))){
								return true;
							}else{
								return false;
							};
						});
						var field = $(th).attr("field");
						var allInput=$("div.datagrid-view2").find("tr.datagrid-row-editing")
						.find("td[field="+field+"]").find("input.datagrid-editable-input");
						var tempArray = [];
						$.each(allInput,function(){
							$(this).val($(this).val().toUpperCase());
							tempArray.push($(this).val());
						});
						var changeValArrays=[];
						for(var k=0;k<tempArray.length;k++){
							changeValArrays.push(tempArray[k].toUpperCase());
						}
						if( /(\x0f[^\x0f]+\x0f)[\s\S]*\1/g.test( "\x0f "+changeValArrays.join( "\x0f\x0f ")+ "\x0f ")){
							this.message="输入的值不能有重复";
							return false;
						}
						//值不能为中文的验证
						if(/[\u4e00-\u9fa5]+/g.test(value)){
							this.message="值不能是中文";
							return false;
						}
						//输入的值不能与数据库中的值相等的验证
						for ( var i = 0; i < v_global_exitsCodes.length; i++) {
							if (value == v_global_exitsCodes[i]) {
								this.message="值已存在!";
								return false;
							}
						}
						return true;

						},
						message : "值已存在!"
					},
					customValid:{
						validator : function(value, param) {
							
							return param[0](value);
						},
						message : "值已存在!"
					},
					compareToDate:{
						validator : function(value, param) {
							return param[0](value);
						},
						message : "起始时间不能大于结束时间!"
					},
					cancelDateValid:{
						validator : function(value, param) {
							return param[0](value);
						},
						message : "作废时间不能在创建时间之前"
					},
					dateFormat : {
						validator : function(value, param) {
							// 支持闰年等验证
							var result = value
									.match(/^((((19|20)\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\d|30))|(((19|20)\d{2})-(0?[13578]|1[02])-31)|(((19|20)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$/);
							if (result == null) {
								return false;
							} else {
								return true;
							}
						},
						message : "日期格式有误"
					},
					timeFormat :{
						validator:function(value, param){
						var result = value.match(/^(\d+)-(\d{1,2})-(\d{1,2})\s(\d{1,2}):(\d{1,2}):(\d{1,2})$/);
						if (result == null) {
							return false;
						} else {
							return true;
						}
					},
						message:"时间格式有误"
					},
					chinese :{
						validator:function (value,param){
							return new RegExp(/^[\u4e00-\u9fa5]*$/).test(value);
						},
						message:"只能输入中文" 
					},
					english : {
						validator:function (value,param){
							var result = value.match(/^[A-Za-z]+$/g);
							if (result == null) {
								return false;
							} else {
								return true;
							}
						},
						message:"只能输入英文" 
					},
					positiveNumber:{
						validator:function(value,param){
							return /^(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value);
						},
						message:"请输入正确的,大于0的整数或小数"
					},
					intNumber:{
						validator:function(value,param){
							return /^\d+$/.test(value);
						},
						message:"只能输入大于0的整数"
					},
					noChinese:{
						validator:function(value,param){
							return !/[\u4e00-\u9fa5]+/.test(value);
						},
						message:"不能含有中文字符"
					},
					maxNumber:{
						validator:function(value,param){
							var bool = /^\d+$/.test(value) && value.length<=param[0];
							return bool;
						},
						message:"请输入不大于{0}位的数字."
					},
					numberRange:{
						validator:function(value,param){
							var bool = /^-?\d*(\.\d+)?$/.test(value) && value>=param[0] && value<=param[1];
							return bool;
						},
						message:"请输入大于{0}小于{1}的数."
					},
					zhengFloat:{
						validator:function(value,param){
							var bool = /^\d*(\.\d+)?$/.test(value);
							return bool;
						},
						message:"请输入大于0的数."
					},
					maxFloat:{
						validator:function(value,param){
							var bool = /^\d*(\.\d+)?$/.test(value) && value.length<=param[0];
							return bool;
						},
						message:"请输入不大于{0}位的数字."
					}
				});	
	
})(jQuery)
