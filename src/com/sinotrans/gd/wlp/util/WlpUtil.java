package com.sinotrans.gd.wlp.util;

import java.util.List;

public class WlpUtil {
	public static boolean checkListSize(List list){
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
}
