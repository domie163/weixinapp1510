package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 饮食养生：(DietaryHealthPreservation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DietaryHealthPreservation")
public class DietaryHealthPreservation implements Serializable {

    // DietaryHealthPreservation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dietary_health_preservation_id")
    private Integer dietary_health_preservation_id;

    // 养生标题
    @Basic
    private String health_title;
    // 养生类型
    @Basic
    private String type_of_health_preservation;
    // 封面图片
    @Basic
    private String cover_photo;
    // 发布日期
    @Basic
    private Timestamp release_date;
    // 养生方法
    @Basic
    private String health_preservation_methods;
    // 养生方案
    @Basic
    private String health_preservation_plan;

    // 点击数
    @Basic
    private Integer hits;

    // 点赞数
    @Basic
    private Integer praise_len;












    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
