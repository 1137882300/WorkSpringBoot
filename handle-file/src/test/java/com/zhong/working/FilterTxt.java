package com.zhong.working;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.zhong.entity.ExcelDemoEntity;
import com.zhong.entity.ExcelEntity;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cc on 2022/5/29
 */
public class FilterTxt {
    public String removeEmojis(String text) {
        if (StringUtils.isBlank(text)) {
            return "";
        }
        Pattern emojiPattern = Pattern.compile("[\\uD83C\\uDC00-\\uD83D\\uDFFF]|[\\uD83E\\uDD00-\\uD83E\\uDDFF]|[\\uD83D\\uDE00-\\uD83D\\uDEFF]|[\\uD83D\\uDE80-\\uD83D\\uDEFF]|[\\u2600-\\u27BF]");

        // 使用正则表达式去除表情符号
//        Pattern emojiPattern = Pattern.compile("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]");
        Matcher emojiMatcher = emojiPattern.matcher(text);
        return emojiMatcher.replaceAll("");
    }

    private List<ExcelDemoEntity> data() {
        List<ExcelDemoEntity> list = Lists.newArrayList();
        String fileName = "F:\\工作记录\\短笔记.xlsx";
        EasyExcel.read(fileName, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(dataList -> {
            for (ExcelDemoEntity entity : dataList) {
                String inputText = entity.getColumn5();

                //1.
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

                //2.
                String strippedText = removeEmojis(inputText);

                //3.
//                String strippedText = "";
//                if (StringUtils.isNotBlank(inputText)) {
//                    strippedText = inputText.replaceAll("<[^>]+>", "");
//                }
                ExcelDemoEntity data = new ExcelDemoEntity();
                data.setColumn1(entity.getColumn1());
                data.setColumn2(entity.getColumn2());
                data.setColumn3(entity.getColumn3());
                data.setColumn4(entity.getColumn4());
                data.setColumn5(strippedText);
                data.setColumn6(entity.getColumn6());
                data.setColumn7(entity.getColumn7());
//                data.setColumn3(chineseText.toString());
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
        String fileName = "E:\\笔记fix-3-13.xlsx";
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

    @Test
    public void test2() {
        String inputText = "This is a sample text with emojis. 😊❤️🎉";

        // 使用正则表达式去除表情符号
        String strippedText = removeEmojis(inputText);

        // 输出去除表情符号后的文本
        System.out.println("去除表情符号后的文本为：" + strippedText);
    }


}
