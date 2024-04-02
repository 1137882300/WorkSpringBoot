package com.zhong.working;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.zhong.encryptor.AesEncryptor;
import com.zhong.entity.ExcelDemoEntity;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

/**
 * @author: juzi
 * @date: 2024/4/1
 * @desc:
 */
public class PhoneDo {

    public static void main(String[] args) {
        List<ExcelDemoEntity> list = Lists.newArrayList();
        String fileName = "F:\\工作记录\\无标题(60).xlsx";
        EasyExcel.read(fileName, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(dataList -> {
            for (ExcelDemoEntity entity : dataList) {
                ExcelDemoEntity data = new ExcelDemoEntity();

                String column14 = entity.getColumn14();

                AesEncryptor aesEncryptor = new AesEncryptor(SecureUtil.aes(StrUtil.bytes("LddSO35mBC4r7S7q")));
                String decrypt = aesEncryptor.decrypt(column14);

                data.setColumn1(column14);
                data.setColumn2(decrypt);


                list.add(data);
            }
        })).sheet().headRowNumber(1).doRead();

        String name = "E:\\解密后.xlsx";
        EasyExcel.write(name, ExcelDemoEntity.class)
                .sheet("模板")
                .doWrite(list);
    }

}
