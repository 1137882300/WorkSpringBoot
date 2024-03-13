package com.zhong.working;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.zhong.entity.ExcelDemoEntity;
import com.zhong.entity.ExcelEntity;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by cc on 2022/5/29
 */
public class filterTxt {

    private List<ExcelDemoEntity> data() {
        List<ExcelDemoEntity> list = Lists.newArrayList();
        String fileName = "E:\\导出-展览3-13.xlsx";
        EasyExcel.read(fileName, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(dataList -> {
            for (ExcelDemoEntity entity : dataList) {
                String inputText = entity.getColumn3();

//                String chinesePattern = "[\\u4e00-\\u9fa5]+";
//                String chinesePattern = "[\\u4e00-\\u9fa5\\p{P}]+";
//                Pattern pattern = Pattern.compile(chinesePattern);
//                Matcher matcher = pattern.matcher(inputText);
//
//                // 提取匹配到的汉字文本
//                StringBuilder chineseText = new StringBuilder();
//                while (matcher.find()) {
//                    chineseText.append(matcher.group());
//                }

                String strippedText = inputText.replaceAll("<[^>]+>", "");

                ExcelDemoEntity data = new ExcelDemoEntity();
                data.setColumn1(entity.getColumn1());
                data.setColumn2(entity.getColumn2());
//                data.setColumn3(chineseText.toString());
                data.setColumn3(strippedText);
                list.add(data);
            }
        })).sheet().headRowNumber(1).doRead();

        return list;
    }

    @Test
    public void simpleWrite() {
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入

        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName = "E:\\带标点符号.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        // 分页查询数据
        EasyExcel.write(fileName, ExcelDemoEntity.class)
                .sheet("模板")
                .doWrite(this::data);
    }

    /**
     * 自动列宽(不太精确)
     * <p>
     * 这个目前不是很好用，比如有数字就会导致换行。而且长度也不是刚好和实际长度一致。 所以需要精确到刚好列宽的慎用。 当然也可以自己参照
     * {@link LongestMatchColumnWidthStyleStrategy}重新实现.
     * <p>
     * poi 自带{@link SXSSFSheet#autoSizeColumn(int)} 对中文支持也不太好。目前没找到很好的算法。 有的话可以推荐下。
     *
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ExcelEntity}
     * <p>
     * 2. 注册策略{@link LongestMatchColumnWidthStyleStrategy}
     * <p>
     * 3. 直接写即可
     */
    @Test
    public void simpleWrite2() {
        String fileName = "C:\\Users\\cc\\Documents\\测试文档" + File.separator + "bbbbb.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ExcelEntity.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("模板").doWrite(this::data);
    }


}
