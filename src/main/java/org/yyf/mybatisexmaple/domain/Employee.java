package org.yyf.mybatisexmaple.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by ye on 15-7-14.
 */
@Data
public class Employee {
    private Long id;
    private String name;
    private Integer age;
    private Double salary;
    private Double lat;
    private Double lng;
    private Date createTime;
}