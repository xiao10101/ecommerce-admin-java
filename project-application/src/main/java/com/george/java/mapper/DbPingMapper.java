package com.george.java.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DbPingMapper {

    @Select("SELECT 1")
    int ping();
}
