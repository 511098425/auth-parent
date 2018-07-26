package com.chang.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户对应权限实体
 *
 * @author Mr.Chang
 * @create 2018-07-25 9:39
 **/
@Data
@Table(name = "authorities")
public class GrantAuthority implements Serializable {

    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

}
