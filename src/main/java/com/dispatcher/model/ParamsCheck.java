package com.dispatcher.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamsCheck {
	String value() default "";
	boolean notNull() default false;
	int length() default 0;
	int precision() default 0;
	int scale() default 0;
}
