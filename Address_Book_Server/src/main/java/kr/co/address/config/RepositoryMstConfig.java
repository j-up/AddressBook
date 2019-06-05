package kr.co.address.config;

import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * <pre>
 * DB CRUD용 MS Sql DB 연결 관련 설정.  
 * </pre>
 *
 */
@Configuration
@PropertySource(value = { "file:/prop/adr-db-config.properties" })
@MapperScan(value={"kr.co.address.mapper"}, sqlSessionFactoryRef = "mstSqlSessionFactory")	
@EnableTransactionManagement
public class RepositoryMstConfig {
	
	private Logger logger = LoggerFactory.getLogger(RepositoryMstConfig.class);

	@Bean(name = "DataSourceProperties")
	@ConfigurationProperties(prefix = "jdbc.main.datasource")
	@Primary // 같은 타입의 빈 우선 지정 
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	
	@Bean(name = "mstDataSource")
	@ConfigurationProperties("jdbc.main.datasource")
	@Primary
									// 타입이 같은 경우 빈에 메타정보를 지정하여 해당 빈을 DI함 
	public HikariDataSource dataSource(@Qualifier("DataSourceProperties") DataSourceProperties properties) {
		return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "mstSqlSessionFactory")
	@Primary
	public SqlSessionFactory mstSqlSessionFactory(@Qualifier("mstDataSource") HikariDataSource mstDataSource,
			ApplicationContext context) throws Exception {

		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		try {
			logger.info("START Setting sqlSessionFactotry");

			sessionFactoryBean.setDataSource(mstDataSource);
			sessionFactoryBean.setMapperLocations(context.getResources("classpath:/mapper/**/*.xml"));
		
			sessionFactoryBean.setTypeAliasesPackage("kr.co.address.domain");
													  

			logger.info("End Setting sqlSessionFactotry");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactoryBean.getObject();
	}

	@Bean(name = "mstSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate mstSqlSessionTemplate(SqlSessionFactory mstSqlSessionFactory) throws Exception {
		
		return new SqlSessionTemplate(mstSqlSessionFactory);
	}

}
