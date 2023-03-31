package com.zhong.es.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @date 2022/7/15 11:23
 */
@Slf4j
@Configuration
public class ElasticSearchConfig {

    @Value("${es.node.host}")
    private String hostName;

    @Value("${es.node.port}")
    private Integer port;

    /**
     * http
     */
    @Value("${es.node.scheme}")
    private String scheme;

    @Value("${es.node.username}")
    private String username;


    @Value("${es.node.password}")
    private String password;


    @Value("${es.config.retryOnConflict}")
    private int retryOnConflict = 3;


    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(restClientBuilder());
    }

    private RestClientBuilder restClientBuilder() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(hostName, port, scheme));
        builder.setDefaultHeaders(buildHeader());
        builder.setFailureListener(failureListener());
        if (Objects.nonNull(username) && Objects.nonNull(password)) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
            builder.setHttpClientConfigCallback(c -> c.setDefaultCredentialsProvider(credentialsProvider));
        }
        return builder;
    }

    private Header[] buildHeader() {
        return new Header[]{new BasicHeader("Content-Type", "application/json")};
    }

    private RestClient.FailureListener failureListener() {
        return new RestClient.FailureListener() {
            @Override
            public void onFailure(Node node) {
                log.error(String.format("elastic search 连接失败, nodeName:%s, hostName:%s, port:%s", node.getName(), node.getHost().getHostName(), node.getHost().getPort()));
                super.onFailure(node);
            }
        };
    }

}
