package com.common.test;

import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pascalstammer
 * @version 05.04.17.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@IndexAnnotated
public @interface Test {
    Class<? extends Exception>[] shouldThrow() default {};
}
