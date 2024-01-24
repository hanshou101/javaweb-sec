package com.anbai.sec.serializes;

import org.apache.commons.collections.functors.ConstantTransformer;

public class ConstantTransformerTest {

    public static void main(String[] args) throws Exception {
        // Object              obj         = Runtime.class;
        Object obj = Class.forName("java.lang.Runtime");
        Class clazz = Class.forName("java.lang.Runtime");

        ConstantTransformer transformer = new ConstantTransformer(clazz);
        System.out.println(transformer.transform(clazz));
    }

}
