package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 中医典籍：(TraditionalChineseMedicineClassics)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "TraditionalChineseMedicineClassics")
public class TraditionalChineseMedicineClassics implements Serializable {

    // TraditionalChineseMedicineClassics编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "traditional_chinese_medicine_classics_id")
    private Integer traditional_chinese_medicine_classics_id;

    // 典籍名称
    @Basic
    private String name_of_classics;
    // 典籍类型
    @Basic
    private String types_of_classics;
    // 作者名称
    @Basic
    private String author_name;
    // 典籍图片
    @Basic
    private String picture_of_classics;
    // 典籍附件
    @Basic
    private String appendix_to_classic_books;
    // 典籍简介
    @Basic
    private String introduction_to_classical_books;

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
