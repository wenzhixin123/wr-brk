<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<%
	String path = request.getContextPath();
SessionContextUserEntity scue = SessionContextUserEntity
.currentUser(); 
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
		<!-- 角色管理页面 2012年2月28日 19:09:55-->
		<script>
			var lastIndex;
			var node=null;
			var menuarrayall=new Array();
			$(function(){
				__menuitem_treeManger();//获取所有菜单的树形结构
				__roledatagirdManger();//初始化角色信息管理列表以及获取数据
				//loadTree();
			});
			
			
			function __menuitem_treeManger(){
			var isoUrl="";
				if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getModuleGroupMenuitemTree1&filter=status&q=<%=CommonUtil.Active%>&date="+new Date();
				}else{
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getModuleGroupMenuitemUserTree1&filter=status&q=<%=CommonUtil.Active%>&date="+new Date();
				}
				$("#menuitem_tree").tree({//取出菜单的树形结构getModuleGroupMenuitemTree1
					url:isoUrl,
					checkbox:true,
					collapseAll:true,//折叠所有的节点
					onClick:function(node){
						setCheckedOfficetree(node,'menuitem_tree');
					},
					onLoadSuccess:function(){
						collapseAll();
					}
				});
			}
			var isoUrl="";
				if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType=SysRoleModel";
				}else{
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=roldeAdmin";
				}
			function __roledatagirdManger(){
				$("#menuitem_tree").tree('collapseAll');
				$('#roleItem').dialog();//初始化添加窗口
				$('#roleItem').dialog('close');//关闭dialog
				$('#roledatagird').datagrid({
					height:452,
					pagination:true,
					rownumbers:true,
					striped:true,
					url:isoUrl,
					singleSelect: true,
					pageSize:20, //每页显示记录数
					pageList:[10,20,30,40,50], //可调整每页显示的记录数
					//singleSelect: false,  // 单选或多选
					idField:'sysRoleUuid,officeCode',
					sortName:'roleName',
					sortOrder:'asc',
					toolbar:[{
						text:'新增',
						iconCls:'icon-add',
						handler:function(){
							roleItemNew();
						}
					},'-',{
						text:'编辑',
						iconCls:'icon-edit',
						handler:function(){
							roleItemEdit();
						}
					},'-',{
						text:'删除',
						id:'deleteRoleitem',
						iconCls:'myCustomerIcon_remove',
						handler:function(){
							roleItemDelete();
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
						$('#roledatagird').datagrid('endEdit', lastIndex);
						$('#roledatagird').datagrid('beginEdit', rowIndex);
						lastIndex = rowIndex;
					},
					onClickRow:function(rowIndex,rowData){//角色表格数据点击事件
						$('#roledatagird').datagrid('endEdit',lastIndex);
						if(rowData.status=="<%=CommonUtil.Active%>"||rowData.status=="<%=CommonUtil.Cancel%>"){
							__getdOe("d");
						}else{
							SysRoleManager.getRoleValidation(rowData.sysRoleUuid,function(date){
								if(date=="true"){
									__getdOe("d");
								}else{
									__getdOe("e");
								}
							});
						}
						rolemenuButton();
					}
				});
			}
			//修改状态。需要传送的值不为空必须为状态
			function userdatagirdStatus(status){
				var row = $('#roledatagird').datagrid('getSelected');
				if(row==null){
					alert('请先选择一行数据再进行操作！');
				}else{
					SysUserManager.updateSysStatusOue(row.sysRoleUuid,status,"SYS_ROLE","STATUS","SYS_ROLE_UUID",function(data){
						if(data){
							alert("操作成功");
						}else{
							alert("操作失败");
						}
						$('#roledatagird').datagrid("reload");//根据表格ID刷新页面
					});
				}
			}
			//角色删除
			function roleItemDelete(){
				var row = $('#roledatagird').datagrid('getSelected');
				if(row==null){
					alert('请先选择一行数据再进行操作！');
				}else{
					var index =$('#roledatagird').datagrid('getRowIndex', row);
					$.messager.confirm('确定框', '确定删除‘'+row.roleName+'’角色信息吗?', function(r){
						if (r){
							//$('#roledatagird').datagrid('deleteRow', index);
							//delete
							$.ajax({
					   			type: "POST",
							    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=deleteroleitem",
							    data: "roleitemjson="+row.sysRoleUuid+"",
							   	dataType:'json',
							    success: function(date){
							    	if(date==null||date.relust){
							    		alert("操作成功");
							    		qingkongsuoyoushuxing();
							    	}else if(date.error){
							    		alert("操作失败、可能有关联数据未清除！错误信息如下："+date.error);
							    	}else{
							    		alert("操作失败、暂无返回信息。");
							    	}
		     						$('#roledatagird').datagrid("reload");//根据表格ID刷新页面
		     						__getdOe("d");//刷新表格后把删除按钮屏蔽掉
		   						}
							});
						}
					});	
				}
			}
			//角色编辑
			function roleItemEdit(){
				getButtonKongZhi("e");
				var row = $('#roledatagird').datagrid('getSelected');
				if(row==null){
					alert('请先选择一行数据再进行操作!');
				}else{
					$.ajax({
		   			type: "POST",
				    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectroleitemid&date="+new Date(),
				    data: "selectroleitem="+row.sysRoleUuid+"",
				    dataType:'json',
				    success: function(date){
				    	$('#roleItem').dialog({
							modal:true
						});
						cleardate('roleitemForm');//重置窗口信息
				    	adduserget();//取出公司仓库树形信息
				    	$("#recVer1").val(date.recVer);//修改的必须条件（版本控制）
				    	$("#sysRoleUuid1").val(date.sysRoleUuid);//修改的必须条件（uuid）
				    	$("#roleName1").val(date.roleName);
				    	$("#isVirtualFlag1").combobox('setValue',date.isVirtualFlag);
				    	$("#roleType1").combobox('setValue',date.roleType);
				    	$("#roleNameEn1").val(date.roleNameEn);
				    	$("#deletedFlag1").combobox('setValue',date.deletedFlag);
				    	$("#canQueryallFlag1").combobox('setValue',date.canQueryallFlag);
				    	$("#expiredDate1").datebox("setValue",date.expiredDate);
				    	$("#activeDate1").datebox("setValue",date.activeDate);
				    	$('#status1').combobox('setValue',date.status);//状态
				    	$("#roleCode1").val(date.roleCode);
				    	$("#officeCode1").combotree('setValue',date.officeCode);//OfficeCode公司（仓库）
				    	$("#remark1").val(date.remark);
				    	if(date.status!=null&&date.status!="" && date.status=='<%=CommonUtil.Active%>'||date.status=='<%=CommonUtil.Cancel%>' ){
				    		$("#roleCode1").attr("readonly","readonly");
				    	}else{
				    		$("#roleCode1").attr("readonly",false);
				    	}
				    	$("#saveDivButton").attr("name","updaterole");//修改完成按钮数据为修改
	 						}
				});
				}
			}
			//新增角色事件方法
			function roleItemNew(){
			getButtonKongZhi("e");
				$('#roleItem').dialog({
					modal:true
				});
				$("#roleCode1").attr("readonly",false);
				$("#saveDivButton").attr("name","saveDivButton");//修改完成按钮数据为保存
				//$("#officeCode1")
				adduserget();
				cleardate('roleitemForm');
				$("#status1").combobox("select","<%=CommonUtil.Active%>");
			}
			//添加节点为选中
			function setCheckedOfficetree(node,treeid){
				$('#'+treeid).tree('check',node.target);
			}
			//取菜单树形中所有数据
			function getChildrensss(){
				if(menuarrayall.length==0){
					//alert("node为空"+menuarrayall.length);
					var children=new Array();
					//var node = $('#menuitem_tree').tree('getSelected');
					//if (node){
						//children = $('#menuitem_tree').tree('getChildren', node.target);
					//} else {
						children = $('#menuitem_tree').tree('getChildren');
					//}
					for(var i=0; i<children.length; i++){
						var b = $('#menuitem_tree').tree('isLeaf', children[i].target);
						if(b){
						  menuarrayall.push(children[i]);
						}
					}
					//alert($.toJSON(menuarrayall));
				}
			}	
			//获取某角色所拥有的角色和权限 
			function rolemenuButton(){
				var noderole=$('#roledatagird').datagrid('getSelected');//获取roledatagird表单中被选中的一条数据
				$.ajax({
		   			type: "POST",
				    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectMenuitemall&date="+new Date(),
				    data: "selectroleid="+noderole.sysRoleUuid+"",
				    success: function(date){
				    	var menujson=eval ("(" + date + ")");
						getChildrensss();
						qingkongsuoyoushuxing();
						//alert(menuarrayall.length);
						//alert($.toJSON(menujson));
				    	$.each(menujson,function(k,obj){//所有输入该角色的菜单k为58
				  			$.each(menuarrayall,function(uuid,menuarrayone){
				  				if(obj.sysMenuItemUuid==menuarrayone.id){
				  					$('#menuitem_tree').tree('check',menuarrayone.target);
				  				}else{
				  					//$('#menuitem_tree').tree('uncheck',menuarrayone.target);
				  				}
				  			});
					  	});
  						}
				});
			}
			//清空树形
			function qingkongsuoyoushuxing(){
				$.each(menuarrayall,function(uuid,menuarrayone){
	  				$('#menuitem_tree').tree('uncheck',menuarrayone.target);
	  			});
			}
		</script>
		<script type="text/javascript">
			//重置按钮事件
			function cleardate(s){
				$('#'+s).form('clear');
				$("#status").combobox("select","");//设定初值
				chushihua();//初始化下拉框不能输入值
			}
			//关闭对话框窗口(传入窗口id)
			function closewicket(c){
				$("#"+c).dialog('close');
			}
			//查询按钮事件
			function userselect(){
				var roleName=$("#roleName").val();
				var roleNameEn=$("#roleNameEn").val();
				var roleType=$("#roleType").val();
				var status=$("#status").combobox('getValue');
		//		var createTime_s=$("#createTime__start").datebox("getValue");
		//		var createTime_e=$("#createTime__end").datebox("getValue");
				var condition = $("#__condition").val();
				//alert(userName+' = '+userType+' = '+creator+' = '+idCard);
				$("#roledatagird").datagrid('load',{
		                	__roleName:roleName,
		                	__roleNameEn:roleNameEn,
		                	__roleType:roleType,
		                	__status:status,
		            //    	createTime__start:createTime_s,
		             //   	createTime__end:createTime_e,
		                	__condition:condition
		                });
			}
			//提交表单新增数据库数据和刷新页面
			function saveuser(){
				var rolejsonResults=$("#roleitemForm").serializeArray(); //得到数组json对象
				var saveuerjudge=$("#saveDivButton").attr("name");
				var rolejson;
					rolejson="{";
					$.each(rolejsonResults,function(k,v){
						if(v.name!='officeCod'){
							if(k==rolejsonResults.length-1){
								rolejson=rolejson+'"'+v.name +'"'+':'+'"'+ v.value+'"';
							}else{
								rolejson=rolejson+'"'+v.name +'"'+':'+'"'+ v.value+'"'+",";
							}
						}else{
							alert(v.name+" : "+ v.value);
						}
					});
					rolejson=rolejson+"}";
					//alert(rolejson);
					//alert(saveuerjudge);
					//alert($.toJSON($("#officeCode1").combotree()));
					if(saveuerjudge=="saveDivButton"){
					LogisticsOrderManager.object2base64(rolejson,function(rolejson1){
						if(rolejson1){
						$.ajax({
				   			type: "POST",
				   			dataType:'json',
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=savarole&date="+new Date(),
						    data: "rolejson="+rolejson1+"",
						    success: function(date){
						    	if(date==null||date.relust){
						    		alert("操作成功");
							    	$('#roleitemForm').form('clear');//重置用户填写窗口的信息
							    	$('#roleItem').dialog('close');//关闭用户信息填写窗口
							    	$('#roledatagird').datagrid("reload");//根据表格ID刷新页面
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
					}else if(saveuerjudge=="updaterole"){
					LogisticsOrderManager.object2base64(rolejson,function(rolejson1){
						if(rolejson1){
						$.ajax({
				   			type: "POST",
				   			dataType:'json',
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=updaterole&date="+new Date(),
						    data: "rolejson="+rolejson1+"",
						    success: function(date){
						    	if(date==null||date.relust){
						    		alert("操作成功");
							    	$('#roleitemForm').form('clear');//重置用户填写窗口的信息
							    	$('#roleItem').dialog('close');//关闭用户信息填写窗口
							    	$('#roledatagird').datagrid("reload");//根据表格ID刷新页面
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
			function provinguser(){
				getButtonKongZhi("D");
				var roleName1=$("#roleName1").val();//角色中文名
				//var activeDate1=$("#activeDate1").val();//生效日期
				var roleCode1=$("#roleCode1").val();//角色代码
				if(roleName1.length>=1&&isNaN(roleName1)){
					//if(activeDate1.length>0){
						if(roleCode1.length>2&&isNaN(roleCode1)){
							saveuser();
						}else{
							$("#roleCode1").focus();
							alert('角色代码无效、请核对后重新输入！'); 
							getButtonKongZhi("e");
						}
					//}else{
					//	$("#activeDate1").focus();
					//	alert('生效日期不能为空、请核对后重新输入！'); 
					//}
				}else{
					$("#roleName1").focus();
					alert('角色中文名不能为空或者数字、请核对后重新输入！'); 
					getButtonKongZhi("e");
				}
			}
			//按钮控制
			function __getdOe(dOe){
				if(dOe!=null&&dOe=="d"){
		    		$("#deleteRoleitem").linkbutton("disable").unbind("click");
		    	}else if(dOe!=null&&dOe=="e"){
					$("#deleteRoleitem").linkbutton("enable");
				}
			}
	</script>
		<script type="text/javascript">
			//取出所有公司(仓库)代码
			function adduserget(){
				$("#officeCode1").combotree({//上级组织
					url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectOfficeCode&date='+new Date(),
					onClick:function(node){
						alert(node.id);
					}	
				});
			}
		function saverolemenu(){//保存选择角色与菜单关联信息
			var noderole=$('#roledatagird').datagrid('getSelected');//officeCode,sysRoleUuid两个是保存sys_role_menu_item该表必须的条件
			if(noderole!=null&&noderole.status=="<%=CommonUtil.Active%>"){ //以下是循环出选中的菜单插入sys_role_menu_item该表中
				var arrays=new Array();
				var nodes = $('#menuitem_tree').tree('getChecked');
				var rolemenujsons =new Object();
					for(var i=0; i<nodes.length; i++){
						//alert(nodes[i].id);
						var b = $('#menuitem_tree').tree('isLeaf', nodes[i].target);
						if(b){
							var rolemenujson =new Object();
							rolemenujson.sysRoleUuid=noderole.sysRoleUuid;
							rolemenujson.sysMenuItemUuid=nodes[i].id;
							rolemenujson.officeCode=noderole.officeCode;
							arrays.push(rolemenujson)
						}
					}
				if(nodes==""){
					var rolemenujson =new Object();
					rolemenujson.sysRoleUuid=noderole.sysRoleUuid;
					arrays.push(rolemenujson)
				}
				rolemenujsons=$.toJSON(arrays);
				LogisticsOrderManager.object2base64(rolemenujsons,function(rolemenujsons1){
					if(rolemenujsons1){
						$.ajax({
				   			type: "POST",
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=saveRoleMenuManager&date="+new Date(),
						    data: "rolemenujson="+rolemenujsons1+"",
						    success: function(date){
						    	if(date){
						    		alert('保存成功 , 注：(如果是当前登陆角色)刷新整个页面后可加载新权限!');
						    	}else{
						    		error("保存失败!");
						    	}
		   					}
						});
					}
			});
			}else{
				if(noderole!=null&&noderole.status!="<%=CommonUtil.Active%>"){
					alert("所选中的角色不是有效状态不能给予分配菜单权限。");
				}else{
					alert("请选中一个角色再执行给予分配菜单！");
				}
			}
		}
			//关闭全部
		function collapseAll(){
			var node = $('#menuitem_tree').tree('getSelected');
			if (node){
				$('#menuitem_tree').tree('collapseAll', node.target);
			} else {
				$('#menuitem_tree').tree('collapseAll');
			}
		}
		//展开全部
		function expandAll(){
			var node = $('#menuitem_tree').tree('getSelected');
			if (node){
				$('#menuitem_tree').tree('expandAll', node.target);
			} else {
				$('#menuitem_tree').tree('expandAll');
			}
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
			 	alert("含有汉字不能保存");
			 	$("#"+textid).val(null);
			}else {
				if(valueUser!=null&&valueUser!=""){
					SysRoleManager.getYanZhenUserCode(valueUser,"<%=scue.getOfficeCode()%>",function(boole){
						if(boole){
							$("#"+textid).val(null);
							alert("该编码已存在、请审核后重新填写！");
						}
					});
				}
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
	//验证是英文名称不能输入中文
	function getYanZhenUserEn(textid,valueUser){
			valueUser=trim(valueUser);//转换大写并且去掉前后空格
			$("#"+textid).val(valueUser);
			if(/[^\x00-\xff]/g.test(valueUser)){
			 	alert( "含有汉字不能保存");
			 	$("#"+textid).val(null);
			}
	}
	function chushihua(){
		$('#isVirtualFlag1').combobox({"editable":false});
		$('#roleType1').combobox({"editable":false});
		$('#deletedFlag1').combobox({"editable":false});
		$('#canQueryallFlag1').combobox({"editable":false});
	}
	
	</script>
		<div region="west"
			style="width: 820px; padding1: 1px; overflow: hidden;">
			<div class="easyui-layout" fit="true">
				<div id="rolequeryTerm" region="north" border="false" title="角色查询" style="height:120px;">
					<form id="rolequeryForm">
						<div style="background: #efefef; width: 100%; float: left">
							<a href="#" class="easyui-linkbutton" plain="true"
								iconCls="icon-search" style="float: left" onclick="userselect()">查询</a>
							<div class="datagrid-btn-separator"></div>
	
							<!-- 这是分割线 -->
							<a href="#" class="easyui-linkbutton" plain="true"
								iconCls="myCustomerIcon_clear" style="float: left"
								onclick="cleardate('rolequeryForm')">重置</a>
						</div>
						<!-- legend>表单验证</legend -->
						<table id="tableQueryForm" style="width: 95%; float: left">
							<tr align="right">
								<td width="15%" align="right">
									<label>
										角色中文名:
									</label>
								</td>
								<td width="35%" align="left">
									<input id="roleName" name="roleName" size="25"
										class="easyui-validatebox" />
									<input type="hidden" id="__condition" name="__condition"
										value="roleName_ilikeAnywhere:createTime_between" />
								</td>
								<td width="15%" align="right">
									<label>
										角色英文名称:
									</label>
								</td>
								<td width="35%" align="left">
									<input id="roleNameEn" name="roleNameEn"
										class="easyui-validatebox" size="25"  />
								</td>
							</tr>
							<tr align="right">
								<td width="15%" align="right">
									<label>
										角色类别:
									</label>
								</td>
								<td width="35%" align="left">
									<input id="roleType" name="roleType" size="25" class="url"
										value="" />
								</td>
								  
								<td width="15%" align="right">
									<label>
										状态:
									</label>
									<td width="35%" align="left">
								<select id="status" name="status" class="easyui-combobox" panelHeight="auto"  style="width: 151px;" editable="false">
								<option value="" selected="selected">全部</option>
									<option value="<%=CommonUtil.Active%>" >有效</option>
									<option value="<%=CommonUtil.Pending%>" >草稿</option>
									<option value="<%=CommonUtil.Cancel%>">作废</option>
								</select>
								</td>
								
								<!--  
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
								-->
							</tr>
						</table>
					</form>
				</div>
				<div region="center" border="false">
					<table id="roledatagird" title="角色信息管理" iconCls="icon-edit"
						singleSelect="true" idField="itemid"
						 fit="true">
						<thead>
							<tr>
								<th field="status" width="60" align="center" formatter="statusOOr" sortable=true>
									状态
								</th>
								<th field="roleCode" width="150" align="center" td_align="left" sortable=true>
									角色代码
								</th>
								<th field="roleName" width="150" align="center" td_align="left" sortable=true>
									角色中文名
								</th>
								<th field="roleNameEn" width="150" align="center" td_align="left" sortable=true>
									角色英文名
								</th>
							<!--  
								<th field="activeDate" width="130" align="center" td_align="left">
									生效日期
								</th>
								<th field="expiredDate" width="130" align="center" td_align="left">
									失效日期
								</th>
								-->
								<th field="roleType" width="130" align="center" td_align="left" sortable=true>
									角色类别
								</th>
								<th field="creator" width="120" align="center" td_align="left" sortable=true>
									创建人
								</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>

		<!-- 中 -->
		<div id="rolemenudiv" region="center" title="角色拥有的菜单"
			style="background-color: #efefef;">
			<div class="easyui-layout" fit="true" style="background: #ccc;">
				<div id="viewqueryTerm" region="north"  collapsible="true" style="background-color: #efefef;">
					<!-- 保存按钮 -->
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-save" style="float: left" onclick="saverolemenu()">保存</a>
					<a class="easyui-linkbutton" plain="true"
						iconCls="accordion-collapse" style="float: left;" onclick="expandAll()">展开</a>
					<a class="easyui-linkbutton" plain="true"
						iconCls="accordion-expand" style="float: left;" onclick="collapseAll()">关闭</a>
				</div>
				<div region="center">
					<ul id="menuitem_tree" class="easyui-tree">
					</ul>
				</div>
			</div>
		</div>

		<!-- 以下是弹出框 -->
		<div id="roleItem" style="padding: 5px; width: 770px; height: 230px;"
			title="角色信息">
			<form action="" name="roleitemForm" id="roleitemForm">
				<div>
					<table id="tableQueryForm" style="width: auto;">
						<!-- 分割线 -->
						<tr>
							<td colspan="6">
						
								<input type="hidden" id="sysRoleUuid1" name="sysRoleUuid" />
								<!-- 隐藏重要属性 -->
								<input type="hidden" id="recVer1" name="recVer" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*&nbsp;</font>角色代码：
							</td>
							<td >
								<input type="text" id="roleCode1" name="roleCode"
									class="easyui-validatebox" required="true" style="TEXT-TRANSFORM:uppercase;ime-mode:disabled" onchange="getYanZhenUserCodeFunction(this.id,this.value)"/>
							</td>
							<td align="right">
								<font color="red">*&nbsp;</font>角色中文名称：
							</td>
							<td>
								<input type="text" id="roleName1" name="roleName"
									class="easyui-validatebox" required="true" />
							</td>
								<td align="right">
								角色英文名称：
							</td>
							<td>
								<input type="text" id="roleNameEn1" name="roleNameEn" style="ime-mode:disabled" onchange="getYanZhenUserEn(this.id,this.value)" />
							</td>
						</tr>
						<tr>
							
							<td align="right">
								虚拟的：
							</td>
							<td>
								<select id="isVirtualFlag1" class="easyui-combobox"  panelHeight="auto" name="isVirtualFlag"
									style="width: 151px;">
									<option value="1">1</option>
									<option value="2">2</option>
								</select>
							</td>
							<td align="right">
								角色类别：
							</td>
							<td>
								<select id="roleType1" class="easyui-combobox"  panelHeight="auto" name="roleType" style="width: 151px;">
									<option value="1">1</option>
									<option value="2">2</option>
								</select>
							</td>
							<td align="right">
								备注：
							</td>
							<td >
								<input type="text"  id="remark1" name="remark" />
							</td>
						</tr>
							<!-- 分割线 -->
					<tr>
						<td colspan="8">
							<hr/>
						</td>
					</tr>
						<tr>
						
							<td align="right">
								删除标志：
							</td>
							<td>
								<select id="deletedFlag1" class="easyui-combobox"  panelHeight="auto"  name="deletedFlag"
									style="width: 151px;">
									<option value="1">1</option>
									<option value="2">2</option>
								</select>
							</td>
							<td align="right">
								允许查询：
							</td>
							<td>
								<select id="canQueryallFlag1"  class="easyui-combobox"  panelHeight="auto"  name="canQueryallFlag"
									style="width: 151px;">
									<option value="1">1</option>
									<option value="2">2</option>
								</select>
							</td>
							<td align="right">
								状态：
							</td>
							<td>
								<select id="status1" name="status" class="easyui-combobox" panelHeight="auto"  style="width: 151px;" editable="false">
									<option value="<%=CommonUtil.Active%>" selected="selected">有效</option>
									<option value="<%=CommonUtil.Pending%>" >草稿</option>
									<option value="<%=CommonUtil.Cancel%>">作废</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								生效日期：
							</td>
							<td>
								<input type="text" id="activeDate1" name="activeDate"
									class="easyui-datebox easyui-validatebox" validType="dateFormat" style="width: 151px;" />
							</td>
							<td align="right">
								失效日期：
							</td>
							<td>
								<input type="text" id="expiredDate1" name="expiredDate"
									class="easyui-datebox easyui-validatebox" validType="dateFormat" style="width: 151px;" />
							</td>
						</tr>
								<!-- 分割线 -->
					<tr>
						<td colspan="8">
							<hr/>
						</td>
					</tr>
					<!-- 	<tr>
							<td align="right">
								公司(仓库)：
							</td>
							<td colspan="5">
								<select id="officeCode1" name="officeCod" style="width: 607px;">
								</select>
							</td>
						</tr> -->
						<tr>
							<td align="center" colspan="6">
								<a href="#" id="saveDivButton" class="easyui-linkbutton" name=""
									onclick="provinguser()">保存</a>
								<!-- <a href="#" class="easyui-linkbutton"
									onclick="cleardate('roleitemForm')">清空</a> -->
								<a href="#" class="easyui-linkbutton" id="closeDivButton"
									onclick="closewicket('roleItem')">关闭</a>
							</td>
						</tr>

					</table>
				</div>
			</form>
		</div>
	</body>
</html>