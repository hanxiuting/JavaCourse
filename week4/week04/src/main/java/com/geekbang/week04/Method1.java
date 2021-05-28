package com.geekbang.week04;

import com.geekbang.week04.entity.ResultEntity;

public class Method1 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        ResultEntity entity = new ResultEntity();
        Thread computeThread = new Thread(() -> {
            entity.setResult(sum());
        }, "computeThread");
        computeThread.start();
        Thread.sleep(1000);

        System.out.println("异步计算结果为：" + entity.getResult());
        System.out.println("isAlive:" + computeThread.isAlive());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
