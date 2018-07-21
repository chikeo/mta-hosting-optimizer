package com.chikelueoji.mtahostingoptimizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@SpringBootApplication
public class MtaHostingOptimizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtaHostingOptimizerApplication.class, args);
	}
	
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("org.h2.Driver");
	    dataSource.setUrl("jdbc:h2:~/myDB;MV_STORE=false");
	    dataSource.setUsername("sa");
	    dataSource.setPassword("");

	    // schema init
	    Resource initSchema = new ClassPathResource("schema.sql");
	    Resource initData = new ClassPathResource("data.sql");
	    DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);
	    DatabasePopulatorUtils.execute(databasePopulator, dataSource);

	    return dataSource;
	}
}
