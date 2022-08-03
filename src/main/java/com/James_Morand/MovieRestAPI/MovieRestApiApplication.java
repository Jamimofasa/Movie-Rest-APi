package com.James_Morand.MovieRestAPI;

import com.James_Morand.MovieRestAPI.Movie.Movie;
import com.James_Morand.MovieRestAPI.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
//@EnableSwagger2
@EnableJpaRepositories(basePackages = "com.James_Morand.MovieRestAPI")
public class MovieRestApiApplication {

	// To Access Swagger: http://localhost:8080/swagger-ui/index.html#/
	// To access H2 : http://localhost:8080/h2-console/

	public static void main(String[] args) {
		SpringApplication.run(MovieRestApiApplication.class, args);
	}



}
