package com.myself.supermarket.pojo;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
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
public class TOrder implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Long orderNumber;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Long userId;

    private boolean used;

    @TableField(exist = false)
    private TUser user;

    @TableField(exist = false)
    private List<TProduct> product;
    private String telPhone;
    private String address;
    private String consignee;
}
