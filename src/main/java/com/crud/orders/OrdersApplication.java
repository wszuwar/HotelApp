package com.crud.orders;

import com.crud.orders.model.AppUser;
import com.crud.orders.model.Role;
import com.crud.orders.repository.RoleRepository;
import com.crud.orders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class OrdersApplication implements CommandLineRunner {


//public class OrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	/*
	 * This method will run during application startup and execute all the codes inside this method
	 *
	 */
	@Override
	public void run(String... arg0) throws Exception {

		if (roleRepository.findAll().isEmpty()) {
			AppUser admin = new AppUser();
			admin.setActive(true);
			admin.setPassword(encoder.encode("password"));
			admin.setUsername("admin");
			admin.setRoles(Arrays.asList(new Role("ADMIN")));
			userRepository.save(admin);

			Role roleUser = new Role();
			roleUser.setRole("USER");
			roleRepository.save(roleUser);
		}


	}

}
