package com.mkuzmik.job.scheduler.util;

import java.util.Arrays;

public class MathUtil {

    public static int leastCommonMultiple(Integer[] numbers) {
        return Arrays.stream(numbers)
                .reduce(1, MathUtil::leastCommonMultiple);
    }

    private static int leastCommonMultiple(int a, int b) {
        return a * (b / greatestCommonDivisor(a, b));
    }

    public static int greatestCommonDivisor(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
