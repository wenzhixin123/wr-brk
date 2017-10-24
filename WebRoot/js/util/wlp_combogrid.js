 /*
  * Name	: 	datagrid表格列中的联想控件
  * Note	:	需要注意的3个配置 otherFields,hiddenField,interceptor
  * Author	:	huangkf 2011/11/24
  * editor="{type: '_combogrid',options:{
											url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType=BasCountryModel',
											idField:'countryCode',
											textField:'countryCode',
											value:'countryCode',
											datagridId:'datagird',		//绑定的datagrid字段
											displayField:'countryCode,countryName,countryNameEn,countryCapital',
											displayTitle:'国家代码,国家名字,国家英文名,首都',
											otherFields:{'columnField1':'countryNameEn','columnField2':'countryCapital'}, //自动填充到editor对象
											hiddenField:{'columnField1':'countryNameEn','columnField2':'countryCapital'}  //或'countryCode'或['dd','cc'],  //自动填充到隐藏的行对象
											interceptor:function(rowObj){}, //拦截行对象的函数
											inputFieldObj:{'inputId_1':'countryCode','inputId_2':'countryName','inputId_3':'countryNameEn'}
										}}"
  */
 
 $.extend($.fn.datagrid.defaults.editors, {
     _combogrid: {
         init: function(container, options){
         	options.inputClass =options.inputClass +" datagrid-editable-input";
         	var inputStr = "<input class='"+options.inputClass+"'>";
            var input = $(inputStr).appendTo(container);
            //解析行数据
            var _columns=[];
            if(options.displayField!=undefined){
            	var titles = (typeof options.displayTitle==="string")?options.displayTitle.split(","):options.displayTitle;
            	var displayFields = (typeof options.displayField==="string")?options.displayField.split(","):options.displayField;
            	options.displayField=displayFields;
            	$.each(displayFields,function(i,v){
            		if(i==1){
            			_columns.push({
	            			field:v,
	            			title:titles[i],
	            			width:160,
	            			align:"center",
	            			td_align:"left"
            			});
            		}else{
            			_columns.push({
	            			field:v,
	            			title:titles[i],
	            			width:110,
	            			align:"center",
	            			td_align:"left"
            			});
            		}
            		
           		});
            }
            options.columns=[_columns];
            //注册单击联想控件某条记录时的额外行为
            options.onClickRow=function(gridRowIndex,rowObj){
            	 var _dg=(options.datagridId==undefined)?undefined:$("#"+options.datagridId);
            	 var _ri = $(container).parent().parent().parent().parent().parent().parent().attr("datagrid-row-index");
	        	 //自动填充指定的datagrid列
	        	 if(options.otherFields!=undefined){
	        	 	if(typeof options.otherFields==="object"){
	        	 		$.each(options.otherFields,function(i,v){
		        	 		var _ce = _dg.datagrid('getEditor',{index:_ri,field:i});
		        	 		_ce.actions.setValue(_ce.target,rowObj[v]);
	        	 		});
	        	 	}
	        	 }
	        	 //自动填充指定的input
	        	 if(options.inputFieldObj!=undefined){
	        	 	if(typeof options.inputFieldObj=="object"){
	        	 		$.each(options.inputFieldObj,function(i,v){
	        	 	 		$('#'+i).val(rowObj[v]);
	        	 	 	});
	        	 	}
	        	 }
	        	 //隐藏域,设置必要的datagrid隐藏域的值
	        	 if(options.hiddenFields!=undefined){
	        	 	if($.isPlainObject(options.hiddenFields)){
	        	 		var data=_dg.datagrid('getData');
	        	 		var _r=data.rows[_ri];
	        	 		$.each(options.hiddenFields,function(i,v){
	        	 			_r[i]=rowObj[v];
	        	 		});
	        	 	}else{
	        	 		var hiddenfields = (typeof options.hiddenFields==="string")?options.hiddenFields.split(","):options.hiddenFields;
		        	 	var data=_dg.datagrid('getData');
	        	 		var _r=data.rows[_ri];
		        	 	$.each(hiddenfields,function(i,v){
		        	 		_r[v]=rowObj[v];
		        	 	});
	        	 	}
	        	 	
	        	 }
	        	 //拦截器,拦截联想控件的行对象
	        	 if(options.interceptor!=undefined){
	        	 	(options.interceptor)(rowObj);
	        	 }
            	
            };
            //默认options配置
            options=$.extend({
            	pagination:'true',
            	panelWidth:450,
            	mode:'remote',
            	autoShow:true,
				autoDWR:true,
				onChange:function(newValue,oldValue){
					//当使用键盘进行选择时,也要触发相应的赋值事件
					var grid=$.data(this, 'combogrid').grid.prevObject;
					var rowData=grid.datagrid("getSelected");
					var rowIndex;
					if(rowData){
						rowIndex=grid.datagrid('getRowIndex',rowData);
					}
					if(typeof rowIndex=="number"){
						options.onClickRow(rowIndex,rowData);
					}
				},
            	filter:function(q,row){
            		var _r=false;
            		if(row[options.idField]){
            			_r=(row[options.idField].indexOf(q)!=-1);
            		}
					return _r;
				}
            },options);
            //延迟加载url的关键代码
             $('#'+options.datagridId).data("get"+options.idField+"Url",options.url);
             options.url=undefined;
            return input.combogrid(options);
         },
        getValue: function(target){
             return $(target).combogrid("getValue");
         },
        setValue: function(target, value){
        	var opts = $(target).combogrid("options");
        	var _columnName=$(target).parents('div.datagrid-cell.datagrid-editable').parent().attr('field');
        	var _ri=$(target).parents('div.datagrid-cell.datagrid-editable').parent().parent().attr("datagrid-row-index");
        	//缓冲textbox
        	$('#'+opts.datagridId).data(opts.datagridId+"."+_columnName+_ri,$(target).combogrid("textbox"));
        	
        	//当焦点移开时,检测输入值在数据库中是否存在,若存在则保留,否则清空
        	//PS:此段代码依赖DWR,请注意DWR的引入
        	if(opts.autoDWR&&opts.autoDWR==true){
        		var url = $('#'+opts.datagridId).data('get'+opts.idField+'Url');
        		var remoteDWR=function(){
        			 var modelName = url.match("[A-Z]{1}[A-Za-z]+Model");
        			 var textValue = $(target).combogrid('getText');
        			 if(textValue!=null&&textValue!=""){
        			 	BasCommonManager.getObjByProperty({
						         type:modelName.toString().split("Model")[0],
						         keyProperty:opts.idField,
						         keyValue:$(target).combogrid('getText')
						 	},
						    function(rs){
						     if(rs==null||rs==""){
								//清空操作
								$(target).combogrid('setText','');
								//清空otherFields
								var _ri=$(target).parents('div.datagrid-cell.datagrid-editable').parent().parent().attr("datagrid-row-index");
								if(typeof opts.otherFields==="object"){
	        	 					$.each(opts.otherFields,function(i,v){
					        	 		var _ce = $("#"+opts.datagridId).datagrid('getEditor',{index:_ri,field:i});
					        	 		_ce.actions.setValue(_ce.target,"");
	        	 					});
	        	 				}
	        	 				//清空隐藏域
					        	 if(opts.hiddenFields!=undefined){
					        	 	if(typeof opts.hiddenFields=="object"){
					        	 		var _r=$("#"+opts.datagridId).datagrid('selectRow',_ri).datagrid('getSelected');
					        	 		$.each(opts.hiddenFields,function(i,v){
					        	 			_r[i]="";
					        	 		});
	        	 					}else{
	        	 						var hiddenfield = (typeof opts.hiddenFields==="string")?opts.hiddenFields.split(","):opts.hiddenFields;
						        	 	var _r=$("#"+opts.datagridId).datagrid('selectRow',_ri).datagrid('getSelected');
						        	 	$.each(hiddenfield,function(i,v){
						        	 		_r[v]="";
						        	 	});
	        	 					}
						        	 	
					        	 }
							}
				    	});
        			 }
        		};
        		$(target).combogrid("textbox").blur(remoteDWR);
        	}
        	
         	 //var _columnName=$(target).parents('div.datagrid-cell.datagrid-editable').parent().attr('field'); //获取所在列的列名
         	 $.data(document.body,$(target).combogrid("options").textField,$(target));
         	 if($(target).combogrid("options").autoShow==true){
         	 	$('input.combo-text',$(target).next()).focus(function(){
         	 		$.each($('input.combo-text'),function(){
         	 			$(this).parent().prev().combo('hidePanel');//隐藏所有其他的联想控件面板
         	 		});
         	 		$(target).combo('showPanel'); //获取焦点时弹出
         	 		//弹出之后再查询后台加载数据
         	 		var options = $(target).combogrid("options");
         	 		var url = $('#'+options.datagridId).data('get'+options.idField+'Url')+"&filter=["+options.displayField.toString()+"]";
         	 		$(target).combogrid("grid").datagrid({url:url});
         	 	});
         	 }
             $(target).combogrid("setValue",value);
         },
       resize: function(target, width){;}
       }

 });
 
