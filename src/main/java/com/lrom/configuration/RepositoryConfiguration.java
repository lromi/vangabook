package com.lrom.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.activation.DataSource;
import javax.persistence.EntityManager;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.lrom.domain"})
@EnableJpaRepositories(basePackages = {"com.lrom.repository"}, entityManagerFactoryRef="emf")
@EnableTransactionManagement
public class RepositoryConfiguration {

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName ("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-243-38-139.compute-1.amazonaws.com:5432/db3pme67alk37p");
        dataSource.setUsername("iovwyalpeiugyt");
        dataSource.setPassword("9026e4278caccf6179c07d130f333a6dd907a2a5b3ecc1975e132746f80b298d");
        return dataSource;
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().getObject().createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.lrom.domain");
        return em;
    }
}
