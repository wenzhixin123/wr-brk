		//封装弹出框--信息（绿色圆形-感叹号）
		function $alertInfo(text){
			$.messager.alert("Information",text,"info");
		}
		//给传入字符串增加蓝色标识
		function fontColorBlueFront(text){
			return "<font color='blue' size=2>"+text+"</font>";
		}
		//封装弹出框--错误（×号）
		function $alertError(text){
			if(text && 0==text){
				text="执行过程中出错了!";
			}else if(text && "no"==text){
				text="已经不存在的单据!";
			}else if(text && "form"==text){
				text="数据效验不通过.(必输项未填)";
			}
			$.messager.alert("Error",text,"error");
		}
		//封装弹出框--警告（黄色三角-感叹号）
		function $alertWarning(text){
			$.messager.alert("Warning",text,"warning");
		}
		//封装弹出框--问题（问号）
		function $alertQuestion(text){
			$.messager.alert("Question",text,"question");
		}
		//封装弹出框--右下角(红色提醒)
		function $alertShow(text){
			if(0==text){
				text="没有可操作数据!";
			} else if(1==text){
				text="仅支持单条数据操作!";
			}else if("error"==text){
				text="操作失败!";
			}else if("ST"==text){
				text="单据状态不支持操作!";
			}else if("form"==text){
				text="数据效验不通过.(必输项未填)";
			}else if ("RE"==text) {
				text="不能执行此操作!(状态或类型不符)[提示这个的有两个原因：1、单证未保存或无关联单证。2、无权限。]";
			}
			$.messager.show({
				title:'警告',
				msg:'<font style="color: red;" size=2>'+text+'</font>',
				width:300,
				height:110,
				showType:'show'
			});
		}
		//封装弹出框--右下角（绿色提示）,一般用于操作成功提示
		//封装弹出框--右下角(绿色提醒)
		function $alertShowGreen(text){
			if("D"==text){
				text="成功删除!(未保存之前是临时删除)";
			}else if("success"==text){//
				text="操作成功!";
			}
			$.messager.show({
				title:'操作提示',
				msg:'<font style="color: green;" size=2>'+text+'</font>',
				height:110,
				showType:'show'
			});
		}
		//封装弹出框--右下角（自定义宽度）
		function $alertShowSlide(text,widths,heights,timeouts){
			var width=300;
			var height=100;
			var timeout=500;
			if(width)
				width=widths;
			if(height)
				height=heights;
			if(timeout)
				timeout=timeouts;
			$.messager.show({
				title:'操作',
				msg:text,
				timeout:timeout,
				width:width,
				height:height,
				showType:'slide'
			});
		}
		//封装弹出框--右下角(红色提醒·光标处弹出)
		function $alertByShow(text){
			if(0==text){
				text="没有可操作数据!";
			} else if(1==text){
				text="仅支持单条数据操作!";
			}else if("error"==text){
				text="操作失败!";
			}else if("ST"==text){
				text="单据状态不支持操作!";
			}else if("form"==text){
				text="数据效验不通过.(必输项未填)";
			}
			var element = document.elementFromPoint(event.x,event.y);//获取点击对象
		    $.messager.showBySite({
		        title:'警告',
		        msg:'<font style="color: red;" size=2>'+text+'</font>',
		        showType:'show'
		    },{
		        top : $(element).position().top+$(element).height(),//将$.messager.show的top设置为点击对象之下
		        left : $(element).position().left,//将$.messager.show的left设置为与点击对象对齐
		        bottom : ""
		    });
		    //由于此弹出框并不会自动关闭所以这里给予手动关闭
		    //setTimeout(function(){$alertClose()},6000);
		}
		//封装弹出框--右下角(绿色提醒·光标处弹出)
		function $alertByShowGreen(text){
			if("D"==text){
				text="成功删除!(未保存之前是临时删除)";
			}else if("success"==text){//
				text="操作成功!";
			}
			var element = document.elementFromPoint(event.x,event.y);//获取点击对象
		    $.messager.showBySite({
		        title:'操作提示',
		        msg:'<font style="color: green;" size=2>'+text+'</font>',
		        showType:'show'
		    },{
		        top : $(element).position().top+$(element).height(),//将$.messager.show的top设置为点击对象之下
		        left : $(element).position().left,//将$.messager.show的left设置为与点击对象对齐
		        bottom : ""
		    });
		    //由于此弹出框并不会自动关闭所以这里给予手动关闭
		    //setTimeout(function(){$alertClose()},6000);
		}
		//封装弹出框--关闭右下角提示
		function $alertClose(){
			$(".messager-body").window('close');
		}
		/**
		 * 指定位置显示$.messager.show
		 * options $.messager.show的options
		 * param = {left,top,right,bottom}
		 */
		$.extend($.messager, {
			showBySite : function(options,param) {
				var site = $.extend( {
					left : "",
					top : "",
					right : 0,
					bottom : -document.body.scrollTop
							- document.documentElement.scrollTop
				}, param || {});
				var win = $("body > div .messager-body");
				if(win.length<=0)
					$.messager.show(options);
				win = $("body > div .messager-body");
				win.window("window").css( {
					left : site.left,
					top : site.top,
					right : site.right,
					zIndex : $.fn.window.defaults.zIndex++,
					bottom : site.bottom
				});
			}
		});
		
		//判断所传送进来的字符是否是空的
		function is_null(value) {
			if (value == "" || value == null || value == undefined || value == "null" || value == "undefined") {
				return true;
			} else {
				// 如果是数字 并且等于0返回true
				if (!isNaN(value) && value == 0) {
					return true;
				} else {
					return false;
				}
			}
		}
		//返回值处理
		function showMsg(returnValue){
			if(returnValue.result){
				if(!is_null(returnValue.msg)){
					$alertShowGreen(returnValue.msg);
				}
				return true;
			}else{
				if(!is_null(returnValue.error)){
					$alertShow(returnValue.error);
				}
				return false;
			}
		}
		/**
		 *  <!-- 进度条 弹出框(左右滚动的图片) -->
		 *	<div id="ProcessingInTheDIV" title="ing..." style="width: 54px;height: 8px;background-color: white;" align="center">
		 *		<img src="<%=path%>/js/easyui/themes/icons/loading.gif" />
		 *	</div>
		 *	<!-- 结束 进度条 弹出框(左右滚动的图片) -->
		 */
		//打开-需要导入页面增加此DIV
		function openPID(){
			$('#ProcessingInTheDIV').dialog({modal:true,closable:false,resizable:false});
		}
		//关闭-需要导入页面增加此DIV
		function closePID(){
			$('#ProcessingInTheDIV').dialog('close');
		}
		//给传入字符串增加红色标识
		function fontColorRedFront(text){
			return "<font color='red' size=2>"+text+"</font>";
		}
		//给传入字符串增加红色标识(给予增加颜色和样式)
		function fontColorFront(text,color,style){
			if(is_null(color)){
				color="#000";
			}
			if(is_null(style)){
				style="";
			}
			return "<font color='"+color+"' size=2 style='"+style+"'>"+text+"</font>";
		}
		/**
		* 去空格
		* onblur="strTrim(this.id);"
		* 注：如果不起作用、请检查是否ID页面有重复。
		*/
		function strTrim(idText){
			$("#"+idText).val($.trim($("#"+idText).val()));
		}
		//转换大写字母去空格
		function majusculeInput(eat){
			eat = "#"+eat;
			var str = $.trim($(eat).val());
			str = str.toLocaleUpperCase();
			$(eat).val(str);
		}
		//打开Dialog的弹出框
		function odlog(dialogId){
			$("#"+dialogId).dialog({
				resizable:true,
				modal:true
				/*closable:false*/
			}).dialog("open");
		}
		//关闭Dialog的弹出框
		function cdLog(logId){
			$("#"+logId).dialog("close");
		}
		/**
		 * 这两个方法的用法：onfocus="deleteItemDesc(this.id,this.value,'请输入车卡号查询(回车)')" onblur="addItemDesc(this.id,this.value,'请输入车卡号查询(回车)')"
		 */
		//输入框控制
		function deleteItemDesc(id,value,isValue){
			if(value==isValue){
				$("#"+id).val("");
			}
		}
		//输入框控制
		function addItemDesc(id,value,isValue){
			if(value==""){
				$("#"+id).val(isValue);
			}
		}
		//获取单选选中数据
		function getPublicDataGridSelected(dataGrid){
			return $("#"+dataGrid).datagrid("getSelected");
		}
		//获取全部行数据
		function getAllPublicDatagrid(dataGrid){
			return $("#"+dataGrid).datagrid("getData").rows;
		}
		//获取多选数据
		function getPublicDatagridSelectedS(dataGrid){
			return $("#"+dataGrid).datagrid("getSelections");
		}
		//获取改变数据
		function getPublicDatagridChanges(dataGrid){
			datagirdendEdit(dataGrid);
			return $("#"+dataGrid).datagrid("getChanges");
		}
		//获取行号
		function getPublicDatagridRowNum(dataGrid,row){
			return $("#"+dataGrid).datagrid("getRowIndex",row);
		}
		//选中全部
		function allPublicDatagridselect(dataGrid){
			$("#"+dataGrid).datagrid("selectAll");
		}
		//取消选中全部
		function allPublicDatagridUnselect(dataGrid){
			$("#"+dataGrid).datagrid("unselectAll");
		}
		//将数据增加到列表中:id、数据、是否需要清空
		function addDatagridData(dataGrid,data,isC){
			if(getAllPublicDatagrid(dataGrid).length==0){
				isC=true;
			}
			if(isC){
				//直接加载
				$("#"+dataGrid).datagrid("loadData",{total:0,rows:data});
			}else{
				//添加明细
				$.each(data,function(index,model){
					$('#'+dataGrid).datagrid('appendRow',model);
				});
			}
		}
		//选中一行
		function sePublicDatagridselectRow(dataGrid,rownum){
			$("#"+dataGrid).datagrid("selectRow",(rownum-1>-1?rownum-1:0));
		}
		//取消选中一行
		function unPublicDatagridselectRow(dataGrid,rownum){
			$("#"+dataGrid).datagrid("unselectRow",(rownum-1>-1?rownum-1:0));
		}
		//执行删除行
		function rowPublicDatagridDelete(dataGrid,rows){
			$.each(rows,function(index,obj){
				var index = $('#'+dataGrid).datagrid('getRowIndex', obj);
				$('#'+dataGrid).datagrid('deleteRow', index);
			});
		}
		//刷新表格
		function datagirdReaload(dataGrid){
			$("#"+dataGrid).datagrid('acceptChanges');
			$("#"+dataGrid).datagrid("reload");
		}
		//进入编辑
		function datagirdbeginEdit(dataGrid,index){
			$("#"+dataGrid).datagrid('beginEdit',index);
		}

		//开始某行编辑
		function datagirdbeginIndexEdit(dataGrid,index){
			$("#"+dataGrid).datagrid('beginEdit',index);
		}
		//结束某行编辑
		function datagirdendIndexEdit(dataGrid,index){
			$("#"+dataGrid).datagrid('endEdit',index);
		}
		//结束列表全部编辑
		function datagirdendEdit(dataGrid){
			$.each(getAllPublicDatagrid(dataGrid),function(index,obj){
				$("#"+dataGrid).datagrid('endEdit',index);
			});
		}
		//结束编辑·并撤销改变
		function datagirdendEditAndChang(dataGrid){
			datagirdendEdit(dataGrid);
			$("#"+dataGrid).datagrid('acceptChanges');
		}
		//修改行数据
		function datagirdUpdateRow(dataGrid,indexs,data){
			$("#"+dataGrid).datagrid('updateRow',{index:indexs,row:data});
		}
		//验证列表必输项是否全部输入（dataGrid新增行的时候使用）
		function datagridValidateData(dataGrid){
			if (! $("#"+dataGrid).datagrid("validate")) {
				$alertShow("必填数据为空!请审核后重新操作!");
				return false;
			}else{
				return true;
			}
		}
		//Form·获取数据
		function getFromData(form_id){
			return $('#'+form_id).form('getData');
		}
		//Form·效验必输项
		function getFormValid(form_id){
			return $('#'+form_id).form('validate');
		}
		//Form·增加数据
		function setFormData(formId,data){
			clearFormData(formId);
			$("#"+formId).form("setData",data);
		}
		//Form·清空数据
		function clearFormData(formId){
			$("#"+formId).form("clear");
		}
		/**
		 * 循环 冻结 数组中按钮 如： var buttonJumpArrayFreeZing = new Array();
		 * buttonJumpArrayFreeZing.push("submitButton");
		 */
		function CirculationFreezingArrayButton(buttonJumpArray) {
			if (buttonJumpArray) {
				$.each(buttonJumpArray, function(index, obj) {
					if(document.getElementById(obj)){
						if(document.getElementById(obj).className.indexOf('easyui-linkbutton')>=0 || document.getElementById(obj).className.indexOf('easyui-menubutton')>=0 ){
							if(document.getElementById(obj).nextSibling){
								if(document.getElementById(obj).nextSibling.className && "datagrid-btn-separator"==document.getElementById(obj).nextSibling.className){
									document.getElementById(obj).nextSibling.style.display="none";//一期框架的有问题所以使用这个了。
								}else{
									//如果有分割线的
									if(document.getElementById(obj).nextSibling.nextElementSibling){
										//顺便隐藏分割线·火狐
										document.getElementById(obj).nextSibling.nextElementSibling.style.display="none";
									}else if(document.getElementById(obj).nextSibling.nextSibling && document.getElementById(obj).nextSibling.nextSibling.style){
										//顺便隐藏分割线·IE
										document.getElementById(obj).nextSibling.nextSibling.style.display="none";
			
									}
								}
							}
							$("#" + obj).linkbutton("disable").unbind("click");
							//隐藏按钮
							document.getElementById(obj).style.display = "none";
						}else{
							$("#" + obj).hide();
						}
					}
				});
			}
		}
		/**
		 * 循环 解冻 数组中按钮 如： var buttonJumpArrayThaw = new Array();
		 * buttonJumpArrayThaw.push("submitButton");
		 */
		function CirculationThawArrayButton(buttonJumpArray) {
			if (buttonJumpArray) {
				$.each(buttonJumpArray, function(index, obj) {
					if(document.getElementById(obj)){
						if(document.getElementById(obj).className.indexOf('easyui-linkbutton')>=0  || document.getElementById(obj).className.indexOf('easyui-menubutton')>=0 ){
							//显示按钮
							document.getElementById(obj).style.display = "";
							if(document.getElementById(obj).nextSibling){
								if(document.getElementById(obj).nextSibling.className && "datagrid-btn-separator"==document.getElementById(obj).nextSibling.className){
									document.getElementById(obj).nextSibling.style.display="inline";//一期框架的有问题所以使用这个了。
								}else{
									//如果有分割线的
									if(document.getElementById(obj).nextSibling.nextElementSibling){
										//顺便显示分割线·火狐
										document.getElementById(obj).nextSibling.nextElementSibling.style.display="inline";
									}else if(document.getElementById(obj).nextSibling.nextSibling && document.getElementById(obj).nextSibling.nextSibling.style){
										//顺便显示分割线·IE
										document.getElementById(obj).nextSibling.nextSibling.style.display="inline";
									}
								}
							}
							$("#" + obj).linkbutton("enable");
						}else{
							$("#" + obj).show();
						}
					}
				});
			}
		}
		var buttonMapJU=new Array();
		/**
		 * 根据按钮权限找是否已经授权
		 * @param arrayButton:按钮权限传送
		 * @param isGO:是否直接屏蔽
		 * @return
		 */
		function _button_Jurisdiction(arrayButton,isGO){
			SysViewButtonManager.getMenuItemButtonMap(arrayButton,function(spj){
				buttonMapJU=spj.object;
				if(!is_null(isGO)){
					$.each(arrayButton,function(index,obj){
						_buttonAddJurisdiction(obj.btnCode);
					});
					//冻结
					CirculationFreezingArrayButton(JURISD_BUT_NO);
					//解冻
					CirculationThawArrayButton(JURISD_BUT_YES);
				}
			});
		}
	
		/**
		 * 导入下拉 过滤选择
		 * @param jurisdictioncomboboxData
		 * @param boole
		 */
		function _jurisdictionData(attribute,arrayButton,boole){
			SysViewButtonManager.getMenuItemComboboxButtonMap(arrayButton,boole,function(spj){
					$(attribute).combobox({
						valueField:"id",
						textField:"text"
					});
				 $(attribute).combobox("loadData",spj.object);
			});
		}
		
		
		
		//拥有的权限
		var JURISD_BUT_YES=new Array();
		//不拥有的权限
		var JURISD_BUT_NO=new Array();
		/**
		 * 控制判断按钮授权问题
		 * 使用此方法必须先调用_button_Jurisdiction此方法
		 * @param but:按钮ID
		 * @param free:冻结数组
		 * @param thaw:解冻数组
		 * @return
		 */
		function _buttonAddJurisdiction(but){
			var isOk=false;
			$.each(buttonMapJU,function(index,obj){
				if(obj==but){
					isOk=true;
				}
			});
			if(isOk){
				JURISD_BUT_YES.push(but);
				return true;
			}else {
				JURISD_BUT_NO.push(but);
				return false;
			}
		}
		/**
		 * 判断两个时间之间对应的天数是否符合规则
		 * @param s：开始时间
		 * @param e：结束时间
		 * @param t：判断天数（不传默认三十）
		 * @return
		 */
		function dateSE(s,e,t){
			if(is_null(s) || is_null(e)){
				return false;
			}
			var timeS = new Date(s.replace("-","/")); 
	       	var b = 30; //分钟数 
	       	if(!is_null(t)){
	       		b=t;
	       	}
	       	timeS.setDate(timeS.getDate() + b); 
	       	var timeE = new Date(e.replace("-","/")); 
	       	if(timeE>=timeS){
		       	return true;
		    }else{
		    	return false;
		    }
		}
		
		//按钮转换
		function buttonColorTs(funName,value,text,id){
			var style="cursor:pointer; background-color: #EBEBEB;border: none;color: #444;border-radius: "+(is_null(text)?".5":("修改"==text?"1.0":".5"))+"em;font-weight: bold;line-height: 1.0;border: 1px solid #FFCC00;";
			return "<button onclick='"+funName+"(this.value,this.id)' value='"+value+"' id='"+id+"' style='"+style+"'>"+text+"</button>";
		}
		
		//单选按钮
		function radioColorTs(funName,value,text,name,id){
			return "<input onclick='"+funName+"(this.value,this.id,this.name)' value='"+value+"' id='"+id+"' name='"+name+"' type='radio'/>";
		}
		var setTime_public=true;
		//初始化仓库的选择
		function _publicSelectsButton(thisId,string){
			if(setTime_public){
				//设置成同步        
				DWREngine.setAsync(false);
				var stringCK_public="<table>";
				stringCK_public+="<tr>";
				stringCK_public+=string;
				stringCK_public+="</tr>";
				stringCK_public+="</table>";
				$alertShowSlide(stringCK_public,400,300,3000);
				setTime_public=false;
				setTimeout(function(){
					setTime_public=true;
				},3000);
				if($("#"+thisId).val() && $("#"+$("#"+thisId).val()) && $("#"+$("#"+thisId).val()).length){
					$("#"+$("#"+thisId).val())[0].checked=true;
				}
				//设置成同步        
				DWREngine.setAsync(false);
			}
		}
		//执行选中后的赋值
		function _publiccheckboxOnclick(thisId,checked,value,title){
			$("#"+thisId).val(value);
		}
		//计算输入的数量。
		function num_public_jisuan_num(id,text){
			if(text.replace(/[^\x00-\xFF]/g,'**').length==0){
				$('#'+id+"_num").val("");
			}else if(text.replace(/[^\x00-\xFF]/g,'**').length>500){
				$('#'+id+"_num").val(text.replace(/[^\x00-\xFF]/g,'**').length);
			}else{
				$('#'+id+"_num").val(text.replace(/[^\x00-\xFF]/g,'**').length);
			}
		}
		/**
		 * 提供公共LO重新回传。
		 **/
		function _repeatReturn_JS(id){
			if(!is_null(id)){
				//同步
				DWREngine.setAsync(false);
				openPID();
				var arrayList=getPublicDatagridSelectedS(id);
				if(arrayList.length==0){
					closePID();
					$alertShow(0);
				}
				var list=new Array();
				$.each(arrayList,function(index,obj){
					var array=new Object();
					array.logisticsOrderUuid=obj.logisticsOrderUuid;
					list.push(array);
				});
				if(list.length>0){
					LogisticsOrderManager.ediDatasRequesJudgen(list,{
						callback : function(spj){
							showMsg(spj);
							datagirdReaload(id);
							setTimeout(function(){
								closePID();
							},300);
						},
	                    errorHandler : function(error) {
	                    	closePID();
							$alertError(error);
	                    }
					});
				}else{
					closePID();
					$alertShow(0);
				}
				//同步
				DWREngine.setAsync(true);
			}
		}
		
		/**
		 * 获取得到当前登录人的仓库、并且使用与判断仓库来运行脚本等、可以做仓库区分来写不同代码。
		 */
		var wmsOffice="";
		function getWms(){
			if(!is_null(wmsOffice)){
				return;
			}
			//同步
			DWREngine.setAsync(false);
			WmsCommonManager.getWMS(function(str){
				wmsOffice=str;
			});
			DWREngine.setAsync(true);
		}

		var fontStyle="font-weight: bold;";
		/**
		 * 单据状态转换
		 * formatter="statusAtter"
		 * formatter:"statusAtter"
		 */
		function statusAtter(value,rowData,index){
			if(!is_null(rowData)){
				if(rowData.cw3=="F" || (!is_null(rowData.controlWord) && rowData.controlWord.charAt(2)=="F"  && "Cancel"!=value)){
					value="Complete";
				}
			}
			if("Active"==value){
				return fontColorFront("生效","yellowgreen",fontStyle);
			}else if("Pending"==value){
				return fontColorFront("草稿","gold",fontStyle);
			}else if("Complete"==value){
				return fontColorFront("完结","burlywood",fontStyle);
			}else if("Cancel"==value){
				return fontColorFront("作废","violet",fontStyle);
			}else if("Approval"==value){
				return fontColorFront("待审批","thistle",fontStyle);
			}else if("Reject"==value){
				return fontColorFront("驳回","tomato",fontStyle);
			}else if("Pastpue"==value){
				return fontColorFront("超时","brown",fontStyle);
			}else if("Claim"==value || "ClaimDemage"==value){
				return fontColorFront("索赔","green",fontStyle);
			}else if("Process"==value){
				return fontColorFront("内部处理","cream",fontStyle);
			}else if("Contract"==value){
				return fontColorFront("申报保险","coral",fontStyle);
			}else if("Callback"==value){
				return fontColorFront("回访","pistac",fontStyle);
			}else if("Reconsider"==value){
				return fontColorFront("复议","pink",fontStyle);
			}else{
				return value;
//				if(rowData.logisticsOrderNo!="当页统计:"){
//					//return fontColorRedFront("暂无");
//				}else{
//				}
			}
		}
		/**
		 * 【是否】转换
		 * formatter="yesOrNoAtter"
		 * formatter:"yesOrNoAtter"
		 */
		function yesOrNoAtter(value,rowData,index){
			if(!is_null(rowData)){
				//留空、待处理（此处判断状态后在后面的判断中增加显示。。这里只是改变value的值）
			}
			if("Y"==value){
				return fontColorFront("是","yellowgreen",fontStyle);
			}else if("N"==value){
				return fontColorFront("否","violet",fontStyle);
			}else if("0"==value){
				return fontColorFront("无","thistle",fontStyle);
			}else if("All"==value){
				return fontColorFront("全部","green",fontStyle);
			}else{
				return value;
			}
		}
		
		/**
		 * 根据datagrid绑定输入事件文本框获取焦点自动选中文本。
		 * 注：暂不支持combogrid
		 * @param datagrid.ID
		 * @return
		 */
		function $datagridSelect(datagrid,rowIndex){
			var editors = $('#'+datagrid).datagrid('getEditors', rowIndex);
			$.each(editors,function(index,obj){
				var objEdit = editors[index];
				objEdit.target.bind("focus",function(){
					this.select();
				});
			});
		}

		/**
		 * 日常用语 下拉框初始化
		 * @param id 所要初始化的下拉框ID
		 * @param type 用语类型
		 * @return 返回数据 注意：返回的数据必须要在 基础资料->日常用语配置
		 */
		function initCombobox(id,type){
			BasCommonManager.getCommonType(type,function(spj){
				if (!is_null(id)) {
		   			$('#'+id).combobox({
						panelHeight:"auto",
					  	data:spj.object,
					  	valueField:'id',
					  	editable:false,
			            textField:'text'
					 });
				}else{
		   			return spj.object;
				}
			});
		}
		/**
		 * 日常用语 一级子状态
		 * @param id 所要初始化的下拉框ID
		 * @param code initCombobox初始化的ID值
		 * @returns 返回子数据 注意：返回的数据必须要在 基础资料->日常用语配置
		 */
		function initComboboxSonType(id,code){
			BasCommonManager.getCommonTypeSonType(code,function(spj){
				if (!is_null(id)) {
		   			$('#'+id).combobox({
						panelHeight:"auto",
						valueField:"id",
						textField:"text",
						required:true,
						editable:false
					 });
					if(spj.result){
						$('#'+id).combobox("loadData",spj.object);
					}
				}else{
		   			return spj.object;
				}
			});
		}
		/**
		 * 用于 datagrid 的combobox
		 * @param id 获取datagrid的编辑 $("#ID").datagrid("getEditor",{index:rowIndex,field:"fieldName"});
		 * @param rowData 加载值
		 * @param select 选中值
		 */
		function initDataGridComboboxRowData(id,rowData,select){
			if (!is_null(id)) {
				$(id.target).combobox({
					panelHeight:"auto",
					valueField:"id",
					textField:"text",
					editable:false
				});
				$(id.target).combobox("loadData",rowData);
				if (!is_null(select)) {
					$(id.target).combobox("select",select);
				}
			}else{
	   			return spj.object;
			}
		}
		/**
		 * 页面数据录入控制
		 * @param formId 表单ID
		 * @param combn 下拉框数组ID或按钮  @ 代表是下拉框
		 * @param isOk true：释放，false禁止
		 * @param type 控制类型 是否显示
		 */
		function _controlPage(formId,combn,isOk,type){
			if (is_null(combn)) {
				combn=[];
			}else{
				var btns = new Array();
				var cobs = new Array();
				$.each(combn,function(i,btn){
					if (btn.substring(0,1)=="@") {
						btn=btn.substring(1,btn.length);
						cobs.push(btn);
					}else{
						btns.push(btn);
					}
				});
				if (type) { //按钮控制 禁止
					if (isOk) {
						$.each(btns,function(i,btn){
							$("#"+btn).linkbutton("enable").unbind("click");
						});
					}else{
						$.each(btns,function(i,btn){
							$("#"+btn).linkbutton("disable").unbind("click");
						});
					}
				} else {//按钮控制 隐藏
					if (isOk) {
						CirculationThawArrayButton(btns);//解冻
					} else {
						CirculationFreezingArrayButton(btns);//冻结
					}
				}
				if (isOk) {
					$.each(cobs,function(i,cob){
						$("#"+cob).combo("enable");
					});
				} else {
					$.each(cobs,function(i,cob){
						$("#"+cob).combo("disable");
					});
				}
			}
			if (!is_null(formId)) {
				formId = "#"+formId;
				if(isOk){
					$(formId).find('input').attr('readonly',false);
					$(formId).find('textarea').attr('readonly',false);//屏蔽Form中所有input字段不可编辑
				}else{
					$(formId).find('input').attr('readonly','readonly');//屏蔽Form中所有input字段不可编辑
					$(formId).find('textarea').attr('readonly','readonly');//屏蔽Form中所有input字段不可编辑
				}
			}
		}
		/**
		 * 获取按钮权限
		 * @param menuItemCode 按钮编码
		 * @return true:有权限 flase:没权限
		 */
		function _getButtonViweJurisdiction(menuItemCode){
			var isOk = true;
			DWREngine.setAsync(false);
			SysViewButtonManager.getMenuItemButtonBoolean(menuItemCode,function(str){
				isOk =  str;
			});
      		DWREngine.setAsync(true);
      		return isOk;
		}
		/**
		 * 附件操作权限
		 * @param uploadCode 【按钮编码】上传
		 * @param showCode 【按钮编码】浏览
		 * @param deleteCode 【按钮编码】删除
		 */
		function _fileViweAcitonJurisdiction(uploadCode,showCode,deleteCode){
			var arrayButton=[{"btnCode":""+(is_null(uploadCode)?"------":uploadCode)+""},{"btnCode":""+(is_null(showCode)?"------":showCode)+""},{"btnCode":""+(is_null(deleteCode)?"------":deleteCode)+""}];
			SysViewButtonManager.getMenuItemButtonMap(arrayButton,function(spj){
				var isUpload = false;
				var isShow = false;
				var isDelete = false;
				$.each(spj.object,function(index,obj){
					if(obj==uploadCode){
						isUpload=true;
					}else if(obj==showCode){
						isShow=true;
					}else if(obj==deleteCode){
						isDelete=true;
					}
				});
	//			alert(isUpload+"-"+isShow+"-"+isDelete);
				if (isUpload&&isShow&&isDelete) {
					updateFjControlStatus();
	//				alert("上传、浏览、下载");
				} else if (isUpload&&isShow) {
					updateFjControlStatus("FJ_CONTROL_U_DO");
	//				alert("上传、浏览");
				} else if (isUpload&&isDelete) {
					updateFjControlStatus("FJ_CONTROL_U_DE");
	//				alert("上传、删除");
				} else if (isShow&&isDelete) {
					updateFjControlStatus("FJ_CONTROL_DO_DE");
	//				alert("浏览、删除");
				} else if (isUpload) {
					updateFjControlStatus("FJ_CONTROL_UPLOAD");
	//				alert("上传");
				} else if (isShow) {
					updateFjControlStatus("FJ_CONTROL_DOWNLOAD");
	//				alert("浏览");
				} else if (isDelete) {
					updateFjControlStatus("FJ_CONTROL_DELETE");
	//				alert("下载");
				} else {
					updateFjControlStatus("FJ_CONTROL_NULL");
	//				alert("无");
				}
			});
		}
		/**
		 * 同步循环效验
		 * @param _dataGrid 循环数据的dataGrid ID
		 * @param _getDataType 获取类型 getSelect：获取选中数据  getAll：获取全部数据
		 * @param _verification 效验方法
		 * @return 返回一个实体， 属性和后台spj一样，额外一个属性isShow是否直接提示 默认是
		 */
		function _cycleVerification(_dataGrid,_getDataType,_verification){
			DWREngine.setAsync(false);
			var _obj = null;
			var _spj = new _comeBackCycleClass();
			if (_getDataType=="getSelect") {
				_obj = getPublicDatagridSelectedS(_dataGrid);
				_spj.error = "操作失败，未选有数据";
			} else if (_getDataType=="getAll") {
				_obj = getAllPublicDatagrid(_dataGrid);
				_spj.error = "操作失败，没数据提供";
			}
			if (_obj.length<=0) {
				_spj.result=false;
			} else {
				$.each(_obj,function(rowIndex,rowData){
					if (_spj.result) {
						_spj = _verification(rowIndex,rowData);
					}
				});
			}
			if (is_null(_spj.isShow)||_spj.isShow) {
				if (_spj.result) {
					$alertInfo(_spj.msg);
				} else {
					$alertShow(_spj.error);
					DWREngine.setAsync(true);
					document.execCommand("stop");
				}
			}
			DWREngine.setAsync(true);
			return _spj;
		}
		/**
		 * 返回信息
		 * @param result Boolean
		 * @param msg 正确消息
		 * @param error 错误消息
		 * @param object 实体
		 * @param isShow 是否直接提示
		 * @returns {_comeBackCycleClass}
		 */
		function _comeBackCycleClass(result,msg,error,object,isShow){
		    this.result = result;  
		    this.msg = msg; 
		    this.error = error;
		    this.object = object;
		    this.isShow = is_null(isShow)?true:isShow; 
		}
		/**
		 * 变量解绑， 用作于 绑在一起的变量
		 * @param obj 传进的实体、模板
		 * @param isNullObj 是否构建新的类，不赋值 默认赋值
		 * @returns {_OffControlClass}
		 */
		function _OffControlClass(obj,isNullObj){
			var objClass = new Object();
			for(name in obj){
				if(!is_null(name)){
					if(isNullObj){
						objClass[name]=undefined;
					} else {
						objClass[name]=obj[name];
					}
				}
			}
			return objClass;
		}
		/**
		 * 用于编辑行数据 ：
		 * 用法 var ecObj = _EditorsClass(objs);  //objs 编辑行参数 可用$('#'+dataGrid_ID).datagrid('getEditors', index); 获取
		 * 属性：ecObj.editors;//编辑列集合
		 * 属性：ecObj.editorAllEvent(事件名称，function(){
		 *  //触发方法
		 * });
		 * 编辑列名：ecObj.编辑列名;
		 * ecObj.编辑列名.getValue();//获取当前列的实时值
		 * ecObj.编辑列名.setValue(Value);//赋值实时值并不影响 ecObj.编辑列名.value值
		 * ecObj.编辑列名.editor;//获取当前编辑列的属性
		 * ecObj.编辑列名.value;//编辑列值
		 * ecObj.编辑列名.index;//编辑列坐标
		 * @param objs 编辑行数组
		 * @returns {_EditorsClass}
		 */
		function _EditorsClass(objs){
			var objClass = new Object();
			objClass.editors=objs;//编辑列集合
			objClass.editorAllEvent=function(edVn,action){//给所有编辑列赋值触发事件
				for (var i = 0; i < this.editors.length; i++) {
					this.editors[i].target.unbind(edVn).bind(edVn,action);
				}
			}
			for (var i = 0; i < objs.length; i++) {
				var valueObj = new Object();
				valueObj.index=i;//坐标
				valueObj.value=objs[i].target.val();//获取界面值（如果界面值已经被修改，将不同步）
				valueObj.editor=objs[i];
				valueObj.editorEvent=function(edVn,action){//给当前列赋值触发事件
					this.editor.target.unbind(edVn).bind(edVn,action);
				}
				valueObj.setValue=function(value){//修改界面值
					var type=this.editor.type;
					if(type=="text")
						this.editor.target.val(value);
					else if(type=="combogrid")
						this.editor.target.combogrid("setValue",value)
					else if(type=="combobox")
						this.editor.target.combogrid("select",value)
				};
				valueObj.getValue=function(){//获取界面值
					return this.editor.target.val();
				};
				objClass[objs[i].field]=valueObj;
				objs[i].target[0].id=i;//编辑列下标
				objs[i].target[0].name=objs[i].field;//编辑列名
			}
			return objClass;
		}

		/**
		 * javaMap 类
		 * @returns {Map}
		 */
		function Map() {
			 /**
			  * 添加键
			  * @param key 键
			  * @param value 值
			  * @returns 无返回值
			  */
		    var put = function(key, value) {
	            this.arr[key] = value;
		    };
		    /**
		     * 获取键值
		     * @param 键
		     * @returns
		     */
		    var get = function(key) {
	            if (this.arr[key] != undefined) {
	                return this.arr[key];
	            } else {
	    	        return null;
	            }
		    };
		 	/**
		 	 * 删除
		 	 * @param key 删除键
		 	 * @returns 无返回值
		 	 */
		    var remove = function(key) {
		    	
		    };
		 	/**
		 	 * 长度
		 	 * @returns 集合长度
		 	 */
		    var size = function() {
		        return this.arr.length;
		    };
		 	/**
		 	 * 是否为空
		 	 * @returns boolean
		 	 */
		    var isEmpty = function() {
		        return this.arr.length <= 0;
		    };
		 
		    this.arr = {};
		    this.get = get;
		    this.put = put;
		    this.remove = remove;
		    this.size = size;
		    this.isEmpty = isEmpty;
		}
		/**
		 * 把字符串转为字符集合
		 * @param strs
		 * @returns {changeStringToCharArray}
		 */
		function _changeStringToCharArray(strs){
			/**
			 * 字符集合
			 * @returns 字符
			 */
			var charMap = function(){
				var cMap = new Map();
				for ( var c in strs) {
					cMap.put(c+1, strs[c]);
				}
				return cMap;
			}
			var cahrArray = function(){
				var arry = new Array();
	            for ( var str in strs) {
	            	arry.push(strs[c]);
				}
	            return arry;
			}
			var pinjiePe = function(chars){
				var str = "";
				for ( var c in chars) {
					str=str+strs[c];
				}
				return str;
			}
			var addCharSting = function(adds,len,hul){
				var str = "";
				if(!is_null(len)){
					for ( var c in strs) {
						if(hul!=strs[c]){
							if((c+1)<=len){
								str=str+strs[c]+adds;
							}
						}
					}
				} else {
					for ( var c in strs) {
						if(hul!=strs[c]){
							str=str+strs[c]+adds;
						}
					}
				}
				str=index.substring(0,index.length-1);
				return str;
			}
			var addCharIndex = function(adds,len,hul){
				var index = "";
				if(!is_null(len)){
					for ( var c in strs) {
						c=Number(c);
						if(hul!=strs[c]){
							if((c+1)<=len){
								index = index+(c+1)+adds;
							}
						}
					}
				} else {
					for ( var c in strs) {
						c=Number(c);
						if(hul!=strs[c]){
							index = index+(c+1)+adds;
						}
					}
				}
				index=index.substring(0,index.length-1);
				return index;
			}
			this.charMap = charMap; //字符Map集合
			this.cahrArray = cahrArray ; //字符数组
			this.source = strs; //源
			this.addCharSting = addCharSting;
			this.addCharIndex = addCharIndex;
		}
		
		/**
		 * 制保留2位小数，如：2，会在2后面补上00.即2.00  -陈雨
		 */
		function toDecimal2(x){
			var f=parseFloat(x);
			if(isNaN(f)){
				return false;
			}
		    var f = Math.round(x*100)/100;
		    var s =f.toString();
		    var rs = s.indexOf('.');
			if (rs <0) {
				rs = s.length;
				s += '.';
			}
			while(s.length<=rs+2){
				s +='0';
			}
			return s;
		}
		
		/**
		 * 调用此方法、并且把对应的class传送过来将会对应的给予字体闪烁的功能。
		 */
		var earlyWarningIdsOOu=0;
		var earlyWarning_ID="",earlyWarning_CLASS="",earlyWarning_COLOR="";
		function earlyWarningChangeColor(){
			if(is_null(earlyWarning_ID))
				earlyWarning_ID="earlyWarningColor";
			if(is_null(earlyWarning_ID))
				earlyWarning_ID="earlyWarningColor";
			if(is_null(color))
				earlyWarning_COLOR="red";
			var color=earlyWarning_COLOR.split("|");
			if(earlyWarningIdsOOu==color.length)
				earlyWarningIdsOOu=0;
			else
				earlyWarningIdsOOu++;
			
			$.each($("."+earlyWarning_ID),function(index,obj){
				this.style.color="white";
				this.style.backgroundColor =color[earlyWarningIdsOOu];
			});
			$.each($("#"+earlyWarning_ID),function(index,obj){
				this.style.color="white";
				this.style.backgroundColor =color[earlyWarningIdsOOu];
			});
		}
		/**
		 * 启动颜色改变方法
		 * id:需要被变化的id(默认:earlyWarningColor)
		 * classs:需要被变化的class(默认：earlyWarningColor)
		 * color:需要变化的颜色(源默认:blue|red；现默认:red)
		 */
		function earlyWarningInterval(id,classs,color){
			if(is_null(earlyWarning_ID)){
				earlyWarning_ID=id;
				earlyWarning_CLASS=classs;
				earlyWarning_COLOR=color;
				setInterval("earlyWarningChangeColor()",100);
			}
		}
		/**
		 * 预警控制转换(必须放在solWorkDork字段上)
		 * formatter="operationEarlyWarning"
		 * formatter:"operationEarlyWarning"
		 * 截至：2014年10月29日 已经不再、请自行写方法调用operationEarlyWarningCustom方法
		 */
		function operationEarlyWarning(value,rowData,index){
			if(!is_null(value)){
				var text="";
				//只需要要显示预警状态即可
//				var arr = new Array();
//				arr = value.split(";");
//				for(var i=0;i<arr.length;i++){
//					if(!is_null(arr[i])){
//						if(arr.length-1==i && is_null(rowData.earlyWarningName)){
//							text+="<a class='earlyWarning_auto_text'>"+arr[i]+"</a>";
//						}else{
//							text+="<a class='earlyWarning_auto_text'>"+arr[i]+"</a>"+">";
//						}
//					}
//				}
				if(!is_null(rowData.earlyWarningNum) && !is_null(rowData.earlyWarningName)){
					text+="<a class='earlyWarningColor earlyWarning_auto_text' title='已超出预计时间约("+geShiHuaShiJian(rowData.earlyWarningNum)+"'>"+rowData.earlyWarningName+"</a>";
				}else if(!is_null(rowData.earlyWarningName)){
					text+=rowData.earlyWarningName;
				}
				return text;
			}else{
				return value;
			}
		}
		
		/**
		 * 截取时间(不要时分秒)
		 * @param date：时间
		 */
		function substrtoDate(date){
			if(!is_null(date)){
				return date.substring(0,10);
			}
			return "";
		}
		/**
		 * 预警控制转换:间接调用方法
		 * @param value：超出分钟数
		 * @param name：预警描述
		 * @param callback：回调方法
		 * @param id：回调方法参数(如果不需要回调、此参数和上面的参数可以为null)
		 */
		function operationEarlyWarningCustom(value,name,callback,id){
			var nums=[];
			if(!is_null(value))
				nums=value.split(";");
			var names=[];
			if(!is_null(name))
				names=name.split(";");
			var text="";
			if(nums.length>0 && names.length>0){
				if(nums.length==names.length){
					for ( var int = 0; int < nums.length; int++) {
						var num=Number(nums[int])*60;
						if(!is_null(num)){
							if(num>0 && !is_null(names[int])){
								var titleS="已超出预计时间约:";
								if(names[int]=="截重预警"){
									titleS="接近计划时间、约还有:";
								}
								if(!is_null(callback)){
									text+="<a class='earlyWarningColor earlyWarning_auto_text' title='"+titleS+geShiHuaShiJian(num)+"' onclick='"+callback+"(this.id,this.title,this.rel)' id='"+id+"' rel='"+names[int]+"'>"+names[int]+"</a>";
								}else{
									text+="<a class='earlyWarningColor earlyWarning_auto_text' title='"+titleS+geShiHuaShiJian(num)+"'>"+names[int]+"</a>";
								}
							}
						}
					}
				}else{
					$alertShow("预警控制参数错误!数字与名称集合大小不对应!");
				}
			}else{
				
			}
			return text;
		}
		//读卡器节点读取
		var IC_CARD_CHANGE=false;
		//控制节点 平湖读卡
		function getIcCardChangeIsControl(){
			PhScannerInboundManager.isControl("BARREADER",null,null,function(spj){
				IC_CARD_CHANGE=spj.result;
			});
		}
		
		//深圳物流读卡数据值[读卡器]-->转16进制--->取后6位---->转10进制=结果   2014/01/06  cy
		function readerNum(value){
			if(IC_CARD_CHANGE){
				if(value.length<10){
					return value;
				}else{
					var vlaues=parseInt(value).toString(16);		
					var vlauess=vlaues.substring(vlaues.length-6,vlaues.length);
					
					return (parseInt(vlauess,16))+"";
				}
			}else{
				return value;
			}
		}
		
		var customsOrderisView=false;
		//入库取消签收权限
		function inCancelCustomsOrder(btnId,userId,officeCode){
			DWREngine.setAsync(false);
			//直接访问后台 获取当前用户是否拥有权限
			HubScannerManager.searchSysTemViewButton(userId,null,btnId,officeCode,function(node){
				//大于0说明授权成功
				if(node.length>0){
					customsOrderisView=true;
				}
			});
			DWREngine.setAsync(true);
		}
		
		//临时写的方法，把字符串格式为2011-01-02的处理成yyyy-mm-dd hh:mm:ss类型【提供一期框架使用】
		function newDateStr(str) {
			var strs;
			var d;
			var result;
			if(str){
				if(typeof(str)=="string"){
					strs = str.split(" ");
					if(strs.length>0){
						var ymd = strs[0];
						var hms = strs[1];
						if(ymd && hms){
							ymds = ymd.split("-");
							hmss = hms.split(":");
							if(ymds.length==3 && hmss.length==3){
								d=new Date(ymds[0],ymds[1]-1,ymds[2],hmss[0],hmss[1],hmss[2]);
							}
						}else if(ymd){
							ymds = ymd.split("-");
							if(ymds.length==3){
								d=new Date(ymds[0],ymds[1]-1,ymds[2]);
							}
						}
					}
				}else if(typeof(str)=="number"){
					d=new Date(str);
				}
			}
				
			if(d){
				result=d.format("yyyy-mm-dd HH:MM:ss");
			}
			
			return result;
		}
		
		//两个日期,返回天数
		function _dateBackDay(dateStart,dateEnd){
			if(dateStart=="" || dateEnd==""){
				$.messager.alert("提示","请选择开始结束日期查询！","info");
				return "error";
			}
			if(dateStart>dateEnd){
				$.messager.alert("提示","开始日期不能大于结束日期！","info");
				return "error";
			}
			var sArr = dateStart.split("-");
			var eArr = dateEnd.split("-");
			var sRDate = new Date(sArr[0], sArr[1], sArr[2]);
			var eRDate = new Date(eArr[0], eArr[1], eArr[2]);
			var result = (eRDate-sRDate)/(24*60*60*1000);
			if(result>32){
				$.messager.alert("提示","查询日期请限定一个月内!","warning");
				return result;
			}else{
				return null;
			}
		}
		
		//两个时间类型,返回天数
		function _timeBackDay(timestart,timeEnd){
			if(timestart=="" || timeEnd==""){
				$.messager.alert("提示","请选择开始结束日期查询！","info");
				return "error";
			}
			if(timestart>timeEnd){
				$.messager.alert("提示","开始日期不能大于结束日期！","info");
				return "error";
			}
			var strDate1=timestart.substring(0,timestart.lastIndexOf(".")).replace(/-/g, "/ ");
			var strDate2=timeEnd.substring(0,timeEnd.lastIndexOf(".")).replace(/-/g, "/ ");
			//去掉毫秒 把-替换成/ 如果不替换转成时间戳类型火狐会出问题
			var   date1   =  Date.parse(strDate1);
			var   date2   =  Date.parse(strDate2);
			var result = (date2-date1)/(24*60*60*1000);
			if(result>32){
				$.messager.alert("Warning","查询日期请限定一个月内!","warning");
				return result;
			}else{
				return null;
			}
		}
		
		//两个时间类型,三个月内
		function _timeBackMonth(orderDateStart,orderDateEnd){
			if(orderDateStart=="" || orderDateEnd==""){
				$.messager.alert("提示","请选择开始结束日期查询！","info");
				return "error";
			}
			if(orderDateStart>orderDateEnd){
				$.messager.alert("提示","开始日期不能大于结束日期！","info");
				return "error";
			}
			var strDate1=orderDateStart.substring(0,10).replace(/-/g, "/ ");
			var strDate2=orderDateEnd.substring(0,10).replace(/-/g, "/ ");
			//去掉毫秒 把-替换成/ 如果不替换转成时间戳类型火狐会出问题
			var   date1   =  Date.parse(strDate1);
			var   date2   =  Date.parse(strDate2);
			var result = (date2-date1)/(24*60*60*1000);
			if(result>93){
				$.messager.alert("Warning","查询日期请限定三个月内!","warning");
				return result;
			}else{
				return null;
			}
		}
		/**
		 * 将from组装成 List<QueryField> 方便后台操作
		 * @param fromName 表单ID
		 * @returns {_FormChangeQueryFields}
		 */
		function _FromChangeQueryFields(fromName){
			var objFrom = getFromData(fromName);
			var fields =[];
			for(name in objFrom){
				if(!is_null(name)&&!is_null(objFrom[name])){
					var objClass = new _QueryFields();
					objClass.fieldName=name;
					objClass.fieldType=null;
					objClass.fieldStringValue=objFrom[name];
//					objClass.fieldValue=objFrom[name];
					objClass.operator=name;
					fields.push(objClass)
				}
			}
			return fields;
		}
		function _QueryFields(){
		    this.fieldName = null;  
		    this.fieldType = null; 
		    this.fieldStringValue = null;
		    this.fieldValue = null;
		    this.operator = null; 
		}
		/**
		 * 区分浏览器，并考虑IE5.5 6 7 8
		 * "FF"：Firefox
		 */
		function myBrowser(){
		    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
		    var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
		    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
		    var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
		    var isSafari = userAgent.indexOf("Safari") > -1; //判断是否Safari浏览器
		    if (isIE) {
		        var IE5 = IE55 = IE6 = IE7 = IE8 = false;
		        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
		        reIE.test(userAgent);
		        var fIEVersion = parseFloat(RegExp["$1"]);
		        IE55 = fIEVersion == 5.5;
		        IE6 = fIEVersion == 6.0;
		        IE7 = fIEVersion == 7.0;
		        IE8 = fIEVersion == 8.0;
		        if (IE55) {
		            return "IE55";
		        }
		        if (IE6) {
		            return "IE6";
		        }
		        if (IE7) {
		            return "IE7";
		        }
		        if (IE8) {
		            return "IE8";
		        }
		    }//isIE end
		    if (isFF) {
		        return "FF";
		    }
		    if (isOpera) {
		        return "Opera";
		    }
		}//myBrowser() end