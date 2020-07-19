package com.huawei.it;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huawei.it.mapper.UserMapper;
import com.huawei.it.pojo.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    //继承BaseMapper，所有方法都来自父类
    //我们也可以编写自己的扩展方法
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        //参数是一个Wrapper，条件构造器，我们先不使用
        List<user> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    //测试插入
    @Test
    public void testInsert() {
        user user1 = new user();
        user1.setAge(3);
        user1.setName("qkcao");
        user1.setEmail("2530783404@qq.com");
        int result = userMapper.insert(user1); //帮我们自动生成id
        System.out.println(result); //受影响的行数
        System.out.println(user1); //发现，id会自动回填
    }

    //测试乐观锁(成功)
    @Test
    public void TestOptimisticLocker() {
        user user1 = userMapper.selectById(1L);
        user1.setName("qkcao");
        user1.setEmail("235466@qq.com");
        userMapper.updateById(user1);
    }

    //测试乐观锁(失败)多线程下
    @Test
    public void TestOptimisticLocker2() {
        //线程1
        user user1 = userMapper.selectById(1L);
        user1.setName("qkcao1111");
        user1.setEmail("235466@qq.com");

        //线程2
        user user2 = userMapper.selectById(1L);
        user2.setName("qkcao2222");
        user2.setEmail("235466@qq.com");
        userMapper.updateById(user2);

        userMapper.updateById(user1); //如果没有乐观锁就会覆盖插队线程的值！
    }


    //批量查询
    @Test
    public void testSelectBatchIds() {
        List<user> userList = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        userList.forEach(System.out::println);
    }

    //条件查询之一使用map操作
    @Test
    public void testMap() {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name","qkcao");
        objectObjectHashMap.put("age",18);
        List<user> userList = userMapper.selectByMap(objectObjectHashMap);
        userList.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    public void testPage() {
        //参数一：当前页，参数二：页面的大小
        Page<user> page = new Page<>(2,5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }

    @Test
    public void testDelete() {
        userMapper.deleteById(1L);
    }

    @Test
    public void testSelect() {
        user user1 = userMapper.selectById(1L);
        System.out.println(user1);
    }
}
