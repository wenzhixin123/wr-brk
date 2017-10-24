function exportToExcel(appContextPath,jParam){
   	var jsonParam = {title:jParam.title,modelId:jParam.modelId,exportTemplate:jParam.exportTemplate,officeCode:jParam.officeCode,authorParam:jParam.authorParam};
   	var form = document.createElement("form");
     form.setAttribute("method", "post"); 
     form.setAttribute("action", appContextPath+"/servlet/EcExportTemplateServlet");
     var hiddenField = document.createElement("input");
     hiddenField.setAttribute("type", "hidden");
     hiddenField.setAttribute("name", "param");
     hiddenField.setAttribute("value", $.toJSON(jsonParam));
     form.appendChild(hiddenField);
     document.body.appendChild(form);
     form.submit(); 
   }