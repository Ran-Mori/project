package whu.constant;

import java.util.HashMap;
import java.util.Map;

public class CommonResult {
    private Integer code;
    private String message;
    private Map<String,Object> data=new HashMap<String, Object>();



    public static CommonResult success(){
        CommonResult commonResult=new CommonResult();
        ResultCode success=ResultCode.SUCCESS;
        commonResult.setCode(success.getCode());
        commonResult.setMessage(success.getMessage());
        return commonResult;
    }
    public static CommonResult fail(ResultCode resultCode){
        CommonResult commonResult=new CommonResult();
        commonResult.setCode(resultCode.getCode());
        commonResult.setMessage(resultCode.getMessage());
        return commonResult;
    }

    public CommonResult add(String key,Object value){
        this.getData().put(key,value);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public CommonResult(Integer code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult() {
    }
}
