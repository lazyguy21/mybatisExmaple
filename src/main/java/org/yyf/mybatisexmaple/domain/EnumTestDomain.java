package org.yyf.mybatisexmaple.domain;

/**
 * Created by yeyf on 2014-12-18.
 */
public class EnumTestDomain {
    private Long id;
    private Color color;
    private Level level;

    @Override
    public String toString() {
        return "EnumTestDomain{" +
                "id=" + id +
                ", color=" + color +
                ", level=" + level +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
