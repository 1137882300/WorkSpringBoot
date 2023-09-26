package com.zhong.transaction;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author: juzi
 * @date: 2023/7/20
 * @desc: 保证事务:方式2
 */
public class SpringBeanTransactional {


    @Transactional(rollbackFor = Exception.class)
    public void upsert() {
        //todo insert, update

        Helper.save();

        //或者
        //SpringUtil.getBean(xxxMapper.class).insert(xxxPO);
    }

    static class Helper {
        public static void save() {
//            SpringUtil.getBean(xxxMapper.class).insert(xxxPO);
        }
    }

    private void insert() {

    }

}
