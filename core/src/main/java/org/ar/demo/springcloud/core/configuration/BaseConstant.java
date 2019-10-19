package org.ar.demo.springcloud.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value= {"${config.path}/basicConstant.properties"},
	ignoreResourceNotFound=true, encoding="utf-8")
public class BaseConstant {
	
	/**
	 * @author ArLandlate
	 * function: my project base constants
	 */
	
	/**
	 * project infomation（逻辑留白）
	 */
	// 项目域名
	private static String PROJECT_DNS;
	// 项目官方邮箱（逻辑留白：这个先写lopmm的，以后再改）
	private static String PROJECT_EMAIL;
	// 项目邮件服务器域名
	private static String PROJECT_EMAIL_DNS;

	/**
	 * for response json template
	 */
	// 服务码：user-server: 102
	private static int SERVICE_CODE;
	// 请求状态：成功、失败、错误
	private static String RJT_STATUS_SUCCESS;
	private static String RJT_STATUS_FAILED;
	private static String RJT_STATUS_ERROR;
	// 状态码
	private static int RJT_CODE_SUCCESS;
	private static int RJT_CODE_ERROR_PARAMETER;
	private static int RJT_CODE_ERROR_PARAMETER_EXPIRE;
	private static int RJT_CODE_ERROR_DATABASE;
	private static int RJT_CODE_ERROR_UNKOWN;
	
	/**
	 * for note information
	 * 出现问题的提示信息
	 * 这部分主要是为了保证后端不同层调用方法时传递的数据一致
	 */
	// 提示信息
	private static String RJT_NOTE_PARAMETER_PHONENUMISBLANK;	//表示当前手机号码为空
	private static String RJT_NOTE_PARAMETER_REGISTERTYPE_USERNAME;	//表示当前用户通过用户名+密码方式注册
	private static String RJT_NOTE_PARAMETER_REGISTERTYPE_PHONE;	//表示当前用户通过手机+验证码+密码方式注册
	private static String RJT_NOTE_PARAMETER_REGISTERTYPE_EMAIL;	//表示当前用户通过邮箱+密码+激活方式注册
	
	private static int RJT_NOTECODE_PARAMETER_REGISTERTYPE_USERNAME;	//表示当前用户通过用户名+密码方式注册
	private static int RJT_NOTECODE_PARAMETER_REGISTERTYPE_PHONE;	//表示当前用户通过手机+验证码+密码方式注册
	private static int RJT_NOTECODE_PARAMETER_REGISTERTYPE_EMAIL;	//表示当前用户通过邮箱+密码+激活方式注册
	
	/**
	 * for portal information
	 * 这部分主要是为了部分前后端传值的统一
	 */
	private static String RJT_DATA_VALIDATE_TRUE;
	private static String RJT_DATA_VALIDATE_FALSE;
	
	/**
	 * for redis key
	 * 这部分是为了统一redis key的命名规则
	 */
	private static String REDIS_KEY_PREFIX_ACTEMAIL;

	/**
	 * getter & setter
	 */
	@Value("${bcst.project.dns}")
	public void setPROJECT_DNS(String project_dns) {
		PROJECT_DNS = project_dns;
	}
	@Value("${bcst.project.email}")
	public void setPROJECT_EMAIL(String pROJECT_EMAIL) {
		PROJECT_EMAIL = pROJECT_EMAIL;
	}
	@Value("${bcst.project.email.dns}")
	public void setPROJECT_EMAIL_DNS(String pROJECT_EMAIL_DNS) {
		PROJECT_EMAIL_DNS = pROJECT_EMAIL_DNS;
	}
	@Value("${bcst.service.code}")
	public void setSERVICE_CODE(int service_code) {
		SERVICE_CODE = service_code;
	}
	@Value("${bcst.rjt.status.success}")
	public void setRJT_STATUS_SUCCESS(String rjt_status_success) {
		RJT_STATUS_SUCCESS = rjt_status_success;
	}
	@Value("${bcst.rjt.status.failed}")
	public void setRJT_STATUS_FAILED(String rjt_status_failed) {
		RJT_STATUS_FAILED = rjt_status_failed;
	}
	@Value("${bcst.rjt.status.error}")
	public void setRJT_STATUS_ERROR(String rjt_status_error) {
		RJT_STATUS_ERROR = rjt_status_error;
	}
	@Value("${bcst.rjt.code.success}")
	public void setRJT_CODE_SUCCESS(int rjt_code_success) {
		RJT_CODE_SUCCESS = rjt_code_success;
	}
	@Value("${bcst.rjt.code.error.parameter}")
	public void setRJT_CODE_ERROR_PARAMETER(int rjt_code_error_parameter) {
		RJT_CODE_ERROR_PARAMETER = rjt_code_error_parameter;
	}
	@Value("${bcst.rjt.code.error.parameter.expire}")
	public void setRJT_CODE_ERROR_PARAMETER_EXPIRE(int rJT_CODE_ERROR_PARAMETER_EXPIRE) {
		RJT_CODE_ERROR_PARAMETER_EXPIRE = rJT_CODE_ERROR_PARAMETER_EXPIRE;
	}
	@Value("${bcst.rjt.code.error.database}")
	public void setRJT_CODE_ERROR_DATABASE(int rjt_code_error_database) {
		RJT_CODE_ERROR_DATABASE = rjt_code_error_database;
	}
	@Value("${bcst.rjt.code.error.unkown}")
	public void setRJT_CODE_ERROR_UNKOWN(int rjt_code_error_unkown) {
		RJT_CODE_ERROR_UNKOWN = rjt_code_error_unkown;
	}
	@Value("${bcst.rjt.note.parameter.phoneNumIsBlank}")
	public void setRJT_NOTE_PARAMETER_PHONENUMISBLANK(String rjt_note_parameter_phonenumisblank) {
		RJT_NOTE_PARAMETER_PHONENUMISBLANK = rjt_note_parameter_phonenumisblank;
	}
	@Value("${bcst.rjt.note.parameter.registerType.userName}")
	public void setRJT_NOTE_PARAMETER_REGISTERTYPE_USERNAME(String rjt_note_parameter_registertype_username) {
		RJT_NOTE_PARAMETER_REGISTERTYPE_USERNAME = rjt_note_parameter_registertype_username;
	}
	@Value("${bcst.rjt.note.parameter.registerType.phone}")
	public void setRJT_NOTE_PARAMETER_REGISTERTYPE_PHONE(String rJT_NOTE_PARAMETER_REGISTERTYPE_PHONE) {
		RJT_NOTE_PARAMETER_REGISTERTYPE_PHONE = rJT_NOTE_PARAMETER_REGISTERTYPE_PHONE;
	}
	@Value("${bcst.rjt.note.parameter.registerType.email}")
	public void setRJT_NOTE_PARAMETER_REGISTERTYPE_EMAIL(String rJT_NOTE_PARAMETER_REGISTERTYPE_EMAIL) {
		RJT_NOTE_PARAMETER_REGISTERTYPE_EMAIL = rJT_NOTE_PARAMETER_REGISTERTYPE_EMAIL;
	}
	@Value("${bcst.rjt.notecode.parameter.registerType.userName}")
	public void setRJT_NOTECODE_PARAMETER_REGISTERTYPE_USERNAME(int rjt_notecode_parameter_registertype_username) {
		RJT_NOTECODE_PARAMETER_REGISTERTYPE_USERNAME = rjt_notecode_parameter_registertype_username;
	}
	@Value("${bcst.rjt.notecode.parameter.registerType.phone}")
	public void setRJT_NOTECODE_PARAMETER_REGISTERTYPE_PHONE(int rjt_notecode_parameter_registertype_phone) {
		RJT_NOTECODE_PARAMETER_REGISTERTYPE_PHONE = rjt_notecode_parameter_registertype_phone;
	}
	@Value("${bcst.rjt.notecode.parameter.registerType.email}")
	public void setRJT_NOTECODE_PARAMETER_REGISTERTYPE_EMAIL(int rjt_notecode_parameter_registertype_email) {
		RJT_NOTECODE_PARAMETER_REGISTERTYPE_EMAIL = rjt_notecode_parameter_registertype_email;
	}
	@Value("${bcst.rjt.data.validate.true}")
	public void setRJT_DATA_VALIDATE_TRUE(String rJT_DATA_VALIDATE_TRUE) {
		RJT_DATA_VALIDATE_TRUE = rJT_DATA_VALIDATE_TRUE;
	}
	@Value("${bcst.rjt.data.validate.false}")
	public void setRJT_DATA_VALIDATE_FALSE(String rJT_DATA_VALIDATE_FALSE) {
		RJT_DATA_VALIDATE_FALSE = rJT_DATA_VALIDATE_FALSE;
	}
	@Value("${bcst.redis.key.prefix.act-email}")
	public void setREDIS_KEY_PREFIX_ACTEMAIL(String rEDIS_KEY_PREFIX_ACTEMAIL) {
		REDIS_KEY_PREFIX_ACTEMAIL = rEDIS_KEY_PREFIX_ACTEMAIL;
	}

	public static String getPROJECT_DNS() {
		return PROJECT_DNS;
	}
	public static String getPROJECT_EMAIL() {
		return PROJECT_EMAIL;
	}
	public static String getPROJECT_EMAIL_DNS() {
		return PROJECT_EMAIL_DNS;
	}
	public static int getSERVICE_CODE() {
		return SERVICE_CODE;
	}
	public static String getRJT_STATUS_SUCCESS() {
		return RJT_STATUS_SUCCESS;
	}
	public static String getRJT_STATUS_FAILED() {
		return RJT_STATUS_FAILED;
	}
	public static String getRJT_STATUS_ERROR() {
		return RJT_STATUS_ERROR;
	}
	public static int getRJT_CODE_SUCCESS() {
		return RJT_CODE_SUCCESS;
	}
	public static int getRJT_CODE_ERROR_PARAMETER() {
		return RJT_CODE_ERROR_PARAMETER;
	}
	public static int getRJT_CODE_ERROR_PARAMETER_EXPIRE() {
		return RJT_CODE_ERROR_PARAMETER_EXPIRE;
	}
	public static int getRJT_CODE_ERROR_DATABASE() {
		return RJT_CODE_ERROR_DATABASE;
	}
	public static int getRJT_CODE_ERROR_UNKOWN() {
		return RJT_CODE_ERROR_UNKOWN;
	}
	public static String getRJT_NOTE_PARAMETER_PHONENUMISBLANK() {
		return RJT_NOTE_PARAMETER_PHONENUMISBLANK;
	}
	public static String getRJT_NOTE_PARAMETER_REGISTERTYPE_USERNAME() {
		return RJT_NOTE_PARAMETER_REGISTERTYPE_USERNAME;
	}
	public static String getRJT_NOTE_PARAMETER_REGISTERTYPE_PHONE() {
		return RJT_NOTE_PARAMETER_REGISTERTYPE_PHONE;
	}
	public static String getRJT_NOTE_PARAMETER_REGISTERTYPE_EMAIL() {
		return RJT_NOTE_PARAMETER_REGISTERTYPE_EMAIL;
	}
	public static int getRJT_NOTECODE_PARAMETER_REGISTERTYPE_USERNAME() {
		return RJT_NOTECODE_PARAMETER_REGISTERTYPE_USERNAME;
	}
	public static int getRJT_NOTECODE_PARAMETER_REGISTERTYPE_PHONE() {
		return RJT_NOTECODE_PARAMETER_REGISTERTYPE_PHONE;
	}
	public static int getRJT_NOTECODE_PARAMETER_REGISTERTYPE_EMAIL() {
		return RJT_NOTECODE_PARAMETER_REGISTERTYPE_EMAIL;
	}
	public static String getRJT_DATA_VALIDATE_TRUE() {
		return RJT_DATA_VALIDATE_TRUE;
	}
	public static String getRJT_DATA_VALIDATE_FALSE() {
		return RJT_DATA_VALIDATE_FALSE;
	}
	public static String getREDIS_KEY_PREFIX_ACTEMAIL() {
		return REDIS_KEY_PREFIX_ACTEMAIL;
	}

}
