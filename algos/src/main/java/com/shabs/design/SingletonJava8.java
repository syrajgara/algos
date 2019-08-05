package com.shabs.design;

/**
 * Using an enum! : (The simplest way to define a singleton! And guess what! Enums are lazily initialized
 * by the JVM, ie they are instantiated the first time they are accessed! The creation of an enum is
 * thread safe too, the JVM ensures that!
 */
public enum SingletonJava8 {
  INSTANCE;
}