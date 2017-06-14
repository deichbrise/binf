package com.week8.hungergames;

import com.common.test.AbstractTest;
import com.common.test.Assert;
import com.common.test.Test;


/**
 * Created by Julia on 14.06.2017.
 */
public class ArenaTest extends AbstractTest{
    Arena arena = new Arena();
    public static void main(String[] args){
        ArenaTest test = new ArenaTest();
        test.runAllTests();
    }
    @Test
    public void testDetermineQuadrant() {
        int[] actual = new int[] {0, 1, 2, 3, 4, -1, 1, 2, 3, 4};
        int[] calc = new int[10];
        calc[0] = arena.determineQuadrant(0, 0);
        calc[1] = arena.determineQuadrant(0, 1);
        calc[2] = arena.determineQuadrant(1, 0);
        calc[3] = arena.determineQuadrant(0, -1);
        calc[4] = arena.determineQuadrant(-1, 0);
        calc[5] = arena.determineQuadrant(-1.5, 0);
        calc[6] = arena.determineQuadrant(0.2, 1.1);
        calc[7] = arena.determineQuadrant(0.5, -0.5);
        calc[8] = arena.determineQuadrant(-0.1, -0.1);
        calc[9] = arena.determineQuadrant(-0.2, 0.1);
        Assert.assertArrayEquals(actual, calc);
    }
    @Test
    public void testDetermineAngle() {
        double[] actual = new double[] {0.0, 45.0, 135.0, 264.80557109226515, 321.3401917459099, -29.999999999999996, 180.0};
        double[] calc = new double[7];
        calc[0] = Math.toDegrees(arena.determineAngle(0,0, arena.determineQuadrant(0, 0)));
        calc[1] = Math.toDegrees(arena.determineAngle(0.5,0.5, arena.determineQuadrant(0.5, 0.5)));
        calc[2] = Math.toDegrees(arena.determineAngle(0.1,-0.1, arena.determineQuadrant(0.1, -0.1)));
        calc[3] = Math.toDegrees(arena.determineAngle(-1.1,-0.1, arena.determineQuadrant(-1.1, -0.1)));
        calc[4] = Math.toDegrees(arena.determineAngle(-0.4,0.5, arena.determineQuadrant(-0.4, 0.5)));
        calc[5] = Math.toDegrees(arena.determineAngle(1.5,0, arena.determineQuadrant(1.5, 0)));
        calc[6] = Math.toDegrees(arena.determineAngle(0,-1, arena.determineQuadrant(0, .1)));
        Assert.assertArrayEquals(actual, calc);
    }
@Test
    public void testGetArea() {
        int[] actual = {-1, 0, 0, 3, 6, 9, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] calc = new int[20];
        calc[0] = arena.getArea(1.5, 0);
        calc[1] = arena.getArea(0, 0);
        calc[2] = arena.getArea(0, 1);
        calc[3] = arena.getArea(1, 0);
        calc[4] = arena.getArea(0, -1);
        calc[5] = arena.getArea(-1, 0);
        calc[6] = arena.getArea(0, 1.5);
        calc[7] = arena.getArea(-1.5, 0);
        calc[8] = arena.getArea(0.01, 1.4);
        calc[9] = arena.getArea(0.8, 0.8);
        calc[10] = arena.getArea(1.09, 0.01);
        calc[11]=arena.getArea(1.1, -0.19);
        calc[12] = arena.getArea(0.5, -0.5);
        calc[13] = arena.getArea(0.1, -1.1);
        calc[14] = arena.getArea(-0.1, -1.2);
        calc[15] = arena.getArea(-0.1, -0.1);
        calc[16] = arena.getArea(-1.1, -0.1);
        calc[17] = arena.getArea(-1.1, 0.2);
        calc[18] = arena.getArea(-0.6, 0.6);
        calc[19] = arena.getArea(-0.1, 1.1);
        Assert.assertArrayEquals(actual, calc);
    }
}
