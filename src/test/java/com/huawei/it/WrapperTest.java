package com.huawei.it;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huawei.it.mapper.UserMapper;
import com.huawei.it.pojo.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @ClassName WrapperTest
 * Description TODO
 * @Author 曹先生
 * @Date 2020/7/19 16:27
 * @Version 1.0
 **/

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        //查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于12
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age",12);
        userMapper.selectList(queryWrapper).forEach(System.out::println);
        //与刚才的map操作比较一下
    }

    //查询一个用户
    @Test
    public void test2() {
        QueryWrapper<user> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("name","Tom");
        user user1 = userMapper.selectOne(objectQueryWrapper);
        System.out.println(user1);
    }

    //查询20岁到30岁之间的用户
    @Test
    public void test3() {
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age",20,30);
        Integer integer = userMapper.selectCount(queryWrapper);
        System.out.println(integer);
    }
    //模糊查询
    @Test
    public void test4() {
        //查询名字中不带有e的，而且名字左边k开头
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.notLike("name","e")
                .likeRight("name","J");//k%
        List<Map<String, Object>> maps =  userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }
}
