package com.zhong.working;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.zhong.entity.ExcelDemoEntity;
import org.apache.commons.compress.utils.Lists;

import java.io.File;
import java.util.List;

/**
 * @author: juzi
 * @date: 2024/3/27
 * @desc: 1
 */
public class StructuredData {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();


        String fileName = "/Users/pangmengting/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/32c489a627d82a589ea561b132ef38ce/Message/MessageTemp/04892995800a8baa4bc9f69781a96a82/File/景区、博物馆、线路20240321.xlsx";
        EasyExcel.read(fileName, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(dataList -> {
            for (ExcelDemoEntity entity : dataList) {
                String column2 = entity.getColumn2();
                String column3 = entity.getColumn3();
                String column4 = entity.getColumn4();
                String column5 = entity.getColumn5();
                String column6 = entity.getColumn6();
                String column7 = entity.getColumn7();
                String column8 = entity.getColumn8();
                String column9 = entity.getColumn9();
                String column10 = entity.getColumn10();
                String column11 = entity.getColumn11();
                String column12 = entity.getColumn12();
                String column13 = entity.getColumn13();
                String column14 = entity.getColumn14();

                String data = String.format("[%s]是%s属于%s的大类是%s的在%s，封面地址是%s，处于%s的状态，目前是%s的，简单介绍下：%s，进入园区你需要知道%s，最大容纳量是%s，详细地址是%s",
                        column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12, column14);


                list.add(data);
            }
        })).sheet().headRowNumber(1).doRead();

        FileUtil.writeUtf8Lines(list, new File("景区-结构化.txt"));

    }


}
