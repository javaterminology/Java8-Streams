package com.terminology.functional.programming;

public class TestEnumErrorCodes {

	public static void main(String[] args) {
		TestEnumErrorCodes.forError("09");
		TestEnumErrorCodes.forError("04");
		TestEnumErrorCodes.forError("01");
	}
	public static void forError(String errorCode){
		
		if(EnumErrorCodes.getErrorDescByCode(errorCode).equals("FanError")){
			reportFanError();
		}else if(EnumErrorCodes.getErrorDescByCode(errorCode).equals("FanError")){
			reportBeltError();
		}else if(EnumErrorCodes.getErrorDescByCode(errorCode).equals("FanError")){
			reportEngineError();
		}else{
			fixProblemNow();
		}
	}
	private static void fixProblemNow() {
		System.out.println("Fixing Problem");
	}
	private static void reportBeltError() {
		System.out.println("Belt Error");
	}
	private static void reportFanError() {
		System.out.println("Fan Error");
	}
	private static void reportEngineError() {
		System.out.println("Engine Error");
	}

}
