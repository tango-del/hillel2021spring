package org.homework.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("database.properties")
@EnableTransactionManagement
public class DatabaseConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        final HikariConfig config = new HikariConfig();
        config.setPassword(environment.getProperty("database.password"));
        config.setUsername(environment.getProperty("database.username"));
        config.setJdbcUrl(environment.getProperty("database.url"));
        config.addDataSourceProperty("databaseName" , environment.getProperty("database.name"));
        config.setDataSourceClassName(PGSimpleDataSource.class.getName());
        final HikariDataSource dataSource = new HikariDataSource(config);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf(final DataSource dataSource) {
        if (dataSource == null) throw new IllegalArgumentException("dataSource must be set");

        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("org.homework.persistence.entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        final Properties properties = new Properties();
        properties.put("hibernate.dialect", PostgreSQL10Dialect.class.getName());
//        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        //properties.put("hibernate.connection.pool_size", 150);
        properties.put("hibernate.c3p0.min_size", 30); // Minimum number of JDBC connections in the pool
        properties.put("hibernate.c3p0.max_size", 150); // Maximum number of JDBC connections in the pool
        properties.put("javax.persistence.query.timeout", 300000); // Max query timeout in milliseconds

        emf.setJpaProperties(properties);
        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        if (entityManagerFactory == null) throw new IllegalArgumentException("entityManagerFactory must be set");

        final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setDataSource(dataSource());
        return jpaTransactionManager;
    }
}
