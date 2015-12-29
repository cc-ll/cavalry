package com.cavalry.lombok;

import lombok.Data;

import java.util.Date;

/**
 * author: master
 * date: 2015/12/29
 */
@Data
public class User {
    private String userName;
    private int userId;
    private Date birthday;
}
