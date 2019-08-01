package com.crud.orders;

import com.crud.orders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//public class OrdersApplication implements CommandLineRunner {
public class OrdersApplication{

		public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	/*
	 * This method will run during application startup and execute all the codes inside this method
	 *
	 */
//	@Override
//	public void run(String... arg0) throws Exception {
//		//Remove or comment this part after first execution of application,
//		//or else duplicate data will be inserted in the database
//		AppUser admin = new AppUser();
//		admin.setActive(true);
//		admin.setPassword(encoder.encode("password"));
//		admin.setUsername("admin");
//		admin.setRoles(Arrays.asList(new Role("ADMIN")));
//		userRepository.save(admin);
//
//	}
}
