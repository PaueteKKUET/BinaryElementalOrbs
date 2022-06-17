package com.fadedbytes.BinaryElementalOrbs.event;

import com.fadedbytes.BinaryElementalOrbs.event.events.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventListener {

}
