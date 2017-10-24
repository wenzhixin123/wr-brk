<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@ page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<%
	String path = request.getContextPath();
	SessionContextUserEntity scue = SessionContextUserEntity
			.currentUser();
	String newsUuid = request.getParameter("uuid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
  <head>
    <title>信息发布管理</title>
    <jsp:include page="../../common/imp_dwr.jsp"></jsp:include>
	<!-- jquery easyui -->    
	<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
	<script type="text/javascript" src="<%=path%>/jsp/news/ueditor/editor_config.js"></script>
	<script type="text/javascript" src="<%=path%>/jsp/news/ueditor/editor_all.js"></script>
	<script type="text/javascript" src="<%=path%>/js/util/myUtil.js"></script>
	<link rel="stylesheet" href="<%=path%>/jsp/news/ueditor/themes/default/ueditor.css"/>
    <style type="text/css">
        textarea{
            float: left;
            width:300px;
            height: 400px;
            margin-left: 20px;
        }
        .clear{
            clear: both;
        }
    </style>
     <script type="text/javascript">
    	$(function(){
    		initDate(["orderDateStart",""]);
    		var newsUuid ='<%=newsUuid%>';
    		if(newsUuid && newsUuid!='null'){
    			SysNewsManager.queryContentToUuid(newsUuid,function(result){
    			   if(result){
    			        setInitNews(result);     //编辑的时候赋值
    				}
    			});
    		}else{
    			$("#status").val('<%=CommonUtil.Pending%>');
    		}
    	});
     
     </script>
    <script type="text/javascript">
        var sysUuid;
		
		//新增文章
		function newContent(){
			$.messager.confirm('确定框', '将会清空之前填写的数据，确定要新增吗?', function(r){
				if (r){
					window.location.href="sysNews.jsp";
				}
			});
		}
		
		//保存文章
		function getContent(){
		  var newsUuid ='<%=newsUuid%>';
		  var validate = $("#queryForm").form("validate");
		  if(!validate){
		  		alert("验证失败:请输入必填数据!");
		  		return ;
		  }
		    var systemNewsEntity =  new Object();  //新建一个对象用来存放所有页面信息
		    var chooseType = $("#chooseType").combobox('getValue');
		    if(chooseType=="normal"){
		    	var urlAddress = $("#urlAddress").val();
		    	urlAddress = escape(encodeURIComponent(urlAddress));
		    }
	        var arr = [];
	        if(chooseType=="detail"){
		        arr.push(editor.getContent());
	        }
	        if(newsUuid!=''||newsUuid!=null){
	        	systemNewsEntity.sysNewsUuid =newsUuid ;
	        }else{
	        	systemNewsEntity.sysNewsUuid =sysUuid ;
	        }
	        systemNewsEntity.seqNo =$("#seqNo").val();
	        systemNewsEntity.title = $("#title").val();
	        systemNewsEntity.dateWork =$("#dateWork").val();
	        systemNewsEntity.newsType = $("#newsType").combobox('getValue');
	        systemNewsEntity.ifFiles = $("#ifFiles").val();
	        systemNewsEntity.functionary = "<%=scue.getUserId()%>";
	        systemNewsEntity.remark =  $("#remark").val();
	        systemNewsEntity.status = $("#status").val();
	        systemNewsEntity.recVer = $("#recVer").val();
	        systemNewsEntity.rowState = $("#rowState").val();
	        systemNewsEntity.basBlobUuid = $("#basBlobUuid").val();    	        			          
	        var jsonresult = encodeURI($.toJSON(systemNewsEntity));
	     	$.ajax({
	   			type: "POST",
			    url:'<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=saveSystemNews',
			    data:'jsonresult='+jsonresult+"&syscCntent="+escape(encodeURIComponent(arr))+"&urlAddress="+urlAddress,
			    dataType:'json',
			    success: function(date){
			    	if(date.object!=""||date.object!=null){
				        $("#sysNewsUuid").val(date.object.sysNewsUuid);
				    	$("#rowState").val('<%=CommonUtil.ROW_STATE_MODIFIED%>');
				    	sysUuid=date.object.sysNewsUuid;
			    		alert("保存成功!");
			    		window.location.href="sysNews.jsp?uuid="+"";
			    	}else{
			    		alert("保存失败!");
			    	}
			   	}
			});
	    }
		
		//附件上传
		function upLoadFile(){
		   		var basBlobUuid = $("#basBlobUuid").val();
				var ld=[];
				var sysNewsUuid = $("#sysNewsUuid").val();
				if(sysNewsUuid!='' && sysNewsUuid!=null){
					ld.push(sysNewsUuid);   
				}else{
					ld.push("sysUuid");
				}
				if(basBlobUuid!='' && basBlobUuid!=null){
			   	   ld.push(basBlobUuid);	
				}else{
				   ld.push("basBlobUuid");
				}
				var importFile=$("#upLoadFile").sinotrans_Import({
					path:'<%=path%>',
					fileType:'.xls,.xlsx,.txt,.doc,.docx,.ppt,.pptx,.png,.gif,.rar,.zip,.jpg,',
					businessType:'IF_FILES',
					modelIds:ld.join(","),
					functionName:'callback1'
				});
				importFile.open();
		}
	    //上传后的回调函数
		function callback1(data){
			if(data.result){
			    $("#_ifFiles").html(data.object.typeDesc);
			    sysUuid = data.object.preDataUuid;
			    $("#basBlobUuid").val(data.object.basBlobUuid);
				alert(data.msg);
			}else{
				alert(data.error);	
			}
		}
		
		//编辑的时候赋值
		function setInitNews(result){
		    $("#title").val(result.title);
		    $("#seqNo").val(result.seqNo);
		    $("#dateWork").val(result.dateWork);
		    var DateD=new Date(result.beginDate);
		    var DateE=new Date(result.endDate);
		   // alert(DateD);
	        $('#orderDateStart').datebox('setValue',DateD.getYear()+1900+"-"+(DateD.getMonth()+1)+"-"+DateD.getDate());
	        if(result.endDate!=null){
	         $('#orderDateEnd').datebox('setValue',DateE.getYear()+1900+"-"+(DateE.getMonth()+1)+"-"+DateE.getDate());	
	        }
		    $("#newsType").combobox('setValue',result.newsType);
		    $("#ifFiles").val(result.ifFiles);
		    $("#chooseType").combobox('disable');
		    if(result.basBlobUuid){
			    $("#basBlobUuid").val(result.basBlobUuid);
			    BasBlobManager.get(result.basBlobUuid, function(model){
			        if(model.typeDesc){
			        	$("#_ifFiles").html(model.typeDesc);
			        }
			    });
		    }else{
		    	$("#_ifFiles").html("未上传附件!");
		    }
		    if(result.urlAddress){
		    	$("#chooseType").combobox('setValue', "normal");
				editor.destroy();
				$("#urlAddress").show();
				$("#urlAddressRemart").html("文章地址:");		    	
		    	$("#urlAddress").val(result.urlAddress);
		    }else{
		    	if(result.strContent){
					editor.setContent(result.strContent);
				}
		    }
		    $("#status").val(result.status);
		    $("#remark").val(result.remark);
		    $("#sysNewsUuid").val(result.sysNewsUuid);
		}
    </script>
    <script type="text/javascript">
       $(function(){
        $("#urlAddress").hide();
        $("#urlAddressRemart").html("文章内容:");								
        //文章分类
		$("#chooseType").combobox({
					onSelect : function(record){
						if(record.value=="normal"){
							editor.destroy();
							$("#urlAddress").show();
							$("#urlAddressRemart").html("文章地址:");
						}
						if(record.value=="detail"){
							editor.render('editor');
							$("#urlAddress").hide();
							$("#urlAddressRemart").html("文章内容:");							
						}
					}
				});
				
		//文章类型
		$("#newsType").combobox({
			url:'<%=path%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.OPTION_SYSTEM_CODE%>&q=<%=CommonUtil.OPTION_SYSTEM_CODE_SYS_NEWS_TYPE%>',
			valueField:'key',
			textField:'value',
			panelHeight:'auto',
		    required:true,
		    editable:false,
		    onSelect:function(record){
		    }
		});
		
       });
    </script>
</head>
<body>
      <div style=" width:900px; margin:0 auto; background-color:aliceblue;">
		<div id="formContainer" >
			<form id="queryForm" >
				<table id="searForm" width="900px" style="margin:0 auto; ">
				
					<tr>
						<td align="left">
							文章标题:
							<input id="title" name="title" class="easyui-validatebox" style="width:500px" required="true"/>
						</td>
						
						<td align="right">
							序号:
							<input id="seqNo" name="seqNo"  class="easyui-validatebox" style="width:40px" />
						</td>
					</tr>
					
					<tr>
						<td align="left">
							文章类型:
							<input id="newsType" name="newsType" style="width:84px;" class="easyui-combobox" />
							文章分类:
							<select id="chooseType" class="easyui-combobox"  panelHeight="auto" editable="false" style="width: 50px">
								<option value="detail" selected>详细</option>
								<option value="normal">普通</option>
							</select>
							附件:
					      	<a id="_ifFiles" name="_ifFiles" />
						</td>
					   <td align="right">
							<a id="upLoadFile" name="upLoadFile" class="easyui-linkbutton" onclick="upLoadFile()">附件上传</a>
						</td>
					   			
					</tr>
                    
					<tr >
					    <td align="left">
							<a id="urlAddressRemart"></a>
							<input id="urlAddress" name="urlAddress" style="width:400px" />
						</td>
					</tr>
                        
					<tr style="display: none;">
						<td><input type="hidden" id="sysNewsUuid" name="sysNewsUuid"/></td>
						<td><input type="hidden" id="functionary" name="functionary"/></td>
						<td><input type="hidden" id="remark" name="remark"/></td>
						<td><input type="hidden" id="dateWork" name="dateWork"/></td>
						<td><input type="hidden" id="ifFiles" name="ifFiles"/></td>
						<td><input type="hidden" id="status" name="status"/></td>
						<td><input type="hidden" id="recVer" name="recVer"/></td>
						<td><input type="hidden" id="rowState" name="rowState" value="<%=CommonUtil.ROW_STATE_ADDED%>"/></td>
						<td><input type="hidden" id="rs_rowState" name="rs_rowState" value="<%=CommonUtil.ROW_STATE_ADDED%>"/></td>
						<td><input type="hidden" id="basBlobUuid" name="basBlobUuid" /></td>
					</tr>
				</table>
			</form>
		
		</div>
		<div id="editor" style="width:900px;  margin: 0 auto;">
			 							
		</div>
		<div style="width:900px;  margin: 0 auto;" >
			<a type="button" id="getContent"  onclick="getContent()" iconCls="icon-save" class="easyui-linkbutton">保存</a>
			<a type="button" id="getContent"  onclick="newContent()" iconCls="icon-add" class="easyui-linkbutton">新增</a>
		</div>
 </div>

<script type="text/javascript">
	
	//设置ueditor的基本属性
	var editorOption = {
	   imagePath:'<%=path%>'+"/jsp/news/ueditor/server/upload/",
	   UEDITOR_HOME_URL:'<%=path%>'+"/jsp/news/ueditor/",
		toolbars:[    //设置ueditor的工具
            [ 'Source', '|', 'Undo', 'Redo', '|',
                'Bold', 'Italic', 'Underline', 'StrikeThrough', 'Superscript', 'Subscript', 'RemoveFormat', 'FormatMatch', '|',
                'BlockQuote', '|', 'PastePlain', '|', 'ForeColor', 'BackColor', 'InsertOrderedList', 'InsertUnorderedList', '|', 'CustomStyle',
                'Paragraph', 'RowSpacing', 'LineHeight', 'FontFamily', 'FontSize', '|',
                'DirectionalityLtr', 'DirectionalityRtl', '|', '', 'Indent', '|',
                'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyJustify', '|',
                'Link', 'Unlink', 'Anchor', '|', 'ImageNone', 'ImageLeft', 'ImageRight', 'ImageCenter', '|', 'InsertImage', 'Emotion', 'InsertVideo', 'Map', 'GMap', 'InsertFrame', 'PageBreak', 'HighlightCode', '|',
                'Horizontal', 'Date', 'Time', 'Spechars', '|',
                'InsertTable', 'DeleteTable', 'InsertParagraphBeforeTable', 'InsertRow', 'DeleteRow', 'InsertCol', 'DeleteCol', 'MergeCells', 'MergeRight', 'MergeDown', 'SplittoCells', 'SplittoRows', 'SplittoCols', '|',
                'SelectAll', 'SearchReplace', 'Print', 'Preview', 'CheckImage', 'Help']
        ],
		minFrameHeight:320, //最小高度
        autoHeightEnabled:false, //是否自动长高
        elementPathEnabled:false, //是否启用elementPath
        highlightJsUrl:"../../jsp/news/ueditor/third-party/SyntaxHighlighter/shCore.js",
        highlightCssUrl:"../../jsp/news/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css",
        textarea:'textarea',
        initialContent:''	//初始化的值
       };
	
    var editor = new baidu.editor.ui.Editor(editorOption);
    editor.render('editor');
	editor.addListener("selectionchange",function(){
	    var state = editor.queryCommandState("source");
	    var btndiv = document.getElementById("btns");
	    if(btndiv){
	        if(state){
	            btndiv.style.display = "none";
	        }else{
	            btndiv.style.display = "";
	        }
	    }

});

</script>
</body>
  
</html>