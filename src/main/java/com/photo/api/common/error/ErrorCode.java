package com.photo.api.common.error;


/**
 * 
 * @author liup
 * 2016年3月14日
 * 错误码
 */
public  enum  ErrorCode {
		SUCCESS("1000","成功","SUCCESS"),
		CODE_AUTH_ERROR	("1001","授权失败"),
		CODE_REQ_AUTH_ERROR	("1002","请求授权失败"),
		CODE_REQ_OBJ_ERROR	("1003","请求对象错误"),
		CODE_OP_AUTH_ERROR ("1004","操作无权限"),
		CODE_SQL_ERROR ("1005","数据库查询出错","sql error"),
		CODE_UPLOAD_FILE_ERROR ("1006","上传文件出错","upload file error"),
		CODE_NICK_USED_ERROR ("1017","用户名重复！","nickname is exist!"),
		CODE_DATABASE_OPERATE_ERROR ("1011","数据库操作出错","sql error"),
		CODE_EMAIL_ERROR ("1012","邮箱错误","email error"),
		SPECIFIED_OBJECT_CANNOT_BE_FOUND("211","指定的对象不存在","The Object is not exist!"),
		INVOKE_SERVICE_ERROR("400","调用服务出错","can't find the service!"),
		CODE_AES_ERROR("9996","密码错误！","error password!"),
	    CODE_SERVICE_ERRER("9997","调用服务错误"),
	    CODE_PARAM_ERROR	("9998","参数错误"),
		CODE_ERROR("9999","未知错误")
		;
		private String code;
        private String message;
        private String enMessage;
		
		private ErrorCode(String code,String message) {
			this.code = code;
			this.message = message;
		}
		private ErrorCode(String code,String message,String enMessage) {
			this.code = code;
			this.message = message;
			this.enMessage = enMessage;
		}
		

		public String getCode() {
			return this.code;
		}
		public String getMessage(){
			return this.message;
		}
		
		public String getEnMessage() {
			return enMessage;
		}
		public void setCode(String code) {
			this.code = code;
		}


}