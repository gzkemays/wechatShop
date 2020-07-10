package com.myself.supermarket.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzkemays
 * @since 2020-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TProductOrders implements Serializable {

    private static final long serialVersionUID=1L;

    private Long productId;

    private Long ordersId;


}
