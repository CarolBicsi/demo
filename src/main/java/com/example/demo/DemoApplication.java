package com.example.demo;

import com.example.demo.Zoo.Zoo;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * @author Y7993
 */
@SpringBootApplication
@MapperScan("com.example.demo.mapper") // 确保包路径正确
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner runZoo(Zoo zoo) {
        return args -> {
            zoo.animalSounds();
        };
    }
    @Component
    public static class UserCommandLineRunner implements CommandLineRunner {

        @Autowired
        private UserRepository userRepository;

        @Override
        public void run(String... args) throws Exception {
            User user = userRepository.findById(1L).orElse(new User());
            System.out.println("用户姓名: " + user.getName());
            System.out.println("用户年龄: " + user.getAge());
        }
    }
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}

