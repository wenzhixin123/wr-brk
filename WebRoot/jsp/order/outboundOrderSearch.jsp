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
		<script >
			var tabTitle = ["订单列表", "订单明细"];
			var uuid="";
			$(function() {
				setDefDate();
				initCombox("#deliveryType", "<%=CommonUtil.OPTION_OUT_WORK_TYPE%>");
				$("#deliveryType").combobox("setValue","");
				$('#submitOrderGrid').datagrid({
					onDblClickRow : function(rowIndex, rowData) {
						$('#dataTabs').tabs("select", "订单明细");
						 uuid = rowData.submitOrderUuid;
						//获取UUID
						$("#submitDetailDatagridIn").datagrid("setQueryFields", [{
							fieldName : "submitOrderUuid",
							fieldStringValue : uuid
						}]);
						$("#submitDetailDatagridIn").datagrid("commonQuery", {
							queryType : "YclSubByUuidQuery"
						});
					}
				});

			});
			
			function initCombox(comBoxId, type) {
				WmsCommonManager.systemCodeCodeDef(type, "<%=CommonUtil.Active%>", "<%=officeCode%>", function(spj) {
					if (spj.object) {
						$(comBoxId).combobox({//货物类型
							valueField : "id",
							textField : "text",
							data : spj.object,
							editable : false
						});
					}
				});
			}
				

			function setDefDate() {
				/* WmsCommonManager.getDataBaseDateFor_Yyyy_Mm_Dd(function(result) {
					$("#dateBegin").datebox("setValue", result);
					$("#dateEnd").datebox("setValue", result);
				}); */
			}


			function searchOrder() {
				$("#dataTabs").tabs("select", tabTitle[0]);
				$("#officeCode").val('<%=officeCode%>');
				$("#submitOrderGrid").datagrid("commonQuery", {
					queryType : "yclOutBoundOrderMangeSearchQueryDataSource",
					paramForm : "searchForm"
				});
			}

			function reset() {
				$("#searchForm").form("clear");
				setDefDate();
			}

			function addOrder() {
				window.parent.addTabs("出库订单办理", "<%=path%>/jsp/order/outboundOrderEditYL.jsp?type=1", true, true);
			}

			function editOrder() {
				var selItem = check();
				if (selItem != null) {
					window.parent.addTabs("出库订单编辑", "<%=path%>/jsp/order/outboundOrderEditYL.jsp?submitOrderUuid=" + selItem.submitOrderUuid, true, true);
				}
			}

			function check() {
				var selItems = $("#submitOrderGrid").datagrid("getSelections");
				if (selItems != null && selItems.length > 0) {
					if (selItems.length == 1) {
						return selItems[0];
					} else {
						$.messager.alert("提示", "一次只能对一条数据进行操作!", "info");
					}
				} else {
					$.messager.alert("提示", "请选择您要进行操作的行!", "info");
				}
				return null;
			}

			//审核
			function submitButton() {
				toSbumitOrder();
			}

			function toSbumitOrder() {
				var submitOrder = $("#submitOrderGrid").datagrid("getSelections");
				if (submitOrder) {
					$.messager.confirm("确认框", "您是否确认提交?", function(b) {
						if (!b) {
							return;
						}
						for (var i = 0; i < submitOrder.length; i++) {
							SubmitOrderOutManager.auditOrderSot(submitOrder[i], null, submitOrder[i].submitOrderUuid.toString(), '<%=officeCode%>', function(data) {
								if (showMsg(data)) {
									gridReload();
								}
							});
						}
					});
				}
			}
			
			//取消审核
			function cancelSubmitButton() {
				var uuids = getOrderUuids('<%=CommonUtil.Active%>');
				var submitOrder = $("#submitOrderGrid").datagrid("getSelections");
				if (uuids != "") {
					$.messager.confirm("确认框", "您是否确认取消提交?", function(b) {
						if (!b) {
							return;
						}
						for (var i = 0; i < submitOrder.length; i++) {
							YclOrderManager.cancelAuditOrderSot(submitOrder[i], null, function(data) {
								if (showMsg(data)) {
									gridReload();
								}
							});
						}
					});
				}
			}

			//删除整个订单
			function removeButton() {
				var uuids = getOrderUuids('<%=CommonUtil.Pending%>');
				if (uuids == null || uuids == "") {
					return;
				}
				$.messager.confirm("确认框", "您确定删除吗?", function(b) {
					if (!b) {
						return;
					}
					SubmitOrderManager.delSubmitOrder(uuids.toString(), '<%=officeCode%>', function(returnValue) {
						if (showMsg(returnValue)) {
							gridReload();
						}
					});
				});
			}


			function strTrim(obj) {
				var str = $(obj).val();
				$(obj).val($.trim(str));
			}

			function strUppercase(obj) {
				var str = $(obj).val();
				$(obj).val(str.toLocaleUpperCase());
			}

			function gridReload() {
				$("#submitOrderGrid").datagrid("unselectAll");
				$("#submitOrderGrid").datagrid("reload");
			}

			function getOrderUuids(status) {
				var orders = $("#submitOrderGrid").datagrid("getSelections");
				var uuids = new Array();
				var errorItems = new Array();
				if (orders && orders.length > 0) {
					$.each(orders, function(index, order) {
						if (order.transactionStatus != status) {
							errorItems.push(order.submitOrderNo);
						} else {
							var uuid = order.submitOrderUuid;
							uuids.push(uuid);
						}
					});
				} else {
					$.messager.alert("提示", "请选择您要进行操作的行!", "info");
				}
				if (errorItems.length > 0) {
					$.messager.alert("提示", "订单:\n" + errorItems.toString() + "\n状态不符合操作要求!");
					return "";
				}
				return uuids.toString();
			}

			function showMsg(returnValue) {
				if (returnValue.result) {
					$.messager.alert('提示', returnValue.msg, 'info');
					return true;
				} else {
					$.messager.alert('提示', returnValue.error, "error");
					return false;
				}
			}


			function saveReceiptSo() {
				var jmReceiptType = $("#jmReceiptType").combobox("getValue");
				if (jmReceiptType == '1') {
					toSbumitOrder();
				} else {
					//买卖单
				}
			}

			function soDetailGridReload(){
				$("#submitDetailDatagridIn").datagrid("unselectAll");
				$("#submitDetailDatagridIn").datagrid("reload");	
			}


		</script>
	</head>

	<body class="easyui-layout">
		<div region="north" title="查询条件" iconCls="icon-search">
			<div class="datagrid-toolbar">
				<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchOrder();">查询</a>
				<a class="easyui-linkbutton" iconCls="myCustomerIcon_clear" plain="true" onclick="reset();">重置</a>
			</div>
			<form id="searchForm" class="easyui-form" columns="4" i18nRoot="OutOrderHeadYcl">
				<input type="hidden" id="officeCode" name="officeCode" />
				<textarea title="订单号" rows="1" cols="1" name="orderNo" id="orderNo" style="width: 157px; height: 32px; font-size: 12px;" class="easyui-validatebox" onkeyup="strUppercase('#orderNo')" onblur="strTrim('#orderNo')"></textarea>
				<input title="出库订单号" name="submitOrderNo" id="submitOrderNo"  class="easyui-validatebox" onkeyup="strUppercase('#submitOrderNo')" onblur="strTrim('#submitOrderNo')"/>	 
				<input name="cargoConsigneeCode" id="cargoConsigneeCode" class="easyui-combogrid" codetype="ALL_CUSTOMER" title="供应商"/>
				<input name="cargoConsigneeDesc" id="cargoConsigneeDesc" type="hidden"/>
				<input name="itemCode" id="itemCode" title="物料编码"  class="easyui-validatebox"/> 
		        <input name="deliveryType" id="deliveryType" class="easyui-combobox"  editable='false' title="作业类型"/>
				<input name="transactionStatus" id="transactionStatus" title="单据状态" class="easyui-combobox"  codetype="STATUS" />
				<div title="办单日期">
					<input name="dateBegin" id="dateBegin" class="easyui-datebox"  style="width:90px;"/>
					<img src="<%=path%>/images/right.gif"/>
					<input name="dateEnd" id="dateEnd" class="easyui-datebox"  style="width:90px;"/>
				</div>
			</form>
		</div>
		<div region="center" border="false">
			<div class="easyui-tabs" fit="true" id="dataTabs">
				<div title="订单列表">
					<div class="easyui-layout" fit="true">
						<div region="north">
							<div class="datagrid-toolbar">
								<a class="easyui-linkbutton" plain="true"  iconCls="icon-add" onclick="addOrder();" id="addBtn">新增</a>
								<a class="easyui-linkbutton" id="editBtn" plain="true"  iconCls="icon-edit" onclick="editOrder();">编辑</a>
								<a class="easyui-linkbutton" id="auditBtn" plain="true" iconCls="icon-ok" onclick="submitButton();">提交</a>
								<a class="easyui-linkbutton" id="cancelBtn" plain="true" iconCls="icon-cancel" onclick="cancelSubmitButton();">取消提交</a>
								<a class="easyui-linkbutton" id="delBtn" plain="true" iconCls="myCustomerIcon_remove" onclick="removeButton();">删除</a>
							</div>
						</div>
						<div region="center">
							<table class="easyui-datagrid" id="submitOrderGrid" fit="true" i18nRoot="OutOrderHeadYcl" >
								<thead>
									<tr>
										<th field="transactionStatus" align="center" width="50px;" codetype="STATUS"  title="状态"></th>
										<th field="deliveryType" title="作业类型" ></th>
										<th field="submitOrderNo" title="出库订单号"></th>
										<th field="orderNo" title="订单号"></th>
										<th field="submitDate" title="办单时间"></th>
										<th field="cargoConsigneeDesc" title="供应商"></th>
										<th field="sumqty" align="right" title="计划数"></th>
										<th field="orderQty" align="right" title="办单数"></th>
										<th field="configName" title="出库策略"></th>
										<th field="remark" title="备注"></th>
										<th field="creator" title="创建人"></th>
									</tr>	
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div title="订单明细">
					<div class="easyui-layout" fit="true">
						<div region="center">
							<table id="submitDetailDatagridIn" class="easyui-datagrid" fit="true" i18nRoot="OutOrderDetailYcl">
								<thead>
									<tr>
										<th field="transactionStatus" align="center" width="50px;"  codetype="STATUS"  title="状态"></th>
										<th field="orderNo" title="订单号"></th>
										<th field="itemCode" title="物料编码"></th>
										<th field="goodsDesc"  width="250px;" title="物料名称"></th>
										<th field="qty" align="right" title="计划数"></th>
										<th field="orderQty" align="right" title="办单数">
										<th field="extItemCode" title="供应商编码"></th>
										<th field="batchNo" title="批次号"></th>
										<th field="spec" title="规格"></th>
										<th field="createTime" title="创建时间"></th>
										<th field="remark" title="备注" ></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>