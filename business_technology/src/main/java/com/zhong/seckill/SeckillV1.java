package com.zhong.seckill;

import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: juzi
 * @date: 2023/10/10
 * @desc: 秒杀： 内存标记 + Redis预减库存 + MQ异步处理
 */
public class SeckillV1 {

    static class v1 implements InitializingBean {
        //系统启动时会对其初始化，将所有秒杀商品id存入map，库存为0是为true
        private final Map<Long, Boolean> localOverMap = new HashMap<>();
        private RedisService redisService;

        //初始化 == @PostConstruct注解
        @Override
        public void afterPropertiesSet() {
            //1. 查询热点数据DB
            //2. 存入redis
            //3. 存入本地缓存Map
            localOverMap.put(123L, false);

//            List<GoodsVo> goodsList = goodsService.listGoodVo();
//             Redis预热秒杀商品数据
//            for (GoodsVo goods : goodsList) {
//                redisService.set(GoodsKey.getSeckillGoodsStock, "" + goods.getId(), goods.getStockCount(), 1800);
//                localOverMap.put(goods.getId(), false);
//            }
        }


        //todo：解决秒杀
        //秒杀方法
        public String seckill() {
            //验证秒杀路径：为每个用户生成有效时长的秒杀路径

            //内存标记，减少redis访问
            boolean over = localOverMap.get(123L);
            if (over) {
                return "库存不足";
            }

            //预减库存
            long stock = redisService.decr("key", "" + 123L);
            if (stock < 0) {
                localOverMap.put(123L, true);
                return "库存不足";
            }

            //判断是否已经秒杀到了
//            SeckillOrder order = orderService.getOrderByUserIdGoodsId(user.getId(), goodsId);
//            if(order != null) {
//                return "已抢到";
//            }

            //压入消息队列：下单操作

            return "排队中";
        }

        //接收到MQ消息
        public void receiveMq() {
            //判断是否还有库存，没库存直接return(不管)

            //再校验是否已经秒杀到了，秒杀到了直接return(不管)

            //减库存 下订单 写入秒杀订单
            //减库存成功：创建订单
            //减库存失败：秒杀商品标记为结束标记，存redis
        }

        //轮询查询是否下单成功
        public void orderDetail() {
            //查询秒杀订单
            //存在：则返回订单号
            //不存在：查看秒杀商品是否已经结束

            //秒杀结束：return 秒杀失败
            //秒杀未结束：return 排队中
        }
    }


    //todo：解决超卖
    //（1）更新的sql语句，只有当库存大于0才能更新库存
    //（2）对用户id和商品id建立一个唯一索引，通过这种约束避免同一用户发同时两个请求秒杀到两件相同商品
    //（3）实现乐观锁，version字段，当库存是足够的情况下发生乐观锁冲突就进行一定次数的重试。

    //其他，不管看
    interface RedisService {
        Long decr(String KeyPrefix, String key);
    }
}
