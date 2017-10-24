/*
PluginDetect v0.7.8 www.pinlady.net/PluginDetect/
www.pinlady.net/PluginDetect/license/
[ beforeInstantiate getInfo getVersion isMinVersion onDetectionDone onWindowLoaded ]
[ AdobeReader PDFreader(OTF & NOTF) ]
*/
var PluginDetect={version:"0.7.8",name:"PluginDetect",handler:function(c,b,a){return function(){c(b,a)
}
},isDefined:function(b){return typeof b!="undefined"
},isArray:function(b){return(/array/i).test(Object.prototype.toString.call(b))
},isFunc:function(b){return typeof b=="function"
},isString:function(b){return typeof b=="string"
},isNum:function(b){return typeof b=="number"
},isStrNum:function(b){return(typeof b=="string"&&(/\d/).test(b))
},getNumRegx:/[\d][\d\.\_,-]*/,splitNumRegx:/[\.\_,-]/g,getNum:function(b,c){var d=this,a=d.isStrNum(b)?(d.isDefined(c)?new RegExp(c):d.getNumRegx).exec(b):null;
return a?a[0]:null
},compareNums:function(h,f,d){var e=this,c,b,a,g=parseInt;
if(e.isStrNum(h)&&e.isStrNum(f)){if(e.isDefined(d)&&d.compareNums){return d.compareNums(h,f)
}c=h.split(e.splitNumRegx);
b=f.split(e.splitNumRegx);
for(a=0;
a<Math.min(c.length,b.length);
a++){if(g(c[a],10)>g(b[a],10)){return 1
}if(g(c[a],10)<g(b[a],10)){return -1
}}}return 0
},formatNum:function(b,c){var d=this,a,e;
if(!d.isStrNum(b)){return null
}if(!d.isNum(c)){c=4
}c--;
e=b.replace(/\s/g,"").split(d.splitNumRegx).concat(["0","0","0","0"]);
for(a=0;
a<4;
a++){if(/^(0+)(.+)$/.test(e[a])){e[a]=RegExp.$2
}if(a>c||!(/\d/).test(e[a])){e[a]="0"
}}return e.slice(0,4).join(",")
},$$hasMimeType:function(a){return function(c){if(!a.isIE&&c){var f,e,b,d=a.isArray(c)?c:(a.isString(c)?[c]:[]);
for(b=0;
b<d.length;
b++){if(a.isString(d[b])&&/[^\s]/.test(d[b])){f=navigator.mimeTypes[d[b]];
e=f?f.enabledPlugin:0;
if(e&&(e.name||e.description)){return f
}}}}return null
}
},findNavPlugin:function(l,e,c){var j=this,h=new RegExp(l,"i"),d=(!j.isDefined(e)||e)?/\d/:0,k=c?new RegExp(c,"i"):0,a=navigator.plugins,g="",f,b,m;
for(f=0;
f<a.length;
f++){m=a[f].description||g;
b=a[f].name||g;
if((h.test(m)&&(!d||d.test(RegExp.leftContext+RegExp.rightContext)))||(h.test(b)&&(!d||d.test(RegExp.leftContext+RegExp.rightContext)))){if(!k||!(k.test(m)||k.test(b))){return a[f]
}}}return null
},getMimeEnabledPlugin:function(k,m,c){var e=this,f,b=new RegExp(m,"i"),h="",g=c?new RegExp(c,"i"):0,a,l,d,j=e.isString(k)?[k]:k;
for(d=0;
d<j.length;
d++){if((f=e.hasMimeType(j[d]))&&(f=f.enabledPlugin)){l=f.description||h;
a=f.name||h;
if(b.test(l)||b.test(a)){if(!g||!(g.test(l)||g.test(a))){return f
}}}}return 0
},getPluginFileVersion:function(f,b){var h=this,e,d,g,a,c=-1;
if(h.OS>2||!f||!f.version||!(e=h.getNum(f.version))){return b
}if(!b){return e
}e=h.formatNum(e);
b=h.formatNum(b);
d=b.split(h.splitNumRegx);
g=e.split(h.splitNumRegx);
for(a=0;
a<d.length;
a++){if(c>-1&&a>c&&d[a]!="0"){return b
}if(g[a]!=d[a]){if(c==-1){c=a
}if(d[a]!="0"){return b
}}}return e
},AXO:window.ActiveXObject,getAXO:function(a){var f=null,d,b=this,c={};
try{f=new b.AXO(a)}catch(d){}return f
},convertFuncs:function(f){var a,g,d,b=/^[\$][\$]/,c=this;
for(a in f){if(b.test(a)){try{g=a.slice(2);
if(g.length>0&&!f[g]){f[g]=f[a](f);
delete f[a]
}}catch(d){}}}},initObj:function(e,b,d){var a,c;
if(e){if(e[b[0]]==1||d){for(a=0;
a<b.length;
a=a+2){e[b[a]]=b[a+1]
}}for(a in e){c=e[a];
if(c&&c[b[0]]==1){this.initObj(c,b)
}}}},initScript:function(){var c=this,a=navigator,e="/",f,i=a.userAgent||"",g=a.vendor||"",b=a.platform||"",h=a.product||"";
c.initObj(c,["$",c]);
for(f in c.Plugins){if(c.Plugins[f]){c.initObj(c.Plugins[f],["$",c,"$$",c.Plugins[f]],1)
}};
c.OS=100;
if(b){var d=["Win",1,"Mac",2,"Linux",3,"FreeBSD",4,"iPhone",21.1,"iPod",21.2,"iPad",21.3,"Win.*CE",22.1,"Win.*Mobile",22.2,"Pocket\\s*PC",22.3,"",100];
for(f=d.length-2;
f>=0;
f=f-2){if(d[f]&&new RegExp(d[f],"i").test(b)){c.OS=d[f+1];
break
}}}c.convertFuncs(c);
c.head=(document.getElementsByTagName("head")[0]||document.getElementsByTagName("body")[0]||document.body||null);
c.isIE=(new Function("return "+e+"*@cc_on!@*"+e+"false"))();
c.verIE=c.isIE&&(/MSIE\s*(\d+\.?\d*)/i).test(i)?parseFloat(RegExp.$1,10):null;
c.ActiveXEnabled=false;
if(c.isIE){var f,j=["Msxml2.XMLHTTP","Msxml2.DOMDocument","Microsoft.XMLDOM","ShockwaveFlash.ShockwaveFlash","TDCCtl.TDCCtl","Shell.UIHelper","Scripting.Dictionary","wmplayer.ocx"];
for(f=0;
f<j.length;
f++){if(c.getAXO(j[f])){c.ActiveXEnabled=true;
break
}}}c.isGecko=(/Gecko/i).test(h)&&(/Gecko\s*\/\s*\d/i).test(i);
c.verGecko=c.isGecko?c.formatNum((/rv\s*\:\s*([\.\,\d]+)/i).test(i)?RegExp.$1:"0.9"):null;
c.isChrome=(/Chrome\s*\/\s*(\d[\d\.]*)/i).test(i);
c.verChrome=c.isChrome?c.formatNum(RegExp.$1):null;
c.isSafari=((/Apple/i).test(g)||(!g&&!c.isChrome))&&(/Safari\s*\/\s*(\d[\d\.]*)/i).test(i);
c.verSafari=c.isSafari&&(/Version\s*\/\s*(\d[\d\.]*)/i).test(i)?c.formatNum(RegExp.$1):null;
c.isOpera=(/Opera\s*[\/]?\s*(\d+\.?\d*)/i).test(i);
c.verOpera=c.isOpera&&((/Version\s*\/\s*(\d+\.?\d*)/i).test(i)||1)?parseFloat(RegExp.$1,10):null;
c.addWinEvent("load",c.handler(c.runWLfuncs,c))},init:function(d){var c=this,b,d,a={status:-3,plugin:0};
if(!c.isString(d)){
return a
}if(d.length==1){c.getVersionDelimiter=d;
return a
}d=d.toLowerCase().replace(/\s/g,"");
b=c.Plugins[d];
if(!b||!b.getVersion){
return a
}a.plugin=b;
if(!c.isDefined(b.installed)){b.installed=null;
b.version=null;
b.version0=null;
b.getVersionDone=null;
b.pluginName=d
}c.garbage=false;
if(c.isIE&&!c.ActiveXEnabled&&d!=="java"){a.status=-2;
return a
}a.status=1;
return a
},fPush:function(b,a){var c=this;
if(c.isArray(a)&&(c.isFunc(b)||(c.isArray(b)&&b.length>0&&c.isFunc(b[0])))){a.push(b)
}},callArray:function(b){var c=this,a;
if(c.isArray(b)){for(a=0;
a<b.length;
a++){if(b[a]===null){return
}c.call(b[a]);
b[a]=null
}}},call:function(c){var b=this,a=b.isArray(c)?c.length:-1;
if(a>0&&b.isFunc(c[0])){
c[0](b,a>1?c[1]:0,a>2?c[2]:0,a>3?c[3]:0)
}else{if(b.isFunc(c)){
c(b)
}}},$$isMinVersion:function(a){return function(h,g,d,c){var e=a.init(h),f,b=-1,j={};
if(e.status<0){return e.status
}f=e.plugin;
g=a.formatNum(a.isNum(g)?g.toString():(a.isStrNum(g)?a.getNum(g):"0"));
if(f.getVersionDone!=1){f.getVersion(g,d,c);
if(f.getVersionDone===null){f.getVersionDone=1
}}a.cleanup();
if(f.installed!==null){b=f.installed<=0.5?f.installed:(f.installed==0.7?1:(f.version===null?0:(a.compareNums(f.version,g,f)>=0?1:-0.1)))
};
return b
}
},getVersionDelimiter:",",$$getVersion:function(a){return function(g,d,c){var e=a.init(g),f,b,h={};
if(e.status<0){return null
};
f=e.plugin;
if(f.getVersionDone!=1){f.getVersion(null,d,c);
if(f.getVersionDone===null){f.getVersionDone=1
}}a.cleanup();
b=(f.version||f.version0);
b=b?b.replace(a.splitNumRegx,a.getVersionDelimiter):b;
return b
}
},$$getInfo:function(a){return function(g,d,c){var b={},e=a.init(g),f,h={};
if(e.status<0){return b
};
f=e.plugin;
if(f.getInfo){if(f.getVersionDone===null){a.isMinVersion?a.isMinVersion(g,"0",d,c):a.getVersion(g,d,c)
}b=f.getInfo()
};
return b
}
},cleanup:function(){
},addWinEvent:function(d,c){var e=this,a=window,b;
if(e.isFunc(c)){if(a.addEventListener){a.addEventListener(d,c,false)
}else{if(a.attachEvent){a.attachEvent("on"+d,c)
}else{b=a["on"+d];
a["on"+d]=e.winHandler(c,b)
}}}},winHandler:function(d,c){return function(){d();
if(typeof c=="function"){c()
}}
},WLfuncs0:[],WLfuncs:[],runWLfuncs:function(a){var b={};
a.winLoaded=true;
a.callArray(a.WLfuncs0);
a.callArray(a.WLfuncs);
if(a.onDoneEmptyDiv){a.onDoneEmptyDiv()
}},winLoaded:false,$$onWindowLoaded:function(a){return function(b){
if(a.winLoaded){
a.call(b)}else{a.fPush(b,a.WLfuncs)
}}
},$$beforeInstantiate:function(a){return function(e,d){var b=a.init(e),c=b.plugin;
if(b.status==-3){return
};
if(!a.isArray(c.BIfuncs)){c.BIfuncs=[]
}a.fPush(d,c.BIfuncs)
}
},$$onDetectionDone:function(a){return function(h,g,c,b){var d=a.init(h),k,e,j={};
if(d.status==-3){return -1
}e=d.plugin;
if(!a.isArray(e.funcs)){e.funcs=[]
}if(e.getVersionDone!=1){k=a.isMinVersion?a.isMinVersion(h,"0",c,b):a.getVersion(h,c,b)
}if(e.installed!=-0.5&&e.installed!=0.5){
a.call(g);
return 1
}if(e.NOTF){a.fPush(g,e.funcs);
return 0
}return 1
}
},div:null,divID:"plugindetect",divWidth:50,pluginSize:1,emptyDiv:function(){var d=this,b,h,c,a,f,g;
if(d.div&&d.div.childNodes){for(b=d.div.childNodes.length-1;
b>=0;
b--){c=d.div.childNodes[b];
if(c&&c.childNodes){for(h=c.childNodes.length-1;
h>=0;
h--){g=c.childNodes[h];
try{c.removeChild(g)
}catch(f){}}}if(c){
try{d.div.removeChild(c)
}catch(f){}}}}if(!d.div){a=document.getElementById(d.divID);
if(a){d.div=a
}}if(d.div&&d.div.parentNode){
try{d.div.parentNode.removeChild(d.div)
}catch(f){}d.div=null
}},DONEfuncs:[],onDoneEmptyDiv:function(){var c=this,a,b;
if(!c.winLoaded){return
}if(c.WLfuncs&&c.WLfuncs.length&&c.WLfuncs[c.WLfuncs.length-1]!==null){return
}for(a in c){b=c[a];
if(b&&b.funcs){if(b.OTF==3){return
}if(b.funcs.length&&b.funcs[b.funcs.length-1]!==null){return
}}}for(a=0;
a<c.DONEfuncs.length;
a++){c.callArray(c.DONEfuncs)
}c.emptyDiv()
},getWidth:function(c){if(c){var a=c.scrollWidth||c.offsetWidth,b=this;
if(b.isNum(a)){return a
}}return -1
},getTagStatus:function(m,g,a,b){var c=this,f,k=m.span,l=c.getWidth(k),h=a.span,j=c.getWidth(h),d=g.span,i=c.getWidth(d);
if(!k||!h||!d||!c.getDOMobj(m)){return -2
}if(j<i||l<0||j<0||i<0||i<=c.pluginSize||c.pluginSize<1){return 0
}if(l>=i){return -1
}try{if(l==c.pluginSize&&(!c.isIE||c.getDOMobj(m).readyState==4)){if(!m.winLoaded&&c.winLoaded){return 1
}if(m.winLoaded&&c.isNum(b)){if(!c.isNum(m.count)){m.count=b
}if(b-m.count>=10){return 1
}}}}catch(f){}return 0
},getDOMobj:function(g,a){var f,d=this,c=g?g.span:0,b=c&&c.firstChild?1:0;
try{if(b&&a){d.div.focus()
}}catch(f){}return b?c.firstChild:null
},setStyle:function(b,g){var f=b.style,a,d,c=this;
if(f&&g){for(a=0;
a<g.length;
a=a+2){try{f[g[a]]=g[a+1]
}catch(d){}}}},insertDivInBody:function(a,i){var h,f=this,b="pd33993399",d=null,j=i?window.top.document:window.document,c="<",g=(j.getElementsByTagName("body")[0]||j.body);
if(!g){try{j.write(c+'div id="'+b+'">o'+c+"/div>");
d=j.getElementById(b)
}catch(h){}}g=(j.getElementsByTagName("body")[0]||j.body);
if(g){if(g.firstChild&&f.isDefined(g.insertBefore)){g.insertBefore(a,g.firstChild)
}else{g.appendChild(a)
}if(d){g.removeChild(d)
}}else{}},insertHTML:function(g,b,h,a,l){var m,n=document,k=this,q,p=n.createElement("span"),o,j,f="<";
var c=["outlineStyle","none","borderStyle","none","padding","0px","margin","0px","visibility","visible"];
var i="outline-style:none;border-style:none;padding:0px;margin:0px;visibility:visible;";
if(!k.isDefined(a)){a=""
}if(k.isString(g)&&(/[^\s]/).test(g)){g=g.toLowerCase().replace(/\s/g,"");
q=f+g+' width="'+k.pluginSize+'" height="'+k.pluginSize+'" ';
q+='style="'+i+'display:inline;" ';
for(o=0;
o<b.length;
o=o+2){if(/[^\s]/.test(b[o+1])){q+=b[o]+'="'+b[o+1]+'" '
}}q+=">";
for(o=0;
o<h.length;
o=o+2){if(/[^\s]/.test(h[o+1])){q+=f+'param name="'+h[o]+'" value="'+h[o+1]+'" />'
}}q+=a+f+"/"+g+">"
}else{q=a
}if(!k.div){j=n.getElementById(k.divID);
if(j){k.div=j
}else{k.div=n.createElement("div");
k.div.id=k.divID
}k.setStyle(k.div,c.concat(["width",k.divWidth+"px","height",(k.pluginSize+3)+"px","fontSize",(k.pluginSize+3)+"px","lineHeight",(k.pluginSize+3)+"px","verticalAlign","baseline","display","block"]));
if(!j){k.setStyle(k.div,["position","absolute","right","0px","top","0px"]);
k.insertDivInBody(k.div)
}}if(k.div&&k.div.parentNode){
if(l&&l.BIfuncs&&l.BIfuncs.length&&l.BIfuncs[l.BIfuncs.length-1]!==null){
k.callArray(l.BIfuncs)};
k.setStyle(p,c.concat(["fontSize",(k.pluginSize+3)+"px","lineHeight",(k.pluginSize+3)+"px","verticalAlign","baseline","display","inline"]));
try{p.innerHTML=q
}catch(m){};
try{k.div.appendChild(p)
}catch(m){};
return{span:p,winLoaded:k.winLoaded,tagName:g,outerHTML:q}
}return{span:null,winLoaded:k.winLoaded,tagName:"",outerHTML:q}
},file:{$:1,any:"fileStorageAny999",valid:"fileStorageValid999",save:function(d,f,c){var b=this,e=b.$,a;
if(d&&e.isDefined(c)){if(!d[b.any]){d[b.any]=[]
}if(!d[b.valid]){d[b.valid]=[]
}d[b.any].push(c);
a=b.split(f,c);
if(a){d[b.valid].push(a)
}}},getValidLength:function(a){return a&&a[this.valid]?a[this.valid].length:0
},getAnyLength:function(a){return a&&a[this.any]?a[this.any].length:0
},getValid:function(c,a){var b=this;
return c&&c[b.valid]?b.get(c[b.valid],a):null
},getAny:function(c,a){var b=this;
return c&&c[b.any]?b.get(c[b.any],a):null
},get:function(d,a){var c=d.length-1,b=this.$.isNum(a)?a:c;
return(b<0||b>c)?null:d[b]
},split:function(g,c){var b=this,e=b.$,f=null,a,d;
g=g?g.replace(".","\\."):"";
d=new RegExp("^(.*[^\\/])("+g+"\\s*)$");
if(e.isString(c)&&d.test(c)){a=(RegExp.$1).split("/");
f={name:a[a.length-1],ext:RegExp.$2,full:c};
a[a.length-1]="";
f.path=a.join("/")
}return f
},z:0},Plugins:{adobereader:{mimeType:"application/pdf",navPluginObj:null,progID:["AcroPDF.PDF","PDF.PdfCtrl"],classID:"clsid:CA8A9780-280D-11CF-A24D-444553540000",INSTALLED:{},pluginHasMimeType:function(d,c,f){var b=this,e=b.$,a;
for(a in d){if(d[a]&&d[a].type&&d[a].type==c){return 1
}}if(e.getMimeEnabledPlugin(c,f)){return 1
}return 0
},getVersion:function(l,j){var g=this,d=g.$,i,f,m,n,b=null,h=null,k=g.mimeType,a,c;
if(d.isString(j)){j=j.replace(/\s/g,"");
if(j){k=j
}}else{j=null
}if(d.isDefined(g.INSTALLED[k])){g.installed=g.INSTALLED[k];
return
}if(!d.isIE){a="Adobe.*PDF.*Plug-?in|Adobe.*Acrobat.*Plug-?in|Adobe.*Reader.*Plug-?in";
if(g.getVersionDone!==0){g.getVersionDone=0;
b=d.getMimeEnabledPlugin(g.mimeType,a);
if(!j){n=b
}if(!b&&d.hasMimeType(g.mimeType)){b=d.findNavPlugin(a,0)
}if(b){g.navPluginObj=b;
h=d.getNum(b.description)||d.getNum(b.name);
h=d.getPluginFileVersion(b,h);
if(!h&&d.OS==1){if(g.pluginHasMimeType(b,"application/vnd.adobe.pdfxml",a)){h="9"
}else{if(g.pluginHasMimeType(b,"application/vnd.adobe.x-mars",a)){h="8"
}}}}}else{h=g.version
}if(!d.isDefined(n)){n=d.getMimeEnabledPlugin(k,a)
}g.installed=n&&h?1:(n?0:(g.navPluginObj?-0.2:-1))
}else{b=d.getAXO(g.progID[0])||d.getAXO(g.progID[1]);
c=/=\s*([\d\.]+)/g;
try{f=(b||d.getDOMobj(d.insertHTML("object",["classid",g.classID],["src",""],"",g))).GetVersions();
for(m=0;
m<5;
m++){if(c.test(f)&&(!h||RegExp.$1>h)){h=RegExp.$1
}}}catch(i){}g.installed=h?1:(b?0:-1)
}if(!g.version){g.version=d.formatNum(h)
}g.INSTALLED[k]=g.installed
}},pdfreader:{mimeType:"application/pdf",progID:["AcroPDF.PDF","PDF.PdfCtrl"],classID:"clsid:CA8A9780-280D-11CF-A24D-444553540000",OTF:null,fileUsed:0,fileEnabled:1,setPluginStatus:function(c,b){var a=this,d=a.$;
a.version=null;
if(a.installed!==0&&a.installed!=1){if(b==3){a.installed=-0.5
}else{a.installed=c?0:(d.isIE?-1.5:-1)
}}if(a.verify&&a.verify.isEnabled()){a.getVersionDone=0
}else{if(a.getVersionDone!=1){a.getVersionDone=b<2&&a.fileEnabled&&a.installed<=-1?0:1
}}},getVersion:function(l,f,b){var g=this,c=g.$,i=false,d,a,j,h=g.NOTF,m=g.doc,k=g.verify;
if(b!==true){b=false
}if(g.getVersionDone===null){g.OTF=0;
if(k){k.begin()
}}if(((c.isGecko&&c.compareNums(c.verGecko,"2,0,0,0")<=0&&c.OS<=4)||(c.isOpera&&c.verOpera<=11&&c.OS<=4)||(c.isChrome&&c.compareNums(c.verChrome,"10,0,0,0")<0&&c.OS<=4)||0)&&!b){g.fileEnabled=0
}c.file.save(g,".pdf",f);
if(g.getVersionDone===0){if(g.OTF<2&&(g.installed<0||b)){if(m.insertHTMLQuery(b)>0){i=true
}g.setPluginStatus(i,g.OTF)
}return
}if(!b){if(!c.isIE){if(c.hasMimeType(g.mimeType)){i=true
}}else{try{if((c.getAXO(g.progID[0])||c.getAXO(g.progID[1])).GetVersions()){i=true
}}catch(j){}}}if(g.OTF<2&&(!i||b)){if(m.insertHTMLQuery(b)>0){i=true
}}g.setPluginStatus(i,g.OTF)
},doc:{$:1,HTML:0,DummyObjTagHTML:0,DummySpanTagHTML:0,queryObject:function(c){var g=this,d=g.$,b=g.$$,a;
if(d.isIE){a=-1;
try{if(d.getDOMobj(g.HTML).GetVersions()){a=1
}}catch(f){}}else{a=d.getTagStatus(g.HTML,g.DummySpanTagHTML,g.DummyObjTagHTML,c)
};
return a
},insertHTMLQuery:function(c){var h=this,d=h.$,f=h.$$,i,b=f.pdf,e=d.file.getValid(f),a="&nbsp;&nbsp;&nbsp;&nbsp;";
if(e){e=e.full
}if(d.isIE){if(c&&(!e||!f.fileEnabled)){return 0
}if(!h.HTML){h.HTML=d.insertHTML("object",["classid",f.classID],["src",c&&e?e:""],a,f)
}if(c){f.fileUsed=1
}}else{if(!e||!f.fileEnabled){return 0
}if(!h.HTML){h.HTML=d.insertHTML("object",["type",f.mimeType,"data",e],["src",e],a,f)
}f.fileUsed=1
}if(f.OTF<2){f.OTF=2
}if(!h.DummyObjTagHTML){h.DummyObjTagHTML=d.insertHTML("object",[],[],a)
}if(!h.DummySpanTagHTML){h.DummySpanTagHTML=d.insertHTML("",[],[],a)
}i=h.queryObject();
if(i!==0){return i
};
var g=f.NOTF;
if(f.OTF<3&&h.HTML&&g){f.OTF=3;
g.onIntervalQuery=d.handler(g.$$onIntervalQuery,g);
if(!d.winLoaded){d.WLfuncs0.push([g.winOnLoadQuery,g])
}setTimeout(g.onIntervalQuery,g.intervalLength)};
return i
}},NOTF:{$:1,count:0,countMax:25,intervalLength:250,$$onIntervalQuery:function(e){var c=e.$,b=e.$$,d=b.doc,a;
if(b.OTF==3){a=d.queryObject(e.count);
if(a>0||a<0||(c.winLoaded&&e.count>e.countMax)){e.queryCompleted(a)
}}e.count++;
if(b.OTF==3){setTimeout(e.onIntervalQuery,e.intervalLength)
}},winOnLoadQuery:function(c,e){var b=e.$$,d=b.doc,a;
if(b.OTF==3){a=d.queryObject(e.count);
e.queryCompleted(a)
}},queryCompleted:function(b){var d=this,c=d.$,a=d.$$;
if(a.OTF==4){return
}a.OTF=4;
a.setPluginStatus(b>0?true:false,a.OTF);
if(a.funcs){
c.callArray(a.funcs)}if(c.onDoneEmptyDiv){c.onDoneEmptyDiv()
}}},getInfo:function(){var b=this,c=b.$,a={OTF:(b.OTF<3?0:(b.OTF==3?1:2)),DummyPDFused:(b.fileUsed?true:false)};
return a
},zz:0},zz:0}};
PluginDetect.initScript();