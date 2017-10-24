<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page import="java.util.UUID"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<%
	String path = request.getContextPath();
SessionContextUserEntity scue = SessionContextUserEntity.currentUser(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
	<head>
		<!-- jquery easyui -->
		<jsp:include page="../../common/imp_dwr.jsp"></jsp:include>
		<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
		<style type="text/css">
#tableQueryForm td {
	font-size: 12px;
}
</style>
	</head>
	<!-- 组织机构管理页面 2012年2月28日 19:09:47-->
	<body class="easyui-layout">
		<script>
			var lastIndex;
			var officeTypeWholeArray=new Array();
			var officeType="";
			$(function(){
				__layoutButtonManger();//伸缩按钮和弹出框初始化
				__officeitemdatagirdManger();//组织机构管理初始化和得到数据
				gongsi();
			});
		</script>
		<script type="text/javascript">
			function gongsi(){
			<%
			String agentConsigneeDesc = CommonUtil.SELECT_CODE_ALL_CUSTOMER;
			String agentConsigneeDescmodelName = agentConsigneeDesc.split(",")[0] + "Model";
			%>
			var customerRow = [{name:'customerCode',value:'客户代码'},{name:'customerName',value:'中文名称'},{name:'customerNameEn',value:'客户名称'}];
			
			combogridSetValueAndTest('customerCode1','',customerRow,'<%=path%>','<%=agentConsigneeDescmodelName%>','',true);
			}
			function __layoutButtonManger(){
				$(".layout-button-left").remove();//删除左侧伸缩按钮
				$('#OfficeItem').dialog();
				$('#OfficeItem').dialog('close');
			}
			function __officeitemdatagirdManger(){
				officeTypeWholeArrayManger();
				
				//获取数据延迟300毫秒 来保证数据获取的完整和页面加载的速度
				setTimeout(function(){
				var isoUrl="";
				 if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType=SysOfficeModel";
					}else{
				     isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType=SysOfficeModel&oc=true";
					}
					$('#officeitemdatagird').datagrid({
						height:448,
						pagination:true,
						rownumbers:true,
						striped:true,
						singleSelect: true,
						pageSize:20, //每页显示记录数
						pageList:[10,20,30,40,50], //可调整每页显示的记录数
						url:isoUrl,
						idField:'officeUuid',
						sortName:'officeName',
						sortOrder:'asc',
						columns:[[
							{field:"status",title:"状态",width:60,align:"center",formatter:statusOOr,sortable:true},
							{field:"officeCode",title:"组织编码",width:150,align:"center",td_align:"left",sortable:true},
							{field:"officeType",title:"组织类型",width:100,align:"center",td_align:"left",formatter:officeTypeHuoQu,sortable:true},
							{field:"officeName",title:"组织全称",width:200,align:"center",td_align:"left",sortable:true},
							{field:"abbrev",title:"组织简称",width:150,align:"center",td_align:"left",sortable:true},
							{field:"officeNameEn",title:"组织英文名",width:260,align:"center",td_align:"left",sortable:true},
							{field:"businessRegisterNo",title:"工商登记号",width:120,align:"center",td_align:"left",sortable:true},
							{field:"taxRegisterNo",title:"税务登记号",width:120,align:"center",td_align:"left",sortable:true},
							{field:"contact",title:"联系人",width:85,align:"center",td_align:"left",sortable:true},
							{field:"tel",title:"电话",width:85,align:"center",td_align:"left",sortable:true}
						]],
						toolbar:[{
							text:'新增',
							iconCls:'icon-add',
							handler:function(){
								officeItemNew();
							}
						},'-',{
							text:'编辑',
							id:'editOfficeitem',
							iconCls:'icon-edit',
							handler:function(){
								officeItemEdit();
							}
						},'-',{
							text:'删除',
							id:'deleteOfficeitem',
							iconCls:'myCustomerIcon_remove',
							handler:function(){
								officeItemDelete();
							}
						},'-',{
						text:'生效',
						id:'activeRoleitem',
						iconCls:'icon-ok',
						handler:function(){
							userdatagirdStatus("<%=CommonUtil.Active%>");
						}
					},'-',{
						text:'作废',
						id:'claseRoleitem',
						iconCls:'icon-cancel',
						handler:function(){
							userdatagirdStatus("<%=CommonUtil.Cancel%>");
						}
					}],
						onDblClickRow:function(rowIndex,rowData){
							$('#officeitemdatagird').datagrid('endEdit', lastIndex);
							$('#officeitemdatagird').datagrid('beginEdit', rowIndex);
							lastIndex = rowIndex;
						},
						onClickRow:function(rowIndex,rowData){
							if(rowData.status=="<%=CommonUtil.Active%>"||rowData.status=="<%=CommonUtil.Cancel%>"){
					    		__getdOe('d');
					    	}else{
								__getdOe('e');
							}
						}
					});
				},400);
			}
			//修改状态。需要传送的值不为空必须为状态
			function userdatagirdStatus(status){
				var row = $('#officeitemdatagird').datagrid('getSelected');
				if(row==null){
					alert('请先选择一行数据再进行操作！');
				}else{
					SysUserManager.updateSysStatusOue(row.officeUuid,status,"SYS_OFFICE","STATUS","OFFICE_UUID",function(data){
						if(data){
							alert("操作成功");
						}else{
							alert("操作失败");
						}
						reload();
						$('#officeitemdatagird').datagrid("reload");//根据表格ID刷新页面
					});
				}
			}
			//组织机构删除
			function officeItemDelete(){
				var row = $('#officeitemdatagird').datagrid('getSelected');
				if(row==null){
					alert('请先选择一行数据再进行操作!');
				}else{
					if(row.status=="<%=CommonUtil.Active%>"||row.status=="<%=CommonUtil.Cancel%>"){
			    		__getdOe('d');
			    		alert("有效、作废数据不能删除！");
			    	}else{
						deleteoffice(row.officeUuid,row.officeName);
					}
				}
			}
			//组织机构修改
			function officeItemEdit(){
			getButtonKongZhi("e");
				var row = $('#officeitemdatagird').datagrid('getSelected');
				if(row==null){
					alert('请先选择一行数据再进行操作!');
				}else{
					editupdateuser(row.officeUuid);
				}
			}
			//组织机构新增
			function officeItemNew(){
			getButtonKongZhi("e");
				$('#OfficeItem').dialog({
					modal:true
				});
				$("#saveDivButton").attr("name","saveOffice");//修改完成按钮数据为保存
				adduserget();//取出所有公司组织机构
				countryprovincecity();
				$("#officeCode1").attr("readonly",false);
				$('#preOfficeUuid1').combotree('setValue',"");//所属公司
				$('#preOfficeUuid1').combotree('enable');
				$("#preOfficeUuid2").val("");//把根据上级ID查询的框清空
				cleardate('OfficeItemForm');//重置表单信息
				$("#status1").combobox("select","<%=CommonUtil.Active%>");
			}
			//updateuser()
			function updateuser(){
				var node = $('#office_tree').tree('getSelected');
				editupdateuser(node.id);
			}
			//编辑时调用
			function editupdateuser(officeuuid){
				if(officeuuid==null){
					alert('请先选择一行数据再进行操作!');
				}else{
					$.ajax({
		   			type: "POST",
				    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectOfficeitemid&date="+new Date(),
				    data: "selectOfficeitem="+officeuuid+"",
				    dataType:'json',
				    success: function(date){
				    	$('#OfficeItem').dialog({
							modal:true
						});
						adduserget();//取出所有公司组织机构
						countryprovincecity();
						cleardate('OfficeItemForm');//重置表单信息
						$('#preOfficeUuid1').combobox('enable');
				    	$("#recVer1").val(date.recVer);//修改的必须条件（版本控制）
				    	$("#officeUuid1").val(date.officeUuid);//修改的必须条件（uuid）
				    	$("#officeCode1").val(date.officeCode);
				    	$("#officeName1").val(date.officeName);
				    	officeType=date.officeType;
				    	$("#officeNameEn1").val(date.officeNameEn);
				    	$("#abbrev1").val(date.abbrev);
				    	$('#language1').combobox('setValue',date.language);//语言
				    	$("#businessRegisterNo1").val(date.businessRegisterNo);
				    	$('#isCustomer1').combobox('setValue',date.isCustomer);//作为客户
				    	$('#status1').combobox('setValue',date.status);//状态
				    	$("#taxRegisterNo1").val(date.taxRegisterNo);
				    	$('#isInternal1').combobox('setValue',date.isInternal);//为内部办事处
				    	$('#contact1').combobox('setValue',date.contact);//联系人
				    	$("#xchgrName1").val(date.xchgrName);
				    	$('#isDept1').combobox('setValue',date.isDept);//作为结算公司
				    	$('#preOfficeUuid1').combotree('setValue',date.preOfficeUuid);//上级组织
						$('#preOfficeUuid1').combotree('enable');//让该框格可以编辑
				    	//alert(date.countryCode+" : "+date.provinceCode+" ; "+date.cityCode);
				    	if(date.countryCode!=null&&date.countryCode!=""){
							BasQueryUtilManager.getPropertyByCode({modelName:"BasCountry",keyProperty:"countryCode",valueProperty:"countryName",keyValue:date.countryCode},
							function(rs){
								$('#countryCode1').val(date.countryCode);//国家代码(隐藏域)
								$('#countryDesc1').combogrid('setValue',date.countryCode);//国家代码
								$("#countryDesc1").combogrid('setText',rs);
		           			});
				    	}
				    	if(date.provinceCode!=null&&date.provinceCode!=""){
				    		BasQueryUtilManager.getPropertyByCode({modelName:"BasProvince",keyProperty:"provinceCode",valueProperty:"provinceName",keyValue:date.provinceCode},
							function(rs){
								$('#provinceCode1').val(date.provinceCode);//省市代码(隐藏域)
								$('#provinceDesc1').combogrid('setValue',date.provinceCode);//省市代码
								$("#provinceDesc1").combogrid('setText',rs);
		           			});
				    	}
				    	if(date.cityCode!=null&&date.cityCode!=""){
				    		BasQueryUtilManager.getPropertyByCode({modelName:"BasCity",keyProperty:"cityCode",valueProperty:"cityName",keyValue:date.cityCode},
							function(rs){
								$('#cityCode1').val(date.cityCode);//城市代码(隐藏域)
								$('#cityDesc1').combogrid('setValue',date.cityCode);//城市代码
								$("#cityDesc1").combogrid('setText',rs);
		           			});
				    	}
				    	if(date.siteCode!=null&&date.siteCode!=""){
				    		BasQueryUtilManager.getPropertyByCode({modelName:"BasSite",keyProperty:"siteCode",valueProperty:"siteName",keyValue:date.siteCode},
							function(rs){
								$('#siteCode1').val(date.siteCode);//城市代码(隐藏域)
								$('#siteDesc1').combogrid('setValue',date.siteCode);//城市代码
								$("#siteDesc1").combogrid('setText',rs);
		           			});
				    	}
				    	
				    	$("#email1").val(date.email);
				    	//$("#customerCode1").combotree('setValue',date.customerCode);//所属公司代码
				    	if(date.customerCode!=null&&date.customerCode!=""){
							BasQueryUtilManager.getPropertyByCode({modelName:"BasCustomer",keyProperty:"customerCode",valueProperty:"customerName",keyValue:date.customerCode},
							function(rs){
								$('#customerCode1').combogrid('setValue',date.customerCode);//公司名称
								$('#customerCode1').combogrid('setText',rs);
		           			});
				    	}
				    	$("#tel1").val(date.tel);
				    	$("#fax1").val(date.fax);
				    	//$("#cancelDate1").datebox("setValue",date.cancelDate);//作废日期
				    	$("#address1").val(date.address);
				    	$("#remark1").val(date.remark);
				    	if(date.status!=null&&date.status!="" && date.status=='<%=CommonUtil.Active%>'||date.status=='<%=CommonUtil.Cancel%>'){
				    		$("#officeCode1").attr("readonly","readonly");
				    	}else{
				    		$("#officeCode1").attr("readonly",false);
				    	}
				    	$("#preOfficeUuid2").val("");//把根据上级ID查询的框清空
				    	$("#saveDivButton").attr("name","updateOffice");//修改完成按钮数据为修改
				    	setTimeout(function(){
				    		$('#officeType1').combobox('setValue',officeType);//组织类型
				    	},200);
				    	$.extend($.fn.validatebox.defaults.rules, { 
							preOfficeValidate: { 
								validator: function(value, param) { 
									if(value != null && value.length > 0 && $("#officeName1").val()==value) {
										return false;
									}else{
										return true;
									}
								}, 
								message: '不能选择本身作为上级组织！'
							}
						});
  					}
				});
				}
			}
			//取出所有公司组织机构
			function adduserget(){
				$("#preOfficeUuid1").combotree({//上级组织
					url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectOfficeitemtree1&date='+new Date(),
					onClick:function(node){
						
					}	
				});
				/*
				$("#customerCode1").combotree({//公司
					url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectOfficeitemtree1&date='+new Date(),
					onClick:function(node){
						//var isleaf=$('.tree').tree('isLeaf',node.target);
						//if(!isleaf){
						//	alert('此节点不是公司节点、请明确后重新选择！');
						//	$('#customerCode1').combotree('clear');
						//}
					}	
				});
				*/
			}
			//删除组织机构信息
			function deleteoffice(nodeid,nodetext){
				if(nodeid==null){
					alert('请先选择一行数据再进行操作！');
				}else{
					//var index =$('#officeitemdatagird').datagrid('getRowIndex', node);
					confirm('确定框', '确定删除‘'+nodetext+'’机构信息吗?', function(r){
						if (r){
							//$('#officeitemdatagird').datagrid('deleteRow', index);
							//删除用户操作Ajax
							$.ajax({
					   			type: "POST",
							    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=deleteOfficeitem&&date="+new Date(),
							    data: "deleteOfficeitem="+nodeid+"",
							    dataType:'json',
							    success: function(date){
							    	//append();
							    	if(date==null||date.relust){
							    		alert("操作成功");
							    		__getdOe('d');
							    	}else if(date.error){
							    		alert("操作失败、可能有关联数据未清除！错误信息如下："+date.error);
							    	}else{
							    		alert("操作失败、暂无返回信息。");
							    	}
							    	reload();
							    	$('#officeitemdatagird').datagrid("reload");//根据表格ID刷新页面
		   						}
							});
						}
					});
				}
			}
			//重置按钮事件
			function cleardate(s){
				$('#'+s).form('clear');
			}
			//关闭对话框窗口(传入窗口id)
			function closewicket(c){
				$("#"+c).dialog('close');
			}
			//查询按钮事件
			function userselect(dianji){
				//alert('查询');
				if(dianji=='onclick'){//点击查询按钮
					$("#preOfficeUuid2").val("");//把根据上级ID查询的框清空
				}else if(dianji=='tree'){//点击树形菜单
					
				}
				var officeName=$("#officeName").val();
				var abbrev=$("#abbrev").val();
				var contact=$("#contact").val();
				var createTime_s=$("#createTime__start").datebox("getValue");
				var createTime_e=$("#createTime__end").datebox("getValue");
				var preOfficeUuid=$("#preOfficeUuid2").val();
				var condition = $("#__condition").val();
				$("#officeitemdatagird").datagrid('load',{
		                	__officeName:officeName,
		                	__abbrev:abbrev,
		                	__contact:contact,
		                	createTime__start:createTime_s,
		                	createTime__end:createTime_e,
		                	__preOfficeUuid:preOfficeUuid,
		                	__condition:condition
		        });
			}
			function convertArray(arrays) {
				var object = {};
				for ( var key in arrays) {
					if (typeof (object[arrays[key].name]) == 'undefined') {
						object[arrays[key].name] = arrays[key].value;
					} else {
						object[arrays[key].name] += "," + arrays[key].value;
					}
				}
				return object;
			}
						
			//提交表单新增数据库数据和刷新页面
			function saveuser(){
			getButtonKongZhi("d");
				var officeitemjsonResults=$("#OfficeItemForm").serializeArray(); //得到数组json对象
				var saveuerjudge=$("#saveDivButton").attr("name");
				
				var countryCode=$('#countryDesc1').combogrid('getValue');//国家代码
				var countryDesc=$("#countryDesc1").combogrid('getText');
				
				var provinceCode=$('#provinceDesc1').combogrid('getValue');//省市代码
				var provinceDesc=$("#provinceDesc1").combogrid('getText');
				var cityCode=$('#cityDesc1').combogrid('getValue');//城市代码
				var cityDesc=$("#cityDesc1").combogrid('getText');
				//var siteCode=$('#siteDesc1').combogrid('getValue');//城市代码
				//var siteDesc=$("#siteDesc1").combogrid('getText');
				var officeitemjson;
					officeitemjson="{";
					$.each(officeitemjsonResults,function(k,v){
						if(v.name=="countryCode"){v.value=countryCode;};
						if(v.name=="provinceCode"){v.value=provinceCode;};
						if(v.name=="cityCode"){v.value=cityCode;};
						//if(v.name=="siteCode"){v.value=siteCode;};
						if(k==officeitemjsonResults.length-1){
							officeitemjson=officeitemjson+'"'+v.name +'"'+':'+'"'+ v.value+'"';
						}else{
							officeitemjson=officeitemjson+'"'+v.name +'"'+':'+'"'+ v.value+'"'+",";
						}
					});
					
					officeitemjson=officeitemjson+"}";
					if(saveuerjudge=="saveOffice"){
					LogisticsOrderManager.object2base64(officeitemjson,function(officeitemjson1){
						if(officeitemjson1){
						$.ajax({
				   			type: "POST",
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=savaOfficeitem&&date="+new Date(),
						    data: "Officeitemjson="+officeitemjson1+"",
						    dataType:'json',
						    success: function(date){
						    	if(date==null||date.relust){
						    		alert("操作成功");
							    	reload();
							    	$('#OfficeItemForm').form('clear');//重置用户填写窗口的信息
							    	$('#OfficeItem').dialog('close');//关闭用户信息填写窗口
							    	$('#officeitemdatagird').datagrid("reload");//根据表格ID刷新页面
						    	}else if(date.error){
						    		alert("操作失败！错误信息如下："+date.error);
						    		getButtonKongZhi("e");
						    	}else{
						    		alert("操作失败、暂无返回信息。");
						    		getButtonKongZhi("e");
						    	}
		   					}
						});
						}
					});
					}else if(saveuerjudge=="updateOffice"){
					LogisticsOrderManager.object2base64(officeitemjson,function(officeitemjson1){
						if(officeitemjson1){
						$.ajax({
				   			type: "POST",
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=savaOfficeitem&&date="+new Date(),
						    data: "Officeitemjson="+officeitemjson1+"",
						    dataType:'json',
						    success: function(date){
						    	if(date==null||date.relust){
						    		alert("操作成功");
						    		reload();
							    	$('#OfficeItemForm').form('clear');//重置用户填写窗口的信息
							    	$('#OfficeItem').dialog('close');//关闭用户信息填写窗口
							    	$('#officeitemdatagird').datagrid("reload");//根据表格ID刷新页面
						    	}else if(date.error){
						    		alert("操作失败！错误信息如下："+date.error);
						    		getButtonKongZhi("e");
						    	}else{
						    		alert("操作失败、暂无返回信息。");
						    		getButtonKongZhi("e");
						    	}
		   					}
						});
					}
				});
				}
			}
			//验证表单数据
			function provinguser(){
			if(!$('#OfficeItemForm').form('validate')){alert("数据验证错误！请重新填写！");return;}
			saveuser();
			/*
				//$("#saveDivButton").attr('disabled','disabled');
				var officeCode1=$('#officeCode1').val();//组织编码
				var officeName1=$('#officeName1').val();//组织全称
				var preOfficeUuid1=$('#preOfficeUuid1').combobox('getValue');
				var customerCode1=$('#customerCode1').combogrid('getValue');
				debugger;
				//var businessRegisterNo1=$('#businessRegisterNo1').val();//工商登记号
				//var taxRegisterNo1=$('#taxRegisterNo1').val();//税务登记号
				//var xchgrName1=$('#xchgrName1').val();//汇率体系
				//alert(officeCode1+" : "+officeName1+" : "+businessRegisterNo1+" : "+taxRegisterNo1+" : "+xchgrName1);
				if(officeCode1.length>0){
					if(officeName1.length>2){
						   if(preOfficeUuid1){
								if(customerCode1){
										saveuser();
								}else{
									$("#customerCode1").focus();
									alert('*为必填项,请正确填写机构管理信息！'); 
								}
							}else{
									$("#preOfficeUuid1").focus();
									alert('*为必填项,请正确填写机构管理信息！'); 
							}
						*/
						/***if(businessRegisterNo1.length>5){
						  	if(taxRegisterNo1.length>3){
								if(xchgrName1.length>0){
									
								}else{
									$("#xchgrName1").focus();
									alert('汇率体系信息错误、请核对后重新输入！'); 
								}	
							}else{
								$("#taxRegisterNo1").focus();
								alert('税务登记号信息错误、请核对后重新输入！'); 
							}
						}else{
							$("#businessRegisterNo1").focus();
							alert('工商登记号信息错误、请核对后重新输入！'); 
						}
						**/
						/*
					}else{
						$("#officeName1").focus();
						alert('*为必填项,请正确填写机构管理信息！'); 
					}
				}else{
					$("#officeCode1").focus();
					alert('*为必填项,请正确填写机构管理信息！'); 
				}
				*/
			}
			//按钮控制
			function __getdOe(dOe){
				if(dOe!=null&&dOe=="d"){
		    		$("#deleteOfficeitem").linkbutton("disable").unbind("click");
		    	}else if(dOe!=null&&dOe=="e"){
					$("#deleteOfficeitem").linkbutton("enable");
				}
			}
	</script>
		<!-- 组织树形菜单方法区 -->
		<script type="text/javascript">
		<!-- 得到数据 -->
		$(function(){
			$("#office_tree").tree({
				url:"<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectOfficeitemtree1&date="+new Date(),
				onClick:function(node){
				var isoUrl="";
				 if(<%=scue.getUserId()!=null&&scue.getUserId().equals(CommonUtil.SYSTEM_ADMIN)?true:false%>){
					isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType=SysOfficeModel&sort=createTime&order=desc";
					}else{
				     isoUrl="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType=SysOfficeModel&sort=createTime&order=desc&__preOfficeUuid="+node.id
					}
					$('#officeitemdatagird').datagrid({
						url:isoUrl
					});
					var node = $('#office_tree').tree('getSelected');
					$("#preOfficeUuid2").val(node.id);
					userselect('tree');
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					$('#office_tree').tree('select', node.target);
					//$('#mm').menu('show', {
					//	left: e.pageX,
					//	top: e.pageY
					//});
				}
			});
		});
		//取出国家省市和城市代码
		function countryprovincecity(){
			/**
			$("#countryCode1").combobox({//国家代码
				url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.SELECT_CODE_ALL_COUNTRY%>&date='+new Date(),
				valueField:'key',
				textField:'value'
			});
			$("#provinceCode1").combobox({//省市代码
				url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.SELECT_CODE_ALL_PROVINCE%>&date='+new Date(),
				valueField:'key',
				textField:'value'
			});
			$("#cityCode1").combobox({//城市代码
				url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.SELECT_CODE_ALL_CITY%>&date='+new Date(),
				valueField:'key',
				textField:'value'
			});
			*/
			<% 
				String customer = CommonUtil.SELECT_CODE_ALL_COUNTRY;
				String modelName = customer.split(",")[0] + "Model";
			%>
			var customerRow = [{name:'countryCode',value:'国家代码'},{name:'countryName',value:'国家中文名称'},{name:'countryNameEn',value:'国家英文名称'}];
			combogridSetValueAndTest('countryDesc1','provinceDesc1',customerRow,'<%=path%>','<%=modelName%>','countryCode1',false);
			
			<% 
				String customerS = CommonUtil.SELECT_CODE_ALL_PROVINCE;
				String modelNameS = customerS.split(",")[0] + "Model";
			%>
			var customerRow1 = [{name:'provinceCode',value:'省份代码'},{name:'provinceName',value:'省份中文名称'},{name:'provinceNameEn',value:'省份英文名称'}];
			combogridSetValueAndTest('provinceDesc1','cityDesc1',customerRow1,'<%=path%>','<%=modelNameS%>','provinceCode1',false);
			
			<% 
				String customerC = CommonUtil.SELECT_CODE_ALL_CITY;
				String modelNameC = customerC.split(",")[0] + "Model";
			%>
			var customerRow2 = [{name:'cityCode',value:'城市代码'},{name:'cityName',value:'城市中文名称'},{name:'cityNameEn',value:'城市英文名称'}];
			combogridSetValueAndTest('cityDesc1','siteDesc1',customerRow2,'<%=path%>','<%=modelNameC%>','cityCode1',false);
			
			<%
				String customerD = CommonUtil.SELECT_CODE_ALL_SITE;
				String modelNameD = customerD.split(",")[0] + "Model";
			%>
			<%-- var customerRow3 = [{name:'siteCode',value:'地点代码'},{name:'siteName',value:'地点中文名称'},{name:'siteNameEn',value:'地点英文名称'}];
			combogridSetValueAndTest('siteDesc1','preOfficeUuid1',customerRow3,'<%=path%>','<%=modelNameD%>','siteCode1',false); --%>
			
			$("#getCountryCodeIndex").sinotrans_Selector({ //国家
				path:'<%=path%>',
				types:'<%=CommonUtil.SELECT_CODE_ALL_COUNTRY%>',
				key:'countryCode1',
				value:'countryDesc1',
				divId:'<%=UUID.randomUUID().toString()%>'
			});
			
			$("#getProvinceCode1").sinotrans_Selector({ //省份
				path:'<%=path%>',
				types:'<%=CommonUtil.SELECT_CODE_ALL_PROVINCE%>',
				key:'provinceCode1',
				value:'provinceDesc1',
				divId:'<%=UUID.randomUUID().toString()%>'
			});
			
			$("#getCityCode1").sinotrans_Selector({ //城市
				path:'<%=path%>',
				types:'<%=CommonUtil.SELECT_CODE_ALL_CITY%>',
				key:'cityCode1',
				value:'cityDesc1',
				divId:'<%=UUID.randomUUID().toString()%>'
			});
			
			<%-- $("#getSiteCode1").sinotrans_Selector({ //地点
				path:'<%=path%>',
				types:'<%=CommonUtil.SELECT_CODE_ALL_SITE%>',
				key:'siteCode1',
				value:'siteDesc1',
				divId:'<%=UUID.randomUUID().toString()%>'
			}); --%>
			$("#officeType1").combobox({ //组织类型
				url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.OPTION_SYSTEM_CODE%>&q=<%=CommonUtil.OPTION_SYSTEM_CODE_OFFICE_TYPE%>&date='+new Date(),
				valueField:'key',
				textField:'value',
				panelHeight:'auto',
				mode:'remote'	
			});
		}
		//展开树形
		function expand(){
			var node = $('#office_tree').tree('getSelected');
			$('#office_tree').tree('expand',node.target);
		}
		// 关闭树形
		function collapse(){
			var node = $('#office_tree').tree('getSelected');
			$('#office_tree').tree('collapse',node.target);
		}
		//在树形中增加部门机构
		function append(){
			var node = $('#office_tree').tree('getSelected');
			$('#OfficeItem').dialog({
				modal:true
			});
			$("#saveDivButton").attr("name","saveOffice");//修改完成按钮数据为保存
			adduserget();//获取部门机构下拉框数据
			countryprovincecity();
			$("#preOfficeUuid2").val("");//把根据上级ID查询的框清空
			cleardate('OfficeItemForm');//重置表单信息
			$('#preOfficeUuid1').combotree('setValue',node.id);//所属公司
			$('#preOfficeUuid1').combotree('disable');
		}
		//在树形中删除部门机构
		function remove(){
			var node = $('#office_tree').tree('getSelected');
			deleteoffice(node.id,node.text);
		}
		//重载
		function reload(){
			var node = $('#office_tree').tree('getSelected');
			$('#office_tree').tree('reload');
		}
		//检测是否是跟节点（新增）
		function detectionNode(){
			var node = $('#office_tree').tree('getSelected');
			var nsode = $('#office_tree').tree('getParent',node.target);
			if(nsode==null){
				//alert('此节点不可操作！请选择有效节点！！');
				confirm('确定框', '确认为‘'+node.text+'’添加直属部门吗?', function(r){
					if (r){
						append();
					}
				});
			}else{
				append();
			}
		}
		
		function clearCombo(objId){
			$('#'+objId).combogrid('setValue','');
		}
		function setComboValue(objId,value){
			$('#'+objId).combogrid('setValue',value);
		}
		function setComboText(objId,value){
			$('#'+objId).combogrid('setText',value);
		}
		
		function statusOOr(value,rowData,rowIndex){
			if(value=='<%=CommonUtil.Active%>'){
				return "有效";
			}else if(value=='<%=CommonUtil.Cancel%>'){
				return "作废";
			}else if(value=='<%=CommonUtil.Pending%>'){
				return "草稿";
			}else{
				return "---";
			}
		}
		//取出组织类型数据保存到全局变量officeTypeWholeArray的数组中。
		function officeTypeWholeArrayManger(){
			if(officeTypeWholeArray!=null&&officeTypeWholeArray.length==0){
				$.ajax({
		   			type: "POST",
				    url:'<%=path%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.OPTION_SYSTEM_CODE%>&q=<%=CommonUtil.OPTION_SYSTEM_CODE_OFFICE_TYPE%>&date='+new Date(),
				    dataType:'json',
				    success: function(date){
				    	$.each(date,function(num,officeTypeModel){
				 			officeTypeWholeArray.push(officeTypeModel); //把取出来的（key、value）
				 		});
				   	}
				});
			}
		}
		function officeTypeHuoQu(value,rowData,rowIndex){
			$.each(officeTypeWholeArray,function(uuid,OfficeCodeModel){
  				if(OfficeCodeModel.key==value){
  					value=OfficeCodeModel.value;
  				}
  			});
  			return value;
		}
		function ceshi(){
			officeTypeWholeArrayManger();
			setTimeout(function(){
				alert(officeTypeWholeArray);
			},300);
		}
	//去左空格;   
	 function ltrim(s){     
	    return s.replace(/(^\s*)/, "");  
	 }   
	 //去右空格;   
	function rtrim(s){   
	  return s.replace(/(\s*$)/, "");  
	}   
	 //去左右空格;   
	 function trim(s){  
	  return rtrim(ltrim(s));   
	 }
		////调用DWR验证userCode是否重复以及转换大写
		function getYanZhenUserCodeFunction(textid,valueUser){
			valueUser=trim(valueUser.toUpperCase());//转换大写并且去掉前后空格
			$("#"+textid).val(valueUser);
			if(/[^\x00-\xff]/g.test(valueUser)){
			 	alert( "含有汉字不能保存");
			 	$("#"+textid).val(null);
			}else {
				if(valueUser!=null&&valueUser!=""){
					SysOfficeManager.getYanZhenUserCode(valueUser,function(boole){
						if(boole){
							$("#"+textid).val(null);
							alert("该编码已存在、请审核后重新填写！");
						}
					});
				}
			}
		}
		function getButtonKongZhi(dOe){
				if(dOe!=null&&dOe=="e"){
				$("#saveDivButton").linkbutton("enable").unbind("click").bind("click");
				$("#closeDivButton").linkbutton("enable").unbind("click").bind("click");
				}else{
				$("#saveDivButton").linkbutton("disable").unbind("click");
				$("#closeDivButton").linkbutton("disable").unbind("click");
				}
	}
	function getYanZhenUserEn(textid,valueUser){
			valueUser=trim(valueUser);//转换大写并且去掉前后空格
			$("#"+textid).val(valueUser);
			if(/[^\x00-\xff]/g.test(valueUser)){
			 	alert( "含有汉字不能保存");
			 	$("#"+textid).val(null);
			}
		}
	</script>
		<div region="center" title="机构管理" style="overflow: hidden;">
			<div class="easyui-layout" fit="true">
			<div id="OfficeItemdatagird" region="north" border="false">
				<form id="OfficreItemqueryForm">
					<div style="background: #efefef; width: 100%; float: left">
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-search" style="float: left" onclick="userselect('onclick')">查询</a>
						
						<div class="datagrid-btn-separator"></div>
						<!-- 这是分割线 -->
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="myCustomerIcon_clear" style="float: left"
							onclick="cleardate('OfficreItemqueryForm')">重置</a>
					</div>
					<!-- 隐藏信息 -->
					<input type="hidden" id="__condition" name="__condition"
						value="officeName_ilikeAnywhere:abbrev_=:contact_=:preOfficeUuid_ilikeAnywhere:createTime_between" />
					<input type="hidden" id="preOfficeUuid2" name="preOfficeUuid"/>
					<!-- legend>表单验证</legend -->
					<table id="tableQueryForm" style="width: 95%; float: left">
						<tr align="right">
							<td width="15%" align="right">
								<label>
									组织全称:
								</label>
							</td>
							<td width="35%" align="left">
								<input id="officeName" name="officeName" size="25"
									class="easyui-validatebox" />
							</td>
							<td width="15%" align="right">
								<label>
									创建时间:
								</label>
							</td>
							<td width="35%" align="left">
								<input id="createTime__start" name="createTime__start"
									class="easyui-datebox easyui-validatebox" validType="dateFormat" style="width: 80px;" />
								<img src="<%=path%>/images/right.gif" />
								<input id="createTime__end" name="createTime__end"
									class="easyui-datebox easyui-validatebox" validType="dateFormat" style="width: 80px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
				<div region="center" title="机构管理" iconCls="icon-edit" >
					<table id="officeitemdatagird" style="width: auto;" singleSelect="true" idField="itemid" fit="true"> </table>
				</div>
			</div>
		</div>
		<!-- 弹出窗口 -->
		<div id="OfficeItem"
			style="padding: 5px; width: 770px; height: 400px;" title="机构管理">
			<form name="OfficeItemForm" id="OfficeItemForm" class="easyui-form">
				<div>
					<table id="tableQueryForm" style="width: auto;">
						<!-- 分割线 -->
						<tr>
							<td colspan="6">
								<hr>
								<input type="hidden" id="officeUuid1" name="officeUuid" />
								<!-- 隐藏重要属性 -->
								<input type="hidden" id="recVer1" name="recVer" />
							</td>
						</tr>
						<tr>
							<td align="right"> <font color="red">*&nbsp;</font>组织编码: </td>
							<td> <input type="text" id="officeCode1" name="officeCode" class="easyui-validatebox" required="true" style="TEXT-TRANSFORM:uppercase;ime-mode:disabled"  onchange="getYanZhenUserCodeFunction(this.id,this.value)"/> </td>
							<td align="right"> <font color="red">*&nbsp;</font>组织全称: </td>
							<td> <input type="text" id="officeName1" name="officeName" class="easyui-validatebox" required="true"></input> </td>
							<td align="right"> 组织类型: </td>
							<td> <select style="width: 130px;" class="easyui-combobox" id="officeType1" name="officeType" editable="false"> </select> </td>
						</tr>
						<tr>
							<td align="right"> 组织英文名: </td>
							<td> <input type="text" id="officeNameEn1" name="officeNameEn" style="ime-mode:disabled" onchange="getYanZhenUserEn(this.id,this.value)" /> </td>
							<td align="right"> 组织简称: </td>
							<td> <input type="text" id="abbrev1" name="abbrev" /> </td>
							<td align="right"> 语言: </td>
							<td> <select style="width: 130px;"  panelHeight="auto" class="easyui-combobox"  id="language1" name="language"> 
									<option value="CN">中文</option>
									<option value="EN">English</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">工商登记号: </td>
							<td> <input type="text" id="businessRegisterNo1" name="businessRegisterNo" class="easyui-validatebox"/> </td>
							<td align="right"> 作为客户: </td>
							<td> <select id="isCustomer1" class="easyui-combobox" panelHeight="auto" name="isCustomer" style="width: 151px;">
									<option value="1">1</option>
									<option value="2">2</option>
								</select>
							</td>
							<td align="right"> 状态: </td>
							<td id="" name=""> <select id="status1"  class="easyui-combobox" panelHeight="auto"  name="status" style="width: 130px;" editable="false">
									<option value="<%=CommonUtil.Active%>" selected="selected">有效</option>
									<option value="<%=CommonUtil.Pending%>" >草稿</option>
									<option value="<%=CommonUtil.Cancel%>">作废</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">税务登记号: </td>
							<td> <input type="text" id="taxRegisterNo1" name="taxRegisterNo" class="easyui-validatebox" /> </td>
							<td align="right"><font color="red">*&nbsp;</font>为内部办事处: </td>
							<td> <select id="isInternal1" class="easyui-combobox"  panelHeight="auto"  name="isInternal" style="width: 151px;" required="true">
									<option value="0">外部公司</option>
									<option value="1">内部码头</option>
									<option value="2">公共客户</option>
									<option value="9">文件中心</option>
								</select>
							</td>
							<td align="right">联系人: </td>
							<td> <select style="width: 130px;"   panelHeight="auto"  class="easyui-combobox"  id="contact1" name="contact">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">汇率体系: </td>
							<td> <input type="text" id="xchgrName1" name="xchgrName" class="easyui-validatebox" /> </td>
							<td align="right">作为结算公司: </td>
							<td> <select id="isDept1"  class="easyui-combobox" panelHeight="auto" name="isDept" style="width: 151px;">
									<option value="1">1</option>
									<option value="2">2</option>
								</select>
							</td>
							<td align="right">邮件地址: </td>
							<td> <input type="text" class="easyui-validatebox" validType="email" id="email1" name="email" /> </td>
						</tr>
						<!-- 分割线 -->
						<tr> <td colspan="6"> <hr> </td> </tr>
						<tr>
							<td align="right"> 国家: </td>
							<td> <input id="countryCode1" name="countryCode" class="easyui-validatebox" type="hidden" />
								<input id="countryDesc1" combo="true" class="easyui-validatebox" style="width: 130px;"/>
								<img src="<%=path%>/images/search.png" class="shouShi" id="getCountryCodeIndex"/>
							</td>
							<td align="right"> 省份: </td>
							<td> <input id="provinceCode1" name="provinceCode" class="easyui-validatebox" type="hidden" />
								<input id="provinceDesc1" combo="true" class="easyui-validatebox" style="width: 130px;"/>
								<img src="<%=path%>/images/search.png" class="shouShi" id="getProvinceCode1"/>
							</td>
							<td align="right">
								城市:
							</td>
							<td> <input id="cityCode1" name="cityCode" class="easyui-validatebox" type="hidden" />
								<input id="cityDesc1" combo="true" class="easyui-validatebox" style="width: 130px;"/>
								<img src="<%=path%>/images/search.png" class="shouShi" id="getCityCode1"/>
							</td>
						</tr>
						<tr>
							<td align="right"> 地点: </td>
							<td> 
								<input id="siteCode1" name="siteCode" class="easyui-validatebox" type="hidden" />
								<input id="siteDesc1" combo="true" class="easyui-validatebox" style="width: 130px;"/>
								<img src="<%=path%>/images/search.png" class="shouShi" id="getSiteCode1"/>
							</td>
							<td align="right"><font color="red">*&nbsp;</font> 上级组织: </td>
							<td> <select id="preOfficeUuid1"   panelHeight="150" required="true"  name="preOfficeUuid"
									style="width: 155px;"  validType="preOfficeValidate">
								</select>
							</td>
							<td align="right"><font color="red">*&nbsp;</font> 所属公司: </td>
							<td> <select id="customerCode1" name="customerCode" required="true" style="width: 155px;"> </select> </td>
						</tr>
						<tr>
							<td align="right"> 电话: </td>
							<td> <input type="text" id="tel1" name="tel"></input> </td>
							<td align="right"> 传真: </td>
							<td> <input type="text" id="fax1" name="fax" /> </td>
							<td align="right"> 作废日期: </td>
							<td> <input class="easyui-datebox" style="width: 154px;" id="cancelDate1" name="cancelDate" /> </td>
						</tr>
						<tr>
							<td align="right"> 地址: </td>
							<td colspan="5"> <input type="text" size="90" id="address1" name="address" /> </td>
						</tr>
						<tr>
							<td align="right"> 备注: </td>
							<td colspan="5"> <input type="text" size="90" id="remark1" name="remark" /> </td>
						</tr>
						<!-- 分割线 -->
						<tr>
							<td colspan="6">
								<hr>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<a href="#" id="saveDivButton" class="easyui-linkbutton" name="saveDivButton"
									onclick="provinguser()">保存</a>
								<!-- <a href="#" class="easyui-linkbutton"
									onclick="cleardate('OfficeItemForm')">清空</a> -->
								<a href="#" class="easyui-linkbutton" id="closeDivButton"
									onclick="closewicket('OfficeItem')">关闭</a>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
		<!-- 树形左侧菜单 -->
		<div region="west" title="组织树形显示" style="width: 220px; padding: 10px;">
			<ul id="office_tree" class="easyui-tree">
			</ul>
		</div>
		<!-- 鼠标右键增删 -->
		<div id="mm" class="easyui-menu" style="width: 120px;">
			<div onclick="detectionNode()" iconCls="icon-add">
				新增
			</div>
			<div onclick="remove()" iconCls="icon-remove">
				删除
			</div>
			<div onclick="updateuser()" iconCls="icon_toolbar_edit">
				编辑
			</div>
			<div onclick="reload()" iconCls="icon-reload">
				重载
			</div>
			<div class="menu-sep"></div>
			<div onclick="expand()">
				展开
			</div>
			<div onclick="collapse()">
				关闭
			</div>
		</div>
	</body>
</html>