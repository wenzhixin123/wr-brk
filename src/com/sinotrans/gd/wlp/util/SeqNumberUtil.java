/**   
* @Title: SeqNumberUtil.java 
* @Package com.sinotrans.gd.wlp.util 
* @Description: TODO 生产不重复序列码 
* @author A18ccms A18ccms_gmail_com   
* @date 2013年10月21日 下午3:40:56 
* @version V1.0   
*/ 
package com.sinotrans.gd.wlp.util;

import java.util.HashMap;

/** 
 * @ClassName: SeqNumberUtil 
 * @Description: TODO 生产不重复序列码 
 * @author A18ccms a18ccms_gmail_com 
 * @date 2013年10月21日 下午3:40:56 
 *  
 */
public class SeqNumberUtil {

	private static HashMap<String, SeqNumerClass> seqNumerMap = new HashMap<String, SeqNumerClass>();
	
	private static boolean locked = false;
	
	public static class SeqNumerClass{
		private long seqNumer;
		private boolean locked=false;
		/**
		 * @return the seqNumer
		 */
		public long getSeqNumer() {
			return seqNumer;
		}
		/**
		 * @param seqNumer the seqNumer to set
		 */
		public void setSeqNumer(long seqNumer) {
			this.seqNumer = seqNumer;
		}
		
		/**
		 * 
		* @Title: isLocked 
		* @Description: TODO(是否锁定) 
		* @return boolean返回类型 
		* @throws
		 */
		public boolean isLocked() {
			return locked;
		}
		/**
		 * 
		* @Title: setLock 
		* @Description: TODO(加上锁)  void返回类型 
		* @throws
		 */
		public void setLock() {
			this.locked = true;
		}
		
		/**
		 * 
		* @Title: unLock 
		* @Description: TODO(解锁)  void返回类型 
		* @throws
		 */
		public void unLock() {
			this.locked = false;
		}
		
	}
	
	/**
	 * 
	* @Title: setSeqInitNumber 
	* @Description: TODO(设置计算的开始值) 
	* @param keyName
	* @param initValue void返回类型 
	* @throws
	 */
	public static void setSeqInitNumber(String keyName, long initValue ){
		if(! locked){
			locked = true;
			SeqNumerClass seqNumberObj = new SeqNumerClass();
			seqNumberObj.setSeqNumer(initValue);
			seqNumerMap.put(keyName, seqNumberObj);
			locked = false;
		}
	}
	
	public static void setSeqInitLock(String keyName){
		SeqNumerClass seqNumberObj = new SeqNumerClass();
		seqNumberObj.setLock();
		seqNumerMap.put(keyName, seqNumberObj);
	}
	
	/**
	 * 
	* @Title: isNeedToSetInit 
	* @Description: TODO(是否需要设置初始值) 
	* @param keyName
	* @return boolean返回类型 
	* @throws
	 */
	public  static boolean isNeedToSetInit(String keyName){
		SeqNumerClass seqNumberObj  =(SeqNumerClass) seqNumerMap.get(keyName);
		if(seqNumberObj == null){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	* @Title: getSeqNumber 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param keyName
	* @return long返回类型 
	* @throws
	 */
	public static long getSeqNumber(String keyName){
		SeqNumerClass seqNumberObj  =(SeqNumerClass) seqNumerMap.get(keyName);
		long l = 0l;
		if(seqNumberObj != null){
			if( seqNumberObj.isLocked() == false) {
				seqNumberObj.setLock();//锁定
				l  = Long.valueOf(seqNumberObj.getSeqNumer());
				l++;
				seqNumberObj.setSeqNumer(l);
				seqNumberObj.unLock();//解锁
			}else{
				return getSeqNumber(keyName);
			}

		}else{
			seqNumberObj  =(SeqNumerClass) seqNumerMap.get(keyName);
			if(seqNumberObj == null){
				setSeqInitNumber(keyName, 0l);
				return getSeqNumber(keyName); 
			}
		}
		return l;
	}
	
	
	/**
	 * 
	* @Title: startGetSeqNo 
	* @Description: 当要获取序号时，关锁
	* @param keyName
	* @return 无 
	* @throws
	 */
	public static void startGetSeqNo(String keyName){
		if(isNeedToSetInit(keyName)){
			setSeqInitLock(keyName);
		}else{
			SeqNumerClass seqNumObject = (SeqNumerClass) seqNumerMap.get(keyName);
			while(seqNumObject==null||seqNumObject.isLocked()){
				if(seqNumObject==null){
					setSeqInitLock(keyName);
					break;
				}else{
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			((SeqNumerClass) seqNumerMap.get(keyName)).setLock();
		}
		
	}
	
	
	/**
	 * 
	* @Title: endGetSeqNo 
	* @Description: 当获取完序号时，解锁
	* @param keyName
	* @return 无 
	* @throws
	 */
	public static void endGetSeqNo(String keyName){
		SeqNumerClass seqNumberObj  =(SeqNumerClass) seqNumerMap.get(keyName);
		if(seqNumberObj!=null){
			seqNumberObj.unLock();
		}
	}
	
	/**
	 * 用例 
	* @Title: userDemo 
	* @Description: TODO(这里用一句话描述这个方法的作用)  void返回类型 
	* @throws
	 */
	@SuppressWarnings("unused")
	private void userDemo(){
		String keyName = "区别其他序列号的名称";
		long initValue=0l;//开始的maxId
		if(SeqNumberUtil.isNeedToSetInit(keyName)){
			SeqNumberUtil.setSeqInitNumber(keyName, initValue);
		}
		long newSeqId = SeqNumberUtil.getSeqNumber(keyName);
	}
}
