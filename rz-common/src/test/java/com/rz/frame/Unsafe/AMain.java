package com.rz.frame.Unsafe;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

import static com.rz.frame.Unsafe.ShallowCopy.toAddress;

public class AMain {

    public static void main(String[] args) throws Exception {
        Unsafe unsafe = UnsafeUtils.getUnsafe();

        try {
//            Constructor<LazySingleton> constructor = LazySingleton.class.getDeclaredConstructor();// reflection
//            constructor.setAccessible(true);
//            LazySingleton lazySingleton = constructor.newInstance();


            LazySingleton o3 = (LazySingleton) UnsafeUtils.getUnsafe().allocateInstance(LazySingleton.class); // unsafe
            System.out.println(o3); // prints 0
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //内存崩溃
        //        Guard guard=new Guard();
        //        System.out.println("修改前："+guard.giveAccess());
        //        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        //        unsafe.putInt(guard,  unsafe.objectFieldOffset(f), 42); // memory corruption
        //        System.out.println("修改后："+guard.giveAccess());
        //
        //        Guard o =(Guard) ShallowCopy.shallowCopy(guard);
        //        System.out.println(o.giveAccess());

        //        String password = new String("l00k@myHor$e");
        //        String fake = new String(password.replaceAll(".", "?"));
        //        System.out.println(password); // l00k@myHor$e
        //        System.out.println(fake); // ????????????
        //
        //        unsafe.copyMemory(
        //                fake, 0L, null, toAddress(password), sizeOf(password));
        //
        //        System.out.println(password); // ????????????
        //        System.out.println(fake); // ????????????
        //        byte[] classContents = getClassContent();
        //        Class c = unsafe.defineClass(null, classContents, 0, classContents.length,null,null);
        //        Object main = c.getMethod("test").invoke(c.newInstance(), null);// 1
        //        System.out.println(main);

    }

    private static byte[] getClassContent() throws Exception {
        File f = new File("D:\\work\\open\\rzframe\\rz-common\\src\\test\\java\\com\\rz\\frame\\cas\\AppTest.class");
        FileInputStream input = new FileInputStream(f);
        byte[] content = new byte[(int) f.length()];
        input.read(content);
        input.close();
        return content;
    }

    public static long sizeOf(Object o) {
        Unsafe u = UnsafeUtils.getUnsafe();
        HashSet<Field> fields = new HashSet<Field>();
        Class c = o.getClass();
        while (c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }

        // get offset
        long maxSize = 0;
        for (Field f : fields) {
            long offset = u.objectFieldOffset(f);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }

        return ((maxSize / 8) + 1) * 8;   // padding
    }

    public static long sizeOf1(Object object) {
        return UnsafeUtils.getUnsafe().getAddress(normalize(UnsafeUtils.getUnsafe().getInt(object, 4L)) + 12L);
    }

    private static long normalize(int value) {
        if (value >= 0)
            return value;
        return (~0L >>> 32) & value;
    }
}
