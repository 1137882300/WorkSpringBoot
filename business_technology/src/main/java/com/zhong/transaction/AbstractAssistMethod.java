package com.zhong.transaction;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * @author: juzi
 * @date: 2023/9/26
 * @desc: 事务辅助方法
 */
public abstract class AbstractAssistMethod {

    public abstract void doMethod();

    /**
     * @desc 手动事务
     */
    public static void doTransaction(DataSourceTransactionManager dataSourceTransactionManager, AbstractAssistMethod abstractAssistMethod) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //事物隔离级别，开启新事务，这样会比较安全些。
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        //获得事务状态
        TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
        try {
            //执行数据库逻辑
            abstractAssistMethod.doMethod();

            dataSourceTransactionManager.commit(status);
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(status);
            throw new RuntimeException("服务繁忙");
        }
    }


    static class Test {
        @Resource(name = "xxx")
        private DataSourceTransactionManager masterTransactionManager;

        private void test() {
            AbstractAssistMethod.doTransaction(masterTransactionManager, new AbstractAssistMethod() {
                @Override
                public void doMethod() {
                    //todo: insert, update
                }
            });

        }
    }

}
