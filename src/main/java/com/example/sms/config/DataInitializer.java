
package com.example.sms.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.sms.entity.User;
import com.example.sms.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner createAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (!userRepository.existsByUsername("admin")) {

                User admin = new User();

                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(
                    passwordEncoder.encode("Admin@123")
                );
                admin.setRole("ADMIN");
                admin.setEnabled(true);

                userRepository.save(admin);

                System.out.println("Default admin account created!");
            }
        };
    }
}
