package com.vult.clipboard.service.impl;

import com.vult.clipboard.entity.Content;
import com.vult.clipboard.mapper.ContentMapper;
import com.vult.clipboard.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Songyi Wang
 * @Email: wangsongyi08@gmail.com
 * @Date: 5/4/2020 9:18 PM
 */

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public Content get(Integer id) {
        return contentMapper.getById(id);
    }

    @Override
    public List<Content> getAll() {
        return contentMapper.getAll();
    }

    @Override
    public List<Content> getSome(int start, int offset, int order) {
        if (order == 0)
            return contentMapper.getSomeOrderById(start, offset);
        else if (order == 1)
            return contentMapper.getSomeOrderByCreateTime(start, offset);
        else if (order == 2)
            return contentMapper.getSomeOrderByUpdateTime(start, offset);
        else
            throw new RuntimeException("Invalid parameter order");
    }

    @Override
    public void deleteContent(Integer id) {
        if (contentMapper.getById(id) == null)
            throw new RuntimeException("content not exist");
        contentMapper.deleteById(id);
    }

    @Override
    public void createContent(String title, String context) {
        Date date = new Date();
        Content content = new Content(title, context);
        content.setCreateTime(date);
        content.setUpdateTime(date);
        contentMapper.insert(content);
    }

    @Override
    public void updateContent(Integer id, String title, String context) {
        Content queryContent = contentMapper.getById(id);
        if (queryContent == null)
            throw new RuntimeException("content not exist");

        Date date = new Date();
        queryContent.setUpdateTime(date);
        queryContent.setTitle(title);
        queryContent.setContext(context);
        contentMapper.update(queryContent);
    }

    @Override
    public int getNumber() {
        return contentMapper.count();
    }
}
