<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<script type="text/javascript">
	
	//查询
	function query() {
		$("#gridResult").datagrid("commonQuery", {
			queryType : "BasVesselModel",
			paramForm : "formQuery"
		});
	};
	
	//重置
	function clearQueryForm() {
		$("#formQuery").form("clear");
	};
	
	//新增
	function appendRow() {
		$("#gridResult").datagrid("appendRow", {
			
		});
	};
	
	//插入
	function insertRow() {
		$("#gridResult").datagrid("insertRow", {
			index : $("#gridResult").datagrid("getSelectedIndex"),
			row : {
				
			}
		});
	};
	
	//编辑
	function editRow() {
		var row = $("#gridResult").datagrid("endEdit").datagrid("getSelected");
		if (row) {
			$("#dialogEdit").dialog("open");
			$("#formEdit").form("setData", row);
		}
	};
	
	//删除
	function deleteSelectedRows() {
		$("#gridResult").datagrid("deleteSelectedRows");
	};
	
	
	
	
	//保存
	function save() {
		if (! $("#gridResult").datagrid("validate")) {
			$.messager.alert("提示", "数据验证错误", "warning");
			return;
		}
		var rows = $("#gridResult").datagrid("getChanges");
		if (rows.length == 0) {
			$.messager.alert("提示", "未修改数据", "warning");
			return;
		}
		for(var i=0;i<rows.length;i++){
			if(rows[i].status == "" || rows[i].status == null){
				rows[i].status="Active";
			}
		}
		BasVesselManager.saveAll(rows, function(result) {
			$("#gridResult").datagrid("refreshSavedData", result);
			$.messager.alert("提示", "保存成功", "info");
		});
	};
	
	//取消
	function reload() {
		$("#gridResult").datagrid("reload");
	};
	
	//编辑窗口确定
	function editDialogOk() {
		if (! $("#formEdit").form("validate")) {
			$.messager.alert("提示", "数据验证错误", "warning");
			return;
		}
		$("#dialogEdit").dialog("close");
		$("#gridResult").datagrid("updateRow", {
			index : $("#gridResult").datagrid("getSelectedIndex"),
			row : $("#formEdit").form("getData")
		});
	};
	
	//编辑窗口取消
	function editDialogCancel() {
		$("#dialogEdit").dialog("close");
	};
	
	$(function() {
		//查询数据后自动开始grid编辑
		$("#gridResult").datagrid("options").onLoadSuccess = function(row, data) {
			$.fn.datagrid.defaults.onLoadSuccess.apply(this, [row, data]);
			$("#gridResult").datagrid("selectRow", 0).datagrid("beginEdit", 0);
		};
		//$('input:text[name="lineCode"]').focus();
		//开始编辑行，自动定位到第一列
		$("#gridResult").datagrid("options").onBeginEdit = function(rowIndex, rowData) {
			$.fn.datagrid.defaults.onBeginEdit.apply(this, [rowIndex, rowData]);
			$("#gridResult").datagrid("getColumnEditor", "vesselCode").select();
		};

	});
	function mapping(){
		var row = $("#gridResult").datagrid("endEdit").datagrid("getSelected");
		var title = "数据字典映射";
		var url = "jsp/dcs/dcsMappingInfo.jsp?tableName=BAS_VESSEL&basName="+row.vesselName;
		
		parent.addTabs(title, url, "icon icon-nav", true);
		parent.addTabs(title, url, false, true); //21Aug, fix slow problem...
		if(parent.getTabIframe(title).contentWindow.queryDetail){
			parent.getTabIframe(title).contentWindow.queryDetail('BAS_VESSEL',row.vesselName); //21Aug, fix slow problem...	
		}
	}
</script>
</head>
  
  <body class="easyui-layout">
  	<div region="north" title="查询条件" iconCls="icon-search">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="clearQueryForm()">重置</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="3" i18nRoot="BasDanger">
			<input name="linerCode"  title="船东代码"/>
			<input name="lineCode"  title="航线代码"/>
			<input name="vesselCode"  title="船舶代码"/>
			<input name="vesselType" title="船型"/>
			<input name="vesselNameEn" title="英文名"/>
			<input name="vesselName" operator="ilikeAnywhere"  title="中文名"/>
		</form>
	</div>
	
	<div region="center" title="危险品信息" iconCls="icon-edit">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-redo" onclick="insertRow()">插入</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editRow()">编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()">取消</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="mapping()">映射</a>
		</div>
		<table id="gridResult" class="easyui-datagrid" i18nRoot="BasDanger" fit="true">
			<thead>
				<tr>
					
					<th field="vesselCode" editor="{type:'validatebox', options:{required:true, validType:'length[0,50]'}}" title="船舶代码"  required="true"/>			
					<th field="vesselName" editor="text" title="中文名"/>
					<th field="vesselNameEn" editor="text" title="英文名"/>
					<th field="mnemonicCode" editor="text" title="助记码"/>
					<th field="linerCode" editor="text" title="船东代码"/>
					<th field="lineCode" editor="text" title="航线代码"/>
					<th field="vesselType" editor="text" title="船型"/>
					<th field="countryId" editor="text" title="船籍（船旗）"/>
					<th field="vesselNo" editor="text" title="船舶出厂号"/> 
					<th field="registerPort" editor="text" title="船原籍港"/>
					<th field="netRt" editor="text" title="船净重吨"/>
					<th field="grsRt" editor="text" title="船总重吨"/>
					<th field="loadWt" editor="text" title="船载重吨"/>
					<th field="totalLength" editor="text" title="船总长度"/>
					<th field="loadTeu" editor="text" title="装载总集装箱数"/>
					<th field="loadTeuE" editor="text" title="其中可装吉箱数"/>
					<th field="customsLicenseNo" editor="text" title="船舶海关编号"/>
					<th field="boundaryNo" editor="text" title="边防备案号"/>
					<th field="vesselLicenseId" editor="text" title="船许可证号"/>
					<th field="imoLicenseNo" editor="text" title="海事备案号"/>
					<th field="contactUser" editor="text" title="业务联系人"/>
					<th field="contactMobile" editor="text" title="业务联系人手机"/>					
					<th field="vesselControlWord" editor="{type:'combobox', options:{editable:false}}" codetype="VESSEL_TYPE" title="船舶类型"/>				
					<th field="remarks" editor="text" title="备注"/>
				</tr>
			</thead>
		</table>
	</div>
	
	<!-- 弹出窗口编辑 -->
	<div id="dialogEdit" class="easyui-dialog" title="危险品信息" iconCls="icon-edit"
			style="width:600px;padding:10px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="2" i18nRoot="BasDanger">	
			
			
			
			
			<input name="vesselCode" class="easyui-validatebox" title="船舶代码"  required="true"/>	
			<input name="vesselName" class="easyui-validatebox" title="中文名"/>
			<input name="vesselNameEn" class="easyui-validatebox" title="英文名"/>
			<input name="linerCode" class="easyui-validatebox" title="船东代码"/>
			<input name="lineCode" class="easyui-validatebox" title="航线代码"/>
			<input name="vesselType" class="easyui-validatebox" title="船型"/>
			<input name="countryId" class="easyui-validatebox" title="船籍（船旗）"/>
			<input name="vesselNo" class="easyui-validatebox" title="船舶出厂号"/>
			<input name="registerPort" class="easyui-validatebox" title="船原籍港"/>
			<input name="netRt" class="easyui-validatebox" title="船净重吨"/>
			<input name="grsRt" class="easyui-validatebox"  title="船总重吨"/>
			<input name="loadWt" class="easyui-validatebox" title="船载重吨"/>
			<input name="totalLength" class="easyui-validatebox" title="船总长度"/>
			<input name="loadTeu" class="easyui-validatebox" title="装载总集装箱数"/>
			<input name="loadTeuE" class="easyui-validatebox" title="其中可装吉箱数"/>
			<input name="customsLicenseNo" class="easyui-validatebox" title="船舶海关编号"/>
			<input name="boundaryNo" class="easyui-validatebox" title="边防备案号"/>
			<input name="vesselLicenseId" class="easyui-validatebox" title="船许可证号"/>
			<input name="imoLicenseNo" class="easyui-validatebox" title="海事备案号"/>
			<input name="contactUser" class="easyui-validatebox" title="业务联系人"/>
			<input name="contactMobile" class="easyui-validatebox" title="业务联系人手机"/>
			<input name="vesselControlWord" class="easyui-combobox" codetype="VESSEL_TYPE" editable="false" required="true" title="船舶类型"/>
			<textarea name="remark" rowspan="2" colspan="2" title="备注"></textarea>
		</form>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogOk()">确定</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
		</div>
	</div>
</body>
</html>	