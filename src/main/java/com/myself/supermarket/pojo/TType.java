package com.myself.supermarket.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

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
public class TType implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String name;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Long parentTypeId;

    @TableField(exist = false)
    private TParentType parentType;

    private String url;

}
