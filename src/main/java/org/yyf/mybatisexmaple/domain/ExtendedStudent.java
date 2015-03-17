package org.yyf.mybatisexmaple.domain;

/**
 * Created by yeyf on 2015-3-11.
 */
public class ExtendedStudent extends Student {
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
