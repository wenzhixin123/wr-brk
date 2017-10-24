//<!--
//===========================================//
//========== 常量定义  ======================//
//===========================================//
//------设备类型------//
var NETCAPKI_DEVICETYPE_ANY	            = -1;
var NETCAPKI_DEVICETYPE_SOFTWARE	    = 0;
var NETCAPKI_DEVICETYPE_EKEY_EKPKXC_V2	= 2;
var NETCAPKI_DEVICETYPE_EPASS3000	    = 3;
var NETCAPKI_DEVICETYPE_HAIKEY	        = 4;
var NETCAPKI_DEVICETYPE_EPASS3003	    = 5;
var NETCAPKI_DEVICETYPE_EKEY_EKPKXC_V3	= 6;
var NETCAPKI_DEVICETYPE_ETAX	        = 30;
var NETCAPKI_DEVICETYPE_HWETAX	        = 31;
var NETCAPKI_DEVICETYPE_HAIKEY_SM2	    = 33;
var NETCAPKI_DEVICETYPE_SJY05B	        = 100;
var NETCAPKI_DEVICETYPE_SJY03B	        = 101;

//-------设备标识-------//
var NETCAPKI_DEVICEFLAG_SILENT					= 1;	//如果这一位设置，则不会显示UI
var NETCAPKI_DEVICEFLAG_CACHE_PIN_IN_PROCESS	= 0;	//在进程中缓存PIN码
var NETCAPKI_DEVICEFLAG_CACHE_PIN_IN_HANDLE		= 2;	//在句柄中缓存PIN码
var NETCAPKI_DEVICEFLAG_NOT_CACHE_PIN			= 4;	//不缓存PIN码

//------证书库类型------//
var NETCAPKI_CERT_STORE_TYPE_CURRENT_USER  = 0; //当前用户
var NETCAPKI_CERT_STORE_TYPE_LOCAL_MACHINE = 1; //本机
var NETCAPKI_CERT_STORE_TYPE_MEMORY	       = 2; //内存中的临时证书库，这时会忽略证书库的名称

//------证书库名称------//
var NETCAPKI_CERT_STORE_NAME_MY         = "my";
var NETCAPKI_CERT_STORE_NAME_OTHERS     = "others";
var NETCAPKI_CERT_STORE_NAME_CA         = "ca";
var NETCAPKI_CERT_STORE_NAME_ROOT       = "root";

//------------证书用途---------------//
var NETCAPKI_CERT_PURPOSE_ALL		=0; //所有
var NETCAPKI_CERT_PURPOSE_ENCRYPT	=1;	//加密
var NETCAPKI_CERT_PURPOSE_SIGN 		=2; //签名

//------------证书颁发机构类型---------------//
var NETCAPKI_NETCA_ALL		=0; //所有证书
var NETCAPKI_NETCA_NETCACERT=1; //网证通证书
var NETCAPKI_NETCA_OTHERCERT=2; //非网证通证书

//---------证书基本信息-------------//
var NETCAPKI_CERT_THUMBPRINT						= 0; //证书微缩图
var NETCAPKI_CERT_SERIALNUMBER						= 1; //证书序列号
var NETCAPKI_CERT_SUBJECT							= 2; //证书主题
var NETCAPKI_CERT_ISSUER							= 3; //证书颁发者
var NETCAPKI_CERT_VALIDFROMDATE 					= 4; //证书有效期起
var NETCAPKI_CERT_VALIDTODATE						= 5; //证书有效期止
var NETCAPKI_CERT_KEYUSAGE 							= 6; //密钥用法
var NETCAPKI_CERT_PUBLICKEYALGO 					= 7; //证书公钥算法
var NETCAPKI_CERT_PUBLICKEYBITS 					= 8; //证书公钥长度
//------------证书属性---------------//
var NETCAPKI_CERT_ATTRIBUTE_ISSUER_DISPLAY_NAME 	= 9;//颁发者显示名，字符串
var NETCAPKI_CERT_ATTRIBUTE_ISSUER_C 				= 10;//颁发者的C，字符串
var NETCAPKI_CERT_ATTRIBUTE_ISSUER_O 				= 11;//颁发者的O，字符串
var NETCAPKI_CERT_ATTRIBUTE_ISSUER_OU 				= 12;//颁发者的OU，字符串
var NETCAPKI_CERT_ATTRIBUTE_ISSUER_CN 				= 13;//颁发者的CN，字符串
var NETCAPKI_CERT_ATTRIBUTE_ISSUER_EMAIL 			= 14;//颁发者的Email，字符串
var NETCAPKI_CERT_ATTRIBUTE_SUBJECT_DISPLAY_NAME 	= 16;//主体显示名，字符串
var NETCAPKI_CERT_ATTRIBUTE_SUBJECT_C				= 17;//主体的C，字符串
var NETCAPKI_CERT_ATTRIBUTE_SUBJECT_O 				= 18;//主体的O，字符串
var NETCAPKI_CERT_ATTRIBUTE_SUBJECT_OU 				= 19;//主体的OU，字符串
var NETCAPKI_CERT_ATTRIBUTE_SUBJECT_CN				= 20;//主体的CN，字符串
var NETCAPKI_CERT_ATTRIBUTE_SUBJECT_EMAIL			= 21;//主体的Email，字符串
var NETCAPKI_CERT_ATTRIBUTE_EX_FRIENDLY_NAME 		= 22;//好记的名字，字符串
var NETCAPKI_CERT_ATTRIBUTE_EX_NAME 				= 23;//证书拥有者名称，字符串
var NETCAPKI_CERT_ATTRIBUTE_EX_ORGANIZATION 		= 24;//证书拥有者所在的单位，字符串
var NETCAPKI_CERT_ATTRIBUTE_EX_DEPARTMENT 			= 25;//证书拥有者所在的部门，字符串
var NETCAPKI_CERT_ATTRIBUTE_EX_EMAIL 				= 26;//证书拥有者的EMail，字符串
var NETCAPKI_CERT_ATTRIBUTE_PREVCERT_THUMBPRINT 	= 29;//更新前的证书的姆印，字符串
var NETCAPKI_CERT_ATTRIBUTE_UPN 					= 36;//UPN，字符串
var NETCAPKI_CERT_ATTRIBUTE_ISSUER_ST 				= 37;//颁发者的ST，字符串
var NETCAPKI_CERT_ATTRIBUTE_ISSUER_L 				= 38;//颁发者的L，字符串
var NETCAPKI_CERT_ATTRIBUTE_SUBJECT_ST 				= 39;//主体的ST，字符串
var NETCAPKI_CERT_ATTRIBUTE_SUBJECT_L 				= 40;//主体的L，字符串
var NETCAPKI_CERT_ATTRIBUTE_EX_DEVICE_TYPE			= 41;//设备类型，整数

//------Keypair Type------//
var NETCAPKI_KEYPAIR_TYPE_SIGN = 1;//	加密密钥对
var NETCAPKI_KEYPAIR_TYPE_ENC = 2;//	签名密钥对

//------Keypair Algo------//
var NETCAPKI_KEYPAIR_ALGO_RSA = 1;//	RSA密钥对
var NETCAPKI_KEYPAIR_ALGO_ECC = 4;//	ECC密钥对，SM2的也属于这种类型

//------签名算法------// 
var NETCAPKI_ALGORITHM_MD5WITHRSA       =1;
var NETCAPKI_ALGORITHM_SHA1WITHRSA	    =2;
var NETCAPKI_ALGORITHM_SHA224WITHRSA	=3;
var NETCAPKI_ALGORITHM_SHA256WITHRSA    =4;
var NETCAPKI_ALGORITHM_SHA384WITHRSA	=5;
var NETCAPKI_ALGORITHM_SHA512WITHRSA    =6;
var NETCAPKI_ALGORITHM_SM3WITHSM2	    =25;
var NETCAPKI_ALGORITHM_SM3WITHRSA	    =31;

//------Hash算法------// 
var NETCAPKI_ALGORITHM_MD5	    =4096;  //MD5算法
var NETCAPKI_ALGORITHM_SHA1  	=8192;  //SHA1算法
var NETCAPKI_ALGORITHM_SHA224	=12288; //SHA224算法
var NETCAPKI_ALGORITHM_SHA256	=16384; //SHA256算法
var NETCAPKI_ALGORITHM_SHA384	=20480; //SHA384算法
var NETCAPKI_ALGORITHM_SHA512	=24576; //SHA512算法
var NETCAPKI_ALGORITHM_SM3	    =28672; //SM3算法

//------CMS编码方式------// 
var NETCAPKI_CMS_ENCODE_DER					= 1;  //	DER编码，返回值是ByteArray类
var NETCAPKI_CMS_ENCODE_BASE64 				= 2;  //	DER编码后再经过不分行的BASE64编码，返回值是字符串
var NETCAPKI_CMS_ENCODE_BASE64_MULTILINE	= 3;  //	DER编码后再经过分行的BASE64编码，返回值是字符串

//------密钥用法------// 
var NETCAPKI_KEYUSAGE_DIGITALSIGNATURE      = 1;	//数字签名
var NETCAPKI_KEYUSAGE_CONTENTCOMMITMENT     = 2;	//不可否认
var NETCAPKI_KEYUSAGE_KEYENCIPHERMENT       = 4;	//加密密钥
var NETCAPKI_KEYUSAGE_DATAENCIPHERMENT      = 8;	//加密数据
var NETCAPKI_KEYUSAGE_KEYAGRESSMENT         = 16;	//密钥协商
var NETCAPKI_KEYUSAGE_KEYCERTSIGN           = 32;	//签证书
var NETCAPKI_KEYUSAGE_CRLSIGN               = 64;	//签CRL
var NETCAPKI_KEYUSAGE_ENCIPHERONLY          = 128;	//密钥协商中只加密
var NETCAPKI_KEYUSAGE_DECIPHERONLY          = 256;	//密钥协商中只解密

//------时间戳响应状态------//
var NETCAPKI_TIMESTAMP_RESP_STATUS_BADTSACERT  			 = -2; //时间戳签名证书不对
var NETCAPKI_TIMESTAMP_RESP_STATUS_BADRESP     			 = -1; //错误的响应
var NETCAPKI_TIMESTAMP_RESP_STATUS_GRANTED     			 = 0;  //正常
var NETCAPKI_TIMESTAMP_RESP_STATUS_GRANTEDWITHMODS		 = 1;  //修改过的授权
var NETCAPKI_TIMESTAMP_RESP_STATUS_REJECTION 			 = 2;  //被拒绝
var NETCAPKI_TIMESTAMP_RESP_STATUS_WAITING 				 = 3;  //等待
var NETCAPKI_TIMESTAMP_RESP_STATUS_REVOCATIONWARNING     = 4;  //停止服务的警告
var NETCAPKI_TIMESTAMP_RESP_STATUS_REVOCATIONNOTIFICATION = 5; //停止服务的通知

//------错误定义------//
var NETCAPKI_ERROR_PARAMETER = "参数错误";

//------全局变量------//
var utilObj;
var deviceSetObj;
var deviceObj;
var storeObj;
var certObj;
var signObj;

//============ 检查类  ======================//
//===========================================//
/* ==========================================
 *@date   2013-03-11
 *描述：判断控件是否安装成功
 *@return：返回浏览器类型和版本
 * ==========================================
 */
function isPKIInstalled()
{	
	var pki = document.getElementById("pki");

	try
	{		
		utilObj = pki.CreateDeviceObject();
		if(typeof(utilObj).toString().indexOf("object") != -1) 
		{	
			return true; 
		}
		else 
		{	
			return false;
		}	
	}
	catch (e)
	{   
		alert(e);
		return false;
	}
}

/* ==========================================
 *@date   2013-03-11
 *描述：获取所有设备, type, flag可以为null
 *@return：返回所有设备
 * ==========================================
 */
function getAllDevices(type, flag)
{
	var deviceSet;
	if(type==null || type=="")
	{
		//默认值为-1
		type = -1;	
	}
	
	if(flag==null || flag=="")
	{
		// 默认在进程间缓存PIN
		flag = NETCAPKI_DEVICEFLAG_CACHE_PIN_IN_PROCESS;	
	}
	
	try
	{
		pki = document.getElementById("pki");
		deviceSet=pki.GetAllDevices(type, flag); 
	}
	catch (e)
	{   
		alert(e);
 		return false;
	} 

	return deviceSet;
}

/* ==========================================
 *@date   2013-03-11
 *描述：获取所有设备, type, flag可以为null
 *@return：返回所有设备
 * ==========================================
 */
function getAllDevicesCount(type, flag)
{	
	var count;
	var deviceSet;
	if(type==null || type=="")
	{
		//默认值为-1
		type = -1;	
	}
	
	if(flag==null || flag=="")
	{
		// 默认在进程间缓存PIN
		flag = NETCAPKI_DEVICEFLAG_CACHE_PIN_IN_PROCESS;	
	}
	
	try
	{
		deviceSet=getAllDevices(type, flag); 
		count = deviceSet.Count;
	}
	catch (e)
	{   
		alert(e);
 		return false;
	} 

	return count;
}

 
/* ==========================================
 *@date   2013-03-14
 *描述：获取第index个设备, index>=1
 *@return：返回设备
 * ==========================================
 */
function getDeviceFromDevicesSet(index)
{
	var device;
	var deviceSet;
	if(index==null || index<1)
	{
		return NETCAPKI_ERROR_PARAMETER;
	}
	 
	try
	{
		deviceSet = getAllDevices(-1,0); 
		device = deviceSet.GetDevice(index);	
		//alert(device.Label);
	}
	catch (e)
	{   
		alert(e);
		return null;
	} 
	return device;
}

/* ================================================================================
 *@date   2013-03-14
 *描述：根据设备类型type，设备序列号sn和标识获取设备flag
 *		用于是否能查找到某设备。
 *@return：返回true OR false 
 *说明：该方法是可以直接方法获取到的设备的，此处是为了兼容只返回是否获取到了设备
 * ================================================================================
 */
function getDevice(type,sn,flag)
{
	var isOK = false;
	
	if(type==null || type=="")
	{
		//默认值为-1
		type = -1;	
	}
	
	if(sn==null || sn=="")
	{
		// 参数错误
		return NETCAPKI_ERROR_PARAMETER;
	}
	
	if(flag==null || flag=="")
	{
		// 默认在进程间缓存PIN
		flag = NETCAPKI_DEVICEFLAG_CACHE_PIN_IN_PROCESS;	
	}
	
	try
	{	deviceObj = null;
		pki = document.getElementById("pki");
		deviceObj = pki.CreateDeviceObject(type,sn,flag);
		if(deviceObj!=null)
		{
			isOK = true;
		}
	}
	catch (e)
	{   
		alert(e);
	} 
	
	return isOK;
}

/* ==========================================
 *@date   2013-03-11
 *描述：从证书库中获取符合条件的所有证书
 *@return：certs
 * ==========================================
 */
function getStoreCerts(storeLocation, storeName, certType, caType)
{
	var count = 0;
	var certArray = new Array();
	
	/*
	if(storeLocation==null || storeLocation=="")
	{
		//默认为当前有户
		storeLocation = NETCAPKI_CERT_STORE_TYPE_CURRENT_USER;
	}
	else if(storeLocation<0 || storeLocation>2)
	{
		return NETCAPKI_ERROR_PARAMETER;
	}
	*/
 	try
    {
		var pki = document.getElementById("pki");
		storeObj = pki.OpenStore(storeLocation, storeName); 
		if(storeObj!=null)
		{
			count = storeObj.GetCertificateCount(); 
			if(count <= 0)
			{
				storeObj.Close();
				storeObj = null;
				alert("没有证书!");
				return null;
			}
		}		
		
		for(i=1; i<count+1; i++)
		{
			var cert;
			var issuer;
			cert = storeObj.GetCertificate(i);
			issuer_o = cert.GetStringInfo(NETCAPKI_CERT_ATTRIBUTE_ISSUER_O);
		
			if(certType == NETCAPKI_CERT_PURPOSE_ALL) 
			{
				if((caType == NETCAPKI_NETCA_ALL)
					|| ((caType == NETCAPKI_NETCA_NETCACERT) && ((issuer_o.toUpperCase()).indexOf("NETCA") >= 0))
					|| ((caType == NETCAPKI_NETCA_OTHERCERT) && ((issuer_o.toUpperCase()).indexOf("NETCA") < 0)) )
				{
					certArray.push(cert);
				}
			}// 第一个certType判断结束
			else if(certType == NETCAPKI_CERT_PURPOSE_SIGN)
			{
				if((caType == NETCAPKI_NETCA_ALL)
					|| ((caType == NETCAPKI_NETCA_NETCACERT) && ((issuer_o.toUpperCase()).indexOf("NETCA") >= 0))
					|| ((caType == NETCAPKI_NETCA_OTHERCERT) && ((issuer_o.toUpperCase()).indexOf("NETCA") < 0)) )
				{
					if((cert.KeyUsage == (NETCAPKI_KEYUSAGE_DIGITALSIGNATURE | NETCAPKI_KEYUSAGE_CONTENTCOMMITMENT)) 
						|| (cert.KeyUsage == NETCAPKI_KEYUSAGE_CONTENTCOMMITMENT) || (cert.KeyUsage ==NETCAPKI_KEYUSAGE_DIGITALSIGNATURE) )
					{
						certArray.push(cert);
					}
				}    
			}//第二个certType判断结束
			else if(certType == NETCAPKI_CERT_PURPOSE_ENCRYPT)
			{
				if((caType == NETCAPKI_NETCA_ALL)
					|| ((caType == NETCAPKI_NETCA_NETCACERT) && ((issuer_o.toUpperCase()).indexOf("NETCA") >= 0))
					|| ((caType == NETCAPKI_NETCA_OTHERCERT) && ((issuer_o.toUpperCase()).indexOf("NETCA") < 0)) )
				{
					if((cert.KeyUsage == (NETCAPKI_KEYUSAGE_KEYENCIPHERMENT | NETCAPKI_KEYUSAGE_DATAENCIPHERMENT)) 
						|| (cert.KeyUsage == NETCAPKI_KEYUSAGE_DATAENCIPHERMENT) 
						|| (cert.KeyUsage ==NETCAPKI_KEYUSAGE_KEYENCIPHERMENT) )
					{
						certArray.push(cert);
					}
				}
			}// 第三个certType判断结束
		}// End for
	
		storeObj = null;
		return certArray;
	}
    catch (e)
    {
        alert(e);
		return null;
    }
}

/* ==========================================
 *@date   2013-03-14
 *描述： 弹出所有符合条件的证书供选择
 *@return：返回选择的证书
 * ==========================================
 */
function chooseStoreCerts(storeLocation, storeName, certType, caType)
{
 	var cert;
	var count;
	var filter="InValidity='True'";
	
	/*
	if(storeLocation==null || storeLocation=="")
	{
		//默认为当前有户
		storeLocation = NETCAPKI_CERT_STORE_TYPE_CURRENT_USER;
	}
	else if(storeLocation<0 || storeLocation>2)
	{
		return NETCAPKI_ERROR_PARAMETER;
	}
	*/
	
	try
    {
		var pki = document.getElementById("pki");
		storeObj = pki.OpenStore(storeLocation, storeName);  
		if(storeObj!=null)
		{
			count = storeObj.GetCertificateCount(); 
			if(count <= 0)
			{
				storeObj.Close();
				storeObj = null;
				alert("没有证书!");
				return null;
			}
		}		
		
		if(certType == NETCAPKI_CERT_PURPOSE_ALL) 
		{
			if (caType==NETCAPKI_NETCA_ALL)
			{
				//无过滤条件
			}
			else  if (caType==NETCAPKI_NETCA_NETCACERT)
			{
				filter+="&&(IssuerO='NETCA Certificate Authority'||IssuerO='NETCA'||IssuerO='netca')";	
			}
			else  if (caType==NETCAPKI_NETCA_OTHERCERT)
			{
				filter+="&&!(IssuerO='NETCA Certificate Authority'||IssuerO='NETCA'||IssuerO='netca')";
			}
			
		}// 第一个certType判断结束 
		else if(certType == NETCAPKI_CERT_PURPOSE_SIGN)
		{
			if (caType==NETCAPKI_NETCA_ALL)
			{
				filter+="&&CertType='Signature'";	
			}
			else  if (caType==NETCAPKI_NETCA_NETCACERT)
			{
				filter+="&&(IssuerO='NETCA Certificate Authority'||IssuerO='NETCA'||IssuerO='netca')&&CertType='Signature'";	
			}
			else  if (caType==NETCAPKI_NETCA_OTHERCERT)
			{
				filter+="&&!(IssuerO='NETCA Certificate Authority'||IssuerO='NETCA'||IssuerO='netca')&&CertType='Signature'";
			} 
		}//第二个certType判断结束
		else if(certType == NETCAPKI_CERT_PURPOSE_ENCRYPT)
		{
			if (caType==NETCAPKI_NETCA_ALL)
			{
				 filter+="&&CertType='Encrypt'";
			}
			else  if (caType==NETCAPKI_NETCA_NETCACERT)
			{
				filter+="&&(IssuerO='NETCA Certificate Authority'||IssuerO='NETCA'||IssuerO='netca')&&CertType='Encrypt'";
			}
			else  if (caType==NETCAPKI_NETCA_OTHERCERT)
			{
				filter+="&&!(IssuerO='NETCA Certificate Authority'||IssuerO='NETCA'||IssuerO='netca')&&CertType='Encrypt'";
			}
		}// 第三个certType判断结束
		else
		{
			storeObj.Close();
			storeObj = null;
			return null;
		}

		cert = storeObj.FindCertificate(filter);
		storeObj = null;
		
		//返回选择的证书
		return cert;
	}
    catch (e)
    {
        alert(e);
		return null;
    }    
}

/* ==========================================
 *@date   2013-03-11
 *描述： 弹出从设备集中所有符合条件的证书供选择
 *@return：选中的证书
 * ==========================================
 */
function chooseDeviceSetCerts(certType, caType)
{
 	var cert;
	var count;
	var filter="InValidity='True'";
	
	try
    {	
		deviceSetObj = getAllDevices(-1,0);
		if(certType == NETCAPKI_CERT_PURPOSE_ALL) 
		{
			if (caType==NETCAPKI_NETCA_ALL)
			{
				//无过滤条件
			}
			else  if (caType==NETCAPKI_NETCA_NETCACERT)
			{
				filter+="&&(IssuerO='NETCA Certificate Authority'||IssuerO='NETCA'||IssuerO='netca')";	
			}
			else  if (caType==NETCAPKI_NETCA_OTHERCERT)
			{
				filter+="&&!(IssuerO='NETCA Certificate Authority'||IssuerO='NETCA'||IssuerO='netca')";
			}
			
		}// 第一个certType判断结束 
		else if(certType == NETCAPKI_CERT_PURPOSE_SIGN)
		{
			if (caType==NETCAPKI_NETCA_ALL)
			{
				filter+="&&CertType='Signature'";	
			}
			else  if (caType==NETCAPKI_NETCA_NETCACERT)
			{
				filter+="&&(IssuerO='NETCA Certificate Authority'||IssuerO='NETCA'||IssuerO='netca')&&CertType='Signature'";	
			}
			else  if (caType==NETCAPKI_NETCA_OTHERCERT)
			{
				filter+="&&!(IssuerO='NETCA Certificate Authority'||IssuerO='NETCA'||IssuerO='netca')&&CertType='Signature'";
			} 
		}//第二个certType判断结束
		else if(certType == NETCAPKI_CERT_PURPOSE_ENCRYPT)
		{
			if (caType==NETCAPKI_NETCA_ALL)
			{
				 filter+="&&CertType='Encrypt'";
			}
			else  if (caType==NETCAPKI_NETCA_NETCACERT)
			{
				filter+="&&(IssuerO='NETCA Certificate Authority'||IssuerO='NETCA'||IssuerO='netca')&&CertType='Encrypt'";
			}
			else  if (caType==NETCAPKI_NETCA_OTHERCERT)
			{
				filter+="&&!(IssuerO='NETCA Certificate Authority'||IssuerO='NETCA'||IssuerO='netca')&&CertType='Encrypt'";
			}
		}// 第三个certType判断结束
		else
		{
			deviceSetObj = null;
			return null;
		}

		cert = deviceSetObj.FindCertificate(filter);
		deviceSetObj = null;
		
		//返回选择的证书
		return cert;
	}
    catch (e)
    {
        alert(e);
		return null;
    }    
}

/* ==========================================
 *@date   2013-03-14
 *描述： 获取证书信息
 *@return：返回证书信息
 * ==========================================
 */
function getCertInfo(oCert, iValueType)
{
	var rvStr="";
    var temp;

	if (oCert==null || iValueType==null)
	{
		return null;
	}
	
	try
	{
		var pki = document.getElementById("pki");
		// 基本信息
		if(iValueType>=0&&iValueType<=8)
		{
			switch (iValueType)
			{
				//证书姆印 0
				case NETCAPKI_CERT_THUMBPRINT: 
					rvStr = pki.BinaryToHex(oCert.ThumbPrint(NETCAPKI_ALGORITHM_SHA1),true);
					break;
				//证书序列号 1
				case NETCAPKI_CERT_SERIALNUMBER: 
					rvStr = oCert.SerialNumber;
				   break;
				//证书Subject 2
				case NETCAPKI_CERT_SUBJECT: 
					rvStr = oCert.Subject;
					break;
				// 证书颁发者 3
				case NETCAPKI_CERT_ISSUER: 
					rvStr = oCert.Issuer;
					break;
				//证书有效期起 4
				case NETCAPKI_CERT_VALIDFROMDATE:					
					rvStr = (string2Date(oCert.ValidFromDate)).toLocaleString();	
					break;
				//证书有效期止 5
				case NETCAPKI_CERT_VALIDTODATE:
					rvStr = (string2Date(oCert.ValidToDate)).toLocaleString();
					break;     
				//密钥用法 KeyUsage 6
				case NETCAPKI_CERT_KEYUSAGE: 
					temp = oCert.KeyUsage;
					//alert(temp);
					if(temp =="3")
					{
						rvStr = "密钥用法：数字签名，不可否认或者内容承诺。";
					}
					else if(temp =="12")
					{
						rvStr = "密钥用法：加密密钥,加密数据。";
					}
					else
					{
						rvStr = "密钥用法值为："+temp+"。请参考密钥用法参数说明";
					}
					break;
				//证书的公钥的算法 7
				case NETCAPKI_CERT_PUBLICKEYALGO: 
					rvStr = ""+ oCert.PublicKeyAlgorithm;
					break;
				//证书的公钥长度 8
				case NETCAPKI_CERT_PUBLICKEYBITS: 
					rvStr = ""+ oCert.PublicKeyBits;
					break;			
				default:
					rvStr = null;
					break;
			}
		}
		else if( (iValueType >=9 && iValueType<=26 && iValueType!=15)
					|| iValueType ==29
					|| (iValueType>=36 && iValueType<=41))
		{
			// 证书属性
			rvStr = oCert.GetStringInfo(iValueType);
		}
		else
		{
			rvStr = null;
		}
	}
	catch(e)
	{
		 rvStr = null;
		 alert(e);
	}
    return rvStr;
}


/* ==========================================
 *@date   2013-03-14
 *描述： P7签名
 *@return：返回签名值
 * ==========================================
 */
function signPKCS7(cert, bContent, hasSource)
{
	var str;
	var tbs;
	if(bContent==null||bContent=="")
	{   
	    alert("原文内容为空!");
		return null;
    }

	try
	{
		pki = document.getElementById("pki");
		signObj =  pki.CreateSignedDataObject(true);
		
		//1.设置证书
		if (signObj.SetSignCertificate(cert, "", false) == false)
		{
			return null;
		}	
		
		//2.设置算法
		signObj.SetSignAlgorithm(-1, NETCAPKI_ALGORITHM_SHA1WITHRSA); 

		// 原文是字符串，将原文编码
		if(typeof(bContent)=="string" || typeof(bContent)=="String")
		{  
			tbs = pki.Encode("UTF-8", bContent);
		}
		else 
		{
			// 原文是数组
			 tbs = bContent;
		}
	
		 // 0：带原文;   1：不带原文;
		if(hasSource==0 || hasSource=="0" || hasSource==false)
		{ 
			// 带原文
			 signObj.Detached = false;   
		}
		else if(hasSource==1 || hasSource=="1" || hasSource==true)
		{    
			// 不带原文
			 signObj.Detached = true;   
		}
		else
		{
			return null;
		}
		
		// 3.签名
		str = signObj.Sign(tbs,NETCAPKI_CMS_ENCODE_BASE64);
		signObj = null;
		utilObj = null;
		return str;
	}
	catch (e)
	{
		alert(e);
		return null;
	}
}

/* ==========================================
 *@date   2013-03-14
 *描述： P7验证签名
 *@return：返回验证结果
 * ==========================================
 */
function verifyPKCS7(bContent, sSignature, hasSource)
{
    var result = false;
	var tbs;
	signObj = null;
	if(bContent==null||bContent=="")
	{   
	    alert("原文内容为空!");
		return result;
    }
	
    if(sSignature=="")
    {
       alert("签名信息为空!");
       return result;
    }
    
	try
	{
		pki = document.getElementById("pki");
		signObj =  pki.CreateSignedDataObject(false);
	
		// 原文是字符串，将原文编码
		if(typeof(bContent)=="string" || typeof(bContent)=="String")
		{  
			tbs = pki.Encode("UTF-8", bContent);
		}
		else 
		{
			// 原文是数组
			 tbs = bContent;
		}

		 // 0：带原文;   1：不带原文;
		var  bContentS; // 返回的原文
		if(hasSource==0 || hasSource=="0" || hasSource==false)
		{ 
			// 带原文
			//signObj.Detached = false;   
			bContentS = signObj.Verify(sSignature); 
			result = tbs.Equals(bContentS);
		}
		else if(hasSource==1 || hasSource=="1" || hasSource==true)
		{    
			// 不带原文
			//signObj.Detached = true;   
			result = signObj.DetachedVerify(tbs, sSignature); 
		}
		else
		{
			return false;
		}
		
		return result;
	}
	catch (e)
	{
		alert(e);
		return false ;
	}
}


//===========================================//
//============ 辅组函数======================//
//===========================================//

/* =======================================================
 *@date   2013-03-16
 *描述： 证书有效期的计算：string的0区时间转为+8的Date
 * String("20130101121212")->Date(2013-01-01 12:12:12);
 *@return：Date
 * =======================================================
 */
function string2Date(str)
{
	var date;
	var dateArray = new Array();
	dateArray[0] = str.substring(0,4); //year
	dateArray[1] = str.substring(4,6); //mouth
	dateArray[2] = str.substring(6,8); //day
	dateArray[3] = str.substring(8,10); //hour
	dateArray[4] = str.substring(10,12); //minute
	dateArray[5] = str.substring(12,14); //seconds
	
	// 先构造GMT的时间，然后构造东8区（默认为东8区）的时间
	var dateGMT0Date = new Date(dateArray[0],dateArray[1],dateArray[2],dateArray[3],dateArray[4],dateArray[5]);
	dateGMT0Date.setMonth(dateGMT0Date.getMonth()-1);
	dateGMT0Date.setHours(dateGMT0Date.getHours()+8);		
	
	date = new Date(dateGMT0Date);

	return date;
}
//-->