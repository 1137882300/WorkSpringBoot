package com.zhong;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.zhong.entity.ExcelTestEntity;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        System.out.println(File.separator); //File.separator == \
        assertTrue(true);
    }


    @Test
    public void simpleRead1() {
        String fileName = "E:\\上海最终.xlsx";
        AtomicInteger i = new AtomicInteger(1);
        EasyExcel.read(fileName, ExcelTestEntity.class, new PageReadListener<ExcelTestEntity>(dataList -> {
            for (ExcelTestEntity demoData : dataList) {
//                System.out.println("读取到一条数据{}" + demoData + i.getAndIncrement());
                if (Integer.valueOf("1").equals(demoData.getStatus())) {
                    //已开通

                } else {
                    //未开通
                }
            }
        })).sheet().headRowNumber(1).doRead();
    }

}
