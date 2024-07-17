package com.doubao.sysblogs.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // get set 方法
@NoArgsConstructor // 无参构造器
@AllArgsConstructor // 有参构造器
/**
 * {
 *     "code":20000,
 *     "data":{
 *         "roles":[
 *             "admin"
 *         ],
 *         "introduction":"I am a super administrator",
 *         "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
 *         "name":"Super Admin"
 *     }
 * }
 */
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;



    // 成功的一系列重载
    public static <T> Result<T> success() {
        return new Result<>(20000, "success", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(20000, "success", data);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(20000, message, data);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(20000, message, null);
    }

    // 失败的一系列重载
    public static<T>  Result<T> fail(){
        return new Result<>(20001,"fail",null);
    }

    public static<T>  Result<T> fail(Integer code){
        return new Result<>(code,"fail",null);
    }

    public static<T>  Result<T> fail(Integer code, String message){
        return new Result<>(code,message,null);
    }

    public static<T>  Result<T> fail( String message){
        return new Result<>(20001,message,null);
    }

}
