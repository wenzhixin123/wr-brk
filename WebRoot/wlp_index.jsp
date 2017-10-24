<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sinotrans.gd.wlp.system.model.SysMenuItemModel" %>
<%@ page import="com.sinotrans.gd.wlp.system.model.SysMenuGroupModel" %>
<%@ page import="com.sinotrans.gd.wlp.system.model.SysModuleModel" %>
<%@page import="com.sinotrans.gd.wlp.common.web.RcUtil"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<%@page import="com.sinotrans.framework.core.support.servlet.Http"%> 

<%
	String path = request.getContextPath();

	List<SysMenuItemModel> menuItems = (List<SysMenuItemModel>)request.getSession().getAttribute("menuItems");
	List<SysMenuGroupModel> menuGroups = (List<SysMenuGroupModel>)request.getSession().getAttribute("menuGroups");
	List<SysModuleModel> modules = (List<SysModuleModel>)request.getSession().getAttribute("modules");
	SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
	
	WebApplicationContext context = (WebApplicationContext)config.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	Http arapDomain = (Http)context.getBean("arapDomain");
	Http arapRootAddress = (Http)context.getBean("arapRootAddress");
	String arapDomainStr = arapDomain.getServletUrl();
	String arapRootAddressStr = arapRootAddress.getServletUrl();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>王牌WMS</title>
	<jsp:include page="common/imp_dwr.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/icon.css"/>
    <link href="<%=path %>/css/index.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=path %>/js/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=path %>/js/util/wlp_msgBox.js"></script>
	<script>
	
		//禁用返回按钮
		document.onkeydown = function(event) {
			if (! event) {
				event = window.event;
			}
			if (event.keyCode == 8) {
				return false;
			}
			if (event.altKey && (event.keyCode == 37 || event.keyCode == 39)) {
				return false;
			}
		};
	
		$(function(){
			/*
				$(".layout-button-left").remove();//删除伸缩按钮
			*/
			if("<%=scue.getUserId()%>"!="PUBLICGY"){
				$(".layout-button-left").trigger("click");//默认缩起左边菜单栏
				$(".panel-header").css({width:"180px",height:"18px"});//更改左边菜单栏高度
			}	
			//加载首页
			addTabs("首页","<%=path %>/systemNewsAction.do","",false);
			tabMenuEvens();
			
			try{
				document.domains='<%=arapDomainStr%>';
			}catch(e){
					
			}
		});
		function createFrame(url)
		{
			var s = "<iframe scrolling='auto' frameborder='0'  src='"+url+"' style='width:100%;height:100%;'></iframe>";
			return s;
		}
		function tabMenuEvens()
		{
			$("#centerTabs").delegate(".tabs-inner", "dblclick", function(){
				var subtitle = $(this).children(".tabs-closable").text();
				$("#centerTabs").tabs("close",subtitle);
			}).delegate(".tabs-inner", "contextmenu", function(e){
				$("#mm").menu("show", {
					left: e.pageX,
					top: e.pageY
				});
				var subtitle =$(this).children(".tabs-title").text();
				$("#mm").data("currtab",subtitle);
				$("#centerTabs").tabs("select",subtitle);
				return false;
			});
			//刷新
			$("#mm-tabupdate").click(function(){
				var currTab = $("#centerTabs").tabs("getSelected");
				var url = $(currTab.panel("options").content).attr("src");
				$("#centerTabs").tabs("update",{
					tab:currTab,
					options:{
						content:createFrame(url)
					}
				});
			});
			//关闭当前
			$("#mm-tabclose").click(function(){
				var currtab_title = $("#mm").data("currtab");
				if(currtab_title!="首页"){
					$("#centerTabs").tabs("close",currtab_title);
				}
			});
			//全部关闭
			$("#mm-tabcloseall").click(function(){
				$(".tabs-inner .tabs-closable").each(function(i,n){
					var t = $(n).text();
					$("#centerTabs").tabs("close",t);
				});
			});
			//关闭除当前之外的TAB
			$("#mm-tabcloseother").click(function(){
				$("#mm-tabcloseright").click();
				$("#mm-tabcloseleft").click();
			});
			//关闭当前左侧的TAB
			$("#mm-tabcloseleft").click(function(){
				var prevall = $(".tabs-selected").prevAll();
				if(prevall.length==0){
					return false;
				}
				prevall.each(function(i,n){
					var t=$("a:eq(0) .tabs-closable",$(n)).text();
					$("#centerTabs").tabs("close",t);
				});
				return false;
			});
			//关闭当前右侧的TAB
			$("#mm-tabcloseright").click(function(){
				var nextall = $(".tabs-selected").nextAll();
				if(nextall.length==0){
					return false;
				}
				nextall.each(function(i,n){
					var t=$("a:eq(0) .tabs-closable",$(n)).text();
					$("#centerTabs").tabs("close",t);
				});
				return false;
			});
			//取消
			$("#mm-cancel").click(function(){
				$("#mm").menu("hide");
			});
		}
		//子页面添加tabs方法
		function addTabs(title,url,newTab,closable){
			//新增单点登录结算中心 evan 2013/11/18 begin
			var ticket ="";
			if('<%=scue.getTicket()%>' != ''){
				ticket = JSON.parse('<%=scue.getTicket()%>');
			}
			if(title=="预存款管理"||title=="预存款查询"||title=="预存款退款查询"||title=="订单查询"){
				url=url.substring(url.indexOf("jsp"), url.length);
				url='<%=arapRootAddressStr%>' + url;
				if (url.indexOf("?") > -1) {
					url = url.substring(0,url.indexOf("?")+1) + 'ticket=' + ticket.content;
				} else {
					url = url + '?ticket=' + ticket;
				}
				//alert(url);
			}
			//新增单点登录结算中心 evan 2013/11/18 end
			
			var tab = $("#centerTabs").tabs("getTab",title);
			var tabCount = $("#centerTabs").tabs('tabs').length;//统计打开窗口个数(不包含首页)
			if(title=="首页"){
				$("#centerTabs").tabs("add",{
					title:title,
					iconCls:"icon_home",
					closable:closable,
					fit:true,
					width: $("#centerTabs").parent().width(), 
					height: "auto",
					content:"<iframe scrolling='no' frameborder='0' style='width:100%;height:100%' marginwidth='0px' marginheight='0px' src='"+url+"'></iframe>"
				});
			}else if(tab){
				if(newTab){
					$("#centerTabs").tabs("close",title);
					$("#centerTabs").tabs("add",{
					title:title,
					closable:closable,
					fit:true,
					width: $("#centerTabs").parent().width(), 
				 height: "auto",
					content:"<iframe scrolling='no' frameborder='0' style='width:100%;height:100%' marginwidth='0px' marginheight='0px' src='"+url+"'></iframe>"
					});
				} else {
					$("#centerTabs").tabs("select",title);
				}
			}else{
				if (tabCount <= 8) {
					$("#centerTabs").tabs("add",{
						title:title,
						closable:closable,
						fit:true,
						width: $("#centerTabs").parent().width(), 
					 	height: "auto",
						content:"<iframe scrolling='no' frameborder='0' style='width:100%;height:100%' marginwidth='0px' marginheight='0px' src='"+url+"'></iframe>"
					});
				}else{
					alert("您打开窗口太多，请关闭没用窗口!");
				}
			}
			$($("#centerTabs").tabs("getSelected").find("iframe")[0].contentWindow.document).find("input:visible:first").focus();
		}
		
		function closeTab(title) {
			$("#centerTabs").tabs("close",title);
		}
		
		function closeCurrentTab() {
			var currtab_title = $("#centerTabs").tabs("getSelected").panel("options").title;
			if(currtab_title!="首页"){
				$("#centerTabs").tabs("close",currtab_title);
			}
		}
		
		function getTabIframe(title) {
			return $("#centerTabs").tabs("getTab",title).find("iframe")[0];
		}
		
		//改变选中状态
		function changeSelected(o){
			$("[class='clicked']").removeClass("clicked");
			$(o).removeClass("mouseOverColor");
			$(o).addClass("clicked");
		}
		
		//鼠标放在菜单上时的颜色
		function mouseOverColor(o){
			if($(o).attr("class")!="clicked"){
				$(o).addClass("mouseOverColor");
			}
		}
		
		//鼠标走开时就把背景颜色去掉
		function clearColor(o){
			if($(o).attr("class")!="clicked"){
				$(o).removeClass("mouseOverColor");
			}
		}
		
		
		//退出
		function doLogoutAction(){
			try{
				window.parent.location.href="logout.action";
			}catch(e){}
		}
		
				
	</script>
	<style type="text/css">
		body {
			background: white;
		}
		.accordion .accordion-header{
			   	background: url("js/easyui/themes/default/images/panel_title.png") repeat-x scroll 0 0 transparent;
			    border: 1px solid #99BBE8;
			    color: #15428B;
			    font-size: 12px;
			    font-weight: bold;
			    line-height: 15px;
			    padding: 5px;
			    position: relative;
		}
		
		.logo .info .changePasswd {
			padding:5px
		}
	</style>
	<script>
		//以下这段script包含了修改密码功能所必须的js代码
		$(function(){
			//css定义
			$('#changePSWForm').attr({
				width:"100%",
				heigh:"100%"
			});
			$("#changePSWTable td:even").attr({
				align:"right"
			});
			$("#changePSWTable td:even").css({
				width:"30%"
			});
			$("#changePSWTable td:odd").attr({
				align:"left"
			});
			$("#changePSWTable td:odd").css({
				width:"60%"
			});
			$("#btnTable td:even").css({
				width:"39%"
			});
			$("#btnTable td:odd").css({
				width:"60%"
			});
			//对话框定义
			$('#changePSWDiv').dialog({
				onClose:function(){
					$('#originalPassword').val('');
					$('#newPassword').val('');
					$('#verifyPassword').val('');
				}
			});
			$('#changePSWDiv').dialog('close');

			//绑定弹出对话框事件
			$('#changePSW').click(function(){
				$('#changePSWDiv').dialog({
					draggable:false,
					modal:true
				});
				$('#changePSWDiv').dialog('open');
				$('#originalPassword').focus();
			});
			
			function bol(){
				var type=0
				var reg=/[a-zA-Z0-9_\-]{6,20}$/g;
				if(!reg.test(arguments[0])){
					alert("长度应为6-20个字符，只能由英文字母、数字和下划线_ - 组成");
					type=1;
				}
				return type;
			}
			
			$("#originalPassword").unbind("keydown").keydown(function(e){
				if(e.keyCode == 13 ) {
					var originalPassword=$(this).val();
					if(bol(originalPassword)==1){
						$(this).val("");
						$("#originalPassword").unbind("foucs").focus();
					}else{
						$("#newPassword").unbind("foucs").focus();
					}
				}
			});
			
			$("#newPassword").unbind("keydown").keydown(function(e){
				if(e.keyCode == 13 ) {
					var newPassword=$(this).val();
					if(bol(newPassword)==1){
						$(this).val("");
						$("#newPassword").unbind("foucs").focus();
					}else{
						$("#verifyPassword").unbind("foucs").focus();
					}
				}
			});
			
			$("#verifyPassword").unbind("keydown").keydown(function(e){
				if(e.keyCode == 13 ) {
					var verifyPassword=$(this).val();
					if(bol(verifyPassword)==1){
						$(this).val("");
						$("#verifyPassword").unbind("foucs").focus();
					}else{
						if($("#newPassword").val()===verifyPassword){
							$('#dialog_saveBtn').click();
						}else{
							alert("两次输入的密码不一致,请重新输入!");
							$("#verifyPassword").val("");
							$("#verifyPassword").unbind("foucs").focus();
						}
					}
				}
			});
			
			//保存按钮
			$('#dialog_saveBtn').click(function(){
				if(!$('#changePSWForm').form('validate')){return;}
				//验证两次输入的密码是否一致
				if(!($('#newPassword').val()===$('#verifyPassword').val())){
					alert("两次输入的密码不一致!");
					$('#verifyPassword').val('');
					$('#verifyPassword').focus();
					return;
				}
				var url="<%=request.getContextPath()%>"
					+"/servlet/SinotransServlet?cmd=changeSysUserPassword";
				$.ajax({
					url:url,
					type: "POST",
					data:{
						originalPsw:$('#originalPassword').val(),
						newPassword:$('#newPassword').val(),
						userCode:$('#userCode').val(),
						userName:$('#userName').val()
					},
		            success: function(result){
						if(result==='true'){
							alert("密码修改成功,下次登录开始生效!");
							$('#changePSWDiv').dialog('close');
						}else{
							error(result);
							$('#originalPassword').val('');
							$('#newPassword').val('');
							$('#verifyPassword').val('');
						}
		            },
		            error:function(XMLHttpRequest, textStatus, errorThrown){
		            	error(errorThrown);
		            	$('#originalPassword').val('');
						$('#newPassword').val('');
						$('#verifyPassword').val('');
						$("#originalPassword").unbind("foucs").focus();
			        }
				});
			});

			//返回按钮
			$('#dialog_returnBtn').click(function(){
				$('#changePSWDiv').dialog('close');
			});
		});
	</script>
	<script type="text/javascript">
		//获得officeName并显示
		$(function(){
			BasCommonManager.getObjByProperty({
				type:"SysOffice",
				keyProperty:"officeCode",
				keyValue:"<%=scue.getOfficeCode()%>"
			},function(rs){
				var rowObj = $.parseJSON(rs);
				if(rowObj!=null && rowObj[0]){
					$('#officeName').html(rowObj[0].officeName);
				}else{
					alert("没有分配组织机构。");
				}
			});
		});
	</script>
	<script type="text/javascript">
	//自动展开收缩
	$(function(){
		//$('.layout-expand').mouseover(function(){autoExpend();});
		//$('.layout-panel').filter('.layout-panel-center').mouseover(function(){autoCollapse();});
	});
	
	//WLPDC-59 检查当前用户是否为公共帐户. 如果是, 则不能修改密码
	$(function() {
		/* SysOfficeManager.isMatchOfficeCodeTypeByString('2',function(answer) {
			if(answer) {
				$('#changePSWSpan').html('');
			} 
		});*/
	});
	
	//展开左边菜单的方法
	function autoExpend(){
		var panels = $.data(document.body, 'layout').panels;
		if(panels.expandWest){
			if (panels.west.panel('options').onBeforeExpand.call(panels.west) == false) return;
			panels.expandWest.panel('close');
			panels.west.panel('panel').stop(true,true);
			panels.west.panel('open').panel('resize', {left: -panels.west.panel('options').width});
			panels.west.panel('panel').animate({
				left: 0
			}, function(){
				//setSize(document.body);
				panels.west.panel('options').onExpand.call(panels.west);
			});
		}
	}
	//收起左边菜单的方法
	function autoCollapse(){
		$(".layout-button-left").trigger("click");
	}
	function isVisible(pp){
		if (!pp) return false;
		if (pp.length){
			return pp.panel('panel').is(':visible');
		} else {
			return false;
		}
	}
	</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" class="head">
		<a class="logo" href="#"><i>王牌</i><span class="name">WMS</span></a>
			<div class="info">
				<span><span id="officeName"></span>&nbsp;|&nbsp;</span>
				<span><span class="name"><%=scue.getUsername() %></span>&nbsp;|&nbsp;</span>
				<span id="changePSWSpan"><a href="#" id="changePSW" class="changePasswd">修改密码</a>&nbsp;|&nbsp;</span>
				<span><a href="javascript:doLogoutAction();" class="logout">退出 </a></span>
			</div>
		<a href="#" class="return">返回首页</a>
    </div>
	<div id="west" region="west" title="菜单栏" style="width:150px;" split="true">
		<div id="leftAccordion" class="easyui-accordion" fit="true" align="center" animate="false">
			<%
				if(!RcUtil.isEmpty(menuGroups) && menuGroups.size()>0){
					for(int i=0;i<menuGroups.size();i++){
			%>
				<div title="<%=menuGroups.get(i).getMenuGrpName() %>" align="center" style="padding-top:12px;background-color:white">
					<%
						if(!RcUtil.isEmpty(menuItems) && menuItems.size()>0){
							for(int j=0;j<menuItems.size();j++){
								if(menuItems.get(j).getSysMenuGroupUuid().equals(menuGroups.get(i).getSysMenuGroupUuid())){
					%>
							<div style="width:100%; height:23px;border-bottom:1px solid gainsboro;text-align: left;line-height:23px;padding-left:14px;font-size: 13px" onmouseout="clearColor(this)" onMouseover= "this.style.cursor= 'pointer';mouseOverColor(this);" onclick="javascript:changeSelected(this);addTabs('<%=menuItems.get(j).getMenuItemName() %>','<%=path+"/"+menuItems.get(j).getMenuItemUrl()+"?menuGroupUuid="+menuItems.get(j).getSysMenuGroupUuid() %>','',true);">
								▶&nbsp;&nbsp;&nbsp;<%=menuItems.get(j).getMenuItemName() %>
							</div>
					<%
								}
							}
						}
					%>
				</div>
			<%
					}
				}
			%>
		</div>
	</div>
	<div region="center" style="overflow: hidden;">
		<div class="easyui-tabs" id="centerTabs" fit="true" border="false">
		</div>
	</div>
	
	<div id="changePSWDiv" style="width: 440px; height: 300px;margin-top: 30px;" title="修改密码">
		<form id="changePSWForm">
			<table id="changePSWTable" style="width: 100%;">
				<tr>
					<td align="right">用户名:</td>
					<td align="right">
						<input type="text" id="userName" name="userName" style="color: #6D6D6D;width: 110px" readonly="readonly" value="<%=scue.getUsername()%>" class="easyui-validatebox"/>
						<input type="hidden"  id="userCode" name="userCode" class="easyui-validatebox"  value="<%=scue.getUserId()%>"/>
					</td>
				</tr>
				<tr>	
					<td align="right">原密码:</td>
					<td align="right">
						<input type="password" id="originalPassword" style="width:110px" name="password" class="easyui-validatebox"  required="true"/>
					</td>
				</tr>
				<tr>	
					<td align="right">新密码:</td>
					<td align="right">
						<input type="password" id="newPassword" name="password" style="width:110px" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>	
					<td align="right">确认:</td>
					<td align="right">
						<input type="password" id="verifyPassword" name="verifyPassword" style="width:110px" class="easyui-validatebox" required="true" />
					</td>
				</tr>
			</table>
			<table id="btnTable" style="width: 100%;">
				<tr >
					<td align="right" >
						<a href="#" id="dialog_saveBtn" class="easyui-linkbutton" name="" 
							style="margin-top: 20px">保存</a>
					</td>
					<td align="left">
						<a href="#"  id="dialog_returnBtn" class="easyui-linkbutton" 
							style="margin-left: 20px;margin-top: 20px;">返回</a>
					</td>
				</tr>
			</table>
		</form>
	</div>		
	<div id="mm" class="easyui-menu" style="width:150px;display:none;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-cancel">取消</div>
	</div>		
</body>
</html>