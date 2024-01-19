package com.zhong.working;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.zhong.entity.ExcelDemoEntity;
import com.zhong.file.FileUtil;
import org.apache.commons.compress.utils.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author: juzi
 * @date: 2024/1/19
 * @desc:
 */
public class DownloadFile {

    public static void main(String[] args) {

        String path = "C:\\Users\\root\\Desktop\\查询11.xlsx";
        List<String> list = Lists.newArrayList();
        EasyExcel.read(path, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(dataList -> {
            list.addAll(dataList.stream().map(ExcelDemoEntity::getColumn4).collect(Collectors.toList()));
        })).sheet().headRowNumber(1).doRead();

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (String imageUrl : new HashSet<>(list)) {
            Runnable task = new FileUtil.FileDownloader.DownloadTask(imageUrl, "mp4");
            executorService.submit(task);
        }
        executorService.shutdown();

    }

}
