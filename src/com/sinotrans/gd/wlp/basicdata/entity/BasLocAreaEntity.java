package com.sinotrans.gd.wlp.basicdata.entity;

import com.sinotrans.gd.wlp.basicdata.model.BasLocAreaModel;
import com.sinotrans.gd.wlp.util.StringUtil;

@SuppressWarnings("serial")
public class BasLocAreaEntity extends BasLocAreaModel {

	private String basLocAreaType;
	
	public String getBasLocAreaType() {
		return basLocAreaType;
	}

	public void setBasLocAreaType(String basLocAreaType) {
		this.basLocAreaType = basLocAreaType;
		this.setKeyBit(1, basLocAreaType);
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
