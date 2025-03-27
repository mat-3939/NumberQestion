package com.example.numberqestion.repository;

import org.apache.ibatis.annotations.*;
import com.example.numberqestion.entity.qestion;
import java.util.List;

@Mapper
public interface NumberQestionMapper {
    @Select("SELECT id, play_date as playDate, play_name as playName, clear_count as clearCount FROM question")
    List<qestion> selectAll();

    @Insert("INSERT INTO question (play_date, play_name, clear_count) VALUES (#{playDate}, #{playName}, #{clearCount})")
    void insert(qestion qestion);

    @Delete("DELETE FROM question WHERE id = #{id}")
    void delete(int id);
}
