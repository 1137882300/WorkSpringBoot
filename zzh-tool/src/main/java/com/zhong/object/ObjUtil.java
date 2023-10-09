package com.zhong.object;

import cn.hutool.core.bean.BeanUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 如果类同名，就选择继承
 */
public class ObjUtil {

    /**
     * 比较两个bean的属性是否相同
     * 返回comparedObj与obj不相同的属性
     * 相同的属性不返回
     * <p>
     * Order(orderNo=123, num=3, amount=null, bookAt=Thu Dec 10 10:07:40 CST 2020)
     * Order(orderNo=1234, num=10, amount=null, bookAt=Thu Dec 10 10:07:40 CST 2020)
     * return Order(orderNo=1234, num=10, amount=null, bookAt=null)
     *
     * @param obj         通常从数据库查询出
     * @param comparedObj 作为被比较的新的
     * @return 返回 comparedObj
     */
    @SuppressWarnings("unchecked")
    public static <T> T equals(T obj, T comparedObj) {
        if (comparedObj == null) {
            return null;
        }
        if (obj == null) {
            return comparedObj;
        }
        Map<String, Object> newMap = new HashMap<>();
        Map<String, Object> objMap = BeanUtil.beanToMap(obj);
        Map<String, Object> comparedMap = BeanUtil.beanToMap(comparedObj);
        for (Map.Entry<String, Object> entry : comparedMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            if (objMap.containsKey(key)) {
                Object o = objMap.get(key);
                if (Objects.equals(o, value)) {
                    continue;
                }
            }
            newMap.put(key, value);
        }
        return newMap.isEmpty() ? null : (T) BeanUtil.toBean(newMap, obj.getClass());
    }

}
