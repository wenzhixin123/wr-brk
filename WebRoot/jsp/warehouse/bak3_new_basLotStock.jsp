<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/fros-easyui/common/include.jsp" %>
	<%@page
			import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
	<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
	<%@page import="com.sinotrans.gd.wlp.util.StringUtil"%>
	<%@page import="com.sinotrans.gd.wlp.common.web.RcUtil"%>
	<%
		String path = request.getContextPath();
		SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
		String officeCode = SessionContextUserEntity.currentUser().getOfficeCode();
	%>


  </head>
  <body class="easyui-layout">
		<div id="codeTypeContainer" region="west" title="仓库" border="true" style="padding:0px;width:300px;" split="true">
			 <%--按钮--%>
			<div class="datagrid-toolbar">
				<a href="#" id="editNewLocAreaBtn" class="easyui-linkbutton" plain="true" onclick="addWareLocArea()" iconCls="icon-add">
					新增区域</a>
				<a href="#" id="PH_WARE_BAL_REMOVEWARE" class="easyui-linkbutton" plain="true" onclick="removeWareLocArea()" iconCls="myCustomerIcon_remove">
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
										<input type="text" id="warehouseCode" name="warehouseCode" class="input_style" onblur="strTrim(this.id);" readonly="readonly" title="仓库编号:">
										<br/>
										<input type="text" id="warehouseName" name="warehouseName" class="input_style" onblur="strTrim(this.id);" title="仓库名称:">
										<br/>
										<input type="text" id="warehouseNameEn" name="warehouseNameEn" class="input_style" onblur="strTrim(this.id);" title="仓库英文名称:">
										<br/>
										<input type="text" id="warehouseCapacity" name="warehouseCapacity" class="input_style" onblur="strTrim(this.id);" title="仓库容量:">
										<br/>
										<input type="text" id="warehouseAddress" name="warehouseAddress" class="input_style" onblur="strTrim(this.id);" title="仓库地址:">
										<br/>
										<input type="text" id="serviceArea" name="serviceArea" class="input_style" onblur="strTrim(this.id);" title="服务区域:">
										<br/>
										<input type="text" id="serviceAging" name="serviceAging" class="input_style" onblur="strTrim(this.id);" title="时效:">
										<br/>
										<input type="text" id="remark" name="remark" class="input_style" onblur="strTrim(this.id);" title="备注:">
										<h4>地图信息:      <a id="mapmassege" name="" style="color : red;"></a><h4/>
										<div style="margin:-20px 0px;">
											<a id="savesvgPath1" name=""  class="easyui-linkbutton"  onclick="uploadwarehousemap()" style="margin:0px 20px;">上传</a>
											<a id="downloadmap" name=""  class="easyui-linkbutton" onclick="downloadwarehousemap()" >下载</a>
										</div>
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
										<input type="text" id="locAreaCode" name="locAreaCode" class="input_style easyui-validatebox" onblur="strTrim(this.id);" required="true" onkeyup="javascript:this.value=this.value.toLocaleUpperCase();" readonly="readonly" title="区域代码:">
										<br/>
										<input type="text" id="locAreaName" name="locAreaName" class="input_style" onblur="strTrim(this.id);" title="区域名称:">
										<br/>
										<input type="text" id="locAreaNameEn" name="locAreaNameEn" class="input_style" onblur="strTrim(this.id);" title="区域描述:">
										<br/>
										<input type="text" id="_remark" name="_remark" class="input_style" onblur="strTrim(this.id);" title="备注:">
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
							<form id="searchLotStock" class="easyui-form" i18nRoot="LogisticsOrderOI" columns="3">
								<input name="lotCode" class="inputSearch_style" title="货位编号:">
								<input name="lotName" class="inputSearch_style" title="货位名称:">
								<div>
									<input id="com_basBasLocTypeUuid" class="easyui-combogrid"  codetype="BAS_LOC_TYPE">
									<input id="se_basBasLocTypeUuid" name="basBasLocTypeUuid" type="hidden" title="货位类型:">
								</div>
								<input id="status" name="status" formatter="statusFormatter" title="状态:" style="width:60px;">
								<input id="pri" name="pri" class="inputSearch_style" title="优先级:">
							</form>
						</div>
						<!-- 中部布局存放仓库信息 -->
						<div region="center" title="仓库信息">
							<!-- 按钮部分 -->
							<div class="datagrid-toolbar" >
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
								<a id="insectImportMetdod" class="easyui-linkbutton" href="#" iconCls="icon_shengcheng" onclick="barcodeImportMethod()"> 
									导入</a>
								<a id="printBtn" class="easyui-linkbutton" href="#" iconCls="icon-print" onclick="printLot()"> 
									打印</a>
							</div>
							<!-- 数据列表部分 -->
							<table id="baslLotStockData" fit="true" class="easyui-datagrid" pageList="[30,50,100]">
								<thead>
									<tr>
										<th field="status" width="50px;"  title="状态" ></th>
										<th field="controlWord" width="50px;"  title="默认"></th>
										<th field="lotCode" width="130px;"  title="货位编号"></th>
										<th field="lotName" width="130px;"  title="货位名称"></th>
										<th field="basBasLocTypeUuid" width="130px;"  title="货位类型"></th
										><!-- 
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
			<form id="addBasLotStock" class="easyui-form"  i18nRoot="LogisticsOrderOI" columns="3">
				<input id="lotCode" name="lotCode" class="easyui-validatebox" style="width:130px;" readonly="readonly" onblur="strTrim(this.id);" onkeyup="javascript:this.value=this.value.toLocaleUpperCase();" title="货位编号:" required="true"/>
				<input id="lotName" name="lotName" class="easyui-validatebox" style="width:130px;" title="货位名称:" required="true"/>
				<div>
					<input id="basBasLocTypeUuid" class="easyui-combogrid" codetype="BAS_LOC_TYPE" style="width:130px;"/>
					<input id="HID_basBasLocTypeUuid" name="basBasLocTypeUuid" type="hidden" title="货位类型:"/>
				</div>
				<input id="putPri" name="putPri" class="easyui-numberbox" style="width:80px;" title="上架优先级:"/>
				<input id="pickPri" name="pickPri" class="easyui-numberbox" style="width:80px;" title="拣货优先级:"/>
				<input id="maxPalletFloor" name="maxPalletFloor" class="easyui-numberbox" style="width:80px;" title="最大堆放层数:"/>
				<input id="maxVoluem" name="maxVoluem" class="easyui-numberbox" style="width:80px;" title="最大体积:"/>
				<input id="maxWeight" name="maxWeight" class="easyui-numberbox" style="width:80px;" title="最大重量:"/>
				<input id="maxPalletQty" name="maxPalletQty" class="easyui-numberbox" style="width:80px;" title="最大托盘数:"/>
				<input id="length" name="length" class="easyui-numberbox" style="width:80px;" title="长:"/>
				<input id="width" name="width" class="easyui-numberbox" style="width:80px;" title="宽:"/>
				<input id="height" name="height" class="easyui-numberbox" style="width:80px;" title="高:"/>
				<input id="lotX" name="lotX" class="easyui-numberbox" style="width:80px;" title="货架位置-行:"/>
				<input id="lotY" name="lotY" class="easyui-numberbox" style="width:80px;" title="护驾位置-列:"/>
				<input id="lotZ" name="lotZ" class="easyui-numberbox" style="width:80px;" title="货架位置-高:"/>
				<div>
					<input id="lockFlag" name="lockFlag" class="easyui-combobox" style="width:40px;" title="锁定:"/> 优先级:
					<input id="pri" name="pri" class="easyui-validatebox" style="width:40px;" title=""/>
				</div>
				<input id="_basLocAreaType" name="basLocAreaUuid" class="easyui-combobox" style="width:80px;"title="所属区域:"/>
				<input id="remark" name="remark" class="easyui-validatebox" style="width:80px;" title="备注:"/>
			</form>
		</div>
		
		<div id="ProcessingInTheDIV" title="ing..." style="width: 54px;height: 8px;background-color: white;" align="center">
			<img src="<%=path%>/js/easyui/themes/icons/loading.gif" />
		</div>
  </body>
