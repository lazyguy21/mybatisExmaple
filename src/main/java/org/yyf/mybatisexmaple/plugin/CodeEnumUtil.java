package org.yyf.mybatisexmaple.plugin;

import java.util.Objects;

public class CodeEnumUtil {

    public static <E extends Enum<?> & CodeInsideEnum> E codeOf(Class<E> enumClass, Integer code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
          if (Objects.equals(e.getCode(),code)) {
            return e;
          }
        }
        return null;
    }
}