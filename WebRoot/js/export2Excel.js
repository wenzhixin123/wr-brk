function exportToExcel(appContextPath,jTitle,jParam){
   	var jsonParam = {serviceName:"commonQueryManager", methodName:"exportExcel", 
   		parameters:{title:jTitle, 
   			queryInfo:{	queryType:jParam.queryInfo.queryType, 
   						queryFields:jParam.queryInfo.queryFields, 
   						orderBy:jParam.queryInfo.orderBy, 
   						pagingInfo:jParam.pagingInfo,
   						fieldCodeTypes:jParam.queryInfo.fieldCodeTypes
   					 }, 
   			fieldDefinitions:jParam.fieldDefinitions}};
   	//console.debug($.toJSON(jsonParam));
   	// var url =
	// APP_CONTEXT_PATH+"/JsonFacadeServlet?json_parameters="+encodeURI(jsonToString(jsonParam));
   	// window.location.href = url;
   	
   	var form = document.createElement("form");
     form.setAttribute("method", "post"); 
     form.setAttribute("action", appContextPath+"/JsonFacadeServlet");
     var hiddenField = document.createElement("input");
     hiddenField.setAttribute("type", "hidden");
     hiddenField.setAttribute("name", "json_parameters");
     hiddenField.setAttribute("value", $.toJSON(jsonParam));
     form.appendChild(hiddenField);
     document.body.appendChild(form);
     form.submit(); 
   }
function QueryField(){
	var fieldName;
	var fieldType;
	var fieldStringValue;
	var fieldValue;
	var operator;
}