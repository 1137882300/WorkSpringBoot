package com.zhong.support.transaction;

import com.zhong.spring.SpringUtil;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 事务模板
 */
public class TransactionTemplateSupport {

    /**
     * 事务执行
     */
    public static void execute(Runnable runnable) throws TransactionException {
        Boolean flag = SpringUtil.getBean(TransactionTemplate.class).execute(status -> {
            runnable.run();
            return Boolean.TRUE;
        });
    }
}
