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
		CODE_RECOMMEND_FILM_ID_NULL_ERROR("1007","影片推荐id为空","film id null error "),
		CODE_RECOMMEND_FILM_TAGS_LENGTH_ERROR("1008","影片推荐id和tag长度不等","film id ,tag length error "),
		CODE_RECOMMEND_FILM_TAGS_NULL_ERROR("1009","影片推荐tag为空","ilm tag null error "),
		CODE_FILM_EPISODE_NULL_ERROR("1010","影片剧集在数据库不存在","episode not exists in database "),
	    CODE_DATABASE_OPERATE_ERROR ("1011","数据库操作出错","sql error"),
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