package com.myself.supermarket.vo;

import lombok.Data;

import java.util.ArrayList;


@Data
public class OrderVO {
    private String username;
    private String orderNumber;
    private String productsId;
    private String productsNum;

}
