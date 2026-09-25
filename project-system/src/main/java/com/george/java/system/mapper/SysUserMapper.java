package com.george.java.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.george.java.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
