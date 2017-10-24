package com.sinotrans.gd.wlp.util;

public enum OfficeCodeTypeEnum {
	EXTN("外部公司","0"),
	INTERNAL_PORT("内部码头","1"),
	//WLPDC-59
	PUBLIC("公共帐户","2"),
	DOCC("文件中心","9");
	
	private String name;
	private String value;
	private OfficeCodeTypeEnum(String name,String value){
		this.name = name;
		this.value = value;
	}
	public String getName(){
		return name;
	}
	public String getValue(){
		return value;
	}
	public static OfficeCodeTypeEnum getEnum(String value) {
        if(value == null)
            throw new IllegalArgumentException();
        for(OfficeCodeTypeEnum v : values())
            if(value.equalsIgnoreCase(v.getValue())) return v;
        throw new IllegalArgumentException();
    }
}
