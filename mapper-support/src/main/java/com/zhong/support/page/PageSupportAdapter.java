package com.zhong.support.page;


/**
 * 分页适配
 *
 * @author ashui
 * @date 2022/4/13
 */
public class PageSupportAdapter {

    private static PagerSupport INSTANCE;
    private static final String CLAZZNAME = "com.zhong.support.page.PagerSupport";

    public static PagerSupport getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            try {
                Class<?> aClass = Class.forName(CLAZZNAME);
                Object object = aClass.newInstance();
                INSTANCE = (PagerSupport) object;
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        return INSTANCE;
    }
}
