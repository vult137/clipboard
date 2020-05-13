package com.vult.clipboard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Songyi Wang
 * @Email: wangsongyi08@gmail.com
 * @Date: 5/11/2020 4:11 PM
 */

@Mapper
@Repository
public interface WhiteListMapper {
    @Select("SELECT * FROM ip_whitelist")
    List<String> getAll();
}
