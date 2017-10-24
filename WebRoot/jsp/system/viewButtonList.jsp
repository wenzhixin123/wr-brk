<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%
	String path = request.getContextPath();
SessionContextUserEntity scue = SessionContextUserEntity.currentUser(); 
%>
<html>
	<head>
		<!-- jquery easyui -->
		<jsp:include page="../../common/imp_dwr.jsp"></jsp:include>
		<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
		<style type="text/css">
#tableQueryForm td {
	font-size: 12px;
}

body {
	background: white;
}
</style>
 <!-- 按钮管理 2012年2月28日 19:10:06-->
	</head>
	<script type="text/javascript">
		var lastIndex;
		$(function(){
			$('#viewButton').dialog();//初始化添加窗口
			$('#viewButton').dialog('close');//关闭dialog
			__menuitem_treeManger();//初始化菜单的树形结构
			__accreditmanageManger();//初始化按钮管理表格
			customerCodeWholeArrayManger();//获取组织机构
		});
		//定义全局变量
		var customerCodeWholeArray=new Array();
	</script>
	<script type="text/javascript">
		function __menuitem_treeManger(){//getModuleGroupMenuitemTree（uuid树）getModuleGroupOfficeNameTree（OfficeCode树）getModuleGroupMenuitemTree1 (多层树)
		var isoUrl="";
				if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getModuleGroupMenuitemTree1&order=desc&filter=status&q=<%=CommonUtil.Active%>&date="+new Date();
				}else{
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getModuleGroupMenuitemUserTree1&order=desc&filter=status&q=<%=CommonUtil.Active%>&date="+new Date();
				}
			$("#menuitem_tree").tree({//取出菜单的树形结构
				//url:"<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getModuleGroupMenuitemUserTree1&sort=createTime&order=desc&date="+new Date(),
				//checkbox:true,
				url:isoUrl,
				onClick:function(node){
					setCheckedOfficetree(node,'menuitem_tree');
					menuOrButton(node);
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					$('#menuitem_tree').tree('select', node.target);
					//$('#mm').menu('show', {
					//	left: e.pageX,
					//	top: e.pageY
					//});
				},
				onLoadSuccess:function(){
					collapseAll();
				}
			});
			setTimeout(function(){
				collapseAll();
			},1000);
		}
		function __accreditmanageManger(){ //初始化按钮管理表格
			var isoUrl="";
			//	if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType=SysViewButtonModel";
			//	}else{
			//		isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType=SysViewButtonModel&oc=true";
			//	}
		
			$('#accreditmanage').datagrid({
				height:420,
				pagination:true,
				rownumbers:true,
				striped:true,
				singleSelect: true,
				pageSize:20, //每页显示记录数
				pageList:[10,20,30,40,50], //可调整每页显示的记录数
				url:isoUrl,
				idField:'sysViewButtonUuid,sysMenuItemUuid',
				toolbar:[{
					text:'新增',//新增按钮
					iconCls:'icon-add',
					handler:function(){
						viewButtonNew();
					}
				},'-',{
					text:'编辑',//修改按钮selectviewButton
					iconCls:'icon-edit',
					handler:function(){
						viewButtonEdit();
					}
				},'-',{
					text:'删除',//删除按钮
					iconCls:'myCustomerIcon_remove',
					handler:function(){
						viewButtonDelete();
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
					$('#accreditmanage').datagrid('endEdit', lastIndex);
					$('#accreditmanage').datagrid('beginEdit', rowIndex);
					lastIndex = rowIndex;
					
				},
				//按钮点击
				onClickRow:function(rowIndex,rowData){
					$('#accreditmanage').datagrid('endEdit',lastIndex);
					//var nodes=$("#accreditmanage").datagrid('getSelected');
					//alert(rowData.sysViewButtonUuid);
					if(rowData.status=="<%=CommonUtil.Active%>"||rowData.status=="<%=CommonUtil.Cancel%>"){
			    		__getdOe('d');
			    	}else{
			    		/*SysRoleMenuItemManager.getValidationMenuItemAssociation(row.sysMenuItemUuid,function(data){
							if(data=="true"){
								__getdOe('d');
							}else{
								__getdOe("e");
							}
						});*/
					}
				}
			});
		}
		//修改状态。需要传送的值不为空必须为状态
		function userdatagirdStatus(status){
			var row = $('#accreditmanage').datagrid('getSelected');
			if(row==null){
				alert('请先选择一行数据再进行操作！');
			}else{
				SysUserManager.updateSysStatusOue(row.sysViewButtonUuid,status,"SYS_VIEW_BUTTON","STATUS","SYS_VIEW_BUTTON_UUID",function(data){
					if(data){
						alert("操作成功");
					}else{
						alert("操作失败");
					}
					$('#accreditmanage').datagrid("reload");//根据表格ID刷新页面
				});
			}
		}
		//按钮删除调用的方法
		function viewButtonDelete(){
			var row = $('#accreditmanage').datagrid('getSelected');
			if(row.status=="<%=CommonUtil.Active%>"){
				alert('该数据以生效。请审核后重新操作！');
				__getdOe('d');//毁掉删除按钮
			}else{
				if(row==null){
					alert('请先选择一行数据再进行操作！');
				}else{
					$.messager.confirm('确定框', '确定删除‘'+row.btnName+'’角色信息吗?', function(r){
						if (r){
							delete
							$.ajax({
					   			type: "POST",
							    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=deleteviewButton",
							    data: "viewButtonUuid="+row.sysViewButtonUuid+"",
							    dataType:'json',
							    success: function(date){
							    	if(date==null||date.relust){
							    		alert("操作成功");
							    	}else if(date.error){
							    		alert("操作失败、可能有关联数据未清除！错误信息如下："+date.error);
							    	}else{
							    		alert("操作失败、暂无返回信息。");
							    	}
							    	//reload();
		     						$('#accreditmanage').datagrid("reload");//根据表格ID刷新页面
		   						}
							});
						}
					});
				}
			}
		}
		//按钮编辑
		function viewButtonEdit(){
		getButtonKongZhi("e");
			var row = $('#accreditmanage').datagrid('getSelected');
			if(row==null){
				alert('请先选择一行数据再进行操作！');
			}else{
				$.ajax({
		   			type: "POST",
				    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectviewButton&date="+new Date(),
				    data: "selectviewbuttonid="+row.sysViewButtonUuid+"",
				    dataType:'json',
				    success: function(date){
				    	$('#viewButton').dialog({
							modal:true
						});
						cleardate("viewButtonForm");
						UserItemAccredits();
						adduserget();
				    	
				    	$("#sysViewButtonUuid1").val(date.sysViewButtonUuid);
				    	$("#recVer1").val(date.recVer);
				    	
				    	$("#btnName1").val(date.btnName);
				    	$("#btnNameEn1").val(date.btnNameEn);
				    	$('#officeCode1').combotree('setValue',date.officeCode);//公司仓库
				    	$('#status1').combobox('select',date.status);//状态
				    	$("#btnAction1").val(date.btnAction);
				    	$('#sysMenuItemUuid1').combotree('setValue',date.sysMenuItemUuid);
				    	$("#remark1").val(date.remark);
				    	$("#btnCode1").val(date.btnCode);
				    	$("#btnUrl1").val(date.btnUrl);
				    	if(date.status!=null&&date.status!="" && date.status=='<%=CommonUtil.Active%>'||date.status=='<%=CommonUtil.Cancel%>' ){
				    		$("#btnCode1").attr("readonly","readonly");
				    	}else{
				    		$("#btnCode1").attr("readonly",false);
				    	}
				    	SysOfficeManager.getOfficeOofficeCodeModel(date.officeCode,function(data){
							if(data){
								$('#officeCode1').combotree('setValue',data);//所属菜单项
							}
						});
				    	$("#saveDivButton").attr("name","update");//修改完成按钮数据为修改
	 						}
				});
			}
		}
		//按钮新增
		function viewButtonNew(){
			getButtonKongZhi("e");
			var node = $('#menuitem_tree').tree('getSelected');
			if(node!=null&&node.id!=null){
				var isleaf=$('.tree').tree('isLeaf',node.target);
				if(!isleaf){
					alert('此节点不是菜单节点、请明确后重新选择！');
					return;
				}
			}
			$('#viewButton').dialog({
				modal:true
			});
			$("#saveDivButton").attr("name","save");//修改完成按钮数据为保存
			UserItemAccredits();
			adduserget();
			cleardate("viewButtonForm");
			SysOfficeManager.getOfficeOofficeCodeModel("<%=scue.getOfficeCode()%>",function(data){
				if(data){
					$('#officeCode1').combotree('setValue',data);//所属菜单项
				}
			});
			$("#btnCode1").attr("readonly",false);
			if(node!=null&&node.id!=null){
				$('#sysMenuItemUuid1').combotree('setValue',node.id);//所属菜单项
				//$('#sysMenuItemUuid1').combotree('setText',node.text);//所属菜单项
				//$('#sysMenuItemUuid1').combotree('disable');
			}
			$("#status1").combobox("select","<%=CommonUtil.Active%>");
		}
			//取出所有公司(仓库)代码
			function adduserget(){
				$("#officeCode1").combotree({//上级组织
					url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectOfficeCode&date='+new Date(),
					onClick:function(node){
					}	
				});
			}
			//根据菜单查询出所属权限按钮
			function menuOrButton(node){
				if(node.id==null){
					collapse();
				}else{
					$("#accreditmanage").datagrid('load',{
			             __sysMenuItemUuid:node.id
			       	});
			    }
			}
			//重置按钮事件
			function cleardate(s){
				$('#'+s).form('clear');
			}
			//关闭对话框窗口(传入窗口id)
			function closewicket(c){
				$("#"+c).dialog('close');
			}
			//取出所属菜单项树形
			function UserItemAccredits(){
			var isoUrl="";
				if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getModuleGroupMenuitemTree1&order=desc&filter=status&q=<%=CommonUtil.Active%>&date="+new Date();
				}else{
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getModuleGroupMenuitemUserTree1&order=desc&filter=status&q=<%=CommonUtil.Active%>&date="+new Date();
				}
				$("#sysMenuItemUuid1").combotree({
				//	url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getModuleGroupMenuitemUserTree1',
					url:isoUrl,
					onClick:function(node){
						var isleaf=$('.tree').tree('isLeaf',node.target);
						if(!isleaf){
							alert('此节点不是菜单节点、请明确后重新选择！');
							$('#sysMenuItemUuid1').combotree('clear');
						}
					}
				});
			}
			//查询按钮事件
			function viewButtonselect(){
				//alert('查询');
				var btnName=$("#btnName").val();
				var btnNameEn=$("#btnNameEn").val();
				var createTime_s=$("#createTime__start").datebox("getValue");
				var createTime_e=$("#createTime__end").datebox("getValue");
				var condition = $("#__condition").val();
				$("#accreditmanage").datagrid('load',{
		                	__btnName:btnName,
		                	__btnNameEn:btnNameEn,
		                	createTime__start:createTime_s,
		                	createTime__end:createTime_e,
		                	__condition:condition
		       	});
			}
			//折叠所有节点
			function zhediesuoyoujiedian(){
				var node = $('.tree').tree('getSelected');
				if (node){
					$('.tree').tree('collapseAll', node.target);
				} else {
					$('.tree').tree('collapseAll');
				}
			}
			//提交表单新增数据库数据和刷新页面
			function saveviewbutton(){
			getButtonKongZhi("d");
				var validateable;
				//验证表单是否通过
				validateable=$('#logisticsOrderForm').form('validate'); //控件验证信息
				if(validateable==false){
					alert("*必填数据为空！请审核后重新操作！");
					getButtonKongZhi("e");
					return;
				}else{
					var viewbuttonjsonResults=$("#viewButtonForm").serializeArray(); //得到数组json对象
					var saveuerjudge=$("#saveDivButton").attr("name");
					var rolejson;
					rolejson="{";
					$.each(viewbuttonjsonResults,function(k,v){
						if(k==viewbuttonjsonResults.length-1){
							if(v.name=="btnAction"){
									rolejson=rolejson+'"'+v.name +'"'+':'+'"'+ v.value.replace(/\r\n/g, "\\n")+'"';
							}else{
									rolejson=rolejson+'"'+v.name +'"'+':'+'"'+ v.value+'"';
							}
						}else{
							if(v.name=="btnAction"){
								rolejson=rolejson+'"'+v.name +'"'+':'+'"'+ v.value.replace(/\r\n/g, "\\n")+'"'+",";
							}else{
								rolejson=rolejson+'"'+v.name +'"'+':'+'"'+ v.value+'"'+",";
							}
						}
					});
					rolejson=rolejson+"}";
					//alert(rolejson);
					//alert(saveuerjudge);
					if(saveuerjudge=="save"){
					LogisticsOrderManager.object2base64(rolejson,function(rolejson1){
						if(rolejson1){
						$.ajax({
				   			type: "POST",
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=savaviewButton&date="+new Date(),
						    data: "viewButtonjson="+rolejson1+"",
						    dataType:'json',
						    success: function(date){
						    	if(date==null||date.relust){
						    		alert("操作成功");
							    	$('#viewButtonForm').form('clear');//重置用户填写窗口的信息
							    	closewicket('viewButton');//关闭用户信息填写窗口
							    	$('#accreditmanage').datagrid("reload");//根据表格ID刷新页面
						    	}else if(date.error){
						    		alert("操作失败！错误信息如下："+date.error);
						    		getButtonKongZhi("e");
						    	}else{
						    		alert("操作失败、暂无返回信息。");
						    		getButtonKongZhi("e");
						    	}
		   					}
						});
						}
					});
					}else if(saveuerjudge=="update"){
					LogisticsOrderManager.object2base64(rolejson,function(rolejson1){
						if(rolejson1){
						//alert($("#sysViewButtonUuid1").val()+" : "+$("#recVer1").val());
						$.ajax({
				   			type: "POST",
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=updateviewButton&date="+new Date(),
						    data: "viewButtonjson="+rolejson1+"",
						    dataType:'json',
						    success: function(date){
						    	if(date==null||date.relust){
						    		alert("操作成功");
							    	$('#viewButtonForm').form('clear');//重置用户填写窗口的信息
							    	closewicket('viewButton');//关闭用户信息填写窗口
							    	$('#accreditmanage').datagrid("reload");//根据表格ID刷新页面
						    	}else if(date.error){
						    		alert("操作失败！错误信息如下："+date.error);
						    		getButtonKongZhi("e");
						    	}else{
						    		alert("操作失败、暂无返回信息。");
						    		getButtonKongZhi("e");
						    	}
		   					}
						});
						}
					});
					}
				}
			}
		//添加节点为选中
		function setCheckedOfficetree(node,treeid){
			$('#'+treeid).tree('check',node.target);
		}
			//展开树形
		function expand(){
			var node = $('#menuitem_tree').tree('getSelected');
			$('#menuitem_tree').tree('expand',node.target);
		}
		// 关闭树形
		function collapse(){
			var node = $('#menuitem_tree').tree('getSelected');
			$('#menuitem_tree').tree('collapse',node.target);
		}
		//在树形中增加部门机构
		function append(){
			var node = $('#menuitem_tree').tree('getSelected');
			$('#viewButton').dialog({
				modal:true
			});
			$("#saveDivButton").attr("name","save");//修改完成按钮数据为保存
			UserItemAccredits();
			cleardate("viewButtonForm");
			//$('#sysMenuItemUuid1').combotree('setValue',node.id);//所属菜单项
			//$('#sysMenuItemUuid1').combotree('disable');
		}
		//重载
		function reload(){
			$('#menuitem_tree').tree('reload');
		}
		//检测是否是跟节点（新增）
		function detectionNode(){
			var node = $('#menuitem_tree').tree('getSelected');
			var nsode = $('#menuitem_tree').tree('getParent',node.target);
			if(nsode==null){
				alert('不能为菜单分组添加按钮权限！');
			}else{
				append();
			}
		}
		//关闭全部
		function collapseAll(){
			$('#menuitem_tree').tree('collapseAll');
		}
		//展开全部
		function expandAll(){
			$('#menuitem_tree').tree('expandAll');
		}
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
					SysViewButtonManager.getYanZhenUserCode(valueUser,function(boole){
						if(boole){
							$("#"+textid).val(null);
							alert("该编码已存在、请审核后重新填写！");
						}
					});
				}
			}
		}
		//按钮控制
		function __getdOe(dOe){
			if(dOe!=null&&dOe=="d"){
	    		$("#deleteMenuItem").linkbutton("disable").unbind("click");
	    	}else if(dOe!=null&&dOe=="e"){
				$("#deleteMenuItem").linkbutton("enable");
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
	function getYanZhenUserEn(textid,valueUser){
			valueUser=trim(valueUser);//转换大写并且去掉前后空格
			$("#"+textid).val(valueUser);
			if(/[^\x00-\xff]/g.test(valueUser)){
			 	alert( "含有汉字不能保存");
			 	$("#"+textid).val(null);
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
		//取出组织类型数据保存到全局变量officeTypeWholeArray的数组中。查询全部office  UseritmecustomerCode
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
					 	//	alert($.toJSON(customerCodeWholeArray));
					   	}
					});
				}
			}
	</script>
	<body class="easyui-layout">
		<div region="center" style="width: 600px;">
			<div class="easyui-layout" fit="true">
				<div id="viewButtonTerm" region="north" border="false">
					<form id="VieWButtonForm">
						<div style="background: #efefef; width: 100%; float: left">
							<a href="#" class="easyui-linkbutton" plain="true"
								iconCls="icon-search" style="float: left" onclick="viewButtonselect();">查询</a>
							<div class="datagrid-btn-separator"></div>
	
							<!-- 这是分割线 -->
							<a href="#" class="easyui-linkbutton" plain="true"
								iconCls="myCustomerIcon_clear" style="float: left"
								onclick="cleardate('VieWButtonForm')">重置</a>
						</div>
						<!-- legend>表单验证</legend -->
						<table id="tableQueryForm" style="width: 95%; float: left">
							<tr align="right">
								<td width="15%" align="right">
									<label>
										按钮名:
									</label>
								</td>
								<td width="35%" align="left">
									<input id="btnName" name="btnName" size="25"
										class="easyui-validatebox" />
									<input type="hidden" id="__condition" name="__condition"
										value="btnName_ilikeAnywhere:createTime_between" />
								</td>
								<td width="15%" align="right">
									<label>
										按钮英文名:
									</label>
								</td>
								<td width="35%" align="left">
									<input id="btnNameEn" name="btnNameEn" size="25"  />
								</td>
							</tr>
							<tr align="right">
								<td width="15%" align="right">
									<label>
										创建日期:
									</label>
								</td>
								<td width="35%" align="left">
									<input id="createTime__start" name="createTime__start"
										class="easyui-datebox easyui-validatebox" validType="dateFormat" style="width: 80px;" />
									<img src="<%=path%>/images/right.gif" />
									<input id="createTime__end" name="createTime__end"
										class="easyui-datebox easyui-validatebox" validType="dateFormat" style="width: 80px;" />
								</td>
								<td width="15%" align="right">
									<label>
										
									</label>
								</td>
								<td width="35%" align="left">
									
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div region="center" border="false" title="按钮管理">
					<table id="accreditmanage" iconCls="icon-edit"
						singleSelect="true" idField="itemid" fit="true">
						<thead>
							<tr>
								<th field="status" width="60" align="center" formatter="statusOOr">
									状态
								</th>
								<th field="btnCode" width="200" align="center" td_align="left">
									按钮代码
								</th>
								<th field="officeCode" width="150" align="center" td_align="left" formatter="customerCodeHuoQu" >
								        所属公司
								</th>
								<th field="btnName" width="150" align="center" td_align="left">
									按钮名
								</th>
								<th field="btnNameEn" width="150" align="center" td_align="left">
									按钮英文名
								</th>
								<th field="btnUrl" width="200" align="center" td_align="left">
									按钮链接
								</th>
								<!-- <th field="btnAction" width="60" align="center">
									按钮提交权限
								</th>
								<th field="status" width="40" align="center">
									状态
								</th> -->
							</tr>
						</thead>
					</table>
			</div>
			</div>
		</div>
		<!-- 菜单树形结构 -->
		<div region="west" title="菜单列表"
			style="background: #efefef; width: 200px; float: left;">
			<form id="viewqueryForm">
				<div id="viewqueryTerm" class="easyui-panel" collapsible="true">
					<a class="easyui-linkbutton" plain="true"
						iconCls="accordion-collapse" style="float: left;" onclick="expandAll()">展开</a>
					<a class="easyui-linkbutton" plain="true"
						iconCls="accordion-expand" style="float: left;" onclick="collapseAll()">关闭</a>
				</div>
				<div region="center">
					<ul id="menuitem_tree" class="easyui-tree">
					</ul>
				</div>
			</form>
		</div>

		<!-- 弹出框 -->
		<div id="viewButton"
			style="padding: 5px; width: 670px; height: 350px;" title="权限按钮信息">
			<form name="viewButtonForm" id="viewButtonForm">
				<div>
					<table id="tableQueryForm" style="width: 100%;">
						<!-- 分割线 -->
						<tr>
							<td colspan="4">
								<hr/>
								<input type="hidden" id="sysViewButtonUuid1"
									name="sysViewButtonUuid" />
								<!-- 隐藏重要属性 -->
								<input type="hidden" id="recVer1" name="recVer" />
							</td>
						</tr>
						<tr>
						<td align="right">
							<font color="red">*&nbsp;</font>按钮代码：
							</td>
							<td >
								<input type="text" id="btnCode1" size="26" name="btnCode"
									class="easyui-validatebox" required="true" style="TEXT-TRANSFORM:uppercase;ime-mode:disabled" onchange="getYanZhenUserCodeFunction(this.id,this.value)"/>
							</td>
							<td align="right">
								<font color="red">*&nbsp;</font>按钮名：
							</td>
							<td>
								<input type="text" id="btnName1" size="26" name="btnName"
									class="easyui-validatebox" required="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								按钮英文名：
							</td>
							<td>
								<input type="text" id="btnNameEn1" size="26" name="btnNameEn" style="ime-mode:disabled" onchange="getYanZhenUserEn(this.id,this.value)"/>
							</td>
							<td align="right">
								<font color="red">*&nbsp;</font>公司(仓库)：
							</td>
							<td>
								<select id="officeCode1" panelHeight="auto"  name="officeCode" style="width: 195px;" required="true" disabled="disabled">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								状态：
							</td>
							<td>
								<select id="status1" class="easyui-combobox"  panelHeight="auto"  name="status" style="width: 195px;" editable="false">
									<option value="<%=CommonUtil.Active%>" selected="selected">有效</option>
									<option value="<%=CommonUtil.Pending%>" >草稿</option>
									<option value="<%=CommonUtil.Cancel%>">作废</option>
								</select>
							</td>
							
						</tr>
						<tr>
							<td align="right">
								<font color="red">*&nbsp;</font>所属菜单项：
							</td>
							<td colspan="3">
								<input id="sysMenuItemUuid1" name="sysMenuItemUuid"
									class="easyui-combotree" style="width: 495px;"
									onclick="zhediesuoyoujiedian()" class="easyui-validatebox"
									required="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								按钮链接：
							</td>
							<td colspan="3">
								<input type="text" size="69" id="btnUrl1" name="btnUrl"
									class="easyui-validatebox" />
							</td>
						</tr>
						<tr>
							<td align="right">
								备注：
							</td>
							<td colspan="3">
								<input type="text" id="remark1" size="69" name="remark" />
							</td>
						</tr>
						<tr>
						<td align="right">
								按钮提交权限：
							</td>
							<td colspan="3">
							<!--  	<input type="text" id="btnAction1" size="26" name="btnAction" />-->
								<textarea name="btnAction" id="btnAction1" style= "font-size:9pt; " rows="3" cols="58"  maxlength="1000"></textarea>
							</td>
						</tr>
						
						<!-- 分割线 -->
						<tr>
							<td colspan="4">
								<hr/>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<a href="#" id="saveDivButton" class="easyui-linkbutton" name="saveDivButton"
									onclick="saveviewbutton()">保存</a>
								<a href="#" class="easyui-linkbutton" id="closeDivButton"
									onclick="closewicket('viewButton')">关闭</a>
							</td>
						</tr>

					</table>
				</div>
			</form>
		</div>
		<!-- 鼠标右键增删 -->
		<div id="mm" class="easyui-menu" style="width: 120px;">
			<div onclick="detectionNode()" iconCls="icon-add">
				新增按钮
			</div>
			<div onclick="reload()" iconCls="icon-reload">
				重载数据
			</div>
			<div class="menu-sep"></div>
			<div onclick="expand()">
				展开节点
			</div>
			<div onclick="collapse()">
				关闭节点
			</div>
		</div>
	</body>
</html>