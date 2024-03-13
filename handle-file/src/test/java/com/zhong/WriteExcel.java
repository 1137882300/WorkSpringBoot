package com.zhong;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.zhong.entity.ExcelEntity;
import org.junit.Test;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by cc on 2022/5/29
 */
public class WriteExcel {

    private List<ExcelEntity> data() {
        List<ExcelEntity> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            ExcelEntity data = new ExcelEntity();
            data.setStringData("字符串字符串字符串字符串字符串字符串字符串字符串字符串" + i);
            data.setDateData(new Date());
            data.setDoubleData(0.56);
//            data.setBooleanData(true);
//            data.setListData(new ArrayList<>());
            data.setLongData(123L);
            list.add(data);
        }
        return list;
    }

    @Test
    public void simpleWrite() {
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入

        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName = "C:\\Users\\cc\\Documents\\测试文档" + File.separator + "写入1.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        // 分页查询数据
        EasyExcel.write(fileName, ExcelEntity.class)
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
        String fileName = "C:\\Users\\cc\\Documents\\测试文档" + File.separator + "写入2.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ExcelEntity.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("模板").doWrite(this::data);
    }


}
