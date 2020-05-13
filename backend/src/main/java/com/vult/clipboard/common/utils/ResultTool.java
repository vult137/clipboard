package com.vult.clipboard.common.utils;

import com.vult.clipboard.common.entity.Result;
import com.vult.clipboard.common.enums.ResultCode;

/**
 * @Author: Songyi Wang
 * @Email: wangsongyi08@gmail.com
 * @Date: 5/4/2020 9:30 PM
 */
public class ResultTool {
    public static Result<Void> success() {
        return new Result<>(true);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, data);
    }

    public static Result<Void> fail() {
        return new Result<>(false);
    }

    public static Result<Void> fail(ResultCode resultEnum) {
        return new Result<>(false, resultEnum);
    }

    public static <T> Result<T> fail(ResultCode resultEnum, T data) {
        return new Result<>(false, resultEnum, data);
    }
}
