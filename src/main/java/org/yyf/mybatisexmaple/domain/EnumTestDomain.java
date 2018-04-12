package org.yyf.mybatisexmaple.domain;

import org.yyf.mybatisexmaple.plugin.ComputerState;

import lombok.Data;

/**
 * Created by yeyf on 2014-12-18.
 */
@Data
public class EnumTestDomain {
    private Long id;
    private Color color;
    private Level level;
    private ComputerState computerState;

}
