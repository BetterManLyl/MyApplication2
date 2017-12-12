// demo.aidl
package com.example.lyl.myapplication;

// Declare any non-default types here with import statements

interface demo {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
