package org.homework;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.activation.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        HikariDataSource ds;
        config.setPassword("Sobik2010");
        config.setUsername("postgres");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.addDataSourceProperty("databaseName" , "postgres");

        config.setDataSourceClassName(PGSimpleDataSource.class.getName());
        config.setMaximumPoolSize(150);
//        config.setIdleTimeout(10000L);
//        config.setMinimumIdle(30);
        //config.setConnectionTestQuery("select 1");

        HikariPool pool = new HikariPool(config);

        ds = new HikariDataSource( config );

        HikariDataSource cpDatasource = new HikariDataSource(config);


        for (int i = 1; i <= 30; i++) {
            Connection connection = cpDatasource.getConnection();

            System.out.println(i);

            connection.close();
        }
    }
}
