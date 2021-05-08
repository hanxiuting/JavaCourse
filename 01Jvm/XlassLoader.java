package com.geekbang.codes.jvm;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.MessageFormat;

/**
 * 定义xlassLoader，加载Hello.xlass，执行hello 方法
 */
public class XlassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        final String className = "Hello";
        final String methodName = "hello";
        //初始化类加载器
        ClassLoader classLoader = new XlassLoader();
        Class<?> classFactory = classLoader.loadClass(className);
        Object instance = classFactory.getDeclaredConstructor().newInstance();
        Method method = classFactory.getMethod(methodName);
        method.invoke(instance);
    }

    /**
     * findClass
     * 读取Hello.xlass，字节处理
     *
     * @param name Hello.xlass
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = MessageFormat.format("{0}.xlass", name.replace(".", "/"));
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        try {
            int length = inputStream.available();
            byte[] byteArr = new byte[length];
            inputStream.read(byteArr);
            byte[] decodeBytes = decodeByte(byteArr);
            return defineClass(name, decodeBytes, 0, decodeBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            close(inputStream);
        }
    }

    /**
     * 解码，转换为255-byteVal
     *
     * @param byteArr
     * @return
     */
    private static byte[] decodeByte(byte[] byteArr) {
        byte[] arr = new byte[byteArr.length];
        for (int i = 0; i < byteArr.length; i++) {
            arr[i] = (byte) (255 - byteArr[i]);
        }
        return arr;
    }

    private static void close(Closeable res) {
        if (null != res) {
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
