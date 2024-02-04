package com.zhong.working;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.google.common.collect.Lists;
import com.zhong.entity.ExcelDemoEntity;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

/**
 * @author: juzi
 * @date: 2024/1/12
 * @desc:
 */
public class PostcardTest {


    public static void main(String[] args) {
        List<String> sqlList = Lists.newArrayList();
        String fileName = "F:\\工作记录\\电子名片信息表模板(杭州艺术学校).xlsx";
        EasyExcel.read(fileName, ExcelDemoEntity.class, new PageReadListener<ExcelDemoEntity>(dataList -> {
            for (ExcelDemoEntity entity : dataList) {

                String real_name = entity.getColumn2();
                String foreign_name = entity.getColumn3();
                String phone = entity.getColumn4();
                if (StringUtils.isNotBlank(phone)) {
                    phone = sm4Encrypt(phone, null);
                }
                String telephone = entity.getColumn5();
                if (StringUtils.isNotBlank(telephone)) {
                    telephone = sm4Encrypt(telephone, null);
                }
                String email = "";

                String job_place = entity.getColumn6();

                String job_unit = entity.getColumn7();
                String job_position = entity.getColumn8();
                String address = entity.getColumn9();

                sqlList.add(
                        String.format("INSERT INTO \"postcard\"(\"real_name\", \"phone\", \"telephone\", \"email\", \"job_unit\", \"job_position\", \"status\", \"foreign_name\", \"address\", \"job_place\")" +
                                        " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '1', '%s', '%s', '%s');",
                                real_name, phone, telephone, email, job_unit, job_position, foreign_name, address, job_place)
                );

            }
        })).sheet().headRowNumber(1).doRead();
        FileUtil.writeUtf8Lines(sqlList, new File("postcard.sql"));


    }

    public static String sm4Encrypt(String content, String secretKey) {
//        635a6df163f598f450192fc1e5ac730e
        byte[] key = HexUtil.decodeHex("635a6df163f598f450192fc1e5ac730e");
        SymmetricCrypto sm4 = SmUtil.sm4(key);
        return sm4.encryptHex(content);
    }


}
