package com.zhong;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.zhong.entity.ExcelDemoEntity;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: juzi
 * @date: 2024/1/8
 * @desc:
 */
public class DemoTest {

    private static final Map<String, Integer> map = new HashMap<>();
    private static int id = 1;

    static {
        map.put("滨江区", 330108);
        map.put("淳安县", 330127);
        map.put("富阳区", 330183);
        map.put("拱墅区", 330105);
        map.put("建德市", 330182);
        map.put("江干区", 330104);
        map.put("临安区", 330185);
        map.put("临平区", 311199);
        map.put("钱塘区", 310020);
        map.put("上城区", 330102);
        map.put("桐庐县", 330122);
        map.put("西湖区", 330106);
        map.put("萧山区", 330109);
        map.put("余杭区", 330110);
        map.put("西湖风景区", 4542427);
    }

    //区县活动
    @Test
    public void simpleRead2() {
        String fileName = "F:\\工作记录\\“宋”福杭州年活动-1.8.xls";
        id = 1;
        Integer type = 0;

        List<String> sqlList = new ArrayList<>();
        List<String> sqlImgList = new ArrayList<>();


        EasyExcel.read(fileName, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(dataList -> {
            for (ExcelDemoEntity demoData : dataList) {
                System.out.println("读取到一条数据：" + demoData);

                String county = demoData.getColumn1();
                Integer countyId = 0;
                if (StringUtils.isNotBlank(county)) {
                    countyId = map.getOrDefault(county, 0);
                }

                String name = StringUtils.isBlank(demoData.getColumn3()) ? "" : demoData.getColumn3();
                String undertakeUnit = StringUtils.isBlank(demoData.getColumn4()) ? "" : demoData.getColumn4();
                String activityTime = StringUtils.isBlank(demoData.getColumn5()) ? "" : demoData.getColumn5();
                String address = StringUtils.isBlank(demoData.getColumn6()) ? "" : demoData.getColumn6();
                String content = StringUtils.isBlank(demoData.getColumn7()) ? "" : demoData.getColumn7();
                String contactWay = StringUtils.isBlank(demoData.getColumn8()) ? "" : demoData.getColumn8();
                String bookingWay = StringUtils.isBlank(demoData.getColumn9()) ? "" : demoData.getColumn9();
                String urls = demoData.getColumn10();
                String sort = demoData.getColumn11();
                int sortId = 0;
                if (StringUtils.isNotBlank(sort)) {
                    sortId = 10;
                }

                if (StringUtils.isNotBlank(name)) {
                    id++;
                    String sql = String.format
                            ("INSERT INTO \"activity\"(\"id\",\"type\", \"county_id\", \"host_unit\", \"undertake_unit\", \"name\", \"content\", \"address\", \"activity_time\", \"contact_person\", \"contact_way\", \"booking_way\", \"sort\") " +
                                            "VALUES (%s, %s, %s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                                    id, type, countyId, "", undertakeUnit, name, content, address, activityTime, "", contactWay, bookingWay, sortId);
                    sqlList.add(sql);

                    //图片
                    if (StringUtils.isNotBlank(urls)) {
                        String[] split = urls.split(",");
                        for (String s : split) {
                            String sqlImg = String.format("INSERT INTO \"activity_resource\"( \"activity_id\", \"type\", \"url\" ) VALUES ( %s, 1, '%s' );",
                                    id, s);
                            sqlImgList.add(sqlImg);
                        }
                    }
                }

            }
        })).sheet().headRowNumber(2).doRead();

        FileUtil.writeUtf8Lines(sqlList, new File("活动sql111.sql"));
        FileUtil.writeUtf8Lines(sqlImgList, new File("活动sqlImg111.sql"));

        System.out.println("id ===> " + id);
    }


    //产品活动
    @Test
    public void simpleRead1() {
        String fileName = "F:\\工作记录\\2024“宋”福杭州年文旅产品(定)(2).xls";
        id = 100;
        Integer type = 3;

        List<String> sqlList = new ArrayList<>();
        List<String> sqlImgList = new ArrayList<>();


        EasyExcel.read(fileName, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(dataList -> {
            for (ExcelDemoEntity demoData : dataList) {
                System.out.println("读取到一条数据：" + demoData);

                String county = demoData.getColumn1();
                Integer countyId = 0;
                if (StringUtils.isNotBlank(county)) {
                    countyId = map.getOrDefault(county, 0);
                }

                String name = StringUtils.isBlank(demoData.getColumn3()) ? "" : demoData.getColumn3();
                String undertakeUnit = StringUtils.isBlank(demoData.getColumn4()) ? "" : demoData.getColumn4();
                String activityTime = StringUtils.isBlank(demoData.getColumn5()) ? "" : demoData.getColumn5();
                String address = StringUtils.isBlank(demoData.getColumn6()) ? "" : demoData.getColumn6();
                String content = StringUtils.isBlank(demoData.getColumn7()) ? "" : demoData.getColumn7();
                String contactWay = StringUtils.isBlank(demoData.getColumn8()) ? "" : demoData.getColumn8();
                String bookingWay = StringUtils.isBlank(demoData.getColumn9()) ? "" : demoData.getColumn9();
                String urls = demoData.getColumn10();
                String sort = demoData.getColumn11();
                int sortId = 0;
                if (StringUtils.isNotBlank(sort)) {
                    sortId = 10;
                }

                if (StringUtils.isNotBlank(name)) {
                    id++;
                    String sql = String.format
                            ("INSERT INTO \"activity\"(\"id\",\"type\", \"county_id\", \"host_unit\", \"undertake_unit\", \"name\", \"content\", \"address\", \"activity_time\", \"contact_person\", \"contact_way\", \"booking_way\", \"sort\") " +
                                            "VALUES (%s, %s, %s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                                    id, type, countyId, "", undertakeUnit, name, content, address, activityTime, "", contactWay, bookingWay, sortId);
                    sqlList.add(sql);

                    //图片
                    if (StringUtils.isNotBlank(urls)) {
                        String[] split = urls.split(",");
                        for (String s : split) {
                            String sqlImg = String.format("INSERT INTO \"activity_resource\"( \"activity_id\", \"type\", \"url\" ) VALUES ( %s, 1, '%s' );",
                                    id, s);
                            sqlImgList.add(sqlImg);
                        }
                    }
                }
            }
        })).sheet().headRowNumber(2).doRead();

        FileUtil.writeUtf8Lines(sqlList, new File("活动sql222.sql"));
        FileUtil.writeUtf8Lines(sqlImgList, new File("活动sqlImg222.sql"));
    }

}
