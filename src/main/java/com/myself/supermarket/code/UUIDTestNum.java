package com.myself.supermarket.code;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class UUIDTestNum {
    private static ThreadLocal<String> tlSDF = new ThreadLocal<>();
    @Autowired
    public void setTlSDF(ThreadLocal tl){UUIDTestNum.tlSDF = tl;}
    private static final AtomicInteger atomicInteger = new AtomicInteger(100000);

    /**
     * 生成唯一订单号
     * @return
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
     * @param
     */
    public static String getOrderNoByAtomic(){
        atomicInteger.getAndIncrement();
        int i = atomicInteger.get();
        tlSDF.set(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        String date = tlSDF.get();
        return date+i;
    }
    public static void main(String[] args) {
        System.out.println("uuid:" + getOrderNoByUUID());
        System.out.println("atomic:"+getOrderNoByAtomic());
    }
}
