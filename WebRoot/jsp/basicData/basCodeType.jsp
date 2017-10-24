<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page
	import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<%
	String path = request.getContextPath();
	String officeCode = SessionContextUserEntity.currentUser()
							.getOfficeCode();
%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html:html>
  <head>
    <title>数据字典定义</title>
    <jsp:include page="../../common/imp_dwr.jsp"></jsp:include>     
	<!-- jquery easyui -->
	<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
	<link rel="stylesheet" href="../../css/system.css" type="text/css"></link>
	<script type="text/javascript" src="../../js/util/wlp_baseCRUD.js"></script>
	<style type="text/css">
		body {
			width: 100%;
			height: 100%;
			background: white;
		}
	</style>
	<script type="text/javascript">
		//按钮的国际化字段定义
		var text_newBtn='<bean:message bundle="wlp.common" key="global.add"/>';
		var text_delBtn='<bean:message bundle="wlp.common" key="global.del"/>';
		var text_saveBtn='<bean:message bundle="wlp.common" key="global.save"/>';
		var text_returnBtn='<bean:message bundle="wlp.common" key="global.undo"/>';
		var text_validateBtn='<bean:message bundle="wlp.common" key="global.active"/>';
		var text_cancelBtn='<bean:message bundle="wlp.common" key="global.invalidate"/>';
		var text_importBtn='<bean:message bundle="wlp.common" key="global.excelImport"/>';
		var text_exportBtn='<bean:message bundle="wlp.common" key="global.excelExport"/>';
		//与model相关的字段定义
		var p_modelUuId="basCodeDefUuid";
		//唯一性校验的全局依赖字段和函数定义
		var v_global_exitsCodes=new Array();
		function fn_validate_isUnique(modelName){
			$.ajax({
              type: "POST",
              url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&oc=true&queryType="+modelName+"&rows="+2000+"",
              dataType:"json",
              success: function(result){
              	  $(result.rows).each(function(){
              	  	v_global_exitsCodes.push(this.codeValue);
              	  });
              }
          	});
		}
		//初始化
		fn_validate_isUnique("BasCodeDefModel");
	</script>
	<script type="text/javascript">
	function setBasicdataState(v,r,i){
		if(v=='Pending') return '草稿';
		if(v=='Active') return '生效';
		if(v=='Cancel') return '作废';
		if(v=='undefined') return '';
		return v;
	}
	//数据词典类型datagrid
	$(function(){
		$('#codeType_datagird').datagrid({
			pagination:true,
			idField:'basCodeTypeUuid',
			singleSelect: true,  // 单选或多选
			height:335,
			onClickRow:function(rowIndex,rowData){
				notNullFieldsObj.basCodeTypeUuid=rowData.basCodeTypeUuid;
				$('#codeDef_datagird').removeData('_defaultRowIndex');
				$("#codeDef_datagird").datagrid("load",{
					__basCodeTypeUuid:rowData.basCodeTypeUuid,
					__status:$('#status').combobox('getValue'),
					__modifiable:$('#modifiable').combobox('getValue')
				});
				$('#codeDef_datagird').data('_basCodeTypeUuid',rowData.basCodeTypeUuid);
				$("#codeDef_datagird").datagrid('clearSelections');
				//更新_defaultRow
				updateDefaultRowObj();
			},
			onLoadSuccess:function(){
				$('#codeType_datagird').datagrid("clearSelections");
				var rows=$('#codeType_datagird').datagrid('getData').rows;
				if(rows!=null&&rows.length>0){
					var fieldObj={
						//__codeValue:$('#codeValue').val(),
						__status:$('#status').combobox('getValue'),
						__basCodeTypeUuid:rows[0].basCodeTypeUuid,
						__modifiable:$('#modifiable').combobox('getValue')//,
						//filter:'codeValue',
						//q:$('#codeValue').val()
					};
					$("#codeDef_datagird").datagrid('load',fieldObj);
				}else{
					$("#codeDef_datagird").datagrid('loadData',{
						rows : []
					});
				}
			},
			toolbar:null,
			columns:[[
				{field:'typeCode',title:'类型编码',width:150,align:"center",td_align:"left"},
				{field:'typeName',title:'类型名称',width:160,align:"center",td_align:"left"},
				{field:'status',title:'状态',width:40,align:"center",td_align:"left",formatter:fn_global_setBasicdataState},
				{field:'controlWord', title:'类型', width:40, align:"center", td_aglin:"center", formatter:show_style}
			]],
			fitColumns: false,
			nowrap:false,
			rownumbers:true,
			showFooter:true,
			pageSize:10, //每页显示记录数
			pageList:[5,10], //可调整每页显示的记录数
			striped:true,	//斑马线
			sortName:"createTime",
			sortOrder:"asc"
		});
	});

	
	// 查询按钮和清空按钮的click事件
	$(function(){
		
		$('#searchBtn').click(function(){ 
			var fieldObj={
				typeName:$('#typeName').val(),
				status:$('#status').combobox('getValue'),
				typeCode:$('#typeCode').val(),
				modifiable:$('#modifiable').combobox('getValue'),
				officeCode:$('#officeCode').val()
			};
            $("#codeType_datagird").datagrid('load',fieldObj);
    	});

    	$('#resetButton').click(function(){
        	$("#typeName").val("");
        	$("#typeCode").val("");
        	$("#status").combobox("setValue","");
        	$("#modifiable").combobox("setValue","");
        });
	});
	
	
	//类型
	function show_style(v,r,i){
	    var controlWord = v.substring(0,1);
	 	if(controlWord=="U"){
	 		return "用户";
	 	}
	 	if(controlWord=="0"){
	 		return "系统";
	 	}
	}
	
	</script>	
	<script type="text/javascript">
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
			"font-size": "13px",
			"text-align":"right"
		});
		$("#queryForm td:odd").css({
			
		});

		$(".layout-button-left").remove();
	});


	//更新全局默认row对象的函数
	function updateDefaultRowObj(){
		var url="<%=request.getContextPath()%>"
			+"/servlet/SinotransServlet?cmd=getDefaultCodeDefRowObj"
			+"&basCodeTypeUuid="+$('#codeDef_datagird').data('_basCodeTypeUuid');
		$.ajax({
					type: "POST",
		            url: url,
		            dataType:"json",
		            success: function(result){
            			if(result){
							$('#codeDef_datagird').data('_defaultRow',result[0]);
                		}else{
                			$('#codeDef_datagird').data('_defaultRow',null);
                    	}
		            }
         });
	}

	function setDefaultView(v,r,i){
		if(v){
			var w = v.substring(0,1);
			if(w=='1'){
				$('#codeDef_datagird').data('_defaultRowIndex',i);
				return '默认';
			}
			if(w=='0') return '';
		}else{
			return '';
		}
		
	}

	</script>
	
	<script type="text/javascript">
	//设置数据字典为当前类型下的默认
	function fn_setCodeDefDefault(){
		if($('#codeType_datagird').datagrid('getSelections').length>0){
			var currentRow = $('#codeDef_datagird').datagrid('getSelected');
			if(currentRow){
				if(!Boolean(currentRow.basCodeDefUuid)){
					alert("请先保存!");
					return;
				}
				if(currentRow.status == "Cancel") {
					$.messager.alert("系统提示","作废的字典定义不能设为默认！");	
					return;
				};
				
				
				$.messager.confirm('确定框', '确定设置这条记录为默认吗?',function(r){
					if(r){
						var temp = [];
						var _dRow = $('#codeDef_datagird').data('_defaultRow');
						if(_dRow){
							_dRow.controlWord = '0'+_dRow.controlWord.substring(1);
							_dRow._class=undefined;
							_dRow.createTime=undefined;
							_dRow.modifyTime=undefined;
							_dRow.rowState="Modified";
							temp.push(_dRow);
						}
						currentRow.controlWord='1'+currentRow.controlWord.substring(1);
						var rowIndex=$('#codeDef_datagird').datagrid('getRowIndex',currentRow);
						$('#codeDef_datagird').datagrid('refreshRow',rowIndex);

						currentRow._class=undefined;
						currentRow.createTime=undefined;
						currentRow.modifyTime=undefined;
						currentRow.rowState="Modified";
						temp.push(currentRow);
						var jsonResult = $.toJSON(temp);
						LogisticsOrderManager.object2base64(jsonResult,function(data){
						if(data){						
								//使用ajax提交到服务器保存数据
								$.ajax({
									type: "POST",
								    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=saveBasCodeDef",
								    data:"jsonResult="+data+"",
								    success: function(result){
									    	if(tempArray1.length>0){
									    		alert('保存成功');
									    	}
									    	tempArray1=[];//清空数组
									    	$('#codeDef_datagird').datagrid("reload");
									    	$('#codeDef_datagird').datagrid("clearSelections");
									    	updateDefaultRowObj();
										},
									error:function (XMLHttpRequest, textStatus, errorThrown){
										alert("Error:"+XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
									}
								});
							}
						});
					}
				});

				
			}else{alert('请选择一个数据字典定义!')}
		}else{alert('请先选择一个数据词典类型!')}
	}
		
	</script>
	
	<script>
	var lastIndex1;
	var tempArray1=[];
	
	$(function(){
		$('#codeDef_datagird').datagrid("clearDefaultEvents");
		$('#codeDef_datagird').datagrid({
			pagination:true,
			idField:'basCodeDefUuid',
			singleSelect: true,  // 单选或多选
			height:335,
			toolbar:[{
				text:text_newBtn,
				iconCls:'icon-add',
				handler:fn_toolbar_newBtn
			},'-',{
				text:text_delBtn,
				iconCls:'myCustomerIcon_remove',
				handler:fn_toolbar_delBtn
			},'-',{
				text:text_saveBtn,
				id:"text_saveBtn",
				iconCls:'icon-save',
				handler:fn_toolbar_saveBtn
			},'-',{
				text:text_returnBtn,
				iconCls:'icon-undo',
				handler:fn_toolbar_returnBtn
			},'-',{
				text:text_validateBtn,
				iconCls:'myCustomerIcon_validate',
				handler:fn_toolbar_validateBtn
			},'-',{
				text:text_cancelBtn,
				iconCls:'myCustomerIcon_cancel',
				handler:fn_toolbar_cancelBtn
			},'-',{
				text:'设为默认',
				iconCls:'icon_set_default',
				handler:fn_setCodeDefDefault
			}],
			commonColumns:{
				before:[],
				behind:[
					{}]
			},
			onSelect:fn_onSelect,
			onDblClickRow:fn_dbClick,
			fitColumns: false,
			nowrap:false,
			rownumbers:true,
			showFooter:true,
			pageSize:10, //每页显示记录数
			pageList:[5,10], //可调整每页显示的记录数
			striped:true,	//斑马线
			sortName:"createTime",
			sortOrder:"desc",
			onLoadSuccess:function(){
				$('#codeDef_datagird').datagrid("clearSelections");
			}
		});
	});
		//新增按钮
		function fn_toolbar_newBtn(){
			if($('#codeType_datagird').datagrid('getSelections').length<=0){
				alert('请先选择一个数据词典类型');
				return;
			}
			$('#codeDef_datagird').datagrid('endEdit', lastIndex1);
			$('#codeDef_datagird').datagrid('insertRow',{
				row:{status:'Pending'}
			});
			lastIndex1 = $('#codeDef_datagird').datagrid('getRows').length-1;
			$('#codeDef_datagird').datagrid('selectRow', lastIndex1);
			$('#codeDef_datagird').datagrid('beginEdit', lastIndex1);
			return;
		}

		//删除按钮
		function fn_toolbar_delBtn(){
			//生效的时候不允许删除
			var _row=$('#codeDef_datagird').datagrid('getSelected');
			if(_row&&_row.status=="Active"){
				alert('已生效的记录不能够删除!');
				return false;
			}
			if (_row){
				$.messager.confirm('确定框', '确定删除?', function(r){
						if (r){
							$('#codeDef_datagird').datagrid('deleteRow',$('#codeDef_datagird').datagrid('getRowIndex',_row));
							_row._class=undefined;
							_row.createTime=undefined;
							_row.modifyTime=undefined;
							_row.rowState="Deleted";
							var delTempArray=[];
							delTempArray.push(_row);
							var jsonResult = $.toJSON(delTempArray) ;
							LogisticsOrderManager.object2base64(jsonResult,function(data){
							if(data){
								//使用ajax提交到服务器保存数据
								$.ajax({
									type: "POST",
								    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=saveBasCodeDef",
								    data:"jsonResult="+data+"",
								    success: function(result){
									    	var rs = $.parseJSON(result);
								   	 		if(rs&&rs.error){
								    			error('执行删除时发生错误:'+rs.error);
								    			$("#"+dataGridId).datagrid('rejectChanges');
								    			return;
				    						}
									    	if(delTempArray.length>0){
									    		alert('删除成功');
									    	}
									    	delTempArray=[];//清空数组
									    	$('#codeDef_datagird').datagrid("reload");
									    	$('#codeDef_datagird').datagrid("clearSelections");
									    	updateDefaultRowObj();
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
		}

		//保存按钮
		function fn_toolbar_saveBtn(){
		    $("#text_saveBtn").linkbutton("disable").unbind("click");
			var validated= false;
			var editRowsIndex=$('#codeDef_datagird').datagrid('getEditRowsIndex');
			$.each(editRowsIndex,function(){
				if(!$('#codeDef_datagird').datagrid('validateRow',this)){
					validated=true;
				}
			});
			if(validated){
			    $("#text_saveBtn").linkbutton("enable").unbind("click").bind("click");
				alert("请输入必输项!");
				return;
			}
		
			//若是编辑的则可以把该行取消编辑
			$('#codeDef_datagird').datagrid('endEdit',lastIndex1);	
			var selectedRow = $('#codeDef_datagird').datagrid('getSelected');
			var updateRows = $('#codeDef_datagird').datagrid('getChanges','updated');  //得到修改的数据
			var deleteRows = $('#codeDef_datagird').datagrid('getChanges','deleted');  //得到删除的数据
			var insertRows = $('#codeDef_datagird').datagrid('getChanges','inserted'); //得到新增的数据
	        if(selectedRow==null||selectedRow.length<=0){
	            alert("请先进行相关操作再保存!");
	            $("#text_saveBtn").linkbutton("enable").unbind("click").bind("click");
	            return;
	        }
			//保存修改的数据 
			if (updateRows.length>0) {
				for ( var i = 0; i < updateRows.length; i++) {
					var row = updateRows[i];
					updateRows[i]._class=undefined;
					updateRows[i].createTime=undefined;
					updateRows[i].modifyTime=undefined;
					updateRows[i].rowState="Modified";
					if(notNullFieldsObj!=undefined){
						$.extend(updateRows[j],notNullFieldsObj);
					}
					if($('#codeDef_datagird').data('_basCodeTypeUuid')){
						updateRows[i].basCodeTypeUuid=$('#codeDef_datagird').data('_basCodeTypeUuid');
					}
					tempArray1.push(updateRows[i]);
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
						if($('#codeDef_datagird').data('_basCodeTypeUuid')){
							deleteRows[j].basCodeTypeUuid=$('#codeDef_datagird').data('_basCodeTypeUuid');
						}
						tempArray1.push(deleteRows[j]);
					}
			}
	
			//保存新增的数据
			$('#codeDef_datagird').datagrid('acceptChanges');
			$.each(insertRows,function(k,v){
				if(v['basCodeDefUuid']==undefined){ //新增
					v._class=undefined;
					v.createTime=undefined;
					v.modifyTime=undefined;
					v.rowState="Added";
					//必输字段的控制,约定优于配置,基础数据的所有必输字段都排在第2位,所以用fields[1]来取得
					var fields=$('#codeDef_datagird').datagrid('getColumnFields');
					var needField=fields[3];
					if(v[needField]!=undefined){
						if(notNullFieldsObj!=undefined){
							$.extend(v,notNullFieldsObj);
						}
						if($('#codeDef_datagird').data('_basCodeTypeUuid')){
							v.basCodeTypeUuid=$('#codeDef_datagird').data('_basCodeTypeUuid');
						}
						tempArray1.push(v);
					}
				}
		}); 
		
		var _defaultRow = $('#codeDef_datagird').data('_defaultRow');
		if(_defaultRow){
			tempArray1.push(_defaultRow);
		}
		
		//再处理一下这个结果集，把不要的字段都删掉
		if(tempArray1){
			$.each(tempArray1,function(){
				this._class=undefined;
				this.createTime=undefined;
				this.modifyTime=undefined;
			});
		}
		
		var jsonResult= $.toJSON(tempArray1);
		LogisticsOrderManager.object2base64(jsonResult,function(data){
		if(data){		
				//使用ajax提交到服务器保存数据
				$.ajax({
					type: "POST",
				    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=saveBasCodeDef",
				    data:"jsonResult="+data+"",
				    success: function(result){
					    	$("#text_saveBtn").linkbutton("enable").unbind("click").bind("click");
					    	var rs = $.parseJSON(result);
				   	 		if(rs&&rs.error){
				    			error('执行保存时发生错误:'+rs.error);
				    			$('#codeDef_datagird').datagrid('rejectChanges');
				    			return;
							}
					    	if(tempArray1.length>0){
					    		alert('保存成功');
					    	}
					    	tempArray1=[];//清空数组
					    	$('#codeDef_datagird').datagrid("reload");
					    	$('#codeDef_datagird').datagrid("clearSelections");
					    	updateDefaultRowObj();
						},
					error:function (XMLHttpRequest, textStatus, errorThrown){
						$("#text_saveBtn").linkbutton("enable").unbind("click").bind("click");
						alert("Error:"+XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
					}
				});
			  }	
			});
		}

		//返回按钮
		function fn_toolbar_returnBtn(){
			$('#codeDef_datagird').datagrid('rejectChanges');
			tempArray1=[];
		}

		//生效按钮
		function fn_toolbar_validateBtn(){
			var rows = $('#codeDef_datagird').datagrid('getSelections');
			var validate = false;
			$.each(rows,function(key,value){
				if(rows[key].basCodeDefUuid==undefined){
					validate = true;
				}
			});
			if(validate){
				alert("请先保存新增数据后再进行此操作!");
				return;
			}
			if(rows.length>0){
				$.messager.confirm('确定框', '确定生效'+rows.length+'条记录吗?', function(r){
					if(r){
						$.each(rows,function(i,v){
							//生效操作,设置状态为生效
							this.status='Active';
							var selectedRow = this;
							var index = $('#codeDef_datagird').datagrid('getRowIndex',this);
							selectedRow._class=undefined;
							selectedRow.createTime=undefined;
							selectedRow.modifyTime=undefined;
							selectedRow.rowState="Modified";
							tempArray1.push(selectedRow);
							$('#codeDef_datagird').datagrid('refreshRow',index);
						});
						var selectedRows = $('#codeDef_datagird').datagrid('getSelections');
						var jsonResult = $.toJSON(selectedRows);
						LogisticsOrderManager.object2base64(jsonResult,function(data){
						if(data){
							//使用ajax提交到服务器保存数据
							$.ajax({
								type: "POST",
							    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=saveBasCodeDef",
							    data:"jsonResult="+data+"",
							    success: function(result){
								    	if(tempArray1.length>0){
								    		alert('生效成功');
								    	}
								    	tempArray1=[];//清空数组
								    	$('#codeDef_datagird').datagrid("reload");
								    	$('#codeDef_datagird').datagrid("clearSelections");
									}
							});
						   }
						});	
					}
				});
			}else{
				$.messager.alert("系统提示","请选择要生效的记录！");			
			}
			return;
		}

		//作废按钮
		function fn_toolbar_cancelBtn(){
			var rows = $('#codeDef_datagird').datagrid('getSelections');
			var validate = false;
			$.each(rows,function(key,value){
				if(rows[key].basCodeDefUuid==undefined){
					validate = true;
				}
			});
			if(validate){
				alert("请先保存新增数据后再进行此操作!");
				return;
			}
			if(rows.length>0){
				$.messager.confirm('确定框', '确定作废这'+rows.length+'条记录吗?', function(r){
					if(r){
						$.each(rows,function(i,v){
							//作废操作,设置状态为生效
							this.status='Cancel';
							var selectedRow = this;
							var index = $('#codeDef_datagird').datagrid('getRowIndex',this);
							selectedRow._class=undefined;
							selectedRow.createTime=undefined;
							selectedRow.modifyTime=undefined;
							selectedRow.rowState="Modified";
							tempArray1.push(selectedRow);
							$('#codeDef_datagird').datagrid('refreshRow',index);
						});
						
						var selectedRows = $('#codeDef_datagird').datagrid('getSelections');
						var jsonResult = $.toJSON(selectedRows);
						LogisticsOrderManager.object2base64(jsonResult,function(data){
						if(data){						
								//使用ajax提交到服务器保存数据
								$.ajax({
									type: "POST",
								    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=saveBasCodeDef",
								    data:"jsonResult="+data+"",
								    success: function(result){
									    	if(tempArray1.length>0){
									    		alert('作废成功');
									    	}
									    	tempArray1=[];//清空数组
									    	$('#codeDef_datagird').datagrid("reload");
									    	$('#codeDef_datagird').datagrid("clearSelections");
										}
								});
							}
						});	
					}
				});
			}else{
				$.messager.alert("系统提示","请选择要作废的记录！");			
			}
			return;
		}


		//触发选择事件
		function fn_onSelect(rowIndex,rowData){
			var dataGridId="codeDef_datagird";
			var rows=$("#"+dataGridId).datagrid('getSelections');
			//若选中的数据有已生效或已作废的记录,则禁用删除按钮
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

		//触发双击事件
		function fn_dbClick(rowIndex, row){
			if (lastIndex1 != rowIndex){
				var dataGridId= 'codeDef_datagird';
				$("#"+dataGridId).datagrid('endEdit', lastIndex1);
				$("#"+dataGridId).datagrid('beginEdit', rowIndex);
				var _i = 1;
				var fields=$("#"+dataGridId).datagrid('getColumnFields',false);
				var currentModifyRow=$(' .datagrid-view2 tr').filter('.datagrid-row-selected').filter('.datagrid-row-editing');
				$("tr[datagrid-row-index="+rowIndex+"] td[field="+fields[_i]+"] input",$(".datagrid-view2 .datagrid-body")).removeClass("validatebox-text validatebox-invalid").hide();
				var text=$("td[field="+fields[_i]+"] div",$(".datagrid-view2 .datagrid-body")).get(rowIndex);
				$("td",text).append(row[fields[_i]]);
			}
			lastIndex1 = rowIndex;
		}


	//设置modified字段
	function setCodeDefModify(value,rowData,rowIndex){
		if(value==1) return '是 ';
		if(value==0) return '否';
	}
	</script>
	
	<script type="text/javascript">
	$(function(){
		$('#modifiable').combobox({
		    data:[
					{
					    "v":"",
					    "text":"全部"
					},
			    	{
					    "v":1,
					    "text":"是"
					},
			    	{
					    "v":0,
					    "text":"否"
					}
				],
		    valueField:'v',
		    textField:'text',
		    panelHeight:67,
		    editable:false
			});
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
		    panelHeight:87
			});
	});
	
	function isUnique(value){
		var rows = $("#codeDef_datagird").datagrid("getRows");
		var bool = true;
		$.each(rows,function(i,o){
			if(i<rows.length-1 && this.codeValue==value){
				bool = false;
			}
		});
		return bool;
	}
	</script>
	
  </head>
  
  <body class="easyui-layout">
	<div region="north" border="false" iconCls="myCustomerIcon_searchForm" title="查询条件"	style="height:150px;">
		<%--按钮--%>
		<div class="myCustomer_toolbar">
			<a href="#" id="searchBtn" class="easyui-linkbutton"
					plain="true" iconCls="icon-search" style="float: left"><bean:message bundle="wlp.common" key="global.search"/></a>
			<div class="datagrid-btn-separator"></div>
			<a href="#" id="resetButton" class="easyui-linkbutton"
					plain="true" iconCls="myCustomerIcon_clear" style="float: left"><bean:message bundle="wlp.common" key="global.clear"/></a>
			<div class="datagrid-btn-separator"></div>
		</div>
		<%--查询表单--%>
		<div id="formContainer" >
			<form id="queryForm">
				<table id="searForm" width="880px" style="margin:0;padding:0;">
					<tr>
						<td align="center" td_align="left" class="textStyle">
							<label for="typeName">
								<bean:message bundle="wlp.basicdata" key="BasCodeType.typeName"/>:
							</label>
						</td>
						<td>
							<input id="typeName" name="typeName"  class="easyui-validatebox" />
						</td>
						<td align="center" td_align="left" class="textStyle">
							<label for="status" >
								<bean:message bundle="wlp.basicdata" key="BasCodeDef.status"/>:
							</label>
						</td>
						<td>
							<input id="status" name="status" style="width: 80px"/>
						</td>
					</tr>
					<tr>
						<td align="center" td_align="left" class="textStyle">
							<label for="typeCode" >
								<bean:message bundle="wlp.basicdata" key="BasCodeType.typeCode"/>:
							</label>
						</td>
						<td>
							<input id="typeCode" name="typeCode" class="easyui-validatebox" />
						</td>
						
						<td  align="center" td_align="left" class="textStyle">
							<label for="modifiable" >
								<bean:message bundle="wlp.basicdata" key="BasCodeDef.modifiable"/>:
							</label>
						</td>
						<td >
							<input id="modifiable" name="modifiable" style="width: 80px">
						</td>
					</tr>
					<input id="officeCode" name="officeCode" type="hidden" value="<%=officeCode%>">
				</table>
			</form>
		</div>
	</div>
	<div region="center" border="false">
	  	<div class="easyui-layout" fit="true" border="false">
			<div id="codeTypeContainer" region="west" title="数据词典类型" border="true" style="width:460px">
					<table id="codeType_datagird" iconCls="icon-edit" fit="true"
								url="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getCodeTypeByCondition&officeCode=<%=officeCode%>">
					</table>
			</div>
			<div id="codeDefContainer"  region="center" title="数据词典定义" border="true" >
				<table id="codeDef_datagird" iconCls="icon-edit" fit="true"
					url="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&oc=true&queryType=BasCodeDefModel">
					<thead>
						<tr>
							<th field="status" formatter='fn_global_setBasicdataState'  width="40"  align="center" td_align="left" >
								<bean:message bundle="wlp.basicdata" key="BasCodeDef.status"/>
							</th>
							<th  field="codeValue"  width="80"  align="center" td_align="left"
								editor="{type:'validatebox',options:{required:true,validType:'customValid[this.isUnique]'}}">
								<bean:message bundle="wlp.basicdata" key="BasCodeDef.codeValue"/>
							</th>
							<th field="codeNumber" width= "65"  align="center" td_align="right" 
								 editor="{type:'numberbox',options:{precision:6,max:99999999999.999999}}">
								<bean:message bundle="wlp.basicdata" key="BasCodeDef.codeNumber"/>
							</th>
							<th field="displayValue" width="100"  align="center" td_align="left"  editor="validatebox">
								<bean:message bundle="wlp.basicdata" key="BasCodeDef.displayValue"/>
							</th>
							<th field="displayValueEn" width="100"  align="center" td_align="left"
								editor="{type:'validatebox',options:{validType:'noChinese'}}">
								<bean:message bundle="wlp.basicdata" key="BasCodeDef.displayValueEn"/>
							</th>
							<th field="modifiable" width="80"  align="center" td_align="left"
								editor="{type:'combobox',options:{
								editable:false,
								panelHeight:46,
								data:[{modifyText:'是',value:'1'},{modifyText:'否',value:'0'}],valueField:'value',textField:'modifyText'}}"
								formatter="setCodeDefModify">
								<bean:message bundle="wlp.basicdata" key="BasCodeDef.modifiable"/>
							</th>
							<th field="controlWord" formatter='setDefaultView'  width="40"  align="center" td_align="left" >
								默认
							</th>
							<th field="remark" width="180"  editor="{type:'validatebox',options:{validType:'maxLength[149]'}}"  align="center" td_align="left">
								<bean:message bundle="wlp.basicdata" key="BasCodeDef.remark"/>
							</th>
							
							<th  field="createTime"  width="120"  align="center" td_align="left"  sortable='true' hidden="true">
								生成时间
							</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
  </body>
</html:html>
