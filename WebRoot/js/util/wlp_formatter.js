/* Name:		wlp_formatter.js
 * Note:		格式化datagrid列的值规则,直接在columns添加formatter:transactionTypeFormat
 * UNC:			通用命名规范,无
 * Author:		wenjp 
 * ChangeLog:	
 * Feature:		1).	格式化datagrid中操作类型transactionType
 * LastModify:	wenjp 2011/11/26
 * Date :		create on 2011/11/26
 * 
 */
function status_Format(value,rowData,rowIndex){
	if(value=='Pending'){
		return '草稿';
	}else if(value=='Active'){
		return '有效';
	}else if(value=='Cancel'){
		return '作废';
	}else if(value=='Complete'){
		return '完结';
	}
	return value;
};

function typeFormat(value,rowData,rowIndex){
	if(value=='PDN'){
		return '加工单';
	}else if(value=='CHK'){
		return '盘点单';
	}else if(value=='ADJ'){
		return '移位单'
	}else if(value=='SIN'){
		return '入库单';
	}else if(value=='SOT'){
		return '出库单';
	}
	return value;
}

function datagridDateFormat(value,rowData,rowIndex){
	if(value==""||value==null){
		return "";
	}
	return value.substring(0,10);
}

function titleCenterStyle(){
	// datagrid标题居中显示
	$('div .datagrid-header-inner').find('.datagrid-cell').css("text-align","center");
	
}
// 设置date默认值
function initDate(ids){
	if(ids&&ids.length>0){
		BasCommonManager.getDataBaseDateFor_Yyyy_Mm_Dd(function(result){// 默认取系统数据时间
			var sysdate = result;
			$.each(ids,function(index,id){
				var obj = $("#"+id);
				// alert("id "+id +"Obj :"+obj + " AttrId:" +obj.attr("id") + "
				// class:"+obj.attr("class"));
				var style = obj.attr("class");
				if(style!=null && style.match("easyui-datebox")!=null){// 样式里包含日期
					obj.datebox("setValue",sysdate);
				}else{
					obj.val(sysdate);
				}
			});
		});
	}
}
function initDateYmdHms(ids){
	if(ids&&ids.length>0){
		BasCommonManager.getDataBaseDateFor_YMD_HMS(function(result){// 默认取系统数据时间
			var sysdate = result;
			$.each(ids,function(index,id){
				var obj = $("#"+id);
				// alert("id "+id +"Obj :"+obj + " AttrId:" +obj.attr("id") + "
				// class:"+obj.attr("class"));
				var style = obj.attr("class");
				if(style!=null && (style.match("easyui-datebox")!=null || style.match("easyui-datetimebox")!=null)){// 样式里包含日期
					obj.datebox("setValue",sysdate);
				}else{
					obj.val(sysdate);
				}
			});
		});
	}
}
// 格式化状态的显示值
function orderStatusFormat(id,statusId){
	var status = $('#'+statusId).val();
	if(status=='Pending'){
		$('#'+id).val('草稿');
	}else if(status=='Active'){
		$('#'+id).val('确认');
	}else if(status=='Cancel'){
		$('#'+id).val('作废');
	}else if(status=='Complete'){
		$('#'+id).val('完结');
	}
}

function remainQty_Format(value,rowData,rowIndex){
	if (value>0) {
		return '<img src="../../js/easyui/themes/icons/ok.png"/>';
	}else{
		return '<img src="../../js/easyui/themes/icons/toolbar_cancel.png"/>';
	}
}

function fullOutQty_Format(value,rowData,rowIndex){
	var remainQty = rowData.remainQty;
	if (value>0 && remainQty<=0) {
		return '<img src="../../js/easyui/themes/icons/ok.png"/>';
	}else{
		return '<img src="../../js/easyui/themes/icons/toolbar_cancel.png"/>';
	}
}

/*
 * 将Date/String类型,解析为String类型. 传入String类型,则先解析为Date类型 不正确的Date,返回 ''
 * 如果时间部分为0,则忽略,只返回日期部分.
 */
function formatDate(v) {
	if (v instanceof Date) {
		var y = v.getFullYear();
		var m = v.getMonth() + 1;
		if(m<10){
			m="0"+m
		}
		var d = v.getDate();
		if(d<10){
			d="0"+d
		}
		return y + '-' + m + '-' + d;
	}
	alert('时间格式不正确!');
	return '';
}
/**
 * 联想控件封装
 * thisId  控件选择ID
 * nextId	回车事件后跳转到某个元素ID 
 * row		数据行对象{{name:,value:}}
 * path		系统上下文根 'request.getContextPath()'
 * modelName	数据对象名称
 * hiddenId		隐藏域ID
 * required		是否必填 
 * 
 */
function combogridSetValueAndTest(thisId,nextId,row,path,modelName,hiddenId,required){
	$("#"+thisId).combogrid({
		panelWidth:500,
		height:500,
		required:required,
		rownumbers:true,
		pagination:true,
		pageSize:5,
		pageList:[5,10,20],
		idField:row[0].name,
		textField:row[1].name,
		url:path+"/servlet/SinotransServlet?cmd=query&queryType="+modelName+"&__status=Active&filter=["+row[0].name+","+row[1].name+","+row[2].name+"]",
		mode:'remote',
		columns:[[
			{field:row[0].name,title:row[0].value,width:100,align:"center"},
			{field:row[1].name,title:row[1].value,width:160,align:"center"},
			{field:row[2].name,title:row[2].value,width:160,align:"center"}
		]],
		onChange:function(newValue, oldValue){
			var select = $('#'+thisId).combogrid('grid').datagrid('getSelected');
			var value = $('#'+thisId).combogrid('getValue');
			if (select) {
				$('#'+hiddenId).val(select[row[0].name]);
			}
			var text = $("#"+thisId).combogrid('getText');
	    	if(!text || text==''){
	    		$('#'+hiddenId).val(null);
	    	}
			if(value){
				$('#'+hiddenId).val(value);
			}
		},
		onSelect:function(rowIndex, rowData){
			if (rowData) {
				$('#'+hiddenId).val(rowData[row[0].name]);
			}
		}
	});
	var textbox = $('#'+thisId).combobox('textbox');// 获取文本框事件
	textbox.bind('keydown',function(event){
	    if(event.keyCode == 13){
	    	var boxText;
	    	if($("#"+nextId).attr('datebox')){
	    		boxText = $("#"+nextId).datebox('textbox');
	    		boxText.focus();
	    	}else if($("#"+nextId).attr('combo')){
	    		boxText = $("#"+nextId).combobox('textbox');
	    		boxText.focus();
	    	}else{
	    		$("#"+nextId).focus();// 获取下一个控件的焦点
	    	}
		}
	}).unbind('change').bind('change',function(){
		var value = $("#"+thisId).combogrid('getValue');
		DWREngine.setAsync(false); 
		 BasCommonManager.getObjByProperty({
	         type:modelName.substring(0,modelName.lastIndexOf("M")),
	         keyProperty:row[0].name,
	         keyValue:value},
	         function(rs){
		             if(rs=='' || rs==null){
						// 验证是否存在，不存在清空即可
						$("#"+thisId).combogrid('setText',null);
						var __jq = $('#'+hiddenId);
						if (__jq) {
				    		__jq.val(null);
						}
					}
	   });
	   DWREngine.setAsync(true); 
	}).unbind('blur').bind('blur',function(){
		textbox.trigger('change');
	});
}

/**
 * 联想给隐藏的Uuid赋值控件封装
 * yuyb
 */
function combogridSetValueAndUuid(thisId,nextId,row,path,modelName,hiddenId,required){
	$("#"+thisId).combogrid({
		panelWidth:500,
		height:500,
		required:required,
		rownumbers:true,
		pagination:true,
		pageSize:5,
		pageList:[5,10,20],
		idField:row[0].name,
		textField:row[1].name,
		url:path+"/servlet/SinotransServlet?cmd=query&queryType="+modelName+"&filter=["+row[0].name+","+row[1].name+","+row[2].name+"]&__status=Active",
		mode:'remote',
		columns:[[
			{field:row[0].name,title:row[0].value,width:100,align:"center"},
			{field:row[1].name,title:row[1].value,width:160,align:"center"},
			{field:row[2].name,title:row[2].value,width:160,align:"center"},
			{field:row[3].name,title:row[3].value,width:160,align:"center",hidden:true}  //含有隐藏的值(uuid)，并把其赋值给隐藏的控件，
		]],
		onChange:function(newValue, oldValue){
			var select = $('#'+thisId).combogrid('grid').datagrid('getSelected');
			if (select) {
				$('#'+hiddenId).val(select[row[3].name]);
			}
			var text = $("#"+thisId).combogrid('getText');
	    	if(!text || text==''){
	    		$('#'+hiddenId).val(null);

	    	}
		},
		onSelect:function(rowIndex, rowData){
			if (rowData) {
				$('#'+hiddenId).val(rowData[row[3].name]);
			}
		}
	});
	var textbox = $('#'+thisId).combobox('textbox');// 获取文本框事件
	textbox.bind('keydown',function(event){
	    if(event.keyCode == 13){
	    	var boxText;
	    	if($("#"+nextId).attr('datebox')){
	    		boxText = $("#"+nextId).datebox('textbox');
	    		boxText.focus();
	    	}else if($("#"+nextId).attr('combo')){
	    		boxText = $("#"+nextId).combobox('textbox');
	    		boxText.focus();
	    	}else{
	    		$("#"+nextId).focus();// 获取下一个控件的焦点
	    	}
		}
	}).unbind('change').bind('change',function(){
		var value = $("#"+thisId).combogrid('getValue');
		 BasCommonManager.getObjByProperty({
	         type:modelName.substring(0,modelName.lastIndexOf("M")),
	         keyProperty:row[0].name,
	         keyValue:value},
	         {
	        	 callback:function(data){
		             if(data=='' || data==null){
						// 验证是否存在，不存在清空即可
						$("#"+thisId).combogrid('setText','');
						var __jq = $('#'+hiddenId);
						if (__jq) {
				    		__jq.val(null);
						}
					}
	         	},
	         	errorHandler:function(errorString, exception) {// 执行异常时
				　　　	alert(errorString,'warning');
				　　}
	         }
	       );

	});
}

function clearCombo(objId){
	$('#'+objId).combogrid('setValue','');
}
function setComboValue(objId,value){
	$('#'+objId).combogrid('setValue',value);
}
function setComboText(objId,value){
	$('#'+objId).combogrid('setText',value);
}
// 回车键代替tab键
function initInputKeyPressEvent(formId){
	$("#"+formId+" input:text").keypress(function(e)
            {
                if (e.which == 13)                         // 判断所按是否回车键
                {
                    var inputs = $("#"+formId).find(":text,.combo");                   // 获取表单中的所有输入框
                    var idx = inputs.index(this);      // 获取当前焦点输入框所处的位置
                    if (idx == inputs.length - 1)     // 判断是否是最后一个输入框
                    {
                      
                    }
                    else
                    {
                        inputs[idx + 1].focus();       // 设置焦点
                        inputs[idx + 1].select();       // 选中文字
                    }
                    return false;// 取消默认的提交行为
                }
            });
}


function focusEditCell(target,index,fieldName){
	var editor = $('#'+target).datagrid('getEditor',{index:index,field:fieldName});
	var style = editor.target.attr("class");
	var obj;
	if(style.match("combo")!=null){
		obj = editor.target.combogrid("textbox");
		if(!obj){
			obj = editor.target.datebox("textbox");
		}
		obj.focus();
		obj.select();
	}else{
		editor.target.focus();
		editor.target.select();
	}
}

// datagrid中回车键代替tab
function editCell(target,index){
			var editors = $('#'+target).datagrid('getEditors', index);
			for(var i=0; i<editors.length; i++){
				var obj;
				if (editors[i].target.attr("class").match('combo')!=null) {
					obj = editors[i].target.combogrid("textbox");
					if(!obj){
						obj = editors[i].target.datebox("textbox");
					}
				}else{
					obj=editors[i].target;
				}
				if(i!=(editors.length-1)){
					
					obj.bind('keydown',{field:editors[i+1].field},function(e){
						if (e.keyCode == 13){
							focusEditCell(target,index,e.data.field);
						} 
					});
				}else{
					obj.bind('keydown',{field:editors[i].field},function(e){
						if (e.keyCode == 13){
							this.blur();
							$('#'+target).datagrid('endEdit', index);
							$('#'+target).datagrid('refreshRow', index);
						} 
					});
				}
				
			}
}

function disabledOrEnabledDateBox(idArray,cmd){
	$.each(idArray,function(index,id){
		var obj = $("#"+id);		
		obj.datebox(cmd);
	});
}
function disabledOrEnabledCombogrid(idArray,cmd){
	$.each(idArray,function(index,id){
		var obj = $("#"+id);
		obj.combogrid(cmd);
	});
	
}

function formatNumInDatagrid(value,rowData,rowIndex){
	if(value){
		var str = value.toString();
		if (str.lastIndexOf('.')>0) {
			var numStr = str.substring(str.lastIndexOf('.')+1,str.length);
			var zoreStr = '';
			if (numStr.length>0) {
				var index=0;
				for ( var i = numStr.length-1; i >=0; i--) {
					if(numStr.charAt(i)=='0'){
						index++;
					}else{
						break;
					}
				}
				if (numStr.length-index>0) {
					numStr = numStr.substr(0,numStr.length-index);
					return Number(str.split('.')[0]+'.'+numStr);
				}else{
					return Number(str.split('.')[0]);
				}
			}
		}
	}
	return value;
}

/*
 * 给form赋值，formId表单id，data为json对象
 */
function load(formId,data){
	var form = $("#"+formId);
	for(var name in data){
		var val = data[name];
		if($('input[id='+name+']', form)){
			$('input[id='+name+']', form).val(val);
		}else if($('textarea[id='+name+']', form)){
			$('textarea[id='+name+']', form).val(val);
		}else if($('select[id='+name+']', form)){
			$('select[id='+name+']', form).val(val);
		}
	}
}
function stringToDate(dateStr) {
	if(dateStr==null || dateStr=='' || dateStr.length<1){
		return "";
	}
	var converted = Date.parse(dateStr);
	var myDate = new Date(converted);
	if (isNaN(myDate)) {
		var arys = dateStr.split('-');
		myDate = new Date(arys[0], --arys[1], arys[2]);
	}
	return myDate;
}







