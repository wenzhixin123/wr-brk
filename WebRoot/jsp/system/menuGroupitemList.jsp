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
	
		<!--  菜单分组页面 2012-3-7 -->
		<script>
		var lastIndex;
			$(function(){
			$("#MenuGroup").hide();//为解决页面出现图标隐藏失败问题
				__getMenuitemdatagirdChuShiHua();//菜单模块初始化表格信息以及数据
				__getmenuGroupItemdatagirdChuShiHua();//菜单分组初始化
				customerCodeWholeArrayManger();//获取组织机构
				shangjicaidangArrayManger();
			});
			//定义全局变量
			var customerCodeWholeArray=new Array();
			var shangjicaidangArray=new Array();
		</script>
		<script type="text/javascript">
			//菜单模块初始化 selectModuleitemtree(没有顶级菜单) 
			isoUrl="";
			//if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
					isoUrl="<%=path%>/servlet/SinotransServlet?cmd=selectFatherModuleitemtree&date="+new Date();
				//}else{
				//	isoUrl="<%=path%>/servlet/SinotransServlet?cmd=selectModuleitemUsertree&date="+new Date();
				//}
			function __getMenuitemdatagirdChuShiHua(){
				$("#office_tree").tree({
					url:isoUrl,
					onClick:function(node){
					var getchecked=$('#office_tree').tree('getChecked',node);
						$('#office_tree').tree('check',node.target);
						if(node.id){
							SysMenuGroupManager.get(node.id,function(model){
								if(model){
									userselectsooo(model.sysMenuGroupUuid,model.preSysMenuGroupUuid);
								}
							});
						}else{
									userselectsysMenuGroup();
						}
					}
				});
			}
		function userselectsysMenuGroup(){
		$('#menuGroupItemdatagird').datagrid({
		url:"<%=path%>/servlet/SinotransServlet?cmd=query&queryType=SysMenuGroupModel&sort=menuGrpSeq&order=asc&date="+new Date(),
		queryParams:{}
		});
		
		}
			
		//查询根据部门UUID查询所属部门
		function userselectsooo(departmentCode,preSysMenuGroupUuid){
				$("#menuGroupItemdatagird").datagrid('load',{
     	  	     __preSysMenuGroupUuid:departmentCode
     	  	     //__sysMenuGroupUuid:departmentCode
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
			
		</script>
		<script type="text/javascript">
			//菜单分组初始化
			function __getmenuGroupItemdatagirdChuShiHua(){
			var isoUrl="";
				//if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
					isoUrl="<%=path%>/servlet/SinotransServlet?cmd=query&queryType=SysMenuGroupModel";
				//}else{
				//	isoUrl="<%=path%>/servlet/SinotransServlet?cmd=query&queryType=SysMenuGroupModel&oc=true";
			//	}
				$('#menuGroupItemdatagird').datagrid({
					url:isoUrl,
					height:452,
					pagination:true,
					rownumbers:true,
					striped:true,
					singleSelect: true,
					pageSize:20, //每页显示记录数
					pageList:[10,20,30,40,50], //可调整每页显示的记录数
					//singleSelect: true,  // 单选或多选
					idField:'sysMenuGroupUuid',
					sortName:'menuGrpSeq',
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
						id:'deleteMenuGroupItem',
						iconCls:'myCustomerIcon_remove',
						handler:function(){
							menuItemDelete();
						}
					},'-',{
						text:'生效',
						id:'activeRoleMenuGroup',
						iconCls:'icon-ok',
						handler:function(){
							userdatagirdStatus("<%=CommonUtil.Active%>");
						}
					},'-',{
						text:'作废',
						id:'claseRoleMenuGroup',
						iconCls:'icon-cancel',
						handler:function(){
							userdatagirdStatus("<%=CommonUtil.Cancel%>");
						}
					}],
					onClickRow:function(rowIndex,rowData){
						if(rowData.status=="<%=CommonUtil.Active%>"||rowData.status=="<%=CommonUtil.Cancel%>"){
				    		__getdOe('d');
				    	}else{
				    		SysRoleMenuItemManager.getValidationMenuItemAssociation(rowData.sysMenuGroupUuid,function(data){
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
		//修改状态。需要传送的值不为空必须为状态
		function userdatagirdStatus(status){
				var row = $('#menuGroupItemdatagird').datagrid('getSelected');
				if(row==null){
					alert('请先选择一行数据再进行操作！');
				}else{
					SysUserManager.updateSysStatusOue(row.sysMenuGroupUuid,status,"sys_menu_group","STATUS","SYS_MENU_GROUP_UUID",function(data){
						if(data){
							alert("操作成功");
						}else{
							alert("操作失败");
						}
						reload();
						$('#menuGroupItemdatagird').datagrid("reload");//根据表格ID刷新页面
					});
				}
			}
			//菜单新增
			function menuItemNew(){
			$("#MenuGroup").show();
			getButtonKongZhi("e");
				$('#MenuGroup').dialog({
					modal:true
				});
				$("#saveDivButton").attr("name","saveDivButton");//修改完成按钮数据为保存
				$("#menuGrpCode1").attr("readonly",false);
				UserItemAccredits();
				adduserget();
				cleardate('MenuGroupForm');
				$("#status1").combobox("select","<%=CommonUtil.Active%>");
				$("#controlWord1").combobox("select","<%=CommonUtil.Default_Control_Word%>");
				SysOfficeManager.getOfficeOofficeCodeModel("<%=scue.getOfficeCode()%>",function(data){
					if(data){
						$('#officeCode1').combotree('setValue',data);//所属菜单项
					}
				});
				var ido=$("#office_tree").tree('getSelected');
				if(ido)
				$("#preSysMenuGroupUuid1").combotree('setValue',ido.id);
				//alert($.toJSON(ido));
			}
		//取出所有公司(仓库)代码
			function adduserget(){
				$("#officeCode1").combotree({//上级组织
					url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectOfficeitemtree1&date='+new Date(),
					onClick:function(node){
						
					}	
				});
			}
		//重置按钮事件
	function cleardate(s){
				$('#'+s).form('clear');
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
					SysMenuGroupManager.getYanZhenUserCode(valueUser,function(boole){
						if(boole){
							$("#"+textid).val(null);
							alert("该编码已存在、请审核后重新填写！");
						}
					});
				}
			}
		}
		//取出所属菜单项树形上级菜单
		function UserItemAccredits(){
				$("#preSysMenuGroupUuid1").combotree({//   selectModuleitemUsertree
					url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getMenuGroupTree&date='+new Date(),
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
			//验证表单数据
			function provinguser(){
			//验证表单是否通过
				var validateable=$('#MenuGroupForm').form('validate');
				if(validateable){
					saveuser();
				}else{
					alert("*必填数据为空！请审核后重新操作！");
				}
			}
			//关闭对话框窗口(传入窗口id)
			function closewicket(c){
				$("#"+c).dialog('close');
			}
		//提交表单新增数据库数据和刷新页面
		function saveuser(){
		getButtonKongZhi("d");
				var menuitemjsonResults=$("#MenuGroupForm").serializeArray(); //得到数组json对象
				var saveuerjudge=$("#saveDivButton").attr("name");
				var menuitemjson;
					menuitemjson="{";
					$.each(menuitemjsonResults,function(k,v){
						if(k==menuitemjsonResults.length-1){
							menuitemjson=menuitemjson+'"'+v.name +'"'+':'+'"'+ v.value+'"';
						}else{
							menuitemjson=menuitemjson+'"'+v.name +'"'+':'+'"'+ v.value+'"'+",";
						}
					});
					menuitemjson=menuitemjson+"}";
					if(saveuerjudge=="saveDivButton"){
					LogisticsOrderManager.object2base64(menuitemjson,function(menuitemjson1){
						if(menuitemjson1){
							//使用ajax提交到服务器保存数据
							$.ajax({
					   			type: "POST",
							    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getmenuGroup&&date="+new Date(),
							    data: "menuitemjson="+menuitemjson1+"",
							    dataType:'json',
							    success: function(date){
							    	if(date.result){
							    		alert(date.msg);
							    	}else if(date.result==false){
							    		alert("保存失败!");
							    		getButtonKongZhi("e");
							    	}
							    	reload();
							    	$('#MenuGroupForm').form('clear');//重置用户填写窗口的信息
							    	$('#MenuGroup').dialog('close');//关闭用户信息填写窗口
							    	$('#menuGroupItemdatagird').datagrid("reload");//根据表格ID刷新页面
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
								    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=updateMenuGroup&date="+new Date(),
								    data: "menuitemjson="+menuitemjson1+"",
								    dataType:'json',
								    success: function(date){
								    	if(date.result){
								    		alert(date.msg);
								    	}else if(date.result==false){
								    		alert("保存失败!");
								    		getButtonKongZhi("e");
								    	}
								    	reload();
								    	$('#MenuGroupForm').form('clear');//重置用户填写窗口的信息
								    	$('#MenuGroup').dialog('close');//关闭用户信息填写窗口
								    	$('#menuGroupItemdatagird').datagrid("reload");//根据表格ID刷新页面
				   					}
								});
							}
						});
					}
			}
		//菜单编辑
			function menuItemEdit(){
			$("#MenuGroup").show();
			getButtonKongZhi("e");
				var row = $('#menuGroupItemdatagird').datagrid('getSelected');//selectuserid
				if(row==null){
				alert('请先选择一行数据再进行操作!');
				}else{
					$.ajax({
		   			type: "POST",
				    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=updateMenuGroupid&date="+new Date(),
				    data: "selectmenuitemid="+row.sysMenuGroupUuid+"",
				    dataType:'json',
				    success: function(date){
				    	$('#MenuGroup').dialog({
							draggable:false,
							modal:true
						});
				    	UserItemAccredits();
				    	adduserget();
				    	$("#recVer1").val(date.recVer);// 并发访问控制
				    	$("#sysMenuGroupUuid1").val(date.sysMenuGroupUuid);//一级菜单UUID
				    	$("#sysModuleUuid1").val(date.sysModuleUuid);//模块UUID
				    	$('#preSysMenuGroupUuid1').combotree('setValue',date.preSysMenuGroupUuid);//上级菜单
				    	$("#status1").combobox('setValue',date.status);//状态
				    	$("#controlWord1").combobox('setValue',date.controlWord);
				    	$("#menuGrpSeq1").val(date.menuGrpSeq);//菜单组序号
				    	$("#menuGrpCode1").val(date.menuGrpCode);//菜单分组代码
				    	$("#menuGrpName1").val(date.menuGrpName);//菜单分组中文名
				    	$("#menuGrpNameEn1").val(date.menuGrpNameEn);//菜单分组英文名
				    	//$("#officeCode1").combobox('select',date.officeCode);//公司(仓库)
				    	if(date.status!=null&&date.status!="" && date.status=='<%=CommonUtil.Active%>'||date.status=='<%=CommonUtil.Cancel%>' ){
				    		$("#menuGrpCode1").attr("readonly","readonly");
				    	}else{
				    		$("#menuGrpCode1").attr("readonly",false);
				    	}
				    	SysOfficeManager.getOfficeOofficeCodeModel(date.officeCode,function(data){
							if(data){
								$('#officeCode1').combotree('setValue',data);//所属菜单项
							}
						});
				    	$("#remark1").val(date.remark);//备注
				    	$("#saveDivButton").attr("name","updatemenuitem");//修改完成按钮数据为修改
				    	//alert(menuitemjson.recVer+" : "+menuitemjson.sysMenuItemUuid+" : "+menuitemjson.menuItemName+" : "+menuitemjson.isDialog+" : "+menuitemjson.menuItemNameCn+" : "+menuitemjson.status+" : "+menuitemjson.menuItemSeq+" : "+menuitemjson.creator+" : "+menuitemjson.menuItemCode);
  						}
				});
				}
			}	
		//菜单删除信息
		function menuItemDelete(){
				var row = $('#menuGroupItemdatagird').datagrid('getSelected');
				if(row.status=="<%=CommonUtil.Active%>"){
					alert('该数据以生效。请审核后重新操作！');
					__getdOe('d');//毁掉删除按钮
				}else{
					if(row==null){
						alert('请先选择一行数据再进行操作！');
					}else{
					var index =$('#menuGroupItemdatagird').datagrid('getRowIndex', row);
						$.messager.confirm('确定框', '确定删除‘'+row.menuGrpName+'’菜单信息吗?', function(r){
							if (r){
								$.ajax({
						   			type: "POST",
								    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=deleteMenuGroup&&date="+new Date(),
								    data: "deleteMenuitem="+row.sysMenuGroupUuid+"",
								    dataType:'json',
								    success: function(date){
								    if(date.result){
						    			alert(date.msg);
						    			__getdOe('d');//毁掉删除按钮（操作成功需要灰掉按钮。否则再次点击确定就报错）
						    		}else if(date.result==false){
						    			alert("删除失败!");
						    		}
								    	reload();
								    	$('#menuGroupItemdatagird').datagrid("reload");//根据表格ID刷新页面
			   						}
								});
							}
						});
					}
				}
			}
		//按钮控制
			function __getdOe(dOe){
				if(dOe!=null&&dOe=="d"){
		    		$("#deleteMenuGroupItem").linkbutton("disable").unbind("click");
		    	}else if(dOe!=null&&dOe=="e"){
					$("#deleteMenuGroupItem").linkbutton("enable");
				}
			}
			//重载
		function reload(){
			var node = $('#office_tree').tree('getSelected');
			$('#office_tree').tree('reload');
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
	function caidanglaixing(value,rowData,rowIndex){
		if(value=='<%=CommonUtil.Default_Control_Word%>'){
			return "WLP内部菜单";
		}else if(value=="10000000000000000000"){
			return "外部服务平台";
		}else{
		    return "无";
		}
	}
	
	function shangjicaidangPreSysMenuGroupUuid(value,rowData,rowIndex){
	if(value){
		$.each(shangjicaidangArray,function(uuid,custCodeModel){
  				if(custCodeModel.sysMenuGroupUuid==value){
  					value=custCodeModel.menuGrpName;
  				}
		  	});
	
	}else{
		value="顶级菜单";
	}
		return value;
	}
	function shangjicaidangArrayManger(){
				if(shangjicaidangArray!=null && shangjicaidangArray.length==0){
					$.ajax({
			   			type: "POST",
					    url:"<%=path%>/servlet/SinotransServlet?cmd=selectmenuGroup&date="+new Date(),
					    dataType:'json',
					    success: function(date){
					    	$.each(date,function(num,customerCode){
					 			shangjicaidangArray.push(customerCode); 
					 		});
					   	}
					});
				}
		}
		</script>
	<!-- 右边显示信息 开始 -->
	    <body class="easyui-layout">
			<div region="center"   style="width:550px;" split="true" >
				<table id="menuGroupItemdatagird" title="菜单分组管理" iconCls="icon-edit" fit="true"  >
					<thead>
						<tr>
							<th field="menuGrpSeq" width="60" align="center" td_align="center" sortable=true>
								序号
							</th>
							<th field="status" width="60" align="center" formatter="statusOOr" sortable=true>
								状态
							</th>
							<th field="menuGrpName" width="150" align="center" td_align="left" sortable=true>
								菜单中文名
							</th>
							<th field="menuGrpNameEn" width="150" align="center" td_align="left" sortable=true>
								菜单英文名
							</th>
							<th field="officeCode" width="150" align="center" td_align="left" formatter="customerCodeHuoQu" sortable=true>
								所属公司
							</th>
							<th field="menuGrpCode" width="100" align="center" td_align="left" sortable=true>
								菜单代码
							</th>
							<th field="controlWord" width="100" align="center" td_align="left" formatter="caidanglaixing" sortable=true>
							    菜单类型
							</th>
							<th field="preSysMenuGroupUuid" width="100" align="center" td_align="left"  formatter="shangjicaidangPreSysMenuGroupUuid" sortable=true>
							    上级菜单
							</th>
							<th field="remark" width="200" align="center" td_align="left" sortable=true>
								备注
							</th>
						</tr>
					</thead>
				</table>
			</div>
			
	<!-- 左 -->
			<div region="west" title="分组管理" style="background: #efefef; width: 200px;" split="true" >
				<ul id="office_tree" class="easyui-tree">
				</ul>
			</div>	
<!-- 弹出框 module-->
	<div id="MenuGroup" style="width: 600px; height: 240px;"
			title="菜单分组管理">
			<form name="MenuGroupForm" id="MenuGroupForm">
				<div>
					<table id="tableMenuGroupForm" style="width: auto;"  align="center">
						<!-- 分割线 -->
						<tr align="center">
							<td colspan="4">
								<input type="hidden" id="sysMenuGroupUuid1" name="sysMenuGroupUuid" />
								<!-- 隐藏重要属性 -->
								<input type="hidden" id="recVer1" name="recVer" />
							</td>
							<td>
								<input type="hidden" id="sysModuleUuid1" name="sysModuleUuid"   maxlength="38"/>
							</td>
						</tr>
						<tr align="center">
						<td align="right">
								<font color="red">*&nbsp;</font>菜单代码:
							</td>
							<td>
								<input type="text" id="menuGrpCode1" name="menuGrpCode" required="true" class="easyui-validatebox" maxlength="50"  style="TEXT-TRANSFORM:uppercase;ime-mode:disabled" onchange="getYanZhenUserCodeFunction(this.id,this.value)"/>
							</td>
							<td align="right">
								<font color="red">*&nbsp;</font>菜单中文名:
							</td>
							<td>
								<input type="text" id="menuGrpName1"  class="easyui-validatebox"  name="menuGrpName" required="true" maxlength="150"/>
							</td>
						</tr>
						<tr align="center">
							<td align="right">
								菜单英文名:
							</td>
							<td>
								<input type="text" id="menuGrpNameEn1" name="menuGrpNameEn" maxlength="150" style="ime-mode:disabled" onchange="getYanZhenUserEn(this.id,this.value)"/>
							</td>
							<td align="right">
								菜单组序号:
							</td>
							<td>
								<input type="text" id="menuGrpSeq1" name="menuGrpSeq"  class="easyui-numberbox" maxlength="50" />
							</td>
						</tr>
						<tr align="center">
							<td align="right">备注:
							</td>
							<td>
								<input type="text" id="remark1" name="remark" maxlength="150"/>
							</td>	
							
							<td align="right">状态:
							</td>
							<td>
								<select id="status1" panelHeight="auto" class="easyui-combobox" name="status" style="width: 150px" editable="false">
									<option value="<%=CommonUtil.Active%>" selected="selected">有效</option>
									<option value="<%=CommonUtil.Pending%>" >草稿</option>
									<option value="<%=CommonUtil.Cancel%>">作废</option>
								</select>
							</td>
						</tr>
						<tr>
						<td align="right">
								菜单类型:
							</td>
							<td colspan="3" >
								<select id="controlWord1" panelHeight="auto" class="easyui-combobox" name="controlWord" style="width: 150px" editable="false" >
									<option value="<%=CommonUtil.Default_Control_Word%>" selected="selected">WLP内部菜单</option>
									<option value="10000000000000000000" >外部服务平台</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								上级菜单:
							</td>
							<td colspan="3">
								<input id="preSysMenuGroupUuid1" name="preSysMenuGroupUuid"
									class="easyui-combotree" style="width: 430px" />
							</td>
							
						</tr>
						<tr>
							<td align="right">
								公司(仓库):
							</td>
							<td colspan="3">
								<select id="officeCode1" panelHeight="auto" class="easyui-combobox" name="officeCode" style="width: 430px" >
								</select>
							</td>
						</tr>
						
						<tr>
							<td align="center" colspan="4">
								<a href="#" id="saveDivButton"  class="easyui-linkbutton" name=""
									onclick="provinguser()">保存</a>
								<a href="#" class="easyui-linkbutton" id="closeDivButton"
									onclick="closewicket('MenuGroup')">关闭</a>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>