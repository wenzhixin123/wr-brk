<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page
	import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%
	String path = request.getContextPath();
	SessionContextUserEntity scue = SessionContextUserEntity
			.currentUser();
%>
<html:html>
<head>
	<!--  用户与角色管理 2012年2月27日 19:15:31-->
	<jsp:include page="../../common/imp_dwr.jsp"></jsp:include>
	<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
	<style type="text/css">
#tableQueryForm td {
	font-size: 12px;
}
</style>
	<script type="text/javascript">
	var node=null;
	var menuarrayallQ=new Array();
	$(function(){
			$(".layout-button-left").remove();//删除左侧伸缩按钮
			$(".layout-button-right").remove();//删除右侧伸缩按钮
			$(".layout-button-up").remove();//删除上侧伸缩按钮
			var isoUrl="";
				if(<%=scue.getUserId() != null
								&& scue.getUserId().equals(
										CommonUtil.SYSTEM_ADMIN) ? true : false%>){
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType=SysUserModel&filter=status&q=<%=CommonUtil.Active%>";
				}else{
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=rolUserdeAdmin";
				}
			$('#userdatagird').datagrid({
					height:($(document).height()*0.94),
					rownumbers:true,
					pagination:true,
					striped:true,
					url:isoUrl,
					pageSize:20, //每页显示记录数
					pageList:[10,20,30,40,50], //可调整每页显示的记录数
					singleSelect: true,  // 是否单选（false为可多选）
					idField:'userUuid',
					sortName:'userName',
					sortOrder:'asc',
					onDblClickRow:function(rowIndex,rowData){
						//$('#userdatagird').datagrid('endEdit', lastIndex);
						//$('#userdatagird').datagrid('beginEdit', rowIndex);
						//lastIndex = rowIndex;
					},
					onClickRow:function(rowIndex,rowData){//当点击表格当中的数据是执行
						//$('#userdatagird').datagrid('endEdit',lastIndex);
						//alert(rowData.userUuid);
						rolemenuButton();
					}
				});
				
			/** 增加多选 如果客户需要 将此处添加进入上方的datagrid中并把多选更改则可以使用。方法不会有错误。可放心使用
				frozenColumns:[[
						{field:"ck",checkbox:true}
					]],
			 **/
			//加载部门分组
			$("#office_tree").tree({
				url:"<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectOfficeitemtree1&date="+new Date(),
				//checkbox:true,
				onClick:function(node){
					//userselect(node.id);
					SysOfficeManager.get(node.id,function(model){
						if(model){
							userselect(model.officeCode);
						}
					});
				}
			});
			//加载角色树形
			$("#role_tree").tree({
				url:"<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectroleAlltreeoffice&date="+new Date(),
				checkbox:true,
				onClick:function(node){
					setCheckedOfficetree(node,'role_tree');//添加某一个节点为选中
				}
			});
	});
	
</script>
	<script type="text/javascript">
	//获取某用户所拥有的角色 
	function rolemenuButton(){
		var noderole=$('#userdatagird').datagrid('getSelected');
		var ids = [];
		var rows = $('#userdatagird').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].userUuid);
		}
		//alert(ids.join(','));
		if(noderole!=null&rows.length<2){
			$.ajax({
	   			type: "POST",
			    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectuserroleall&date="+new Date(),
			    data: "selectroleid="+noderole.userUuid+"",
			    success: function(date){
			    	var rolejson=eval ("(" + date + ")");
					getChildrensss();
					qingkongsuoyoushuxing();
			    	$.each(rolejson,function(k,obj){
			  			$.each(menuarrayallQ,function(uuid,menuarrayone){
			  				if(obj.sysRoleUuid==menuarrayone.id){
			  					$('#role_tree').tree('check',menuarrayone.target);
			  				}
			  			});
				  	});
				}
			});
		}else{
			getChildrensss();
			qingkongsuoyoushuxing();
		}
	}
	//取菜单树形中所有数据
		function getChildrensss(){
			if(menuarrayallQ.length==0){
				//alert("node为空"+menuarrayall.length);
				node = $('#role_tree').tree('getSelected');
				if (node){
					var children = $('#role_tree').tree('getChildren', node.target);
				} else {
					var children = $('#role_tree').tree('getChildren');
				}
				for(var i=0; i<children.length; i++){
					var b = $('#role_tree').tree('isLeaf', children[i].target);
					if(b){
					  menuarrayallQ.push(children[i]);
					}
				}
			}
		}	
	//清空角色树形
	function qingkongsuoyoushuxing(){
		$.each(menuarrayallQ,function(uuid,menuarrayone){
 			$('#role_tree').tree('uncheck',menuarrayone.target);
 		});
	}
	//主动选中树形结构中的框
	function setCheckedOfficetree(node,treeid){
		$('#'+treeid).tree('check',node.target);
		//var getchecked=$('#'+treeid).tree('getChecked',node.target);//去出所有选中信息
		//userselect(node.id);
	}
	//取出所有树形结构中被选中的框信息
	function getCheckedOfficetree(treeid){
			var nodes = $('#'+treeid).tree('getChecked');
			var selectEQoffice = '';
			for(var i=0; i<nodes.length; i++){
				var b = $('#'+treeid).tree('isLeaf', nodes[i].target);
				if(b){
					if (selectEQoffice != '') selectEQoffice += ',';
					selectEQoffice += nodes[i].id;
				}
			}
			alert(selectEQoffice);
	}
	//查询根据部门UUID查询所属员工
	function userselect(departmentCode){
		$("#userdatagird").datagrid('load',{
            __officeCode:departmentCode
       	});
	}
	//去出所有被选中信息(暂时无用)
	function xuanzhogn(treeid){
		var nodes = $('#'+treeid).tree('getChecked');
		var s = '';
		for(var i=0; i<nodes.length; i++){
			if (s != '') s += ',';
			s += nodes[i].text;
		}
		alert(s);
	}
	//取出所有选中信息
	function quxuanzhong(){
		saverolemenusss();
		//saverolemenu('office_tree');
		//saverolemenu('user_tree');
		//saverolemenu('role_tree');
	}
	function saverolemenusss(){//保存选择的用户与角色关系（多对多）
		var userjsons,rolejsons;
		var userarrays=new Array();
		var rolearrays=new Array();
		var rows = $('#userdatagird').datagrid('getSelections');
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				var userjson =new Object();
				userjson.userUuid=rows[i].userUuid;
				userjson.officeCode=rows[i].officeCode;
				userarrays.push(userjson);
			}
			
			var nodes = $('#role_tree').tree('getChecked');
			for(var i=0; i<nodes.length; i++){
				var b = $('#role_tree').tree('isLeaf', nodes[i].target);
				if(b){
					var rolejson =new Object();
					rolejson.sysRoleUuid=nodes[i].id;
					rolearrays.push(rolejson);
				}
			}
			
			userjsons=$.toJSON(userarrays);
			rolejsons=$.toJSON(rolearrays);
			LogisticsOrderManager.object2base64(userjsons,function(userjsons1){
				if(userjsons1){
					LogisticsOrderManager.object2base64(rolejsons,function(rolejsons1){
						if(rolejsons1){
						$.ajax({
				   			type: "POST",
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=saveUserRoleManagerMangToMang&date="+new Date(),
						    data: "userAll="+userjsons1+"&ruleAll="+rolejsons1,
						    dataType:'json',
						    success: function(date){
						    	if(date==null||date.relust){
						    		alert('保存成功 , 注：(如果是当前登陆角色)刷新整个页面后可加载新权限!');
						    	}else if(date.error){
						    		alert("操作失败！错误信息如下："+date.error);
						    	}else{
						    		alert("操作失败、暂无返回信息。");
						    	}
				 			}
						});
					 }
					});
				}
			});
		}else{
			alert("请选中至少一个用户再执行给予分配角色！");
		}
	}
</script>
</head>
<body class="easyui-layout">
	<!-- 上方 -->
	<!-- <div region="north" title="操作"
		style="height: 57px; background: #efefef; float: left">
		<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save"
			style="float: left" onclick="quxuanzhong()">保存</a>
	</div> -->
	<!-- 下方 
	<div region="south" title="暂时没有用" split="true"
		style="height: 100px; padding: 10px; background: #efefef;">
		<div class="easyui-layout" fit="true" style="background: #ccc;">
			<div region="center">
				sub center
			</div>
			<div region="east" split="true" style="width: 500px;">
				sub center
			</div>
		</div>
	</div>-->

	<!-- 右 -->
	<!-- <div region="east" iconCls="icon-reload" title="菜单列表"
		style="width: 300px;">
		<ul id="menuitem_tree" class="easyui-tree">
		</ul>
	</div> -->

	<!-- 左 -->
	<div region="west" title="组织机构" style="width: 300px;">
		<ul id="office_tree" class="easyui-tree">
		</ul>
	</div>

	<!-- 中 -->
	<div region="center" style="overflow: hidden;">
		<div class="easyui-layout" fit="true" style="background: #ccc;">
			<div region="center" title="用户列表">
				<table id="userdatagird" singleSelect="true" idField="itemid"
					fit="true">
					<thead>
						<tr>
							<th field="userCode" width="130" sortable=true>
								用户编码
							</th>
							<th field="userName" width="130" sortable=true>
								用户名称
							</th>
							<th field="userNameEn" width="130" sortable=true>
								用户名称(英文)
							</th>
							<th field="position" width="130" align="right" sortable=true>
								职位描述
							</th>
							<th field="email" width="175" align="center" sortable=true>
								电子邮件
							</th>
						</tr>
					</thead>
				</table>
			</div>
			<div region="east" title="角色列表" style="width: 150px;">
				<div class="easyui-layout" fit="true" style="background: #ccc;">
					<div id="viewqueryTerm" region="north" collapsible="true"
						style="background-color: #efefef;">
						<!-- 保存按钮 -->
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-save" style="float: left" onclick="quxuanzhong()">保存</a>
					</div>
					<div region="center">
						<ul id="role_tree" class="easyui-tree">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html:html>