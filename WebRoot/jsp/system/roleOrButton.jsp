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
	<!--  角色与权限管理 2012年2月27日 19:14:41-->
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
		var menuarrayallQButton=new Array();
		var roleButton=new Array();
		var nones,noneList;
		$(function(){
			__layout_buttonManger();//伸缩按钮控制
			__menuitem_treeManger();//菜单树形结构初始化
			__roledatagirdManger();//角色列表初始化
		});
	</script>
	<script type="text/javascript">
			function __layout_buttonManger(){ 
				$(".layout-button-left").remove();//删除左侧伸缩按钮
				$(".layout-button-right").remove();//删除右侧伸缩按钮
				$(".layout-button-up").remove();//删除右侧伸缩按钮
			}
			function __menuitem_treeManger(){ //加载树形结构时候需要状态是有效的
			var isoUrl="";
				if(<%=scue.getUserId() != null
								&& scue.getUserId().equals(
										CommonUtil.SYSTEM_ADMIN) ? true : false%>){
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getModuleGroupMenuitemTree1&filter=status&q=<%=CommonUtil.Active%>&date="+new Date();
				}else{
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getModuleGroupMenuitemUserTree1&filter=status&q=<%=CommonUtil.Active%>&date="+new Date();
				}
				$("#menuitem_tree").tree({//加载菜单树形结构
					url:isoUrl,
					checkbox:true,
					onClick:function(node){
						var noderoledata = $('#roledatagird').datagrid('getSelected');
						if(noderoledata==null){
							alert('请选择相应的角色再给予分配权限按钮权限信息！');
						}else{
							unSelect_viewButton_tree();//如果在没有选择了角色就去点击菜单权限。那么先清空菜单权限的选择。否则会出错
							rolemenuButtonButton(node);
						}
					}
				});
				setTimeout(function(){
					collapseAll();
				},1000);
			}
			function __roledatagirdManger(){
				var isoUrl="";
				if(<%=scue.getUserId() != null
								&& scue.getUserId().equals(
										CommonUtil.SYSTEM_ADMIN) ? true : false%>){
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType=SysRoleModel&filter=status&q=<%=CommonUtil.Active%>";
				}else{
					//isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType=SysRoleModel&oc=true&filter=status&q=<%=CommonUtil.Active%>";
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=rolORdeAdmin";
				}
				$('#roledatagird').datagrid({//加载角色列表初始化信息
					height:575,
					pagination:true,
					fitColumns: true,
					nowrap:true,
					rownumbers:true,
					showFooter:true,
					url:isoUrl,
					pageSize:20, //每页显示记录数
					pageList:[10,20,30,40,50], //可调整每页显示的记录数
					idField:'sysRoleUuid,officeCode',
					sortName:'roleName',
					sortOrder:'asc',
					onClickRow:function(rowIndex,rowDate){
						unSelect_menuitem_tree();//如果在没有选择了角色就去点击菜单权限。那么先清空菜单权限的选择。否则会出错
						rolemenuButton();
						qingkongButton();
					}
				});
			}
			
			//获取某菜单所拥有的按钮权限
			function rolemenuButtonButton(nodes){
				var noderoledata = $('#roledatagird').datagrid('getSelected');
				//alert($.toJSON(noderoledata));
				if(noderoledata==null){
					alert('请选择相应的角色再给予分配权限按钮权限信息！');
				}else{
					setCheckedOfficetree(nodes,'menuitem_tree');
					$("#viewButton_tree").tree({
						url:"<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectviewButtonall&sysMenuItemUuid="+nodes.id+"&date="+new Date(),
						checkbox:true,
						onClick:function(node){
							setCheckedOfficetree(node,'viewButton_tree'); //当单击的时候选中前方的——复选框
						}
					});
					setTimeout(function(){
						$.ajax({
				   			type: "POST",
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectMenuButtonall&date="+new Date(),
						    data: "roleuuid="+noderoledata.sysRoleUuid+"&menuuuid="+nodes.id,
						    dataType:'json',
						    success: function(date){
								getChildrensssButton();//取“按钮”中所有数据
						    	$.each(date,function(k,obj){//所有输入该角色的菜单k为58
						  			$.each(menuarrayallQButton,function(uuid,menuarrayone){
						  				if(obj.sysViewButtonUuid==menuarrayone.id){
						  					$('#viewButton_tree').tree('check',menuarrayone.target);
						  				}
						  			});
							  	});
						   }
						});
					},200);
				}
			}
			//添加节点为选中
			function setCheckedOfficetree(node,treeid){
				$('#'+treeid).tree('check',node.target);
			}
			function unSelect_menuitem_tree(){//如果在没有选择了角色就去点击菜单权限。那么先清空菜单权限的选择。否则会出错
				var nodesQuanBu=$('#menuitem_tree').tree('getRoots');
				$.each(nodesQuanBu,function(id,node){
					$('#menuitem_tree').tree('select',node);
				});
			}
			function unSelect_viewButton_tree(){//如果在没有选择了角色就去点击菜单权限。那么先清空菜单权限的选择。否则会出错
				var nodesQuanBu=$('#viewButton_tree').tree('getRoots');
				$.each(nodesQuanBu,function(id,node){
					$('#viewButton_tree').tree('select',node);
				});
			}
			//获取某角色所拥有的菜单 
			function rolemenuButton(){
				var noderole=$('#roledatagird').datagrid('getSelected');
				$.ajax({
		   			type: "POST",
				    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectMenuitemall&date="+new Date(),
				    data: "selectroleid="+noderole.sysRoleUuid+"",
				    success: function(date){
				    	var menujson=eval ("(" + date + ")");
						getChildrensss();
						qingkongsuoyoushuxing();
				    	$.each(menujson,function(k,obj){
					  		$.each(menuarrayallQ,function(uuid,menuarrayone){
				  				if(obj.sysMenuItemUuid==menuarrayone.id){
				  					$('#menuitem_tree').tree('check',menuarrayone.target);
				  				}
					  		});
					  	});
  					}
				});
			}
		//取“菜单树”中所有数据
		function getChildrensss(){
			if(menuarrayallQ.length==0){
				//alert("node为空"+menuarrayall.length);
				node = $('#menuitem_tree').tree('getSelected');
				if (node){
					var children = $('#menuitem_tree').tree('getChildren', node.target);
				} else {
					var children = $('#menuitem_tree').tree('getChildren');
				}
				for(var i=0; i<children.length; i++){
					var b = $('#menuitem_tree').tree('isLeaf', children[i].target);
					if(b){
					  menuarrayallQ.push(children[i]);
					}
				}
			}
		}	
		//清空 “菜单” 树形
		function qingkongsuoyoushuxing(){
			$.each(menuarrayallQ,function(uuid,menuarrayone){
	 			$('#menuitem_tree').tree('uncheck',menuarrayone.target);
	 		});
		}
		//取“按钮”中所有数据
		function getChildrensssButton(){
			menuarrayallQButton=new Array();
			//if(menuarrayallQButton.length==0){
				node = $('#viewButton_tree').tree('getSelected');
				if (node){
					var children = $('#viewButton_tree').tree('getChildren', node.target);
				} else {
					var children = $('#viewButton_tree').tree('getChildren');
				}
				for(var i=0; i<children.length; i++){
					var b = $('#viewButton_tree').tree('isLeaf', children[i].target);
					if(b){
					  menuarrayallQButton.push(children[i]);
					}
				}
			//}
		}	
		//清空 “按钮” 树形
		function qingkongsuoyoushuxingButton(){
			$.each(menuarrayallQButton,function(uuid,menuarrayone){
	 			$('#viewButton_tree').tree('uncheck',menuarrayone.target);
	 		});
		}
		//点击保存获取被选中信息（角色对应的菜单）
		function saveRoleMenu(){
			var noderole=$('#roledatagird').datagrid('getSelected');//officeCode,sysRoleUuid两个是保存sys_role_menu_item该表必须的条件
			if(noderole!=null){//以下是循环出选中的菜单插入sys_role_menu_item该表中
				var arrays=new Array();
				var nodes = $('#menuitem_tree').tree('getChecked');
				//alert($.toJSON(nodes));
				var rolemenujsons =new Object();
					for(var i=0; i<nodes.length; i++){
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
						    dataType:'json',
						    success: function(date){
						    	if(date.result){
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
			}else{
				alert("请选中一个角色再执行给予分配菜单！");
			}
		}
		//保存（菜单对应的按钮权限）saveRoleButtonOrMenuButton
		function saveMenuButton(){
			var menutree = $('#menuitem_tree').tree('getSelected');
			var roledatagrid=$('#roledatagird').datagrid('getSelected');
			if(menutree==null){
				alert('请选择菜单项再给予分配按钮权限！');
			}else {
				var buttontree = $('#viewButton_tree').tree('getChecked');
				//alert($.toJSON(menutree)+" #### "+$.toJSON(roledatagrid)+" #### "+$.toJSON(buttontree));
				var rolejsons,menujsons,buttonjsons;
				var rolearrays=new Array();
				var menuarrays=new Array();
				var buttonarrays=new Array();
				
				for(var i=0; i<buttontree.length; i++){
					var buttons =new Object();
					buttons.sysViewButtonUuid=buttontree[i].id;
					buttonarrays.push(buttons);
				}
				
				var rolejson =new Object();
				rolejson.sysRoleUuid=roledatagrid.sysRoleUuid;
				rolejson.officeCode=roledatagrid.officeCode;
				rolejson.status=roledatagrid.status;
				rolearrays.push(rolejson);
				
				var menus =new Object();
				menus.sysMenuItemUuid=menutree.id;
				menuarrays.push(menus);
				
				buttonjsons=$.toJSON(buttonarrays);
				rolejsons=$.toJSON(rolearrays);
				menujsons=$.toJSON(menuarrays);
				
				//此下取出所有菜单信息并封装和角色！因 : 如果选择分配的菜单暂时还没有属于该角色时！则不能保存成功！
						var arrays=new Array();
						var nodes = $('#menuitem_tree').tree('getChecked');
						var rolemenujsons =new Object();
							for(var i=0; i<nodes.length; i++){
								var b = $('#menuitem_tree').tree('isLeaf', nodes[i].target);
								if(b){
									var rolemenujson =new Object();
									rolemenujson.sysRoleUuid=roledatagrid.sysRoleUuid;
									rolemenujson.sysMenuItemUuid=nodes[i].id;
									rolemenujson.officeCode=roledatagrid.officeCode;
									arrays.push(rolemenujson)
								}
							}
						if(nodes==""){
							var rolemenujson =new Object();
							rolemenujson.sysRoleUuid=roledatagrid.sysRoleUuid;
							arrays.push(rolemenujsons)
						}
						rolemenujsons=$.toJSON(arrays);
				//alert("角色："+rolejsons);
				//alert("菜单："+menujsons);
				//alert("按钮："+buttonjsons);
			LogisticsOrderManager.object2base64(buttonjsons,function(buttonjsons){
				LogisticsOrderManager.object2base64(rolejsons,function(rolejsons){
					LogisticsOrderManager.object2base64(menujsons,function(menujsons){
						LogisticsOrderManager.object2base64(rolemenujsons,function(rolemenujsons){
							$.ajax({
					   			type: "POST",
							    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=saveRoleButtonOrMenuButton&date="+new Date(),
							    data: "buttonjsons="+buttonjsons+"&rolejsons="+rolejsons+"&menujsons="+menujsons+"&rolemenujsons="+rolemenujsons+"",
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
						});
					});
				});
			});
				
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
		//清空角色对应的按钮权限
		function qingkongButton(){
			/**$("#viewButton_tree").tree({
				url:null
			});*/
			$("#viewButton_tree").tree("loadData",{rows:{}});
		}
	</script>
</head>
<body class="easyui-layout">
	<!-- 上方 -->
	<!-- <div region="north" title="操作"
		style="height: 57px; background: #efefef; float: left">
		<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save"
			style="float: left" onclick="saverolemenu()">保存</a>
	</div> -->
	<!-- 右 -->
	<div region="east" title="角色拥有按钮的权限"
		style="width: 200px; background-color: #efefef;">

		<div class="easyui-layout" fit="true" style="background: #ccc;">
			<div id="viewqueryTerm" region="north"
				style="background-color: #efefef; height: 29px;">
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-save" style="float: left" onclick="saveMenuButton()">保存</a>
			</div>
			<div region="center">
				<ul id="viewButton_tree" class="easyui-tree">
				</ul>
			</div>
		</div>
	</div>
	<!-- 左 -->

	<div region="west" title="角色列表"
		style="width: 300px; padding1: 1px; overflow: hidden;">
		<table id="roledatagird" singleSelect="true" idField="itemid"
			fit="true">
			<thead>
				<tr>
					<th field="roleName" width="130" align="center">
						角色中文名
					</th>
					<th field="activeDate" width="130" align="center">
						生效日期
					</th>
					<th field="expiredDate" width="130" align="center">
						失效日期
					</th>
					<!-- <th field="email" width="80" align="center">
						角色类别
					</th> -->
				</tr>
			</thead>
		</table>
	</div>
	<!-- 中 -->
	<div region="center" title="角色拥有的菜单" style="background-color: #efefef;">
		<div class="easyui-layout" fit="true" style="background: #ccc;">
			<div id="viewqueryTerm" region="north"
				style="background-color: #efefef; height: 29px;">
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="accordion-collapse" style="float: left;"
					onclick="expandAll()">展开</a>
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="accordion-expand" style="float: left;"
					onclick="collapseAll()">关闭</a>
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-save" style="float: left" onclick="saveRoleMenu()">保存</a>
			</div>
			<div region="center">
				<ul id="menuitem_tree" class="easyui-tree">
				</ul>
			</div>
		</div>
	</div>
</body>
</html:html>