/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gosolar2;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.sqlite.JDBC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@ComponentScan ("com.gosolar2")
@RestController
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan
@EnableJpaRepositories
@SpringBootApplication
@EnableScheduling
@ImportResource (value = "classpath:applicationContext.xml")
public class GoSolar2Application {

	final static Logger logger         = Logger.getLogger(GoSolar2Application.class);
	private      String databaseDriver = JDBC.class.getName();

	public static void main (String[] args) throws Exception {
		SpringApplication.run(GoSolar2Application.class, args);

		try {
			Runtime.getRuntime().exec("open http://localhost:8000/");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping ("/**")
	@ResponseBody
	public byte[] home (HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletPath();
		try {
			if (!path.contains(".")) { path += "/index.html"; }
			// TODO: 4/8/17 Remove once we have a favicon.ico @solo
			if (path.contains("favicon.ico")) { return null; }

			ClassLoader classLoader = getClass().getClassLoader();
			return IOUtils.toByteArray(classLoader.getResourceAsStream(("site/" + path).replaceAll("/+", "/")));
		}
		catch (Exception e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found!\n --------- \n" + path);
			return null;
		}
	}

	@Bean
	public WebMvcConfigurer corsConfigurer () {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings (CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation () {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory () {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPersistenceUnitName("jpaPersistenceUnit");
		entityManagerFactory.setPackagesToScan("com.gosolar2");
		return entityManagerFactory;
	}

	@Bean
	public DataSource dataSource () {
		return DataSourceBuilder.create()
								.url("jdbc:sqlite:gosolar2.db")
								.driverClassName(databaseDriver)
								.build();
	}

	@GetMapping ("/resetServerData")
	public void resetServerData (HttpServletResponse response) throws Exception {
		logger.debug("Resetting server data to default data.");

		PreparedStatement preparedStatementToGetCreateTableScripts = dataSource().getConnection().prepareStatement("SELECT group_concat(sql,';') FROM sqlite_master;");
		String dbCreateTableScript = preparedStatementToGetCreateTableScripts.executeQuery().getString(1);
		preparedStatementToGetCreateTableScripts.close();
		new File("gosolar2.db").delete();


		ClassLoader classLoader = getClass().getClassLoader();

		List<String> createScripts = new ArrayList<>();
		ScriptUtils.splitSqlScript(dbCreateTableScript, ";", createScripts);
		createScripts.forEach(this::executeSingleStatementOnDb);

		List<String> populateDatabaseStatementsList = new ArrayList<>();
		String populateDatabaseStatements = IOUtils.toString(classLoader.getResourceAsStream("gosolar2.sql"));
		ScriptUtils.splitSqlScript(populateDatabaseStatements, ";", populateDatabaseStatementsList);
		populateDatabaseStatementsList.forEach(this::executeSingleStatementOnDb);

		response.sendRedirect("");
	}

	private void executeSingleStatementOnDb (String statement) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = dataSource().getConnection().prepareStatement(statement);
			preparedStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				preparedStatement.close();
			}
			catch (Exception ignored) {
			}
		}
	}
}