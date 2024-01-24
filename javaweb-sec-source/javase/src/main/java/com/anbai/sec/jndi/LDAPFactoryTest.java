package com.anbai.sec.jndi;

import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * Creator: yz
 * Date: 2019/12/24
 */
public class LDAPFactoryTest {
    public static String userDN = "cn=admin,dc=example,dc=org";

    public static String password = "admin";

    public static void main(String[] args) {
        try {
            // 设置用户LDAP登陆用户DN
            // String userDN = "cn=Manager,dc=javaweb,dc=org";

            // 设置登陆用户密码
            // String password = "123456";


            // 创建环境变量对象
            Hashtable<String, Object> env = new Hashtable<String, Object>();

            // 设置JNDI初始化工厂类名
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

            String ip = "192.168.44.129";
            // String ip = "localhost";

            // 设置JNDI提供服务的URL地址
            env.put(Context.PROVIDER_URL, "ldap://" + ip + ":389");

            // 设置安全认证方式
            env.put(Context.SECURITY_AUTHENTICATION, "simple");

            // 设置用户信息
            env.put(Context.SECURITY_PRINCIPAL, userDN);

            // 设置用户密码
            env.put(Context.SECURITY_CREDENTIALS, password);

            // 创建LDAP连接
            DirContext ctx = new InitialDirContext(env);

            // 使用ctx可以查询或存储数据,此处省去业务代码

            //
            //
            //
            //
            //
            //
            //
            //
            //
            //
            //
            //
            //
            //
            //
            //
            //

            // 示例1
            // Object obj = ctx.lookup("cn=Rosanna Lee,ou=People");
            Object obj2 = ctx.lookup("cn=Subschema");
            // 示例2
            // NamingEnumeration list = ctx.list("ou=People");
            // NamingEnumeration list = ctx.list("dc=org");
            NamingEnumeration list = ctx.list("dc=example,dc=org");
            // NamingEnumeration list = ctx.list("dc=example");
            while (list.hasMore()) {
                NameClassPair nc = (NameClassPair) list.next();
                System.out.println(nc);
            }
            // 示例3
            NamingEnumeration bindings = ctx.listBindings("ou=People");
            while (bindings.hasMore()) {
                Binding bd = (Binding) bindings.next();
                System.out.println(bd.getName() + ": " + bd.getObject());
            }

            /**
             * 添加、替换、删除
             */
            // 1
            // Create the object to be bound
            Fruit fruit_新增 = new Fruit("orange");
            // Perform the bind
            ctx.bind("cn=Favorite Fruit", fruit_新增);
            // 2
            // Create the object to be bound
            Fruit fruit_更新 = new Fruit("lemon");
            // Perform the bind
            ctx.rebind("cn=Favorite Fruit", fruit_更新);
            // Remove the binding
            ctx.unbind("cn=Favorite Fruit");


            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Fruit {
        public String name;

        public Fruit(String name) {
            this.name = name;
        }
    }

}
