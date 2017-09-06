package com.hjw.jsbridgeannotaton;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hjw on 2017/9/5.14:41
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface BridgePath {
    String path();
}
