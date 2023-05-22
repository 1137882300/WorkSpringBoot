package com.zhong.web.base.filter;

import java.io.InputStream;
import java.util.LinkedHashMap;

/**
 * @author: juzi
 * @date: 2023/5/19
 * @desc:
 */
public class AuthorizeBuilder {

    private final LinkedHashMap<String, SecurityFilterEnum> filterChains = new LinkedHashMap<>();

    private LinkedHashMap<SecurityFilterEnum, AuthFilter> register = new LinkedHashMap<>();


    public void add(AuthFilter filter) {
        register.put(filter.getAuthEnum(), filter);
    }

    AuthorizeBuilder(LinkedHashMap<SecurityFilterEnum, AuthFilter> register) {
        this.register = register;
    }

    AuthorizeBuilder() {
    }

    public static AuthorizeBuilder builder() {
        return new AuthorizeBuilder();
    }

    public AuthorizeBuilder register(AuthFilter filter) {
        register.put(filter.getAuthEnum(), filter);
        return this;
    }

    public AuthorizeBuilder build() {
        return new AuthorizeBuilder(this.register);
    }

}
