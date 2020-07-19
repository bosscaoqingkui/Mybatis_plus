package com.huawei.it.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huawei.it.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author 曹先生
 * @Date 2020/7/18 21:22
 * @Version 1.0
 */
//@Mapper
@Repository //持久层  两个注解都可以
public interface UserMapper extends BaseMapper<user> {
}
