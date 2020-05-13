package com.vult.clipboard.service;

import com.vult.clipboard.entity.Content;


import java.util.List;

/**
 * @Author: Songyi Wang
 * @Email: wangsongyi08@gmail.com
 * @Date: 5/4/2020 9:15 PM
 */
public interface ContentService {
    Content get(Integer id);
    List<Content> getAll();
    List<Content> getSome(int start, int offset, int order);
    void deleteContent(Integer id);
    void createContent(String title, String context);
    void updateContent(Integer id, String title, String context);
    int getNumber();
}
