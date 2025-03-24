package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 疾病防治：(DiseaseControl)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DiseaseControl")
public class DiseaseControl implements Serializable {

    // DiseaseControl编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_control_id")
    private Integer disease_control_id;

    // 标题名称
    @Basic
    private String title_name;
    // 疾病类型
    @Basic
    private String disease_type;
    // 症状反应
    @Basic
    private String symptom_response;
    // 建议措施
    @Basic
    private String recommended_measure;


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
