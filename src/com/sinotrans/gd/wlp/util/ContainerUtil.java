package com.sinotrans.gd.wlp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContainerUtil {
	
	
	public static  boolean ContainerNoCheckDigit(String ls_cntno) {
		/*
		 * 集装箱校验码校验规则： 集装箱号由4位公司代码和7位数字组成，其中第七位数字就是校验码。
		 * 首先将公司代码转换为数字，去掉11及其倍数，乘以(2^i)连加除以11，其余数为校验位。 A=10 B=12 C=13 D=14 E=15
		 * F=16 G=17 H=18 I=19 J=20 K=21 L=23 M=24 N=25 O=26 P=27 Q=28 R=29 S=30
		 * T=31 U=32 V=34 W=35 X=36 Y=37 Z=38
		 */
		boolean lb_ret = false;
		if (!isBlank(ls_cntno) && ls_cntno.length() >= 11) {
			if (checkCtrno(ls_cntno)) {
				int bitNum, posAt, chkNum, totalNum;
				String numString = "1012131415161718192021232425262728293031323435363738";
				// A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
				String letterString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				totalNum = 0;
				char[] checkDigit = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',' ', ' ', ' ' };
				ls_cntno.getChars(0, 11, checkDigit, 0);
				for (int i = 0; i < 10; i++) {
					if ((int) checkDigit[i] < 58 && (int) checkDigit[i] > 47) { // ascii 48 - 57 为 0123456789
						bitNum = Integer.parseInt(Character.toString(checkDigit[i]));
					} else {
						posAt = letterString.indexOf((int) checkDigit[i]) * 2;
						bitNum = Integer.parseInt(numString.substring(posAt,posAt + 2));
					}
					totalNum = totalNum + bitNum * ((int) java.lang.Math.pow(2, i));
				}
				chkNum = (totalNum % 11);
				if ((int) checkDigit[10] < 58 && (int) checkDigit[10] > 47) {
					bitNum = Integer.parseInt(Character.toString(checkDigit[10]));
				} else {
					bitNum = 11; // 非数字
				}
				if (chkNum == 10) {
					if (bitNum == 0 || bitNum == 1)
						lb_ret = true;
				} else {
					if (chkNum == bitNum)
						lb_ret = true;
				}
			}
		}
		return lb_ret;
	}
	
	/**
	 * 判断字符串是否为空 null或 ""
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		boolean isEmpty = false;
		if (str == null) {
			return true;
		}
		if (str.trim().equals("")) {
			return true;
		}
		return isEmpty;
	}
	
	private static boolean checkCtrno(String inCtrno) {
		/**
		 * 验证集装箱号格式是否正确
		 * @param inCtrno一个集装箱号字符串参数
		 * @return 如果集装箱号格式正确则返回true，否则返回false
		 */
		boolean flag = false;
		Pattern p = Pattern.compile("^[A-Z]{4}+[0-9]{7}");
		Matcher m = null;
		m = p.matcher(inCtrno);
		flag = m.matches();
		return flag;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ContainerUtil.ContainerNoCheckDigit("COSU8001215"));
	}

}
