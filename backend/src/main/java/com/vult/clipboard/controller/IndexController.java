package com.vult.clipboard.controller;

import com.vult.clipboard.common.entity.Result;
import com.vult.clipboard.common.utils.ResultTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Songyi Wang
 * @Email: wangsongyi08@gmail.com
 * @Date: 5/4/2020 9:31 PM
 */
@RestController
public class IndexController {

    @GetMapping("")
    public Result<String> index() {
        return ResultTool.success("Hello!");
    }
}
