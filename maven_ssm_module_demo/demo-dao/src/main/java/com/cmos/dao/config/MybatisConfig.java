package com.cmos.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author HS
 */
@MapperScan("com.cmos.dao")
@EnableTransactionManagement
@SpringBootApplication
public class MybatisConfig {

    @Bean(name = "dataSource")
    @Profile("dev")
    public DataSource getDevDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/sms?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");

        return dataSource;
    }

    @Bean(name = "dataSource")
    @Profile("test")
    public DataSource getTestDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_sms?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");

        return dataSource;
    }

    @Bean("sqlSessionFactory")
    @Profile("dev")
    public SqlSessionFactory getDevSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDevDataSource());
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver
                = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(
                pathMatchingResourcePatternResolver.getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.cmos.dao");

        return sqlSessionFactoryBean.getObject();
    }

    @Bean("sqlSessionFactory")
    @Profile("test")
    public SqlSessionFactory getTestSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getTestDataSource());
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver
                = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(
                pathMatchingResourcePatternResolver.getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.cmos.dao");

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    @Profile("dev")
    public SqlSessionTemplate getDevSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(getDevSqlSessionFactory());
    }

    @Bean(name = "sqlSessionTemplate")
    @Profile("test")
    public SqlSessionTemplate getTestSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(getTestSqlSessionFactory());
    }
}
