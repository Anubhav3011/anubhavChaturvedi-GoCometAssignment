package com.ac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.ac.entity.Role;
import com.ac.entity.User;
import com.ac.service.RoleService;
import com.ac.service.UserService;

@SpringBootApplication(scanBasePackages = "com.ac")
public class WebsiteCrawlerApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WebsiteCrawlerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebsiteCrawlerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Adding role - ADMIN
		roleService.save(Role.builder().name("ADMIN").build());

		// Adding user - admin having password & role same as username
		userService.save(
				User.builder().username("admin").password("admin").userRoles(roleService.getByNames("ADMIN")).build());

	}

}
