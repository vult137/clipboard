package com.vult.clipboard.mapper;

import com.vult.clipboard.entity.Content;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Songyi Wang
 * @Email: wangsongyi08@gmail.com
 * @Date: 5/4/2020 7:39 PM
 */

@Mapper
@Repository
public interface ContentMapper {

    @Select("SELECT * FROM content WHERE id = #{id}")
    Content getById(@Param("id") Integer id);

    @Select("SELECT * FROM content")
    List<Content> getAll();

    @Select("SELECT * FROM content ORDER BY id ASC LIMIT #{start}, #{offset}")
    List<Content> getSomeOrderById(@Param("start") int start, @Param("offset") int offset);

    @Select("SELECT * FROM content ORDER BY create_time DESC LIMIT #{start}, #{offset}")
    List<Content> getSomeOrderByCreateTime(@Param("start") int start, @Param("offset") int offset);

    @Select("SELECT * FROM content ORDER BY update_time DESC LIMIT #{start}, #{offset}")
    List<Content> getSomeOrderByUpdateTime(@Param("start") int start, @Param("offset") int offset);

    @Delete("DELETE FROM content WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);

    @Insert("INSERT INTO content(title, context, create_time, update_time) " +
            "VALUES(#{title}, #{context}, #{createTime}, #{updateTime})")
    void insert(Content content);

    @Update("UPDATE content SET title = #{title}, context = #{context}, update_time = #{updateTime} WHERE id = #{id}")
    void update(Content content);

    @Select("SELECT COUNT(*) FROM content")
    int count();

}
