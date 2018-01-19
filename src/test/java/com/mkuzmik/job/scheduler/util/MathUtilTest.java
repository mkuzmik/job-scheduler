package com.mkuzmik.job.scheduler.util;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class MathUtilTest {

    @Test
    public void testLeastCommonMultiple() throws Exception {

        assertThat(MathUtil.leastCommonMultiple(
                new Integer[] {1,3,5}
        )).isEqualTo(15);

        assertThat(MathUtil.leastCommonMultiple(
                new Integer[] {81, 9, 3}
        )).isEqualTo(81);

        assertThat(MathUtil.leastCommonMultiple(
                new Integer[] {78, 66, 45, 2}
        )).isEqualTo(12870);
    }

    @Test
    public void testGreatestCommonDivisor() throws Exception {

        assertThat(MathUtil.greatestCommonDivisor(3,4)).isEqualTo(1);

        assertThat(MathUtil.greatestCommonDivisor(10,5)).isEqualTo(5);
    }
}