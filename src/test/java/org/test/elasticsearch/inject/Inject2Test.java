package org.test.elasticsearch.inject;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class Inject2Test {

    private static String data;
    private static Inject inject;

    @BeforeClass
    public static void beforeClass() {
        inject = new Inject();
        data = new Data().getData();
    }

    @AfterClass
    public static void afterClass() {
        inject = null;
    }

    // @Test
    public void testRandom() {
        System.out.printf(inject.getRandom());
    }

    @Test
    public void testInject() {
        inject.inject("adaptimo","affaire",data,100);
    }

}
