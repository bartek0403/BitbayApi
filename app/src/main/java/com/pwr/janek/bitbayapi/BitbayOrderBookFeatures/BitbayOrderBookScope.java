package com.pwr.janek.bitbayapi.BitbayOrderBookFeatures;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


/*
    Sposób definiowania singletonów
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface BitbayOrderBookScope {

}