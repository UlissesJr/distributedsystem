package com.sample.mall.order.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.rm.datasource.xa.DataSourceProxyXA;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by LuoboGan
 * Date:2023-03-30
 */
public class DataSourceProxyConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }

    /**
     * 创建数据源代理，并且设置为主数据源，否则不能支持Seata
     * @param druidDataSource
     * @return
     */
    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource(DruidDataSource druidDataSource){

        // AT模式
        // return new DataSourceProxy(druidDataSource);

        // XA模式
        // return new DataSourceProxyXA(druidDataSource);

        // TCC模式是不需要数据源代理的
        return druidDataSource;
    }


}
