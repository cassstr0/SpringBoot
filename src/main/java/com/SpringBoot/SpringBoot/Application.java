package com.SpringBoot.SpringBoot;

import com.SpringBoot.SpringBoot.Repository.LaptopRepository;
import com.SpringBoot.SpringBoot.Entities.Laptop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop laptop1 = new Laptop(null, "Dell", "Inspiron", 699.95);
		Laptop laptop2 = new Laptop(null, "Apple", "MacBook Pro", 1095.90);

		repository.save(laptop1);
		repository.save(laptop2);

		repository.findAll();
	}
}
