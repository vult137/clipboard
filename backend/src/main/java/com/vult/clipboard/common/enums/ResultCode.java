package com.vult.clipboard.common.enums;

/**
 * @Author: Songyi Wang
 * @Email: wangsongyi08@gmail.com
 * @Date: 5/4/2020 9:27 PM
 */
public enum ResultCode {
    SUCCESS(200, "success"),

    COMMON_FAIL(999, "fail"),

    PARAM_NOT_VALID(1001, "parameter invalid "),
    PARAM_IS_BLANK(1002, "parameter blank"),
    PARAM_TYPE_ERROR(1003, "parameter type wrong"),
    PARAM_NOT_COMPLETE(1004, "parameter not complete"),

    CONTENT_NOT_EXIST(3001, "content not exist"),
    CONTENT_ALREADY_EXIST(3002, "content already exist"),

    SYSTEM_INNER_ERROR(5001, "system inner error");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
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

    public static String getMessageByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
