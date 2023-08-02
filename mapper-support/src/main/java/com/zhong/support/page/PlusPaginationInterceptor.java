//package com.zhong.support.page;
//
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.core.MybatisDefaultParameterHandler;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.core.metadata.OrderItem;
//import com.baomidou.mybatisplus.core.parser.ISqlParser;
//import com.baomidou.mybatisplus.core.parser.SqlInfo;
//import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
//import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
//import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
//import com.baomidou.mybatisplus.extension.plugins.pagination.DialectFactory;
//import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
//import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
//import com.baomidou.mybatisplus.extension.toolkit.SqlParserUtils;
//import net.sf.jsqlparser.JSQLParserException;
//import net.sf.jsqlparser.parser.CCJSqlParserUtil;
//import net.sf.jsqlparser.schema.Column;
//import net.sf.jsqlparser.statement.select.*;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.logging.Log;
//import org.apache.ibatis.logging.LogFactory;
//import org.apache.ibatis.mapping.*;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.SystemMetaObject;
//import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
//import org.apache.ibatis.session.Configuration;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * 分页插件
// *
// * @author ashui
// * @date 2022/9/9
// */
//@Intercepts({@Signature(
//        type = StatementHandler.class,
//        method = "prepare",
//        args = {Connection.class, Integer.class}
//)})
//public class PlusPaginationInterceptor extends AbstractSqlParserHandler implements Interceptor {
//    protected static final Log logger = LogFactory.getLog(PlusPaginationInterceptor.class);
//    protected ISqlParser countSqlParser;
//    protected boolean overflow = false;
//    protected long limit = 500L;
//    protected String dialectType;
//    protected String dialectClazz;
//
//    public PlusPaginationInterceptor() {
//    }
//
//    public static String concatOrderBy(String originalSql, IPage<?> page) {
//        if (CollectionUtils.isNotEmpty(page.orders())) {
//            try {
//                List<OrderItem> orderList = page.orders();
//                Select selectStatement = (Select) CCJSqlParserUtil.parse(originalSql);
//                List<OrderByElement> orderByElements;
//                List<OrderByElement> orderByElementsReturn;
//                if (selectStatement.getSelectBody() instanceof PlainSelect) {
//                    PlainSelect plainSelect = (PlainSelect) selectStatement.getSelectBody();
//                    orderByElements = plainSelect.getOrderByElements();
//                    orderByElementsReturn = addOrderByElements(orderList, orderByElements);
//                    plainSelect.setOrderByElements(orderByElementsReturn);
//                    return plainSelect.toString();
//                }
//
//                if (selectStatement.getSelectBody() instanceof SetOperationList) {
//                    SetOperationList setOperationList = (SetOperationList) selectStatement.getSelectBody();
//                    orderByElements = setOperationList.getOrderByElements();
//                    orderByElementsReturn = addOrderByElements(orderList, orderByElements);
//                    setOperationList.setOrderByElements(orderByElementsReturn);
//                    return setOperationList.toString();
//                }
//
//                if (selectStatement.getSelectBody() instanceof WithItem) {
//                    return originalSql;
//                }
//
//                return originalSql;
//            } catch (JSQLParserException var7) {
//                logger.warn("failed to concat orderBy from IPage, exception=" + var7.getMessage());
//            }
//        }
//
//        return originalSql;
//    }
//
//    private static List<OrderByElement> addOrderByElements(List<OrderItem> orderList, List<OrderByElement> orderByElements) {
//        orderByElements = CollectionUtils.isEmpty(orderByElements) ? new ArrayList<>(orderList.size()) : orderByElements;
//        List<OrderByElement> orderByElementList = orderList.stream()
//                .filter((item) -> StringUtils.isNotBlank(item.getColumn())).map((item) -> {
//                    OrderByElement element = new OrderByElement();
//                    element.setExpression(new Column(item.getColumn()));
//                    element.setAsc(item.isAsc());
//                    return element;
//                }).collect(Collectors.toList());
//        orderByElements.addAll(orderByElementList);
//        return orderByElements;
//    }
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
//        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
//
//        // SQL 解析
//        this.sqlParser(metaObject);
//
//        // 先判断是不是SELECT操作  (2019-04-10 00:37:31 跳过存储过程)
//        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
//        if (SqlCommandType.SELECT != mappedStatement.getSqlCommandType()
//                || StatementType.CALLABLE == mappedStatement.getStatementType()) {
//            return invocation.proceed();
//        }
//
//        // 针对定义了rowBounds，做为mapper接口方法的参数
//        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
//        Object paramObj = boundSql.getParameterObject();
//
//        // 判断参数里是否有page对象
//        IPage<?> page = null;
//        if (paramObj instanceof IPage) {
//            page = (IPage<?>) paramObj;
//        } else if (paramObj instanceof Map) {
//            for (Object arg : ((Map<?, ?>) paramObj).values()) {
//                if (arg instanceof IPage) {
//                    page = (IPage<?>) arg;
//                    break;
//                }
//            }
//        }
//
//        //分页获取
//        if (page == null) {
//            page = PageSupportAdapter.getInstance().getPage();
//        }
//
//        //不需要分页的场合，如果 size 小于 0 返回结果集
//
//        if (null == page || page.getSize() < 0) {
//            return invocation.proceed();
//        }
//
//        if (this.limit > 0 && this.limit <= page.getSize()) {
//            //处理单页条数限制
//            handlerLimit(page);
//        }
//
//        String originalSql = boundSql.getSql();
//        Connection connection = (Connection) invocation.getArgs()[0];
//        DbType dbType = StringUtils.isNotBlank(dialectType) ? DbType.getDbType(dialectType)
//                : JdbcUtils.getDbType(connection.getMetaData().getURL());
//
//        if (page.isSearchCount()) {
//            SqlInfo sqlInfo = SqlParserUtils.getOptimizeCountSql(page.optimizeCountSql(), countSqlParser, originalSql);
//            this.queryTotal(sqlInfo.getSql(), mappedStatement, boundSql, page, connection);
//            if (page.getTotal() <= 0) {
//                return null;
//            }
//        }
//
//        String buildSql = concatOrderBy(originalSql, page);
//        DialectModel model = DialectFactory.buildPaginationSql(page, buildSql, dbType, dialectClazz);
//        Configuration configuration = mappedStatement.getConfiguration();
//        List<ParameterMapping> mappings = new ArrayList<>(boundSql.getParameterMappings());
//        Map<String, Object> additionalParameters = (Map<String, Object>) metaObject.getValue("delegate.boundSql.additionalParameters");
//        model.consumers(mappings, configuration, additionalParameters);
//        metaObject.setValue("delegate.boundSql.sql", model.getDialectSql());
//        metaObject.setValue("delegate.boundSql.parameterMappings", mappings);
//        return invocation.proceed();
//    }
//
//    protected void handlerLimit(IPage<?> page) {
//        page.setSize(this.limit);
//    }
//
//    protected void queryTotal(String sql, MappedStatement mappedStatement, BoundSql boundSql, IPage<?> page, Connection connection) {
//        try {
//            PreparedStatement statement = connection.prepareStatement(sql);
//            Throwable var7 = null;
//
//            try {
//                DefaultParameterHandler parameterHandler = new MybatisDefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), boundSql);
//                parameterHandler.setParameters(statement);
//                long total = 0L;
//                ResultSet resultSet = statement.executeQuery();
//                Throwable var12 = null;
//
//                try {
//                    if (resultSet.next()) {
//                        total = resultSet.getLong(1);
//                    }
//                } catch (Throwable var37) {
//                    var12 = var37;
//                    throw var37;
//                } finally {
//                    if (resultSet != null) {
//                        if (var12 != null) {
//                            try {
//                                resultSet.close();
//                            } catch (Throwable var36) {
//                                var12.addSuppressed(var36);
//                            }
//                        } else {
//                            resultSet.close();
//                        }
//                    }
//
//                }
//
//                page.setTotal(total);
//                if (this.overflow && page.getCurrent() > page.getPages()) {
//                    this.handlerOverflow(page);
//                }
//            } catch (Throwable var39) {
//                var7 = var39;
//                throw var39;
//            } finally {
//                if (statement != null) {
//                    if (var7 != null) {
//                        try {
//                            statement.close();
//                        } catch (Throwable var35) {
//                            var7.addSuppressed(var35);
//                        }
//                    } else {
//                        statement.close();
//                    }
//                }
//
//            }
//
//        } catch (Exception var41) {
//            throw ExceptionUtils.mpe("Error: Method queryTotal execution error of sql : \n %s \n", var41, sql);
//        }
//    }
//
//    protected void handlerOverflow(IPage<?> page) {
//        page.setCurrent(1L);
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return target instanceof StatementHandler ? Plugin.wrap(target, this) : target;
//    }
//
//    @Override
//    public void setProperties(Properties prop) {
//        String dialectType = prop.getProperty("dialectType");
//        String dialectClazz = prop.getProperty("dialectClazz");
//        if (StringUtils.isNotBlank(dialectType)) {
//            this.dialectType = dialectType;
//        }
//
//        if (StringUtils.isNotBlank(dialectClazz)) {
//            this.dialectClazz = dialectClazz;
//        }
//
//    }
//
//    public PlusPaginationInterceptor setCountSqlParser(final ISqlParser countSqlParser) {
//        this.countSqlParser = countSqlParser;
//        return this;
//    }
//
//    public PlusPaginationInterceptor setOverflow(final boolean overflow) {
//        this.overflow = overflow;
//        return this;
//    }
//
//    public PlusPaginationInterceptor setLimit(final long limit) {
//        this.limit = limit;
//        return this;
//    }
//
//    public PlusPaginationInterceptor setDialectType(final String dialectType) {
//        this.dialectType = dialectType;
//        return this;
//    }
//
//    public PlusPaginationInterceptor setDialectClazz(final String dialectClazz) {
//        this.dialectClazz = dialectClazz;
//        return this;
//    }
//}
