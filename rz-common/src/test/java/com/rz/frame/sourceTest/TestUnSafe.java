package com.rz.frame.sourceTest;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestUnSafe{
    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        Integer i=0;
        System.out.println("ok");
        // System.out.println(sizeOf(f));
        // U.compareAndSwapInt(i, arg1, arg2, arg3)
    }

    // public static long sizeOf(Object o) {
    //     Unsafe u = getUnsafe();
    //     HashSet<Field> fields = new HashSet<Field>();
    //     Class c = o.getClass();
    //     while (c != Object.class) {
    //         for (Field f : c.getDeclaredFields()) {
    //             if ((f.getModifiers() & Modifier.STATIC) == 0) {
    //                 fields.add(f);
    //             }
    //         }
    //         c = c.getSuperclass();
    //     }
    
    //     // get offset
    //     long maxSize = 0;
    //     for (Field f : fields) {
    //         long offset = u.objectFieldOffset(f);
    //         if (offset > maxSize) {
    //             maxSize = offset;
    //         }
    //     }
    
    //     return ((maxSize/8) + 1) * 8;   // padding
    // }
}