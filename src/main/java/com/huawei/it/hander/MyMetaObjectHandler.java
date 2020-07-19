package com.huawei.it.hander;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.sound.midi.MetaEventListener;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName MyMetaObjectHandler
 * Description TODO
 * @Author 曹先生
 * @Date 2020/7/19 10:36
 * @Version 1.0
 **/
@Slf4j
@Component //一定不要忘记把我们的处理器加入到IOC容器中
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    //更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
