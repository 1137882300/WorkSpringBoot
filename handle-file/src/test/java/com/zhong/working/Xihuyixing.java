package com.zhong.working;/**
 * @author: juzi
 * @date: 2024/4/15
 * @desc:
 */

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhong.entity.ExcelDemoEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: juzi
 * @date: 2024/4/15
 * @desc: 1
 */
public class Xihuyixing {

    public static void main1(String[] args) {
        List<String> phoneList = Lists.newArrayList();

        Map<String, String> map = readExcel();
        map.forEach((k, v) -> {
            if (v.equals("游侠客")) {
                phoneList.add(k);
            }
        });
        System.out.println(phoneList);

        List<String> uidList = Lists.newArrayList();
        Map<String, String> mapUid = readWalk();
        mapUid.forEach((k, v) -> {
            if (phoneList.contains(k)) {
                uidList.add(v);
            }
        });
        System.out.println(uidList);
    }

    public static void main(String[] args) {

        Map<String, ExcelDemoEntity> userMap = readWalkV2();

        //platform
        Map<String, String> platformMap = readExcel();

        List<ExcelDemoEntity> yxkList = readYXK();
        Map<String, ExcelDemoEntity> yxkMap = yxkList.stream()
                .collect(Collectors.toMap(ExcelDemoEntity::getColumn2, Function.identity(), (s, e) -> s));

        List<ExcelDemoEntity> writeList = Lists.newArrayList();
        userMap.forEach((phone, excelDemoEntity) -> {
            ExcelDemoEntity write = new ExcelDemoEntity();

            String name = excelDemoEntity.getColumn3();
            String phone2 = excelDemoEntity.getColumn4();
            String bankNo = excelDemoEntity.getColumn5();
            String bankName = excelDemoEntity.getColumn6();
            String bankOpen = excelDemoEntity.getColumn7();

            write.setColumn1(name);
            write.setColumn2(phone2);
            write.setColumn3(bankNo);
            write.setColumn4(bankName);
            write.setColumn5(bankOpen);
            if (platformMap.containsKey(phone)) {
                write.setColumn6(platformMap.get(phone));
            }
            if (yxkMap.containsKey(phone)) {
                ExcelDemoEntity yxkEntity = yxkMap.get(phone);
                //订单号
                write.setColumn7(yxkEntity.getColumn2());
                //客商号
                write.setColumn8(yxkEntity.getColumn2());
                //计划号
                write.setColumn9(yxkEntity.getColumn2());
                //金额
                write.setColumn10(yxkEntity.getColumn2());
            }
            writeList.add(write);
        });

        String fileName = "/Users/pangmengting/Downloads/最终导出.xlsx";

        EasyExcel.write(fileName, ExcelDemoEntity.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("one")
                .doWrite(writeList);

    }

    private static List<ExcelDemoEntity> readYXK() {
        List<ExcelDemoEntity> list = Lists.newArrayList();
        String fileName = "/Users/pangmengting/Downloads/游侠客平台.xlsx";
        try {
            EasyExcel.read(fileName, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(list::addAll))
                    .sheet()
                    .headRowNumber(1)
                    .doRead();
        } catch (Exception e) {
            System.out.println("readExcel error");
        }
        return list;
    }

    private static Map<String, String> readWalk() {
        //key:phone
        //value:uid
        Map<String, String> map = Maps.newHashMap();
        String fileName = "/Users/pangmengting/Downloads/walk_user_bank.xlsx";
        try {
            EasyExcel.read(fileName, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(dataList -> {
                for (ExcelDemoEntity excel : dataList) {
                    String column2 = excel.getColumn2();
                    String column4 = excel.getColumn4();
                    if (StringUtils.isNotBlank(column2) && StringUtils.isNotBlank(column4)) {
                        map.put(column4, column2);
                    }
                }
            })).sheet().headRowNumber(1).doRead();
        } catch (Exception e) {
            System.out.println("readExcel error");
        }
        return map;
    }

    private static Map<String, ExcelDemoEntity> readWalkV2() {
        Map<String, ExcelDemoEntity> map = Maps.newHashMap();
        String fileName = "/Users/pangmengting/Downloads/walk_user_bank.xlsx";
        try {
            EasyExcel.read(fileName, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(dataList -> {
                for (ExcelDemoEntity excel : dataList) {
                    String column4 = excel.getColumn4();
                    map.put(column4, excel);
                }
            })).sheet().headRowNumber(1).doRead();
        } catch (Exception e) {
            System.out.println("readExcel error");
        }
        return map;
    }


    private static Map<String, String> readExcel() {
        //key:phone
        //value:platform
        Map<String, String> map = Maps.newHashMap();
        String fileName = "/Users/pangmengting/Downloads/2021西湖毅行未退费总表.xlsx";
        try {
            EasyExcel.read(fileName, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(dataList -> {
                for (ExcelDemoEntity excel : dataList) {
                    String column1 = excel.getColumn1();
                    String phone;
                    if (StringUtils.isNotBlank(column1)) {
                        int lastIndex = column1.lastIndexOf("/");
                        if (lastIndex != -1) {
                            phone = column1.substring(lastIndex + 1);
                        } else {
                            phone = excel.getColumn4();
                        }
                    } else {
                        phone = excel.getColumn4();
                    }
                    String column7 = excel.getColumn7();
                    map.put(phone, column7);
                }
            })).sheet().headRowNumber(1).doRead();
        } catch (Exception e) {
            System.out.println("readExcel error");
        }
        return map;
    }

}
