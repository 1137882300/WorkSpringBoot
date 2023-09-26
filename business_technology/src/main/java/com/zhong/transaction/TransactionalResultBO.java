package com.zhong.transaction;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author juzi
 * @date 2023/9/26 下午 4:27
 * @description 事务的后置处理
 */
@Slf4j
@Data
@Builder
public class TransactionalResultBO<T> {
    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 后置函数列表
     */
    private List<AfterHandler> afterHandlerList;

    /**
     * 可能返回的数据
     */
    private T data;


    public TransactionalResultBO() {
        init();
    }

    private void init() {
        afterHandlerList = new ArrayList<>();
    }

    /**
     * 成功后执行后置函数方法
     */
    public void doAfterHandler() {
        if (isSuccess) {
            for (AfterHandler afterHandler : afterHandlerList) {
                afterHandler.handler();
            }
        }
    }

    /**
     * 成功后执行后置函数方法
     * 捕获异常
     */
    public void doAfterHandlerForTryCatch() {
        if (isSuccess) {
            try {
                for (AfterHandler afterHandler : afterHandlerList) {
                    afterHandler.handler();
                }
            } catch (Exception e) {
                log.error("doAfterHandlerForTryCatch error", e);
            }
        }
    }

    public void addAfterHandler(AfterHandler afterHandler) {
        afterHandlerList.add(afterHandler);
    }

    public interface AfterHandler {
        void handler();
    }


    /**
     * @author juzi
     * @date 2023/7/28 上午 10:53
     * @description 使用
     */
    static class Test {

        public void test() {
            SpringUtil.getBean(Test.class).doMethod().doAfterHandlerForTryCatch();
        }

        //使用方法
        public TransactionalResultBO<Object> doMethod() {

            TransactionalResultBO<Object> transactionalResultBO = TransactionalResultBO.builder()
                    .isSuccess(Boolean.TRUE)
                    .data(new ArrayList<>())
                    .build();

            //后置处理
            transactionalResultBO.addAfterHandler(() -> {
                //保存日志
            });

            transactionalResultBO.addAfterHandler(() -> {
                //其他操作
            });

            return transactionalResultBO;
        }
    }


}
