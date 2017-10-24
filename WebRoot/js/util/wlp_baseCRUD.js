/* Name:		wlp_baseCRUD.js
 * Note:		封装easyUI datagrid的基本crud
 * UNC:			通用命名规范,无
 * Author:		huangkf
 */
/********************************Begin 旧版本代码****************************************************/
/*
 * 旧版本源代码,将在重构之后删除,以便减少文件大小
 */
var BaseCRUD=window.BaseCRUD =function(gri,opt,url){
	$.datagridNo = 0;
	$.data(document.body,'_datagridID'+$.datagridNo,gri);
	$.datagridNo=$.datagridNo+1;
	new WLP_BaseCRUD(gri,opt,url);
}
var notNullFieldsObj = {};
function WLP_BaseCRUD(p_girdId,p_options,p_request_url){
	$('#'+p_girdId).data('lastIndex');
	var tempArray=new Array(); //存放临时model的数组对象
	var dataGridId;
	var  doSthAfterSave;
	var p_modelUuId=p_options.modelUuId;
	$('#'+p_girdId).data('_time_201111271153_options',p_options);
	doSthAfterSave = function(){};
	
	if(p_options.uniqueColumnsIndex){
		$('#'+p_girdId).data('uniqueColumnsIndex',p_options.uniqueColumnsIndex);
	}
	if(p_options.hasOwnProperty("doSthAfterSave")){
		doSthAfterSave=p_options.doSthAfterSave;
	}
	function EasyUIFunc(){
		var url=p_request_url;
		dataGridId=arguments[0];
		//新增按钮
		this["fn_newBtn"]=function(){
			//先验证下正在编辑的数据
			var vilidate = true;
			var lastIndex = $('#'+dataGridId).data('lastIndex');
			if(lastIndex>=0){
				vilidate=$("#"+dataGridId).datagrid('validateRow',lastIndex);
				if(!vilidate){
					alert("验证失败：请先正确填写正在编辑的数据");
					return;
				}
			}
		
			$("#"+dataGridId).datagrid('endEdit', $('#'+dataGridId).data('lastIndex'));
			$('#'+dataGridId).datagrid('insertRow',{
				row:{status:'Pending'}
			});
			$('#'+p_girdId).data('lastIndex',$("#"+dataGridId).datagrid('getRows').length-1);
			$("#"+dataGridId).datagrid('selectRow', $('#'+dataGridId).data('lastIndex'));
			$("#"+dataGridId).datagrid('beginEdit', $('#'+dataGridId).data('lastIndex'));
			editCell(dataGridId,$('#'+dataGridId).data('lastIndex'));
			return;
		};
		//删除按钮
		this["fn_delBtn"]=function(){
			//生效的时候不允许删除
			var _rows=$("#"+dataGridId).datagrid('getAllSelected');
			var _isActive=false;
			$.each(_rows,function(){
				if(this.status=="Active"||this.status=="Cancel"){
					_isActive=true;
					return false;
				}
			});
			if(_isActive){
				alert('已生效或已作废的记录不能够删除!');
				return;
			}
			if (_rows.length>0){
				$.messager.confirm('确定框', '确定删除'+_rows.length+'条记录吗?', function(r){
						if (r){
							//缓冲删除的数据
							if (_rows.length>0) {
									for ( var j = 0; j < _rows.length; j++) {
										_rows[j]._class=undefined;
										_rows[j].createTime=undefined;
										_rows[j].modifyTime=undefined;
										_rows[j].rowState="Deleted";
										if(notNullFieldsObj!=undefined){
											$.extend(_rows[j],notNullFieldsObj);
										}
										tempArray.push(_rows[j]);
									}
							}
							
							var jsonResult= $.toJSON(tempArray);
							//使用ajax提交到服务器保存数据
							LogisticsOrderManager.object2base64(jsonResult,function(data){
							if(data){
								
								$.ajax({
									type: "POST",
								    url: url,
								    data:"jsonResult="+data+"",
								    success: function(result){
								    		var rs = $.parseJSON(result);
								   	 		if(rs&&rs.error){
								    			alert('执行删除时发生错误:'+rs.error);
								    			$("#"+dataGridId).datagrid('rejectChanges');
								    			return;
				    						}
									    	var model = p_modelUuId.split('Uuid');
									   		model=model[0].substring(0,1).toUpperCase()+model[0].substring(1)+"Model";
									   		v_global_exitsCodes=[];
										    if(fn_validate_isUnique){
						   						fn_validate_isUnique(model);
						   					}
									    	if(tempArray.length>0){
									    		alert('删除成功');
									    		//执行删除
												$(tempArray).each(function(){
													$("#"+dataGridId).datagrid('deleteRow',$("#"+dataGridId).datagrid('getRowIndex',this));
												});
									    		doSthAfterSave();
									    	}
									    	tempArray=[];//清空数组
									    	$("#"+dataGridId).datagrid({});
									    	$("#"+dataGridId).datagrid("clearSelections");
										},
									error:function (XMLHttpRequest, textStatus, errorThrown){
										alert("Error:"+XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
									}
								});
							}
						 });
						 
						}
				});
			}else{
				 alert("请选择所要删除的行！");
			}
		};	
		//返回按钮
		this["fn_returnBtn"]=function(){
			$("#"+dataGridId).datagrid('rejectChanges');
			tempArray=[];
		};
		
		//s
		this["fn_validateBtn"]=function(){
			//验证
			var validated= false;
			if($('#'+dataGridId).datagrid('getEditRowsIndex').length>0){
				var editRowsIndex=$('#'+dataGridId).datagrid('getEditRowsIndex');
				$.each(editRowsIndex,function(){
					if(!$("#"+dataGridId).datagrid('validateRow',this)){
						validated=true;
					}
				});
			}
			if(validated){
				alert("验证失败:请输入必输项!");
				return;
			}
				
			$('#'+dataGridId).datagrid('endAllEdits');
			var rows = $('#'+dataGridId).datagrid('getAllSelected');
			if(rows.length==0){
				alert("请至少选择一条记录!");
				return;
			}
			var _opts = $('#'+dataGridId).data('_time_201111271153_options');
			var flag=true;
			var flag1=false;
			$.each(rows,function(){
					if(this.status!="Active"){
						flag=false;
					}
//					if(this.status=="Cancel"){
//						alert("作废的记录不能够再生效");
//						flag1=true;
//						return false;
//					}
				});
			if(flag){
				alert("所选记录都已生效");
				return;
			}
			if(flag1){
				return;
			}
			if(rows.length>0){
				$.messager.confirm('确定框', '确定生效'+rows.length+'条记录吗?', function(r){
					if(r){
						$.each(rows,function(i,v){
							//生效操作,设置状态为生效
							this.status='Active';
							var selectedRow = this;
							var index = $("#"+dataGridId).datagrid('getRowIndex',this);
							if(selectedRow._class){
								selectedRow.rowState="Modified";
							}else{
								selectedRow.rowState="Added";
							}
							selectedRow._class=undefined;
							selectedRow.createTime=undefined;
							selectedRow.modifyTime=undefined;
							tempArray.push(selectedRow);
							$("#"+dataGridId).datagrid('refreshRow',index);
						});
						//使用ajax提交到服务器保存数据
						var jsonResult = $.toJSON(tempArray);
						LogisticsOrderManager.object2base64(jsonResult,function(data){
						if(data){
							$.ajax({
								type: "POST",
							    url: url,
							    data:"jsonResult="+data+"",
							    success: function(result){
							    		var rs = $.parseJSON(result);
							   	 		if(rs&&rs.error){
							    			alert('执行生效时发生错误:'+rs.error);
							    			$("#"+dataGridId).datagrid('rejectChanges');
							    			return;
			    						}
								    	var model = p_modelUuId.split('Uuid');
								   		model=model[0].substring(0,1).toUpperCase()+model[0].substring(1)+"Model";
								   		v_global_exitsCodes=[];
								   		if(fn_validate_isUnique){
					   						fn_validate_isUnique(model);
					   					}
								    	if(tempArray.length>0){
								    		alert('生效成功');
								    		doSthAfterSave();
								    	}
								    	tempArray=[];//清空数组
								    	$("#"+dataGridId).datagrid("reload");
								    	$("#"+dataGridId).datagrid("clearSelections");
									}
							});
						}
						});
						
						
					}
				});
			}else{
				alert("请选择要生效的记录！");			
			}
			return;
		};
		
		//作废按钮
		this["fn_cancelBtn"]=function(){
			if($('#'+dataGridId).datagrid('getEditRowsIndex').length>0){
				alert("保存后才能进行作废操作!");
				return;
			}
			var validated=false;
			if($('#'+dataGridId).datagrid('getEditRowsIndex').length>0){
				var editRowsIndex=$('#'+dataGridId).datagrid('getEditRowsIndex');
				$.each(editRowsIndex,function(){
					if(!$("#"+dataGridId).datagrid('validateRow',this)){
						validated=true;
					}
				});
			}
			if(validated){
				alert("验证失败:请输入必输项!");
				return;
			}
			$('#'+dataGridId).datagrid('endAllEdits');
			var rows = $('#'+dataGridId).datagrid('getAllSelected');
			var _opts = $('#'+dataGridId).data('_time_201111271153_options');
			if(rows.length==0){
				alert("请至少选择一条记录!");
				return;
			}
			var flag;
			if(rows.length==1){
				if(rows[0].status=="Cancel"){
					alert("该记录已作废!");
					flag = true; 
					return;
				}
			}
			
			if(rows.length>0){
				var flag=false;
				$.each(rows,function(){
					if(this.status=="Pending"){
						flag=true;
					}
				});
				if(flag){
					alert("生效的记录才能作废!");
					return;
				}
				$.messager.confirm('确定框', '确定作废这'+rows.length+'条记录吗?', function(r){
					if(r){
						$.each(rows,function(i,v){
							//作废操作,设置状态为作废
							this.status='Cancel';
							var selectedRow = this;
							var index = $("#"+dataGridId).datagrid('getRowIndex',this);
							selectedRow._class=undefined;
							selectedRow.createTime=undefined;
							selectedRow.modifyTime=undefined;
							selectedRow.rowState="Modified";
							tempArray.push(selectedRow);
							$("#"+dataGridId).datagrid('refreshRow',index);
						});
						
						//使用ajax提交到服务器保存数据
						var jsonResult = $.toJSON(tempArray);
						LogisticsOrderManager.object2base64(jsonResult,function(data){
						if(data){
						
							$.ajax({
								type: "POST",
							    url: url,
							    data:"jsonResult="+data+"",
							    success: function(result){
							    		var rs = $.parseJSON(result);
							   	 		if(rs&&rs.error){
							    			alert('执行作废时发生错误:'+rs.error);
							    			$("#"+dataGridId).datagrid('rejectChanges');
							    			return;
			    						}
								    	var model = p_modelUuId.split('Uuid');
								   		model=model[0].substring(0,1).toUpperCase()+model[0].substring(1)+"Model";
								   		v_global_exitsCodes=[];
								    	if(fn_validate_isUnique){
					   						fn_validate_isUnique(model);
					   					}
								    	if(tempArray.length>0){
								    		alert('作废成功');
								    		doSthAfterSave();
								    	}
								    	tempArray=[];//清空数组
								    	$("#"+dataGridId).datagrid("reload");
								    	$("#"+dataGridId).datagrid("clearSelections");
									}
							});
						 }
						});
						
						
						
					}
				});
			}else{
				alert("请选择要作废的记录！");			
			}
			return;
		};
		
		//导入按钮
		this["fn_importBtn"]=function(){};
		
		//导出按钮
		this["fn_exportBtn"]=function(){};
		
		//保存按钮
		this["fn_saveBtn"]=function(){
		    $("#text_saveBtn").linkbutton("disable").unbind("click");
				var validated= false;
				if($('#'+dataGridId).data('lastIndex')){
					var editRowsIndex=$('#'+dataGridId).datagrid('getEditRowsIndex');
					$.each(editRowsIndex,function(){
						if(!$("#"+dataGridId).datagrid('validateRow',this)){
							validated=true;
						}
					});
				}
				if(validated){
					$("#text_saveBtn").linkbutton("enable").unbind("click").bind("click");
					alert("验证失败:请正确填写所输入的数据");
					return;
				}
				//若结束所有编辑
				$("#"+dataGridId).datagrid('endAllEdits');
				//$("#"+dataGridId).datagrid('endEdit',$('#'+dataGridId).data('lastIndex'));	
				var selectedRow = $("#"+dataGridId).datagrid('getSelected');
				var data = $("#"+dataGridId).datagrid('getData');
				var updateRows = $("#"+dataGridId).datagrid('getChanges','updated');  //得到修改的数据
				var deleteRows = $("#"+dataGridId).datagrid('getChanges','deleted');  //得到删除的数据
				var insertRows = $("#"+dataGridId).datagrid('getChanges','inserted'); //得到新增的数据

				//保存修改的数据 
				if (updateRows.length>0) {
					for ( var i = 0; i < updateRows.length; i++) {
						var row = updateRows[i];
						updateRows[i]._class=undefined;
						updateRows[i].createTime=undefined;
						updateRows[i].modifyTime=undefined;
						updateRows[i].rowState="Modified";
						if(notNullFieldsObj!=undefined){
							$.extend(updateRows[i],notNullFieldsObj);
						}
						tempArray.push(updateRows[i]);
						
					}
				}
		
				//保存删除的数据
				if (deleteRows.length>0) {
						for ( var j = 0; j < deleteRows.length; j++) {
							deleteRows[j]._class=undefined;
							deleteRows[j].createTime=undefined;
							deleteRows[j].modifyTime=undefined;
							deleteRows[j].rowState="Deleted";
							if(notNullFieldsObj!=undefined){
								$.extend(deleteRows[j],notNullFieldsObj);
							}
							tempArray.push(deleteRows[j]);
						}
				}
		
				//保存新增的数据
				$("#"+dataGridId).datagrid('acceptChanges');
				$.each(insertRows,function(k,v){
				if(v[p_modelUuId]==undefined){ //新增
					v._class=undefined;
					v.createTime=undefined;
					v.modifyTime=undefined;
					v.rowState="Added";
					//必输字段的控制,约定优于配置,基础数据的所有必输字段都排在第2位,所以用fields[1]来取得
					var fields=$("#"+dataGridId).datagrid('getColumnFields');
					var needField=fields[3];
					if(v[needField]!=undefined){
						if(notNullFieldsObj!=undefined){
							$.extend(v,notNullFieldsObj);
						}
						tempArray.push(v);
					}
				}
			}); 
				
			var jsonResult= $.toJSON(tempArray);
			if(tempArray.length<=0){
				$("#text_saveBtn").linkbutton("enable").unbind("click").bind("click");
				return;
			}
			//使用ajax提交到服务器保存数据
			LogisticsOrderManager.object2base64(jsonResult,function(data){
			if(data){
						$.ajax({
						type: "POST",
					    url: url,
					    data:"jsonResult="+data+"",
					    success: function(result){
					           	$("#text_saveBtn").linkbutton("enable").unbind("click").bind("click");
					    		var rs = $.parseJSON(result);
					   	 		if(rs&&rs.error){
					    			alert('执行保存时发生错误:'+rs.error);
					    			$("#"+dataGridId).datagrid('rejectChanges');
					    			return;
								}
						    	var model = p_modelUuId.split('Uuid');
						   		model=model[0].substring(0,1).toUpperCase()+model[0].substring(1)+"Model";
						   		v_global_exitsCodes=[];
						   		if(fn_validate_isUnique){
						   			fn_validate_isUnique(model);
						   		}
						    	if(tempArray.length>0){
						    		alert('保存成功');
						    		doSthAfterSave();
						    	}
						    	tempArray=[];//清空数组
						    	$("#"+dataGridId).datagrid({});
						    	$("#"+dataGridId).datagrid("clearSelections");
							},
						error:function (XMLHttpRequest, textStatus, errorThrown){
							$("#text_saveBtn").linkbutton("enable").unbind("click").bind("click");
							alert("Error:"+XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
						}
					  });
					}
				});
			

		};//end of fn_toolbar_saveBtn
		
		//选中触发的事件
		this["fn_clickEvent"]=function(rowIndex,rowData){
			var rows=$("#"+dataGridId).datagrid('getSelections');
			//$('#'+dataGridId).data('lastIndex',rowIndex);
			//若选中的数据有已生效的记录,则禁用删除按钮
			var isActive=false;
			$.each(rows,function(i,v){
				if(this.status=="Active"||this.status=="Cancel"){
					isActive=true;
					return;
				}
			});
			
			if(isActive==true){
				$("#gridContainer a").has($(".myCustomerIcon_remove")).linkbutton('disable');
				return;
			} 
			$("#gridContainer a").has($(".myCustomerIcon_remove")).linkbutton('enable');
		};
		
		//双击触发的事件
		this["fn_dbClickEvent"]=function(rowIndex,rowData){
			//先验证下正在编辑的数据
			var vilidate = true;
			var lastIndex = $('#'+dataGridId).data('lastIndex');
			if(lastIndex>=0){
				vilidate=$("#"+dataGridId).datagrid('validateRow',lastIndex);
				if(!vilidate){
					alert("验证失败：请先正确填写正在编辑的数据");
					return;
				}
			}
			
			
			$("#"+dataGridId).datagrid('endEdit', $('#'+dataGridId).data('lastIndex'));
			$("#"+dataGridId).datagrid('beginEdit', rowIndex);
			fn_datagrid_clearEditor(dataGridId,rowData,rowIndex);
			$("#"+dataGridId).datagrid('clearSelections');
			$('#'+dataGridId).data('lastIndex',rowIndex);
			$("#"+dataGridId).datagrid('selectRow', rowIndex);
			editCell(dataGridId,rowIndex);
		};
		
	}
	
	function EasyUICRUD(easyUIFunc){
		//将页面上的HTML表格转换为columns数组对象
		function htmltoColumns(_opt) {
				var _rs = [];
				$("tr", _opt).each(function() {
					var _a = [];
					$("th", this).each(function() {
								var th = $(this);
								var col = {
									title : th.html(),
									align : th.attr("align") || "left",
									sortable : th.attr("sortable") == "true" || false,
									checkbox : th.attr("checkbox") == "true" || false
								};
								if (th.attr("field")) {
									col.field = th.attr("field");
								}
								if (th.attr("formatter")) {
									col.formatter = eval(th.attr("formatter"));
								}
								if (th.attr("styler")) {
									col.styler = eval(th.attr("styler"));
								}
								if (th.attr("editor")) {
									var s = $.trim(th.attr("editor"));
									if (s.substr(0, 1) == "{") {
										col.editor = eval("(" + s + ")");
									} else {
										col.editor = s;
									}
								}
								if (th.attr("rowspan")) {
									col.rowspan = parseInt(th.attr("rowspan"));
								}
								if (th.attr("colspan")) {
									col.colspan = parseInt(th.attr("colspan"));
								}
								if (th.attr("width")) {
									col.width = parseInt(th.attr("width"));
								}
								if (th.attr("hidden")) {
									col.hidden = true;
								}
								if (th.attr("resizable")) {
									col.resizable = th.attr("resizable") == "true";
								}
								_a.push(col);
							});
					_rs.push(_a);
				});
			return _rs;
		};
		var _arrays;
		if(arguments[1].columns){
			_arrays=arguments[1].columns;
		}else{
			_arrays = htmltoColumns($('#'+dataGridId));
			//插入公共列,checkbox,并按照createTime降序排列
			_arrays=[$.merge(
						$.merge([{field:'ck',checkbox:true}],_arrays[0]),
						[{field:'createTime',title:'生成时间',width:100,align:'right',sortable:true,hidden:true}])
					];
		}
		//auto weight
		//parseInt($(document.body).css("height"))-parseInt($(".myCustomerIcon_searchForm").parent().parent().css("height"))
		var options = {
			pagination:true,
			idField:arguments[1].modelUuId,
			singleSelect: false,  // 单选或多选
			sortName:'createTime',
			sortOrder:'desc',
			height:335,
			toolbar:[{
				text:text_newBtn,
				iconCls:'icon-add',
				handler:easyUIFunc["fn_newBtn"]
			},'-',{
				text:text_delBtn,
				iconCls:'myCustomerIcon_remove',
				handler:easyUIFunc["fn_delBtn"]
			},'-',{
				text:text_saveBtn,
				iconCls:'icon-save',
				id:'text_saveBtn',
				handler:easyUIFunc["fn_saveBtn"]
			},'-',{
				text:'撤销',
				iconCls:'icon-undo',
				handler:easyUIFunc["fn_returnBtn"]
			},'-',{
				text:text_validateBtn,
				iconCls:'myCustomerIcon_validate',
				handler:easyUIFunc["fn_validateBtn"]
			},'-',{
				text:text_cancelBtn,
				iconCls:'myCustomerIcon_cancel',
				handler:easyUIFunc["fn_cancelBtn"]
			},'-',{
				text:text_importBtn,
				iconCls:'excel_import',
				handler:easyUIFunc["fn_importBtn"]
			},'-',{
				text:text_exportBtn,
				iconCls:'excel_export',
				handler:easyUIFunc["fn_exportBtn"]
			}],
			columns:_arrays,
			onSelect:easyUIFunc["fn_clickEvent"],
			onDblClickRow:easyUIFunc["fn_dbClickEvent"],
			onUnselect:function(rowIndex,rowData){
				var rows=$("#"+dataGridId).datagrid('getAllSelected');
				if(rows.length==0){
					$("#gridContainer a").has($(".myCustomerIcon_remove")).linkbutton('enable');
				}
				//$('#'+dataGridId).data('lastIndex',rowIndex);
				//若选中的数据有已生效或者已作废的记录,则禁用删除按钮
				var isActive=false;
				$.each(rows,function(i,v){
					if(this.status=="Active"||this.status=="Cancel"){
						isActive=true;
						return false;
					}
				});
				
				if(isActive){
					$("#gridContainer a").has($(".myCustomerIcon_remove")).linkbutton('disable');
					return;
				} 
				$("#gridContainer a").has($(".myCustomerIcon_remove")).linkbutton('enable');
			},
			onLoadSuccess:function(){
				$("#"+dataGridId).datagrid('clearSelections');
				//禁用导入导出按钮
				$("#gridContainer a").has($(".excel_import")).linkbutton('disable');
				$("#gridContainer a").has($(".excel_export")).linkbutton('disable');
			},
			fitColumns: false,
			nowrap:false,
			rownumbers:true,
			showFooter:true,
			remoteSort:false,
			pageSize:10, //每页显示记录数
			pageList:[10,20,30], //可调整每页显示的记录数
			striped:true	//斑马线
		};
		
		//定义参数拦截器
		if(arguments[1].interceptor!=undefined){
			this.optionsInterceptor=arguments[1].interceptor;
			this.optionsInterceptor(options,easyUIFunc);
		}
		$("#"+dataGridId).datagrid(options);
	}
	
	
	EasyUICRUD(new EasyUIFunc(p_girdId,p_request_url),p_options);//该类指定了datagrid数据表格的相关定义
 	
}
/********************************End 旧版本代码****************************************************/


/********************************Begin 全局函数***********************************************************************/
/*
 * 公用的实用代码
 */
//设置某一列的editor为不能编辑
function fn_datagrid_clearEditor(dataGridId,row,rowIndex){
	var _i = 2;
	if($('#'+dataGridId).data('uniqueColumnsIndex')){
		_i=$('#'+dataGridId).data('uniqueColumnsIndex');
	}
	var fields=$("#"+dataGridId).datagrid('getColumnFields',false);
	var currentModifyRow=$(' .datagrid-view2 tr').filter('.datagrid-row-selected').filter('.datagrid-row-editing');
	$("tr[datagrid-row-index="+rowIndex+"] td[field="+fields[_i]+"] input",$(".datagrid-view2 .datagrid-body")).removeClass("validatebox-text validatebox-invalid").hide();
	var text=$("td[field="+fields[_i]+"] div",$(".datagrid-view2 .datagrid-body")).get(rowIndex);
	$("td",text).append(row[fields[_i]]);
}


//返回搜索表单的字段对象,并在字段前加 __
function fn_jsUtil_getSearchFormObj(formId,notNullFieldsObj){
	var dgID = $.data(document.body,'_datagridID'+($.datagridNo-1));
	$('#'+dgID).datagrid('clearSelections');
	var exp=formId+' input,#'+formId+' select';
	var $allInput = $('#'+exp);
	var searchTableObj = {};
	if(notNullFieldsObj!=undefined){
		$.extend(searchTableObj,notNullFieldsObj);
	}
	
	for(var i=0;i<$allInput.size();i=i+1){
		searchTableObj["__"+$allInput[i].name]=$("#"+$allInput[i].name).val();
		//添加模糊查询支持
		if(i==0){
			searchTableObj.__condition=$allInput[i].name+"_ilikeAnywhere";
		}else{
			searchTableObj.__condition+=":"+$allInput[i].name+"_ilikeAnywhere";
		}
	}
	searchTableObj.__status=$('#status').combobox("getValue");
	return  searchTableObj;
}

//设置查询表单的样式
	$(function(){
		
		$("#queryForm").css({
				width:"99.6%",
				heigh:"99%",
				margin:"2px 1px 2px 2px"
			});
			
		$("#queryForm td:even").css({
				width:"9%",
				border:"1px,soild,red"
			});
			
		$(".textStyle").css({
			"font-size": "12px",
			"text-align":"right"
		});
		
		$("#queryForm td:odd:even").css({
			width:"20%"
		});
		
		$('#formContainer').attr('style','overflow:hidden');
		$('.easyui-panel').attr('style','overflow:hidden');
	});
	
	//格式化第一列
	function fn_global_setBasicdataState(v,r,i){
		if(v=='Pending') return '草稿';
		if(v=='Active') return '生效';
		if(v=='Cancel') return '作废';
		if(v=='undefined') return '';
		return v;
	}
	
//datagrid中回车键代替tab
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
							$('#'+target).datagrid('endEdit', index);
							 $('#'+target).datagrid('refreshRow', index);
						} 
					});
				}
				
			}
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

//设置查询条件的状态下拉框
$(function(){
	$('#status').combobox({
    data:[
    	{
		    "v":"",
		    "text":"全部"
		},
    	{
		    "v":"Active",
		    "text":"生效"
		},{
		    "v":"Pending",
		    "text":"草稿"
		},{
		    "v":"Cancel",
		    "text":"作废"
		}
	],
    valueField:'v',
    textField:'text',
    panelHeight:95,
    panelHeight:"auto",
    editable:false
	});

});
//光标定位
$(function(){
	var vInputs=$("#queryForm input:visible");
	$.each(vInputs,function(i,v){
		if(i==0){$(this).focus()}
		$(this).keydown(function(even){
			if(even.keyCode==13){
				if($(vInputs)[i+1]){
					$($(vInputs)[i+1]).focus();
				}
			}
		});
	});	
});
/**********************************End 全局函数*********************************************************************/

/**********************************Begin 代码重构*********************************************************************/

/*
 * 重构的代码
 */
(function($){
//备份默认的datagrid default设置
$._cache={};
$._cache.easyui_datagrid_defaultOptions = $.extend({},$.fn.datagrid.defaults);
$.extend($.fn.datagrid.methods,{
	clearDefaultEvents:function(jq){
		$.fn.datagrid.defaults = $.extend({},$.fn.datagrid.defaults,{
				onBeforeLoad : function() {	},
				onLoadSuccess : function() {},
				onLoadError : function() {},
				onClickRow : function() {},
				onDblClickRow : function() {},
				onClickCell : function() {},
				onDblClickCell : function() {},
				onSortColumn : function() {},
				onResizeColumn : function( ) {},
				onSelect : function(){},
				onUnselect : function() {},
				onSelectAll : function() {},
				onUnselectAll : function() {},
				onBeforeEdit : function() {},
				onAfterEdit : function() {},
				onCancelEdit : function() {},
				onHeaderContextMenu : function() {},
				onRowContextMenu : function() {}
		});
	},
	setOptionsDefault:function(jq){
		if($._cache.easyui_datagrid_defaultOptions){
			$.fn.datagrid.defaults=$.cache.easyui_datagrid_defaultOptions;
		}else{
			jq.datagrid("clearDefaultEvents");
		}
	}
});
$.fn.datagrid.defaults = $.extend({},$.fn.datagrid.defaults,{
			canOnlyAdd:[2],//设置某些列的编辑器只在新增的时候有效,也就是设置该列不可编辑
			sortName:'createTime',
			sortOrder:'asc',
			height:365,
			rownumbers:true,
			pagination: true,
			pageNumber: 1,
			pageSize:20, //每页显示记录数
			pageList:[10,20,30], //可调整每页显示的记录数
			commonColumns:{
				before:[{field:'ck',checkbox:true}],//公共列,排在最前面
				behind:[
					{field:'createTime',title:'生成时间',width:100,align:'right',sortable:true,hidden:true}
				] //公共列,排在最后面
			},
			toolbar:[{
					text:"新增",
					iconCls:'icon-add',
					handler:function(){
						fn_toolbar_newBtn.call(this);
					}
				},'-',{
					text:"删除",
					iconCls:'myCustomerIcon_remove',
					handler:function(){
						fn_toolbar_delBtn.call(this);
					}
				},'-',{
					text:"保存",
					iconCls:'icon-save',
					handler:function(){
						fn_toolbar_saveBtn.call(this);
					}
				},'-',{
					text:'撤销',
					iconCls:'icon-undo',
					handler:function(){
						fn_toolbar_undoBtn.call(this);
					}
				},'-',{
					text:"生效",
					iconCls:'myCustomerIcon_validate',
					handler:function(){
						fn_toolbar_validateBtn.call(this);
					}
				},'-',{
					text:"作废",
					iconCls:'myCustomerIcon_cancel',
					handler:function(){
						fn_toolbar_cancelBtn.call(this);
					}
				},'-',{
					text:"导入",
					iconCls:'excel_import',
					handler:function(){
						fn_toolbar_importBtn.call(this);
					}
				},'-',{
					text:"导出",
					iconCls:'excel_export',
					handler:function(){
						fn_toolbar_exportBtn.call(this);
					}
				}],
			onClickRow:function(rowIndex,rowData){
			},
			onSelect:function(rowIndex,rowData){
				fn_event_onSelect.call(this,rowIndex,rowData);
			},
			onDblClickRow:function(rowIndex,rowData){
				fn_event_onDbClick.call(this,rowIndex,rowData);
			},
			onUnselect:function(rowIndex,rowData){
				var jq=$(this);
				var rows=jq.datagrid('getAllSelected');
				if(rows.length==0){
					$("#gridContainer a").has($(".myCustomerIcon_remove")).linkbutton('enable');
				}
				jq.data('lastIndex',rowIndex);
				//若选中的数据有已生效或者已作废的记录,则禁用删除按钮
				var isActive=false;
				$.each(rows,function(i,v){
					if(this.status=="Active"||this.status=="Cancel"){
						isActive=true;
						return false;
					}
				});
				
				if(isActive){
					$("#gridContainer a").has($(".myCustomerIcon_remove")).linkbutton('disable');
					return;
				} 
				$("#gridContainer a").has($(".myCustomerIcon_remove")).linkbutton('enable');
			},
			onLoadSuccess:function(){
				//禁用导入导出按钮
				$("#gridContainer a").has($(".excel_import")).linkbutton('disable');
				$("#gridContainer a").has($(".excel_export")).linkbutton('disable');
			}
		});
		
	//选中记录触发的事件
	function fn_event_onSelect(rowIndex,rowData){
			var rows=$(this).datagrid('getSelections');
			$(this).data('lastIndex',rowIndex);
			//若选中的数据有已生效的记录,则禁用删除按钮
			var isActive=false;
			$.each(rows,function(i,v){
				if(this.status=="Active"||this.status=="Cancel"){
					isActive=true;
					return;
				}
			});
			
			if(isActive==true){
				$("#gridContainer a").has($(".myCustomerIcon_remove")).linkbutton('disable');
				return;
			} 
			$("#gridContainer a").has($(".myCustomerIcon_remove")).linkbutton('enable');
		}
	//双击记录出发的事件
	function fn_event_onDbClick(rowIndex,rowData){
		var jq = $(this);
		jq.datagrid('beginEdit', rowIndex);
		//删除指定列的编辑器  ---->>>代码待改进
		if(jq.datagrid("options").canOnlyAdd){
			$.each(jq.datagrid("options").canOnlyAdd,function(i,v){
				var fields=jq.datagrid('getColumnFields',false);
				var currentModifyRow=$(' .datagrid-view2 tr').filter('.datagrid-row-selected').filter('.datagrid-row-editing');
				$("tr[datagrid-row-index="+rowIndex+"] td[field="+fields[v]+"] input",$(".datagrid-view2 .datagrid-body")).removeClass("validatebox-text validatebox-invalid").hide();
				var text=$("td[field="+fields[v]+"] div",$(".datagrid-view2 .datagrid-body")).get(rowIndex);
				$("td",text).append(rowData[fields[v]]);		
			});
		}
		editCell(jq.attr("id"),rowIndex);
	}
		
	//新增按钮
	function fn_toolbar_newBtn(){
		var jq  = $($(this).parent().next().children("table:hidden"));
		jq.datagrid('insertRow',{
			row:{status:'Pending'}
		});
		var currentIndex = jq.datagrid('getRows').length-1;
		jq.datagrid('selectRow',currentIndex);
		jq.datagrid('beginEdit',currentIndex);
		editCell(jq.attr("id"),currentIndex);	//耦合点,依赖于全局函数editCell,待解耦
		return;
	}
	//删除按钮
	function fn_toolbar_delBtn(){
			var target  = $(this).parent().next().children("table:hidden");
			var jq = $(target);
			//生效的时候不允许删除
			var _rows=jq.datagrid('getAllSelected');
			var _isActive=false;
			$.each(_rows,function(){
				if(this.status=="Active"||this.status=="Cancel"){
					_isActive=true;
					return false;
				}
			});
			if(_isActive){
				alert('已生效或已作废的记录不能够删除!');
				return;
			}
			if (_rows.length>0){
				$.messager.confirm('确定框', '确定删除'+_rows.length+'条记录吗?', function(r){
						if (r){
							//缓冲删除的数据
							if (_rows.length>0) {
									for ( var j = 0; j < _rows.length; j++) {
										_rows[j]._class=undefined;
										_rows[j].createTime=undefined;
										_rows[j].modifyTime=undefined;
										_rows[j].rowState="Deleted";
										jq.data("datagrid").deletedRows.push(_rows[j]);
									}
							}
							var jsonResult= $.toJSON(jq.data("datagrid").deletedRows);
							//使用ajax提交到服务器保存数据
							$.ajax({
								type: "POST",
							    url: jq.data("datagrid_saveUrl"),
							    data:"jsonResult="+jsonResult+"",
							    success: function(result){
							    		var rs = $.parseJSON(result);
							   	 		if(rs&&rs.error){
							    			alert('执行删除时发生错误:'+rs.error);
							    			jq.datagrid('rejectChanges');
							    			return;
			    						}
								    	if(jq.data("datagrid").deletedRows.length>0){
								    		alert('删除成功');
								    		//执行删除
											$(jq.data("datagrid").deletedRows).each(function(){
												jq.datagrid('deleteRow',jq.datagrid('getRowIndex',this));
											});
								    	}
								    	jq.data("datagrid").deletedRows=[];//清空数组
								    	jq.datagrid({});
								    	jq.datagrid("clearSelections");
									},
								error:function (XMLHttpRequest, textStatus, errorThrown){
									alert("Error:"+XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
								}
							});
							
						}
				});
			}else{
				 alert("请选择所要删除的行！");
			}
		}
	//保存按钮
	function fn_toolbar_saveBtn(){
		var target  = $(this).parent().next().children("table:hidden");
		var jq = $(target);
		var validated= false;
		if(jq.data('lastIndex')){
			var editRowsIndex=jq.datagrid('getEditRowsIndex');
			$.each(editRowsIndex,function(){
				if(!jq.datagrid('validateRow',this)){
					validated=true;
				}
			});
		}
		if(validated){
			alert("验证失败:请输入必输项!");
			return;
		}
		//结束所有编辑
		jq.datagrid('endAllEdits');
		var updateRows = jq.datagrid("getChanges","updated");//得到修改数据的副本
		var insertRows=jq.datagrid("getChanges","inserted"); //得到新增数据的副本
		//修改updateRows
		$.each(updateRows,function(){
			this._class=undefined;
			this.createTime=undefined;
			this.modifyTime=undefined;
			this.rowState="Modified";
		});
		
		//修改insertRows
		jq.datagrid('acceptChanges');
		$.each(insertRows,function(k,v){
			v._class=undefined;
			v.createTime=undefined;
			v.modifyTime=undefined;
			v.rowState="Added";
		});
		var temp = [];
		if(insertRows.length>0){
			$.merge(temp,insertRows);
		}
		if(updateRows.length>0){
			$.merge(temp,updateRows);
		}
		var jsonResult= $.toJSON(temp);
		//使用ajax提交到服务器保存数据
		$.ajax({
			type: "POST",
		    url: jq.data("datagrid_saveUrl"),
		    data:"jsonResult="+jsonResult+"",
		    success: function(result){
		    		var rs = $.parseJSON(result);
		   	 		if(rs&&rs.error){
		    			alert('执行保存时发生错误:'+rs.error);
		    			jq.datagrid('rejectChanges');
		    			return;
					}else{
						alert("保存成功");
					}
			    	jq.datagrid("acceptChanges");
			    	jq.datagrid({});
			    	jq.datagrid("clearSelections");
				},
			error:function (XMLHttpRequest, textStatus, errorThrown){
				alert("Error:"+XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
			}
		});
	}
	//撤销按钮
	function fn_toolbar_undoBtn(){
		var target  = $(this).parent().next().children("table:hidden");
		$(target).datagrid("rejectChanges");
		$(target).datagrid("clearSelections");
	}
	//生效按钮
	function fn_toolbar_validateBtn(){
			var target  = $(this).parent().next().children("table:hidden");
			var jq = $(target);
			//验证
			var validated= false;
			if(jq.datagrid('getEditRowsIndex').length>0){
				var editRowsIndex=jq.datagrid('getEditRowsIndex');
				$.each(editRowsIndex,function(){
					if(!jq.datagrid('validateRow',this)){
						validated=true;
					}
				});
			}
			if(validated){
				alert("验证失败:请输入必输项!");
				return;
			}
				
			jq.datagrid('endAllEdits');
			var rows = jq.datagrid('getAllSelected');
			if(rows.length==0){
				alert("请至少选择一条记录!");
				return;
			}
			var flag=true;
			var flag1=false;
			$.each(rows,function(){
					if(this.status!="Active"){
						flag=false;
					}
					if(this.status=="Cancel"){
						alert("作废的记录不能够再生效");
						flag1=true;
						return false;
					}
				});
			if(flag){
				alert("所选记录都已生效");
				return;
			}
			if(flag1){
				return;
			}
			if(rows.length>0){
				$.messager.confirm('确定框', '确定生效'+rows.length+'条记录吗?', function(r){
					if(r){
						var temp=[];
						$.each(rows,function(i,v){
							//生效操作,设置状态为生效
							this.status='Active';
							var selectedRow = this;
							var index = jq.datagrid('getRowIndex',this);
							if(selectedRow._class){
								selectedRow.rowState="Modified";
							}else{
								selectedRow.rowState="Added";
							}
							selectedRow._class=undefined;
							selectedRow.createTime=undefined;
							selectedRow.modifyTime=undefined;
							temp.push(selectedRow);
							jq.datagrid('refreshRow',index);
						});
						//使用ajax提交到服务器保存数据
						$.ajax({
							type: "POST",
						    url: jq.data("datagrid_saveUrl"),
						    data:"jsonResult="+$.toJSON(temp)+"",
						    success: function(result){
						    		var rs = $.parseJSON(result);
						   	 		if(rs&&rs.error){
						    			alert('执行生效时发生错误:'+rs.error);
						    			jq.datagrid('rejectChanges');
						    			return;
		    						}else{
		    							alert('生效成功');
		    						}
							    	jq.datagrid("acceptChanges");
							    	jq.datagrid("reload");
							    	jq.datagrid("clearSelections");
								}
						});
					}
				});
			}else{
				alert("请选择要生效的记录！");			
			}
			return;
		}
	//作废按钮
	function fn_toolbar_cancelBtn(){
			var target  = $(this).parent().next().children("table:hidden");
			var jq = $(target);
			if(jq.datagrid('getEditRowsIndex').length>0){
				alert("保存后才能进行作废操作!");
				return;
			}
			var validated=false;
			if(jq.datagrid('getEditRowsIndex').length>0){
				var editRowsIndex=jq.datagrid('getEditRowsIndex');
				$.each(editRowsIndex,function(){
					if(!jq.datagrid('validateRow',this)){
						validated=true;
					}
				});
			}
			if(validated){
				alert("验证失败:请输入必输项!");
				return;
			}
			jq.datagrid('endAllEdits');
			var rows = jq.datagrid('getAllSelected');
			if(rows.length==0){
				alert("请至少选择一条记录!");
				return;
			}
			var flag;
			if(rows.length==1){
				if(rows[0].status=="Cancel"){
					alert("该记录已作废!");
					flag = true; 
					return;
				}
			}
			
			if(rows.length>0){
				var flag=false;
				$.each(rows,function(){
					if(this.status=="Pending"){
						flag=true;
					}
				});
				if(flag){
					alert("生效的记录才能作废!");
					return;
				}
				$.messager.confirm('确定框', '确定作废这'+rows.length+'条记录吗?', function(r){
					if(r){
						var temp = [];
						$.each(rows,function(i,v){
							//作废操作,设置状态为作废
							this.status='Cancel';
							var selectedRow = this;
							var index = jq.datagrid('getRowIndex',this);
							selectedRow._class=undefined;
							selectedRow.createTime=undefined;
							selectedRow.modifyTime=undefined;
							selectedRow.rowState="Modified";
							temp.push(selectedRow);
							jq.datagrid('refreshRow',index);
						});
						//使用ajax提交到服务器保存数据
						$.ajax({
							type: "POST",
						    url: jq.data("datagrid_saveUrl"),
						    data:"jsonResult="+$.toJSON(temp)+"",
						    success: function(result){
						    		var rs = $.parseJSON(result);
						   	 		if(rs&&rs.error){
						    			alert('执行作废时发生错误:'+rs.error);
						    			jq.datagrid('rejectChanges');
						    			return;
		    						}else{
		    							alert('作废成功');
		    						}
		    						jq.datagrid("acceptChanges");
							    	jq.datagrid("reload");
							    	jq.datagrid("clearSelections");
								}
						});
					}
				});
			}else{
				alert("请选择要作废的记录！");			
			}
			return;
		}
	//导入按钮
	function fn_toolbar_importBtn(){
		var target  = $(this).parent().next().children("table:hidden");
	}
	//导出按钮
	function fn_toolbar_exportBtn(){
		var target  = $(this).parent().next().children("table:hidden");
	}

})(jQuery)
