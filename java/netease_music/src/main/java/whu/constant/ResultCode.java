package whu.constant;

public enum ResultCode {
    SUCCESS(200,"访问成功"),
    SELECT_NULL(301,"mybatis-plus查询的结果为空"),
    FAIL_TO_SELECT(302,"查询所有失败,被catch住了"),
    INSERT_RETURN_NOT_EQUAL_ONE(303,"mybatis-plus新增返回值不等于1"),
    INSERT_ERROR(304,"执行插入语句出错，被catch住了"),
    DELETE_RETURN_NOT_EQUAL_ONE(305,"mybatis-plus删除返回值不等于1"),
    DELETE_ERROR(306,"执行删除语句出错，被catch住了"),
    UPDATE_RETURN_NOT_EQUAL_ONE(307,"mybatis-plus更新返回值不等于1"),
    UPDATE_ERROR(308,"更新错误，被catch住了"),
    PARA_NULL(309,"参数为空"),
    UPLOAD_ERROR(501,"上传音乐失败");


    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultCode(Integer code, String message){
        this.code=code;
        this.message=message;
    }
}
