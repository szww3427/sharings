package com.rchenhyy.helloworld;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author chenjiahua.chen
 * @version v1.0.0
 * @since 17/9/15
 */
public class _Genericity {

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = _Genericity.class.getMethod("mapList");
        Class<?> returnType = method.getReturnType();
        Type genericReturnType = method.getGenericReturnType();

        System.out.println(returnType);
        System.out.println(genericReturnType);

        ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
        System.out.println(parameterizedType.getRawType());
        for (Type arg : parameterizedType.getActualTypeArguments()) {
            System.out.println(arg);
            for (Type typeArgument : ((ParameterizedType) arg).getActualTypeArguments()) {
                System.out.println(typeArgument);
            }
        }
    }

    public static List<Map<String, String>> mapList() {
        return null;
    }
}
