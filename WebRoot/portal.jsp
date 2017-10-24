<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="com.sinotrans.gd.wlp.system.model.SysNewsModel"%>
<%@page import="com.sinotrans.gd.wlp.common.web.RcUtil"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String path = request.getContextPath();
	List<SysNewsModel> bzwdList = (List<SysNewsModel>)request.getSession().getAttribute("bzwdList");
	List<SysNewsModel> tglList = (List<SysNewsModel>)request.getSession().getAttribute("tglList");
	List<SysNewsModel> xxfbList = (List<SysNewsModel>)request.getSession().getAttribute("xxfbList");
	//SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
	Map<String,String> si=new HashMap<String,String>();
	si = (Map<String,String>)request.getSession().getAttribute("snMap");
	
	//String snTitle="郑州移动紫鼎仓库简介";
	String snTitle=si.get("snTitle");
	if(RcUtil.isEmpty(snTitle)){
		snTitle="暂无简介";
	}
	//si.put("0","仓储服务的内容包括：入库、货物存储、理货包装、出库和货物装卸等等。");
	//si.put("1","仓库总面积约为8700㎡，其中7000㎡用于郑州移动仓储服务。");
	//si.put("2","地址：河南省郑州市管城区金岱工业园紫鼎食品仓库11号库（文治路与新107国道交叉口）");
	//si.put("3","联系电话：0371-63779338&nbsp;&nbsp;传&nbsp;真：0371-66928069");
	//si.put("images","true");
	//si.put("td","郑州移动紫鼎仓库是中国外运广东有限公司为河南移动郑州分公司提供优质的第三方物流仓储服务的仓库。");
	
	
	
%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<jsp:include page="common/imp_dwr.jsp"></jsp:include>
		<jsp:include page="common/imp_easyui.jsp"></jsp:include>
		<script type="text/javascript" src="<%=path%>/fancybox/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path%>/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	    <script type="text/javascript" src="<%=path%>/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	    <script type="text/javascript" src="<%=path%>/fancybox/jquery.slideViewerPro.1.5.js" charset="UTF-8"></script>
    	<script type="text/javascript" src="<%=path%>/fancybox/jquery.timers.js"></script>
	    <link rel="Stylesheet" type="text/css" href="<%=path%>/fancybox/jquery.fancybox-1.3.4.css"  media="screen"/>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/svwp_style.css" />
		<script language="javascript" type="text/javascript">
		
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

			$(document).ready(function(){
				$("div#my-folio-of-works").slideViewerPro({
					 thumbs: 4,
					 autoslide: false,
					 asTimer: 3000,
					 typo: true,
					 galBorderWidth: 1,
					 thumbsBorderOpacity: 0,
					 buttonsTextColor: "#707070",
					 buttonsWidth: 20,
					 thumbsRightMargin: 1,//缩略图之间的间隙
					 thumbsPercentReduction: 18,//缩略图是原图的22%，与buttonsWidth对应，越少，%分比则可增加
					 thumbsActiveBorderOpacity: 0.5,//透明度
					 thumbsActiveBorderColor: "aqua",
					 thumbsActiveBorderColor: "#DDDDDD",
					 shuffle: false  //随机排序
				});
				$("#svwp0").hide();
				
			   $("a[rel=example_group]").fancybox({
					'transitionIn':'none',
					'transitionOut':'none',
					'titlePosition':'over',
					'titleFormat':function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});
				
				$("a[rel=example1_group]").unbind("click").click(function(){
					var _this=$(this);
					var title=_this.html();
					var url=_this.attr("url");
					if(url){
						window.parent.addTabs(title,"<%=path%>/jsp/news/"+_this.attr("url"),true,true);
					}
				});
			});
			
		  function xxfbNews(uuid,sysUrl){
		    if(sysUrl && sysUrl!="null"){
		    	var url = sysUrl;
		    	window.open(url);
			}else{
			  	var url ="<%=path%>/jsp/news/news2.jsp?uuid="+uuid;
				parent.window.addTabs("信息发布",url,true,true);
			}
		  } 
		  
		  function tglNews(uuid,sysUrl){
			  if(sysUrl && sysUrl!="null"){
			    	var url = sysUrl;
			    	window.open(url);
				}else{
				    var url ="<%=path%>/jsp/news/news2.jsp?uuid="+uuid;
					parent.window.addTabs("通告栏",url,true,true);
				}
		  } 
		  
		  function bzwdNews(uuid,sysUrl){
		  	  if(sysUrl && sysUrl!="null"){
			    	var url = sysUrl;
			    	window.open(url);
				}else{
				    var url ="<%=path%>/jsp/news/news2.jsp?uuid="+uuid;
					parent.window.addTabs("帮助文档",url,true,true);
				}
		  } 
	</script>
	<style type="text/css">
		body {
			background-color: white;
		}
		.changePic{
		 	width:450px;
		 	height:380px;
		 	background-color:#ccc;
		 	border:1px solid #333
		}
		   
		#changeA a:link {color: blue; text-decoration:none; cursor:pointer;}    
		#changeA a:active {color: red; cursor:pointer; }  
		#changeA a:visited {color:blue;text-decoration:none; cursor:pointer;}  
		#changeA a:hover {color: red; text-decoration:underline; cursor:pointer;} 
	</style>
	</head>
	<body class="easyui-layout">
		<div region="north" border="false" style="height: 285px; overflow:hidden;">
			<div class="easyui-layout" fit="true">
				<div id="summary" border="false" style="text-align:center;height:285px;;padding:5px;width: 550px; overflow:hidden;" region="west">
					<div class="easyui-panel" title=<%=snTitle %> fit="true" collapsible="false" cache="false"  style="overflow: hidden;">
						<table fit="true" border="0">
							<tr><td>
							</td></tr>
							<tr><td align="left" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<%
								if(!RcUtil.isEmpty(si.get("td"))){
									%>
									<%=si.get("td") %>
									<%
								}
							%>
							</td></tr>
							<tr><td colspan="2">
							<ul>
								<%
									for(int i=0;i<(si.size()-3);i++){
										%><li style="text-align: left;padding-bottom: 8px;"><%=
										si.get(""+(i+1)+"")
										%></li><%
									};
								%>
								<!-- 
								<li style="text-align: left;padding-bottom: 8px;">仓储服务的内容包括：入库、货物存储、理货包装、出库和货物装卸等等。</li>
								<li style="text-align: left;padding-bottom: 8px;">仓库总面积约为8700㎡，其中7000㎡用于郑州移动仓储服务。</li>
								<li style="text-align: left;padding-bottom: 8px;">地址：河南省郑州市管城区金岱工业园紫鼎食品仓库11号库（文治路与新107国道交叉口）</li>
								<li style="text-align: left;padding-bottom: 2px;">联系电话：0371-63779338&nbsp;&nbsp;传&nbsp;真：0371-66928069</li>
								 -->
							</ul>
							</td></tr>
							<tr>
								<%
									if(!RcUtil.isEmpty(si.get("images"))&&si.get("images").equals("true")){
										%>
										<td colspan="2">
											<div id="my-folio-of-works" class="svwp">
												<ul>
													<li><img alt="作业工具" rel="example_group" src="<%=path%>/images/company/p11.jpg" class="changePic"/></li>
													<li><img alt="作业中" rel="example_group" src="<%=path%>/images/company/p12.jpg" class="changePic"/></li>
													<li><img alt="仓库一角" rel="example_group" src="<%=path%>/images/company/p13.jpg" class="changePic" /></li>
													<li><img alt="员工集体合照" rel="example_group" src="<%=path%>/images/company/yg.jpg" class="changePic" title="员工集体合照"/></li>
													<li><img alt="认真工作中" rel="example_group" src="<%=path%>/images/company/wing.jpg" class="changePic" title="认真工作中"/></li>
												</ul>
											</div>
										</td>
										<%
									}
								%>
							</tr>
						</table> 
					</div>
				</div>
				<div id="summary" title="" border="false" style="text-align:center;height:250px;padding:5px;width: 550px; overflow:hidden;" region="center">
					<div class="easyui-panel" title="信息发布" fit="true" collapsible="false" cache="false"  style="overflow: hidden;">
						<table fit="true" border="0">
							<tr><td></td></tr>
							<%							
								if(!RcUtil.isEmpty(xxfbList) && xxfbList.size()>0){
									for(int i=0;i<xxfbList.size();i++){
									String date = RcUtil.date2String(xxfbList.get(i).getCreateTime(),RcUtil.yyyy_MM_dd_HH_mm_ss);
									date = date.substring(0,10);
							%>
							<tr>
								<td align="left" width="630px" id="changeA">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path%>/images/s1.gif" />&nbsp;&nbsp;<a id="title1"  style="text-decoration:none;" onclick="xxfbNews('<%=xxfbList.get(i).getSysNewsUuid()%>','<%=xxfbList.get(i).getUrlAddress() %>');"><%=xxfbList.get(i).getTitle() %></a></td><td width="18%"><label id="createTime1"><%=date%></label></td>
							</tr>
							<% 
									}
								}
							%>																																														
						</table>
					</div>
				</div>
			</div>
		</div>
		<div region="center" border="false" style="overflow: hidden;">
			<div class="easyui-layout" fit="true">
				<div id="notice" title="" border="false" style="text-align:center;height:250px;padding:5px;width:550px; overflow:hidden;" region="west">
					<div class="easyui-panel" title="通告栏" fit="true" collapsible="false" cache="false" style="overflow: hidden;">
					<form id="tgForm">
						<table fit="true">
							<tr><td></td></tr>
							<%
								if(!RcUtil.isEmpty(tglList) && tglList.size()>0){
									for(int i=0;i<tglList.size();i++){
										String date = RcUtil.date2String(tglList.get(i).getCreateTime(),RcUtil.yyyy_MM_dd_HH_mm_ss);
										date = date.substring(0,10);										
							%>
							<tr>
								<td align="left" width="420px" id="changeA">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path%>/images/s1.gif" />&nbsp;&nbsp;<a id="title1"  onclick="tglNews('<%=tglList.get(i).getSysNewsUuid()%>','<%=tglList.get(i).getUrlAddress() %>')" style="text-decoration:none;"><%=tglList.get(i).getTitle() %></a></td><td><label id="createTime1"><%=date%></label></td>
							</tr>
							<% 
									}
								}
							%>
						</table>
					</form>
					</div>
				</div>
				<div id="summary" border="false" style="text-align:center;height:250px;padding:5px;width: 550px; overflow:hidden;" region="center">
					<div class="easyui-panel" title="帮助文档" fit="true" collapsible="false" cache="false"  style="overflow: hidden;">
						<table fit="true" border="0">
							<tr><td></td></tr>
							<%
								if(!RcUtil.isEmpty(bzwdList) && bzwdList.size()>0){
									for(int i=0;i<bzwdList.size();i++){
										String date = RcUtil.date2String(bzwdList.get(i).getCreateTime(),RcUtil.yyyy_MM_dd_HH_mm_ss);
										date = date.substring(0,10);										
							%>
							<tr>
								<td align="left" width="630px" id="changeA">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path%>/images/s1.gif" />&nbsp;&nbsp;<a id="title1"  onclick="bzwdNews('<%=bzwdList.get(i).getSysNewsUuid()%>','<%=bzwdList.get(i).getUrlAddress() %>')" style="text-decoration:none;"><%=bzwdList.get(i).getTitle() %></a></td><td width="18%"><label id="createTime1"><%=date%></label></td>
							</tr>
							<% 
									}
								}
							%>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>