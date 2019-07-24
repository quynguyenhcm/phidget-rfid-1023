package com.qrolling.rfid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
@Configuration
@EnableTransactionManagement
@ComponentScans(value = {@ComponentScan("com.qrolling.rfid.dao"),
        @ComponentScan("com.qrolling.rfid.services"),
        @ComponentScan("com.qrolling.rfid.core"),
        @ComponentScan("com.qrolling.rfid.readers")})
    public class AppConfig {

    //private final String MY_SQL_PERSISTENCE = "MY_SQL_PERSISTENCE";
    private final String H2_IN_MEMORY_PERSISTENCE = "H2_IN_MEMORY_PERSISTENCE";

    @Bean
    public LocalEntityManagerFactoryBean geEntityManagerFactoryBean() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        try {
            factoryBean.setPersistenceUnitName(H2_IN_MEMORY_PERSISTENCE);
        } catch (Exception e) {
            System.out.println("Could not find MYSQL database. Running on in memory database, the data will be lost on application shutdown");
            factoryBean.setPersistenceUnitName(H2_IN_MEMORY_PERSISTENCE);
        }
        return factoryBean;
    }

    @Bean
    public JpaTransactionManager geJpaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(geEntityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
