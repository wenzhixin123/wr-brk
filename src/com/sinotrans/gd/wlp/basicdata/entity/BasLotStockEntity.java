package com.sinotrans.gd.wlp.basicdata.entity;

import com.sinotrans.gd.wlp.basicdata.model.BasLotStockModel;
import com.sinotrans.gd.wlp.util.StringUtil;

@SuppressWarnings("serial")
public class BasLotStockEntity extends BasLotStockModel {

	//货位所属区域类型
	private String cw1;
	
	public String getCw1() {
		return cw1;
	}

	public void setCw1(String cw1) {
		this.cw1 = cw1;
		this.setKeyBit(1, cw1);
	}
	//默认字段
	private String cw2;
	
	public String getCw2() {
		return cw2;
	}

	public void setCw2(String cw2) {
		this.cw2 = cw2;
		this.setKeyBit(2, cw2);
	}

	public String getKeyBit(int index) {
		String keyBit = "0";
		String controlWord = super.getControlWord();
		controlWord = StringUtil.fixKey(controlWord, 20, "0");
		keyBit = controlWord.substring(index - 1, index);
		return keyBit;
	}

	public void setKeyBit(int index, String keyBit) {
		String controlWord = super.getControlWord();
		controlWord = StringUtil.fixKey(controlWord, 20, "0");
		if (StringUtil.isNull(keyBit))
			keyBit = "0";
		keyBit = keyBit.substring(0, 1);
		controlWord = controlWord.substring(0, index - 1) + keyBit
				+ controlWord.substring(index);
		super.setControlWord(controlWord);
	}
}
