<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@ page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<%
	String path = request.getContextPath();
	SessionContextUserEntity scue = SessionContextUserEntity
	.currentUser();
%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
  <head>
  
    <title>信息发布</title>
	<jsp:include page="../../common/imp_dwr.jsp"></jsp:include>
	<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
	<link rel="stylesheet" href="../../css/system.css" type="text/css"></link>
	
	<style type="text/css">
		body {
			background:white;
		}

	</style>
	<script type="text/javascript">
	$(function(){
		$('#sysNews_datagird').datagrid({
			pagination:true,
			singleSelect: true,  // 单选或多选
			height:335,
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				id:'addBtn',
				handler:fn_toolbar_addBtn
			},'-',{
				text:'删除',
				iconCls:'myCustomerIcon_remove',
				id:'deleteBtn',
				handler:fn_toolbar_delBtn
			},'-',{
				text:'编辑',
				id:"editBtn",
				iconCls:'icon-edit',
				handler:fn_toolbar_editBtn
			},'-',{
				text:'生效',
				iconCls:'icon-ok',
				id:'validateBtn',
				handler:fn_toolbar_validateBtn
			},'-',{
				text:'作废',
				iconCls:'icon-cancel',
				id:'cancelBtn',
				handler:fn_toolbar_cancelBtn
			}],
			//onSelect:fn_onSelect,
			//onDblClickRow:fn_dbClick,
			fitColumns: false,
			nowrap:false,
			rownumbers:true,
			showFooter:true,
			pageSize:10, //每页显示记录数
			pageList:[5,10], //可调整每页显示的记录数
			striped:true,	//斑马线
			onLoadSuccess:function(){},
			sortName:"createTime",
			sortOrder:"desc",
			onLoadSuccess:function(){
				$('#sysNews_datagird').datagrid("clearSelections");
			}
		});
	});
	
	 //新增
	function fn_toolbar_addBtn(){ 
	  	var url="<%=path%>/jsp/news/sysNews.jsp?uuid="+"";
	  	parent.window.addTabs("信息发布",url,true,true);
		return;
  	}
  	
  	//删除
  	function fn_toolbar_delBtn(){
	  	var row = $("#sysNews_datagird").datagrid('getSelected');
	  	if(row.status=='<%=CommonUtil.Active%>'||row.status=='<%=CommonUtil.Cancel%>'){
	  		alert("生效和作废的数据不能删除!");
	  		return;
	  	}
	  	if(row){
		 $.messager.confirm('确认框','确定删除信息和信息下面的所有数据吗?', function(r){
				if(r){
					SysNewsManager.deleteNewsAndBlob(row.sysNewsUuid, function(result){
					   if(result){
					   		alert("删除成功");
					   		$("#sysNews_datagird").datagrid('reload');
					   }else{
					   		alert("删除失败");
					   }
					});
				}
			});
		}else{
			alert("请先选择数据再删除!");
			return ;
		}
  	}
	
	//编辑
  	function fn_toolbar_editBtn(){
  		var row = $("#sysNews_datagird").datagrid('getSelected');
  		if(row){
  			var url="<%=path%>/jsp/news/sysNews.jsp?uuid="+row.sysNewsUuid;
		  	parent.window.addTabs("信息发布",url,true,true);
			return;
  		}else{
  			alert("请选择需要的数据!");
  			return;
  		}
  	}	
  	
  	//生效
  	function fn_toolbar_validateBtn(){
  		var row = $("#sysNews_datagird").datagrid('getSelected');
  		if(row){
  			$.messager.confirm('确认框','确定生效此信息和信息下面的所有数据吗?', function(r){
  			  if(r){
  			  		var systemNewsEntity =  new Object();  //新建一个对象用来存放所有页面信息
  			  		systemNewsEntity.sysNewsUuid =row.sysNewsUuid ;
		  			systemNewsEntity.seqNo =row.seqNo;
			        systemNewsEntity.title = row.title;
			        systemNewsEntity.dateWork = row.dateWork;
			        systemNewsEntity.newsType = row.newsType;
			        systemNewsEntity.ifFiles = row.ifFiles;
			        systemNewsEntity.functionary = row.functionary;
			        systemNewsEntity.remark =  row.remark;
			        systemNewsEntity.status = "<%=CommonUtil.Active%>";
			        systemNewsEntity.recVer = row.recVer;
			        systemNewsEntity.rowState = row.rowState;
			        
		  			var urlAddress = escape(encodeURIComponent(row.urlAddress));
		  			var jsonresult = encodeURI($.toJSON(systemNewsEntity));  
	  			  	$.ajax({
		   			type: "POST",
				    url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=valAndCancelNews',
				    data:'jsonresult='+jsonresult+"&syscCntent="+""+"&urlAddress="+urlAddress,
				    dataType:'json',
				    success: function(date){
				    	if(date.object!=""||date.object!=null){
				    		$("#sysNews_datagird").datagrid('reload');
				    		alert("生效成功!");
				    	}else{
				    		alert("生效失败!");
				    	}
				   	}
				});
				
  			  }
  			});
  		}else{
  			alert("请选择需要的数据!");
  			return;
  		}
  		
  	}	  	
  	
  	//作废
  	function fn_toolbar_cancelBtn(){
  		var row = $("#sysNews_datagird").datagrid('getSelected');
  		if(row){
  			$.messager.confirm('确认框','确定作废此信息和信息下面的所有数据吗?', function(r){
  			  if(r){
  			  		var systemNewsEntity =  new Object();  //新建一个对象用来存放所有页面信息
  			  		systemNewsEntity.sysNewsUuid =row.sysNewsUuid ;
		  			systemNewsEntity.seqNo =row.seqNo;
			        systemNewsEntity.title = row.title;
			        systemNewsEntity.dateWork = row.dateWork;
			        systemNewsEntity.newsType = row.newsType;
			        systemNewsEntity.ifFiles = row.ifFiles;
			        systemNewsEntity.functionary = row.functionary;
			        systemNewsEntity.remark =  row.remark;
			        systemNewsEntity.status = "<%=CommonUtil.Cancel%>";
			        systemNewsEntity.recVer = row.recVer;
			        systemNewsEntity.rowState = row.rowState;
			        systemNewsEntity.beginDate=row.beginDate;
			        systemNewsEntity.endDate=row.endDate;
			        systemNewsEntity.createTime=row.createTime;
		  			var urlAddress = escape(encodeURIComponent(row.urlAddress));  
		  			var jsonresult = encodeURI($.toJSON(systemNewsEntity));  
	  			  	$.ajax({
		   			type: "POST",
				    url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=valAndCancelNews',
				    data:'jsonresult='+jsonresult+"&syscCntent="+""+"&urlAddress="+urlAddress,
				    dataType:'json',
				    success: function(date){
				    	if(date.object!=""||date.object!=null){
				    		alert("作废成功!");
				    		$("#sysNews_datagird").datagrid('reload');
				    	}else{
				    		alert("作废失败!");
				    	}
				   	}
				});
				
  			  }
  			});
  		}else{
  			alert("请选择需要的数据!");
  			return;
  		}
	
  	}	
  	  	
  	//状态的显示  	
 	function fn_global_setBasicdataState(v,r,i){
		if(v=='Pending') return '草稿';
		if(v=='Active') return '生效';
		if(v=='Cancel') return '作废';
		if(v=='undefined') return '';
		return v;
	}
	
	//显示文章的类型   	
 	function fn_setNewsStyle(v,r,i){
		if(v=='BZWD') return '帮助文档';
		if(v=='TGL') return '通过栏';
		if(v=='XXFB') return '信息发布';
		return v;
	}
	
	//显示经办人的名称
	function functionaryName(value){
		var name ="<%=scue.getUsername()%>";
		return name;
	}
	</script>

	<script type="text/javascript">
	// 查询按钮和清空按钮的click事件
	$(function(){
	
		$('#searchBtn').click(function(){ 
			var title=$("#title").val();
			var createTime_s=$("#createTime__start").datebox('getValue');
			var createTime_e=$("#createTime__end").datebox('getValue');
			var newsType = $("#newsType").combobox('getValue');
			var condition = $("#__condition").val();
			var status = $("#status").combobox('getValue');
	           $("#sysNews_datagird").datagrid('load',{
	           		__title:title,
	              	__newsType:newsType,
	              	__status:status,
	        		createTime__start:createTime_s,
	              	createTime__end:createTime_e,
	              	__condition:condition
	           });
   		});
    	$('#resetButton').click(function(){
    			 $('#queryForm').form('clear');
       			 $("#status").combobox("setValue","");
        });
        
        //状态
     	$('#status').combobox({ 
			panelHeight:"auto", 
			editable:false,
			onSelect:function(redor){
			}
		});
		var ido=null;
		//文章类型
		$('#newsType').combobox({
			url:'<%=path%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.OPTION_SYSTEM_CODE%>&q=<%=CommonUtil.OPTION_SYSTEM_CODE_SYS_NEWS_TYPE%>',
			valueField:'key',
			textField:'value',
			panelHeight:'auto',
		    required:false,
		    editable:false,
		    onSelect:function(record){ 
		    	
		    },
		    onLoadSuccess:function(none){
				//加载成功后需要执行的内容
				if(none.length>0&&ido==null||ido==""){
					ido="OK";
					var workItemSIG=$('#newsType').combobox("getData");
					workItemSIG.push({"key":"","value":"全部"});
					$('#newsType').combobox("loadData",workItemSIG);
					$('#newsType').combobox("select","");//默认选中
				}
			}
		});
		
	});
	</script>
  </head>
  
  <body class="easyui-layout">
	<div region="north" border="false" iconCls="myCustomerIcon_searchForm" title="<bean:message bundle="wlp.common" key="global.searchTitle"/>"	style="height:120px">
		<%--按钮--%>
		<div class="myCustomer_toolbar">
			<a href="#" id="searchBtn" class="easyui-linkbutton"
					plain="true" iconCls="icon-search"  style="float: left;" ><bean:message bundle="wlp.common" key="global.search"/></a>
			<div class="datagrid-btn-separator"></div>
			<a href="#" id="resetButton" class="easyui-linkbutton"
					plain="true" iconCls="myCustomerIcon_clear" style="float: left;" ><bean:message bundle="wlp.common" key="global.clear"/></a>
			<div class="datagrid-btn-separator"></div>
			<input type="hidden" id="__condition" name="__condition"
			value="title_ilikeAnywhere:createTime_between" />
		</div>
		<%--查询表单--%>
		<div id="formContainer" >
			<form id="queryForm">
				<table id="searForm" width="880px" style="margin:0;padding:0;">
					<tr>
						<td align="right" td_align="left" class="textStyle">
							<label for="title">
								文章标题:
							</label>
						</td>
						<td>
							<input id="title" name="title"  class="easyui-validatebox" />

						</td>
						<td align="right" td_align="left" class="textStyle">
							<label for="newsType" >
								类型:
							</label>
						</td>
						<td>
							<input id="newsType" name="newsType" />
						</td>
					</tr>
					<tr>
						<td align="right" td_align="left" class="textStyle">
							<label for="status" >
								状态:
							</label>
						</td>
						<td>
							<select id="status" name="status" calss="easyui-validatebox easyui-combobox" style="width:50px">
								<option value="">全部</option>
								<option value="Active">生效</option>
								<option value="Pending">草稿</option>
								<option value="Cancel">作废</option>
							</select>
						</td>
						<td  align="right" td_align="left" class="textStyle">
							<label for="dateWork" >
								发布日期:
							</label>
						</td>
						<td width="35%" align="left">
								<input id="createTime__start" name="createTime__start"
									class="easyui-datebox easyui-validatebox" validType="dateFormat" editable="false" style="width: 85px;" />
								<img src="<%=path%>/images/right.gif" />
								<input id="createTime__end" name="createTime__end"
									class="easyui-datebox easyui-validatebox" validType="dateFormat" editable="false" style="width: 85px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div region="center" border="false">
		<table id="sysNews_datagird" title="信息资料列表" iconCls="icon-edit"	
			url="<%=path%>/servlet/SinotransServlet?cmd=query&queryType=SysNewsModel&oc=true" fit="true">
			<thead>
				<tr>
					<th field="status" formatter='fn_global_setBasicdataState'  width="40"  align="center" td_align="left" >
						状态
					</th>
					<th field="title"  width="350" align="center" td_align="left" editor="validatebox">
						文章标题
					</th>
					<th field="newsType" formatter='fn_setNewsStyle' width="70" align="center" td_align="left" >
					    类型
					</th>
					<th field="urlAddress" width="210"  align="center" td_align="left" editor="validatebox">
						连接地址
					</th>
					<th field="seqNo" width="50"  align="center" td_align="left" editor="validatebox">
						序号
					</th>
					<th field="createTime" width="150"  align="center" td_align="left" editor="validatebox">
						发布日期
					</th>
					<th field="ifFiles" width="80"  align="center" td_align="left" editor="validatebox" >
					    是否有附件
					</th>
					<th field="remark" width="140"  align="center" td_align="right"	editor="numberbox">
						备注
					</th>
					<th field="functionary" width="80"  align="center" td_align="center" editor="numberbox" formatter="functionaryName">
						经手人
					</th>
				</tr>
			</thead>
		</table>
	 </div>
  </body>
</html>
