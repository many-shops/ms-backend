package ru.ershov.backend.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

@Slf4j
@Profile("dev")
@Configuration
public class DevDataSourceConfig {

    private static final String hubImageNamePrefix = "postgres";

    @Bean
    public PostgreSQLContainer postgreSQLContainer() {
        var dockerImageName = DockerImageName.parse(hubImageNamePrefix)
                .asCompatibleSubstituteFor("postgres:13.4");
        final var container = new PostgreSQLContainer(dockerImageName);
        container.start();
        log.info("DB Url: {}", container.getJdbcUrl());
        log.info("DB User: {}", container.getUsername());
        log.info("DB Pass: {}", container.getPassword());
        return container;
    }

    @Bean
    @Primary
    public DataSource dataSource(PostgreSQLContainer container) {
        final var hikariConfig = new HikariConfig();
        hikariConfig.setPassword(container.getPassword());
        hikariConfig.setUsername(container.getUsername());
        hikariConfig.setJdbcUrl(container.getJdbcUrl());
        hikariConfig.setDriverClassName(container.getDriverClassName());
        final var hikariDataSource = new HikariDataSource(hikariConfig);
        Flyway.configure()
                .dataSource(hikariDataSource)
                .baselineVersion("0")
                .baselineOnMigrate(true)
                .outOfOrder(true)
                .placeholderReplacement(false)
                .load()
                .migrate();
        return hikariDataSource;
    }
}
