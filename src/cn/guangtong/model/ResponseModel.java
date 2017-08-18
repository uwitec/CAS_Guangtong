package cn.guangtong.model;

import java.util.List;

public class ResponseModel<T> {

	private String msg = "失败";
	private boolean success = false;
	private List<T> obj = null;
	private T t;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<T> getObj() {
		return obj;
	}

	public void setObj(List<T> obj) {
		this.obj = obj;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public void init() {
		this.msg = "失败";
		this.success = false;
		this.obj = null;
		this.t = null;
	}

	@Override
	public String toString() {
		return "ResponseModel [msg=" + msg + ", success=" + success + ", obj=" + obj + "]";
	}
}
