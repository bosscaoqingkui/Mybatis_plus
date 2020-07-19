package com.huawei.it.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName user
 * Description TODO
 * @Author 曹先生
 * @Date 2020/7/18 21:18
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class user implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableLogic //逻辑删除注解
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @Version//乐观锁的Version注解
    private Integer version;
}
