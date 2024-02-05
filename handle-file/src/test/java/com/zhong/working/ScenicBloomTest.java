package com.zhong.working;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.google.common.collect.Lists;
import com.zhong.entity.ExcelDemoEntity;
import lombok.SneakyThrows;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author: juzi
 * @date: 2024/2/4
 * @desc:
 */
public class ScenicBloomTest {

    public static void main(String[] args) {
        List<String> sqlList = Lists.newArrayList();
        String fileName = "F:\\工作记录\\2024杭州赏梅花开进度表-2.4.xlsx";

        EasyExcel.read(fileName, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(dataList -> {
            for (ExcelDemoEntity entity : dataList) {
                String scenicId = entity.getColumn2();
                String florescence = entity.getColumn4();
                String one = entity.getColumn5();
                String two = entity.getColumn6();
                String three = entity.getColumn7();

                List<Tuple3<Date, Date, String>> list = Lists.newArrayList();
                list.add(doDate(one, "0.2"));
                list.add(doDate(two, "0.5"));
                list.add(doDate(three, "1"));


                for (Tuple3<Date, Date, String> tuple3 : list) {
                    sqlList.add(
                            String.format("INSERT INTO \"public\".\"mall_scenic_bloom\"(\"scenic_id\", \"bloom_percent\", \"start_at\", \"end_at\", \"florescence\")\n" +
                                            "VALUES (%s, '%s', '%s', '%s', '%s');",
                                    scenicId, tuple3.getT3(), tuple3.getT1(), tuple3.getT2(), florescence)
                    );
                }
            }
        })).sheet().headRowNumber(2).doRead();
        FileUtil.writeUtf8Lines(sqlList, new File("postcard.sql"));


    }

    @SneakyThrows
    public static Tuple3<Date, Date, String> doDate(String dateStr, String bloom_percent) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        String[] dateParts = dateStr.split("-");
        Date startDate = sdf.parse("2024年" + dateParts[0]);
        Date endDate = sdf.parse("2024年" + dateParts[1]);
        return Tuples.of(startDate, endDate, bloom_percent);
    }

    public static void main2(String[] args) {

        List<String> dateStrList = new ArrayList<>();
        dateStrList.add("2月3日-2月9日");
        dateStrList.add("2月6日-2月11日");
        dateStrList.add("2月19日-2月25日");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);

        try {
            for (String dateStr : dateStrList) {
                String[] dateParts = dateStr.split("-");
                Date startDate = sdf.parse("2024年" + dateParts[0]);
                Date endDate = sdf.parse("2024年" + dateParts[1]);

            }
        } catch (ParseException e) {
            e.printStackTrace();

        }
    }

}
