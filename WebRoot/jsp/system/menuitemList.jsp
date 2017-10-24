<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<%
	String path = request.getContextPath();
SessionContextUserEntity scue = SessionContextUserEntity.currentUser(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
	<head>
		<!-- jquery easyui -->
		<jsp:include page="../../common/imp_dwr.jsp"></jsp:include>
		<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
		<style type="text/css">
#tableQueryForm td {
	font-size: 12px;
}
</style>

	</head>
	<body class="easyui-layout">
		<!--  菜单管理页面 2012年2月28日 19:09:39-->
		<script>
		var lastIndex;
		var customerCodeWholeArray=new Array();
			$(function(){
				customerCodeWholeArrayManger();//获取所有组织机构信息保存在页面中。不显示页面信息
				setTimeout(function(){
					__getMenuitemdatagirdChuShiHua();//初始化表格信息以及数据
				},300);
				__getOfficeUPChuShiHua();//初始化页面查询所需要的下拉框等
				__getofficeCodetree();
			});
		</script>
		<script type="text/javascript">//
		//取出组织类型数据保存到全局变量officeTypeWholeArray的数组中。
			function customerCodeWholeArrayManger(){
				if(customerCodeWholeArray!=null && customerCodeWholeArray.length==0){
					$.ajax({
			   			type: "POST",
					    url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=UseritmecustomerCode&date='+new Date(),
					    dataType:'json',
					    success: function(date){
					    	$.each(date,function(num,customerCode){
					 			customerCodeWholeArray.push(customerCode); 
					 		});
					 		//alert($.toJSON(customerCodeWholeArray));
					   	}
					});
				}
			}
			function __getOfficeUPChuShiHua(){
			var isoUrl="";
			//	if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
					isoUrl="<%=path%>/servlet/SinotransServlet?cmd=selectModuleitemtree&date="+new Date();
			//	}else{
			//		isoUrl="<%=path%>/servlet/SinotransServlet?cmd=selectModuleitemUsertree&date="+new Date();
			//	}
				$('#sysMenuGroupUuid').combotree({ //上级菜单getMenuGroupModelMapS有顶级菜单 getMenuGroupTree
					url:isoUrl,
					valueField:"id",
					textField:"text",
					onSelect:function(redor1){
						var menuItemName=$("#menuItemName").val();
						var status=$("#status").combobox("getValue");
						var createTime_s=$("#createTime__start").datebox("getValue");
						var createTime_e=$("#createTime__end").datebox("getValue");
						var condition = $("#__condition").val();
						var sysofficeCode=null;
						if("<%=scue.getUserId()%>"=="<%=CommonUtil.SYSTEM_ADMIN%>"){
							sysofficeCode=$('#sysofficeCode').combotree('getValue');
						}
							$("#menuitemdatagird").datagrid('load',{
			                	__menuItemName:menuItemName,
			                	__status:status,
			                	__sysMenuGroupUuid:redor1.id,
			                	createTime__start:createTime_s,
			                	createTime__end:createTime_e,
			                	__officeCode:sysofficeCode,
			                	__condition:condition
			                });
		                
					}
				});
				$('#status').combobox({ //状态
					panelHeight:"auto", 
					onSelect:function(redor2){
						var menuItemName=$("#menuItemName").val();
						var createTime_s=$("#createTime__start").datebox("getValue");
						var createTime_e=$("#createTime__end").datebox("getValue");
						var condition = $("#__condition").val();
						var  sysMenuGroupUuid=$('#sysMenuGroupUuid').combotree('getValue');//上级菜单
						var sysofficeCode=null;
						if("<%=scue.getUserId()%>"=="<%=CommonUtil.SYSTEM_ADMIN%>"){
							sysofficeCode=$('#sysofficeCode').combotree('getValue');
						}
							$("#menuitemdatagird").datagrid('load',{
			                	__menuItemName:menuItemName,
			                	__status:redor2.value,
			                	__sysMenuGroupUuid:sysMenuGroupUuid,
			                	__officeCode:sysofficeCode,
			                	createTime__start:createTime_s,
			                	createTime__end:createTime_e,
			                	__condition:condition
			                });
					}
				});
			}
			function __getofficeCodetree(){
			$("#sysofficeCode").combotree({
					url:"<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectOfficeCodeTree&date="+new Date(),
					onSelect:function(redor3){
						var menuItemName=$("#menuItemName").val();
						var status=$("#status").combobox("getValue");
						var createTime_s=$("#createTime__start").datebox("getValue");
						var createTime_e=$("#createTime__end").datebox("getValue");
						var condition = $("#__condition").val();
						var  sysMenuGroupUuid=$('#sysMenuGroupUuid').combotree('getValue');//上级菜单
						$("#menuitemdatagird").datagrid('load',{
		                	__menuItemName:menuItemName,
		                	__status:status,
		                	__officeCode:redor3.id,
		                	__sysMenuGroupUuid:sysMenuGroupUuid,
		                	createTime__start:createTime_s,
		                	createTime__end:createTime_e,
		                	__condition:condition
		                });
					}	
				});
			}
			function __getMenuitemdatagirdChuShiHua(){
			var isoUrl="";
				if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
					isoUrl="<%=path%>/servlet/SinotransServlet?cmd=query&queryType=SysMenuItemModel";
				}else{
					isoUrl="<%=path%>/servlet/SinotransServlet?cmd=query&queryType=SysMenuItemModel&oc=true";
				}
				$('#Menuitem').dialog();
				$('#Menuitem').dialog('close');
				$('#menuitemdatagird').datagrid({
					url:isoUrl,
					height:452,
					pagination:true,
					rownumbers:true,
					striped:true,
					singleSelect: true,
					pageSize:20, //每页显示记录数
					pageList:[10,20,30,40,50], //可调整每页显示的记录数
					//singleSelect: true,  // 单选或多选
					idField:'sysMenuItemUuid',
					sortName:'menuItemName',
					sortOrder:'asc',
					toolbar:[{
						text:'新增',
						iconCls:'icon-add',
						handler:function(){
							menuItemNew();
						}
					},'-',{
						text:'编辑',
						iconCls:'icon-edit',
						handler:function(){
							menuItemEdit();
						}
					},'-',{
						text:'删除',
						id:'deleteMenuItem',
						iconCls:'myCustomerIcon_remove',
						handler:function(){
							menuItemDelete();
						}
					},'-',{
						text:'生效',
						id:'activeRoleitem',
						iconCls:'icon-ok',
						handler:function(){
							userdatagirdStatus("<%=CommonUtil.Active%>");
						}
					},'-',{
						text:'作废',
						id:'claseRoleitem',
						iconCls:'icon-cancel',
						handler:function(){
							userdatagirdStatus("<%=CommonUtil.Cancel%>");
						}
					}],
					
					onDblClickRow:function(rowIndex,rowData){
						$('#menuitemdatagird').datagrid('endEdit', lastIndex);
						$('#menuitemdatagird').datagrid('beginEdit', rowIndex);
						lastIndex = rowIndex;
					},
					onClickRow:function(rowIndex,rowData){
						if(rowData.status=="<%=CommonUtil.Active%>"||rowData.status=="<%=CommonUtil.Cancel%>"){
				    		__getdOe('d');
				    	}else{
				    		SysRoleMenuItemManager.getValidationMenuItemAssociation(rowData.sysMenuItemUuid,function(data){
								if(data=="true"){
									__getdOe('d');
								}else{
									__getdOe("e");
								}
							});
						}
					}
				});
			}
			//菜单新增
			function menuItemNew(){
			getButtonKongZhi("e");
				$('#Menuitem').dialog({
					modal:true
				});
				$("#menuItemCode1").attr("readonly",false);
				$("#saveDivButton").attr("name","saveDivButton");//修改完成按钮数据为保存
				UserItemAccredits();
				adduserget();
				cleardate('MenuitemForm');
				$("#status1").combobox("select","<%=CommonUtil.Active%>");
				SysOfficeManager.getOfficeOofficeCodeModel("<%=scue.getOfficeCode()%>",function(data){
					if(data){
						$('#officeCode1').combotree('setValue',data);//所属菜单项
					}
				});
				//alert($("#saveDivButton").attr("name"));
				//$('#officeCode1').combotree('setValue',"TRANSGD");
				var ido=$("#sysMenuGroupUuid").combotree('getValue');
				if(ido)
				$("#sysMenuGroupUuid1").combotree('setValue',ido);
			}
			//菜单编辑
			function menuItemEdit(){
			getButtonKongZhi("e");
				var row = $('#menuitemdatagird').datagrid('getSelected');//selectuserid
				if(row==null){
				alert('请先选择一行数据再进行操作!');
				}else{
					$.ajax({
		   			type: "POST",
				    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectMenuitemid&date="+new Date(),
				    data: "selectmenuitemid="+row.sysMenuItemUuid+"",
				    dataType:'json',
				    success: function(date){
				    	$('#Menuitem').dialog({
							draggable:false,
							modal:true
						});
				    	UserItemAccredits();
				    	adduserget();
				    	//alert('ss : '+date.userUuid);
				    	$("#recVer1").val(date.recVer);
				    	$("#sysMenuItemUuid1").val(date.sysMenuItemUuid);
				    	$("#menuItemName1").val(date.menuItemName);
				    	$('#sysMenuGroupUuid1').combotree('setValue',date.sysMenuGroupUuid);//上级菜单
				    	$("#isDialog1").combobox('setValue',date.isDialog);//是否对话框
				    	$("#status1").combobox('setValue',date.status);//状态
				    	$("#menuItemNameCn1").val(date.menuItemNameCn);
				    	$("#menuItemSeq1").val(date.menuItemSeq);
				    	$("#creator1").val(date.creator);
				    	$("#menuItemCode1").val(date.menuItemCode);
				    	if(date.status!=null&&date.status!="" && date.status=='<%=CommonUtil.Active%>'||date.status=='<%=CommonUtil.Cancel%>' ){
				    		$("#menuItemCode1").attr("readonly","readonly");
				    	}else{
				    		$("#menuItemCode1").attr("readonly",false);
				    	}
				    	SysOfficeManager.getOfficeOofficeCodeModel(date.officeCode,function(data){
							if(data){
								$('#officeCode1').combotree('setValue',data);//所属菜单项
							}
						});
				    	$("#menuItemUrl1").val(date.menuItemUrl);
				    	$("#remark1").val(date.remark);
				    	$("#className1").val(date.className);
				    	$("#menuItemAction1").val(date.menuItemAction);
				    	
				    	$("#saveDivButton").attr("name","updatemenuitem");//修改完成按钮数据为修改
				    	//alert(menuitemjson.recVer+" : "+menuitemjson.sysMenuItemUuid+" : "+menuitemjson.menuItemName+" : "+menuitemjson.isDialog+" : "+menuitemjson.menuItemNameCn+" : "+menuitemjson.status+" : "+menuitemjson.menuItemSeq+" : "+menuitemjson.creator+" : "+menuitemjson.menuItemCode);
  						}
				});
				}
			}
			//菜单删除信息
			function menuItemDelete(){
				var row = $('#menuitemdatagird').datagrid('getSelected');
				if(row.status=="<%=CommonUtil.Active%>"){
					alert('该数据以生效。请审核后重新操作！');
					__getdOe('d');//毁掉删除按钮
				}else{
					if(row==null){
						alert('请先选择一行数据再进行操作！');
					}else{
					var index =$('#menuitemdatagird').datagrid('getRowIndex', row);
						$.messager.confirm('确定框', '确定删除‘'+row.menuItemName+'’菜单信息吗?', function(r){
							if (r){
								//$('#menuitemdatagird').datagrid('deleteRow', index);
								//删除用户操作Ajax
								$.ajax({
						   			type: "POST",
								    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=deleteMenuitem&&date="+new Date(),
								    data: "deleteMenuitem="+row.sysMenuItemUuid+"",
								    dataType:'json',
								    success: function(date){
								    	if(date==null||date.relust){
								    		alert("操作成功");
								    		__getdOe('d');//毁掉删除按钮（操作成功需要灰掉按钮。否则再次点击确定就报错）
								    	}else if(date.error){
								    		alert("操作失败、可能有关联数据未清除！错误信息如下："+date.error);
								    	}else{
								    		alert("操作失败、暂无返回信息。");
								    	}
								    	$('#menuitemdatagird').datagrid("reload");//根据表格ID刷新页面
			   						}
								});
							}
						});
					}
				}
			}
			//修改状态。需要传送的值不为空必须为状态
			function userdatagirdStatus(status){
				var row = $('#menuitemdatagird').datagrid('getSelected');
				if(row==null){
					alert('请先选择一行数据再进行操作！');
				}else{
					SysUserManager.updateSysStatusOue(row.sysMenuItemUuid,status,"SYS_MENU_ITEM","STATUS","SYS_MENU_ITEM_UUID",function(data){
						if(data){
							alert("操作成功");
						}else{
							alert("操作失败");
						}
						$('#menuitemdatagird').datagrid("reload");//根据表格ID刷新页面
					});
				}
			}
			//取出所有公司(仓库)代码
			function adduserget(){
				$("#officeCode1").combotree({//上级组织
					url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectOfficeitemtree1&date='+new Date(),
					onClick:function(node){
							var isleaf=$('.tree').tree('isLeaf',node.target);
						if(!isleaf){
							alert('此节点不是菜单节点、请明确后重新选择！');
							$('#officeCode1').combotree('clear');
						}
					}	
				});
			}
			//重置按钮事件
			function cleardate(s){
				$('#'+s).form('clear');
				$('#isDialog1').combobox({"editable":false});
			}
			function cleardates(s){
				$('#'+s).form('clear');
				$('#status').combobox("select","null");
			}
			
			
			//关闭对话框窗口(传入窗口id)
			function closewicket(c){
				$("#"+c).dialog('close');
			}
			//查询按钮事件
			function userselect(){
				//alert('查询');
				var menuItemName=$("#menuItemName").val();
				var status=$("#status").combobox("getValue");
			//	var sysMenuGroupUuid=$("#sysMenuGroupUuid").val();
				var createTime_s=$("#createTime__start").datebox("getValue");
				var createTime_e=$("#createTime__end").datebox("getValue");
				//var sysMenuGroupUuid=$("#sysMenuGroupUuid").combobox("getValue");
				var condition = $("#__condition").val();
				var  sysMenuGroupUuid=$('#sysMenuGroupUuid').combotree('getValue');//上级菜单
				//alert(userName+' = '+userType+' = '+creator+' = '+idCard);
				var sysofficeCode=null;
				if("<%=scue.getUserId()%>"=="<%=CommonUtil.SYSTEM_ADMIN%>"){
					sysofficeCode=$('#sysofficeCode').combotree('getValue');
				}
				if(sysofficeCode){
				
				}else{
					$("#menuitemdatagird").datagrid('load',{
	                	__menuItemName:menuItemName,
	                	__status:status,
	                	createTime__start:createTime_s,
	                	createTime__end:createTime_e,
	                	__condition:condition,
	                	__officeCode:sysofficeCode,
	                	__sysMenuGroupUuid:sysMenuGroupUuid
	                });
		       	}
			}
			//提交表单新增数据库数据和刷新页面
			function saveuser(){
			getButtonKongZhi("d");
				var menuitemjsonResults=$("#MenuitemForm").serializeArray(); //得到数组json对象
				var saveuerjudge=$("#saveDivButton").attr("name");
				var menuitemjson;
					menuitemjson="{";
					$.each(menuitemjsonResults,function(k,v){
						if(k==menuitemjsonResults.length-1){
							if(v.name=="menuItemAction"){
								menuitemjson=menuitemjson+'"'+v.name +'"'+':'+'"'+ v.value.replace(/\r\n/g, "\\n")+'"';
							}else{
								menuitemjson=menuitemjson+'"'+v.name +'"'+':'+'"'+ v.value+'"';
							}
						}else{
							if(v.name=="menuItemAction"){
								menuitemjson=menuitemjson+'"'+v.name +'"'+':'+'"'+ v.value.replace(/\r\n/g, "\\n")+'"'+",";
							}else{
								menuitemjson=menuitemjson+'"'+v.name +'"'+':'+'"'+ v.value+'"'+",";
							}
							
						}
					});
					menuitemjson=menuitemjson+"}";
					if(saveuerjudge=="saveDivButton"){
					LogisticsOrderManager.object2base64(menuitemjson,function(menuitemjson1){
						if(menuitemjson1){
						//使用ajax提交到服务器保存数据
						$.ajax({
				   			type: "POST",
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=savaMenuitem&&date="+new Date(),
						    data: "menuitemjson="+menuitemjson1+"",
						    dataType:'json',
						    success: function(date){
						    	if(date==null||date.relust){
						    		alert("操作成功");
						    	}else if(date.error){
						    		alert("操作失败！错误信息如下："+date.error);
						    		getButtonKongZhi("e");
						    	}else{
						    		alert("操作失败、暂无返回信息。");
						    		getButtonKongZhi("e");
						    	}
						    	$('#MenuitemForm').form('clear');//重置用户填写窗口的信息
						    	$('#Menuitem').dialog('close');//关闭用户信息填写窗口
						    	$('#menuitemdatagird').datagrid("reload");//根据表格ID刷新页面
		   					}
						});
						}
					});
					}else if(saveuerjudge=="updatemenuitem"){
					LogisticsOrderManager.object2base64(menuitemjson,function(menuitemjson1){
						if(menuitemjson1){
						//使用ajax提交到服务器保存数据
						$.ajax({
				   			type: "POST",
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=updateMenuitem&&date="+new Date(),
						    data: "menuitemjson="+menuitemjson1+"",
						    dataType:'json',
						    success: function(date){
						    	if(date==null||date.relust){
						    		alert("操作成功");
						    	}else if(date.error){
						    		alert("操作失败！错误信息如下："+date.error);
						    		getButtonKongZhi("e");
						    	}else{
						    		alert("操作失败、暂无返回信息。");
						    		getButtonKongZhi("e");
						    	}
						    	$('#MenuitemForm').form('clear');//重置用户填写窗口的信息
						    	$('#Menuitem').dialog('close');//关闭用户信息填写窗口
						    	$('#menuitemdatagird').datagrid("reload");//根据表格ID刷新页面
		   					}
						});
						}
					});
					}
			}
			//验证表单数据
			function provinguser(){
					//验证表单是否通过
				var validateable=$('#MenuitemForm').form('validate');
				if(validateable){
					saveuser();
				}else{
					alert("*必填数据为空！请审核后重新操作！");
				}
				//$("#saveDivButton").attr('disabled','disabled');//enable
				/**var menuItemName1=$("#menuItemName1").val();//菜单中文名称
				var menuItemCode1=$("#menuItemCode1").val();//菜单代码
				var menuItemUrl1=$("#menuItemUrl1").val();//菜单连接
				var menuItemSeq1=$("#menuItemSeq1").val();//菜单排序号(数字)
				var saveuerjudge=$("#saveDivButton").attr("name");//取得判断保存与修改的关键字
				if(isNaN(menuItemName1)){
					if(isNaN(menuItemCode1)){
						if(isNaN(menuItemUrl1)){
							if(menuItemName1.length>1){
								if(menuItemCode1.length>2){
									if(menuItemUrl1.length>2){
										if(menuItemSeq1.length==0){
											saveuser();
										}else if(isNaN(menuItemSeq1)){
											$("#menuItemSeq1").focus();
											alert('菜单排序号为数字、请核对后重新输入！');
											$("#saveDivButton").attr('disabled','enable');//enable
										}else{
											saveuser();
										}
										
									}else{
										$("#menuItemUrl1").focus();
										alert('菜单连接输入有误、请核对后重新输入！'); 
									}
								}else{ 
									$("#menuItemCode1").focus();
									alert('菜单代码输入有误、请核对后重新输入！');
								}
							}else{ 
								$("#menuItemName1").focus();
								alert('菜单中文名称不能少于两个字符！');
							}
						}else{
							$("#menuItemUrl1").val("");
							$("#menuItemUrl1").focus();
							alert('菜单连接不能为数字、或者为空！请审核后重新输入！'); 
						}
					}else{
						$("#menuItemCode1").val("");
						$("#menuItemCode1").focus();
						alert('菜单代码不能为数字、或者为空！请审核后重新输入！'); 
					}
				}else{
					$("#menuItemName1").val("");
					$("#menuItemName1").focus();
					alert('菜单中文名称不能为数字、或者为空！请审核后重新输入！'); 
				}*/
			}
			//取出所属菜单项树形
			function UserItemAccredits(){
			isoUrl="";
			//if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
			//		isoUrl="<%=path%>/servlet/SinotransServlet?cmd=getMenuGroupTree&date="+new Date();
			//	}else{
					isoUrl="<%=path%>/servlet/SinotransServlet?cmd=selectModuleitemtree&date="+new Date();
			//	}
				$("#sysMenuGroupUuid1").combotree({//getMenuGroupTree(有顶级菜单)
					url:isoUrl,
					onClick:function(node){
						//alert($.toJSON(node));
						var isleaf=$('.tree').tree('isLeaf',node.target);
						if(!isleaf){
							//alert('此节点不是菜单节点、请明确后重新选择！');
							//$('#sysMenuGroupUuid1').combotree('clear');
						}
					}	
				});
			}
			//状态。在获取数据的时候给某一列增加的字段显示方法
			function statusOOr(value,rowData,rowIndex){
				if(value=='<%=CommonUtil.Active%>'){
					return "有效";
				}else if(value=='<%=CommonUtil.Cancel%>'){
					return "作废";
				}else if(value=='<%=CommonUtil.Pending%>'){
					return "草稿";
				}else{
					return "---";
				}
			}
			function customerCodeHuoQu(value,rowData,rowIndex){
				$.each(customerCodeWholeArray,function(uuid,custCodeModel){
	  				if(custCodeModel.officeCode==value){
	  					value=custCodeModel.officeName;
	  				}
	  			});
	  			if(value==null||value==""||value=="null"){
	  				value="无";
	  			}
	  			return value;
			}
			//按钮控制
			function __getdOe(dOe){
				if(dOe!=null&&dOe=="d"){
		    		$("#deleteMenuItem").linkbutton("disable").unbind("click");
		    	}else if(dOe!=null&&dOe=="e"){
					$("#deleteMenuItem").linkbutton("enable");
				}
			}
			//去左空格;   
		 function ltrim(s){     
		    return s.replace(/(^\s*)/, "");  
		 }   
		 //去右空格;   
		function rtrim(s){   
		  return s.replace(/(\s*$)/, "");  
		}   
		 //去左右空格;   
		 function trim(s){  
		  return rtrim(ltrim(s));   
		 }
		////调用DWR验证userCode是否重复以及转换大写
		function getYanZhenUserCodeFunction(textid,valueUser){
			valueUser=trim(valueUser.toUpperCase());//转换大写并且去掉前后空格
			$("#"+textid).val(valueUser);
			if(/[^\x00-\xff]/g.test(valueUser)){
			 	alert( "含有汉字不能保存");
			 	$("#"+textid).val(null);
			}else {
				if(valueUser!=null&&valueUser!=""){
					SysMenuItemManager.getYanZhenUserCode(valueUser,function(boole){
						if(boole){
							$("#"+textid).val(null);
							alert("该编码已存在、请审核后重新填写！");
						}
					});
				}
			}
		}
		function getYanZhenUserEn(textid,valueUser){
			valueUser=trim(valueUser);//转换大写并且去掉前后空格
			$("#"+textid).val(valueUser);
			if(/[^\x00-\xff]/g.test(valueUser)){
			 	alert( "含有汉字不能保存");
			 	$("#"+textid).val(null);
			}
		}
		
		
	function getButtonKongZhi(dOe){
				if(dOe!=null&&dOe=="e"){
				$("#saveDivButton").linkbutton("enable").unbind("click").bind("click");
				$("#closeDivButton").linkbutton("enable").unbind("click").bind("click");
				}else{
				$("#saveDivButton").linkbutton("disable").unbind("click");
				$("#closeDivButton").linkbutton("disable").unbind("click");
				}
	}	
	</script>
			<div id="MenuitemqueryTerm" region="north" border="false" title="条件查询" style="height:160px;">
				<form id="MenuitemqueryForm">
					<div style="background: #efefef; width: 100%; float: left">
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-search" style="float: left" onclick="userselect()">查询</a>
						<div class="datagrid-btn-separator"></div>

						<!-- 这是分割线 -->
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="myCustomerIcon_clear" style="float: left"
							onclick="cleardates('tableQueryForm')">重置</a>
						<input type="hidden" id="__condition" name="__condition"
							value="menuItemName_ilikeAnywhere:sysMenuGroupUuid_ilikeAnywhere:createTime_between" />
					</div>
					<!-- legend>表单验证</legend -->
					<table id="tableQueryForm" style="width: 95%; float: left" >
						<tr align="right">
							<td width="100px;" align="right">
									菜单名:
							</td>
							<td width="200px;" align="left">
								<input id="menuItemName" name="menuItemName" size="25" class="easyui-validatebox" />
							</td>
							<td width="100px;" align="right">
									状态:
							</td>
							<td width="100px;" align="left">
								<!-- <input id="status" class="easyui-validatebox" size="25"  style="width:180px;"/> -->
								<select id="status" name="status" panelHeight="auto" name="status" style="width: 50px">
								 	<option value="null" selected="selected">全部</option>
									<option value="Active">有效</option>
									<option value="Cancel">作废</option>
									<option value="Pending">草稿</option>
								</select>
							</td>
							<td width="100px;" align="right">
									创建时间:
							</td>
							<td width="300px;" align="left">
								<input id="createTime__start" name="createTime__start"
									class="easyui-datebox easyui-validatebox" validType="dateFormat" style="width: 80px;" />
								<img src="<%=path%>/images/right.gif" />
								<input id="createTime__end" name="createTime__end"
									class="easyui-datebox easyui-validatebox" validType="dateFormat" style="width: 80px;" />
							</td>
						</tr>
						<tr align="right">
							<td align="right">
									上级菜单:
							</td>
							<td align="left">
								<input id="sysMenuGroupUuid" name="sysMenuGroupUuid" size="25" style="width:180px;"
									class="easyui-combotree" value="" />
							</td>
							
				<%
					if(scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false){
				%>
						<td align="right">
								组织机构:
						</td>
						<td align="left">
							<input id="sysofficeCode" name="sysofficeCode" size="25" style="width:180px;"
								class="easyui-combotree" value="" />
						</td>
				<% 	
						}
				%>
						</tr>
						<td align="right">
						</td>
						<td align="left">
						</td>
					</table>
				</form>
			</div>
			<div region="center" border="false">
			<table id="menuitemdatagird" title="菜单项管理" iconCls="icon-edit"
				singleSelect="true" idField="itemid" fit="true">
				<thead>
					<tr>
						<!-- <th field="sysMenuItemUuid" width="80">
							菜单项ID
						</th>-->
						<th field="status" width="50" align="center" formatter="statusOOr">
							状态
						</th>
						<th field="menuItemSeq" width="50" align="center">
							排序号
						</th>
						<th field="menuItemName" width="150" align="center" td_align="left">
							菜单中文名
						</th>
						<th field="menuItemNameCn" width="150" align="center" td_align="left">
							菜单英文名
						</th>
						<th field="menuItemUrl" width="300" align="center" td_align="left">
							菜单链接
						</th>
						<th field="menuItemCode" width="150" align="center" td_align="left">
							菜单代码
						</th>
						<th field="officeCode" width="150" align="center" formatter="customerCodeHuoQu">
							仓库代码
						</th>
						<th field="menuItemAction" width="120" align="center" td_align="left">
							功能提交权限
						</th>
						<th field="createTime" width="130" align="center" td_align="left">
							创建时间
						</th>
					</tr>
				</thead>
			</table>
		<!-- 弹出框 -->
		<div id="Menuitem" style="padding: 5px; width: 600px; height: 420px; z-index: 9999;"
			title="菜单项管理">
			<form name="MenuitemForm" id="MenuitemForm">
				<div>
					<table id="tableQueryForm" style="width: auto;">
						<!-- 分割线 -->
						<tr>
							<td colspan="4">
								<input type="hidden" id="sysMenuItemUuid1"
									name="sysMenuItemUuid" />
								<!-- 隐藏重要属性 -->
								<input type="hidden" id="recVer1" name="recVer" />
							</td>
						</tr>
						<tr>
						<td align="right">
								<font color="red">*&nbsp;</font>菜单代码：
							</td>
							<td>
								<input type="text" id="menuItemCode1" name="menuItemCode" missingMessage="请输入至少4位" required="true" class="easyui-validatebox" maxlength="50"  style="TEXT-TRANSFORM:uppercase;ime-mode:disabled" onchange="getYanZhenUserCodeFunction(this.id,this.value)"/>
							</td>
							<td align="right">
								<font color="red">*&nbsp;</font>菜单中文名：
							</td>
							<td>
								<input type="text" id="menuItemName1" name="menuItemName"
									required="true" class="easyui-validatebox" maxlength="150"/>
							</td>
							
						</tr>
						<tr>
							<td align="right">
								菜单英文名：
							</td>
							<td>
								<input type="text" id="menuItemNameCn1" name="menuItemNameCn" class="easyui-validatebox" maxlength="150" style="ime-mode:disabled" onchange="getYanZhenUserEn(this.id,this.value)"/>
							</td>
							<td align="right">
								菜单排序号：
							</td>
							<td>
								<input type="text" id="menuItemSeq1" name="menuItemSeq" maxlength="38"/>
							</td>
						</tr>
						<tr>
						<td align="right">
								状态：
							</td>
							<td>
								<select id="status1" panelHeight="auto" class="easyui-combobox" name="status" style="width: 150px" editable="false">
									<option value="<%=CommonUtil.Active%>" selected="selected">有效</option>
									<option value="<%=CommonUtil.Pending%>" >草稿</option>
									<option value="<%=CommonUtil.Cancel%>">作废</option>
								</select>
							</td>
							
							<td align="right">
								是否对话框：
							</td>
							<td>
								<select id="isDialog1"  panelHeight="auto"  class="easyui-combobox" name="isDialog" style="width: 150px">
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</td>
						</tr>
						<!-- 分割线 -->
						<tr>
							<td colspan="4">
								<hr />
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*&nbsp;</font>上级菜单：
							</td>
							<td colspan="3">
								<input id="sysMenuGroupUuid1" name="sysMenuGroupUuid"
									class="easyui-combotree" style="width: 430px" required="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								公司(仓库)：
							</td>
							<td colspan="3">
								<select id="officeCode1" panelHeight="auto" class="easyui-combobox" name="officeCode" style="width: 430px">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*&nbsp;</font>菜单链接：
							</td>
							<td colspan="3">
								<input type="text" size="60" id="menuItemUrl1"
									name="menuItemUrl" class="easyui-validatebox" required="true"    validType="length[4,500]"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								备注：
							</td>
							<td colspan="3">
								<input type="text" size="60" id="remark1" name="remark" maxlength="150"></input>
							</td>
						</tr>
						<tr>
							<td align="right">
								类名：
							</td>
							<td colspan="3">
								<input type="text" size="60" id="className1" name="className"
									class="easyui-validatebox"  maxlength="1000"></input>
							</td>
						</tr>
						<tr>
							<td align="right">
								功能提交权限：
							</td>
							<td colspan="3">
							<!-- 
								<input type="text" size="60" id="menuItemAction1"
									name="menuItemAction" maxlength="1000"></input>
							 -->	
								<textarea name="menuItemAction" id="menuItemAction1" style= "font-size:9pt; " rows="4" cols="55"  maxlength="1000"></textarea>
								
							</td>
						</tr>
						<!-- 分割线 -->
						<tr>
							<td colspan="4">
								<hr />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<a href="#" id="saveDivButton"  class="easyui-linkbutton" name=""
									onclick="provinguser()">保存</a>
								<!-- <a href="#" class="easyui-linkbutton"
									onclick="cleardate('MenuitemForm')">清空</a> -->
								<a href="#" class="easyui-linkbutton" id="closeDivButton"
									onclick="closewicket('Menuitem')">关闭</a>
							</td>
						</tr>

					</table>
				</div>
			</form>
		</div>
		</div>
	</body>
</html>