package com.newsapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Lokesh Mudgal on 1/12/20.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
