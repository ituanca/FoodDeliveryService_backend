package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.mail.Address;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Configuration
public class Config {

    private ZoneService zoneService;
    private AdminService adminService;
    private CategoryService categoryService;
    private  CustomerService customerService;
    private MenuRepository menuRepository;

    @Autowired
    public Config(ZoneService zoneService, AdminService adminService, CategoryService categoryService, CustomerService customerService, MenuRepository menuRepository) {
        this.zoneService = zoneService;
        this.adminService = adminService;
        this.categoryService = categoryService;
        this.customerService = customerService;
        this.menuRepository = menuRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, RestaurantRepository restaurantRepository){
        return args -> {
            User admin = new User("admin@yahoo.com", "admin1", new BCryptPasswordEncoder().encode("password123"), "Admin");
            admin.setType("Admin");
            userRepository.save(admin);

            List<Zone> zones;
            zones = zoneService.findAll();
            List<Zone> zonesToBeAdded = new ArrayList<Zone>();
            zonesToBeAdded.add(zones.get(0));
            zonesToBeAdded.add(zones.get(1));

            Menu menu = new Menu();
            menuRepository.save(menu);

            Restaurant restaurant = new Restaurant("Crinul Alb", "str.Macesilor, nr.56", zonesToBeAdded, menu, admin);
            restaurantRepository.save(restaurant);
        };
    }

//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository repository){
//        return args -> {
//            User customer1 = new Customer("ituanca", "password123", "Anca Itu", "ituanca@yahoo.com");
//            User customer2 = new Customer("mihaiStan", "pass12345", "Mihai Stan", "mihaiStan@yahoo.com");
//            User customer3 = new Customer("cristinalorenatanase", "14372813agdr", "Cristina Lorena Tanase", "cristinaLorena@yahoo.com");
//            User customer4 = new Customer("dianaion", "mxuwt1793SRV", "Diana Ion", "iondiana10@yahoo.com");
//            User customer5 = new Customer("denisCristian", "qstq5830dO", "Denis Cristian", "deniscristian@yahoo.com");
//            customer1.setType("Client");
//            customer2.setType("Client");
//            customer3.setType("Client");
//            customer4.setType("Client");
//            customer5.setType("Client");
//            repository.saveAll(List.of(customer1, customer2, customer3, customer4, customer5));
//        };
//    }

//    @Bean
//    CommandLineRunner commandLineRunner(ZoneRepository repository){
//        return args -> {
//            Zone zone1 = new Zone("Andrei Muresanu");
//            Zone zone2 = new Zone("Becas");
//            Zone zone3 = new Zone("Borhanci");
//            Zone zone4 = new Zone("Bulgaria");
//            Zone zone5 = new Zone("Buna Ziua");
//            Zone zone6 = new Zone("Centru");
//            Zone zone7 = new Zone("Dambul Rotund");
//            Zone zone8 = new Zone("Europa");
//            Zone zone9 = new Zone("Faget");
//            Zone zone10 = new Zone("Gheorgheni");
//            Zone zone11 = new Zone("Gradini Manastur");
//            Zone zone12 = new Zone("Grigorescu");
//            Zone zone13 = new Zone("Gruia");
//            Zone zone14 = new Zone("Iris");
//            Zone zone15 = new Zone("Intre Lacuri");
//            Zone zone16 = new Zone("Manastur");
//            Zone zone17 = new Zone("Marasti");
//            Zone zone18 = new Zone("Someseni");
//            Zone zone19 = new Zone("Sopor");
//            Zone zone20 = new Zone("Zorilor");
//
//            repository.saveAll(List.of(zone1, zone2, zone3, zone4, zone5, zone6, zone7, zone8, zone9,
//                    zone10, zone11, zone12, zone13, zone14, zone15, zone16, zone17, zone18, zone19, zone20));
//        };
//    }

//    @Bean
//    CommandLineRunner commandLineRunner(CategoryRepository repository){
//        return args -> {
//            Category category1 = new Category("Starters");
//            Category category2 = new Category("Breakfast");
//            Category category4 = new Category("Cuisines");
//            Category category5 = new Category("Pizza");
//            Category category6 = new Category("Pasta");
//            Category category7 = new Category("Rice");
//            Category category8 = new Category("Salad");
//            Category category9 = new Category("Burgers");
//            Category category10 = new Category("Soups");
//            Category category11 = new Category("Meat dishes");
//            Category category12 = new Category("Side dishes");
//            Category category13 = new Category("Dessert");
//            Category category14 = new Category("Drinks");
//
//           // mongoTemplate.save(category1);
//            repository.saveAll(List.of(category1, category2, category4, category5, category6, category7,
//                    category8, category9, category10, category11, category12, category13, category14));
//        };
//    }

    // encrypt all admins
//    @Bean
//    CommandLineRunner commandLineRunner(AdminRepository repository){
//        return args -> {
//          List<Admin> admins = adminService.findAll();
//          BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//          for(Admin a : admins){
//              if(!a.getPassword().startsWith("$2a$10")){
//                  System.out.println(a.getPassword());
//                  a.setPassword( bCryptPasswordEncoder.encode(a.getPassword()));
//              }
//          }
//          repository.saveAll(admins);
//        };
//    }

//    @Bean
//    CommandLineRunner commandLineRunner(CustomerRepository repository){
//        return args -> {
//            List<Customer> customers = customerService.findAll();
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//            for(Customer c: customers){
//                if(!c.getPassword().startsWith("$2a$10")){
//                    System.out.println(c.getPassword());
//                    c.setPassword( bCryptPasswordEncoder.encode(c.getPassword()));
//                }
//            }
//            repository.saveAll(customers);
//        };
//    }


}
