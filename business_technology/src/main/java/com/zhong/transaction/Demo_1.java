package com.zhong.transaction;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author: juzi
 * @date: 2023/7/20
 * @desc: 保证事务:方式1
 */
public class Demo_1 {

    public static class TransactionTemplateSupport {
        //事务执行
        public static void execute(Runnable runnable) throws TransactionException {
            Boolean flag = SpringUtil.getBean(TransactionTemplate.class).execute(status -> {
                runnable.run();
                return Boolean.TRUE;
            });
        }
    }

    public static class Test {

        private final DbPostTransaction dbPostTransaction = new DbPostTransaction();

        private void upsert() {
            TransactionTemplateSupport.execute(() -> {
                dbPostTransaction.doInsert();
                dbPostTransaction.doUpdate();
            });
        }
    }


    public static class DbPostTransaction {
        private void doUpdate() {
            //todo: 更新操作
        }

        private void doInsert() {
            //todo: 插入操作
        }
    }

}
