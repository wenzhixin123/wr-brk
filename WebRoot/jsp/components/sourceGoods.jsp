<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String path=request.getContextPath();
	SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
 %>
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<script type="text/javascript">
	var div_id='${param.divId}';
	var _key='${param.key}';
	var transaction_type='${param.type}';
	$(function(){
		//alert(divId+"=="+key+"=="+value);
		_searchRemainHold();
	});
	function _searchRemainHold(){
		$("#selGrid").datagrid("setQueryFields",[
			{
				fieldName:"transactionType",
				fieldStringValue:transaction_type
			},
			{
				fieldName:"officeCode",
				fieldStringValue:'<%=scue.getOfficeCode()%>'
			}
		]);
		$("#selGrid").datagrid("commonQuery",{
			queryType:'HUBRemainHoldByItemCodeQuery',
			paramForm:'searchForm'
		});
	}
	function _reset(){
		$("#searchForm").form("clear");
	}
	function _defineSel(){
		var selItems=$("#selGrid").datagrid("getSelections");
		if(selItems!=null&&selItems.length>0){
			var oldItems=parent.$('#'+_key+'').datagrid("getData").rows;
			var b=true;
			$.each(oldItems,function(i,row){
				$.each(selItems,function(index,selItem){
					if(row.remainHoldUuid==selItem.remainHoldUuid){
						$.messager.alert("提示","不能重复选择相同的数据!","info");
						b=false;
						return false;
					}
				});
				if(!b){
					return false;
				}
			});
			if(b){
				$.each(selItems,function(index,selItem){
					parent.$('#'+_key+'').datagrid('appendRow',selItem);
				});
				parent.$('#'+div_id+'').window('close');
			}
			$("#selGrid").datagrid("unselectAll");
		}else{
			$.messager.alert("提示","至少选择一行!","info");
		}
	}
	function _strTrim(obj){
		var str=$(obj).val();
		$(obj).val($.trim(str));
	}
</script>
</head>

<body class="easyui-layout">
	<div region="north" border="false">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_searchRemainHold();">
			<bean:message bundle="wlp.common" key="global.search" /></a>
			<a class="easyui-linkbutton" iconCls="myCustomerIcon_clear" plain="true" onclick="_reset();">
			<bean:message bundle="wlp.common" key="global.reset" /></a>
			<a class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="_defineSel();">
			<bean:message bundle="wlp.common" key="global.ok" /></a>
		</div>
		<form class="easyui-form" id="searchForm" columns="2" i18nRoot="GoodsSourceSelected">
			<input name="logisticsOrderNo" id="logisticsOrderNo" class="easyui-validatebox" onblur="_strTrim('#logisticsOrderNo')"/>		
			<input name="itemCode" id="itemCode" class="easyui-validatebox" onblur="_strTrim('#itemCode')"/>
			<input name="contractNo" id="contractNo"  class="easyui-validatebox" onblur="_strTrim('#contractNo')"/>
			<input name="orderNo" id="orderNo"  class="easyui-validatebox" onblur="_strTrim('#orderNo')"/>
			<div colspan="2">
			<input name="orderDate" id="dateBegin" class="easyui-datebox" operator="dateBegin" style="width:85px;"/>
			<img src="<%=path %>/fros-easyui/js/jquery-easyui-1.2.4/themes/icons/right.gif"/>
			<input name="orderDate" id="dateEnd" class="easyui-datebox" operator="dateEnd" style="width:85px;"/>
			</div>
		</form>
	</div>
	<div region="center" border="false">
		<table class="easyui-datagrid" id="selGrid" fit="true"
			i18nRoot="GoodsSourceSelected">
			<thead>
				<th field="inLogisticsOrderNo" width="110">
				</th>
				<th field="seqNo" width="35" align="center"></th>
				<th field="itemCode" width="200">
				</th>
				<th field="goodsDesc" width="200">
				</th>
				<th field="remainQty" width="60" align="right">
				</th>
				<th field="remainQtyHoldQtySum" width="90" align="right">
				</th>
				<th field="secondQty" width="95" align="right">
				</th>
				<th field="remainSecondQtyHoldSum" width="85" align="right">
				</th>
				<th field="netWeight" width="70" align="right">
				</th>
				<th field="grossWeight" width="70" align="right">
				</th>
				<th field="length" width="50" align="right">
				</th>
				<th field="width" width="50" align="right">
				</th>
				<th field="height" width="50" align="right">
				</th>
				<th field="volume" width="70" align="right">
				</th>
			</thead>
		</table>
	</div>
</body>
</html>