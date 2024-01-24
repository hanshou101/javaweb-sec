package com.anbai.sec.reflection;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Creator: yz
 * Date: 2019/12/18
 */
public class TestReflection {

    public static void main(String[] args) {
        try {
            String cmd = "whoami";
            String className = "java.lang.Runtime";
            Class runtimeClass1 = Class.forName(className);
            Class runtimeClass2 = java.lang.Runtime.class;
            Class runtimeClass3 = ClassLoader.getSystemClassLoader().loadClass(className);
            // 获取构造方法
            Constructor constructor = runtimeClass1.getDeclaredConstructor();
            constructor.setAccessible(true);
            // 创建Runtime类示例，等价于 Runtime rt = new Runtime();
            Object runtimeInstance = constructor.newInstance();
            // 获取Runtime的exec(String cmd)方法
            Method runtimeMethod = runtimeClass1.getMethod("exec", String.class);
            // 调用exec方法，等价于 rt.exec(cmd);
            Process process = (Process) runtimeMethod.invoke(runtimeInstance, cmd);
            // 获取命令执行结果
            InputStream in = process.getInputStream();
            // 输出命令执行结果
            System.out.println(IOUtils.toString(in, "UTF-8"));

            func2(runtimeClass1, runtimeInstance);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void func2(Class runtimeClass1, Object runtimeInstance) throws Exception {
        System.out.println("------------------------------反射获取成员变量------------------------------");
        Field[] fields = runtimeClass1.getDeclaredFields();


        // 获取Runtime类的currentRuntime变量Field对象
        Field field = runtimeClass1.getDeclaredField("currentRuntime");


        // 反射获取Field类的modifiers
        Field modifiers = field.getClass().getDeclaredField("modifiers");
        // 设置modifiers修改权限
        modifiers.setAccessible(true);
        // 修改成员变量的Field对象的modifiers值
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.setAccessible(true);

        Runtime rt_1 = (Runtime) field.get(runtimeInstance);
        System.out.println(rt_1);
        System.out.println(System.identityHashCode(rt_1));
        System.out.println(System.identityHashCode("123"));
        System.out.println(Integer.toBinaryString(System.identityHashCode(rt_1)));
        System.out.println(Integer.toBinaryString(System.identityHashCode("123")));

        // 修改获取Runtime的currentRuntime变量值为null
        field.set(runtimeInstance, null);
        // 获取Runtime的currentRuntime变量值
        Runtime rt_2 = (Runtime) field.get(runtimeInstance);
        System.out.println(rt_2);
    }


}
