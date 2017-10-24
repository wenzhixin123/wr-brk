<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%
	String path = request.getContextPath();
SessionContextUserEntity scue = SessionContextUserEntity.currentUser(); 
%>
<html:html>
<head>
	<!-- jquery easyui -->
	<jsp:include page="../../common/imp_dwr.jsp"></jsp:include>
	<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
	<style type="text/css">
#tableQueryForm td {
	font-size: 12px;
}
</style>
	<!--  用户管理页面 2012年2月22日 22:39:31-->
<script type="text/javascript">
		function gongsi(){
			<%
		String agentConsigneeDesc = CommonUtil.SELECT_CODE_ALL_CUSTOMER;
		String agentConsigneeDescmodelName = agentConsigneeDesc.split(",")[0] + "Model";
		%>
		var customerRow = [{name:'customerCode',value:'客户代码'},{name:'customerName',value:'中文名称'},{name:'customerNameEn',value:'客户名称'}];
		
		combogridSetValueAndTest('customerCode1','orderNo',customerRow,'<%=path%>','<%=agentConsigneeDescmodelName%>','',false);
		
			/**$('#customerCode1').combogrid({ //应急发货单号初始化  getUrgentOrderNoDatagrive
				panelWidth:450,
				idField:'customerCode',
				textField:'officeName',
				rownumbers:true,
				pagination:true,
				pageSize:5,
				pageList:[5,10,20],
				url:"<%=path%>/servlet/SinotransServlet?cmd=selectCompany&date="+new Date(),
				columns:[[
					{field:'customerCode',title:'公司编码',width:100},
					{field:'officeCode',title:'组织编码',width:100},
					{field:'officeName',title:'组织名称',width:60},
					{field:'officeNameEN',title:'组织英文名称',width:100},
					{field:'remark',title:'备注',width:120}
				]]
			});*/
		}
		var panDuanKongZhi=null;
		$(function(){
			gongsi();//公司查询
			$(".panel-tool-collapse").remove();
			$(".tabs-panels").remove();
			if(panDuanKongZhi==null || panDuanKongZhi==""){
				adduserget(); //初始化数据 取出所有公司组织 和部门
			}
			dateKongZhi(); //控制手机号码传呼等数据格式事件添加
			clearData(); //重置按钮信息为保存状态
			$("#idCard1").unbind("blur").blur(function(){
				var $val=$(this).val();
				if(bol($val)==1){
					$(this).val("");
				}
			}).unbind("change").change(function(){
				var $val=$(this).val();
				if(bol($val)==1){
					$(this).val("");
				}
			});
		});
		
		function bol(){
			var type=0;
			var _this=arguments[0];
			var reGen=/^\d{15}(\d{2}[A-Za-z0-9])?$/g;
			if(!reGen.test(_this)){
				type=1;
			}
			return type;
		}
</script>
	<script type="text/javascript">
	//绑定时间等的控制事件
	function dateGongYong(inputId){
		$("#"+inputId).unbind("blur").blur(function(){
			var _this=$(this).val();
			if(_this){
				var reg=/^[-\+\(\)\d]+$/;
				var mobileReg=/^([0]?13\d{9}|15\d{9})$/;
				if(!reg.test(_this) && !mobileReg.test(_this)) {
					$(this).val(""); 
				};
			}
		});
	}
	function dateKongZhi(){
		dateGongYong("mobile1");//验证移动电话
		dateGongYong("bpcall1");//验证传呼
		dateGongYong("telephone1");//验证联系电话
		dateGongYong("telephone2");//验证联系电话2
		dateGongYong("zipCode1");//验证邮编
		dateGongYong("homeTelphone1");//验证家庭电话
		//dateGongYong("idCard1");//验证身份证
	}
	//取出所有公司组织 和部门
	function adduserget(){
		if(panDuanKongZhi==null || panDuanKongZhi==""){
			$("#officeCode1").combotree({//公司
				url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectOfficeitemtree1&date='+new Date(),
				checkbox:true,
				panelHeight:'auto',
				onClick:function(node){
				//	var isleaf=$('.tree').tree('isLeaf',node.target);
				//	if(!isleaf){
				//		alert('此节点不是菜单节点、请明确后重新选择！');
				//		$('#officeCode1').combotree('clear');
				//	}
				}	
			});
		
	//		$("#departmentCode1").combotree({//部门
	//			url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectOfficeitemtree1&date='+new Date(),
	//			checkbox:true,
	//			panelHeight:'auto',
	//			onClick:function(node){
					//alert($.toJSON(node));
	//			}	
	//		});
			$("#userType1").combobox({ //用户类型
				url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.OPTION_SYSTEM_CODE%>&q=<%=CommonUtil.OPTION_SYSTEM_CODE_USER_TYPE%>&date='+new Date(),
				valueField:'key',
				textField:'value',
				panelHeight:'auto',
				mode:'remote'
			});
			$('#preUserUuid1').combobox({ //上级用户
				url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.OPTION_USER%>&date='+new Date(),
				valueField:'key',
				textField:'value',
				panelHeight:'auto',
				mode:'remote'
			});
			if(panDuanKongZhi==null || panDuanKongZhi==""){
				panDuanKongZhi="OK";
			}
		}
	}
	//关闭按钮事件
	function closeuserInfo(){
		$("#userCode1").attr("readonly",false);
		parent.$('#userInfo').window('close');
	}
	//提交表单新增数据库数据和刷新页面
	function saveDivButton(){
		var pass1=$('#password1').val();
		var pass2=$('#password2').val();
		if(pass1==pass2&pass1.length==pass2.length&pass1.length>=6){
				var userjsonResults=$("#userInfoForm").serializeArray(); //得到数组json对象
				var saveuerjudge=$("#saveDivButton").attr("name");//取出相应的按钮事件
				var preUserUuid=$('#preUserUuid1').combobox('getText');//上级领导
				var userjson;
					userjson="{";
					$.each(userjsonResults,function(k,v){
						if(v.name=='preUserUuid'){v.value=preUserUuid;};
						if(v.name=='password'&&v.value=='20120215174514'){
							//在这里判断。如果密码为20120215174514说明是修改。则略过密码获取其他属性
						}else{
							if(k==userjsonResults.length-1){
								userjson=userjson+'"'+v.name +'"'+':'+'"'+ v.value+'"';
							}else{
								userjson=userjson+'"'+v.name +'"'+':'+'"'+ v.value+'"'+",";
							}
						}
					});
					userjson=userjson+"}";
					if(saveuerjudge=="saveDivButton"){
					LogisticsOrderManager.object2base64(userjson,function(userjson1){
						if(userjson1){
						//alert('进入保存');
						//使用ajax提交到服务器保存数据
						$.ajax({
				   			type: "POST",
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=savaUser&date="+new Date(),
						    data: "userjson="+userjson1+"",
						    dataType:'json',
						    success: function(date){
						    	if(date==null||date.relust){
						    		alert("操作成功");
							    	clearData();//重置用户填写窗口的信息
							    	parent.$('#userInfo').window('close');//关闭用户信息填写窗口
							    	parent.$('#userdatagird').datagrid("reload");//根据表格ID刷新页面
						    	}else if(date.error){
						    		alert("操作失败！错误信息如下:"+date.error);
						    		getButtonKongZhi("e");
						    	}else{
						    		alert("操作失败。暂无返回信息。");
						    		getButtonKongZhi("e");
						    	}
		   					}
						});
						}
					});
					}else if(saveuerjudge=="updateUser"){
					LogisticsOrderManager.object2base64(userjson,function(userjson1){
						if(userjson1){
						//使用ajax提交到服务器保存数据
						$.ajax({
				   			type: "POST",
						    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=updateUser&date="+new Date(),
						    data: "userjson="+userjson1+"",
						    dataType:'json',
						    success: function(date){
						    	if(date==null||date.relust){
						    		alert("操作成功");
						    		clearData();//重置用户填写窗口的信息
						    		$("#userCode1").attr("readonly",false);
							    	parent.$('#userInfo').window('close');//关闭用户信息填写窗口
							    	parent.$('#userdatagird').datagrid("reload");//根据表格ID刷新页面
						    	}else if(date.error){
						    		alert("操作失败！错误信息如下:"+date.error);
						    		getButtonKongZhi("e");
						    	}else{
						    		alert("操作失败。暂无返回信息。");
						    		getButtonKongZhi("e");
						    	}
		   					}
						});
						}
					});
					}
		}else if(pass1.length<6){
			alert('密码长度不足、请重新输入！'); 
			getButtonKongZhi("e");
			$('#password1').focus();
		}else{
			$('#password1').val("");
			$('#password2').val("");
			$('#password1').focus();
			alert('两次密码输入不一致、请重新输入！'); 
			getButtonKongZhi("e");
		}
	}
	function editUser(userUuid){
		adduserget(); //获取下拉框 取出所有公司组织 和部门
		clearData(); ////重置按钮信息为保存状态
		if(userUuid!=undefined && userUuid!=null && userUuid!=""){
			$.ajax({
	   			type: "POST",
			    url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=selectUserid&date="+new Date(),
			    data: "selectuserid="+userUuid+"",
			    dataType:'json',
			    success: function(data){
			    	var userjson=data;
			    	$("#recVer1").val(userjson.recVer);
			    	$("#userUuid1").val(userjson.userUuid);
			    	$("#userCode1").val(userjson.userCode);
			    	$("#userName1").val(userjson.userName);
			    	$("#lastName1").val(userjson.lastName);
			    	$("#middleName1").val(userjson.middleName);
			    	$("#firstName1").val(userjson.firstName);
			    	$("#nativeAddress1").val(userjson.nativeAddress);
			    	$("#userNameEn1").val(userjson.userNameEn);
			    	$('#sex1').combobox('select',userjson.sex);//性别
			    	$("#password1").val("20120215174514");//由于时间不能做任何修改。所以再次给予赋值。此时的字符串为更改是的系统时间2012年2月15日 17:45:14
			    	$("#password2").val("20120215174514");
			    	$("#password3").val("20120215174514");
			    	$("#password4").val("20120215174514");
			    	$('#marital1').combobox('select',userjson.marital);//婚姻状况
			    	$("#mobile1").val(userjson.mobile);
			    	$("#bpcall1").val(userjson.bpcall);
			    	$("#hireDate1").datebox("setValue",userjson.hireDate);
			    	$('#education1').combobox('select',userjson.education);//教育程度
			    	$("#telephone1").val(userjson.telephone1);
			    	$("#telephone2").val(userjson.telephone2);
			    	$("#birthDay1").datebox("setValue",userjson.birthDay);//生日
		//	    	$('#customerCode1').combogrid('setValue',userjson.customerCode);//所属公司
			    	$("#fax1").val(userjson.fax);
			    	$("#email1").val(userjson.email);
			    	$("#idCard1").val(userjson.idCard);
			    	$('#userType1').combobox('select',userjson.userType);//用户类型
			    	
			    	//alert(userjson.userType+" : "+$('#userType1').combobox('getValue');
			    	
			    	$('#preUserUuid1').combobox('select',userjson.preUserUuid);//上级领导
			    	$("#position1").val(userjson.position);
			    	$("#homeTelphone1").val(userjson.homeTelphone);
			    	SysOfficeManager.getOfficeOofficeCodeModel(userjson.officeCode,function(data){
			    		if(data){
			    			$('#officeCode1').combotree('setValue',data);//所属机构
			    		}
			    	});
			    	
			    	
			    	$('#defaultLang1').combobox('setValue',userjson.defaultLang);//缺省语言
			    	$("#zipCode1").val(userjson.zipCode);
			    	$("#homeAddress1").val(userjson.homeAddress);
			    	$("#remark1").val(userjson.remark);
			    	$("#personalInfo1").val(userjson.personalInfo);
			    	if(userjson.customerCode!=null&&userjson.customerCode!=""){
							BasQueryUtilManager.getPropertyByCode({modelName:"BasCustomer",keyProperty:"customerCode",valueProperty:"customerName",keyValue:userjson.customerCode},
							function(rs){
								$('#customerCode1').combogrid('setValue',userjson.customerCode);//公司名称
								$('#customerCode1').combogrid('setText',rs);
		           			});
				    	}
			    	$("#password1").attr("style","display:none;");
					$("#password2").attr("style","display:none;");
					$("#password3").attr("style","display:inline;");
					$("#password4").attr("style","display:inline;");
					$("#status1").combobox("select",userjson.status);
					if(userjson.status!=null&&userjson.status!="" && userjson.status=='<%=CommonUtil.Active%>'||userjson.status=='<%=CommonUtil.Cancel%>'){
			    		$("#userCode1").attr("readonly","readonly");
			    	}else{
			    		$("#userCode1").attr("readonly",false);
			    	}
					getButtonKongZhi("e");
			    	$("#saveDivButton").attr("name","updateUser");//修改完成按钮数据为修改
					}
			});
		}
	}
	//该方法用于验证用户名、身份证和用户编码是否输入或者是否满足条件
	function provinguser(){
		getButtonKongZhi("d");
		var validateable;
		var customerCode = $("#customerCode1").combogrid("getValue");
		//验证表单是否通过
		validateable=$('#logisticsOrderForm').form('validate'); //控件验证信息
		if(validateable==false||customerCode==null||customerCode==""){
			/*if(customerCode==null||customerCode==""){
				$("#customerCode1").addClass("validatebox-invalid");
			}*/
			alert("*必填数据为空！请审核后重新操作！");
			getButtonKongZhi("e");
			return;
		}else{
			var officeUUID=$("#officeCode1").combotree("getValue");
			DWREngine.setAsync(false);
			SysOfficeManager.get(officeUUID,function(officeModel){
				if(officeModel.officeCode=='<%=scue.getOfficeCode()%>'){
					saveDivButton();
				}else{
					confirm('确认框','用户组织机构已更改.是否确认分配该组织机构?', function(r){
						if(r){
							saveDivButton();
						}else{
							getButtonKongZhi("e");
						}
					});
				}
			});
			DWREngine.setAsync(true);
		}
		/**var userCode,idCard,userName,hireDate;
		hireDate=$("#hireDate1").datebox("getValue");
		userCode=$('#userCode1').val();
		idCard=$('#idCard1').val();
		userName=$('#userName1').val();
		var saveuerjudge=$("#saveDivButton").attr("name");
		if(userName.length>1){
			if(userCode.length>=6){
				saveDivButton();
			}else{
				alert('请输入至少6位以上用户编码、以免重复！ 注:该编码为登录帐号！'); 
				$("#userCode").focus();
			}
			/**if(hireDate.length==10){
				if(idCard.length>15){
					if(idCard.length==17){
						alert('用户身份证号码输入错误、请确认后重新输入！'); 
						$("#idCard").focus();
					}else{
						
					}
				}else{
					alert('用户身份证号码输入错误、请确认后重新输入！'); 
					$("#idCard1").focus();
				}
			}else{
				alert('雇佣日期有误、请审核后重新操作！');
			}
		}else{
			alert('请输入正确的用户名称！确保信息准确！'); 
			 $("#userName1").focus();
		}**/
	}
	//重置按钮事件
	function clearData(){
		$('#marital1').combobox({"editable":false});
		$("#sex1").combobox({"editable":false});
		$("#education1").combobox({"editable":false});
		$("#defaultLang1").combobox({"editable":false});
		$("#userCode1").attr("readonly",false);
		$("#userInfoForm").form('clear');
		$("#password1").attr("style","display:inline;");
		$("#password2").attr("style","display:inline;");
		$("#password3").attr("style","display:none;");
		$("#password4").attr("style","display:none;");
		$("#status1").combobox("select","<%=CommonUtil.Active%>");
		$("#sex1").combobox("select","M");
		$("#marital1").combobox("select","0");
		$("#education1").combobox("select","本科");
		$("#defaultLang1").combobox("select","CN");
    	$("#saveDivButton").attr("name","saveDivButton");//修改完成按钮数据为修改
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
					SysUserManager.getYanZhenUserCode(valueUser,function(boole){
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
			valueUser=trim(valueUser);//转换去掉前后空格
			$("#"+textid).val(valueUser);
			if(/[^\x00-\xff]/g.test(valueUser)){
			 	alert( "含有汉字不能保存");
			 	$("#"+textid).val(null);
			}
		}
</script>
</head>
<body>
		<p id="results">
			<b></b>
		</p>
		<form name="userInfoForm" id="userInfoForm">
			<div>
				<table id="tableQueryForm" style="width:auto;">
					<tr>
				  <!-- <td align="right"  rowspan="2" width="100px"> 个人头像: </td>
						<td align="left" rowspan="2">
							<input type="hidden" id="userUuid1" name="userUuid" />
							<input type="hidden" id="recVer1" name="recVer" />
							<label>
								<textarea rows="2" cols="4" disabled="disabled" readonly="readonly" id="personalPic" name="personalPic"></textarea>
								<br/>
							</label>
						</td>
						
						 -->
						<td align="right">
						<input type="hidden" id="userUuid1" name="userUuid" />
							<input type="hidden" id="recVer1" name="recVer" />
						<font color="red">*&nbsp;</font>用户编码:</td>
						<td align="left"> <input type="text" missingMessage="请输入至少5位以上用户编码、以免重复！注:该编码为登录帐号！" validType="length[5,20]" size="10" id="userCode1" name="userCode" class="easyui-validatebox" required="true" onchange="getYanZhenUserCodeFunction(this.id,this.value)" style="TEXT-TRANSFORM:uppercase;ime-mode:disabled"/> </td><!--  onkeyup="this.value=this.value.toUpperCase()" -->
						<td align="right"><font color="red">*&nbsp;</font>密码:</td>
						<td align="left">
							<input type="password" size="15" id="password1" name="password" class="easyui-validatebox" required="true" validType="length[6,20]" />
							<input type="password" size="15" id="password3" class="easyui-validatebox" disabled="disabled" style="display: none;"/>
						</td>
						<td align="right"><font color="red">*&nbsp;</font>重复密码:</td>
						<td align="left">
							<input type="password" size="15" id="password2" class="easyui-validatebox" required="true"  validType="length[6,20]"/>
							<input type="password" size="15" id="password4" disabled="disabled" class="easyui-validatebox" style="display: none;"/>
						</td>
						<td align="right">上级用户:</td>	
						<td align="left"> <input id="preUserUuid1" name="preUserUuid" style="width: 80px"/> </td>
					</tr>
					<tr>
						<td align="right"><font color="red">*&nbsp;</font>用户名:</td>
						<td align="left"><input type="text" align="right" size="10" id="userName1" name="userName" class="easyui-validatebox" required="true" /></td>
						<td align="right">本地名字:</td>
						<td align="left"><input type="text" size="15" id="nativeAddress1" name="nativeAddress" /> </td>
						<td align="right">用户名(英文):</td>
						<td align="left"><input type="text" align="right" size="15" id="userNameEn1" name="userNameEn" style="ime-mode:disabled" onchange="getYanZhenUserEn(this.id,this.value)"/> </td>
						<td align="right">状态:</td>
						<td align="left">
								<select id="status1" name="status" class="easyui-combobox "  panelHeight="auto"  style="width: 60px;" editable="false">
									<option value="<%=CommonUtil.Active%>" selected="selected">有效</option>
									<option value="<%=CommonUtil.Pending%>">草稿</option>
									<option value="<%=CommonUtil.Cancel%>">作废</option>
								</select></td>
					</tr>
					<!-- 分割线 -->
					<tr>
						<td colspan="8">
							<hr/>
						</td>
					</tr>
					<tr>
						<td align="right">性别:</td>
						<td align="left">
							<select id="sex1" class="easyui-combobox" panelHeight="auto" name="sex" style="width: 80px">
								<option value="M" >男</option>
								<option value="W">女</option>
							</select>
						</td>
						<td align="right"> 姓:</td>
						<td align="left"><input type="text" align="right" size="10" id="lastName1" name="lastName" /></td>
						<td align="right"> 中间名字: </td>
						<td align="left"> <input type="text" size="15" id="middleName1" name="middleName" /> </td>
						<td align="right"> 名字: </td>
						<td align="left"> <input type="text" align="right" size="15" id="firstName1" name="firstName" /> </td>
					</tr>
					<tr>
						<td align="right">婚姻状况:</td>
						<td align="left">
							<select id="marital1" panelHeight="auto"  class="easyui-combobox"  name="marital" style="width: 80px" title="ss">
								<option value="0"  >未婚</option>
								<option value="1">已婚</option>
							</select>
						</td>
						<td align="right">移动电话:</td>
						<td align="left"> <input type="text" maxlength="11" size="10" id="mobile1" name="mobile" /> </td>
						<td align="right">传呼:</td>
						<td align="left"> <input type="text" size="15" id="bpcall1" name="bpcall" /> </td>
						<td align="right">雇佣日期:</td>
						<td align="left"> <input class="easyui-datebox easyui-validatebox" validType="dateFormat" id="hireDate1" name="hireDate" style="width: 117px;" /></td>
					</tr>
					<tr>
						<td align="right">教育程度:</td>
						<td align="left">
							<select id="education1" class="easyui-combobox"  name="education" style="width: 80px">
								<option value="小学">小学</option>
								<option value="初中">初中</option>
								<option value="高中">高中</option>
								<option value="中技">中技</option>
								<option value="中专">中专</option>
								<option value="大专">大专</option>
								<option value="专科">专科</option>
								<option value="本科" >本科</option>
								<option value="硕士">硕士</option>
								<option value="博士">博士</option>
								<option value="留学生">留学生</option>
								<option value="其他">其他</option>
							</select>
						</td>
						<td align="right">联系电话:</td>
						<td align="left"> <input type="text" size="10" id="telephone1" name="telephone1" /> </td>
						<td align="right">联系电话2:</td>
						<td align="left"> <input type="text" size="15" id="telephone2" name="telephone2" /> </td>
						<td align="right">生日:</td>
						<td align="left"> <input id="birthDay1" name="birthDay" class="easyui-datebox easyui-validatebox" validType="dateFormat" style="width: 117px;"></input> </td>
					</tr>
					<!-- 分割线 -->
					<tr>
						<td colspan="8">
							<hr/>
						</td>
					</tr>
					<tr>
						<td align="right">缺省语言:</td>
						<td align="left">
							<select id="defaultLang1" class="easyui-combobox"  panelHeight="auto"  name="defaultLang" style="width: 80px">
								<option value="CN">中文</option>
								<option value="EN">English</option>
							</select>
						</td>
						<td align="right">传真:</td>
						<td align="left"> <input type="text" size="10" id="fax1" name="fax" /> </td>
						<td align="right"><font color="red">*&nbsp;</font>电子邮件:</td>
						<td align="left"> <input class="easyui-validatebox" validType="email" required="true" size="15" id="email1" name="email" /> </td>
						<td align="right">邮编:</td>
						<td align="left"> <input type="text" id="zipCode1" name="zipCode" maxlength="6" size="15" /> </td>
					</tr>
					<tr>
						<td align="right">用户类型:</td>
						<td align="left"> <input id="userType1" name="userType" style="width: 80px"/> </td>
						<td align="right">职位描述:</td>
						<td align="left"> <input type="text" align="left" size="10" id="position1" name="position" /> </td>
						<td align="right">家庭电话:</td>
						<td align="left"> <input type="text" size="15" id="homeTelphone1" name="homeTelphone" /> </td>
						<td align="right">身份证号:</td>
						<td align="left"> <input type="text" maxlength="18" size="18" id="idCard1" name="idCard" size="15" /> </td>
					</tr>
					<tr>
						
						<td align="right"></td>
						<td align="left"> </td>
						<td align="right"></td>
						<td align="left"> </td>
					</tr>
					<tr>
						<td align="right"><font color="red">*&nbsp;</font>所属机构:</td>
						<td align="left" colspan="3">
							<select id="officeCode1" name="officeCode" style="width: 230px;"  required="true"></select>
						</td>
						<td align="right"><font color="red">*&nbsp;</font>所属公司:</td>
						<td align="left" colspan="3">
							<!-- <select id="customerCode1" name="customerCode" style="width: 270px;">
							</select> -->
							<input id="customerCode1" name="customerCode" combo="true" style="width: 153px;" />
						</td>
					</tr>
					<!-- 分割线 -->
					<tr>
						<td colspan="8">
							<hr/>
						</td>
					</tr>
					<tr>
						<td align="right"> 联系地址: </td>
						<td align="left" colspan="7"> <input type="text" size="80" id="homeAddress1" name="homeAddress" /> </td>
					</tr>
					<tr>
						<td align="right"> 备注: </td>
						<td align="left" colspan="7"> <input type="text" size="80" id="remark1" name="remark"/> </td>
					</tr>
					<tr>
						<td align="right"> 个性签名: </td>
						<td align="left" colspan="7"> <input type="text" size="80" id="personalInfo1" name="personalInfo"/> </td>
					</tr>
					<tr>
						<td align="center" colspan="8">
							<a href="#" id="saveDivButton" class="easyui-linkbutton" name="saveDivButton" onclick="provinguser();">保存</a>
							<a href="#" class="easyui-linkbutton" onclick="closeuserInfo();" id="closeDivButton">关闭</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
</body>
</html:html>