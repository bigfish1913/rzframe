package com.rz.frame.dynamicproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class RzProxy {

    public Object getProxy(ClassLoader classLoader, Class[] clazz, Object obj) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        StringBuilder classContent = new StringBuilder();
        classContent.append("package com.rz.frame.dynamicproxy;");
        Class targetInfo = clazz[0];
        String targetInfoName = targetInfo.getSimpleName();
        String dynamicName = obj.getClass().getSimpleName();
        classContent.append(" import ").append(targetInfo.getName()).append(";");
        classContent.append("import java.lang.reflect.Method;");
        classContent.append("public class $Proxy implements ").append(targetInfoName);
        classContent.append("{private ").append(dynamicName).append(" target;");
        classContent.append("public $Proxy(").append(dynamicName).append(" target){this.target=target;}");
        Method[] declaredMethods = targetInfo.getDeclaredMethods();
        for (Method m : declaredMethods) {
            String methodName = m.getName();
            Class returnTYpe = m.getReturnType();
            Class<?>[] parameterTypes = m.getParameterTypes();
            StringBuilder argContent = new StringBuilder();
            StringBuilder argsName = new StringBuilder();
            StringBuilder argsClass = new StringBuilder();
            int i = 0;
            for (Class param : parameterTypes) {
                String sName = param.getSimpleName();
                argContent.append(sName).append(" var").append(i).append(",");
                argsName.append(" var").append(i).append(",");
                argsClass.append(" var").append(i).append(".getClass(),");

                i++;
            }
            if (argContent.length() > 0) {
                argContent = new StringBuilder(argContent.substring(0, argContent.lastIndexOf(",")));
                argsName = new StringBuilder(argsName.substring(0, argsName.lastIndexOf(",")));
                argsClass = new StringBuilder(argsClass.substring(0, argsClass.lastIndexOf(",")));
            }
            classContent.append("public ").append(returnTYpe.getSimpleName()).append(" ").append(methodName).append("(").append(argContent).append("){");
            classContent.append(" try{Method m =").append(targetInfoName).append(".class.getDeclaredMethod(\"").append(methodName).append("\",").append(argsClass).append(");");

            classContent.append(" Object invoke = this.target.invoke(").append("this,m,new Object[]{ ").append(argsName).append("});");
            classContent.append("return (").append(returnTYpe.getSimpleName()).append(")invoke;} catch (Throwable ex) {return null;}}}");


        }
        System.out.println(classContent);

        File file = new File(this.getClass().getResource("").getPath().concat("$Proxy.java"));

        FileWriter fw = new FileWriter(file);
        fw.write(classContent.toString());
        fw.flush();
        fw.close();

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable unit = fileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, fileManager, null, null, null, unit);
        task.call();
        fileManager.close();

        Class<?> aClass = classLoader.loadClass("com.rz.frame.dynamicproxy.$Proxy");
        Constructor<?> constructor = aClass.getConstructor(obj.getClass());
        Object o = constructor.newInstance(obj);
        return o;

        //        return null;

    }


}
