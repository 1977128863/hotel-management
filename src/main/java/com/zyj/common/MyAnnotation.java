package com.zyj.common;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String[] value() default {};
}
