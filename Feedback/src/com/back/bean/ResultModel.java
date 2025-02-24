package com.back.bean;

/**
 * ͳһ����Ӧģ��
 * @author Administrator
 *
 */
public class ResultModel {
	
	private int code=200;
	private String msg;
	private Object data;
	private long total;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}