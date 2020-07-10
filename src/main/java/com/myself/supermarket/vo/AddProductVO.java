package com.myself.supermarket.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AddProductVO {
    private Long userId;
    private ArrayList<Long> productsId;
    private ArrayList<Long> productsNum;
}
