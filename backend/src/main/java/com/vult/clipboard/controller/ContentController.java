package com.vult.clipboard.controller;

import com.alibaba.fastjson.JSONObject;
import com.vult.clipboard.common.entity.Result;
import com.vult.clipboard.common.enums.ResultCode;
import com.vult.clipboard.common.utils.ResultTool;
import com.vult.clipboard.entity.Content;
import com.vult.clipboard.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

/**
 * @Author: Songyi Wang
 * @Email: wangsongyi08@gmail.com
 * @Date: 5/4/2020 9:32 PM
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    public static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private ContentService contentService;

    @GetMapping("/{id}")
    public ResponseEntity<Result<Content>> getById(@PathVariable("id") Integer id) {
        Content content = contentService.get(id);
        if (content == null)
            return new ResponseEntity<>(ResultTool.fail(ResultCode.CONTENT_NOT_EXIST, null), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ResultTool.success(content), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Result<List<Content>>> getByPage(@RequestParam("start") int start,
                                           @RequestParam("offset") int offset,
                                           @RequestParam("order") int order) {
        if (offset > 20)
            return new ResponseEntity<>(ResultTool.fail(ResultCode.PARAM_NOT_VALID, null),
                    HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(ResultTool.success(contentService.getSome(start, offset, order)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteContent(@PathVariable("id") Integer id) {
        try {
            contentService.deleteContent(id);
            return new ResponseEntity<>(ResultTool.success(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(MessageFormat.format("Can not delete content {0}. No such content", id));
            return new ResponseEntity<>(ResultTool.fail(ResultCode.CONTENT_NOT_EXIST), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "", produces = "application/json;charset=utf-8")
    public ResponseEntity<Result<Void>> createContent(@RequestBody JSONObject contentInfo) {
        String title = contentInfo.getString("title");
        String context = contentInfo.getString("context");
        if (context == null || context.equals(""))
            return new ResponseEntity<>(ResultTool.fail(ResultCode.PARAM_NOT_VALID), HttpStatus.BAD_REQUEST);
        contentService.createContent(title, context);
        return new ResponseEntity<>(ResultTool.success(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = "application/json;charset=utf-8")
    public ResponseEntity<Result<Void>> updateContent(@PathVariable("id") Integer id,@RequestBody JSONObject contentInfo) {
        String title = contentInfo.getString("title");
        String context = contentInfo.getString("context");
        if (context == null || context.equals(""))
            return new ResponseEntity<>(ResultTool.fail(ResultCode.PARAM_NOT_VALID), HttpStatus.BAD_REQUEST);
        try {
            contentService.updateContent(id, title, context);
            return new ResponseEntity<>(ResultTool.success(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(MessageFormat.format("Can not update content {0}. No such Content.", id));
            return new ResponseEntity<>(ResultTool.fail(ResultCode.PARAM_NOT_VALID), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Result<Integer>> getNumber() {
        return new ResponseEntity<>(ResultTool.success(contentService.getNumber()), HttpStatus.OK);
    }
}
