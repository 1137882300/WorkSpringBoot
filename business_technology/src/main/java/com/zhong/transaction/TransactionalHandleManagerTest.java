package com.zhong.transaction;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author: juzi
 * @date: 2023/9/26
 * @desc:
 */
public class TransactionalHandleManagerTest {

    static class TransactionalHandleManager {

        @Transactional(rollbackFor = Exception.class, transactionManager = "xxx")
        public void masterTransaction(MethodHandler handler) {
            handler.handler();
        }

        public interface MethodHandler {
            void handler();
        }
    }


    static class Test {
        private TransactionalHandleManager transactionalHandleManager;

        public void test() {
            transactionalHandleManager.masterTransaction(() -> {
                //todo: insert , update
            });
        }
    }

}
