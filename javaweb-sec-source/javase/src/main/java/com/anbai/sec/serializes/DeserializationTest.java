package com.anbai.sec.serializes;

import java.io.*;
import java.util.Arrays;

/**
 * Creator: yz
 * Date: 2019/12/15
 */
public class DeserializationTest implements Serializable {

    private String username;

    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public DeserializationTest() {
        System.out.println("init...");
    }

    /**
     * 自定义反序列化类对象
     *
     * @param ois 反序列化输入流对象
     * @throws IOException            IO异常
     * @throws ClassNotFoundException 类未找到异常
     */
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        System.out.println("readObject...");

        Runtime.getRuntime().exec("calc");

        // 调用ObjectInputStream默认反序列化方法
        ois.defaultReadObject();

        // 省去调用自定义反序列化逻辑...
    }

    /**
     * 自定义序列化类对象
     *
     * @param oos 序列化输出流对象
     * @throws IOException IO异常
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();

        System.out.println("writeObject...");
        // 省去调用自定义序列化逻辑...
    }


    public static byte[] _本地攻击() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 创建DeserializationTest类，并类设置属性值
        DeserializationTest t = new DeserializationTest();
        t.setUsername("yz");
        t.setEmail("admin@javaweb.org");

        // 创建Java对象序列化输出流对象
        ObjectOutputStream out = new ObjectOutputStream(baos);

        // 序列化DeserializationTest类
        out.writeObject(t);
        out.flush();
        out.close();

        // 打印DeserializationTest类序列化以后的字节数组，我们可以将其存储到文件中或者通过Socket发送到远程服务地址
        System.out.println("DeserializationTest类序列化后的字节数组:" + Arrays.toString(baos.toByteArray()));
        return baos.toByteArray();
    }

    public static void _服务器读取(byte[] payload) throws Exception {
        // 利用DeserializationTest类生成的二进制数组创建二进制输入流对象用于反序列化操作
        ByteArrayInputStream bais = new ByteArrayInputStream(payload);

        // 通过反序列化输入流(bais),创建Java对象输入流(ObjectInputStream)对象
        ObjectInputStream in = new ObjectInputStream(bais);

        // 反序列化输入流数据为DeserializationTest对象
        DeserializationTest test = (DeserializationTest) in.readObject();
        System.out.println("用户名:" + test.getUsername() + ",邮箱:" + test.getEmail());

        // 关闭ObjectInputStream输入流
        in.close();
    }


    public static void main(String[] args) throws Exception {

        try {


            // byte[] payload = _本地攻击();
            byte[] payload = new byte[]{
                    -84, -19, 0, 5, 115, 114, 0, 44, 99, 111, 109, 46, 97, 110, 98, 97, 105, 46, 115, 101, 99, 46, 115, 101, 114, 105, 97, 108, 105, 122, 101, 115, 46, 68, 101, 115, 101, 114, 105, 97, 108, 105, 122, 97, 116, 105, 111, 110, 84, 101, 115, 116, 40, 67, 56, -102, 98, 30, -42, -20, 3, 0, 2, 76, 0, 5, 101, 109, 97, 105, 108, 116, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 76, 0, 8, 117, 115, 101, 114, 110, 97, 109, 101, 113, 0, 126, 0, 1, 120, 112, 116, 0, 17, 97, 100, 109, 105, 110, 64, 106, 97, 118, 97, 119, 101, 98, 46, 111, 114, 103, 116, 0, 2, 121, 122, 120
            };

            _服务器读取(payload);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}