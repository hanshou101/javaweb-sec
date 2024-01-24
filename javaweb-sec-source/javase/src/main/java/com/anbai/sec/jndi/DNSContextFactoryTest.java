package com.anbai.sec.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * Creator: yz
 * Date: 2019/12/23
 */
public class DNSContextFactoryTest {

	public static void main(String[] args) {
		// 创建环境变量对象
		Hashtable env = new Hashtable();

		// 设置JNDI初始化工厂类名
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");

		// 设置JNDI提供服务的URL地址，这里可以设置解析的DNS服务器地址
		env.put(Context.PROVIDER_URL, "dns://223.6.6.6/");		// CloudFlare   1.1.1.1

		try {
			// 创建JNDI目录服务对象
			DirContext context = new InitialDirContext(env);

			// 获取DNS解析记录测试
			Attributes attrs1 = context.getAttributes("pwd123456.dnslog.com", new String[]{"A"}); 		// DNS解析记录 。   会有一个【A】的属性。
			Attributes attrs2 = context.getAttributes("qq.com", new String[]{"A"});
			Attributes attrs3 = context.getAttributes("163.com", new String[]{"A"});
			Attributes attrs4 = context.getAttributes("bytedance.com", new String[]{"A"});
			Attributes attrs5 = context.getAttributes("taobao.com", new String[]{"A"});
			Attributes attrs6 = context.getAttributes("alibaba.com", new String[]{"A"});

			System.out.println(attrs1);
			System.out.println(attrs2);
			System.out.println(attrs3);
			System.out.println(attrs4);
			System.out.println(attrs5);
			System.out.println(attrs6);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
