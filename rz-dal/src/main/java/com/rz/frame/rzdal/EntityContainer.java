package com.rz.frame.rzdal;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class EntityContainer {

    public static ConcurrentHashMap<String, List<Field>> ClassContainer = new ConcurrentHashMap<>();


    public static void init() throws Exception {
        if (ClassContainer == null) {
            ClassContainer = new ConcurrentHashMap<>();
        }
        List<String> className = getClassName(GlobalConstat.packageName.replace(".", "/"), new ArrayList<>());
        System.out.println(className);


    }

    private static List<String> getClassName(String filePath, List<String> className) {
        List<String> myClassName = new ArrayList<String>();
        File file = new File(ClassLoader.getSystemResource("").getPath() + filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                myClassName.addAll(getClassName(childFile.getPath(), myClassName));
            } else {
                String childFilePath = childFile.getPath();
                childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
                childFilePath = childFilePath.replace("\\", ".");
                myClassName.add(childFilePath);
            }
        }

        return myClassName;

    }

    public static void main(String[] args) {
        try {
            EntityContainer.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(EntityContainer.ClassContainer);
    }

}
