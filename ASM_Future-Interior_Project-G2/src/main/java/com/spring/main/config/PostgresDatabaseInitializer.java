package com.spring.main.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class PostgresDatabaseInitializer {

    private static final Pattern JDBC_POSTGRES_URL = Pattern.compile(
            "jdbc:postgresql://([^:/?#]+)(?::(\\d+))?/(\\w+)(?:\\?.*)?");

    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        String url = properties.getUrl();
        if (url != null && url.startsWith("jdbc:postgresql://")) {
            DatabaseInfo info = parsePostgresUrl(url);
            if (info != null) {
                createDatabaseIfMissing(info, properties.getUsername(), properties.getPassword());
            }
        }
        return properties.initializeDataSourceBuilder().build();
    }

    private DatabaseInfo parsePostgresUrl(String url) {
        Matcher matcher = JDBC_POSTGRES_URL.matcher(url);
        if (!matcher.matches()) {
            return null;
        }
        String host = matcher.group(1);
        String port = matcher.group(2) != null ? matcher.group(2) : "5432";
        String database = matcher.group(3);
        return new DatabaseInfo(host, port, database);
    }

    private void createDatabaseIfMissing(DatabaseInfo info, String username, String password) {
        String adminUrl = String.format("jdbc:postgresql://%s:%s/postgres", info.host, info.port);
        try (Connection adminConnection = DriverManager.getConnection(adminUrl, username, password);
                Statement statement = adminConnection.createStatement()) {
            String quotedDatabase = quoteIdentifier(info.database);
            String createSql = "CREATE DATABASE " + quotedDatabase + " WITH ENCODING 'UTF8'";
            statement.execute(createSql);
        } catch (SQLException ex) {
            if ("42P04".equals(ex.getSQLState()) || ex.getMessage().contains("already exists")) {
                return;
            }
            if ("42501".equals(ex.getSQLState()) || ex.getMessage().toLowerCase().contains("permission denied")) {
                if (canConnectToTargetDatabase(info, username, password)) {
                    return;
                }
            }
            throw new IllegalStateException(
                    "Unable to create PostgreSQL database '" + info.database + "'. " + ex.getMessage(), ex);
        }
    }

    private boolean canConnectToTargetDatabase(DatabaseInfo info, String username, String password) {
        String targetUrl = String.format("jdbc:postgresql://%s:%s/%s", info.host, info.port, info.database);
        try (Connection targetConnection = DriverManager.getConnection(targetUrl, username, password)) {
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    private String quoteIdentifier(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }

    private static final class DatabaseInfo {
        final String host;
        final String port;
        final String database;

        DatabaseInfo(String host, String port, String database) {
            this.host = host;
            this.port = port;
            this.database = database;
        }
    }
}
