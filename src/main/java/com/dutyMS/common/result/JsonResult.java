package com.dutyMS.common.result;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * json通用返回结果
 *
 * @author wanghongliang
 * @create 2018年12月17日
 * @project springboot-tiodemo
 */
public class JsonResult {

	private static final int SUCCESS_COCE = 200;
	private static final int FAIL_COCE = 500;

	public JsonResult() {

	}

	public JsonResult(String msg) {
		this(FAIL_COCE, msg, null);
	}

	public JsonResult(Object data, String msg) {
		this(SUCCESS_COCE, msg, data);
	}

	public JsonResult(Integer code, String msg, Object data) {
		this.data = data;
		this.code = code;
		this.msg = msg;
	}

	private Integer code;
	private String msg;
	private Object data;

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	@JsonIgnore
	public boolean isSuccess() {
		return code == SUCCESS_COCE;
	}

	@JsonIgnore
	public boolean isFail() {
		return !isSuccess();
	}

	/**
	 * 成功结果，有返回值
	 */
	public static JsonResult success(Object data, String msg) {
		return new JsonResult(data,msg);
	}

	/**
	 * 成功结果无返回值
	 */
	public static JsonResult success() {
		return success(null,"");
	}

	/**
	 * 失败结果
	 */
	public static JsonResult fail(String msg) {
		return new JsonResult(msg);
	}

	/**
	 * 失败结果
	 */
	public static JsonResult fail(String msg, Object data) {
		return new JsonResult(FAIL_COCE, msg, data);
	}

}
