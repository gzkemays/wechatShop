package com.myself.supermarket.utils;

import com.myself.supermarket.code.UUIDTestNum;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderNumGenerate {
    private static ThreadLocal<String> tlSDF = new ThreadLocal<>();
    @Autowired
    public void setTlSDF(ThreadLocal tl){
        OrderNumGenerate.tlSDF = tl;
    }
    private static final AtomicInteger atomicInteger = new AtomicInteger(100000);

    /**
     * 生成唯一订单号
     */
    public static String getOrderNoByUUID(){
        tlSDF.set(new SimpleDateFormat("DHHmmss").format(new Date()));

        Integer uuidHashCode = UUID.randomUUID().toString().hashCode();
        if(uuidHashCode<0){
            uuidHashCode = -1*uuidHashCode;
        }
        String date = tlSDF.get();
        return date+uuidHashCode;
    }
    /**
     * 同一秒生成的订单号
     */
    public static String getOrderNoByAtomic(){
        atomicInteger.getAndIncrement();
        int i = atomicInteger.get();
        tlSDF.set(new SimpleDateFormat("DHHmmss").format(new Date()));
        String date = tlSDF.get();
        return date+i;
    }
}
