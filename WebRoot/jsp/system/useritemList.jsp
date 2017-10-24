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
<html:html>
<head>
	<jsp:include page="../../common/imp_dwr.jsp"></jsp:include>
	<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
	<style type="text/css">
#tableQueryForm td {
	font-size: 12px;
}
</style>
</head>
<body class="easyui-layout">
	<!--  用户管理页面 2012年2月28日 19:10:17-->
	<script type="text/javascript">
	var panDuanShiFouQingKong=null;
	var panDuanShiFouDiYiCiBianJi=null;
	var userTypeWholeArray=new Array();
	var customerCodeWholeArray=new Array();
	var panDuanKongZhi=null;
	var lastIndex;
			$(function(){
			roleitem();//初始化话角色
				if(panDuanKongZhi==null||panDuanKongZhi==""){
					__getUserOfficeChuShiHua();//初始化当前页面所需要的 组织机构树形结果和用户类型的数据
					setTimeout(function(){
						__getUserdatagirdChuShiHua(); //初始化用户列表的所有用户数据
					},500);
					$(".layout-button-left").remove();//删除左侧伸缩按钮
					$(".panel-tool-collapse panel-tool-over").remove();//删除用户信息管理伸缩按钮
					panDuanKongZhi="OK";
				}
			});
			function userTypeHuoQu(value,rowData,rowIndex){
				$.each(userTypeWholeArray,function(uuid,userCodeModel){
	  				if(userCodeModel.key==value){
	  					value=userCodeModel.value;
	  				}
	  			});
	  			if(value==null||value==""||value=="null"){
	  				value="无";
	  			}
	  			return value;
			}
			function customerCodeHuoQu(value,rowData,rowIndex){
				$.each(customerCodeWholeArray,function(uuid,custCodeModel){
	  				if(custCodeModel.customerCode==value){
	  					value=custCodeModel.customerName;
	  				}
	  			});
	  			if(value==null||value==""||value=="null"){
	  				value="无";
	  			}
	  			return value;
			}
			function __getUserOfficeChuShiHua(){
				//加载部门分组
				$("#office_tree").tree({
					url:"<%=path%>/servlet/SinotransServlet?cmd=selectOfficeitemtree1&date="+new Date(),
					onClick:function(node){
					//	var getchecked=$('#office_tree').tree('getChecked',node);
					//	$('#office_tree').tree('check',node.target);
						SysOfficeManager.get(node.id,function(model){
						if(model){
							userselectsooo(model.officeCode);
						}
						});
					}
				});
				$("#userType").combobox({
					url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.OPTION_SYSTEM_CODE%>&q=<%=CommonUtil.OPTION_SYSTEM_CODE_USER_TYPE%>&date='+new Date(),
					valueField:'key',
					textField:'value',
					panelHeight:'auto',
					mode:'remote'	
				});
			}
			//取出组织类型数据保存到全局变量officeTypeWholeArray的数组中。
			function userTypeWholeArrayManger(){
				if(userTypeWholeArray!=null&&userTypeWholeArray.length==0){
					$.ajax({
			   			type: "POST",
					    url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.OPTION_SYSTEM_CODE%>&q=<%=CommonUtil.OPTION_SYSTEM_CODE_USER_TYPE%>&date='+new Date(),
					    dataType:'json',
					    success: function(date){
					    	$.each(date,function(num,userTypeModel){
					 			userTypeWholeArray.push(userTypeModel); //把取出来的（key、value）
					 		});
					   	}
					});
				}
			}
			//取出组织类型数据保存到全局变量officeTypeWholeArray的数组中。查询全部office  UseritmecustomerCode
			function customerCodeWholeArrayManger(){
				if(customerCodeWholeArray!=null && customerCodeWholeArray.length==0){
					$.ajax({
			   			type: "POST",
					    url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=basCgetustomerAll&date='+new Date(),
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
			//初始化角色代码
			function roleitem(){
			<%
			String agentConsigneeDesc = CommonUtil.SELECT_CODE_ALL_ROLEIEM;
			String agentConsigneeDescmodelName = agentConsigneeDesc.split(",")[0] + "Model";
			%>
			var customerRow = [{name:'roleCode',value:'角色代码'},{name:'roleName',value:'角色名称'},{name:'roleNameEn',value:'角色英文名称'}];
			
			combogridSetValueAndTest('roleCode','',customerRow,'<%=path%>','<%=agentConsigneeDescmodelName%>','',true);
			}
			//初始化用户列表表格和数据
			function __getUserdatagirdChuShiHua(){
			userTypeWholeArrayManger(); //初始化活的所有用户类型（仅仅保存在页面中。并没有显示。等待加载用户表格的时候再调用显示）
			customerCodeWholeArrayManger();//获取组织机构
				var isoUrl="";
				if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
					isoUrl="<%=path%>/servlet/SinotransServlet?cmd=query&queryType=SysUserModel";
				}else{
					isoUrl="<%=path%>/servlet/SinotransServlet?cmd=deAdmin";
				}
				$('#userdatagird').datagrid({
					height:($(document).height()*0.8),
					url:isoUrl,
					pagination:true,
					rownumbers:true,
					striped:true,
					singleSelect: true,
					pageSize:20, //每页显示记录数
					pageList:[10,20,30,40,50], //可调整每页显示的记录数
					idField:'userUuid',
					remotesort: true,
					sortName:'userName',
					sortOrder:'asc',
					columns:[[
						{field:"status",title:"状态",width:50,align:"center",formatter:statusOOr,sortable:true},
						{field:"userCode",title:"用户编码",width:120,align:"center",td_align:"left",sortable:true},
						{field:"userName",title:"用户名",width:120,align:"center",td_align:"left",sortable:true},
						{field:"userNameEn",title:"用户名称(英文)",width:150,align:"center",td_align:"left",sortable:true},
						{field:"customerCode",title:"所属公司",width:200,align:"center",td_align:"left",formatter:customerCodeHuoQu,sortable:true},
				//		{field:"customerCode",title:"所属仓库",width:200,align:"center",td_align:"left",formatter:customerCodeHuoQu},
						{field:"userType",title:"用户类型",width:100,align:"center",td_align:"left",formatter:userTypeHuoQu,sortable:true},
						{field:"position",title:"职位描述",width:150,align:"center",td_align:"left",sortable:true},
						{field:"email",title:"电子邮件",width:150,align:"center",td_align:"left",sortable:true},
					//	{field:"createTime",title:"创建时间",width:130,align:"center",td_align:"left"},
						{field:"mobile",title:"移动电话",width:120,align:"center",td_align:"left",sortable:true},
						{field:"education",title:"教育程度",width:80,align:"center",td_align:"left",sortable:true}
					//	{field:"roleName",title:"用户角色",width:120,align:"center",td_align:"left"}
						
					]],
					toolbar:[{
						text:'新增',//新增按钮
						iconCls:'icon-add',
						handler:function(){
							userInfoNew();
						}
					},'-',{
						text:'<bean:message bundle="wlp.system" key="system.user.userEdit"/>',//修改按钮
						iconCls:'icon-edit',
						handler:function(){
							userdatagirdEdit();//调用编辑的事件
						}
					},'-',{
						text:'删除',//删除按钮
						id:'deleteUseritem',
						iconCls:'myCustomerIcon_remove',
						handler:function(){
							userdatagirdDelete();
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
					},'-',{
						text:'重置密码',
						id:'chongzhimima',
						iconCls:'icon-reload',
						handler:function(){
							chongzhimima();
						}
					}],					
					onDblClickRow:function(rowIndex,rowData){
						$('#userdatagird').datagrid('endEdit', lastIndex);
						$('#userdatagird').datagrid('beginEdit', rowIndex);
						lastIndex = rowIndex;
						userdatagirdEdit();//调用编辑的事件
					},
					onClickRow:function(rowIndex,rowData){
						$('#userdatagird').datagrid('endEdit',lastIndex);
						if(rowData.status=="<%=CommonUtil.Active%>"||rowData.status=="<%=CommonUtil.Cancel%>"){
							__getdOe("d");
						}else{
							SysRoleUserManager.getUserValidationUR(rowData.userUuid,function(data){
								if(data=="true"){
									__getdOe("d");
								}else{
									__getdOe("e");
								}
							});
						}
					}
					,onRowContextMenu:function(e,node,rowData){
						e.preventDefault();
						$('#userdatagird').datagrid('selectRow', node);
						//userdatagirdEdit();//调用编辑的事件
						/**$('#menuUser').menu('show', {
							left: e.pageX,
							top: e.pageY
						});*/
					}
				});
			}
			//用户新增调用
			function userInfoNew(){
				initPage();
				$('#userInfo').window('open');
				if(document.getElementById("userInfoIframe").contentWindow.clearData!=null){
					document.getElementById("userInfoIframe").contentWindow.clearData();
					document.getElementById("userInfoIframe").contentWindow.getButtonKongZhi("e");
				}
			}
			//修改状态。需要传送的值不为空必须为状态
			function userdatagirdStatus(status){
				var row = $('#userdatagird').datagrid('getSelected');
				if(row==null){
					alert('请先选择一行数据再进行操作！');
				}else{
				if(row.userCode!='<%=scue.getUserId()%>'){
				
				SysUserManager.updateSysStatusOue(row.userUuid,status,"SYS_USER","STATUS","USER_UUID",function(data){
						if(data){
							alert("操作成功");
						}else{
							alert("操作失败");
						}
						$('#userdatagird').datagrid("reload");//根据表格ID刷新页面
					});
					}else{
							alert("不能修改自己的状态!");
					}
				}
				}
			
			//删除用户信息
			function userdatagirdDelete(){
				var row = $('#userdatagird').datagrid('getSelected');
				if(row.status=="<%=CommonUtil.Active%>"){
					alert('该数据以生效。请审核后重新操作！');
					__getdOe('d');//毁掉删除按钮
				}else{
					if(row==null){
						alert('请先选择一行数据再进行操作！');
					}else{
						var index =$('#userdatagird').datagrid('getRowIndex', row);
						confirm('确定框', '确定删除‘ '+row.userName+' ’的用户信息吗?', function(r){
							if (r){
								//$('#userdatagird').datagrid('deleteRow', index);
								//删除用户操作Ajax
								$.ajax({
						   			type: "POST",
								    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=deleteUser&date="+new Date(),
								    data: "deleteuser="+row.userUuid+"",
								    dataType:'json',
								    success: function(date){
								    	if(date==null||date.result){
								    		alert("操作成功");
								    	}else if(date.error){
								    		alert("操作失败！错误信息如下："+date.error);
								    	}else{
								    		alert("操作失败。暂无返回信息。");
								    	}
								    	$('#userdatagird').datagrid("reload");//根据表格ID刷新页面
								    	__getdOe("d");//刷新表格后把删除按钮屏蔽掉
			   						}
								});
							}
						});
					}
				}
			}
			function userdatagirdEdit(){//编辑用户信息
				//initPage();
				var row = $('#userdatagird').datagrid('getSelected');//selectuserid
				if(row==null){
					alert('请先选择一行数据再进行操作！');
				}else{
					SysUserManager.get(row.userUuid,function(userModel){
						if(userModel!=null){
							editUserInfo(row.userUuid);//编辑用户信息
						}else{
							alert("以获取不到此信息!请重新选择数据.");
						}
					});
				}
			}
			function reload(){
				$('#userdatagird').datagrid("reload");//根据表格ID刷新页面
			}
			function initPage(){
				var src = $("#userInfoIframe").attr("src");
				if(src==null || src==''){
					$("#userInfoIframe").attr("src","editUser.jsp");
				}
				var iframe01 = document.getElementById("userInfoIframe");//判断iframe 页面是否加载完
				if (iframe01.attachEvent){ 
					iframe01.attachEvent("onload", function(){
						document.getElementById("userInfoIframe").contentWindow.clearData();
				    });
				} else {
					iframe01.onload = function(){
						document.getElementById("userInfoIframe").contentWindow.clearData();
				    };
				}
			}
			//按钮控制
			function __getdOe(dOe){
				if(dOe!=null&&dOe=="d"){
		    		$("#deleteUseritem").linkbutton("disable").unbind("click");
		    	}else if(dOe!=null&&dOe=="e"){
					$("#deleteUseritem").linkbutton("enable");
				}
			}
			//重置密码
			function chongzhimima(){
			var row = $('#userdatagird').datagrid('getSelected');//selectuserid
				if(row){
					$.messager.confirm("提示", "是否确认要重置密码", function(b) {
						if(b){
							 SysUserManager.resetpassword(row.userUuid,function(date){
								  	if(date.result){
								  		alert(date.msg);
								  	}else{
								  	    alert("重置失败!");
								  	}
							 });
						}
					  });
				}else{
					alert("请选则一条数据在进行操作!");
				}
			} 
			
		</script>
<script type="text/javascript">
	//查询根据部门UUID查询所属员工
	function userselectsooo(departmentCode){
		$("#userdatagird").datagrid('load',{
            __officeCode:departmentCode
       	});
	}
	//根据部门查询用户
	function shijian(){
		$("#userdatagird").datagrid('load',{
		    __departmentCode:node.id
		 });
	}
	//查询按钮事件
	function userselect(){
	   var  roleCode=$("#roleCode").combogrid('getValue');
		var userName=$("#userName").val();
	//	var userType=$('#userType').combobox('getValue');
		//var education=$('#education1').combobox('getValue');
		var createTime_s=$("#createTime__start").datebox("getValue");
		var createTime_e=$("#createTime__end").datebox("getValue");
		var condition = $("#__condition").val();
		//alert("用户名字："+userName+" 用户类型："+userType+" 学历："+education+"时间："+createTime_s+" : "+createTime_e);
		$("#userdatagird").datagrid('load',{
              	__userName:userName,
            //  	__userType:userType,
                __roleCode:roleCode,
              	createTime__start:createTime_s,
              	createTime__end:createTime_e,
              	__condition:condition
       	});
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
	function clear(){
		var iframe = document.getElementById("userInfoIframe");
		iframe.clearData();
	}
	function editUserInfo(userUuid){
		$('#userInfo').window('open');
		initPage();
		//if(document.getElementById("userInfoIframe").contentWindow.clearData!=null){
		//	document.getElementById("userInfoIframe").contentWindow.clearData();
		//}
		if(panDuanShiFouDiYiCiBianJi!=null){
			window.frames["userInfoIframe"].editUser(userUuid);
		}else{
			setTimeout(function(){
				window.frames["userInfoIframe"].editUser(userUuid);
				src = $("#userInfoIframe").attr("src");
			},1110);
			panDuanShiFouDiYiCiBianJi="OK";
		}
	}
		//重置按钮事件
	function cleardate(s){
		$('#'+s).form('clear');
	}
		//重置按钮事件
	function cleardates(s){
		$('#'+s).form('clear');
		$('#userType').combobox("select","null");
	}
</script>
	<div split="true" region="center" style="overflow: hidden;"
		title="<bean:message bundle="wlp.system" key="system.user.userManager"/>">
		<div class="easyui-layout" fit="true">
			<div id="UserqueryTerm" region="north" border="false">
				<form id="UserqueryForm">
					<div style="background: #efefef; width: 100%; float: left">
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-search" style="float: left" onclick="userselect()">查询</a>
						<div class="datagrid-btn-separator"></div>
	
						<!-- 这是分割线 -->
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="myCustomerIcon_clear" style="float: left"
							onclick="cleardates('UserqueryForm')">重置</a>
					</div>
					<!-- legend>表单验证</legend -->
					<table id="tableQueryForm" style="width: 95%; float: left">
						<tr align="right">
							<td width="15%" align="right">
								<label>
									<bean:message bundle="wlp.system" key="system.user.usrUserName" />
									:
								</label>
							</td>
							<td width="35%" align="left">
								<input id="userName" name="userName" size="25"
									class="easyui-validatebox" />
								<input type="hidden" id="__condition" name="__condition"
									value="userName_ilikeAnywhere:userType_=:createTime_between" />
							</td>
							<!--  
							<td width="15%" align="right">
								<label>
									用户类型:
								</label>
							</td>
							<td width="35%" align="left">
								<input id="userType" name="userType"/> 
								<!-- <select id="userType" name="userType" class="easyui-combobox" style="width: 80px">
									<option value="">全部</option>
								</select>-->
								<!-- 
							</td>
							-->
							<td width="15%" align="right">
								<label>
									用户角色:
								</label>
							</td>
							<td width="35%" align="left">
								<input id="roleCode" name="roleCode"/>
							</td>
						
							
						</tr>
						<tr align="right">
							<td width="15%" align="right">
								<label>
									创建日期:<!-- 教育程度: -->
								</label>
							</td>
							<td width="35%" align="left">
								<input id="createTime__start" name="createTime__start"
									class="easyui-datebox easyui-validatebox" validType="dateFormat" style="width: 80px;" />
								<img src="<%=path%>/images/right.gif" />
								<input id="createTime__end" name="createTime__end"
									class="easyui-datebox easyui-validatebox" validType="dateFormat" style="width: 80px;" />
									
								<!-- <input type="text" id="education" size="25"/> -->
								<!-- 
								<select id="education1" class="easyui-combobox" style="width: 80px">
									<option value="">全部</option>
									<option value="小学">小学</option>
									<option value="初中">初中</option>
									<option value="高中">高中</option>
									<option value="中技">中技</option>
									<option value="中专">中专</option>
									<option value="大专">大专</option>
									<option value="专科">专科</option>
									<option value="本科">本科</option>
									<option value="硕士">硕士</option>
									<option value="博士">博士</option>
									<option value="留学生">留学生</option>
									<option value="其他">其他</option>
								</select>
								-->
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
			<div region="center" border="false">
				<table id="userdatagird" title="用户信息管理"  fit="true"></table>
			</div>
		</div>
	</div>
	<!-- 左 -->
	<div region="west" title="组织机构" style="width: 220px; padding: 10px;" >
		<ul id="office_tree" class="easyui-tree" >
		</ul>
	</div>
	<!-- 弹出框 -->
	<div id="userInfo" style="padding: 5px; width: 850px; height: 490px;"
		title="用户信息" class="easyui-window" closed="true" modal="true" minimizable="false" maximizable="false" collapsible="fasle">
		<iframe name="userInfoIframe" id="userInfoIframe" scrolling="yes" frameborder="0"  style="width:100%;height:100%;"></iframe>
	</div>
	
		<!-- 鼠标右键增删 -->
		<div id="menuUser" class="easyui-menu" style="width: 120px;">
			<div onclick="userdatagirdEdit()" iconCls="icon_toolbar_edit">
				编辑
			</div>
			<div onclick="reload()" iconCls="icon-reload">
				重载
			</div>
			<div onclick="userdatagirdStatus('<%=CommonUtil.Active%>')" iconCls="icon-ok">
				生效
			</div>
			<div onclick="userdatagirdStatus('<%=CommonUtil.Cancel%>')" iconCls="icon-cancel">
				作废
			</div>
			<div class="menu-sep"></div>
			<div onclick="userdatagirdDelete()" iconCls="icon-remove">
				删除
			</div>
		</div>
	
</body>
</html:html>