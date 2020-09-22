package com.mandy.api.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@MapperScan("com.mandy.api.dao")
@PropertySource(value = "classpath:prop/oracle.prod.properties", ignoreResourceNotFound = true) 
@PropertySource(value = "classpath:prop/oracle.test.properties", ignoreResourceNotFound = true)
public class DbConfig {
	
    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;
    
    @Value("${jdbc.username}")
    private String username;
    
    @Value("${jdbc.password}")
    private String password;
    
    @Value("${jdbc.initialSize}")
    private int initialSize;
    
    @Value("${jdbc.maxActive}")
    private int maxActive;
    
    @Value("${jdbc.minIdle}")
    private int minIdle;
    
    @Value("${jdbc.maxWait}")
    private int maxWait;
    
    @Value("${jdbc.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    
    @Value("${jdbc.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    
    @Value("${jdbc.validationQuery}")
    private String validationQuery;
    
    @Value("${jdbc.testWhileIdle}")
    private boolean testWhileIdle;
	
    @Value("${jdbc.testOnBorrow}")
    private boolean testOnBorrow;
    
    @Value("${jdbc.testOnReturn}")
    private boolean testOnReturn;
    
    @Value("${jdbc.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    
    @Value("${jdbc.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    
    /* for production and test
	@Bean
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(initialSize);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxActive(maxActive);
		dataSource.setMaxWait(maxWait);
		dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis); 
		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis); 
		dataSource.setValidationQuery(validationQuery); 
		dataSource.setTestWhileIdle(testWhileIdle); 
		dataSource.setTestOnBorrow(testOnBorrow); 
	    dataSource.setTestOnReturn(testOnReturn); 
	    dataSource.setPoolPreparedStatements(poolPreparedStatements); 
	    dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize); 
		return dataSource;
	}
	*/ 
	
	/* for Demo */ 
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.setName("demodb;MODE=Oracle")
				.addScript("classpath:schema.sql")
				.addScript("classpath:data.sql")
				.build();
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory() throws IOException{
		org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
		config.setMapUnderscoreToCamelCase(true);
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("com.mandy.api.pojo");
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/sql/*Mapper.xml"));
		sessionFactory.setConfiguration(config);
		return sessionFactory;
	}
	
	@Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}