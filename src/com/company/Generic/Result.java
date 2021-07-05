package com.company.Generic;

/**
 * @Description Result
 * @Author stopping
 * @date: 2021/7/4 17:27
 */
public class Result<T> {
    /**
     * 状态码
     * */
    private Integer code;
    /**
     * 返回数据
     * */
    private T data;
    /**
     * 提示信息
     * */
    private String massage;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Result(Integer code, T data, String massage) {
        this.code = code;
        this.data = data;
        this.massage = massage;
    }

    public Result() {
    }

    /**
     * @Description 交互成功
     * @Author stopping
     */
    public Result<T> success(T data){
        return new Result<>(200,data,"success");
    }
    /**
     * @Description 失败成功
     * @Author stopping
     */
    public Result<T> fail(T data, String massage){
        return new Result<>(501,data,massage);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", data=" + data +
                ", massage='" + massage + '\'' +
                '}';
    }
}
