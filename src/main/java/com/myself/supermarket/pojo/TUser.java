package com.myself.supermarket.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUser implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String address;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Integer gender;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String username;

    private String openId;

    private String avatarUrl;

    // 消息条目数
    @TableField(exist = false)
    private Long messNum;

    @TableField(exist = false)
    private List<TOrder> orders;

}
