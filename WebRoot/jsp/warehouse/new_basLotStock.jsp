
<%@page import="com.sinotrans.gd.wlp.basicdata.model.BasPanelModel"%>
<%@page import="com.sinotrans.gd.wlp.basicdata.model.BasLocAreaModel"%>
<%@page import="com.sinotrans.gd.wlp.basicdata.model.BasLotStockModel"%>
<%@page import="com.sinotrans.gd.wlp.basicdata.model.BasWarehouseModel"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<%@page import="com.sinotrans.gd.wlp.util.StringUtil"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@include file="../../fros-easyui/common/include.jsp" %>
<%
	String path = request.getContextPath();
	SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
	String officeCode = SessionContextUserEntity.currentUser().getOfficeCode();
%>

<script type="text/javascript" src="<%=path%>/js/util/myUtil.js"></script>
<script type="text/javascript" src="<%=path%>/js/export2Excel.js"></script>
<script type="text/javascript" src="<%=path%>/js/bc/sinotrans_print.js"></script>
<script type="text/javascript" src="<%=path%>/js/util/wlp_commAlertToFunction.js"></script>
	<style type="text/css">
		body {
			width: 100%;
			height: 100%;
		}
		.input_style{
			width: 250px;
    		height: 30px;
    		background-color: azure;
		}
		.inputSearch_style{
			margin:4px 0px 0px 3px;
			width: 120px;
    		height: 20px;
		}
	</style>
	
	<script type="text/javascript">
	var saveBasWarehouseUuid = "";
		
		//是否选中了仓库区域
		var isSelectLot=false;
		//加载树形结构
		$(function(){
			//仓库树形结构初始化、包括事件监听
			_treeChuShiHua();
			//初始化树形结构数据
			resultTree();
			//货位查询-状态下拉框初始化
	  	 	_statusChuShiHua();
	  	 	//区域类型下拉框初始化
			_formChuShiHua();
			setTimeout(function(){
				__buttonStateJump("<%=CommonUtil.Active%>",null,null);},200);
		});
		
		
			
		//保存仓库地点信息
		function saveWareHouse(){
			BasWarehouseManager.saveBasWarehouse(getFromData("left_queryForm"),function(spj){
				if(showMsg(spj)){
					clearFormData("left_queryForm");
					clearFormData("right_queryForm");
					//刷新仓库树形菜单
					resultTree();
				}
			});
		}
		
		//保存仓库区域信息
		function saveWareLocArea(){
			BasLocAreaManager.saveBasLocArea(getFromData("right_queryForm"),function(spj){
				if(showMsg(spj)){
					clearFormData("right_queryForm");
					clearFormData("left_queryForm");
					//刷新仓库树形菜单
					resultTree();
					$('#locAreaCode').attr('readonly',true);
				}
			});
		}
		
		//撤销仓库地点信息
		function cancelWareHouse(){
			var node = $('#warehouseTree').tree('getSelected');
			if(node.attributes.remark=="wh"){
				$("#jspTabs").tabs("select","仓库/区域信息");
					BasWarehouseManager.get(node.id,function(result){
						setFormData("left_queryForm",result);
				});
			}
			if(node.attributes.remark=="area"){
			 	$("#jspTabs").tabs("select","仓库/区域信息");
					BasLocAreaManager.get(node.id,function(result){
						BasWarehouseManager.get(result.basWarehouseUuid,function(bwModel){
							setFormData("left_queryForm",bwModel);
					});
				});
			}
		}
		
		//撤销仓库区域信息
		function cancelWareLocArea(){
			var node = $('#warehouseTree').tree('getSelected');
			if(node.attributes.remark=="area"){
			 	$("#jspTabs").tabs("select","仓库/区域信息");
					BasLocAreaManager.get(node.id,function(result){
						setFormData("right_queryForm",result);
				});
			}
		}
		
		//删除区域信息
		function removeWareLocArea(){
			var node = $('#warehouseTree').tree('getSelected');
			if(!is_null(node)&&node.attributes.remark!="wh"){
				var data=getFromData("right_queryForm");
				$.messager.confirm('提示信息',"是否确定要删除此区域？",function(b){
					if(b){
						data.rowState="<%=BasLocAreaModel.ROW_STATE_DELETED%>";
						BasLocAreaManager.saveBasLocArea(data,function(spj){
							if(showMsg(spj)){
								//刷新仓库树形菜单
								clearFormData("right_queryForm");
								resultTree();
							}
						});
					}
				});
			}else{
				$alertShow('请先选择区域！');
			}
		}
		
		//新增区域信息
		function addWareLocArea(){
			var node = $('#warehouseTree').tree('getSelected');
			if(!is_null(node)){
				var bwEntity=node.attributes;
				if(bwEntity.remark=="wh"){
					clearFormData("right_queryForm");
					$('#locAreaCode').attr('readonly',false);
					var data=new Object();
					data.basWarehouseUuid=bwEntity.id;
					data.officeCode="<%=scue.getOfficeCode()%>";
					data.status="<%=CommonUtil.Active%>";
					data.controlWord="<%=CommonUtil.Default_Control_Word%>";
					setFormData("right_queryForm",data);
					$("#locAreaCode").focus();
				}else{
					$alertShow('请先选择仓库！');
				}
			}else{
				$alertShow(0);
			}
		}
		
		
		function callback(spj){
			if(showMsg(spj)){
				location.reload(); 
			}
		}
		//区域类型下拉框初始化
	  	function _formChuShiHua(){
	  		$("#basLocAreaType").combobox({
				panelHeight:'auto',
				width:80,
				editable:false,
				data:[
					  {'id':'','text':'==请选择=='},
				      {'id':'T','text':'退货区'},
					  {'id':'P','text':'拣货区'},
					  {'id':'S','text':'零售区'},
					  {'id':'C','text':'成品区'},
					  {'id':'R','text':'收货区'},
					  {'id':'M','text':'加工区'},
					  {'id':'Z','text':'暂存区'},
					  {'id':'0','text':'无'}
					 ],
				valueField:'id',
				textField:'text'
			});
	  		$("#lockFlag").combobox({
	  			panelHeight:45,
				editable:false,
				data:[
					  {'id':'0','text':'否'},
					  {'id':'1','text':'是'}
					 ],
				valueField:'id',
				textField:'text'
			});
			$("#_basLocAreaType").combobox({
				panelHeight:'auto',
				editable:false,
				valueField:'basLocAreaUuid',
				textField:'locAreaName'
			});
			$("#basBasLocTypeUuid").combogrid({
				onSelect:function(index,obj){
					$("#HID_basBasLocTypeUuid").val(obj.basBasLocTypeUuid);
				}
			});
			//初始化查询条件货位类型UUID查询
			$('#com_basBasLocTypeUuid').combogrid({
				onSelect:function(index,obj){
					$("#se_basBasLocTypeUuid").val(obj.basBasLocTypeUuid);
				},
				onChange:function(newObj,oldValue){
					if(is_null(newObj)){
						$("#se_basBasLocTypeUuid").val("");
					}
				}
			});
		}
		
		//货位查询-状态下拉框初始化
	  	function _statusChuShiHua(){
	  		$("#status").combobox({
	  			panelHeight:90,
				editable:false,
				data:[
					  {'id':'','text':'全部'},
				      {'id':'<%=CommonUtil.Active%>','text':'生效'},
					  {'id':'<%=CommonUtil.Pending%>','text':'草稿'},
					  {'id':'<%=CommonUtil.Cancel%>','text':'作废'}
					 ],
				valueField:'id',
				textField:'text'
			});
			odlog("inboundImage");
			cdLog("inboundImage");
		}
		
		//查询货位信息
		function searchLotStock(){
			if(!isSelectLot){
				$alertShow("请选择区域再查询货位!");
				return;
			}
			$("#baslLotStockData").datagrid("commonQuery",{
					queryType:"BasLotStockModel",
					paramForm:"searchLotStock"
			});
		}

		//重置查询条件
		function resetButtonOnclick(){
			$("#searchLotStock").form("clear");
			$('#status').combobox("select","");
			$('#com_basBasLocTypeUuid').combogrid("setValue","");
		}
		
		//默认按钮
		function defaultButton(){
			var getSeData=getPublicDatagridSelectedS("baslLotStockData");
			if(getSeData&&getSeData.length==1){
				var text='确定将这条数据设置默认?';
				$.messager.confirm('提示信息',text,function(b){
					if(b){
						getSeData[0].cw2="D";
						BasLotStockManager.setDefaultValue(getSeData[0],function(spj){
							if(showMsg(spj)){
								//刷新表格
								datagirdReaload("baslLotStockData");
							}
						});
					}
				});
				
			}else if(getSeData&&getSeData.length>1){
				$alertShow(1);
			}else{
				$alertShow(0);
			}
		}
		
		//点击新增-弹出编辑框
		function add_BasLotStock(){
			var node = $('#warehouseTree').tree('getSelected');
			if(!is_null(node)){
				//设置成同步        
				DWREngine.setAsync(false);
				var bwEntity=node.attributes;
				var data=new Object();
				data.basLocAreaUuid=bwEntity.id;
				data.officeCode="<%=scue.getOfficeCode()%>";
				data.status="<%=CommonUtil.Pending%>";
				data.controlWord="<%=CommonUtil.Default_Control_Word%>";
				if(bwEntity.remark=="area"){
					$('#inboundImage').dialog({
						modal:true,
						title:"货位信息编辑",
						buttons:[{
							text:'保存',
							id:'update_detailSave',
							handler:function(){
								saveDiv();
							}
						},{
							text:'取消',
							handler:function(){
								closeImage();
							}
						}]
					});
					BasLocAreaManager.get(node.id,function(result){
						BasWarehouseManager.getLocAeaName(result.officeCode, result.basWarehouseUuid,function(spj){
							if(showMsg(spj)) {
								//首先赋值
				   				$("#_basLocAreaType").combobox("loadData",spj.object);
							}
						});
					});
					setFormData("addBasLotStock",data);
					$('#lotCode').attr('readonly',false);
				    $("#_basLocAreaType").combobox("select",bwEntity.id);
				}else{
					$alertShow('请先选择区域！');
				}
				//恢复异步        
				DWREngine.setAsync(true);
			}else{
				$alertShow(0);
			}
		}
		
		//点击新增-弹出编辑框
		function add_BasLot_Stock(){
			var node = $('#warehouseTree').tree('getSelected');
			if(!is_null(node)){
				//设置成同步        
				DWREngine.setAsync(false);
				var bwEntity=node.attributes;
				var data=new Object();
				data.basLocAreaUuid=bwEntity.id;
				data.officeCode="<%=scue.getOfficeCode()%>";
				data.status="<%=CommonUtil.Pending%>";
				data.controlWord="<%=CommonUtil.Default_Control_Word%>";
				if(bwEntity.remark=="area"){
					$('#inboundImage').dialog({
						modal:true,
						title:"货位信息编辑",
						buttons:[{
							text:'保存',
							id:'update_detailSave',
							handler:function(){
								_saveDiv();
							}
						},{
							text:'取消',
							handler:function(){
								closeImage();
							}
						}]
					});
					BasLocAreaManager.get(node.id,function(result){
						BasWarehouseManager.getLocAeaName(result.officeCode, result.basWarehouseUuid,function(spj){
							if(showMsg(spj)) {
								//首先赋值
				   				$("#_basLocAreaType").combobox("loadData",spj.object);
							}
						});
					});
					setFormData("addBasLotStock",data);
					$('#lotCode').attr('readonly',false);
				    $("#_basLocAreaType").combobox("select",bwEntity.id);
				}else{
					$alertShow('请先选择区域！');
				}
				//恢复异步        
				DWREngine.setAsync(true);
			}else{
				$alertShow(0);
			}
		}
		
		//保存-新增货位信息
		function saveDiv(){
			if(!getFormValid("addBasLotStock")){
				$alertShow("form");
				return;
			}
			var node = $('#warehouseTree').tree('getSelected');
			var bwEntity=node.attributes;
			if(bwEntity.remark=="area"){
				var data=getFromData("addBasLotStock");
				data.rowState="<%=BasLotStockModel.ROW_STATE_ADDED%>";
				var obj=new Array();
				obj.push(data);
				 BasLotStockManager.saveBasLocStock(obj,"<%=BasLotStockModel.ROW_STATE_ADDED%>",function(spj){
					if(showMsg(spj)){
						//刷新表格
						closeImage();
						$("#baslLotStockData").datagrid("reload");
					}
				}); 
				
				<%-- BasWarehouseManager.getLocAeaName(obj,"<%=BasLotStockModel.ROW_STATE_ADDED%>",function(spj){
					if(showMsg(spj)) {
						//首先赋值
		   				$("#_basLocAreaType").combobox("loadData",spj.object);
					}
				}); --%>
			}
		}
		
		//保存-编辑货位信息
		function _saveDiv(){
			if(!getFormValid("addBasLotStock")){
				$alertShow("form");
				return;
			}
			var node = $('#warehouseTree').tree('getSelected');
			var bwEntity=node.attributes;
			if(bwEntity.remark=="area"){
				var data=getFromData("addBasLotStock");
				data.rowState=tractorUuid=getPublicDataGridSelected("baslLotStockData").status;
				var obj=new Array();
				obj.push(data);
				BasLotStockManager.saveBasLocStock(obj,data.rowState,function(spj){
					if(showMsg(spj)){
						//刷新表格
						closeImage();
						datagirdReaload("baslLotStockData");
					}
				});
			}
		}
		
		//点击编辑-弹出框显示详细
		function edi_BasLotStock(){
			var getSeData=getPublicDatagridSelectedS("baslLotStockData");
			if(getSeData.length==1){
				add_BasLot_Stock();
				$('#lotCode').attr('readonly',true);
				$("#addBasLotStock").form("setData",getSeData[0]);
				$("#basBasLocTypeUuid").combogrid("setValue",getSeData[0].basBasLocTypeUuid);
			}else if(getSeData.length<1){
				$alertShow(0);
			}else{
				$alertShow(1);
			}
		}
		//生效
		function activeButtonOnclick(){
			var node = $('#warehouseTree').tree('getSelected');
			var bwEntity=node.attributes;
			if(bwEntity.remark=="area"){
				__allOperation("<%=CommonUtil.Active%>");
			}else{
				$alertShow("请选择仓库区域后再进行此操作！");
			}
		}
		
		//作废
		function cancelButtonOnclick(){
			var node = $('#warehouseTree').tree('getSelected');
			var bwEntity=node.attributes;
			if(bwEntity.remark=="area"){
				__allOperation("<%=CommonUtil.Cancel%>");
			}else{
				$alertShow("请选择仓库区域后再进行此操作！");
			}
		}
		
		//删除
		function deleteButtonOnclick(){
			var node = $('#warehouseTree').tree('getSelected');
			var bwEntity=node.attributes;
			if(bwEntity.remark=="area"){
				__allOperation("<%=BasLotStockModel.ROW_STATE_DELETED%>");
			}else{
				$alertShow("请选择仓库区域后再进行此操作！");
			}
		}
		
		var blsDatagrid_id="baslLotStockData";
		//提供·删除·作废·生效·取消生效
		function __allOperation(type){
			var rows=getPublicDatagridSelectedS(blsDatagrid_id);
			var marsk =0;
			var deliveryOrderUuids =new Array();
			if(rows!=""){
				for(var i=0; i<rows.length; i++){
					if("<%=BasLotStockModel.ROW_STATE_DELETED%>"==type){
						if(rows[i].status=='<%=CommonUtil.Active%>' || rows[i].status=='<%=CommonUtil.Cancel%>'){
							marsk = marsk+1;
							unPublicDatagridselectRow(blsDatagrid_id,rows[i].rownum);
						}else{
							deliveryOrderUuids.push(rows[i]);
						}
					}else if("<%=CommonUtil.Active%>"==type){
						if(rows[i].status=='<%=CommonUtil.Active%>'){
							unPublicDatagridselectRow(blsDatagrid_id,rows[i].rownum);
							marsk = marsk+1;
						}else{
							deliveryOrderUuids.push(rows[i]);
						}
					}else if("<%=CommonUtil.Cancel%>"==type){
						if(rows[i].status=='<%=CommonUtil.Cancel%>' || rows[i].status=='<%=CommonUtil.Pending%>'){
							unPublicDatagridselectRow(blsDatagrid_id,rows[i].rownum);
							marsk = marsk+1;
						}else{
							deliveryOrderUuids.push(rows[i]);
						}
					}else if("<%=CommonUtil.Pending%>"==type){
						if(rows[i].status=='<%=CommonUtil.Cancel%>' || rows[i].status=='<%=CommonUtil.Pending%>'){
							unPublicDatagridselectRow(blsDatagrid_id,rows[i].rownum);
							marsk = marsk+1;
						}else{
							deliveryOrderUuids.push(rows[i]);
						}
					}else if("F"==type){
						
					}
				}
			  if(marsk > 0){
				  $alertShow("选中数据仅有"+(rows.length-marsk)+"条可操作数据!");
			  }
			}else{
				$alertShow(0);
			}
			if(deliveryOrderUuids!=null && deliveryOrderUuids.length >0){
				var text="";
				if("<%=BasLotStockModel.ROW_STATE_DELETED%>"==type){
					text="是否确认对选中数据( "+rows.length+" )中,可操作的 "+(rows.length-marsk)+" 条数据进行"+fontColorRedFront("删除")+"操作?";
				}else if("<%=CommonUtil.Active%>"==type){
					text="是否确认对选中数据( "+rows.length+" )中,可操作的 "+(rows.length-marsk)+" 条数据进行"+fontColorRedFront("生效")+"操作?";
				}else if("<%=CommonUtil.Pending%>"==type){
					text="是否确认对选中数据( "+rows.length+" )中,可操作的 "+(rows.length-marsk)+" 条数据进行"+fontColorRedFront("取消生效")+"操作?";
				}else if("<%=CommonUtil.Cancel%>"==type){
					text="是否确认对选中数据( "+rows.length+" )中,可操作的 "+(rows.length-marsk)+" 条数据进行"+fontColorRedFront("作废")+"操作?";
				}else if("F"==type){
					text="是否确认对选中数据( "+rows.length+" )中,可操作的 "+(rows.length-marsk)+" 条数据进行"+fontColorRedFront("完结")+"操作?";
				}
				$.messager.confirm("提示",text,function(b){
					if(b){
						openPID();
						BasLotStockManager.saveBasLocStock(deliveryOrderUuids,type,{
		                    callback : function(spj){
								closePID();
								if(spj.result){
									$alertShowGreen(spj.msg);
									datagirdReaload(blsDatagrid_id);
								}else{
									$alertError(spj.error);
								}
		                    },
		                    errorHandler : function(error) {
		                    	closePID();  
								$alertError(error);
		                    }
						});
					}
				});
			}
		}
		
		
		function callReturnback(spj){
			if(showMsg(spj)){
				datagirdReaload("baslLotStockData");
			}
		}
		
		
	
		//关闭弹出框
		function closeImage(){
			$('#inboundImage').dialog('close');
		}
	</script>
	
	<script type="text/javascript">

		//加载仓库树形结构
		function resultTree(){
			BasWarehouseManager.getTreeData(function(result){
		 		$("#warehouseTree").tree("loadData",result);
		 		$('.tree').tree('collapseAll');
			});
		}
		//仓库树形结构初始化、包括事件监听
		function _treeChuShiHua(){
			//填充仓库/区域信息tab
			$("#warehouseTree").tree({
				onClick:function(node){
			        if(node.attributes != null&& node.attributes.remark =="wh"){
                        if(node.attributes.remark !="wh")
                            return;
			        	isSelectLot=false;
				        $("#jspTabs").tabs("select","仓库/区域信息");
			        	BasWarehouseManager.get(node.id,function(result){
			        		setFormData("left_queryForm",result);
			        		clearFormData("right_queryForm");
			        		$('#locAreaCode').attr('readonly',true);
							$("#basLocAreaType").combobox("select","");
							saveBasWarehouseUuid = node.id;
				        });
				    }else{
				    	isSelectLot=true;
				    	$("#jspTabs").tabs("select","货位信息");
				    	//获取仓库区域id并填充对应的信息
				    	BasLocAreaManager.get(node.id,function(result){
				    		result.basLocAreaType=result.controlWord.charAt(0);
				    		setFormData("right_queryForm",result);
				    		//将仓库代码输入框设置为只读
				    		$('#locAreaCode').attr('readonly',true);
				    		//显示仓库区域类型的值
				    		$("#basLocAreaType").combobox("select",result.basLocAreaType);
				    		//获取仓库区域所属仓库地点的id并填充对应的信息
				    		BasWarehouseManager.get(result.basWarehouseUuid,function(bwModel){
				        		setFormData("left_queryForm",bwModel);
					        });
					        //设置查询条件
					        $("#baslLotStockData").datagrid("setQueryFields",[
								{
									fieldName:'basLocAreaUuid',
									fieldStringValue:result.basLocAreaUuid
								}
							]);
							$('#com_basBasLocTypeCode').combogrid("setValue","");
					        //根据区域找区域下的货位baslLotStockData
					        $("#baslLotStockData").datagrid("commonQuery",{
								queryType:"BasLotStockModel",
							});
				  	    });
					}
				}
			});
		}
		
		
	function callback1(data){
		if(data.result){
			$("#downloadmap").linkbutton("enable").unbind("click").bind("click"); 	//显示下载按键	
			$("#mapmassege").html("该仓库地图上传成功！");
			$("#isUpload").val(1);
			alert(data.msg);
		}else{
			$("#isUpload").val(0);
			alert(data.error);	
		}
	}
	
	//下载
	function downloadwarehousemap(){
		var downloadaction_url="<%=path%>/downLoadAction.do?basWarehouseUuid="+saveBasWarehouseUuid;
		window.open(downloadaction_url);
	}
		//转换默认值
		function cwAutoAtter(value,rowData,index){
			if(!is_null(value)){
				if(rowData.controlWord.charAt(1)=="D"){
					return "默认";
				}
			}
		}
		//列表初始化
		function __searchBaslLotStockDataChuShiHua(){
			$('#baslLotStockData').datagrid({
				onSelect:function(rowIndex,rowData){
					__buttonStateJump(rowData.status,rowData.controlWord,rowData);
				}
			});
		}
	</script>
  </head>
  <body class="easyui-layout">
		<div id="codeTypeContainer" region="west" title="仓库" border="true" style="padding:0px;width:300px;" split="true">
			 <%--按钮--%>
			<div class="datagrid-toolbar">
				<a href="#" id="editNewLocAreaBtn" class="easyui-linkbutton" onclick="addWareLocArea()" iconCls="icon-add">
					新增区域</a>
				<a href="#" id="PH_WARE_BAL_REMOVEWARE" class="easyui-linkbutton" onclick="removeWareLocArea()" iconCls="myCustomerIcon_remove">
					删除区域</a>
			</div>
			<ul id="warehouseTree" state='closed' class='easyui-tree' animate='true'></ul>
		</div>
		<div id="tabsContainer" region="center" border="true">
			<div id="jspTabs" class="easyui-tabs" fit="true" border="true">
				<!-- 仓库区域tab -->
				<div id="ww" title="仓库/区域信息" border="true">
					<!-- 展开一个布局 -->
					<div class="easyui-layout" border="true" fit="true">
						<!-- 中部布局存放仓库信息 -->
						<div region="center" title="仓库信息">
							<!-- 按钮部分 -->
							<div class="datagrid-toolbar">
								<a id="saveButton" class="easyui-linkbutton" href="#" iconCls="icon-save" onclick="saveWareHouse()"> 
									保存</a>
								<a id="cheXiaoButton" class="easyui-linkbutton" href="#" iconCls="icon-undo" onclick="cancelWareHouse()"> 
									撤销</a>
							</div>
							<!-- 表单部分 -->
							<div id="formContainer">
								<div align="center">
									<form id="left_queryForm" class="easyui-form" i18nRoot="LogisticsOrderOI" columns="1">
										<input type="text" id="warehouseCode" name="warehouseCode" class="input_style" onblur="strTrim(this.id);" readonly="readonly" title="仓库编号">
										<br/>
										<input type="text" id="warehouseName" name="warehouseName" class="input_style" onblur="strTrim(this.id);" title="仓库名称">
										<br/>
										<input type="text" id="warehouseNameEn" name="warehouseNameEn" class="input_style" onblur="strTrim(this.id);" title="仓库英文名称">
										<br/>
										<input type="text" id="warehouseCapacity" name="warehouseCapacity" class="input_style" onblur="strTrim(this.id);" title="仓库容量">
										<br/>
										<input type="text" id="warehouseAddress" name="warehouseAddress" class="input_style" onblur="strTrim(this.id);" title="仓库地址">
										<br/>
										<input type="text" id="serviceArea" name="serviceArea" class="input_style" onblur="strTrim(this.id);" title="服务区域">
										<br/>
										<input type="text" id="serviceAging" name="serviceAging" class="input_style" onblur="strTrim(this.id);" title="时效">
										<br/>
										<input type="text" id="remark" name="remark" class="input_style" onblur="strTrim(this.id);" title="备注">
									</form>
								</div>
							</div>
						</div>
						<!-- 东部布局存放区域信息-->
						<div region="east"  title="区域信息" style="width:450px;" split="true">
							<!-- 按钮部分 -->
							<div class="datagrid-toolbar">
								<a id="saveButton" class="easyui-linkbutton" href="#" iconCls="icon-save" onclick="saveWareLocArea()"> 
									保存</a>
								<a id="cheXiaoButton" class="easyui-linkbutton" href="#" iconCls="icon-undo" onclick="cancelWareLocArea()"> 
									撤销</a>
							</div>
							<!-- 表单部分 -->
							<div id="formContainer">
								<div align="center">
									<form id="right_queryForm" class="easyui-form" i18nRoot="LogisticsOrderOI" columns="1">
										<input type="text" id="locAreaCode" name="locAreaCode" class="input_style easyui-validatebox" onblur="strTrim(this.id);" required="true" onkeyup="javascript:this.value=this.value.toLocaleUpperCase();" readonly="readonly" title="区域代码">
										<br/>
										<input type="text" id="locAreaName" name="locAreaName" class="input_style" onblur="strTrim(this.id);" title="区域名称">
										<br/>
										<input type="text" id="locAreaNameEn" name="locAreaNameEn" class="input_style" onblur="strTrim(this.id);" title="区域描述">
										<br/>
										<input type="text" id="_remark" name="_remark" class="input_style" onblur="strTrim(this.id);" title="备注">
										<br/>
										<input id="basLocAreaType" name="basLocAreaType" title="区域类型:" style="width: 100px;height: 40px;background-color: azure;"/>
									</form>
							</div>
						</div>
					</div>
				</div>
			</div>
				<!-- 货位信息tab -->
				<div id="ee"  title="货位信息"  border="true" >
					<!-- 展开一个布局 -->
					<div class="easyui-layout" border="true" fit="true">
						<!-- 北部布局存放查询条件 -->
						<div region="north" border="false" title="查询条件">
							<!-- 按钮部分 -->
							<div class="datagrid-toolbar">
								<a id="saveButton" class="easyui-linkbutton" href="#" iconCls="icon-search" onclick="searchLotStock()"> 
									查询</a>
								<a id="cheXiaoButton" class="easyui-linkbutton" href="#" iconCls="myCustomerIcon_clear" onclick="resetButtonOnclick()">
									重置</a>
							</div>
							<form id="searchLotStock" class="easyui-form"  columns="3">
								<input name="lotCode" class="inputSearch_style" title="货位编号">
								<input name="lotName" class="inputSearch_style" title="货位名称">
								<input id="com_basBasLocTypeCode" name="basBasLocTypeCode" class="easyui-combogrid"  codetype="BAS_LOC_TYPE" title="货位类型">
								<input id="status" name="status" formatter="statusFormatter" title="状态" style="width:60px;">
								<input id="pri" name="pri" class="inputSearch_style" title="优先级">
							</form>
						</div>
						<!-- 中部布局存放仓库信息 -->
						<div region="center" title="货位信息">
							<!-- 按钮部分 -->
							<div class="datagrid-toolbar">
								<a id="add_BasLotStock" class="easyui-linkbutton" href="#" iconCls="icon-add" onclick="add_BasLotStock()"> 
									新增</a>
								<a id="editButton" class="easyui-linkbutton" href="#" iconCls="icon-edit" onclick="edi_BasLotStock()"> 
									编辑</a>
								<a id="shengxiaoButton" class="easyui-linkbutton" href="#" iconCls="myCustomerIcon_validate" onclick="activeButtonOnclick()"> 
									生效</a>
								<a id="cancelButton" class="easyui-linkbutton" href="#" iconCls="myCustomerIcon_cancel" onclick="cancelButtonOnclick()"> 
									作废</a>
								<a id="PH_STOCK_BAS_REMOVESTOCK" class="easyui-linkbutton" href="#" iconCls="myCustomerIcon_remove" onclick="deleteButtonOnclick()"> 
									删除</a>
								<a id="saveButton" class="easyui-linkbutton" href="#" iconCls="icon_set_default" onclick="defaultButton()"> 
									默认</a>
								
							</div>
							<!-- 数据列表部分 -->
							<table id="baslLotStockData" fit="true" class="easyui-datagrid" pageList="[30,50,100]">
								<thead>
									<tr>
										<th field="status" width="50px;"  title="状态" codetype="STATUS"></th>
										<th field="controlWord" width="50px;"  title="默认" formatter="cwAutoAtter"></th>
										<th field="lotCode" width="130px;"  title="货位编号"></th>
										<th field="lotName" width="130px;"  title="货位名称"></th>
										<th field="basBasLocTypeCode" width="130px;"  title="货位类型"></th>
										<!--
										<th field="locAreaCode" width="130px;"  title="区域编号"></th>
										<th field="locAreaName" width="130px;"  title="区域名称"></th> 
										-->
										<th field="pri" width="130px;" title="优先级"></th>
										<th field="putPri" width="130px;"  title="上架优先级"></th>
										<th field="pickPri" width="130px;"  title="拣货优先级"></th>
										<th field="maxPalletFloor" width="130px;"  title="最大堆放层数"></th>
										<th field="maxVoluem" width="130px;"  title="最大体积"></th>
										<th field="maxWeight" width="130px;"  title="最大重量"></th>
										<th field="maxPalletQty" width="130px;"  title="最大托盘数"></th>
										<th field="length" width="130px;"  title="长"></th>
										<th field="width" width="130px;"  title="宽"></th>
										<th field="height" width="130px;"  title="高"></th>
										<th field="lotX" width="130px;"  title="货架位置-行"></th>
										<th field="lotY" width="130px;"  title="货架位置-列"></th>
										<th field="lotZ" width="130px;"  title="货架位置-高"></th>
										<th field="lockFlag" width="130px;"  title="锁定"></th>
										<th field="remark" width="130px;"  title="备注"></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="inboundImage" icon="icon-save"> 
			<form id="addBasLotStock" class="easyui-form"    columns="3">
				<input id="lotCode" name="lotCode" class="easyui-validatebox" style="width:130px;" readonly="readonly" onblur="strTrim(this.id);" onkeyup="javascript:this.value=this.value.toLocaleUpperCase();" title="货位编号" required="true"/>
				<input id="lotName" name="lotName" class="easyui-validatebox" style="width:130px;" title="货位名称" required="true"/>
				<div>
					<input id="basBasLocTypeCode" class="easyui-combogrid" codetype="BAS_LOC_TYPE" style="width:130px;"/>
					<input id="HID_basBasLocTypeCode" name="basBasLocTypeCode" type="hidden" title="货位类型"/>
				</div>
				<input id="putPri" name="putPri" class="easyui-numberbox" style="width:80px;" title="上架优先级"/>
				<input id="pickPri" name="pickPri" class="easyui-numberbox" style="width:80px;" title="拣货优先级"/>
				<input id="maxPalletFloor" name="maxPalletFloor" class="easyui-numberbox" style="width:80px;" title="最大堆放层数"/>
				<input id="maxVoluem" name="maxVoluem" class="easyui-numberbox" style="width:80px;" title="最大体积"/>
				<input id="maxWeight" name="maxWeight" class="easyui-numberbox" style="width:80px;" title="最大重量"/>
				<input id="maxPalletQty" name="maxPalletQty" class="easyui-numberbox" style="width:80px;" title="最大托盘数"/>
				<input id="length" name="length" class="easyui-numberbox" style="width:80px;" title="长"/>
				<input id="width" name="width" class="easyui-numberbox" style="width:80px;" title="宽"/>
				<input id="height" name="height" class="easyui-numberbox" style="width:80px;" title="高"/>
				<input id="lotX" name="lotX" class="easyui-numberbox" style="width:80px;" title="货架位置-行"/>
				<input id="lotY" name="lotY" class="easyui-numberbox" style="width:80px;" title="护驾位置-列"/>
				<input id="lotZ" name="lotZ" class="easyui-numberbox" style="width:80px;" title="货架位置-高"/>
				<div>
					<input id="lockFlag" name="lockFlag" class="easyui-combobox" style="width:40px;" title="锁定"/> 优先级:
					<input id="pri" name="pri" class="easyui-validatebox" style="width:40px;" title=""/>
				</div>
				<input id="_basLocAreaType" name="basLocAreaUuid" class="easyui-combobox" style="width:80px;"title="所属区域"/>
				<input id="remark" name="remark" class="easyui-validatebox" style="width:80px;" title="备注"/>
			</form>
		</div>
		
		<div id="ProcessingInTheDIV" title="ing..." style="width: 54px;height: 8px;background-color: white;" align="center">
			<img src="<%=path%>/js/easyui/themes/icons/loading.gif" />
		</div>
  </body>
