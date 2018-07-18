package com.terminology.functional.programming;
import java.util.HashMap;
import java.util.Map;

public enum EnumErrorCodes {
	ERROR_01("01","BeltError"),
	ERROR_02("02","BeltError"),
	ERROR_03("03","BeltError"),
	ERROR_04("04","EngineError"),
	ERROR_05("05","EngineError"),
	ERROR_06("06","EngineError"),
	ERROR_07("07","FanError"),
	ERROR_08("08","FanError"),
	ERROR_09("09","FanError");
	
	static final Map<String, String> errorMap = new HashMap<String, String>();
	String errorCode;
	String desc;
	static {
        for (EnumErrorCodes error : EnumErrorCodes.values()) {
            errorMap.put(error.getErrorCode(), error.getDesc());
        }
    }
	private EnumErrorCodes(String errorCode,String desc){
		this.errorCode = errorCode;
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc= desc;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public static String getErrorDescByCode(String code){
		return errorMap.get(code);
	}
	
}
