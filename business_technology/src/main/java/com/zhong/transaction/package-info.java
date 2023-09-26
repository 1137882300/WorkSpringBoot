/**
 * @author: juzi
 * @date: 2023/7/28
 * @desc:
 */
package com.zhong.transaction;

/*
  @author juzi
 * @date 2023/7/28 上午 9:22
 * @description
 * 保证事务的方式:
 * 1. SpringUtil.getBean(xxxMapper.class).insert(xxxPO);
 * 2. TransactionTemplateSupport.execute(() -> {
                dbPostTransaction.doInsert();
                dbPostTransaction.doUpdate();
            });
 * 3. TransactionalResultBO (仅后置处理或者记录数据)
 * 4. AbstractAssistMethod
 * 5. TransactionalHandleManager
 */