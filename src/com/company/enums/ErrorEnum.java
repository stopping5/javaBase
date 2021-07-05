package com.company.enums;

/**
 * @Description ErrorEnum
 * @Author stopping
 * @date: 2021/7/5 22:06
 */
public enum ErrorEnum {
    /**
     * 成功
     * */
    SUCCESS(200,"success"){
        @Override
        public void say() {
            System.out.println("this success enum");
        }
    },
    /**
     * 异常
     * */
    EXCEPTION(501,"exception") {
        @Override
        public void say() {
            System.out.println("this exception enum");
        }
    };

    private Integer code;
    private String msg;
    
    abstract public void say();

    ErrorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
