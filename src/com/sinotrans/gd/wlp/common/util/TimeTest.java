package com.sinotrans.gd.wlp.common.util;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/13.
 */
public class TimeTest {
    @Test
    public void test2(){
        DateTest dateTest = new DateTest();
        dateTest.setTime(new Date());

        DateStrTest dateStrTest = new DateStrTest();
        try {
            BeanUtils.copyProperties(dateStrTest,dateTest);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.print(dateStrTest.getTime());
    }
}
