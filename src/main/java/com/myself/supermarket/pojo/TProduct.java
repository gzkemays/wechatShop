package com.myself.supermarket.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

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
public class TProduct implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String details;

    private String picture;

    private Long price;

    private String productName;

    private Long repertory;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Long typeId;

    @TableField(exist = false)
    private Long pNum;

    @TableField(exist = false)
    private TType tType;

}
