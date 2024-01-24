package com.anbai.sec.rmi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.rmi.Naming;

import static com.anbai.sec.rmi.RMIServerTest.RMI_NAME;

public class RMIClientTest {

    public static void main(String[] args) {
        try {
            // 查找远程RMI服务
            RMITestInterface rt = (RMITestInterface) Naming.lookup(RMI_NAME);

            // RemoteObjectInvocationHandler ———— RemoteObject
            /**
             * Proxy 接口  &   RMITestInterface接口
             *
             * RemoteObjectInvocationHandler 实现了  RemoteObject  再实现了  Remote。
             *
             * Proxy做了一个铰合。
             *
             */

            // rt.test();
            // WARN 没有第二种选择，只能足够简化的  去调用【RMITestInterface】。
            /**
             * 类比：    ——————>  SpringBoot的  【Service层】。
             */

            // 调用远程接口RMITestInterface类的test方法
            Object result = rt.test(new Client_PayloadObject());

            // 输出RMI方法调用结果
            System.out.println(result);            // TIP result，和  RMIServerTest.java  的【 new RMITestImpl() 】  这个相关。
            int b = 1 + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Server_PayloadObject implements Serializable {
        private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
            System.out.println("readObject...");

            Runtime.getRuntime().exec("cmd /c start  https://www.baidu.com/s?wd=来自服务端Server的payload");

            // 调用ObjectInputStream默认反序列化方法
            ois.defaultReadObject();

            // 省去调用自定义反序列化逻辑...
        }
    }

}