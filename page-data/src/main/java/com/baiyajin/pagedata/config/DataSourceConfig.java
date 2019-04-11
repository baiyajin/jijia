package com.baiyajin.pagedata.config;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

    /**
     * 数据源配置
     */
    @Configuration
    public class DataSourceConfig {

        @Bean(name="dataSource")
        @ConfigurationProperties(prefix="spring.datasource")
        public DataSource dataSource(){
            return new DruidDataSource();
        }

        // 配置事物管理器
        @Bean(name="transactionManager")
        public DataSourceTransactionManager transactionManager(){
            return new DataSourceTransactionManager(dataSource());
        }

    }
