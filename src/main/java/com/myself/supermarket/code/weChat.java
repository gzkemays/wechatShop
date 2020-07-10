package com.myself.supermarket.code;

import com.myself.supermarket.pojo.TUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_wechat")
public class weChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 用户
    @OneToOne
    private User user_id;
    // 客服
    @OneToOne
    private admin admin;
    // 聊天内容
    private String chatHistory;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
