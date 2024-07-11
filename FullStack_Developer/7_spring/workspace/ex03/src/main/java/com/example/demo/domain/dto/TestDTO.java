package com.example.demo.domain.dto;

public class TestDTO {
	int intData;
	String strData;
	public TestDTO() {}
	public int getIntData() {
		return intData;
	}
	public void setIntData(int intData) {
		this.intData = intData;
	}
	public String getStrData() {
		return strData;
	}
	public void setStrData(String strData) {
		this.strData = strData;
	}
	
	@Override
	public String toString() {
		return "TestDTO(intData="+intData+", strData="+strData+")";
	}
}
