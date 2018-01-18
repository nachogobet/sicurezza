package com.ig.sicurezza.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url("postgres://gfxpoqbprezear:ab56e5ea6667591f5ea764bdd02a12bc37587cf1e843426cec5cc27e049ae5aa@ec2-54-243-193-227.compute-1.amazonaws.com:5432/d6nlqjcjbj2lqr");
		dataSourceBuilder.username("gfxpoqbprezear");
		dataSourceBuilder.password("ab56e5ea6667591f5ea764bdd02a12bc37587cf1e843426cec5cc27e049ae5aa");
		return dataSourceBuilder.build();   
	}
}
