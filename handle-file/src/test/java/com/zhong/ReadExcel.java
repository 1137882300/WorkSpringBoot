package com.zhong;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.zhong.entity.ExcelEntity;
import com.zhong.listener.ExcelReadListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by cc on 2022/5/29
 */
@Slf4j
public class ReadExcel {

    /**
     * 写法 1： 不用额外写一个 DemoData Listener
     */
    @Test
    public void simpleRead1() {
        String fileName = "C:\\Users\\cc\\Documents\\测试文档" + File.separator + "工作簿1.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, ExcelEntity.class, new PageReadListener<ExcelEntity>(dataList -> {
            for (ExcelEntity demoData : dataList) {
                System.out.println("读取到一条数据{}" + demoData);
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).sheet().headRowNumber(1).doRead();
    }

    /**
     * 写法 3：
     */
    @Test
    public void simpleRead3(){
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        String fileName = "C:\\Users\\cc\\Documents\\测试文档" + File.separator + "工作簿1.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExcelEntity.class, new ExcelReadListener()).sheet().doRead();
    }

    /**
     * 写法 4 ：
     */
    @Test
    public void simpleRead4(){
        // 写法4： 使用 try-with-resources @since 3.1.0
        String fileName = "C:\\Users\\cc\\Documents\\测试文档" + File.separator + "工作簿1.xlsx";
        // 一个文件一个reader
        try (ExcelReader excelReader = EasyExcel.read(fileName, ExcelEntity.class, new ExcelReadListener()).build()) {
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
        }
    }

    /**
     * 写法 5：
     */
    @Test
    public void simpleRead5(){
        // 写法5： 不使用 try-with-resources
        String fileName = "C:\\Users\\cc\\Documents\\测试文档" + File.separator + "工作簿1.xlsx";
        // 一个文件一个reader
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, ExcelEntity.class, new ExcelReadListener()).build();
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.close();
            }
        }
    }

    /**
     * 写法 2
     */
    @Test
    public void simpleRead2(){
        // 匿名内部类 不用额外写一个DemoDataListener
        String fileName = "C:\\Users\\cc\\Documents\\测试文档" + File.separator + "工作簿1.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExcelEntity.class, new ReadListener<ExcelEntity>() {
            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 100;
            /**
             *临时存储
             */
            private List<ExcelEntity> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

            @Override
            public void invoke(ExcelEntity data, AnalysisContext context) {
                cachedDataList.add(data);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    saveData();
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData();
            }
            /**
             * 加上存储数据库
             */
            private void saveData() {
                log.info("{}条数据，开始存储数据库！", cachedDataList.size());
                log.info("存储数据库成功！");
            }
        }).sheet().doRead();
    }

}
