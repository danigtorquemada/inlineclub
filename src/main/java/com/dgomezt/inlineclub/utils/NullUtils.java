package com.dgomezt.inlineclub.utils;

import java.util.function.Consumer;

public class NullUtils {
    public static <T> void updateIfPresent(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
