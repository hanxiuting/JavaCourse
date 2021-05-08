package com.geekbang.codes.jvm;

public class Hello {
    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic();
        System.out.println("add =" + arithmetic.add(5d, 10));
        System.out.println("minus=" + arithmetic.minus(5d, 2f));
        int sum = 0;
        int i = 0;
        for (; i < 10; i++) {
            sum += i;
        }
        System.out.println("sum=" + sum);
        if (sum > 10) {
            System.out.println("greater than 10");
        }
    }
}

class Arithmetic {
    public double add(double dVal, int iVal) {
        return dVal + iVal;
    }

    public double minus(double mVal, float fVal) {
        return Math.abs(mVal - fVal);
    }

    public float plus(int iVal, float fVal) {
        return iVal * fVal;
    }
}
