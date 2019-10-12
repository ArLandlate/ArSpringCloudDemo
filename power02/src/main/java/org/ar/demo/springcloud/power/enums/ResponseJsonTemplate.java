package org.ar.demo.springcloud.power.enums;

import org.ar.demo.springcloud.power.configuration.BaseConstant;

public enum ResponseJsonTemplate {
	
	/**
	 * @author ArLandlate
	 * useful: 向前端项目返回json数据的模板
	 */
	
	//instances
	SUCCESS(
			BaseConstant.getRJT_STATUS_SUCCESS(),
			BaseConstant.getRJT_CODE_SUCCESS(), 
			BaseConstant.getSERVICE_CODE(), 
			"请求成功", "", ""
			),
	ERROR301(
			BaseConstant.getRJT_STATUS_ERROR(), 
			BaseConstant.getRJT_CODE_ERROR_PARAMETER(), 
			BaseConstant.getSERVICE_CODE(), 
			"参数异常", "", ""
			),
	ERROR401(
			BaseConstant.getRJT_STATUS_ERROR(), 
			BaseConstant.getRJT_CODE_ERROR_DATABASE(), 
			BaseConstant.getSERVICE_CODE(), 
			"数据异常", "", ""
			),
	ERROR500(
			BaseConstant.getRJT_STATUS_ERROR(), 
			BaseConstant.getRJT_CODE_ERROR_UNKOWN(), 
			BaseConstant.getSERVICE_CODE(), 
			"系统错误", "", ""
			);
	
	//constructors
	private ResponseJsonTemplate(String status, int code, int serviceCode, String note, String description,
                                 Object data) {
		this.status = status;
		this.code = code;
		this.serviceCode = serviceCode;
		this.note = note;
		this.description = description;
		this.data = data;
	}
	
	// properties
	private String status;		//状态
	private int code;				//状态码
	private int serviceCode;	//服务码
	private String note;			//提示信息
	private String description;//描述
	
	private Object data;			//返回数据内容
	
	// getter & setter
	public String getStatus() {
		return status;
	}

	public int getCode() {
		return code;
	}

	public int getServiceCode() {
		return serviceCode;
	}

	public String getNote() {
		return note;
	}

	public String getDescription() {
		return description;
	}

	public Object getData() {
		return data;
	}

}
