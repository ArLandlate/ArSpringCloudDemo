package org.ar.demo.springcloud.ribbon.enums;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

public class ResultContainer {
	
	/**
	 * @author ArLandlate
	 * useful: ResponseJsonTemplate的容器
	 */
	
	/**
	 *  properties
	 */
	private ResponseJsonTemplate statusType;
	
	private String status;		//状态
	private int code;				//状态码
	private int serviceCode;	//服务码
	private int noteCode;		//提示码
	private String note;			//提示信息
	private String description;//描述
	
	private Object data;			//返回数据内容
	
	/**
	 * constructor
	 */
	public ResultContainer() {
	}
	public ResultContainer(ResponseJsonTemplate template) {
		this.statusType = template;
	}
	
	/**
	 * methods
	 */
	//提供静态方法获取实例
	public static ResultContainer getSuccessInstance() {
		return new ResultContainer(ResponseJsonTemplate.SUCCESS);
	}
	public static ResultContainer getError301Instance() {
		return new ResultContainer(ResponseJsonTemplate.ERROR301);
	}
	public static ResultContainer getError401Instance() {
		return new ResultContainer(ResponseJsonTemplate.ERROR401);
	}
	public static ResultContainer getError500Instance() {
		return new ResultContainer(ResponseJsonTemplate.ERROR500);
	}
	
	//将返回数据或提示信息写入返回体
	public ResultContainer rwData(Object data) {
		this.data = data;
		return this;
	}
	public ResultContainer rwCode(int code) {
		this.code = code;
		return this;
	}
	public ResultContainer rwNoteCode(int noteCode) {
		this.noteCode = noteCode;
		return this;
	}
	public ResultContainer rwNote(String note) {
		this.note = note;
		return this;
	}
	public ResultContainer rwDescription(String description) {
		this.description = description;
		return this;
	}
	//修改返回体模板（所有数据均会重置）
	public ResultContainer transform(ResponseJsonTemplate newTemplate) {
		this.statusType = newTemplate;
		this.note = null;
		this.description = null;
		this.data = null;
		return this;
	}
	
	//获取当前对象的json字串
	public String toJsonString() {
		return JSON.toJSONString(this);
	}
	
	//装载对象以解析json字串
	public ResultContainer compile() {
		this.status = this.statusType.getStatus();
		this.code = (0!=this.code)?this.code:this.statusType.getCode();
		this.serviceCode = this.statusType.getServiceCode();
		this.note = (null!=this.note)?this.note:this.statusType.getNote();
		this.description = (null!=this.description)?this.description:this.statusType.getDescription();
		this.data = (null!=this.data)?this.data:this.statusType.getData();
		return this;
	}
	
	//验证当前提示信息是否为某一字串（用以判断校验结果）
	public boolean noteIs(String validNote) {
		if(StringUtils.isBlank(this.note)) {
			return false;
		}
		return this.note.equals(validNote);
	}
	
	/**
	 * getter & setter
	 */
	public ResponseJsonTemplate getStatusType() {
		return statusType;
	}
	public void setStatusType(ResponseJsonTemplate statusType) {
		this.statusType = statusType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getNoteCode() {
		return noteCode;
	}
	public void setNoteCode(int noteCode) {
		this.noteCode = noteCode;
	}

}
