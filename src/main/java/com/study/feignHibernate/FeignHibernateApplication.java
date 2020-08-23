package com.study.feignHibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.study.feignHibernate.Service.PostService;

@EnableFeignClients
@SpringBootApplication
public class FeignHibernateApplication implements CommandLineRunner {

	@Autowired
	private PostService postService;

	public static void main(String[] args) {
		SpringApplication.run(FeignHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// feignTestGetAll();
		// feignTestGetOne();
		try {
			postService.hibernateSaveAndShowAllWithOneErrorToRollback();
		} catch (Exception e) {}
		postService.list();

	}
}
